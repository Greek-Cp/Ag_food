package com.example.agfood.Fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.agfood.Adapter.AdapterMenuProfile;
import com.example.agfood.ModelAdapter.ModelAdapterViewProfile;
import com.example.agfood.R;
import com.example.agfood.databinding.FragmentViewProfileUserBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentViewProfileUser#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentViewProfileUser extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentViewProfileUser() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentViewProfileUser.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentViewProfileUser newInstance(String param1, String param2) {
        FragmentViewProfileUser fragment = new FragmentViewProfileUser();
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

    List<ModelAdapterViewProfile> listMenuButtonTop = new ArrayList<>();
    List<ModelAdapterViewProfile> listMenuButtonBottom = new ArrayList<>();
    public void initializeItemButtonTop(){

        listMenuButtonTop.add(new ModelAdapterViewProfile("Akun Saya", R.drawable.ic_menu_profile));
        listMenuButtonTop.add(new ModelAdapterViewProfile("Pengaturan", R.drawable.ic_menu_setting));
        listMenuButtonTop.add(new ModelAdapterViewProfile("Pesanan Saya", R.drawable.ic_menu_pesanan));
        listMenuButtonTop.add(new ModelAdapterViewProfile("Favorit Saya", R.drawable.ic_menu_favorit_pesanan));
        listMenuButtonTop.add(new ModelAdapterViewProfile("Log-Out Akun", R.drawable.ic_menu_logout));
    }
    public void initializeItemButtonBottom(){
        listMenuButtonBottom.add(new ModelAdapterViewProfile("Tentang Aplikasi", R.drawable.ic_menu_about));
        listMenuButtonBottom.add(new ModelAdapterViewProfile("Hubungi Kami",R.drawable.ic_menu_direct_message));
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        FragmentViewProfileUserBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_view_profile_user,container , false);
        initializeItemButtonTop();
        initializeItemButtonBottom();
        AdapterMenuProfile.AdapterMenuProfileListener adapterMenuProfileListenerTop = new AdapterMenuProfile.AdapterMenuProfileListener() {
            @Override
            public void clickMenuItemListener(int position) {
                switch (position){
                    case 0:
                        Toast.makeText(getActivity().getApplicationContext(), "Click Item " + position,  Toast.LENGTH_LONG).show();
                    break;
                    case 1:
                        Toast.makeText(getActivity().getApplicationContext(), "Click Item " + position,  Toast.LENGTH_LONG).show();
                        break;

                }
            }
        };
        AdapterMenuProfile.AdapterMenuProfileListener adapterMenuProfileListenerBottom = new AdapterMenuProfile.AdapterMenuProfileListener() {
            @Override
            public void clickMenuItemListener(int position) {

            }
        };
        AdapterMenuProfile adapterMenuProfileTop = new AdapterMenuProfile(adapterMenuProfileListenerTop,listMenuButtonTop);
        AdapterMenuProfile adapterMenuProfileBottom = new AdapterMenuProfile(adapterMenuProfileListenerBottom,listMenuButtonBottom);
        binding.idRecviewMenuTop.setAdapter(adapterMenuProfileTop);
        binding.idRecViewMenuBottom.setAdapter(adapterMenuProfileBottom);
        return binding.getRoot();
    }
}