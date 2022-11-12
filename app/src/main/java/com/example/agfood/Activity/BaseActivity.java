package com.example.agfood.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.agfood.Fragment.FragmentDetailMakanan;
import com.example.agfood.Fragment.FragmentKeranjang;
import com.example.agfood.Fragment.FragmentViewInformasi;
import com.example.agfood.Fragment.HomeFragment;
import com.example.agfood.Fragment.LoginFragment;
import com.example.agfood.Fragment.SplashScreenFragment;
import com.example.agfood.R;
import com.example.agfood.Util.Util;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class BaseActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        hiddenActionBar();
        getSupportFragmentManager().beginTransaction().replace(R.id.id_base_frame_layout, new SplashScreenFragment(),"FRAGMENT_SPLASH_SCREEN").commit();
        bottomNavigationView = findViewById(R.id.id_nav_bar);
        bottomNavigationSelected();
    }
    void bottomNavigationSelected(){
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.id_nav_keranjang:
                        Util.switchFragment(getSupportFragmentManager(),new FragmentKeranjang(),"FRAGMENT_KERANJANG");
                        break;
                    case R.id.id_nav_home:
                        Util.switchFragment(getSupportFragmentManager() , new HomeFragment() ,"FRAGMENT_HOME");
                        break;
                    case R.id.id_nav_informas:
                        Util.switchFragment(getSupportFragmentManager(),  new FragmentViewInformasi(),"FRAGMENT_INFORMASI");
                        break;
                }
                return true;

            }
        });
    }
    void hiddenActionBar(){
        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
    }
}