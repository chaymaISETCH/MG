package com.example.sameh.mymg;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ListActP extends AppCompatActivity {

    // CONNECTION_TIMEOUT and READ_TIMEOUT are in milliseconds
    public static final int CONNECTION_TIMEOUT=20000;
    public static final int READ_TIMEOUT=30000;
    private EditText etLogin;
    private EditText etPassword;
    public static String prod;
    ListView listView;
    TextView tlibelle,tprix;
    public static ArrayList<Produit> listePro = new ArrayList<Produit>();
    String lib[];
    String prix[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.act0ivity_list);
        new AsyncLogin().execute(null,null);


        //*********************************************
    }
        /*  listView.setOnClickListener(new AdapterView.OnClickListener(){

            @Override
            public void onClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(Produits.this,"lib->"+lib[i]+"prix->"+prix[i],Toast.LENGTH_SHORT).show();


    }*/

    // Triggers when LOGIN Button clicked


    class AsyncLogin extends AsyncTask<String, String, String>
    {
        ProgressDialog pdLoading = new ProgressDialog(ListActP.this);
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

                // Enter URL address where your php file resides
                url = new URL("http://192.168.8.105/test/tt.php");

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
                conn.setRequestMethod("GET");


                // setDoInput and setDoOutput method depict handling of both send and receive
                conn.setDoInput(true);


                // Append parameters to URL



                // Open connection for sending data

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
                Toast.makeText(ListActP.this, "Something went wrong. Connection Problem."+e.getMessage(), Toast.LENGTH_LONG).show();

                return "exception";

            } finally {
                conn.disconnect();
            }


        }

        @Override
        protected void onPostExecute(String result) {
            pdLoading.dismiss();

            prod=result;
//            Toast.makeText(ListActP.this, result, Toast.LENGTH_LONG).show();


            try {



                Produit produit = new Produit();
                JSONArray data_array=new JSONArray(prod);

                for (int i = 0 ; i < data_array.length() ; i++)
                {
                    JSONObject obj=new JSONObject(data_array.get(i).toString());

                  /* produit.setLibelle(obj.getString("libelle"));
                    produit.setPrix(obj.getString("prix"));

                    tlibelle.setText(produit.getLibelle());
                    tprix.setText(produit.getPrix());*/
                    Produit p = new Produit();
                    p.setNom(obj.getString("lib"));
                    p.setPrix(obj.getString("prixp"));
                    p.setDes(obj.getString("description"));
                    p.setGarantie(obj.getString("garantie"));
                    //p.setIdCat(obj.getString("idCat"));
                    p.setIdcat(obj.getString("idcat"));
                    //p.setPrixP("2500");
                    byte[] byteArray =  Base64.decode(obj.getString("image"), Base64.DEFAULT) ;
                    Bitmap b = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                    p.setImage(b);




                    //p.setPrix(Float.parseFloat(obj.getString("prix")));
                    listePro.add(p);
                    Intent intent = new Intent(ListActP.this,ProduitsP.class);
                    startActivity(intent);

                }

            } catch (ParseException e1) {
                e1.printStackTrace();
            }

            catch (JSONException e) {
                e.printStackTrace();
            }



        }
    }

}