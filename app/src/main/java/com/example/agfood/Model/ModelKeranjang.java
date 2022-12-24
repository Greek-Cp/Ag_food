package com.example.agfood.Model;

public class ModelKeranjang {
    ModelBarang selectedFood;
    private boolean statusCheckBoxChecked = false;

    public ModelKeranjang(ModelBarang selectedFood) {
        this.selectedFood = selectedFood;
    }
    public ModelBarang getSelectedFood() {
        return selectedFood;
    }   
    public boolean isStatusCheckBoxChecked() {
        return statusCheckBoxChecked;
    }
    public void setStatusCheckBoxChecked(boolean statusCheckBoxChecked) {
        this.statusCheckBoxChecked = statusCheckBoxChecked;
    }
    public void setSelectedFood(ModelBarang selectedFood) {
        this.selectedFood = selectedFood;
    }
}
