package com.example.agfood.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agfood.Adapter.AdapterCart;
import com.example.agfood.DataModel.ModelResponseAccount;
import com.example.agfood.Model.ModelAccount;
import com.example.agfood.Model.ModelBarang;
import com.example.agfood.Model.ModelFood;
import com.example.agfood.Model.ModelKeranjang;
import com.example.agfood.Model.ModelResponseBarang;
import com.example.agfood.R;
import com.example.agfood.Util.SharedPrefDetail;
import com.example.agfood.Util.Util;
import com.example.agfood.databinding.FragmentKeranjangBinding;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentKeranjang extends Fragment {
    FragmentKeranjangBinding fragmentKeranjangBinding;
    List<ModelKeranjang> listDataKeranjang = new ArrayList<>();
    AdapterCart adapterCart;
    void testDataKeranjang(){

}
    ModelAccount mdl;
    public FragmentKeranjang(ModelAccount mdlAccount){
        this.mdl = mdlAccount;
    }
    public FragmentKeranjang(){

    }
    UtilPref utilPref = new UtilPref();
    int st = 1 , sr = 1;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentKeranjangBinding = DataBindingUtil.inflate(getLayoutInflater(),R.layout.fragment_keranjang, container, false);
        SharedPrefDetail sharedPrefDetailAccount = utilPref.accountPrefences;
        Util.hiddenNavBottom(getActivity());
        mdl = Util.getCurrentAccount(sharedPrefDetailAccount, getActivity());
        testDataKeranjang();

        Util.getApiRequetData().getKeranjangUser(mdl.getIdAkun()).enqueue(new Callback<ModelResponseBarang>() {
            @Override
            public void onResponse(Call<ModelResponseBarang> call, Response<ModelResponseBarang> response) {
                List<ModelBarang> mdlBarang = response.body().getDataBarang();
                System.out.println(new Gson().toJson(mdlBarang) + "DATA BARANG");
                for(ModelBarang objBrg : mdlBarang){
                    listDataKeranjang.add(new ModelKeranjang(objBrg));
                }

                AdapterCart.AdapterCartInterface adapterCartInterface = new AdapterCart.AdapterCartInterface() {
                    @Override
                    public void tambahPesanan(int positionPesanan) {
                        int l = listDataKeranjang.get(positionPesanan).getSelectedFood().getHargaOriginal() * st;

                                System.out.println(l + " HARGA SAAT INI TAMBAH");
                        int curJmlh = listDataKeranjang.get(positionPesanan).getSelectedFood().getTotalItemKeranjang() + 1;
                        int hrgFinal = l + listDataKeranjang.get(positionPesanan).getSelectedFood().getHarga();
                        System.out.println("Test = " + hrgFinal );
                        listDataKeranjang.get(positionPesanan).getSelectedFood().setTotalItemKeranjang(curJmlh);
                        adapterCart.notifyDataSetChanged();
                        st++;
                    }
                    @Override
                    public void kurangPesanan(int positionPesanan) {
                        int l = listDataKeranjang.get(positionPesanan).getSelectedFood().getHargaOriginal() * st;
                        System.out.println(l + " HARGA SAAT INI KURANG");
                       // int hrgFinal = l - listDataKeranjang.get(positionPesanan).getSelectedFood().getHarga();
                        System.out.println("L kurang = " + l);
                       // System.out.println("Test = " + hrgFinal );
                        int curJmlh = listDataKeranjang.get(positionPesanan).getSelectedFood().getTotalItemKeranjang() - 1;
                        System.out.println("Test = " + curJmlh);
                        listDataKeranjang.get(positionPesanan).getSelectedFood().setTotalItemKeranjang(curJmlh);
                        adapterCart.notifyDataSetChanged();
                        st--;

                    }

                    @Override
                    public void checkBoxItemSelected(int position, boolean statusCheckbox) {
                        listDataKeranjang.get(position).setStatusCheckBoxChecked(statusCheckbox);
                        if(listDataKeranjang.get(position).isStatusCheckBoxChecked()){
                            System.out.print("Ter Klik");
                        } else{
                            System.out.print("Tidak Di Pilih");
                        }
                        adapterCart.notifyDataSetChanged();
                    }
                };
                fragmentKeranjangBinding.idBtnDetailMakananBackButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Util.switchFragment(new HomeFragment(), getActivity());
                    }
                });
                fragmentKeranjangBinding.idCheckBoxSemuaKeranjang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                      for(int i = 0; i < listDataKeranjang.size(); i++){
                            if(listDataKeranjang.get(i).isStatusCheckBoxChecked() == false){
                                listDataKeranjang.get(i).setStatusCheckBoxChecked(true);
                                System.out.println(new Gson().toJson(listDataKeranjang.get(i)) + " sTTS");
                                adapterCart.notifyDataSetChanged();
                            } else if(listDataKeranjang.get(i).isStatusCheckBoxChecked() == true){
                                listDataKeranjang.get(i).setStatusCheckBoxChecked(false);
                                System.out.println(new Gson().toJson(listDataKeranjang.get(i)) + " sTTS");
                                adapterCart.notifyDataSetChanged();

                            }

                        }
                        adapterCart = new AdapterCart(listDataKeranjang,adapterCartInterface);
                        fragmentKeranjangBinding.idRecKeranjang.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), RecyclerView.VERTICAL,false));
                        fragmentKeranjangBinding.idRecKeranjang.setAdapter(adapterCart);
                        adapterCart.notifyDataSetChanged();
                    }
                });
                adapterCart = new AdapterCart(listDataKeranjang,adapterCartInterface);
                fragmentKeranjangBinding.idRecKeranjang.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), RecyclerView.VERTICAL,false));
                fragmentKeranjangBinding.idRecKeranjang.setAdapter(adapterCart);
            }

            @Override
            public void onFailure(Call<ModelResponseBarang> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(),"ERROR " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        return  fragmentKeranjangBinding.getRoot();
    }
}
