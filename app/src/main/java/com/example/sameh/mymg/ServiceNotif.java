package com.example.sameh.mymg;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class ServiceNotif extends Service {
    public  static final String res="rien";
    public static final String URL = "urlpath";
    public static final String SOURCE_URL = "destination_source";
    public static String s;





    @Override
    public void onCreate() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "service doing", Toast.LENGTH_SHORT).show();
//**************************************************************************

        String urlPath = intent.getStringExtra(URL);
        InputStream is = null;
        BufferedReader r = null;
        StringBuffer result = new StringBuffer();
        new AsyncLogin().execute("", "");
       // Toast.makeText(this,"result : " +res, Toast.LENGTH_SHORT).show();
       /* if(AsyncLogin.res.equals("true")) {
            createNotif(AsyncLogin.res);
        }
*/
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(Splash.MyReceiver.ACTION_RESP);
        broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
        broadcastIntent.putExtra(SOURCE_URL, "hiiii");
        sendBroadcast(broadcastIntent);
        scheduleNextUpdate();


//*******************************************************************************
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // We don't provide binding, so return null
        return null;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
    }


    private void scheduleNextUpdate() {
        Intent intent = new Intent(this, this.getClass());
        PendingIntent pendingIntent =
                PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, 10 * 1000, pendingIntent);
      // new AsyncLogin().execute("","");

    }
    //**************************************************************************


    public void createNotif(String s){
     /*   NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.promo)
                        .setContentTitle("Promotion")
                        .setContentText("une nouvelle promotion vous attend! venez la découvrir");

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1, builder.build());
*/


        NotificationManager mNotificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, ListActP.class), 0);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.promo)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText("une nouvelle promotion vous attend! venez la découvrir"))
                        .setContentText("une nouvelle promotion vous attend! venez la découvrir")
                        .setDefaults(Notification.DEFAULT_ALL).setContentTitle("Promotion");


        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(1, mBuilder.build());




    //*****************************************************************************
















    }




//********************************
class AsyncLogin extends AsyncTask<String, String, String>
{
    public String res="";
    public  final int CONNECTION_TIMEOUT=10000;
    public  final int READ_TIMEOUT=15000;
    java.net.URL url = null;
    HttpURLConnection conn;


    @Override
    protected void onPreExecute() {
        super.onPreExecute();


    }
    @Override
    protected String doInBackground(String... params) {
        try {
             String FAVORITE_COLOR = "fav color";

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ServiceNotif.this);
            SharedPreferences.Editor editor = preferences.edit();

            String couleur = preferences.getString(FAVORITE_COLOR, "");
            String FAVORITE_COLORR = "fav colorr";

             preferences = PreferenceManager.getDefaultSharedPreferences(ServiceNotif.this);
            editor = preferences.edit();

            String couleurr = preferences.getString(FAVORITE_COLORR, "");
            // Enter URL address where your php file resides
            url = new URL("http://192.168.8.105/test/ttt.php?date="+couleur+"%20"+couleurr);

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "exception";
        }
        try {
            // Setup HttpURLConnection class to send and receive data from php and mysql
            conn = (HttpURLConnection)url.openConnection();

            conn.setReadTimeout(READ_TIMEOUT);
            conn.setConnectTimeout(CONNECTION_TIMEOUT);
            conn.setRequestMethod("POST");

            // setDoInput and setDoOutput method depict handling of both send and receive
            conn.setDoInput(true);
            conn.setDoOutput(true);

            // Append parameters to URL
            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("login", params[0])
                    .appendQueryParameter("password", params[1]);
            String query = builder.build().getEncodedQuery();

            // Open connection for sending data
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(query);
            writer.flush();
            writer.close();
            os.close();
            conn.connect();

        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return "exception";
        }

        try {

            int response_code = conn.getResponseCode();

            // Check if successful connection made
            if (response_code == HttpURLConnection.HTTP_OK) {

                // Read data sent from server
                InputStream input = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                StringBuilder result = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                // Pass data to onPostExecute method
                res=result.toString();
                return(result.toString());

            }else{

                return("unsuccessful");
            }

        } catch (IOException e) {
            e.printStackTrace();

            return "exception";

        } finally {
            conn.disconnect();
        }


    }

    @Override
    protected void onPostExecute(String result) {

        Toast.makeText(ServiceNotif.this,result, Toast.LENGTH_SHORT).show();


        if(result.equalsIgnoreCase("true"))

        {


          //  Calendar cal = Calendar.getInstance();
            //cal.add(Calendar.DATE, 1);

            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat format2 = new SimpleDateFormat("HH:mm:ss");

            // System.out.println(cal.getTime());
// Output "Wed Sep 26 14:23:28 EST 2012"

            // String formatted = format1.format(cal.getTime());





            Date currentTime = Calendar.getInstance().getTime();
            String formatted = format1.format(currentTime.getTime());
            String formattedd = format2.format(currentTime.getTime());

           Toast.makeText(ServiceNotif.this,formatted+formattedd, Toast.LENGTH_SHORT).show();

            //***************************************************************************
            String FAVORITE_COLOR = "fav color";
            String FAVORITE_COLORR = "fav colorr";

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ServiceNotif.this);
            SharedPreferences.Editor editor = preferences.edit();

            editor.putString(FAVORITE_COLOR,formatted);
            editor.putString(FAVORITE_COLORR,formattedd);

            editor.commit();

            createNotif("");

        }
    }

}}