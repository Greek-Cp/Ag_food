package com.example.agfood.Fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.agfood.Model.ModelResponseInformasi;
import com.example.agfood.R;
import com.example.agfood.Util.Util;
import com.example.agfood.databinding.FragmentOrderanSayaBinding;
import com.example.agfood.databinding.FragmentViewInformasiBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentViewInformasi#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentViewInformasi extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentViewInformasi() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentViewInformasi.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentViewInformasi newInstance(String param1, String param2) {
        FragmentViewInformasi fragment = new FragmentViewInformasi();
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
        // Inflate the layout for this fragmen
       FragmentViewInformasiBinding fragmentViewInformasiBinding = DataBindingUtil.inflate(inflater,
               R.layout.fragment_view_informasi,container,false);
        Util.getApiRequetData().getInformasi("test").enqueue(new Callback<ModelResponseInformasi>() {
            @Override
            public void onResponse(Call<ModelResponseInformasi> call, Response<ModelResponseInformasi> response) {
                if(response.body().getKode() == 1){
                    System.out.println("inform " + response.body().getInformasi());
                    fragmentViewInformasiBinding.idTvTextInformasi.setText(response.body().getInformasi());
                }
            }

            @Override
            public void onFailure(Call<ModelResponseInformasi> call, Throwable t) {

            }
        });
        return fragmentViewInformasiBinding.getRoot();

    }
}