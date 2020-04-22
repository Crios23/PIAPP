package com.example.enzo.altoalcovid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class InicioActivity extends AppCompatActivity {

    EditText edtNombre,edtApellido, edtDni,edtCelular,edtCorreo;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        edtNombre=(EditText)findViewById(R.id.edtNombre);
        edtApellido=(EditText)findViewById(R.id.edtApellido);
        edtDni=(EditText)findViewById(R.id.edtDni);
        edtCelular=(EditText)findViewById(R.id.edtCelular);
        edtCorreo=(EditText)findViewById(R.id.edtCorreo);
        btnGuardar=(Button)findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtCorreo==null){
                    Toast.makeText(getApplicationContext(), "DEBE REGISTRARSE", Toast.LENGTH_SHORT).show();
                    //guardarRegistro("http://192.168.1.61:80/AppCovid/insertar_usuario.php");
                }if(edtCorreo!=null) {
                    guardarRegistro("http://192.168.1.61:80/AppCovid/insertar_usuario.php");
                    //Intent intent = new Intent(MainActivity2.this, CentroActivity.class);
                    // startActivity(intent);

                }if(edtCorreo!=null){
                    Intent intent = new Intent(InicioActivity.this, MenuActivity.class);
                    startActivity(intent);
                }


            }
        });


    }


    private void guardarRegistro(String URL){

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
                parametros.put("nombres",edtNombre.getText().toString());
                parametros.put("apellidos",edtApellido.getText().toString());
                parametros.put("dni",edtDni.getText().toString());
                parametros.put("celular",edtCelular.getText().toString());
                parametros.put("correo",edtCorreo.getText().toString());


                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        //************************************

    }
}
