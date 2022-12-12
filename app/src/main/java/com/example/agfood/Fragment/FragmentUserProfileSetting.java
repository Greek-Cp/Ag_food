package com.example.agfood.Fragment;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.example.agfood.API.APIRequestData;
import com.example.agfood.DataModel.ModelResponseAccount;
import com.example.agfood.Model.ModelAccount;
import com.example.agfood.Model.ModelResponseUpload;
import com.example.agfood.R;
import com.example.agfood.Util.SharedPrefDetail;
import com.example.agfood.Util.Util;
import com.example.agfood.databinding.FragmentUserProfileSettingBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.sanju.motiontoast.MotionToast;
import www.sanju.motiontoast.MotionToastStyle;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentUserProfileSetting#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentUserProfileSetting extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentUserProfileSetting() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentUserProfileSetting.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentUserProfileSetting newInstance(String param1, String param2) {
        FragmentUserProfileSetting fragment = new FragmentUserProfileSetting();
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

    ModelAccount mdl;
   // StorageReference mStorageRef;
   // private StorageTask mUploadTask;
    String part_image;
    FragmentUserProfileSettingBinding fragmentUserProfileSettingBinding;
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

                  fragmentUserProfileSettingBinding.idCircularImageViewProfile.setImageURI(selectedImage);
                }
            });
    private String getFileExtension(Uri uri) {
        ContentResolver cR = getActivity().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
    public void uploadImage() {
        MotionToast.Companion.createColorToast(getActivity(), "Upload Photo Berhasil",
                "Selamat Foto Profil Anda Berhasil Di Ganti", MotionToastStyle.SUCCESS,MotionToast.GRAVITY_TOP,MotionToast.LONG_DURATION, ResourcesCompat.getFont(getActivity().getApplicationContext(),R.font.sfprodisplayregular
                ));
        File imageFile = new File(part_image);
        RequestBody reqBody = RequestBody.create(MediaType.parse("multipart/form-file"), imageFile);
        MultipartBody.Part partImage = MultipartBody.Part.createFormData("file", imageFile.getName(), reqBody);
        RequestBody  filename = RequestBody.create(MediaType.parse("text/plain"), "profile_" + mdl.getUsername());
        Util.getApiRequetData().uploadImage(partImage,filename).enqueue(new Callback<ModelResponseUpload>() {
            @Override
            public void onResponse(Call<ModelResponseUpload> call, Response<ModelResponseUpload> response) {
                System.out.println(response.body().getLink() + " MESSAGE");
                Util.getApiRequetData().updateProfilePicture(mdl.getEmail(),response.body().getLink()).enqueue(new Callback<ModelResponseAccount>() {
                    @Override
                    public void onResponse(Call<ModelResponseAccount> call, Response<ModelResponseAccount> response) {
                        System.out.println(response.body().getPesan());
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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentUserProfileSettingBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_user_profile_setting,container
        ,false);

        Util.hiddenNavBottom(getActivity());
        UtilPref utilPref = new UtilPref();
        SharedPrefDetail sharedPrefDetailAccount = utilPref.accountPrefences;
        mdl = Util.getCurrentAccount(sharedPrefDetailAccount, getActivity());
        fragmentUserProfileSettingBinding.idEditTextEmail.setText(mdl.getEmail());
        Util.getApiRequetData().getImageProfile(
                mdl.getEmail()
        ).enqueue(new Callback<ModelResponseAccount>() {
            @Override
            public void onResponse(Call<ModelResponseAccount> call, Response<ModelResponseAccount> response) {
                if(response.body().gambar_profile == null){
                    System.out.println("Profile Belum Di Set !");
                } else{
                    Picasso.get()
                            .load(response.body().getGambar_profile()).resize(512,512).centerCrop()
                            .into(fragmentUserProfileSettingBinding.idCircularImageViewProfile);
                }
            }

            @Override
            public void onFailure(Call<ModelResponseAccount> call, Throwable t) {

            }
        });
        fragmentUserProfileSettingBinding.idEditTextNoHandphone.setText(String.valueOf(mdl.getNoHp()));
        fragmentUserProfileSettingBinding.idEditTextUsername.setText(String.valueOf(mdl.getUsername()));
        fragmentUserProfileSettingBinding.idEditTextNamaLengkap.setText(String.valueOf(mdl.getNamaLengkap()));

        Util.getApiRequetData().getNamaLengkap(mdl.getIdAkun()).enqueue(new Callback<ModelResponseAccount>() {
            @Override
            public void onResponse(Call<ModelResponseAccount> call, Response<ModelResponseAccount> response) {
                  fragmentUserProfileSettingBinding.idEditTextNamaLengkap.setText(response.body().nama_lengkap);
            }

            @Override
            public void onFailure(Call<ModelResponseAccount> call, Throwable t) {

            }
        });
        fragmentUserProfileSettingBinding.idEditTextUsername.setEnabled(false);
        fragmentUserProfileSettingBinding.idEditTextNoHandphone.setEnabled(false);
        fragmentUserProfileSettingBinding.idEditTextEmail.setEnabled(false);
        fragmentUserProfileSettingBinding.idCircularImageViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                someActivityResultLauncher.launch(photoPickerIntent);
            }
        });

        fragmentUserProfileSettingBinding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(part_image != null){
                    uploadImage();
                }
                Util.getApiRequetData().updateNamaLengkap(mdl.getIdAkun(),fragmentUserProfileSettingBinding.idEditTextNamaLengkap.getText().toString()).enqueue(new Callback<ModelResponseAccount>() {
                    @Override
                    public void onResponse(Call<ModelResponseAccount> call, Response<ModelResponseAccount> response) {

                    }

                    @Override
                    public void onFailure(Call<ModelResponseAccount> call, Throwable t) {

                    }
                });
            }
        });
        fragmentUserProfileSettingBinding.idBtnDetailMakananBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.switchFragment(getActivity().getSupportFragmentManager(),new HomeFragment(),"FRAGMENT_HOME");
            }
        });
        if(mdl.getStatusVerif().equals("1")){
            fragmentUserProfileSettingBinding.idEditTextStatusVerifikasi.setText("Status Terverifikasi");
            fragmentUserProfileSettingBinding.idEditTextStatusVerifikasi.setEnabled(false);
        } else{
            fragmentUserProfileSettingBinding.idEditTextStatusVerifikasi.setText("Belum Terverifikasi");
            fragmentUserProfileSettingBinding.idEditTextStatusVerifikasi.setEnabled(false);
        }
        return fragmentUserProfileSettingBinding.getRoot();
    }
}