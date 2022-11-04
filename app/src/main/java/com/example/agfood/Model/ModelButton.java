package com.example.agfood.Model;

public class ModelButton {
    private String nameButton;
    private boolean isClicked = false;

    public ModelButton(String nameButton, boolean isClicked) {
        this.nameButton = nameButton;
        this.isClicked = isClicked;
    }

    public String getNameButton() {
        return nameButton;
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
