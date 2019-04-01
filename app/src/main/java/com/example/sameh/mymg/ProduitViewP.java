package com.example.sameh.mymg;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProduitViewP extends LinearLayout {

    private TextView tvNom, tvPrix,tvPrixp;
    private ImageView tvImg;

    public ProduitViewP(Context c, AttributeSet attrs) {
        super(c, attrs);

    }


    public static ProduitViewP create(ViewGroup parent) {

        LayoutInflater li = LayoutInflater.from(parent.getContext());
        ProduitViewP itemView = (ProduitViewP) li.inflate(R.layout.item, parent, false);
        itemView.findViews();
        return itemView;

    }

    private void findViews() {
        tvNom = (TextView) findViewById(R.id.item_nom);
       tvPrix = (TextView) findViewById(R.id.item_prix);
     //   tvPrixp = (TextView) findViewById(R.id.item_prixp);

       tvImg = (ImageView) findViewById(R.id.item_image);
    }

    public void display(final Produit p) {
       tvNom.setText(p.getNom());
        tvPrix.setText(p.getPrix() + " DT");

        tvImg.setImageBitmap(p.getImage());
        tvPrixp.setText(p.getPrixP()+" DT");

    }

}