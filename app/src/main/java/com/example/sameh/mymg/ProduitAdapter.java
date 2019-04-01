package com.example.sameh.mymg;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;

import java.util.List;

public class ProduitAdapter extends ArrayAdapter<Produit> {
    Animation scaleUp;

    public ProduitAdapter(Context c,List<Produit> p){

        super(c,0,p);


    }
    @Override
    public View getView(int position,View recup,ViewGroup parent)
    {
        ProduitView vueItem =(ProduitView) recup;
        if(vueItem == null)
            vueItem = ProduitView.create(parent);

        vueItem.display(super.getItem(position));
        return vueItem;


    }


}