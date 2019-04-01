package com.example.sameh.mymg;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by LENOVO on 21/11/2017.
 */

public class Receiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            //  Intent serviceIntent = new Intent("com.myapp.NotifyService");
            // context.startService(serviceIntent);
            Toast.makeText(context, "receiver Started", Toast.LENGTH_LONG).show();

        }

        Toast.makeText(context, "receiver Started out if ", Toast.LENGTH_LONG).show();


    }}