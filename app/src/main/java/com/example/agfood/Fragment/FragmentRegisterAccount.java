package com.example.agfood.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.agfood.R;
import com.example.agfood.databinding.FragmentRegisterAccountBinding;

public class FragmentRegisterAccount extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentRegisterAccountBinding fragmentRegisterAccountBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_register_account, container, false);
        fragmentRegisterAccountBinding.idBtnDetailMakananBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in,R.anim.fade_out,R.anim.fade_in,R.anim.slide_out).replace(R.id.id_base_frame_layout,new LoginFragment()).commit();
            }
        });
        return fragmentRegisterAccountBinding.getRoot();
    }
}
