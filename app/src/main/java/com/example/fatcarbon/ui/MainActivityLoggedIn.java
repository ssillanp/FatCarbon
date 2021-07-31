package com.example.fatcarbon.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.fatcarbon.R;
import com.example.fatcarbon.app.FineliApi;
import com.example.fatcarbon.app.FoodItem;
import com.example.fatcarbon.app.User;
import com.example.fatcarbon.ui.foods.FoodSearchFragment;

import java.util.ArrayList;

public class MainActivityLoggedIn extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    FragmentManager manager = getSupportFragmentManager();
    DrawerLayout drawer;
    NavController navController;
    NavigationView navigationView;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_logged_in);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_weights, R.id.nav_foods, R.id.nav_activity, R.id.nav_settings)
                .setDrawerLayout(drawer)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main_activity_logged_in, menu);
//        return true;
//    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void foodSearch(View view) {
        EditText keyWord = findViewById(R.id.editTextFoodKeyword);
        FineliApi searchApi = new FineliApi(keyWord.getText().toString());
        ArrayList<FoodItem> listItems = (searchApi.parseFineliData());
        Bundle args = new Bundle();
        args.putSerializable("list", listItems);
        args.putSerializable("user", user);
        Fragment frag = new FoodSearchFragment();
        frag.setArguments(args);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.foodsFragmentLayout, frag);
        transaction.commit();

    }


}