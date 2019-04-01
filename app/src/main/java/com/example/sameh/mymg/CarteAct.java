package com.example.sameh.mymg;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import android.app.AlertDialog;
import android.app.ProgressDialog;

import android.content.DialogInterface;
import android.content.Intent;

import android.content.SharedPreferences;

import android.net.Uri;

import android.os.AsyncTask;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;

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

public class CarteAct extends AppCompatActivity {
    String s="000";


    public static final int CONNECTION_TIMEOUT=10000;

    public static final int READ_TIMEOUT=15000;
    int points = 0;
    EditText t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carte);
        t = (EditText) findViewById(R.id.t1);
        s = t.getText().toString();
        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                if(s.length() == 0) {
                    new AsyncLogin().execute(t.getText().toString(),t.getText().toString());


                }


                else
                    Toast.makeText(CarteAct.this,"verifier votre numero de carte",Toast.LENGTH_LONG).show();




            }
        });


    }

//************************************************************************



    private class AsyncLogin extends AsyncTask<String, String, String>

    {

        ProgressDialog pdLoading = new ProgressDialog(CarteAct.this);

        HttpURLConnection conn;

        URL url = null;



        @Override

        protected void onPreExecute() {

            super.onPreExecute();



            //this method will be running on UI thread

            pdLoading.setMessage("\tLoading...");

            pdLoading.setCancelable(false);

            pdLoading.show();



        }

        @Override

        protected String doInBackground(String... params) {

            try {




                url = new URL("http://192.168.8.105/test/carte.php?carte="+params[0]);


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

                    return(result.toString());



                }else{



                    return("unsuccessful");

                }



            } catch (IOException e) {

                e.printStackTrace();

                Toast.makeText(CarteAct.this, "Something went wrong. Connection Problem."+e.getMessage(), Toast.LENGTH_LONG).show();



                return "exception";



            } finally {

                conn.disconnect();

            }





        }



        @Override

        protected void onPostExecute(String result) {







            pdLoading.dismiss();



if(result.equals("false"))
    Toast.makeText(CarteAct.this,"verifier votre numero de carte",Toast.LENGTH_LONG).show();
else {
    final AlertDialog.Builder dlgAlert = new AlertDialog.Builder(CarteAct.this);
    dlgAlert.setPositiveButton("Ok",
            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            });
    dlgAlert.setMessage("Vous avez " + result + " points");
    dlgAlert.setTitle("Carte fidélité");
    dlgAlert.setPositiveButton("OK", null);
    dlgAlert.setCancelable(true);
    dlgAlert.create().show();
}


            if (result.equalsIgnoreCase("exception") || result.equalsIgnoreCase("unsuccessful")) {



                Toast.makeText(CarteAct.this, "Something went wrong. Connection Problem.", Toast.LENGTH_LONG).show();



            }

        }



    }

}

