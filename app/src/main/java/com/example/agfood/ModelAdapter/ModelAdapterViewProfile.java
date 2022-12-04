package com.example.agfood.ModelAdapter;

public class ModelAdapterViewProfile {
    private String nameButton;
    private int imageButton;

    public ModelAdapterViewProfile(String nameButton, int imageButton) {
        this.nameButton = nameButton;
        this.imageButton = imageButton;
    }

    public String getNameButton() {
        return nameButton;
    }

    public void setNameButton(String nameButton) {
        this.nameButton = nameButton;
    }

    public int getImageButton() {
        return imageButton;
    }

    public void setImageButton(int imageButton) {
        this.imageButton = imageButton;
    }
}
