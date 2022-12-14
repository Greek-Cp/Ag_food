package com.example.agfood.Fragment;

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
import com.example.agfood.DataModel.ModelResponseAccount;
import com.example.agfood.Model.ModelAccount;
import com.example.agfood.Model.ModelRetrieveAccount;
import com.example.agfood.R;
import com.example.agfood.Util.Util;
import com.example.agfood.databinding.FragmentLoginBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.sanju.motiontoast.MotionToast;
import www.sanju.motiontoast.MotionToastStyle;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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

    List<ModelRetrieveAccount> listDataAccount = new ArrayList<>();
    public void retrieveData(){
        }


    void recolorTextView(){
        Util.setCustomColorText(fragmentLoginBinding.idTvTittleApp,"AG", " FOOD","ff4552");
        Util.setCustomColorText(fragmentLoginBinding.idTvDoesntHaveAcc, "Don't have an account?",  " Sign Up","ff4552");
    }
    void loginApp(){
        fragmentLoginBinding.idBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fragmentLoginBinding.idEditTextEmail.getText().toString().isEmpty() && fragmentLoginBinding.idEditTextPassword.getText().toString().isEmpty()){
                    Toast.makeText(getActivity().getApplicationContext(),"Email Dan Password Tidak Boleh Kosong !",Toast.LENGTH_SHORT).show();
                } else{
                    APIRequestData ardData = BaseServerApp.konekRetrofit().create(APIRequestData.class);
                    Call<ModelResponseAccount> getLoginResponse = ardData.loginAccount(fragmentLoginBinding.idEditTextEmail.getText().toString(), fragmentLoginBinding.idEditTextPassword.getText().toString());
                    getLoginResponse.enqueue(new Callback<ModelResponseAccount>() {
                            @Override
                            public void onResponse(Call<ModelResponseAccount> call, Response<ModelResponseAccount> response) {
                                System.out.println(response.body().kode + " kode pesan login");
                                if(response.body().kode == 3){
                                    Util.switchFragment(getActivity().getSupportFragmentManager(), new FragmentSendOtp(response.body().getDetail_account().get(0),2),"" );
                                } else if (response.body().kode == 1){
                                MotionToast.Companion.createColorToast(getActivity(), "Login Berhasil",
                                        "Login Telah Berhasil", MotionToastStyle.SUCCESS,MotionToast.GRAVITY_TOP,MotionToast.LONG_DURATION,ResourcesCompat.getFont(getActivity().getApplicationContext(),R.font.sfprodisplayregular
                                        ));
                                        getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in,R.anim.fade_out,R.anim.fade_in,R.anim.slide_out).replace(R.id.id_base_frame_layout,new FragmentSuccesfullLogin(response.body().getDetail_account().get(0))).commit();
                            } else if(response.body().kode == 4){
                                MotionToast.Companion.createColorToast(getActivity(),"Login Gagal","Akun Anda Sedang Login Di Device Lain ! Harap Log-Out Dari Device Tersebut",
                                        MotionToastStyle.ERROR,MotionToast.GRAVITY_CENTER,MotionToast.LONG_DURATION,ResourcesCompat.getFont(getActivity().getApplicationContext(),R.font.sfprodisplayregular));
                            } else if(response.body().kode == 5){
                                MotionToast.Companion.createColorToast(getActivity(),"Login Gagal","Akun Tidak Temukan ! Harap Daftar Terlebih Dahulu",
                                        MotionToastStyle.ERROR,MotionToast.GRAVITY_CENTER,MotionToast.LONG_DURATION,ResourcesCompat.getFont(getActivity().getApplicationContext(),R.font.sfprodisplayregular));
                            } else if(response.body().kode == 0){
                                MotionToast.Companion.createColorToast(getActivity(),"Login Gagal","Mohon Periksa Password Anda !",
                                        MotionToastStyle.ERROR,MotionToast.GRAVITY_CENTER,MotionToast.LONG_DURATION,ResourcesCompat.getFont(getActivity().getApplicationContext(),R.font.sfprodisplayregular));

                            }
                        }
                        @Override
                        public void onFailure(Call<ModelResponseAccount> call, Throwable t) {

                        }
                    });
                }


                /*
                  for(ModelRetrieveAccount modelAccount : listDataAccount){
                    System.out.println("Data = " + modelAccount.getEmail());
                    System.out.println("Password = " +modelAccount.getPassword());
                    if(modelAccount.getEmail().equals(fragmentLoginBinding.idEditTextEmail.getText().toString())
                            && modelAccount.getPassword().equals(fragmentLoginBinding.idEditTextPassword.getText().toString())){
                        MotionToast.Companion.createColorToast(getActivity(), "Login Berhasil",
                                "Login Telah Berhasl", MotionToastStyle.SUCCESS,MotionToast.GRAVITY_TOP,MotionToast.LONG_DURATION,ResourcesCompat.getFont(getActivity().getApplicationContext(),R.font.sfprodisplayregular
                                ));
                        getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in,R.anim.fade_out,R.anim.fade_in,R.anim.slide_out).replace(R.id.id_base_frame_layout,new FragmentSuccesfullLogin(modelAccount)).commit();
                        break;
                    } else{
                        MotionToast.Companion.createColorToast(getActivity(), "Login Gagal",
                                "Harap Cek Email Dan Password Anda !", MotionToastStyle.ERROR,MotionToast.GRAVITY_CENTER,MotionToast.LONG_DURATION,ResourcesCompat.getFont(getActivity().getApplicationContext(),R.font.sfprodisplayregular
                                ));
                    }

                }
                 */


            }
        });
        }
    FragmentLoginBinding fragmentLoginBinding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         fragmentLoginBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_login, container,false);
         getActivity().findViewById(R.id.id_nav_bar).setVisibility(View.INVISIBLE);

         fragmentLoginBinding.idTvDoesntHaveAcc.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in,R.anim.fade_out,R.anim.fade_in,R.anim.slide_out).replace(R.id.id_base_frame_layout,new FragmentRegisterAccount()).commit();
             }
         });
        fragmentLoginBinding.idTextViewLupaPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.switchFragment(getActivity().getSupportFragmentManager(), new FragmentResetPassword(),"FRAGMENT_RESET_PASSWORD");
            }
        });
         recolorTextView();
         loginApp();
         return fragmentLoginBinding.getRoot();

    }

    /**
     * A simple {@link Fragment} subclass.
     * Use the {@link IntroFragment#newInstance} factory method to
     * create an instance of this fragment.
     */
    public static class IntroFragment extends Fragment {

        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private static final String ARG_PARAM1 = "param1";
        private static final String ARG_PARAM2 = "param2";

        // TODO: Rename and change types of parameters
        private String mParam1;
        private String mParam2;

        public IntroFragment() {
            // Required empty public constructor
        }

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment IntroFragment.
         */
        // TODO: Rename and change types and number of parameters
        public static IntroFragment newInstance(String param1, String param2) {
            IntroFragment fragment = new IntroFragment();
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
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_intro, container, false);
        }
    }
}