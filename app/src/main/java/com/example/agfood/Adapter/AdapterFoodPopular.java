package com.example.agfood.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agfood.Model.ModelFood;
import com.example.agfood.R;
import com.example.agfood.Util.Util;
import com.example.agfood.databinding.ItemFoodLayoutAdapterBinding;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

public class AdapterFoodPopular extends RecyclerView.Adapter {
    List<ModelFood> listModelFood;
    Context context;
    AdapterFoodPopular.AdapterFoodInterface mAdapterFoodPopularInterface;
    public interface AdapterFoodInterface{
        void clickItemSelectedListener(int positionOfItemFoodSelected);
    }
    public AdapterFoodPopular(List<ModelFood> listModelFood, Context context,AdapterFoodPopular.AdapterFoodInterface adapterFoodInterface) {
        this.listModelFood = listModelFood;
        this.context = context;
        this.mAdapterFoodPopularInterface = adapterFoodInterface;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
         ItemFoodLayoutAdapterBinding itemFoodLayoutAdapterBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_food_layout_adapter,parent,false);
        return new AdapterFoodViewHolder(itemFoodLayoutAdapterBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        AdapterFoodViewHolder adapterFoodViewHolder = (AdapterFoodViewHolder) holder;
        adapterFoodViewHolder.itemFoodLayoutAdapterBinding.adapterIdTvFoodName.setText(listModelFood.get(position).getNameFood());
        adapterFoodViewHolder.itemFoodLayoutAdapterBinding.adapterIdTvFoodPrice.setText(String.valueOf(Util.convertToRupiah(listModelFood.get(position).getHargaFood())));
        adapterFoodViewHolder.itemFoodLayoutAdapterBinding.idImageItemFood.setImageResource(listModelFood.get(position).getImageFood());
        adapterFoodViewHolder.itemFoodLayoutAdapterBinding.setNamaFood(listModelFood.get(position).getNameFood());
    }

    @Override
    public int getItemCount() {
        return listModelFood.size();
    }
    public class AdapterFoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ItemFoodLayoutAdapterBinding itemFoodLayoutAdapterBinding;
        public AdapterFoodViewHolder(@NonNull ItemFoodLayoutAdapterBinding ItemFoodLayoutAdapterBinding) {
            super(ItemFoodLayoutAdapterBinding.getRoot());
            itemFoodLayoutAdapterBinding = ItemFoodLayoutAdapterBinding;
            itemFoodLayoutAdapterBinding.card.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            mAdapterFoodPopularInterface.clickItemSelectedListener(getAdapterPosition());
        }
    }
}
