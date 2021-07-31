package com.example.fatcarbon.ui;

/**************************************
 LUT Olio-ohjelmointi Harjoitustyö
 @author Sami Sillanpää
 @copyright Sami Sillanpää 2021
 @licence GNU GPL3.0
 **************************************/

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import com.example.fatcarbon.R;
import com.example.fatcarbon.app.*;

public class MainActivity extends AppCompatActivity {
    /**
    Main activity. App lands here upon start
     functionality for login and signup actions.
     */

    private final Context context = MainActivity.this;
    FragmentManager manager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        // Show login Screen
        Fragment frag = new LoginScreen();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.main_ragment_window, frag);
        transaction.commit();


    }

    // login action
    public void login(View v) {
        EditText userName = findViewById(R.id.editTextUserName);
        EditText passWord = findViewById(R.id.editTextPasswd);
        // Read the user from storage
        User user = (User) new UserDataReader(context).readItem(userName.getText().toString());
        // if user exists start the logged in activity
        if (user != null) {
            // check the user password
            if (user.getPasswordHasher().validatePassword(passWord.getText().toString())) {
                Intent intent = new Intent(this, MainActivityLoggedIn.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("user", user);
                startActivity(intent);
            } else {
                // show field error if password wrong
                passWord.setError(getString(R.string.pwd_wrong));
            }
            // if user does not exist, show message
        } else {
            Snackbar.make(v, R.string.user_not_exist, Snackbar.LENGTH_LONG).show();
        }

    }

    // Submit action
    public void submit(View v) {
        EditText uname = findViewById(R.id.editTextSetUserName);
        EditText password = findViewById(R.id.editTextSetPassword);
        EditText height = findViewById(R.id.editTextHeight);
        EditText weight = findViewById(R.id.editTextWeight);
        EditText age = findViewById(R.id.editTextAge);
        SeekBar act_level = findViewById(R.id.seekBarActLevel);
        RadioButton female = findViewById(R.id.checkBoxFemale);
        RadioButton male = findViewById(R.id.checkBoxMale);
        PasswordValidator pv = new PasswordValidator();
        // dataOK, to validate all values.
        boolean dataOK = true;
        // Check if user with given username already exists
        if (new UserDataReader(context).readItem(uname.getText().toString()) != null) {
            uname.setError(getString(R.string.user_exists));
            dataOK = false;
        }
        // check that username is not empty
        if (uname.length() == 0) {
            uname.setError(getString(R.string.pl_enter_uname));
            dataOK = false;
        }
        // check that password given meets the criteria.
        if (!pv.validatePassword(password.getText().toString())){
            password.setError(getString(R.string.pl_enter_password));
            dataOK = false;
        }
        //check that all fields are filled
        if (height.getText().toString().equals("")){
            height.setError(getString(R.string.fill_all_values));
            dataOK = false;
        }
        if (weight.getText().toString().equals("")){
            weight.setError(getString(R.string.fill_all_values));
            dataOK = false;
        }
        if (age.getText().toString().equals("")){
            age.setError(getString(R.string.fill_all_values));
            dataOK = false;
        }
        if (female.isSelected()&!male.isSelected()){
            female.setError(getString(R.string.sex_must_selected));
            dataOK = false;
        }
        // if all filled and meet criteria, create new user and save
        if (dataOK) {
            PasswordHasher hasher = new PasswordHasher(password.getText().toString());
            User user = new User(uname.getText().toString(), hasher);
            try {
                user.setHeight(Double.parseDouble(height.getText().toString()));
                user.setStartWeight(Double.parseDouble(weight.getText().toString()));
                user.setTargetWeight(user.getStartWeight() * 0.9);
                user.setAge(Integer.parseInt(age.getText().toString()));
                switch (act_level.getProgress()) {
                    case 0:
                        user.setActivityLevel(User.actLevel.INACTIVE);
                        break;
                    case 1:
                        user.setActivityLevel(User.actLevel.OCCASIONAL);
                        break;
                    case 2:
                        user.setActivityLevel(User.actLevel.REGULAR);
                        break;
                    case 3:
                        user.setActivityLevel(User.actLevel.ACTIVE);
                        break;
                    case 4:
                        user.setActivityLevel(User.actLevel.PRO);
                        break;

                }
                if (female.isSelected()) {
                    user.setSex(User.sexes.FEMALE);
                } else {
                    user.setSex(User.sexes.MALE);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            //save user in storage
            UserDataWriter udw = new UserDataWriter(context);
            udw.writeItem(user);
            // start logged In activity
            Intent intent = new Intent(this, MainActivityLoggedIn.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("user", user);
            startActivity(intent);
        } else {
            Snackbar.make(v, R.string.signup_error_snackbar_msg, Snackbar.LENGTH_LONG).show();
        }

    }

    // navigate to Submit fragment
    public void toSubmit(View v) {
        Fragment frag = new SignUpScreen();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.main_ragment_window, frag);
        transaction.commit();
    }
    // navigate to login fragment
    public void toLogin(View v) {
        Fragment frag = new LoginScreen();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.main_ragment_window, frag);
        transaction.commit();

    }


}

