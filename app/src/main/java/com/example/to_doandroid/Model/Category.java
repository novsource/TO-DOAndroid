package com.example.to_doandroid.Model;

import android.media.Image;

public class Category {
    String nameCategory;
    Image imageCategory;

    public Image getImageCategory() {
        return imageCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setImageCategory(Image imageCategory) {
        this.imageCategory = imageCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }
}
