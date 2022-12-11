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

    import com.example.agfood.API.APIRequestData;
    import com.example.agfood.Adapter.AdapterCart;
    import com.example.agfood.DataModel.ModelResponseAccount;
    import com.example.agfood.Model.ModelAccount;
    import com.example.agfood.Model.ModelBarang;
    import com.example.agfood.Model.ModelFood;
    import com.example.agfood.Model.ModelKeranjang;
    import com.example.agfood.Model.ModelResponseBarang;
    import com.example.agfood.Model.ModelResponseGetCurrentIdBarang;
    import com.example.agfood.R;
    import com.example.agfood.Util.SharedPrefDetail;
    import com.example.agfood.Util.Util;
    import com.example.agfood.databinding.FragmentKeranjangBinding;
    import com.google.android.material.tabs.TabLayout;
    import com.google.gson.Gson;

    import java.util.ArrayList;
    import java.util.List;
    import java.util.Objects;

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
        int totalHargaYangAkanDicheckout = 0;
        int hargaTotalTambah;
        int hargaTotalKurang;
        int totalDipilih = 0;
        int state = 0;
        String idKeranjang = "";
        List<ModelKeranjang> listKeranjangDipilihUser = new ArrayList<>();
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            fragmentKeranjangBinding = DataBindingUtil.inflate(getLayoutInflater(),R.layout.fragment_keranjang, container, false);
            Util.hiddenNavBottom(getActivity());
            SharedPrefDetail sharedPrefDetailAccount = utilPref.accountPrefences;

            mdl = Util.getCurrentAccount(sharedPrefDetailAccount, getActivity());
            fragmentKeranjangBinding.idTextViewCheckoutTotalPembayaran.setText(String.valueOf(Util.convertToRupiah(0)));
            Util.getApiRequetData().getKeranjangUser(mdl.getIdAkun()).enqueue(new Callback<ModelResponseBarang>() {
                @Override
                public void onResponse(Call<ModelResponseBarang> call, Response<ModelResponseBarang> response) {
                    List<ModelBarang> mdlBarang = response.body().getDataBarang();
                    System.out.println(new Gson().toJson(mdlBarang) + "DATA BARANG");
                    if(mdlBarang != null){
                        for(ModelBarang objBrg : mdlBarang){
                            listDataKeranjang.add(new ModelKeranjang(objBrg));
                        }
                        AdapterCart.AdapterCartInterface adapterCartInterface = new AdapterCart.AdapterCartInterface() {
                            @Override
                            public void tambahPesanan(int positionPesanan) {
                                int currentJumlahOrder = listDataKeranjang.get(positionPesanan).getSelectedFood().getTotalItemKeranjang() + 1;
                                int hargaOriginalOrder = listDataKeranjang.get(positionPesanan).getSelectedFood().getHargaOriginal();
                                int hargaTotalTambahs = currentJumlahOrder * hargaOriginalOrder;
                                listDataKeranjang.get(positionPesanan).getSelectedFood().setTotalItemKeranjang(currentJumlahOrder);
                                //st++;
                                System.out.println("harga total = " + hargaTotalTambahs);
                                listDataKeranjang.get(positionPesanan).getSelectedFood().setHarga(hargaTotalTambahs);
                                //  updateHargaPesanan(hargaTotalTambahs);
                                adapterCart.notifyDataSetChanged();

                            }
                            @Override
                            public void kurangPesanan(int positionPesanan) {
                                int currentJumlahOrder = listDataKeranjang.get(positionPesanan).getSelectedFood().getTotalItemKeranjang() - 1;
                                if(currentJumlahOrder > 0){
                                    int hargaOriginalOrder = listDataKeranjang.get(positionPesanan).getSelectedFood().getHargaOriginal();
                                    int hargaTotalKurangs = currentJumlahOrder * hargaOriginalOrder;
                                    listDataKeranjang.get(positionPesanan).getSelectedFood().setTotalItemKeranjang(currentJumlahOrder);
                                    //st++;
                                    System.out.println("harga total = " + hargaTotalKurangs);
                                    listDataKeranjang.get(positionPesanan).getSelectedFood().setHarga(hargaTotalKurangs);
                                    adapterCart.notifyDataSetChanged();
                                }
                            }

                            @Override
                            public void checkBoxItemSelected(int position, boolean statusCheckbox) {
                                listDataKeranjang.get(position).setStatusCheckBoxChecked(statusCheckbox);
                                if(listDataKeranjang.get(position).isStatusCheckBoxChecked()){
                                    System.out.print("Ter Klik");
                                    totalDipilih += listDataKeranjang.get(position).getSelectedFood().getHarga();
                                    updateHargaPesanan(totalDipilih);
                                    listKeranjangDipilihUser.add(listDataKeranjang.get(position));
                                    updateTotalPesanan();
                                } else{
                                    totalDipilih -= listDataKeranjang.get(position).getSelectedFood().getHarga();
                                    System.out.print("Tidak Di Pilih");
                                    updateHargaPesanan(totalDipilih);
                                    listKeranjangDipilihUser.remove(listDataKeranjang.get(position));
                                    updateTotalPesanan();
                                }
                                adapterCart.notifyDataSetChanged();
                            }
                        };
                        /*
                          fragmentKeranjangBinding.idBtnDetailMakananBackButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Util.switchFragment(new HomeFragment(), getActivity());
                            }
                        });
                         */
                        fragmentKeranjangBinding.idBtnBuatPesanan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                System.out.println(new Gson().toJson(listDataKeranjang) + "TOTAL YG DIPILIH");
                                if(listKeranjangDipilihUser.size() == 0){
                                    Toast.makeText(getActivity().getApplicationContext(),"Mohon Pilih Barang Yang Akan Di Checkout !",Toast.LENGTH_LONG).show();
                                } else{
                                    Util.getApiRequetData().getListIdKeranjang().enqueue(new Callback<ModelResponseGetCurrentIdBarang>() {
                                        @Override
                                        public void onResponse(Call<ModelResponseGetCurrentIdBarang> call, Response<ModelResponseGetCurrentIdBarang> response) {
                                            System.out.println("Response Id Keranjang = " + response.body().getIdKeranjang() + " data");
                                            if(response.body().getKode() == 0){
                                                idKeranjang = "KRJ" + response.body().getIdKeranjang();
                                            } else if(response.body().getKode() == 1){
                                                idKeranjang = "KRJ" + response.body().getIdKeranjang();
                                            }
                                            for(ModelKeranjang mSelectedFoodModel : listKeranjangDipilihUser){
                                                Util.getApiRequetData().pindahPesananKeOrderPending(
                                                                mdl.idAkun,
                                                                mSelectedFoodModel.getSelectedFood().getId_barang(),
                                                                String.valueOf(mSelectedFoodModel.getSelectedFood().getHarga()),
                                                                String.valueOf(mSelectedFoodModel.getSelectedFood().getTotalItemKeranjang()),idKeranjang
                                                        ).enqueue(
                                                                new Callback<ModelResponseBarang>() {
                                                                    @Override
                                                                    public void onResponse(Call<ModelResponseBarang> call, Response<ModelResponseBarang> response) {
                                                                        int kode = response.body().getKode();
                                                                        System.out.println(response.body().getPesan() + " PESAN ");
                                                                        if(kode == 1){
                                                                        } else{
                                                                            Toast.makeText(getActivity().getApplicationContext(),"Masukan Keranjang Gagal",Toast.LENGTH_LONG).show();
                                                                        }
                                                                    }
                                                                    @Override
                                                                    public void onFailure(Call<ModelResponseBarang> call, Throwable t) {
                                                                        System.out.println(t.getCause() + ":" + t.getMessage() + " ERROR");
                                                                    }
                                                                });
                                            }
                                        }
                                        @Override
                                        public void onFailure(Call<ModelResponseGetCurrentIdBarang> call, Throwable t) {
                                        }
                                    });

                                    Util.switchFragment(getActivity().getSupportFragmentManager(),new FragmentCheckoutBarang(listKeranjangDipilihUser),"FRAGMENT_CHECKOUT");
                                }
                            }
                        });
                        fragmentKeranjangBinding.idCheckBoxSemuaKeranjang.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(state == 0){
                                    for(int x = 0; x < listDataKeranjang.size(); x++){
                                        totalDipilih += listDataKeranjang.get(x).getSelectedFood().getHarga();
                                        updateHargaPesanan(totalDipilih);
                                        listKeranjangDipilihUser.add(listDataKeranjang.get(x));
                                        if(listDataKeranjang.get(x).isStatusCheckBoxChecked() == true){
                                            totalDipilih -= listDataKeranjang.get(x).getSelectedFood().getHarga();
//                                        listDataKeranjang.get(x).setStatusCheckBoxChecked(false);
                                        }
                                        if(listDataKeranjang.get(x).isStatusCheckBoxChecked() == false){
                                            listDataKeranjang.get(x).setStatusCheckBoxChecked(true);
                                            System.out.println(new Gson().toJson(listDataKeranjang.get(x)) + " sTTS");
                                            adapterCart.notifyDataSetChanged();
                                        } else if(listDataKeranjang.get(x).isStatusCheckBoxChecked() == true){
                                            listDataKeranjang.get(x).setStatusCheckBoxChecked(false);
                                            System.out.println(new Gson().toJson(listDataKeranjang.get(x)) + " sTTS");
                                            adapterCart.notifyDataSetChanged();
                                        }
                                    }
                                    Toast.makeText(getActivity().getApplicationContext(),"Total Keranjang Before = " + listDataKeranjang.size() , Toast.LENGTH_LONG).show();
                                    updateTotalPesanan(listDataKeranjang.size());
                                    state++;
                                } else if(state == 1){
                                    totalDipilih = 0;
                                    updateHargaPesanan(totalDipilih);
                                    listKeranjangDipilihUser = new ArrayList<>();
                                    for(int y = 0; y < listDataKeranjang.size(); y++){
                                        if(listDataKeranjang.get(y).isStatusCheckBoxChecked() == false){
                                            listDataKeranjang.get(y).setStatusCheckBoxChecked(true);
                                            System.out.println(new Gson().toJson(listDataKeranjang.get(y)) + " sTTS");
                                            adapterCart.notifyDataSetChanged();
                                        } else if(listDataKeranjang.get(y).isStatusCheckBoxChecked() == true){
                                            listDataKeranjang.get(y).setStatusCheckBoxChecked(false);
                                            System.out.println(new Gson().toJson(listDataKeranjang.get(y)) + " sTTS");
                                            adapterCart.notifyDataSetChanged();
                                        }
                                    }

                                    Toast.makeText(getActivity().getApplicationContext(),"Total Keranjang After = " + listDataKeranjang.size() , Toast.LENGTH_LONG).show();
                                    state = 0;
                                    updateTotalPesanan(0);
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

                }

                @Override
                public void onFailure(Call<ModelResponseBarang> call, Throwable t) {
                    Toast.makeText(getActivity().getApplicationContext(),"ERROR " + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
            return  fragmentKeranjangBinding.getRoot();
        }

        void updateTotalPesanan(){
            fragmentKeranjangBinding.idTvCheckoutTotal.setText("Checkout(" + String.valueOf(listKeranjangDipilihUser.size()) + ")");
        }
        void updateTotalPesanan(int totItem){
            fragmentKeranjangBinding.idTvCheckoutTotal.setText("Checkout(" + String.valueOf(totItem) + ")");
        }
        void updateHargaPesanan(int hargaMakanan){

            fragmentKeranjangBinding.idTextViewCheckoutTotalPembayaran.setText(String.valueOf(Util.convertToRupiah(hargaMakanan)));
        }
    }
