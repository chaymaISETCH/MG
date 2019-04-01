package com.example.sameh.mymg;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gigamole.library.PulseView;

public class Splash extends AppCompatActivity {
    PulseView pulseView;
    Thread splashTread;


    public class MyReceiver extends BroadcastReceiver {
        public static final String ACTION_RESP = "com.myapp.intent.action.TEXT_TO_DISPLAY";

        @Override
        public void onReceive(Context context, Intent intent) {
            String text = intent.getStringExtra(ServiceNotif.SOURCE_URL);
            // send text to display



            //Toast.makeText(MainActivity.this, "receive", Toast.LENGTH_LONG).show();
//***************************************************


        }
    }

    private MyReceiver receiver;










    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



        Intent msgIntent = new Intent(this, ServiceNotif.class);
        msgIntent.putExtra(ServiceNotif.URL, "http://10.0.2.2/test/i.php");
        startService(msgIntent);




        pulseView = (PulseView) findViewById(R.id.pv);
        StartAnimations();
    }

    private void StartAnimations() {
        pulseView.startPulse();
        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 3500) {
                        sleep(100);
                        waited += 100;
                    }
                    Intent intent = new Intent(Splash.this,
                            Main1.class);
                    startActivity(intent);
                    Splash.this.finish();
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    Splash.this.finish();
                }

            }
        };
        splashTread.start();


    }
}