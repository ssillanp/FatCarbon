package com.example.fatcarbon;

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

public class MainActivity extends AppCompatActivity {

    private final Context context = MainActivity.this;
    FragmentManager manager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Fragment frag = new LoginScreen();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.main_ragment_window, frag);
        transaction.commit();



    }


    public void login(View v) {
        EditText userName = findViewById(R.id.editTextUserName);
        EditText passWord = findViewById(R.id.editTextPasswd);
        userName.setText("Sami");
        passWord.setText("aaa");
        User user = (User) new UserDataReader(context).readItem(userName.getText().toString());
        if (user != null) {
            if (user.getPasswordHasher().validatePassword(passWord.getText().toString())){
                Intent intent = new Intent(this, MainActivityLoggedIn.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        } else {
            Snackbar.make(v, R.string.user_not_exist, Snackbar.LENGTH_LONG).show();
        }

    }

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
        boolean dataOK = true;
        if (new UserDataReader(context).readItem(uname.getText().toString()) != null){
            uname.setError(getString(R.string.user_exists));
            dataOK = false;
        }
        if (uname.length() == 0){
            uname.setError(getString(R.string.pl_enter_uname));
            dataOK = false;
        }
//        if (!pv.validatePassword(password.getText().toString())){
//            password.setError(getString(R.string.pl_enter_password));
//            dataOK = false;
//        }
        if (dataOK){
            PasswordHasher hasher = new PasswordHasher(password.getText().toString());
            User user = new User(uname.getText().toString(), hasher);
            try {
                user.setHeight(Double.parseDouble(height.getText().toString()));
                user.setStartWeight(Double.parseDouble(weight.getText().toString()));
                user.setAge(Integer.parseInt(height.getText().toString()));
                switch (Integer.parseInt(height.getText().toString())){
                    case 0: user.setActivityLevel(User.actLevel.INACTIVE);
                    case 1: user.setActivityLevel(User.actLevel.OCCASIONAL);
                    case 2: user.setActivityLevel(User.actLevel.REGULAR);
                    case 3: user.setActivityLevel(User.actLevel.ACTIVE);
                    case 4: user.setActivityLevel(User.actLevel.PRO);
                }
                if (female.isSelected()){
                    user.setSex(User.sexes.FEMALE);
                } else {
                    user.setSex(User.sexes.MALE);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            UserDataWriter udw = new UserDataWriter(context);
            udw.writeItem(user);
            Intent intent = new Intent(this, MainActivityLoggedIn.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("user", user);
            startActivity(intent);
        } else {
            Snackbar.make(v, R.string.signup_error_snackbar_msg, Snackbar.LENGTH_LONG).show();
        }

    }

    public void toSubmit(View v) {
        Fragment frag = new SignUp();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.main_ragment_window, frag);
        transaction.commit();
    }

    public void toLogin(View v) {
        Fragment frag = new LoginScreen();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.main_ragment_window, frag);
        transaction.commit();

    }


//    public void login(View v) {
//        EditText userName = findViewById(R.id.editTextUserName);
//        EditText passWord = findViewById(R.id.editTextPasswd);
//        PasswordValidator pv = new PasswordValidator();
//        System.out.println("HERE");
//        if (userName.length() == 0){
//            userName.setError(getString(R.string.pl_enter_uname));
//        }
//        if (!pv.validatePassword(passWord.getText().toString())){
//            passWord.setError(getString(R.string.pl_enter_password));
//        }

//        FoodItem fi = new FoodItem();
//        DiaryItem di = new DiaryItem(fi);
//        System.out.println(di.getItem().getClass());
//        System.out.println(di.getItem() instanceof FoodItem);
//        System.out.println(di.getDate());

    /**
     Shared prefs testing
     */
//        SharedPreferences sharedPreferences = getSharedPreferences("FatCarbSharedPref",MODE_PRIVATE);
//        SharedPreferences.Editor myEdit = sharedPreferences.edit();
//        myEdit.putStringSet("uname", new HashSet<String>(Arrays.asList("a", "b", "c")));
//        myEdit.commit();
//        SharedPreferences SH = getSharedPreferences("FatCarbSharedPref", MODE_APPEND);
//        System.out.println(SH.getStringSet("uname", new HashSet<String>()).toString());

    /**
     Serialization tests
     */

//        UserDataReader rd = new UserDataReader(context);
//        User user = (User) rd.readItem("Sama");
//        System.out.println(user.validateUser("Sama", "Password124"));
//        System.out.println(user.getPasswordHasher().validatePassword("Password124"));
    /**
     PWD Hasher testing
     */
//        PasswordHasher pw = new PasswordHasher("Password123");
//        PasswordHasher pw1 = new PasswordHasher("Password124");
//        User usr = new User("Sami", pw);
//        User usr1 = new User("Sama", pw1);
//        UserDataWriter dw = new UserDataWriter(context);
//        dw.writeItem(usr);
//        dw.writeItem(usr1);
    /**
     Fineli Api tests
     */
//        FineliApi api = new FineliApi();
//        for (FoodItem item:api.parseFineliData("Kala")){
//            System.out.println(item.getName().toString());
//        }
//    }


}

