package com.example.sameh.mymg;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;

import java.util.List;

public class Recipes extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView list;
    List<Produit> mliste;

    RecipeAdapter a;
    public static Recipe recipe= new Recipe();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
      /*  BoomMenuButton bmb = (BoomMenuButton) findViewById(R.id.bmb);
        int[] bd = new int[]{
                R.drawable.heart,
                R.drawable.list,
                R.drawable.catalogue,
                R.drawable.parking,
                R.drawable.recette,
                R.drawable.facebook

        };
        String[] bd1 = new String[]{
                "centre d'intérêt",
                "Liste",
                "Catalogue",
                "Place Parking",
                "Recette",
                "Facebook"
        };*

        for (int i = 0; i < bd.length; i++) {
            TextInsideCircleButton.Builder builder = new TextInsideCircleButton.Builder()
                    .normalImageRes(bd[i])
                    .normalText(bd1[i]);

            bmb.addBuilder(builder);
        }*/


        List<Recipe> mliste;


        mliste = ListRecipe.listeRecipe;
        //  ArrayAdapter<Produit> a = new ArrayAdapter<Produit>(this,android.R.layout.simple_list_item_1,mliste);
//ListView lv =(ListView) findViewById(R.id.list);
//lv.setAdapter(a);
        a = new RecipeAdapter(this, mliste);
        ListView lv = (ListView) findViewById(R.id.list_recipe);
        lv.setAdapter(a);
        LayoutAnimationController controller
                = AnimationUtils.loadLayoutAnimation(
                this, R.anim.list_layout_controller);
        lv.setLayoutAnimation(controller);
        lv.setOnItemClickListener(this);


    }
    //*****************************************************************


    public void onItemClick(AdapterView<?> l, View v, int position, long id) {
        recipe = ListRecipe.listeRecipe.get(position);
        Intent i = new Intent(this, ProduitDetail.class);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

    }


    //*********************************************************************
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                a.getFilter().filter(newText);

                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

}
