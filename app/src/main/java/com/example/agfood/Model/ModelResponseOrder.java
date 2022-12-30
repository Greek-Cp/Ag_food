package com.example.agfood.Model;

import com.google.gson.annotations.SerializedName;

public class ModelResponseOrder {
    @SerializedName("status_order")
    String status_bayar;

    public ModelResponseOrder(String status_bayar) {
        this.status_bayar = status_bayar;
    }
    public String getStatus_bayar() {
        return status_bayar;
    }
    public void setStatus_bayar(String status_bayar) {
        this.status_bayar = status_bayar;
    }
}
