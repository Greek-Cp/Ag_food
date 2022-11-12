package com.example.agfood.Util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.agfood.R;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
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
}
