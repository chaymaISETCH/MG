package com.example.sameh.mymg;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;

public class ButtonAct extends AppCompatActivity {

    CircleMenu circleMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        circleMenu = (CircleMenu) findViewById(R.id.circle_menu);

        circleMenu.setMainMenu(Color.parseColor("#FFB2B3DF"), R.mipmap.icon_menu, R.mipmap.icon_cancel );
        circleMenu.addSubMenu(Color.parseColor("#258CFF"), R.drawable.catalogue)
                .addSubMenu(Color.parseColor("#30A400"), R.drawable.list)
                .addSubMenu(Color.parseColor("#FF4B32"), R.mipmap.icon_gps)
                .addSubMenu(Color.parseColor("#8A39FF"), R.drawable.recette)
                .addSubMenu(Color.parseColor("#FF6A00"),R.drawable.cart );


        circleMenu.setOnMenuSelectedListener(new OnMenuSelectedListener() {

                                                 @Override
                                                 public void onMenuSelected(int index) {
                                                     switch (index) {
                                                         case 0:
                                                             startActivity(new Intent(ButtonAct.this,Main2Activity.class));                                                             break;
                                                         case 1:
startActivity(new Intent(ButtonAct.this,ListAct.class));                                                             break;
                                                         case 2:
                                                             Toast.makeText(ButtonAct.this, "position", Toast.LENGTH_SHORT).show();
                                                             break;
                                                         case 3:
                                                             startActivity(new Intent(ButtonAct.this,ListActP.class));                                                             break;

                                                         case 4:
                                                             startActivity(new Intent(ButtonAct.this,CarteAct.class));                                                             break;

                                                     }
                                                 }
                                             }

        );

        circleMenu.setOnMenuStatusChangeListener(new OnMenuStatusChangeListener() {

            @Override
            public void onMenuOpened() {

            }

            @Override
            public void onMenuClosed() {

            }}
        );
    }

    @Override
    public void onBackPressed() {
        if (circleMenu.isOpened())
            circleMenu.closeMenu();
        else
            finish();
    }

}

