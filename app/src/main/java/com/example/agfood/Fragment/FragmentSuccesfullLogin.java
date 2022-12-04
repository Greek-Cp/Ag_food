package com.example.agfood.Fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.example.agfood.Model.ModelAccount;
import com.example.agfood.Model.ModelDetailAccount;
import com.example.agfood.Model.ModelRetrieveAccount;
import com.example.agfood.R;
import com.example.agfood.Util.Util;
import com.example.agfood.databinding.FragmentSuccesfullLoginBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentSuccesfullLogin#newInstance} factory method to
 * create an instance of this fragment.
 */
public class  FragmentSuccesfullLogin extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ModelAccount modelAccount;
    public FragmentSuccesfullLogin(ModelAccount modelAccount){
        this.modelAccount = modelAccount;
    }
    public FragmentSuccesfullLogin(){

    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentSuccesfullLogin.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentSuccesfullLogin newInstance(String param1, String param2) {
        FragmentSuccesfullLogin fragment = new FragmentSuccesfullLogin();
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

    LottieAnimationView lottieAnimationView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentSuccesfullLoginBinding mLoginSuccesBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_succesfull_login, container,false);
        lottieAnimationView = mLoginSuccesBinding.getRoot().findViewById(R.id.id_lottie_login_succes);
        lottieAnimationView.addAnimatorListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Util.switchFragment(new HomeFragment(modelAccount),getActivity());
            }
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
        return mLoginSuccesBinding.getRoot();
    }
}