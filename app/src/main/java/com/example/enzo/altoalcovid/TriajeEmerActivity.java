package com.example.enzo.altoalcovid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class TriajeEmerActivity extends AppCompatActivity {

    CheckBox chkNo;
    Button btnSiguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triaje_emer);

        chkNo=(CheckBox)findViewById(R.id.chkNo);
        btnSiguiente=(Button)findViewById(R.id.btnSiguiente);

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(chkNo.isChecked()){ EnviarEmergencia("http://192.168.1.61:80/AppCovid/TriajeEmergencia1.php");}
               // AppCovid/TriajeEmergencia.php
                else{
                   EnviarEmergencia("http://192.168.1.61:80/AppCovid/TriajeEmergencia2.php");
               }

            }
        });
    }

    private void EnviarEmergencia(String URL){
        StringRequest stringRequest= new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "OPERACION EXITOSA", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>parametros= new HashMap<String,String>();
                //parametros.put("codigo",edtCodigo.getText().toString());
                //if(chkNo.isChecked()==true){
                    parametros.put("emercia",chkNo.isChecked()?"SI":"NO");



                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
