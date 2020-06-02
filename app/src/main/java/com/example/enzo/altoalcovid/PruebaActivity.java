package com.example.enzo.altoalcovid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PruebaActivity extends AppCompatActivity {

    EditText edtCodigo, edtNombreP,edtCantidad,edtColor;
    Button btnBuscar;

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba);

        edtCodigo=(EditText)findViewById(R.id.edtCodigo);
        edtNombreP=(EditText)findViewById(R.id.edtNombreP);
        edtCantidad=(EditText)findViewById(R.id.edtCantidad);
        edtColor=(EditText)findViewById(R.id.edtColor);
        btnBuscar=(Button)findViewById(R.id.btnBuscar) ;

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarProducto("http://192.168.1.61:80/AppCovid/buscarXDepartamento.php?codigo="+edtCodigo.getText()+"");
                }
        });

    }

    private void buscarProducto(String URL){

        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        edtNombreP.setText(jsonObject.getString("nombre"));
                        edtCantidad.setText(jsonObject.getString("cantidad"));
                        edtColor.setText(jsonObject.getString("color"));

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
        requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
}
