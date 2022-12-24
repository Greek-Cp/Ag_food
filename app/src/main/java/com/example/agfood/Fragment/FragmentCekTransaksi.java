package com.example.agfood.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.agfood.Adapter.AdapterRinciaTransaksi;
import com.example.agfood.DataModel.ModelResponseAccount;
import com.example.agfood.Model.ModelAccount;
import com.example.agfood.Model.ModelKeranjang;
import com.example.agfood.Model.ModelResponseUpload;
import com.example.agfood.R;
import com.example.agfood.Util.SharedPrefDetail;
import com.example.agfood.Util.Util;
import com.example.agfood.databinding.FragmentCekTransaksiBinding;
import com.example.agfood.databinding.FragmentUserProfileSettingBinding;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.sanju.motiontoast.MotionToast;
import www.sanju.motiontoast.MotionToastStyle;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentCekTransaksi#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCekTransaksi extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    List<ModelKeranjang> modelKeranjangList;
    private String metodePembayaran;
    private String noRek;
    private String requestFragmentType;
    private String idKeranjang;
    private String statusBayar;
    private String alamatUser;
    public FragmentCekTransaksi(List<ModelKeranjang> listKeranjang ,
                                String metodePembayaran ,
                                String noRek,
                                String alamatUser,
                                String requestFragmentType,String idKeranjang,String statusBayar) {
        this.modelKeranjangList = listKeranjang;
        this.metodePembayaran = metodePembayaran;
        this.noRek = noRek;
        this.requestFragmentType = requestFragmentType;
        this.idKeranjang = idKeranjang;
        this.statusBayar = statusBayar;
        this.alamatUser = alamatUser;
        System.out.println(statusBayar + " Status Bayar : ");
        System.out.println(metodePembayaran + " metode pembayaran : ");
        System.out.println(noRek + " metode pembayaran : ");
    }
    public FragmentCekTransaksi(List<ModelKeranjang> listKeranjang ,
                                String metodePembayaran ,
                                String noRek,
                                String requestFragmentType) {
        this.modelKeranjangList = listKeranjang;
        this.metodePembayaran = metodePembayaran;
        this.noRek = noRek;
        this.requestFragmentType = requestFragmentType;
        System.out.println(metodePembayaran + " metode pembayaran : ");
        System.out.println(noRek + " metode pembayaran : ");
    }
    public FragmentCekTransaksi() {
        // Required empty public constructor
    }

    /**
         * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentCekTransaksi.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentCekTransaksi newInstance(String param1, String param2) {
        FragmentCekTransaksi fragment = new FragmentCekTransaksi();
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
    FragmentUserProfileSettingBinding fragmentUserProfileSettingBinding;
    String part_image;
    ModelAccount mdl;
    public void uploadImage() {
        File imageFile = new File(part_image);
        RequestBody reqBody = RequestBody.create(MediaType.parse("multipart/form-file"), imageFile);
        MultipartBody.Part partImage = MultipartBody.Part.createFormData("file", imageFile.getName(), reqBody);
        RequestBody  filename = RequestBody.create(MediaType.parse("text/plain"), "profile_" + mdl.getUsername());
        System.out.println("ID KERANJANG == " + idKeranjang);
        Util.getApiRequetData().uploadImageTransaksi(partImage,filename).enqueue(new Callback<ModelResponseUpload>() {
            @Override
            public void onResponse(Call<ModelResponseUpload> call, Response<ModelResponseUpload> response) {
                System.out.println(response.body().getLink() + " MESSAGE");
                Util.getApiRequetData().updateImageTransaksi(idKeranjang,response.body().getLink()).enqueue(new Callback<ModelResponseAccount>() {
                    @Override
                    public void onResponse(Call<ModelResponseAccount> call, Response<ModelResponseAccount> response) {
                        if(response.body().getKode() == 1){
                            MotionToast.Companion.createColorToast(getActivity(), "Upload Transaksi Berhasil",
                                    "Transaksi Anda Akan Kami Validasi Terlebih Dahulu Mungkin Akan Membutuhkan Waktu Beberapa Menit", MotionToastStyle.SUCCESS,MotionToast.GRAVITY_TOP,MotionToast.LONG_DURATION, ResourcesCompat.getFont(getActivity().getApplicationContext(),R.font.sfprodisplayregular
                                    ));
                        }
                    }

                    @Override
                    public void onFailure(Call<ModelResponseAccount> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onFailure(Call<ModelResponseUpload> call, Throwable t) {
                System.out.println(t.getMessage() + "error ");
            }
        });
    }
    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    // There are no request codes
                    // doSomeOperations();
                    Intent data = result.getData();
                    Uri selectedImage = Objects.requireNonNull(data).getData();
                    String[] imageProjection = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getActivity().getContentResolver().query(selectedImage, imageProjection, null, null, null);
                    if(cursor != null) {
                        cursor.moveToFirst();
                        int indexImage = cursor.getColumnIndex(imageProjection[0]);
                        part_image = cursor.getString(indexImage);
                        System.out.println("Part Image = " + part_image);
                    }
                    InputStream imageStream = null;
                    try {
                        imageStream = getActivity().getContentResolver().openInputStream(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    // StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    //         + "." + getFileExtension(selectedImage));
                    BitmapFactory.decodeStream(imageStream);
                    System.out.println(selectedImage + " PATH ");
                    //Util.getApiRequetData().uploadImage()

                }
            });
    int totl = 0;
    int st = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentCekTransaksiBinding fragmentCekTransaksiBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_cek_transaksi,container,false);
        UtilPref utilPref = new UtilPref();
        SharedPrefDetail sharedPrefDetailAccount = utilPref.accountPrefences;
        mdl = Util.getCurrentAccount(sharedPrefDetailAccount, getActivity());
        switch (statusBayar){
            case "sudah_bayar":
                fragmentCekTransaksiBinding.idCekTransaksiStatusOrder.setText("Pembayaran Terkonfirmasi ");
                fragmentCekTransaksiBinding.idCekTransaksiIcon.setAnimation(R.raw.success_tick);
                fragmentCekTransaksiBinding.idCekTransaksiIcon.loop(false);
                fragmentCekTransaksiBinding.idCekTransaksiBtnUploadFotoTransaksi.setVisibility(View.INVISIBLE);
                break;

        }
        switch (metodePembayaran){
            case "Dana":
                fragmentCekTransaksiBinding.idCekTransaksiMetodePembayaran.setText("Melalui Transfer E-Wallet Dana Ke Nomor");
                break;
            case "Shoppe Pay":
                fragmentCekTransaksiBinding.idCekTransaksiMetodePembayaran.setText("Melalui Transfer E-Wallet Shoppe Pay Ke Nomor");
                break;
            case "BNI":
                fragmentCekTransaksiBinding.idCekTransaksiMetodePembayaran.setText("Melalui Transfer Bank BNI Ke No Rek");
                break;
        }
        fragmentCekTransaksiBinding.idCekTransaksiNomrRek.setText(noRek);

        AdapterRinciaTransaksi.AdapterRincianTransaksiInterface adapterRincianTransaksiInterface = new AdapterRinciaTransaksi.AdapterRincianTransaksiInterface() {
            @Override
            public void clickRincianTransaksi(int positionOfTransaksi) {

            }
        };
        AdapterRinciaTransaksi adapterRinciaTransaksi = new AdapterRinciaTransaksi(modelKeranjangList,adapterRincianTransaksiInterface);
        fragmentCekTransaksiBinding.idCekTransaksiRecRincianTransaksi.setAdapter(adapterRinciaTransaksi);
        for(ModelKeranjang mdl :modelKeranjangList){
            totl += mdl.getSelectedFood().getHarga();
        }
        fragmentCekTransaksiBinding.idTextViewValueAlamatPenerima.setText(this.alamatUser);
        fragmentCekTransaksiBinding.idCekTransaksiTotalPembayaranTextView.setText(Util.convertToRupiah(totl));
        fragmentCekTransaksiBinding.idCekTransaksiTotalPembayaranFinalTextView.setText(Util.convertToRupiah(totl));
        fragmentCekTransaksiBinding.idBtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.switchFragment(getActivity().getSupportFragmentManager(),new FragmentBaseKeranjang("KRJ"),"FRAGMENT HOME");
            }
        });
        fragmentCekTransaksiBinding.idCekTransaksiTextViewKirimBukti.setText("Upload Gambar Transaksi");
        fragmentCekTransaksiBinding.idCekTransaksiBtnUploadFotoTransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(part_image == null){
                    Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                    photoPickerIntent.setType("image/*");
                    someActivityResultLauncher.launch(photoPickerIntent);
                }
                fragmentCekTransaksiBinding.idCekTransaksiTextViewKirimBukti.setText("Kirim Bukti Transaksi");

                if(part_image != null){
                    MotionToast.Companion.createColorToast(getActivity(), "Upload Transaksi Berhasil",
                            "Transaksi Anda Akan Kami Validasi Terlebih Dahulu Mungkin Akan Membutuhkan Waktu Beberapa Menit", MotionToastStyle.SUCCESS,MotionToast.GRAVITY_TOP,MotionToast.LONG_DURATION, ResourcesCompat.getFont(getActivity().getApplicationContext(),R.font.sfprodisplayregular
                            ));
                    uploadImage();
                }
            }
        });
        return fragmentCekTransaksiBinding.getRoot();


    }
}