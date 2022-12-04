package com.example.agfood.Fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.agfood.DataModel.ModelResponseAccount;
import com.example.agfood.R;
import com.example.agfood.Util.Util;
import com.example.agfood.databinding.FragmentResetPasswordBinding;

import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

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
 * Use the {@link FragmentResetPassword#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentResetPassword extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentResetPassword() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentResetPassword.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentResetPassword newInstance(String param1, String param2) {
        FragmentResetPassword fragment = new FragmentResetPassword();
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

    static String generateOtp(){
        int arr[] = new int[5];
        StringBuilder sb = new StringBuilder();
        for(int t = 0; t < 5; t++){
            arr[t] = ThreadLocalRandom.current().nextInt(1,9);
            sb.append(arr[t]);
        }
        return sb.toString();
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
            mimeMessage.setHeader("Content-Type", "text/html");

            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(receiveEmail));
            mimeMessage.setSubject("Lupa Password");

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
                    "                                        <h1 style=\"color:#1e1e2d; font-weight:500; margin:0;font-size:32px;font-family:'Rubik',sans-serif;\">Reset Password\n" +
                    "                                        </h1>\n" +
                    "                                        <p style=\"font-size:15px; color:#455056; margin:8px 0 0; line-height:24px;\">\n" +
                    "                                            Kode Reset Password Anda\n" +
                    "                                            <h1 style=\"color: black;\">" + otpKode + "</h1>\n" +
                    "                                            Untuk mengganti passsword akun anda ,harap masukan kode di atas.\n" +
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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Util.hiddenNavBottom(getActivity());
        FragmentResetPasswordBinding fragmentResetPasswordBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_reset_password, container, false);
        int verifiy = Integer.parseInt(generateOtp());
        fragmentResetPasswordBinding.idBtnKirimKodeVerifikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = fragmentResetPasswordBinding.idEditTextInputEmailLupaPassword.getText().toString().trim();
                System.out.println("email = " + email);
                Util.getApiRequetData().validateEmail(email).enqueue(new Callback<ModelResponseAccount>() {
                  @Override
                  public void onResponse(Call<ModelResponseAccount> call, Response<ModelResponseAccount> response) {
                      System.out.println("response = " + response.body().getPesan() + " message ");
                      if(response.body().getKode() == 1){
                          Toast.makeText(getActivity().getApplicationContext(), "Email Terverifikasi", Toast.LENGTH_LONG).show();
                          sendMessage(fragmentResetPasswordBinding.idEditTextInputEmailLupaPassword.getText().toString(), String.valueOf(verifiy));
                          Util.switchFragment(getActivity().getSupportFragmentManager(), new FragmentResetPasswordInputVerifyNumberOne(verifiy,fragmentResetPasswordBinding.idEditTextInputEmailLupaPassword.getText().toString().trim()), "FRAGMENT_RESET");
                      } else{
                          Toast.makeText(getActivity().getApplicationContext(), "Maaf email anda tidak terdaftar", Toast.LENGTH_LONG).show();
                      }
                  }
                  @Override
                  public void onFailure(Call<ModelResponseAccount> call, Throwable t) {
                        Toast.makeText(getActivity().getApplicationContext(), "Maaf telah terjadi error" + t.getMessage(), Toast.LENGTH_SHORT).show();
                  }
              });
              System.out.println(verifiy);
            }
        });
        return fragmentResetPasswordBinding.getRoot();
    }
}