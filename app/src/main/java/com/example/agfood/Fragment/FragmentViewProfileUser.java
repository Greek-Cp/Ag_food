package com.example.agfood.Fragment;

import android.os.Bundle;

import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.agfood.Adapter.AdapterMenuProfile;
import com.example.agfood.DataModel.ModelResponseAccount;
import com.example.agfood.Dialog.DialogHelper;
import com.example.agfood.Model.ModelAccount;
import com.example.agfood.ModelAdapter.ModelAdapterViewProfile;
import com.example.agfood.R;
import com.example.agfood.Util.SharedPrefDetail;
import com.example.agfood.Util.Util;
import com.example.agfood.databinding.FragmentViewProfileUserBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.sanju.motiontoast.MotionToast;
import www.sanju.motiontoast.MotionToastStyle;

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
        listMenuButtonTop.add(new ModelAdapterViewProfile("Pesanan Saya", R.drawable.ic_menu_pesanan));
        listMenuButtonTop.add(new ModelAdapterViewProfile("Log-Out Akun", R.drawable.ic_menu_logout));
    }
    public void initializeItemButtonBottom(){
        listMenuButtonBottom.add(new ModelAdapterViewProfile("Tentang Aplikasi", R.drawable.ic_menu_about));
        listMenuButtonBottom.add(new ModelAdapterViewProfile("Hubungi Kami",R.drawable.ic_menu_direct_message));
    }
    ModelAccount mdl;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        FragmentViewProfileUserBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_view_profile_user,container , false);
        initializeItemButtonTop();
        initializeItemButtonBottom();
        UtilPref utilPref = new UtilPref();
        SharedPrefDetail sharedPrefDetailAccount = utilPref.accountPrefences;
        mdl = Util.getCurrentAccount(sharedPrefDetailAccount, getActivity());

        binding.idTvnameProfile.setText(mdl.getNamaLengkap());
        binding.idTvusernameProfile.setText(mdl.getUsername());
        AdapterMenuProfile.AdapterMenuProfileListener adapterMenuProfileListenerTop = new AdapterMenuProfile.AdapterMenuProfileListener() {
            @Override
            public void clickMenuItemListener(int position) {
                switch (position){
                    case 0:
                        Util.switchFragment(getActivity().getSupportFragmentManager(),new FragmentUserProfileSetting(),"PROFILE_SETTING");
                        break;
                    case 1:
                        break;
                    case 2:
                        DialogHelper dialogHelper;
                        DialogHelper.DialogListener dialogListener = new DialogHelper.DialogListener() {
                            @Override
                            public void clickIya() {
                                UtilPref utilPref = new UtilPref();
                                Util.getApiRequetData().logoutSession(mdl.getEmail()).enqueue(new Callback<ModelResponseAccount>() {
                                    @Override
                                    public void onResponse(Call<ModelResponseAccount> call, Response<ModelResponseAccount> response) {
                                        System.out.println("RESPONSE == " + response.body().pesan + ":" + response.body().kode);
                                        if(response.body().kode == 1){
                                            MotionToast.Companion.createColorToast(getActivity(), "Logout ",
                                                    "Log-Out Berhasil", MotionToastStyle.SUCCESS,MotionToast.GRAVITY_TOP,MotionToast.LONG_DURATION, ResourcesCompat.getFont(getActivity().getApplicationContext(),R.font.sfprodisplayregular
                                                    ));
                                            Util.logoutCurrentAccount(utilPref.accountPrefences,getActivity());
                                            Util.switchFragment(getActivity().getSupportFragmentManager(),new LoginFragment(),"FRAGMENT_LOGIN");
                                        } else {
                                            MotionToast.Companion.createColorToast(getActivity(),"Logout","Log-out Account Gagal",
                                                    MotionToastStyle.ERROR,MotionToast.GRAVITY_CENTER,MotionToast.LONG_DURATION,ResourcesCompat.getFont(getActivity().getApplicationContext(),R.font.sfprodisplayregular));
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ModelResponseAccount> call, Throwable t) {

                                    }
                                });
                            }
                            @Override
                            public void clickTidak() {
                           }
                        };
                        dialogHelper = new DialogHelper(dialogListener);
                        dialogHelper.show(getParentFragmentManager(),"Test");
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