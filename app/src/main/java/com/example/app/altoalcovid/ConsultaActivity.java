package com.example.enzo.altoalcovid;

import android.content.ClipData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public class ConsultaActivity extends AppCompatActivity {
    Spinner spnDepa;
    TextView lblReC,lblCaPC,lblFaC;
    Button btnConCasos;


    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        lblReC=(TextView)findViewById(R.id.lblReC) ;
        lblCaPC=(TextView)findViewById(R.id.lblCaPC);
        lblFaC=(TextView)findViewById(R.id.lblFaC);
        btnConCasos=(Button)findViewById(R.id.btnConCasos);
        spnDepa=(Spinner)findViewById(R.id.spnDepa);

        btnConCasos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnConCasos.isClickable()){
                    ConsultaCasosP("http://192.168.1.2:80/AppCovid/casosPXDepartamento.php?departamento="+spnDepa.getSelectedItem()+"");
                }if (btnConCasos.isClickable()){
                    ConsultaCasosR("http://192.168.1.2:80/AppCovid/casosRXDepartamento.php?departamento="+spnDepa.getSelectedItem()+"");
                }if(btnConCasos.isClickable()){
                    ConsultaCasosF("http://192.168.1.2:80/AppCovid/casosFXDepartamento.php?departamento="+spnDepa.getSelectedItem()+"");
                }if(btnConCasos.isClickable()) {
                    Toast.makeText(getApplicationContext(), "Resultados actualizados el dia de hoy", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    private void ConsultaCasosP(String URL){
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        lblCaPC.setText(jsonObject.getString("Positivo"));

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
    private void ConsultaCasosR(String URL){
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        lblReC.setText(jsonObject.getString("Recuperado"));

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
    private void ConsultaCasosF(String URL){
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        lblFaC.setText(jsonObject.getString("Fallecido"));

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
