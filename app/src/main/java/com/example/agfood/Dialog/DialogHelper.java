package com.example.agfood.Dialog;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.databinding.DataBindingUtil;

import com.example.agfood.R;
import com.example.agfood.databinding.DialogCloseBinding;

public class DialogHelper extends AppCompatDialogFragment implements View.OnClickListener {
    Dialog dialog;
    DialogCloseBinding dialogCloseBinding;
    DialogHelper.DialogListener dialogListener;

    public DialogHelper(DialogListener dialogListener){
            this.dialogListener = dialogListener;
        }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.id_btn_log_out:
                dialog.dismiss();;
                dialogListener.clickIya();

                break;
            case R.id.id_btn_batal:
                dialog.dismiss();
                dialogListener.clickTidak();

                break;

        }
    }

    public interface DialogListener  {
        void clickIya();
        void clickTidak();
    }

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        dialog = new Dialog(getActivity());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogCloseBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.dialog_close,null,false);
        dialogCloseBinding.idBtnLogOut.setOnClickListener(this::onClick);
        dialogCloseBinding.idBtnBatal.setOnClickListener(this::onClick);
        dialog.setContentView(dialogCloseBinding.getRoot());
        return dialog;
    }
}
