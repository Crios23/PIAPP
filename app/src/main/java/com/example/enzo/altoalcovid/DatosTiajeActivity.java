package com.example.enzo.altoalcovid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public class DatosTiajeActivity extends AppCompatActivity {
    EditText edtNombrePC,edtApellidoPC,edtDniPC;
    Spinner spnDepaPC;
    Button btnSiguienteDPC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_tiaje);


        edtNombrePC=(EditText)findViewById(R.id.edtNombrePC);
        edtApellidoPC=(EditText)findViewById(R.id.edtApellidoPC);
        edtDniPC=(EditText)findViewById(R.id.edtDniPC);
        spnDepaPC=(Spinner)findViewById(R.id.spnDepaPC);
        btnSiguienteDPC=(Button)findViewById(R.id.btnSiguienteDPC);

        btnSiguienteDPC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnSiguienteDPC.isClickable()){
                DatosPC("http://192.168.1.61:80/AppCovid/insertar_DatosPC.php");
                    //Toast.makeText(getApplicationContext(),"UD.ENVIO SUS DATOS",Toast.LENGTH_SHORT).show();
                }Intent intent = new Intent(DatosTiajeActivity.this, TriajeEmerActivity.class);
                startActivity(intent);//if(btnSiquiente)

            }
        });

    }

    private void DatosPC(String URL){
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
                parametros.put("nombre",edtNombrePC.getText().toString());
                parametros.put("apellidos",edtApellidoPC.getText().toString());
                parametros.put("dni",edtDniPC.getText().toString());
                parametros.put("departamento",spnDepaPC.getSelectedItem().toString());



                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
