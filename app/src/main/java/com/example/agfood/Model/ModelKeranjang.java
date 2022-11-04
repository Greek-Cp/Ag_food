package com.example.agfood.Model;

public class ModelKeranjang {
    ModelFood selectedFood;
    private boolean statusCheckBoxChecked = false;
    public ModelKeranjang(ModelFood selectedFood) {
        this.selectedFood = selectedFood;
    }
    public ModelFood getSelectedFood() {
        return selectedFood;
    }   

    public boolean isStatusCheckBoxChecked() {
        return statusCheckBoxChecked;
    }

    public void setStatusCheckBoxChecked(boolean statusCheckBoxChecked) {
        this.statusCheckBoxChecked = statusCheckBoxChecked;
    }

    public void setSelectedFood(ModelFood selectedFood) {
        this.selectedFood = selectedFood;
    }
}
