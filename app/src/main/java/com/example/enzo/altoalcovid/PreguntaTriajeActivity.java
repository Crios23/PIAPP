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

public class PreguntaTriajeActivity extends AppCompatActivity {

    CheckBox chkP1S, chkP1N,
            chkP2S, chkP2N,
            chkP3S, chkP3N,
            chkP4S, chkP4N,
            chkP5S, chkP5N,
            chkP6S, chkP6N,
            chkP7S, chkP7N;
    Button btnTFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta_triaje);

        chkP1N=(CheckBox)findViewById(R.id.chkP1N);
        chkP1S=(CheckBox)findViewById(R.id.chkP1S);
        chkP2N=(CheckBox)findViewById(R.id.chkP2N);
        chkP2S=(CheckBox)findViewById(R.id.chkP2S);
        chkP3N=(CheckBox)findViewById(R.id.chkP3N);
        chkP3S=(CheckBox)findViewById(R.id.chkP3S);
        chkP4N=(CheckBox)findViewById(R.id.chkP4N);
        chkP4S=(CheckBox)findViewById(R.id.chkP4S);
        chkP5N=(CheckBox)findViewById(R.id.chkP5N);
        chkP5S=(CheckBox)findViewById(R.id.chkP5S);
        chkP6N=(CheckBox)findViewById(R.id.chkP6N);
        chkP6S=(CheckBox)findViewById(R.id.chkP6S);
        chkP7N=(CheckBox)findViewById(R.id.chkP7N);
        chkP7S=(CheckBox)findViewById(R.id.chkP7S);
        btnTFinal=(Button)findViewById(R.id.btnTFinal);

        btnTFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chkP1S.isChecked()){
                    enviarRespuestas("http://192.168.1.2:8080/AppCovid/Respuesta1si.php");

                }if (chkP1N.isChecked()){
                    enviarRespuestas("http://192.168.1.2:8080/AppCovid/Respuesta1no.php");
                }
                if(chkP2S.isChecked()){
                    enviarRespuestas("http://192.168.1.2:8080/AppCovid/Respuesta2si.php");
                }
                if(chkP2N.isChecked()){
                    enviarRespuestas("http://192.168.1.2:8080/AppCovid/Respuesta2no.php");
                }
                if(chkP3S.isChecked()){
                    enviarRespuestas("http://192.168.1.2:8080/AppCovid/Respuesta3si.php");
                }
                if(chkP3N.isChecked()){
                    enviarRespuestas("http://192.168.1.2:8080/AppCovid/Respuesta3no.php");
                }
                if(chkP4S.isChecked()){
                    enviarRespuestas("http://192.168.1.2:8080/AppCovid/Respuesta4si.php");
                }
                if(chkP4N.isChecked()){
                    enviarRespuestas("http://192.168.1.2:8080/AppCovid/Respuesta4no.php");
                }
                if(chkP5S.isChecked()){
                    enviarRespuestas("http://192.168.1.2:8080/AppCovid/Respuesta5si.php");
                }
                if(chkP5N.isChecked()){
                    enviarRespuestas("http://192.168.1.2:8080/AppCovid/Respuesta5no.php");
                }
                if(chkP6S.isChecked()){
                    enviarRespuestas("http://192.168.1.2:8080/AppCovid/Respuesta6si.php");
                }
                if(chkP6N.isChecked()){
                    enviarRespuestas("http://192.168.1.2:8080/AppCovid/Respuesta6no.php");
                }if(chkP7S.isChecked()){
                    enviarRespuestas("http://192.168.1.2:8080/AppCovid/Respuesta7si.php");
                }
                if(chkP7N.isChecked()){
                    enviarRespuestas("http://192.168.1.2:8080/AppCovid/Respuesta7no.php");
                }

            }
        });
    }

    private void enviarRespuestas (String URL){
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

                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
