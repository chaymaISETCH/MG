package com.example.sameh.mymg;

import android.graphics.Bitmap;

/**
 * Created by imen on 17/11/17.
 */

public class Produit {
    private String nom;
    private String prix;
    private Bitmap image;
    private String des;
    private String garantie;
private String idcat;

    private String prixP;

    public Produit(String s, String p) {
        setNom(s);
        setPrix(p);
    }

    public Produit() {
    }

    public String getNom() {
        return nom;
    }


    public void setNom(String n) {
        nom = n;
    }

    public String toString() {
        return nom;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getGarantie() {
        return garantie;
    }

    public void setGarantie(String garantie) {
        this.garantie = garantie;
    }


    public String getIdcat() {
        return idcat;
    }

    public void setIdcat(String idcat) {
        this.idcat = idcat;
    }

    public String getPrixP() {
        return prixP;
    }

    public void setPrixP(String prixP) {
        this.prixP = prixP;
    }
}