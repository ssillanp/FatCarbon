package com.example.fatcarbon;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private final Context context = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void testbtn(View v) {
        UserDataReader rd = new UserDataReader(context);
        User user = (User) rd.readItem("Sama");
        System.out.println(user.validateUser("Sama", "Password124"));
        System.out.println(user.getPasswordHasher().validatePassword("Password124"));

//        PasswordHasher pw = new PasswordHasher("Password123");
//        PasswordHasher pw1 = new PasswordHasher("Password124");
//        User usr = new User("Sami", pw);
//        User usr1 = new User("Sama", pw1);
//        UserDataWriter dw = new UserDataWriter(context);
//        dw.writeItem(usr);
//        dw.writeItem(usr1);



//            FineliApi api = new FineliApi();
//            for (FoodItem item:api.parseFineliData("Kala")){
//                System.out.println(item.getName().toString());
//            }
    }



    }

