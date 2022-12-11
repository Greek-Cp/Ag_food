package com.example.agfood.Fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.agfood.Model.ModelAccount;
import com.example.agfood.R;
import com.example.agfood.Util.SharedPrefDetail;
import com.example.agfood.Util.Util;
import com.example.agfood.databinding.FragmentUserProfileSettingBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentUserProfileSetting#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentUserProfileSetting extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentUserProfileSetting() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentUserProfileSetting.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentUserProfileSetting newInstance(String param1, String param2) {
        FragmentUserProfileSetting fragment = new FragmentUserProfileSetting();
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

    ModelAccount mdl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentUserProfileSettingBinding fragmentUserProfileSettingBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_user_profile_setting,container
        ,false);
        Util.hiddenNavBottom(getActivity());

        UtilPref utilPref = new UtilPref();
        SharedPrefDetail sharedPrefDetailAccount = utilPref.accountPrefences;
        mdl = Util.getCurrentAccount(sharedPrefDetailAccount, getActivity());
        fragmentUserProfileSettingBinding.idEditTextEmail.setText(mdl.getEmail());
        fragmentUserProfileSettingBinding.idEditTextNoHandphone.setText(String.valueOf(mdl.getNoHp()));
        fragmentUserProfileSettingBinding.idEditTextUsername.setText(String.valueOf(mdl.getUsername()));
        fragmentUserProfileSettingBinding.idEditTextUsername.setEnabled(false);

        fragmentUserProfileSettingBinding.idBtnDetailMakananBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.switchFragment(getActivity().getSupportFragmentManager(),new HomeFragment(),"FRAGMENT_HOME");
            }
        });
        if(mdl.getStatusVerif().equals("1")){
            fragmentUserProfileSettingBinding.idEditTextStatusVerifikasi.setText("Status Terverifikasi");
            fragmentUserProfileSettingBinding.idEditTextStatusVerifikasi.setEnabled(false);
        } else{
            fragmentUserProfileSettingBinding.idEditTextStatusVerifikasi.setText("Belum Terverifikasi");
            fragmentUserProfileSettingBinding.idEditTextStatusVerifikasi.setEnabled(false);
        }
        return fragmentUserProfileSettingBinding.getRoot();
    }
}