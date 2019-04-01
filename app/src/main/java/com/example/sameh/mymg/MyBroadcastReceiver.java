package com.example.sameh.mymg;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by LENOVO on 21/11/2017.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {

    //*********************************************
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Service Started", Toast.LENGTH_LONG).show();
        // new AsyncPromo().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        Intent service = new Intent(context,ServiceNotif.class);
        service.putExtra(ServiceNotif.URL,"http://10.0.2.2/test/i.php");
        context.startService(service);
        String text = intent.getStringExtra(ServiceNotif.SOURCE_URL);
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();


    }
}