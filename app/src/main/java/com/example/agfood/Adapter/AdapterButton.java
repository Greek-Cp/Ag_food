package com.example.agfood.Adapter;

import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.animation.animpresseffect.PressEffectCardView;
import com.example.agfood.Model.ModelButton;
import com.example.agfood.R;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class AdapterButton extends RecyclerView.Adapter<AdapterButton.ViewHolder> {
    List<ModelButton> listButtonName;
    AdapterButtonClickListener adapterButtonClickListener;
    public AdapterButton(List<ModelButton> listButtonName, AdapterButtonClickListener adapterButtonClickListener) {
        this.listButtonName = listButtonName;
        this.adapterButtonClickListener = adapterButtonClickListener;
    }
    public interface AdapterButtonClickListener{
        void clickButtonListener(int positionOfButton);
    }
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_adapter_button, parent,false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nameButton.setText(listButtonName.get(position).getNameButton());
        if(listButtonName.get(position).getImageRes() != 0){
            holder.imageViewIconCategory.setImageResource(listButtonName.get(position).getImageRes());
        }

        if(listButtonName.get(position).isClicked()){
            holder.cardViewButton.setCardBackgroundColor(Color.parseColor("#ff4552"));
            holder.nameButton.setTextColor(Color.parseColor("#ffffff"));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                holder.cardViewButton.setOutlineAmbientShadowColor(Color.parseColor("#ff4552"));
                holder.cardViewButton.setOutlineSpotShadowColor(Color.parseColor("#ff4552"));
            }
        } else{
            holder.cardViewButton.setCardBackgroundColor(Color.parseColor("#e6e6e6"));
            holder.nameButton.setTextColor(Color.parseColor("#000000"));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                holder.cardViewButton.setOutlineAmbientShadowColor(Color.parseColor("#000000"));
                holder.cardViewButton.setOutlineSpotShadowColor(Color.parseColor("#000000"));
            }
        }
    }
    @Override
    public int getItemCount() {
        return listButtonName.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        PressEffectCardView cardViewButton;
        TextView nameButton;
        ImageView imageViewIconCategory;
        boolean[] mListButtonCondition = new boolean[listButtonName.size()];
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewButton = itemView.findViewById(R.id.id_button_kategori);
            nameButton = itemView.findViewById(R.id.id_text_button_kategory);
            imageViewIconCategory = itemView.findViewById(R.id.imgview_kategory);
            cardViewButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            adapterButtonClickListener.clickButtonListener(getAdapterPosition());
        }
    }
}
