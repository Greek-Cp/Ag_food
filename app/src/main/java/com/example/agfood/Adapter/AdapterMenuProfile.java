package com.example.agfood.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agfood.ModelAdapter.ModelAdapterViewProfile;
import com.example.agfood.R;
import com.google.gson.Gson;

import java.util.List;

public class AdapterMenuProfile extends RecyclerView.Adapter<AdapterMenuProfile.ViewHolder> {

    AdapterMenuProfile.AdapterMenuProfileListener adapterMenuProfileListener;
    List<ModelAdapterViewProfile> listButtonProfile;
    public AdapterMenuProfile(AdapterMenuProfileListener adapterMenuProfileListener, List<ModelAdapterViewProfile> listButtonMenu) {
        this.adapterMenuProfileListener = adapterMenuProfileListener;
        this.listButtonProfile = listButtonMenu;
        System.out.println("Size = " + listButtonMenu.size());
        Gson gson = new Gson();
        System.out.println("log = " + gson.toJson(listButtonMenu));
        System.out.println("Initialize Adapter Succes");
    }
    public interface AdapterMenuProfileListener{
        void clickMenuItemListener(int position);
    }
    @Override
    public AdapterMenuProfile.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_adapter_menu_button,parent,false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMenuProfile.ViewHolder holder, int position) {
        holder.textViewNameButton.setText(listButtonProfile.get(position).getNameButton());
        holder.imageViewButton.setImageResource(listButtonProfile.get(position).getImageButton());
    }

    @Override
    public int getItemCount() {
        System.out.println(listButtonProfile.size() + " SIZE");
        return listButtonProfile.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageViewButton;
        TextView textViewNameButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewButton = itemView.findViewById(R.id.id_adapter_profile_menu_img);
            textViewNameButton = itemView.findViewById(R.id.id_adapter_profile_menu_name);
            itemView.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View view) {
            adapterMenuProfileListener.clickMenuItemListener(getAdapterPosition());
        }
    }
}
