package com.example.agfood.Activity;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.agfood.API.APIRequestData;
import com.example.agfood.API.BaseServerApp;
import com.example.agfood.Adapter.AdapterFoodPopular;
import com.example.agfood.Fragment.FragmentDetailMakanan;
import com.example.agfood.Fragment.HomeFragment;
import com.example.agfood.Fragment.UtilPref;
import com.example.agfood.Model.ModelAccount;
import com.example.agfood.Model.ModelBarang;
import com.example.agfood.Model.ModelFavorit;
import com.example.agfood.Model.ModelResponseBarang;
import com.example.agfood.R;
import com.example.agfood.Util.SharedPrefDetail;
import com.example.agfood.Util.Util;
import com.example.agfood.databinding.FragmentSearchMenuMakananBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentSearchMenuMakanan#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSearchMenuMakanan extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentSearchMenuMakanan() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentSearchMenuMakanan.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentSearchMenuMakanan newInstance(String param1, String param2) {
        FragmentSearchMenuMakanan fragment = new FragmentSearchMenuMakanan();
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


    ModelAccount mdlAccount;
    AdapterFoodPopular adapterFoodPopular;
    FragmentSearchMenuMakananBinding fragmentSearchMenuMakananBinding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentSearchMenuMakananBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_search_menu_makanan,container,false);
        Util.hiddenNavBottom(getActivity());
        UtilPref utilPref = new UtilPref();
        mdlAccount= Util.getCurrentAccount(utilPref.accountPrefences,getActivity().getApplicationContext() );
        fragmentSearchMenuMakananBinding.idEditTextSearchMenu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().equals("") || editable.toString().isEmpty()){
                    fragmentSearchMenuMakananBinding.idRecPopularFood.setVisibility(View.INVISIBLE);
                } else{
                    fragmentSearchMenuMakananBinding.idRecPopularFood.setVisibility(View.VISIBLE);

                }
                Util.getApiRequetData().cariMakanan(editable.toString()).enqueue(new Callback<ModelResponseBarang>() {
                    @Override
                    public void onResponse(Call<ModelResponseBarang> call, Response<ModelResponseBarang> response) {
                        AdapterFoodPopular.AdapterFoodInterface mAdapterFoodInterface = new AdapterFoodPopular.AdapterFoodInterface() {
                            @Override
                            public void clickItemSelectedListener(int positionOfItemFoodSelected) {
                                ModelBarang foodSelected = response.body().getDataBarang().get(positionOfItemFoodSelected);
                                getActivity().getSupportFragmentManager().beginTransaction()
                                        .setCustomAnimations(R.anim.slide_in,R.anim.fade_in,R.anim.fade_in,R.anim.slide_out).replace(R.id.id_base_frame_layout,new FragmentDetailMakanan(mdlAccount,foodSelected,"HOME")).commit();
                            }

                            @Override
                            public void clickLoveListener(int positionOfItemLikeByUser) {

                            }
                        };
                        adapterFoodPopular = new AdapterFoodPopular(response.body().getDataBarang(),mAdapterFoodInterface);
                        // mFragmentHomeBinding.idRecPopularFood.setLayoutManager(new CenterZoomLayoutManager(getActivity().getApplicationContext(), RecyclerView.HORIZONTAL,false));
                        fragmentSearchMenuMakananBinding.idRecPopularFood.setAdapter(adapterFoodPopular);
                        adapterFoodPopular.notifyDataSetChanged();

                    }

                    @Override
                    public void onFailure(Call<ModelResponseBarang> call, Throwable t) {

                    }
                });
            }
        });
        fragmentSearchMenuMakananBinding.idSearchLayout.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fragmentSearchMenuMakananBinding.idEditTextSearchMenu.getText().toString().equals("") || fragmentSearchMenuMakananBinding.idEditTextSearchMenu.getText().toString().toString().isEmpty()){
                    fragmentSearchMenuMakananBinding.idRecPopularFood.setVisibility(View.INVISIBLE);
                } else{
                    fragmentSearchMenuMakananBinding.idRecPopularFood.setVisibility(View.VISIBLE);

                }
                Util.getApiRequetData().cariMakanan(fragmentSearchMenuMakananBinding.idEditTextSearchMenu.getText().toString().toString()).enqueue(new Callback<ModelResponseBarang>() {
                    @Override
                    public void onResponse(Call<ModelResponseBarang> call, Response<ModelResponseBarang> response) {
                        AdapterFoodPopular.AdapterFoodInterface mAdapterFoodInterface = new AdapterFoodPopular.AdapterFoodInterface() {
                            @Override
                            public void clickItemSelectedListener(int positionOfItemFoodSelected) {
                                ModelBarang foodSelected = response.body().getDataBarang().get(positionOfItemFoodSelected);
                                getActivity().getSupportFragmentManager().beginTransaction()
                                        .setCustomAnimations(R.anim.slide_in,R.anim.fade_in,R.anim.fade_in,R.anim.slide_out).replace(R.id.id_base_frame_layout,new FragmentDetailMakanan(mdlAccount,foodSelected,"HOME")).commit();
                            }

                            @Override
                            public void clickLoveListener(int positionOfItemLikeByUser) {

                            }
                        };
                        adapterFoodPopular = new AdapterFoodPopular(response.body().getDataBarang(),mAdapterFoodInterface);
                        // mFragmentHomeBinding.idRecPopularFood.setLayoutManager(new CenterZoomLayoutManager(getActivity().getApplicationContext(), RecyclerView.HORIZONTAL,false));
                        fragmentSearchMenuMakananBinding.idRecPopularFood.setAdapter(adapterFoodPopular);
                        adapterFoodPopular.notifyDataSetChanged();

                    }

                    @Override
                    public void onFailure(Call<ModelResponseBarang> call, Throwable t) {

                    }
                });
            }
        });
        fragmentSearchMenuMakananBinding.idBtnDetailMakananBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.switchFragment(getActivity().getSupportFragmentManager(),new HomeFragment(),"FRAGMENT_HOME");
            }
        });
        return fragmentSearchMenuMakananBinding.getRoot();
    }
}