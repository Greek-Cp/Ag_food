package com.example.agfood.ModelAdapter;

public class ModelAdapterMetodePesanan {
    private String metodePesananan;
    private String deskripiPesanan;

    private boolean isMetodePesananSelected;

    public ModelAdapterMetodePesanan(String metodePesananan, boolean isMetodePesananSelected,String deskripiPesanan) {
        this.metodePesananan = metodePesananan;
        this.isMetodePesananSelected = isMetodePesananSelected;
        this.deskripiPesanan = deskripiPesanan;
    }

    public String getMetodePesananan() {
        return metodePesananan;
    }

    public String getDeskripiPesanan() {
        return deskripiPesanan;
    }

    public void setDeskripiPesanan(String deskripiPesanan) {
        this.deskripiPesanan = deskripiPesanan;
    }

    public void setMetodePesananan(String metodePesananan) {
        this.metodePesananan = metodePesananan;
    }

    public boolean isMetodePesananSelected() {
        return isMetodePesananSelected;
    }

    public void setMetodePesananSelected(boolean metodePesananSelected) {
        isMetodePesananSelected = metodePesananSelected;
    }
}
