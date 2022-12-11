package com.example.agfood.Util;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.agfood.API.APIRequestData;
import com.example.agfood.API.BaseServerApp;
import com.example.agfood.Fragment.UtilPref;
import com.example.agfood.Model.ModelAccount;
import com.example.agfood.Model.ModelBarang;
import com.example.agfood.Model.ModelRetrieveAccount;
import com.example.agfood.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;


public class Util {

    public static void switchFragment(Fragment fragmentTarget , FragmentActivity activityFragment){
        activityFragment.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in,R.anim.fade_out,R.anim.fade_in,R.anim.slide_out).replace(R.id.id_base_frame_layout,fragmentTarget).commit();
    }

    public static void hiddenNavBottom(FragmentActivity activity){
        activity.findViewById(R.id.id_nav_bar).setVisibility(View.INVISIBLE);
    }
    public static void showNavBottom(FragmentActivity activity){
        activity.findViewById(R.id.id_nav_bar).setVisibility(View.VISIBLE);
    }
    public static void switchFragment(FragmentManager fragmentManager, Fragment fragment ,String TAG){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.id_base_frame_layout,fragment).commit();
    }
    public static void setCustomColorText(TextView mTextViewTarget ,String oldText , String coloredText , String coloredHex){
        mTextViewTarget.setText(Html.fromHtml(oldText + "<font color=\"#" +  coloredHex + "\">" + coloredText));
    }
    public static void setCustomColorText(TextView mTextViewtTarget , String startText , String midText , String endText , String startEndColoredText , String coloredTargetHex){
        mTextViewtTarget.setText(Html.fromHtml(startText + " " +"<font color=\"#" + coloredTargetHex + "\">" + midText + "</>" + " " + endText));
    }
    public static void setNavigationBarColor(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            // getActivity().getWindow().setNavigationBarColor(getResources().getColor(R.color.green_background));
        }
    }
        public static String convertToRupiah(int totalRupiah){
            Locale myIndonesianLocale = new Locale("in", "ID");
            NumberFormat formater = NumberFormat.getCurrencyInstance(myIndonesianLocale);
        return formater.format(totalRupiah).replace(",00" ,"");
    }
    public static void saveDataListPrefences(SharedPrefDetail sharedPrefDetail,Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(sharedPrefDetail.getNameSharedPref(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        System.out.println(sharedPrefDetail.getSerializeDataList() + "save list");
        editor.putString(sharedPrefDetail.getNameKey(), sharedPrefDetail.getSerializeDataList());
        editor.commit();
        System.out.println("Pref name = " + sharedPrefDetail.getNameSharedPref());
        System.out.println("Key name = " + sharedPrefDetail.getNameKey());
        System.out.println("get pref = " + sharedPreferences.getString(sharedPrefDetail.getNameKey(), ""));
    }
    public static ModelAccount getCurrentAccount(SharedPrefDetail sharedPrefDetail,Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(sharedPrefDetail.getNameSharedPref(), Context.MODE_PRIVATE);
        Type typeAccount = new TypeToken<ModelAccount>(){}.getType();
        Gson gson = new Gson();
        System.out.println("account = " + sharedPrefDetail.getNameSharedPref());
        System.out.println("account key = " + sharedPrefDetail.getNameKey());
        ModelAccount account =gson.fromJson(sharedPreferences.getString(sharedPrefDetail.getNameKey(), ""),typeAccount);
        return account;
    }
    public static void logoutCurrentAccount(SharedPrefDetail sharedPrefDetail,Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(sharedPrefDetail.getNameSharedPref(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(sharedPrefDetail.getNameKey(), "");
        editor.commit();
    }
    public static APIRequestData getApiRequetData(){
        APIRequestData apiRequestData = BaseServerApp.konekRetrofit().create(APIRequestData.class);
        return apiRequestData;
    }
    public static void resetButtonAdapter(Activity activity){
        SharedPreferences sharedPreferences = activity.getSharedPreferences("PREF_BUTTON", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().commit();


    }
    public static List<ModelBarang> getListMenuMakanDariPref(SharedPrefDetail sharedPrefDetail, Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(sharedPrefDetail.getNameSharedPref(), Context.MODE_PRIVATE);
        Gson gson = new Gson();
        Type typeMenuMakanan = new TypeToken<List<ModelBarang>>(){}.getType();
        List<ModelBarang> listBarang = gson.fromJson(sharedPreferences.getString(sharedPrefDetail.getNameKey(),"" ), typeMenuMakanan);
        return listBarang;
    }
}
