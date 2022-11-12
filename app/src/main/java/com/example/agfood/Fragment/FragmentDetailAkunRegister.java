package com.example.agfood.Fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.agfood.API.APIRequestData;
import com.example.agfood.API.BaseServerApp;
import com.example.agfood.Model.ModelAccount;
import com.example.agfood.Model.ModelDetailAccount;
import com.example.agfood.Model.ModelResponseAccount;
import com.example.agfood.R;
import com.example.agfood.Util.Util;
import com.example.agfood.databinding.FragmentDetailAkunBinding;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentDetailAkunRegister#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentDetailAkunRegister extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ModelAccount modelAccount;
    public FragmentDetailAkunRegister(ModelAccount modelAccount) {
        // Required empty public constructor
        this.modelAccount = modelAccount;
    }
    public FragmentDetailAkunRegister(){

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentDetailAkun.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentDetailAkunRegister newInstance(String param1, String param2) {
        FragmentDetailAkunRegister fragment = new FragmentDetailAkunRegister();
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

    FragmentDetailAkunBinding fragmentDetailAkunBinding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentDetailAkunBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_akun, container, false);
              Gson gson1 = new Gson();
        fragmentDetailAkunBinding.idBtnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModelDetailAccount modelDetailAccount =
                        new ModelDetailAccount(
                                fragmentDetailAkunBinding.idEditTextDetailRegisterNamaLengkap.getText().toString(),
                                fragmentDetailAkunBinding.idEditTextDetailRegisterNomorHandphone.getText().toString(),
                                fragmentDetailAkunBinding.idEditTextDetailRegisterAlamat.getText().toString());

                APIRequestData apiRequestData = BaseServerApp.konekRetrofit().create(APIRequestData.class);
                modelAccount.setNamaLengkap(modelDetailAccount.getNamaLengkap());
                modelAccount.setNoHP(modelDetailAccount.getNoHp());
                modelAccount.setAlamat(modelDetailAccount.getAlamat());
                Call<ModelResponseAccount> simpanData = apiRequestData
                        .arcReateData(modelAccount.getUsername(),
                                modelAccount.getEmail(),
                                modelAccount.getPassword(),"user",
                                modelDetailAccount.getNamaLengkap(),modelDetailAccount.getNoHp(),modelAccount.getAlamat());
                Gson gson = new Gson();
                System.out.println(gson.toJson(modelAccount) + "========");
                simpanData.enqueue(new Callback<ModelResponseAccount>() {
                    @Override
                    public void onResponse(Call<ModelResponseAccount> call, Response<ModelResponseAccount> response) {
                        System.out.println("Kode " + response.body().getKode());
                        if(response.body().getKode() == 1){
                            Util.switchFragment(new FragmentSuccesRegisterAlert(), getActivity());
                        }
                    }

                    @Override
                    public void onFailure(Call<ModelResponseAccount> call, Throwable t) {
                            System.out.println(t.getMessage() + " t" + t.getLocalizedMessage());
                        System.out.println("Failed !!" + t.getMessage() );

                    }
                });

            }
        });
        return fragmentDetailAkunBinding.getRoot();
    }
}