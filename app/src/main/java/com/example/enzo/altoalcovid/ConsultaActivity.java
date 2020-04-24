package com.example.enzo.altoalcovid;

import android.content.ClipData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
    EditText edtPositivo, edtRecuperado,edtFallecido, edtCarga;
    Button btnConCasos;


    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);


        edtPositivo=(EditText)findViewById(R.id.edtPositivo);
        edtFallecido=(EditText)findViewById(R.id.edtFallecido);
        edtRecuperado=(EditText)findViewById(R.id.edtRecuperado);
        btnConCasos=(Button)findViewById(R.id.btnConCasos);
        edtCarga=(EditText)findViewById(R.id.edtCarga) ;
        spnDepa=(Spinner)findViewById(R.id.spnDepa);

        btnConCasos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(spnDepa!=null){
                    ConsultaCasos("http://192.168.1.61:80/AppCovid/casosPXDepartamento.php?departamento="+spnDepa.getSelectedItem()+"");
                    //ConsultaCasos("http://192.168.1.61:80/AppCovid/casosRXDepartamento.php?departamento="+spnDepa.getSelectedItem()+"");
                }if(spnDepa.isSelected()){
                    ConsultaCasos("http://192.168.1.61:80/AppCovid/casosPXDepartamento.php?departamento="+spnDepa.getSelectedItem()+"");
                }if(spnDepa!=null){
                    ConsultaCasos("http://192.168.1.61:80/AppCovid/casosFXDepartamento.php?departamento="+spnDepa.getSelectedItem()+"");
                }if(spnDepa!=null){
                    Toast.makeText(getApplicationContext(),"NO FUNCIONA", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    private void ConsultaCasos(String URL){
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        edtRecuperado.setText(jsonObject.getString("Positivo"));
                        edtPositivo.setText(jsonObject.getString("Positivo"));
                        edtFallecido.setText(jsonObject.getString("Fallecido"));


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
