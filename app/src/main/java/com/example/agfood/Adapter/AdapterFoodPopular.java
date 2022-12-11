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

import com.example.agfood.Model.ModelBarang;
import com.example.agfood.Model.ModelFav;
import com.example.agfood.Model.ModelFood;
import com.example.agfood.R;
import com.example.agfood.Util.Util;
import com.example.agfood.databinding.ItemFoodLayoutAdapterBinding;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

public class AdapterFoodPopular extends RecyclerView.Adapter {
    List<ModelBarang> listModelFood;
    List<ModelFav> listFavFood;
    Context context;
    AdapterFoodPopular.AdapterFoodInterface mAdapterFoodPopularInterface;
    public interface AdapterFoodInterface{
        void clickItemSelectedListener(int positionOfItemFoodSelected);
        void clickLoveListener(int positionOfItemLikeByUser);
    }
    public AdapterFoodPopular(List<ModelBarang> listModelFood,List<ModelFav> listFavFood, Context context,AdapterFoodPopular.AdapterFoodInterface adapterFoodInterface) {
        this.listModelFood = listModelFood;
        this.listFavFood = listFavFood;
        this.context = context;
        this.mAdapterFoodPopularInterface = adapterFoodInterface;
    }
    public AdapterFoodPopular(List<ModelBarang> listModelFood, Context context,AdapterFoodPopular.AdapterFoodInterface adapterFoodInterface) {
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
        adapterFoodViewHolder.itemFoodLayoutAdapterBinding.adapterIdTvFoodName.setText(listModelFood.get(position).getNama_barang());
        adapterFoodViewHolder.itemFoodLayoutAdapterBinding.adapterIdTvFoodPrice.setText(String.valueOf(Util.convertToRupiah(listModelFood.get(position).getHargaOriginal())));
        System.out.println("Iamge = " + listModelFood.get(position).getGambar_barang());
        Picasso.get()
                .load(listModelFood.get(position).getGambar_barang()).resize(512,512).centerCrop()
                .into(adapterFoodViewHolder.itemFoodLayoutAdapterBinding.idImageItemFood);
        adapterFoodViewHolder.itemFoodLayoutAdapterBinding.idLoveStatic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterFoodViewHolder.itemFoodLayoutAdapterBinding.idLoveStatic.setVisibility(View.INVISIBLE);
                adapterFoodViewHolder.itemFoodLayoutAdapterBinding.idLoveAnim.setVisibility(View.VISIBLE);
                adapterFoodViewHolder.itemFoodLayoutAdapterBinding.idLoveAnim.playAnimation();
                mAdapterFoodPopularInterface.clickLoveListener(adapterFoodViewHolder.getAdapterPosition());
            }
        });
        adapterFoodViewHolder.itemFoodLayoutAdapterBinding.idLoveAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterFoodViewHolder.itemFoodLayoutAdapterBinding.idLoveStatic.setVisibility(View.VISIBLE);
                adapterFoodViewHolder.itemFoodLayoutAdapterBinding.idLoveAnim.setVisibility(View.INVISIBLE);
                mAdapterFoodPopularInterface.clickLoveListener(adapterFoodViewHolder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return listModelFood == null ? 0 : listModelFood.size();
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
