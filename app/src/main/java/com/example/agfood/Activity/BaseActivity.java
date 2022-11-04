package com.example.agfood.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;

import com.example.agfood.Fragment.FragmentDetailMakanan;
import com.example.agfood.Fragment.FragmentKeranjang;
import com.example.agfood.Fragment.HomeFragment;
import com.example.agfood.Fragment.LoginFragment;
import com.example.agfood.Fragment.SplashScreenFragment;
import com.example.agfood.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        hiddenActionBar();
        getSupportFragmentManager().beginTransaction().replace(R.id.id_base_frame_layout, new SplashScreenFragment()).commit();
    }
    void hiddenActionBar(){
        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
    }
}