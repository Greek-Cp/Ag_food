package com.example.agfood.Fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.agfood.R;
import com.example.agfood.Util.Util;
import com.example.agfood.databinding.FragmentResetPasswordInputVerifyNumberOneBinding;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentResetPasswordInputVerifyNumberOne#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentResetPasswordInputVerifyNumberOne extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    int otps;
    String email;
    public FragmentResetPasswordInputVerifyNumberOne(int otp
    ,String email){
        this.otps = otp;
        this.email = email;
    }
    public FragmentResetPasswordInputVerifyNumberOne() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentResetPasswordInputVerifyNumberOne.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentResetPasswordInputVerifyNumberOne newInstance(String param1, String param2) {
        FragmentResetPasswordInputVerifyNumberOne fragment = new FragmentResetPasswordInputVerifyNumberOne();
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
        FragmentResetPasswordInputVerifyNumberOneBinding fragmentResetPasswordInputVerifyNumberOneBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_reset_password_input_verify_number_one, container, false);
        fragmentResetPasswordInputVerifyNumberOneBinding.idBtnVerifikasiKode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int otp = Integer.parseInt(fragmentResetPasswordInputVerifyNumberOneBinding.otpView.getOTP());
                if(otp == otps){
                    Util.switchFragment(getActivity().getSupportFragmentManager(), new FragmentResetPasswordInputNewPassword(email),"FRAGMENT_INPUT_NEW_PASSWORD");
                } else{
                    Toast.makeText(getActivity().getApplicationContext(), "Kode Verifikasi Gagal", Toast.LENGTH_LONG).show();
                }
            }
        });
        fragmentResetPasswordInputVerifyNumberOneBinding.idTvKirimulangOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage(email, String.valueOf(otps));
                fragmentResetPasswordInputVerifyNumberOneBinding.idTvKirimulangOtp.setVisibility(View.INVISIBLE);
            }
        });
        return fragmentResetPasswordInputVerifyNumberOneBinding.getRoot();
    }
    public void sendMessage(String receiveEmail,String otpKode) {
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

            mimeMessage.setContent("\n" +
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
}