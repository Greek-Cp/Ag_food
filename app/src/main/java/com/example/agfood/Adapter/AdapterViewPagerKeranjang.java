package com.example.agfood.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.agfood.Fragment.FragmentCekTransaksi;

public class AdapterViewPagerKeranjang extends FragmentStateAdapter {

    private String[] tittles =  new String[]{"Keranjang Saya","Orderan Saya"};
    public AdapterViewPagerKeranjang(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new FragmentCekTransaksi();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return tittles.length;
    }
}

