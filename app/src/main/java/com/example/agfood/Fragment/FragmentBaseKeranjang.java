package com.example.agfood.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.agfood.R;
import com.example.agfood.Util.Util;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentBaseKeranjang#newInstance} factory method to
 * create an instance of this fragment.
 */


public class FragmentBaseKeranjang extends Fragment {
    private class ViewPagerFragmentAdapter extends FragmentStateAdapter {

        public ViewPagerFragmentAdapter( FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        // return fragments at every position

        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new FragmentKeranjang(); // calls fragment
                case 1:
                    return new FragmentOrderanSaya(); // chats fragment
            }
            return new FragmentKeranjang(); // calls fragment
        }

        // return total number of tabs in our case we have 3
        @Override
        public int getItemCount() {
            return labelFragment.length;
        }
    }
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    String state;
    public FragmentBaseKeranjang(){

    }
    public FragmentBaseKeranjang(String state) {
        // Required empty public constructor
        this.state =state;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentBaseKeranjang.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentBaseKeranjang newInstance(String param1, String param2) {
        FragmentBaseKeranjang fragment = new FragmentBaseKeranjang();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    String labelFragment[] = new String[]{
            "Keranjang Saya" , "Orderan Saya"
    };
    ViewPager2 viewPager2;
    TabLayout tabLayout;
    ViewPagerFragmentAdapter adapter;
    ImageView imgBack;

    private void init(View v) {
        // initialize tabLayout
        tabLayout = v.findViewById(R.id.id_fragment_keranjang_tab_layout_parent);
        // initialize viewPager2
        viewPager2 = v.findViewById(R.id.view_pager2);
        // create adapter instance
        imgBack = v.findViewById(R.id.id_btn_detail_makanan_back_button);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeFragment homeFragment = new HomeFragment();
                homeFragment.setState("STATE_KERANJANG");
                Util.switchFragment(getActivity().getSupportFragmentManager(),homeFragment,"FRAGMENT_HOME");
            }
        });
        adapter = new ViewPagerFragmentAdapter(getActivity());
        // set adapter to viewPager2
        viewPager2.setAdapter(adapter);
        // remove default elevation of actionbar
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            tab.setText(labelFragment[position]);
        }).attach();

        // set default position to 1 instead of default 0
        if(state == "KRJ"){
            viewPager2.setCurrentItem(1, false);
        }else{
            viewPager2.setCurrentItem(0, false);
        }


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_base_keranjang, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }
}