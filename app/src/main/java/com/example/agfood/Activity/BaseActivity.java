package com.example.agfood.Activity;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.example.agfood.Fragment.FragmentCheckoutBarang;
import com.example.agfood.Fragment.FragmentDetailMakanan;
import com.example.agfood.Fragment.FragmentKeranjang;
import com.example.agfood.Fragment.FragmentResetPassword;
import com.example.agfood.Fragment.FragmentResetPasswordInputNewPassword;
import com.example.agfood.Fragment.FragmentSendOtp;
import com.example.agfood.Fragment.FragmentSuccesRegisterAlert;
import com.example.agfood.Fragment.FragmentViewInformasi;
import com.example.agfood.Fragment.FragmentViewProfileUser;
import com.example.agfood.Fragment.HomeFragment;
import com.example.agfood.Fragment.LoginFragment;
import com.example.agfood.Fragment.SplashScreenFragment;
import com.example.agfood.R;
import com.example.agfood.Util.Util;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BaseActivity extends AppCompatActivity {
    private ActivityResultLauncher<String[]> mActivityResultLauncher;
    private boolean isPermissionAccessReadExternalGranted = false;
    private boolean isPermissionAccessWriteExternalGranted = false;
    private boolean isPermissionAccessCoarseLocationGranted = false;
    private boolean isPermissionAccessFineLocation = false;
    private boolean isPermissionAccessInternet = false;
    private void permission(){
        mActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback<Map<String, Boolean>>() {
            @Override
            public void onActivityResult(Map<String, Boolean> result) {
                if(result.get(Manifest.permission.READ_EXTERNAL_STORAGE) != null){
                    isPermissionAccessReadExternalGranted =result.get(Manifest.permission.READ_EXTERNAL_STORAGE);
                }
                if(result.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) != null){
                    isPermissionAccessWriteExternalGranted = result.get(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                }
                if(result.get(Manifest.permission.ACCESS_FINE_LOCATION) != null){
                    isPermissionAccessFineLocation = result.get(Manifest.permission.ACCESS_FINE_LOCATION);
                }
                if(result.get(Manifest.permission.ACCESS_COARSE_LOCATION) != null){
                    isPermissionAccessCoarseLocationGranted = result.get(Manifest.permission.ACCESS_COARSE_LOCATION);
                }
                if(result.get(Manifest.permission.INTERNET) != null){
                    isPermissionAccessInternet = result.get(Manifest.permission.INTERNET);
                }
            }
        });
        requestPermission();
    }
    private void requestPermission(){
        isPermissionAccessReadExternalGranted = ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        isPermissionAccessWriteExternalGranted = ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        isPermissionAccessCoarseLocationGranted = ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        isPermissionAccessFineLocation = ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        isPermissionAccessInternet = ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED;

        List<String> listUngrantedPermission = new ArrayList<>();
        if(!isPermissionAccessReadExternalGranted){
            listUngrantedPermission.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if(!isPermissionAccessWriteExternalGranted){
            listUngrantedPermission.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if(!isPermissionAccessFineLocation){
            listUngrantedPermission.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if(!isPermissionAccessCoarseLocationGranted){
            listUngrantedPermission.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        if(!isPermissionAccessInternet){
            listUngrantedPermission.add(Manifest.permission.INTERNET);
        }
        mActivityResultLauncher.launch(listUngrantedPermission.toArray(new String[0]));
    }
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_base);
        permission();
        requestPermission();
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
                    case R.id.id_nav_akunsaya:
                        Util.switchFragment(getSupportFragmentManager(), new FragmentViewProfileUser(),"FRAGMENT_VIEW");
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