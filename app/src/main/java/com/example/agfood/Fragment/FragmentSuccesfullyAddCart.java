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
import com.example.agfood.R;
import com.example.agfood.databinding.FragmentSuccesfullyAddCartBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentSuccesfullyAddCart#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSuccesfullyAddCart extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentSuccesfullyAddCart() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentSuccesfullyAddCart.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentSuccesfullyAddCart newInstance(String param1, String param2) {
        FragmentSuccesfullyAddCart fragment = new FragmentSuccesfullyAddCart();
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
                             Bundle savedInstanceSsdastate) {
        FragmentSuccesfullyAddCartBinding fragmentSuccesfullyAddCartBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_succesfully_add_cart, container,false);
        lottieAnimationView = fragmentSuccesfullyAddCartBinding.getRoot().findViewById(R.id.id_lottie_check_out_succes);
        lottieAnimationView.addAnimatorListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in,R.anim.fade_out,R.anim.fade_in,R.anim.slide_out).replace(R.id.id_base_frame_layout, new HomeFragment()).commit();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
        return fragmentSuccesfullyAddCartBinding.getRoot();
    }
}