package com.example.agfood.Fragment;

import android.animation.Animator;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.agfood.R;
import com.example.agfood.Util.Util;
import com.example.agfood.databinding.FragmentResetPasswordSuccesfullyBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentResetPasswordSuccesfully#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentResetPasswordSuccesfully extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentResetPasswordSuccesfully() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a n    ew instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentResetPasswordSuccesfully.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentResetPasswordSuccesfully newInstance(String param1, String param2) {
        FragmentResetPasswordSuccesfully fragment = new FragmentResetPasswordSuccesfully();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Util.hiddenNavBottom(getActivity());
        FragmentResetPasswordSuccesfullyBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_reset_password_succesfully, container, false);
        binding.idLottieVerifSuccess.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                Util.switchFragment(getActivity().getSupportFragmentManager(), new LoginFragment(),"FRAGMENT_RESET_PASSWORD");
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        return binding.getRoot();

    }
}