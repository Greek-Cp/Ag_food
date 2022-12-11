package com.example.agfood.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.agfood.Model.ModelAccount;
import com.example.agfood.R;
import com.example.agfood.Util.Util;
import com.example.agfood.databinding.FragmentRegisterAccountBinding;

public class FragmentRegisterAccount extends Fragment {
    ModelAccount modelAccount;
    public FragmentRegisterAccount(ModelAccount modelAccount) {
        this.modelAccount = modelAccount;
    }

    public FragmentRegisterAccount(){

    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentRegisterAccountBinding fragmentRegisterAccountBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_register_account, container, false);
        fragmentRegisterAccountBinding.idBtnDetailMakananBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in,R.anim.fade_out,R.anim.fade_in,R.anim.slide_out).replace(R.id.id_base_frame_layout,new LoginFragment()).commit();

            }
        });

        if(modelAccount != null){
            fragmentRegisterAccountBinding.idEditTextRegisterEmail.setText(modelAccount.getEmail());
            fragmentRegisterAccountBinding.idEditTextRegisterNama.setText(modelAccount.getUsername());
            fragmentRegisterAccountBinding.idEditTextRegisterPassword.setText(modelAccount.getPassword());
        }
        fragmentRegisterAccountBinding.idBtnSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fragmentRegisterAccountBinding.idEditTextRegisterEmail.getText().length() == 0){
                    fragmentRegisterAccountBinding.idEditTextRegisterEmail.setError("Email Tidak Boleh Kosong !");
                }
                if(fragmentRegisterAccountBinding.idEditTextRegisterNama.getText().length() == 0){
                    fragmentRegisterAccountBinding.idEditTextRegisterNama.setError("Nama Tidak Boleh Kosong !");
                }
                if(fragmentRegisterAccountBinding.idEditTextRegisterPassword.getText().length() == 0){
                    fragmentRegisterAccountBinding.idEditTextRegisterPassword.setError("Password Tidak Boleh Kosong !");
                }
                if(fragmentRegisterAccountBinding.idEditTextRegisterEmail.getText().toString().length() > 5 && fragmentRegisterAccountBinding.idEditTextRegisterPassword.getText().toString().length()> 5 && fragmentRegisterAccountBinding.idEditTextRegisterNama.getText().length() > 5){
                    System.out.println("Alamat = " + fragmentRegisterAccountBinding.idEditTextRegisterEmail.getText().toString());
                    ModelAccount modelAccount = new ModelAccount(fragmentRegisterAccountBinding.idEditTextRegisterNama.getText().toString(),
                            fragmentRegisterAccountBinding.idEditTextRegisterEmail.getText().toString(),
                            fragmentRegisterAccountBinding.idEditTextRegisterPassword.getText().toString(),"user");
                       Util.switchFragment(new FragmentDetailAkunRegister(modelAccount),getActivity());
                }

                }
        });
        return fragmentRegisterAccountBinding.getRoot();
    }
}
