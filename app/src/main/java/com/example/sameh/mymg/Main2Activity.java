package com.example.sameh.mymg;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ParseException;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import android.content.Intent;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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


public class Main2Activity extends AppCompatActivity implements OnClickListener {
    public static Produit produit;
    public static String scanContent;
    private ImageView scanBtn;
    private TextView formatTxt, contentTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        scanBtn = (ImageView)findViewById(R.id.scan_button);
        formatTxt = (TextView)findViewById(R.id.scan_format);
        contentTxt = (TextView)findViewById(R.id.scan_content);
        scanBtn.setOnClickListener(this);
    }
    public void onClick(View v){
        if(v.getId()==R.id.scan_button){
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
             scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            formatTxt.setText("FORMAT: " + scanFormat);
            contentTxt.setText("CONTENT: " + scanContent);
          new AsyncLogin().execute(scanContent,"");

        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    //**********************************************************************

    private class AsyncLogin extends AsyncTask<String, String, String>
    {
        ProgressDialog pdLoading = new ProgressDialog(Main2Activity.this);
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
            //    url = new URL("http://192.168.8.105/test/carte1.php?code="+scanContent);
                url = new URL("http://192.168.8.105/test/carte1.php?code="+scanContent);
               // http://localhost/mgWeb/web/app_dev.php/products/1234567890128/by/bar/code

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Toast.makeText(Main2Activity.this, e.getMessage(), Toast.LENGTH_LONG).show();

                return "exception";
            }
            try {
                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection)url.openConnection();
                conn.setReadTimeout(6000);
                conn.setConnectTimeout(6000);
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
                Toast.makeText(Main2Activity.this, e1.getMessage(), Toast.LENGTH_LONG).show();

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

                    return("unsuccessful"+response_code);
                }

            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(Main2Activity.this, "Something went wrong. Connection Problem."+e.getMessage(), Toast.LENGTH_LONG).show();

                return "exception";

            } finally {
                conn.disconnect();
            }


        }

        @Override
        protected void onPostExecute(String result) {



            pdLoading.dismiss();

//            Toast.makeText(Main2Activity.this, result, Toast.LENGTH_LONG).show();
            try {





                JSONObject obj=new JSONObject(result.toString());

                  /* produit.setLibelle(obj.getString("libelle"));
                    produit.setPrix(obj.getString("prix"));

                    tlibelle.setText(produit.getLibelle());
                    tprix.setText(produit.getPrix());*/
                Produit p = new Produit();
                p.setNom(obj.getString("lib"));
                p.setPrix(obj.getString("prix"));
                p.setDes(obj.getString("description"));
                p.setGarantie(obj.getString("garantie"));

                byte[] byteArray =  Base64.decode(obj.getString("image"), Base64.DEFAULT) ;
                Bitmap b = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                p.setImage(b);

                produit=p;


                //p.setPrix(Float.parseFloat(obj.getString("prix")));





            } catch (ParseException e1) {
                e1.printStackTrace();
                Toast.makeText(Main2Activity.this, "noooo"+e1.getMessage(), Toast.LENGTH_LONG).show();

            }

            catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(Main2Activity.this, "noooo"+e.getMessage(), Toast.LENGTH_LONG).show();

            }
            Intent intent = new Intent(Main2Activity.this,ProduitDetail2.class);
            startActivity(intent);
        }

    }







}