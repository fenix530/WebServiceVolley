package com.example.diegoorozco.webservicevolley;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RequestQueue requestQueue = Volley.newRequestQueue(this); // creando cola de peticiones


        final String url = "https://dl.dropboxusercontent.com/s/u859m74wwhhzv72/logo-universidad-de-antioquia.png?dl=0"; // url para descargar contenido

        final Button bDescargar=(Button) findViewById(R.id.bDescarga);
        final ImageView Imagen=(ImageView) findViewById(R.id.imagen);
        final TextView Text= (TextView) findViewById(R.id.textdes);
        final Button bText=(Button) findViewById(R.id.bText);


        bDescargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() { //funcion que permite crear la peticion para una imagen
                    @Override
                    public void onResponse(Bitmap response) {
                        Imagen.setImageBitmap(response); // tratamiento de la respuesta obtenida
                    }
                }, 0, 0, ImageView.ScaleType.CENTER_CROP, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Conxion Fallida","Error Respuesta en JSON: " + error.getMessage()); // que se hace si hay error
                    }
                }
                );
                imageRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));//politica de reintentos
                requestQueue.add(imageRequest); //mandando peticion a la calo para que se ejecutada
            }
        });
        final String url1="https://dl.dropboxusercontent.com/s/mfm3uu51ptqi2fr/TextoEjeplo.txt?dl=0";


        bText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest request= new StringRequest(url1, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Text.setText(response); // tratamiento de la respuesta obtenida
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Conxion Fallida","Error Respuesta en JSON: " + error.getMessage()); // que se hace si hay error
                    }
                });
                request.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                requestQueue.add(request);
            }

        });
    }
    }

