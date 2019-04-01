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
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.nightonke.boommenu.BoomButtons.BoomButton;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;

import java.util.ArrayList;
import java.util.List;

public class Produits extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView list;
    List<Produit> mliste;

    ProduitAdapter a;
    public static Produit produit = new Produit();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produits);






        List<Produit> mliste;


        mliste = ListAct.listePro;
        //  ArrayAdapter<Produit> a = new ArrayAdapter<Produit>(this,android.R.layout.simple_list_item_1,mliste);
//ListView lv =(ListView) findViewById(R.id.list);
//lv.setAdapter(a);
        a = new ProduitAdapter(this, mliste);
        ListView lv = (ListView) findViewById(R.id.list);
        lv.setAdapter(a);
        LayoutAnimationController controller
                = AnimationUtils.loadLayoutAnimation(
                this, R.anim.list_layout_controller);
        lv.setLayoutAnimation(controller);
        lv.setOnItemClickListener(this);

        //***********************************************************************



     Button   ht = (Button) findViewById(R.id.ht);
        ht.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Produit> l = new ArrayList<Produit>();

                for(int i=0;i<ListAct.listePro.size();i++)
                {
                    if(ListAct.listePro.get(i).getIdcat().equals("2"))
                        l.add(ListAct.listePro.get(i));
                }

                a = new ProduitAdapter(Produits.this, l);

                ListView lv = (ListView) findViewById(R.id.list);

                lv.setAdapter(a);

            }
        });

       Button alim = (Button) findViewById(R.id.alim);
        alim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Produit> l = new ArrayList<Produit>();

                for(int i=0;i<ListAct.listePro.size();i++)
                {
                    if(ListAct.listePro.get(i).getIdcat().equals("1"))
                        l.add(ListAct.listePro.get(i));
                }

                a = new ProduitAdapter(Produits.this, l);

                ListView lv = (ListView) findViewById(R.id.list);

                lv.setAdapter(a);

            }
        });


 Button   em = (Button) findViewById(R.id.em);
        em.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      List<Produit> l = new ArrayList<Produit>();

                                      for (int i = 0; i < ListAct.listePro.size(); i++) {
                                          if (ListAct.listePro.get(i).getIdcat().equals("3"))
                                              l.add(ListAct.listePro.get(i));
                                      }

                                      a = new ProduitAdapter(Produits.this, l);

                                      ListView lv = (ListView) findViewById(R.id.list);

                                      lv.setAdapter(a);

                                  }
                              });

       Button cos = (Button) findViewById(R.id.cos);
        cos.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        List<Produit> l = new ArrayList<Produit>();

        for(int i=0;i<ListAct.listePro.size();i++)
        {
        if(ListAct.listePro.get(i).getIdcat().equals("4"))
        l.add(ListAct.listePro.get(i));
        }

        a = new ProduitAdapter(Produits.this, l);

        ListView lv = (ListView) findViewById(R.id.list);

        lv.setAdapter(a);

        }
        });

        Button me = (Button) findViewById(R.id.me);
        me.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        List<Produit> l = new ArrayList<Produit>();

        for(int i=0;i<ListAct.listePro.size();i++)
        {
        if(ListAct.listePro.get(i).getIdcat().equals("5"))
        l.add(ListAct.listePro.get(i));
        }

        a = new ProduitAdapter(Produits.this, l);

        ListView lv = (ListView) findViewById(R.id.list);

        lv.setAdapter(a);

        }
        });


       Button net = (Button) findViewById(R.id.net);
        net.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        List<Produit> l = new ArrayList<Produit>();

        for(int i=0;i<ListAct.listePro.size();i++)
        {
        if(ListAct.listePro.get(i).getIdcat().equals("6"))
        l.add(ListAct.listePro.get(i));
        }

        a = new ProduitAdapter(Produits.this, l);

        ListView lv = (ListView) findViewById(R.id.list);

        lv.setAdapter(a);

        }
        });












        }








    //*****************************************************************


    public void onItemClick(AdapterView<?> l, View v, int position, long id) {
        produit = ListAct.listePro.get(position);
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
