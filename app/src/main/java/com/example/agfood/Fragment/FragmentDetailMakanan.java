package com.example.agfood.Fragment;

import android.os.Bundle;

import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.agfood.Adapter.AdapterCheckBoxTopping;
import com.example.agfood.Model.ModelFood;
import com.example.agfood.Model.ModelTopping;
import com.example.agfood.R;
import com.example.agfood.Util.Util;
import com.example.agfood.databinding.FragmentDetailMakananBinding;
import com.mikhaellopez.rxanimation.RxAnimation;

import java.util.ArrayList;
import java.util.List;

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

    private ModelFood mSelectedFoodModel;

    public FragmentDetailMakanan(ModelFood modelFood){
        this.mSelectedFoodModel = modelFood;
    }
    public FragmentDetailMakanan() {
        // Required empty public constructor
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
        if(listTopping == null){
            listTopping = new ArrayList<>();
        }
        listTopping.add(new ModelTopping("Sosis", 2000, 1, false));
        listTopping.add(new ModelTopping("Telur", 2500, 1, false));
        listTopping.add(new ModelTopping("Keju", 2500, 1, false));
        listTopping.add(new ModelTopping("Sayuran", 3000, 1, false));
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         fragmentDetailMakananBinding = DataBindingUtil.inflate(getLayoutInflater(),R.layout.fragment_detail_makanan
        ,container, false);
        setDataDetailMakanan();
        initalizeDataToppingForTest();
        setCheckBoxForFood();
        crudOperation();
        return fragmentDetailMakananBinding.getRoot();
    }
    void crudOperation(){
        fragmentDetailMakananBinding.idCardTambahPesanan.setOnClickListener(this);
        fragmentDetailMakananBinding.idCardKurangPesanan.setOnClickListener(this);
        fragmentDetailMakananBinding.btnDetailMakananTambahPesanan.setOnClickListener(this);
    }

    void setCheckBoxForFood(){
        mAdapterCheckBoxToppingInterface =  new AdapterCheckBoxTopping.AdapterToppingInteface() {
            @Override
            public void clickCheckBox(int position, boolean statusChecked) {
                listTopping.get(position).setCheckboxCliked(statusChecked);
                if(listTopping.get(position).isCheckboxCliked()){
                    System.out.println("total harga topping " + listTopping.get(position).getTotalHargaTopping());
                    totalFinalHargaTopping += listTopping.get(position).getTotalHargaTopping();
                    int totalHargaMakanan = totalPesanan * mSelectedFoodModel.getHargaFood();
                    updateHargaPesanan(totalHargaMakanan, totalFinalHargaTopping);
                    System.out.println("Total Harga Topping Final = " + totalFinalHargaTopping);
                } else{
                    totalFinalHargaTopping -= listTopping.get(position).getTotalHargaTopping();
                    int totalHargaMakanan = totalPesanan * mSelectedFoodModel.getHargaFood();
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
    void setDataDetailMakanan(){
        if(mSelectedFoodModel != null){
            fragmentDetailMakananBinding.idTvDetailMakananNamaMakanan.setText(mSelectedFoodModel.getNameFood());
            fragmentDetailMakananBinding.idTvDetailMakananHargaPesanan.setText(String.valueOf(Util.convertToRupiah(mSelectedFoodModel.getHargaFood())));
            fragmentDetailMakananBinding.idImageDetailFood.setImageResource(mSelectedFoodModel.getImageFood());
            fragmentDetailMakananBinding.idBtnDetailMakananBackButton.setOnClickListener(this);
            fragmentDetailMakananBinding.idKeranjang.setOnClickListener(this);
            fragmentDetailMakananBinding.idTvDetailMakananTopName.setText(mSelectedFoodModel.getNameFood());
            fragmentDetailMakananBinding.idTvDetailMakananTopName.setSelected(true);
            fragmentDetailMakananBinding.idTvDetailMakananHargaMakanan.setText(String.valueOf(Util.convertToRupiah(mSelectedFoodModel.getHargaFood())));
            fragmentDetailMakananBinding.idTvDetailMakananHargaPesanan.setText(String.valueOf(Util.convertToRupiah(mSelectedFoodModel.getHargaFood())));
        }
    }

    void updateHargaPesanan(int hargaMakanan , int hargaTopping){
        int totalHargaPesanan = hargaMakanan + hargaTopping;
        fragmentDetailMakananBinding.idTvDetailMakananHargaPesanan.setText(String.valueOf(Util.convertToRupiah(totalHargaPesanan)));
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
                fragmentDetailMakananBinding.idTvDetailMakananJumlahPesanan.setText(String.valueOf(totalPesanan));;
                int totalHargaMakanan = totalPesanan * mSelectedFoodModel.getHargaFood();
                updateHargaPesanan(totalHargaMakanan, totalFinalHargaTopping);
                break;
            case R.id.id_card_kurang_pesanan:
                if(totalPesanan > 1){
                    totalPesanan -= 1;
                    int totalHargaMakanans = totalPesanan * mSelectedFoodModel.getHargaFood();
                    updateHargaPesanan(totalHargaMakanans, totalFinalHargaTopping);
                    fragmentDetailMakananBinding.idTvDetailMakananJumlahPesanan.setText(String.valueOf(totalPesanan));
                }
                break;
            case R.id.btn_detail_makanan_tambah_pesanan:


/*
                MotionToast.Companion.createColorToast(getActivity(), "Pesanan Dimasukan Kedalam Keranjang",
                        "Notifikasi Pesanan", MotionToastStyle.SUCCESS,MotionToast.GRAVITY_CENTER,MotionToast.LONG_DURATION, ResourcesCompat.getFont(getActivity().getApplicationContext(),R.font.sfprodisplayregular
                        ));
 */
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in,R.anim.fade_out,R.anim.fade_in,R.anim.slide_out).replace(R.id.id_base_frame_layout,new FragmentSuccesfullyAddCart()).commit();
                break;
            case R.id.id_btn_detail_makanan_back_button:
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out,R.anim.fade_in,R.anim.slide_out).replace(R.id.id_base_frame_layout,new HomeFragment()).commit();
                break;
            case R.id.idKeranjang:
                Util.switchFragment(new FragmentKeranjang(), getActivity());
                break;
        }

    }
}