package com.example.agfood.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.agfood.API.APIRequestData;
import com.example.agfood.API.BaseServerApp;
import com.example.agfood.Model.ModelAccount;
import com.example.agfood.Model.ModelDetailAccount;
import com.example.agfood.DataModel.ModelResponseAccount;
import com.example.agfood.R;
import com.example.agfood.Util.Util;
import com.example.agfood.databinding.FragmentDetailAkunBinding;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentDetailAkunRegister#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentDetailAkunRegister extends Fragment implements OnMapReadyCallback {

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
    public void sendMessage(String receiveEmail,String otpKode){
        try {
            String stringSenderEmail = "e41210753@student.polije.ac.id";
            String stringPasswordSenderEmail = "Riyan_Islam_2003";
            String stringHost = "smtp.gmail.com";
            Properties properties = System.getProperties();
            properties.put("mail.smtp.host", stringHost);
            properties.put("mail.smtp.port", "465");
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.auth", "true");
            javax.mail.Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(stringSenderEmail, stringPasswordSenderEmail);
                }
            });

            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(receiveEmail));
            mimeMessage.setSubject("Verifikasi Akun");

            mimeMessage.setContent ("\n" +
                    "<!doctype html>\n" +
                    "<html lang=\"en-US\">\n" +
                    "\n" +
                    "<head>\n" +
                    "    <meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\" />\n" +
                    "    <title>New Account Email Template</title>\n" +
                    "    <meta name=\"description\" content=\"New Account Email Template.\">\n" +
                    "    <style type=\"text/css\">\n" +
                    "        a:hover {text-decoration: underline !important;}\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "\n" +
                    "<body marginheight=\"0\" topmargin=\"0\" marginwidth=\"0\" style=\"margin: 0px; background-color: #f2f3f8;\" leftmargin=\"0\">\n" +
                    "    <!-- 100% body table -->\n" +
                    "    <table cellspacing=\"0\" border=\"0\" cellpadding=\"0\" width=\"100%\" bgcolor=\"#f2f3f8\"\n" +
                    "        style=\"@import url(https://fonts.googleapis.com/css?family=Rubik:300,400,500,700|Open+Sans:300,400,600,700); font-family: 'Open Sans', sans-serif;\">\n" +
                    "        <tr>\n" +
                    "            <td>\n" +
                    "                <table style=\"background-color: #f2f3f8; max-width:670px; margin:0 auto;\" width=\"100%\" border=\"0\"\n" +
                    "                    align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                    "                    <tr>\n" +
                    "                        <td style=\"height:80px;\">&nbsp;</td>\n" +
                    "                    </tr>\n" +
                    "                    <tr>\n" +
                    "                        <td style=\"text-align:center;\">\n" +
                    "                    \n" +
                    "                          <a href=\"https://imgbb.com/\" target=\"_blank\" title=\"logo\">\n" +
                    "                            <img src=\"https://i.ibb.co/mbVKGR5/AG-FOOD.png\" title=\"logo\" alt=\"logo\" border=\"0\"></a>\n" +
                    "                        </td>\n" +
                    "                    </tr>\n" +
                    "                    <tr>\n" +
                    "                        <td style=\"height:20px;\">&nbsp;</td>\n" +
                    "                    </tr>\n" +
                    "                    <tr>\n" +
                    "                        <td>\n" +
                    "                            <table width=\"95%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                    "                                style=\"max-width:670px; background:#fff; border-radius:3px; text-align:center;-webkit-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);-moz-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);box-shadow:0 6px 18px 0 rgba(0,0,0,.06);\">\n" +
                    "                                <tr>\n" +
                    "                                    <td style=\"height:40px;\">&nbsp;</td>\n" +
                    "                                </tr>\n" +
                    "                                <tr>\n" +
                    "                                    <td style=\"padding:0 35px;\">\n" +
                    "                                        <h1 style=\"color:#1e1e2d; font-weight:500; margin:0;font-size:32px;font-family:'Rubik',sans-serif;\">Kode Verifikasi\n" +
                    "                                        </h1>\n" +
                    "                                        <p style=\"font-size:15px; color:#455056; margin:8px 0 0; line-height:24px;\">\n" +
                    "                                            Kode Verifikasi Akun Anda\n" +
                    "                                            <h1 style=\"color: black;\">" + otpKode + "</h1>\n" +
                    "                                            Untuk memverifikasi akun Anda, masukan kode di atas.\n" +
                    "                                           \n" +
                    "                                        </p>\n" +
                    "                                    \n" +
                    "                                    </td>\n" +
                    "                                </tr>\n" +
                    "                                <tr>\n" +
                    "                                    <td style=\"height:40px;\">&nbsp;</td>\n" +
                    "                                </tr>\n" +
                    "                            </table>\n" +
                    "                        </td>\n" +
                    "                    </tr>\n" +
                    "                    <tr>\n" +
                    "                        <td style=\"height:20px;\">&nbsp;</td>\n" +
                    "                    </tr>\n" +
                    "                    <tr>\n" +
                    "                        <td style=\"text-align:center;\">\n" +
                    "                            <p style=\"font-size:14px; color:rgba(69, 80, 86, 0.7411764705882353); line-height:18px; margin:0 0 0;\">&copy; <strong>AG FOOD TEAM</strong> </p>\n" +
                    "                        </td>\n" +
                    "                    </tr>\n" +
                    "                    <tr>\n" +
                    "                        <td style=\"height:80px;\">&nbsp;</td>\n" +
                    "                    </tr>\n" +
                    "                </table>\n" +
                    "            </td>\n" +
                    "        </tr>\n" +
                    "    </table>\n" +
                    "    <!--/100% body table-->\n" +
                    "</body>\n" +
                    "\n" +
                    "</html>", "text/html");
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Transport.send(mimeMessage);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    FragmentDetailAkunBinding fragmentDetailAkunBinding;
    private String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
                Log.w("My Current loction address", strReturnedAddress.toString());
            } else {
                Log.w("My Current loction address", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("My Current loction address", "Canont get Address!");
        }
        return strAdd;
    }
    private GoogleMap mMap;
    private boolean oke = false;
    String id_user,alamatGet;
    List<Address> addressList = null;
    @SuppressLint("MissingPermission")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentDetailAkunBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_akun, container, false);
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);

        fragmentDetailAkunBinding.idBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.switchFragment(getActivity().getSupportFragmentManager(), new FragmentRegisterAccount(modelAccount), "");
            }
        });
        getActivity().findViewById(R.id.id_nav_bar).setVisibility(View.INVISIBLE);
        fragmentDetailAkunBinding.idBtnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModelDetailAccount modelDetailAccount =
                        new ModelDetailAccount(
                                fragmentDetailAkunBinding.idEditTextDetailRegisterNamaLengkap.getText().toString(),
                                fragmentDetailAkunBinding.idEditTextDetailRegisterNomorHandphone.getText().toString(),
                                fragmentDetailAkunBinding.idEditTextDetailRegisterAlamat.getText().toString());
                 modelAccount.setNamaLengkap(modelDetailAccount.getNamaLengkap());
                modelAccount.setNoHp(modelDetailAccount.getNoHp());
                modelAccount.setAlamat(modelDetailAccount.getAlamat());
                String username = modelAccount.getUsername().trim();
                String password = modelAccount.getPassword().trim();
                String email = modelAccount.getEmail().trim();
                String kedudukan = "user";
                String alamat = modelDetailAccount.getAlamat().trim();
                String namaUser = modelAccount.getNamaLengkap().trim();
                String noHp = modelAccount.getNoHp().trim();
                APIRequestData apiRequestData = BaseServerApp.konekRetrofit().create(APIRequestData.class);
                Call<ModelResponseAccount> modelResponseAccountCall = apiRequestData.createAccount(username, password,
                        kedudukan, email,alamat,namaUser,noHp);
                if(fragmentDetailAkunBinding.idEditTextDetailRegisterAlamat.getText().length() == 0){
                    fragmentDetailAkunBinding.idEditTextDetailRegisterAlamat.setError("Alamat Tidak Boleh Kosong !");
                }
                if(fragmentDetailAkunBinding.idEditTextDetailRegisterNamaLengkap.getText().length() == 0)
                {
                    fragmentDetailAkunBinding.idEditTextDetailRegisterNamaLengkap.setError("Nama Lengkap Tidak Boleh Kosong !");
                }
                if(fragmentDetailAkunBinding.idEditTextDetailRegisterNomorHandphone.getText().length() == 0){
                    fragmentDetailAkunBinding.idEditTextDetailRegisterNomorHandphone.setError("Nomor Handphone Tidak Boleh Kosong !");
                }

                String noHpFromTxtField = fragmentDetailAkunBinding.idEditTextDetailRegisterNomorHandphone.getText().toString();
                String namaLengkapFromTxtField = fragmentDetailAkunBinding.idEditTextDetailRegisterNamaLengkap.getText().toString();
                String alamatFromTxtField = fragmentDetailAkunBinding.idEditTextDetailRegisterNamaLengkap.getText().toString();
                int lenNoHp = noHpFromTxtField.length();
                int lenNamaLengkap = namaLengkapFromTxtField.length();
                int lenAlamat = alamatFromTxtField.length();
                if((lenNoHp != 12)){
                    fragmentDetailAkunBinding.idEditTextDetailRegisterNomorHandphone.setError("Nomor Handphone Tidak Valid !");
                } else if(lenNamaLengkap < 4){
                    fragmentDetailAkunBinding.idEditTextDetailRegisterAlamat.setError("Nama Lengkap Tidak Boleh Kosong !");
                } else if(lenAlamat < 5){
                    fragmentDetailAkunBinding.idEditTextDetailRegisterAlamat.setError("Alamat Tidak Boleh Kosong !");
                } else if(lenNoHp == 0) {
                    fragmentDetailAkunBinding.idEditTextDetailRegisterNomorHandphone.setError("Nomor Handphone Tidak Boleh Kosong !");
                }else{
                    modelResponseAccountCall.enqueue(new Callback<ModelResponseAccount>() {
                        @Override
                        public void onResponse(Call<ModelResponseAccount> call, Response<ModelResponseAccount> response) {
                            if(response.body().getKode() == 1){
                                System.out.println("Succes = " + response.body().getKode() + response.body().getPesan());
                                Util.getApiRequetData().getOtp(email.trim()).enqueue(new Callback<ModelResponseAccount>() {
                                    @Override
                                    public void onResponse(Call<ModelResponseAccount> call, Response<ModelResponseAccount> response) {
                                        sendMessage(email, String.valueOf(response.body().getOtp()));
                                        if(response.body().getKode() == 1){
                                            modelAccount.setVerifyNumber(String.valueOf(response.body().getOtp()));
                                            Util.switchFragment(getActivity().getSupportFragmentManager(),
                                                    new FragmentSendOtp(modelAccount,1), "");
                                        }
                                    }
                                    @Override
                                    public void onFailure(Call<ModelResponseAccount> call, Throwable t) {

                                    }
                                });
                            }  else if(response.body().getKode() == 2){
                                Toast.makeText(getActivity().getApplicationContext(), "Mohon Maaf Email Anda Telah Digunakan !", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ModelResponseAccount> call, Throwable t) {
                            System.out.println("Gagal = " + t.getMessage());
                        }
                    });
                }


            }
        });
        Geocoder geocoder = new Geocoder(getActivity().getApplicationContext(), Locale.getDefault());
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 0, new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
//                try {
//                    addressList = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
//                    if (addressList != null){
//                        Address returnAdd = addressList.get(0);
//                        StringBuilder stringBuilder = new StringBuilder("");
//                        for (int i = 0; i<returnAdd.getMaxAddressLineIndex(); i++){
//                            stringBuilder.append(returnAdd.getAddressLine(i)).append("\n");
//                        }
//                        Log.w("my Location",stringBuilder.toString());
//                    } else {
//                        Log.w("my Location","No Address");
//                    }
//                } catch (Exception e){
//                    System.out.println(e.getMessage());
//                }
                if (oke) {
                    mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                        @Override
                        public void onMapClick(@NonNull LatLng latLng) {
                            try {
                                addressList = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                                MarkerOptions markerOptions = new MarkerOptions();
                                markerOptions.position(latLng);
                                String addressLine = addressList.get(0).getAddressLine(0);
                                LatLng Posisi = new LatLng(latLng.latitude, latLng.longitude);
                                System.out.println("CLick Map" + Posisi.longitude  + " : " + Posisi.latitude);
                                mMap.addMarker(new MarkerOptions().position(Posisi).title("Posisi"));
                                mMap.moveCamera(CameraUpdateFactory.newLatLng(Posisi));
                                fragmentDetailAkunBinding.idEditTextDetailRegisterAlamat.setText(addressLine);
                                alamatGet = addressLine;
                                System.out.println(alamatGet + " test ");
                                float zoomLevel = 16.0f;
                                mMap.clear();
                                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel));
                                mMap.addMarker(markerOptions);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                }
            }

        });

                return fragmentDetailAkunBinding.getRoot();
            }

            @SuppressLint("MissingPermission")
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                mMap = googleMap;
                oke = true;

                // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                mMap.setMyLocationEnabled(true);
            }
}
