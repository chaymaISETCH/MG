package com.example.sameh.mymg;

import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import java.util.List;


/**
 * Created by imen on 2/12/17.
 */

public class RecipeAdapter extends ArrayAdapter<Recipe> {
    public RecipeAdapter(Context c,List<Recipe> r){

        super(c,0,r);


    }
    @Override
    public View getView(int position,View recup,ViewGroup parent)
    {
        RecipeView vueItem =(RecipeView) recup;
        if(vueItem == null)
            vueItem = RecipeView.create(parent);

        vueItem.display(super.getItem(position));
        return vueItem;


    }



}
