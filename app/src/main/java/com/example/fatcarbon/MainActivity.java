package com.example.fatcarbon;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private final Context context = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        }



    public void login(View v) {
        EditText userName = findViewById(R.id.editTextUserName);
        EditText passWord = findViewById(R.id.editTextPasswd);
        PasswordValidator pv = new PasswordValidator();
        System.out.println("HERE");
        if (userName.length() == 0){
            userName.setError(getString(R.string.pl_enter_uname));
        }
        if (!pv.validatePassword(passWord.getText().toString())){
            passWord.setError(getString(R.string.pl_enter_password));
        }

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
    }



    }

