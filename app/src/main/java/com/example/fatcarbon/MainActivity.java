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
//        User usr = new User();
//        DataWriter dw = new DataWriter(context);
//        dw.writeItems(usr);
//        User iii = (User) dw.readItems();
//        System.out.println(iii.getUsername());
            FineliApi api = new FineliApi();
            for (FoodItem item:api.parseFineliData("Kala")){
                System.out.println(item.getName().toString());
            }
    }



    }

