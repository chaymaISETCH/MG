package com.example.sameh.mymg;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by imen on 17/11/17.
 */

public class Recipe {
    private String name;
    private String difficult;
    private Bitmap image;
    private String time;
    private String description;

    private List<Produit> products = new ArrayList<Produit>();


    public Recipe() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDifficult() {
        return difficult;
    }

    public void setDifficult(String difficult) {
        this.difficult = difficult;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Produit> getProducts() {
        return products;
    }

    public void setProducts(List<Produit> products) {
        this.products = products;
    }
}