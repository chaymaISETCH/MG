package com.example.sameh.mymg;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by imen on 2/12/17.
 */

public class RecipeView extends LinearLayout {
    private TextView tvName, tvDifficult;
    private ImageView tvImage;

    public RecipeView(Context c, AttributeSet attrs) {
        super(c, attrs);

    }


    public static RecipeView create(ViewGroup parent) {

        LayoutInflater li = LayoutInflater.from(parent.getContext());
        RecipeView itemView = (RecipeView) li.inflate(R.layout.item, parent, false);
        itemView.findViews();
        return itemView;

    }

    private void findViews() {
        tvName = (TextView) findViewById(R.id.item_name);
        tvDifficult = (TextView) findViewById(R.id.item_dificult);
        tvImage = (ImageView) findViewById(R.id.item_image_recipe);
    }

    public void display(final Recipe r) {
        tvName.setText(r.getName());
        tvDifficult.setText(r.getDifficult());

        tvImage.setImageBitmap(r.getImage());


    }


}
