package com.example.agfood.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.agfood.API.APIRequestData;
import com.example.agfood.API.BaseServerApp;
import com.example.agfood.Adapter.AdapterCheckBoxTopping;
import com.example.agfood.Adapter.AdapterTableDetailTopping;
import com.example.agfood.Model.ModelAccount;
import com.example.agfood.Model.ModelBarang;
import com.example.agfood.Model.ModelFood;
import com.example.agfood.Model.ModelResponseBarang;
import com.example.agfood.Model.ModelTopping;
import com.example.agfood.R;
import com.example.agfood.Util.Util;
import com.example.agfood.databinding.FragmentDetailMakananBinding;
import com.google.gson.Gson;
import com.mikhaellopez.rxanimation.RxAnimation;
import com.squareup.picasso.Picasso;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.sanju.motiontoast.MotionToast;
import www.sanju.motiontoast.MotionToastStyle;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentDetailMakanan#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentDetailMakanan extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ModelBarang mSelectedFoodModel;
    private ModelAccount modelAccount;

    private String fromView;
    public FragmentDetailMakanan(ModelAccount mdlAccount , ModelBarang modelFood,String fromView){
        this.mSelectedFoodModel = modelFood;
        this.fromView = fromView;
        this.modelAccount = mdlAccount;
         totalHargaPesanan= mSelectedFoodModel.getHargaOriginal();
        System.out.println("From view = " + fromView);
    }
    public FragmentDetailMakanan() {
        // Required empty public constructor
    }

    SharedPreferences sharedPreferences;
    public void saveDataToPrefences(){
        sharedPreferences = getActivity().getSharedPreferences("PREF_DATA_BARANG", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String jsonSerializeDataBarang = gson.toJson(mSelectedFoodModel);


    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentDetailMakanan.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentDetailMakanan newInstance(String param1, String param2) {
        FragmentDetailMakanan fragment = new FragmentDetailMakanan();
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
    List<ModelTopping> listTopping;
    private int totalPesanan = 1;
    private int totalHargaTopping = 0;
    AdapterCheckBoxTopping.AdapterToppingInteface mAdapterCheckBoxToppingInterface;
    AdapterCheckBoxTopping adapterCheckBoxTopping;
    FragmentDetailMakananBinding fragmentDetailMakananBinding;
    private int totalFinalHargaTopping = 0;

   // static int TEST_HARGA_DEFAULT = 10000;
    void initalizeDataToppingForTest(){
       // if(mSelectedFoodModel.getListModelTopping() == null){
            if(listTopping == null){
                listTopping = new ArrayList<>();
            }
            /*
            listTopping.add(new ModelTopping("Sosis", 2000, 1, false,R.drawable.ic_sosis));
            listTopping.add(new ModelTopping("Telur", 2500, 1, false,R.drawable.ic_telur));
            listTopping.add(new ModelTopping("Keju", 2500, 1, false,R.drawable.ic_keju));
            listTopping.add(new ModelTopping("Sayuran", 3000, 1, false,R.drawable.ic_sosis));
        */
        //} else{
         //   listTopping = mSelectedFoodModel.getListModelTopping();
        //}
        APIRequestData apiRequestData = BaseServerApp.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponseBarang> modelResponseBarangCall  = apiRequestData.getResponseDataBarang();
        modelResponseBarangCall.enqueue(new Callback<ModelResponseBarang>() {
            @Override
            public void onResponse(Call<ModelResponseBarang> call, Response<ModelResponseBarang> response) {
                for(ModelBarang modelBarang : response.body().getDataBarang()){
                   if(modelBarang.getJenis_barang().equals("Menu Tambahan")){
                       System.out.println("Nama barang toping = " + modelBarang.getNama_barang());
                       ModelTopping currentlyTopping = new ModelTopping(modelBarang.getNama_barang(), modelBarang.getHargaOriginal(), 1, false,modelBarang.getGambar_barang());
                        listTopping.add(currentlyTopping);
                    }adapterCheckBoxTopping.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ModelResponseBarang> call, Throwable t) {
                System.out.println("error = " + t.getMessage());
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().findViewById(R.id.id_nav_bar).setVisibility(View.INVISIBLE);

        fragmentDetailMakananBinding = DataBindingUtil.inflate(getLayoutInflater(),R.layout.fragment_detail_makanan
        ,container, false);
        updateHargaPesanan(Integer.parseInt(String.valueOf(mSelectedFoodModel.getHargaOriginal())),0);
        setDataDetailMakanan();
        initalizeDataToppingForTest();
        initializeDataTopping();
        setCheckBoxForFood();
        crudOperation();
        return fragmentDetailMakananBinding.getRoot();
    }

    /*

     */
    void crudOperation(){
        fragmentDetailMakananBinding.idCardTambahPesanan.setOnClickListener(this);
        fragmentDetailMakananBinding.idCardKurangPesanan.setOnClickListener(this);
        fragmentDetailMakananBinding.idCardContainerDetailMakanan.setOnClickListener(this);
    }
    void setCheckBoxForFood(){

        mAdapterCheckBoxToppingInterface =  new AdapterCheckBoxTopping.AdapterToppingInteface() {
            @Override
            public void clickCheckBox(int position, boolean statusChecked) {
                listTopping.get(position).setCheckboxCliked(statusChecked);
                if(listTopping.get(position).isCheckboxCliked()){
                    System.out.println(MessageFormat.format("total harga topping {0}", listTopping.get(position).getTotalHargaTopping())) ;
                    totalFinalHargaTopping += listTopping.get(position).getTotalHargaTopping();
                    int totalHargaMakanan = totalPesanan * mSelectedFoodModel.getHargaOriginal();
                    updateHargaPesanan(totalHargaMakanan, totalFinalHargaTopping);
               //     fragmentDetailMakananBinding.idTvDetailMakananHargaTopping.setText(Util.convertToRupiah(totalFinalHargaTopping));
                } else{
                    totalFinalHargaTopping -= listTopping.get(position).getTotalHargaTopping() ;
                    int totalHargaMakanan = totalPesanan * mSelectedFoodModel.getHargaOriginal();
    //               fragmentDetailMakananBinding.idTvDetailMakananHargaTopping.setText(Util.convertToRupiah(totalFinalHargaTopping));
                    updateHargaPesanan(totalHargaMakanan, totalFinalHargaTopping);
                    System.out.println("Total Harga Topping Final = " + totalFinalHargaTopping);
                }
                adapterCheckBoxTopping.notifyDataSetChanged();
            }
            @Override
            public void clickAddTopping(int position, int jumlahToping) {
                totalHargaTopping = 0;
                listTopping.get(position).setSatuanTopping(jumlahToping);
                adapterCheckBoxTopping.notifyDataSetChanged();
                totalHargaTopping += listTopping.get(position).getHargaTopping() * listTopping.get(position).getSatuanTopping();
                listTopping.get(position).setTotalHargaTopping(totalHargaTopping);
                /*
                if(listTopping.get(position).isCheckboxCliked()){
                    totalFinalHargaTopping += listTopping.get(position).getHargaTopping() * listTopping.get(position).getSatuanTopping();
                    System.out.println("Total Harga Topping Final = " + totalFinalHargaTopping);
                }
                 */
                System.out.println("Tambah Total Harga Topping = " + totalHargaTopping);
                System.out.println(listTopping.get(position).getSatuanTopping()
                        + "  x " + listTopping.get(position).getHargaTopping() + " = " + totalHargaTopping);
            }
            @Override
            public void clickMinTopping(int position, int jumlahToping) {
                System.out.println("Toping before set = " + listTopping.get(position).getSatuanTopping());
                listTopping.get(position).setSatuanTopping(jumlahToping);
                System.out.println("Toping after set = " + listTopping.get(position).getSatuanTopping());
                adapterCheckBoxTopping.notifyDataSetChanged();
                totalHargaTopping -= listTopping.get(position).getHargaTopping() * listTopping.get(position).getSatuanTopping();
                System.out.println("Total Harga Topping After Dikurangi = " + totalHargaTopping);
                System.out.println(listTopping.get(position).getSatuanTopping()
                        + "  x " + listTopping.get(position).getHargaTopping() + " = " + totalHargaTopping);
            }
        };
        adapterCheckBoxTopping = new AdapterCheckBoxTopping(listTopping, mAdapterCheckBoxToppingInterface);
        fragmentDetailMakananBinding.idRecCheckbox.setAdapter(adapterCheckBoxTopping);
    }
    List<ModelTopping> listToppingSelected = new ArrayList<>();
    void initializeDataTopping(){
        if(listToppingSelected == null){
            listToppingSelected = new ArrayList<>();
        }
        listToppingSelected.add(new ModelTopping("Nama Topping ",2000,1, false,123));
        listToppingSelected.add(new ModelTopping("Sosis ",2000,1, false,123));
        listToppingSelected.add(new ModelTopping("Tahu ",2000,1, false,123));
        listToppingSelected.add(new ModelTopping("Martabak ",2000,1, false,123));
     }
    void setDataDetailMakanan(){
        if(mSelectedFoodModel != null){
            fragmentDetailMakananBinding.idTvDetailMakananNamaMakanan.setText(mSelectedFoodModel.getNama_barang());
            fragmentDetailMakananBinding.idTvDetailMakananHargaPesanan.setText(String.valueOf(Util.convertToRupiah(mSelectedFoodModel.getHargaOriginal())));
            Picasso.get().load(mSelectedFoodModel.getGambar_barang()).into(fragmentDetailMakananBinding.idImageDetailFood);
              fragmentDetailMakananBinding.idBtnDetailMakananBackButton.setOnClickListener(this);
            fragmentDetailMakananBinding.idKeranjang.setOnClickListener(this);
            fragmentDetailMakananBinding.idTvDetailMakananTopName.setText(mSelectedFoodModel.getNama_barang());
            fragmentDetailMakananBinding.idTvDetailMakananTopName.setSelected(true);
            fragmentDetailMakananBinding.idTvDetailMakananDeskripsi.setText(mSelectedFoodModel.getDeskripsi_barang());
            fragmentDetailMakananBinding.idTvDetailMakananHargaMakanan.setText(String.valueOf(Util.convertToRupiah(mSelectedFoodModel.getHargaOriginal())));
        }
    }
    int totalHargaPesanan;

    void updateHargaPesanan(int hargaMakanan , int hargaTopping){
        System.out.println("Harga Topping = " + hargaTopping);
        totalHargaPesanan= hargaMakanan + hargaTopping;
        fragmentDetailMakananBinding.idTvDetailMakananHargaPesanan.setText(String.valueOf(Util.convertToRupiah(totalHargaPesanan)));
        fragmentDetailMakananBinding.idTvDetailMakananTextViewTotalHarga.setText(String.valueOf(Util.convertToRupiah(totalHargaPesanan)));
    }
    /*
    Model Data Keranjang.
    void tambahkanKeKeranjang(){
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.id_card_tambah_pesanan:
                totalPesanan += 1;
                fragmentDetailMakananBinding.idTvDetailMakananJumlahPesanan.setText(String.valueOf(totalPesanan));
                int totalHargaMakanan;
                if(totalFinalHargaTopping > 0){
                    totalHargaMakanan = totalPesanan * mSelectedFoodModel.getHargaOriginal() + totalFinalHargaTopping;
                } else{
                    totalHargaMakanan = totalPesanan* mSelectedFoodModel.getHargaOriginal();
                }
                System.out.println("total harga topping = " + totalFinalHargaTopping);
                updateHargaPesanan(totalHargaMakanan, totalFinalHargaTopping);
                break;
            case R.id.id_card_kurang_pesanan:
                if(totalPesanan > 1){
                    totalPesanan -= 1;
                    int totalHargaMakanans;
                    if(totalFinalHargaTopping > 0){
                        totalHargaMakanans = totalPesanan   * mSelectedFoodModel.getHargaOriginal();
                    } else{
                        totalHargaMakanans = totalPesanan * mSelectedFoodModel.getHargaOriginal();
                    }
                    System.out.println("total harga topping = " + totalFinalHargaTopping);
                    updateHargaPesanan(totalHargaMakanans, totalFinalHargaTopping);
                    fragmentDetailMakananBinding.idTvDetailMakananJumlahPesanan.setText(String.valueOf(totalPesanan));
                }
                break;
            case R.id.id_card_container_detail_makanan:
/*
                MotionToast.Companion.createColorToast(getActivity(), "Pesanan Dimasukan Kedalam Keranjang",
                        "Notifikasi Pesanan", MotionToastStyle.SUCCESS,MotionToast.GRAVITY_CENTER,MotionToast.LONG_DURATION, ResourcesCompat.getFont(getActivity().getApplicationContext(),R.font.sfprodisplayregular
                        ));
 */             mSelectedFoodModel.setPesanDariUser(fragmentDetailMakananBinding.idCatatanDariUser.getText().toString());
                Util.getApiRequetData().savePesananKeKeranjang(modelAccount.idAkun,this.mSelectedFoodModel.getId_barang(),String.valueOf(totalHargaPesanan),fragmentDetailMakananBinding.idTvDetailMakananJumlahPesanan.getText().toString(),
                        fragmentDetailMakananBinding.idCatatanDariUser.getText().toString()).enqueue(new Callback<ModelResponseBarang>() {
                    @Override
                    public void onResponse(Call<ModelResponseBarang> call, Response<ModelResponseBarang> response) {
                        int kode = response.body().getKode();
                        if(kode == 1){
                            Toast.makeText(getActivity().getApplicationContext(),"Masukan Keranjang Berhasil",Toast.LENGTH_LONG).show();
                            getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in,R.anim.fade_out,R.anim.fade_in,R.anim.slide_out).replace(R.id.id_base_frame_layout,new FragmentSuccesfullyAddCart()).addToBackStack(null).commit();
                        } else{
                            Toast.makeText(getActivity().getApplicationContext(),"Masukan Keranjang Gagal",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ModelResponseBarang> call, Throwable t) {
                        System.out.println(t.getCause() + ":" + t.getMessage() + " ERROR");
                    }
                });
                break;
            case R.id.id_btn_detail_makanan_back_button:
                switch (fromView){
                    case "HOME":
                        getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out,R.anim.fade_in,R.anim.slide_out).replace(R.id.id_base_frame_layout,new HomeFragment()).addToBackStack(null).commit();
                        break;
                    case "Makanan":
                        getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out,R.anim.fade_in,R.anim.slide_out).replace(R.id.id_base_frame_layout,new FragmentViewMenuSelected(fromView)).addToBackStack(null).commit();
                        break;
                    case "Minuman":
                        getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out,R.anim.fade_in,R.anim.slide_out).replace(R.id.id_base_frame_layout,new FragmentViewMenuSelected(fromView)).addToBackStack(null).commit();
                        break;
                }
                break;
            case R.id.idKeranjang:
                Util.switchFragment(new FragmentKeranjang(), getActivity());
                break;
        }

    }
}