package com.example.enzo.altoalcovid;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MenuActivity extends AppCompatActivity {

    TextView lblR, lblP,lblN,lblF;
    RequestQueue requestQueue;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        lblP=(TextView)findViewById(R.id.lblP) ;
        lblR=(TextView)findViewById(R.id.lblR) ;
        lblN=(TextView)findViewById(R.id.lblN);
        lblF=(TextView)findViewById(R.id.lblF) ;

        // =================================== MENSAJE DE DIALOGO =====================================\\
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle("Bienvenido");
        dialogo1.setMessage("¿Acepta hacer el uso responsable de está aplicacion?");
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                //listaCasos("http://192.168.1.2:8080/AppCovid/SelectMenuR.php");
                aceptar();
            }
        });

        dialogo1.show();
        // =================================== MENSAJE DE DIALOGO =====================================\\

        ImageButton imgb = (ImageButton) findViewById(R.id.imbTriaje);
        imgb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, TriajeInicioActivity.class);
                startActivity(intent);
            }
        });

        ImageButton imgbc = (ImageButton) findViewById(R.id.imbConsulta);
        imgbc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, ConsultaActivity.class);
                startActivity(intent);

            }
        });

        ImageButton imgba = (ImageButton) findViewById(R.id.imbAlerta);
        imgba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, PruebaActivity.class);
                startActivity(intent);

            }
        });

    }

  public void aceptar() {
        if(lblP.getText().toString().isEmpty()){
      listaCasos("http://192.168.1.2:8080/AppCovid/SelectMenu.php");
        }
     if(lblP!=null){
            listaRecuperado("http://192.168.1.2:8080/AppCovid/SelectMenuR.php");
        }
      if(lblP!=null){
          listaFallecido("http://192.168.1.2:8080/AppCovid/SelectMenuF.php");
        }
          Toast t=Toast.makeText(this,"Bienvenido al Menu de Alto al Covid", Toast.LENGTH_SHORT);
          t.show();

  }

   private void listaCasos(String URL){
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        lblP.setText(jsonObject.getString("Positivo"));
                       // edtR.setText(jsonObject.getString("Recuperado"));

                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"ERROR DE CONEXION",Toast.LENGTH_SHORT).show();
            }
        }

        );
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
    private void listaRecuperado(String URL){
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        //edtP.setText(jsonObject.getString("Positivo"));
                        lblR.setText(jsonObject.getString("Recuperado"));

                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"ERROR DE CONEXION",Toast.LENGTH_SHORT).show();
            }
        }

        );
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
    private void listaFallecido(String URL){
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        //edtP.setText(jsonObject.getString("Positivo"));
                        lblF.setText(jsonObject.getString("Fallecido"));

                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"ERROR DE CONEXION",Toast.LENGTH_SHORT).show();
            }
        }

        );
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
}
