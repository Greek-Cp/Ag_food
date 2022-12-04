package com.example.agfood.Model;

public class ModelButton {
    private String nameButton;
    private boolean isClicked = false;
    private int imageRes;

    public ModelButton(String nameButton, boolean isClicked) {
        this.nameButton = nameButton;
        this.isClicked = isClicked;
    }

    public ModelButton(String nameButton, boolean isClicked, int imageRes) {
        this.nameButton = nameButton;
        this.isClicked = isClicked;
        this.imageRes = imageRes;
    }

    public String getNameButton() {
        return nameButton;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    public void setNameButton(String nameButton) {
        this.nameButton = nameButton;
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }
}
