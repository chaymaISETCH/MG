package com.example.sameh.mymg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProduitDetail extends AppCompatActivity {
Produit p = Produits.produit;
    TextView t1,t2,t3,t4;
    ImageView i1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produit_detail);

t1=(TextView) findViewById(R.id.lib);
        t2=(TextView) findViewById(R.id.prix);
        t3=(TextView) findViewById(R.id.des);
        t4=(TextView) findViewById(R.id.gar);
        i1=(ImageView) findViewById(R.id.img);

        t1.setText(p.getNom());
        t2.setText(p.getPrix()+"DT");
        t3.setText("Garantie   "+p.getGarantie()+" ans");
        t4.setText("Description  "+p.getDes());
        i1.setImageBitmap(p.getImage());



        Button bt1=(Button) findViewById(R.id.bt1);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent=new Intent(Intent.ACTION_SEND);
                // shareIntent=new Intent(Intent.ACTION_MEDIA_SHARED);
                // shareIntent.putExtra(Intent.EXTRA_STREAM,true);
                shareIntent.setType("text/plain");
                String sharebody="le Produit: "+p.getNom()+"  : "+p.getDes()+" à "+p.getPrix()+" ";
                //p.getClass();
                // img.setImageBitmap(produit.getImage());
                // String sharebody1=p.getPrix();
                shareIntent.putExtra(Intent.EXTRA_SUBJECT,sharebody);
                shareIntent.putExtra(Intent.EXTRA_TEXT,sharebody);
                startActivity(Intent.createChooser(shareIntent,"share via"));
            }
        });





    }
}
