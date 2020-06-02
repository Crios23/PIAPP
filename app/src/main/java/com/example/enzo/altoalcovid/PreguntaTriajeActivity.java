package com.example.enzo.altoalcovid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
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

    RadioButton chkP1S, chkP1N,
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

        chkP1N=(RadioButton)findViewById(R.id.Rb1No);
        chkP1S=(RadioButton)findViewById(R.id.Rb1Si);
        chkP2N=(RadioButton)findViewById(R.id.Rb2No);
        chkP2S=(RadioButton)findViewById(R.id.Rb2Si);
        chkP3N=(RadioButton)findViewById(R.id.Rb3No);
        chkP3S=(RadioButton)findViewById(R.id.Rb3Si);
        chkP4N=(RadioButton)findViewById(R.id.Rb4No);
        chkP4S=(RadioButton)findViewById(R.id.Rb4Si);
        chkP5N=(RadioButton)findViewById(R.id.Rb5No);
        chkP5S=(RadioButton)findViewById(R.id.Rb5Si);
        chkP6N=(RadioButton)findViewById(R.id.Rb6No);
        chkP6S=(RadioButton)findViewById(R.id.Rb6Si);
        chkP7N=(RadioButton)findViewById(R.id.Rb7No);
        chkP7S=(RadioButton)findViewById(R.id.Rb7Si);
        btnTFinal=(Button)findViewById(R.id.btnTFinal);

        btnTFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
<<<<<<< HEAD
                if(chkP1S.isChecked()){
                    enviarRespuestas("http://192.168.1.61:80/AppCovid/Respuesta1si.php");

                }if (chkP1N.isChecked()){
                    enviarRespuestas("http://192.168.1.61:80/AppCovid/Respuesta1no.php");
=======

                /*String res1n=chkP1N.getText().toString();
                String res1s=chkP1S.getText().toString();
                String res2n=chkP2N.getText().toString();
                String res2s=chkP2S.getText().toString();
                String res3n=chkP3N.getText().toString();
                String res3s=chkP3S.getText().toString();
                String res4n=chkP4N.getText().toString();
                String res4s=chkP4S.getText().toString();
                String res5n=chkP5N.getText().toString();
                String res5s=chkP5S.getText().toString();
                String res6n=chkP6N.getText().toString();
                String res6s=chkP6S.getText().toString();
                String res7n=chkP7N.getText().toString();
                String res7s=chkP7S.getText().toString();*/



                if(chkP1S.isChecked()==true){
                    enviarRespuestas("http://192.168.1.2:8080/AppCovid/Respuesta1si.php");
                }
                if(chkP1N.isChecked()){
                    enviarRespuestas("http://192.168.1.2:8080/AppCovid/Respuesta1no.php");
>>>>>>> a944949526e6a4507a14e1797f571c2bfc1cb37e
                }
                if(chkP2S.isChecked()){
                    enviarRespuestas("http://192.168.1.61:80/AppCovid/Respuesta2si.php");
                }
                if(chkP2N.isChecked()){
                    enviarRespuestas("http://192.168.1.61:80/AppCovid/Respuesta2no.php");
                }
                if(chkP3S.isChecked()){
                    enviarRespuestas("http://192.168.1.61:80/AppCovid/Respuesta3si.php");
                }
                if(chkP3N.isChecked()){
                    enviarRespuestas("http://192.168.1.61:80/AppCovid/Respuesta3no.php");
                }
                if(chkP4S.isChecked()){
                    enviarRespuestas("http://192.168.1.61:80/AppCovid/Respuesta4si.php");
                }
                if(chkP4N.isChecked()){
                    enviarRespuestas("http://192.168.1.61:80/AppCovid/Respuesta4no.php");
                }
                if(chkP5S.isChecked()){
                    enviarRespuestas("http://192.168.1.61:80/AppCovid/Respuesta5si.php");
                }
                if(chkP5N.isChecked()){
                    enviarRespuestas("http://192.168.1.61:80/AppCovid/Respuesta5no.php");
                }
                if(chkP6S.isChecked()){
                    enviarRespuestas("http://192.168.1.61:80/AppCovid/Respuesta6si.php");
                }
                if(chkP6N.isChecked()){
<<<<<<< HEAD
                    enviarRespuestas("http://192.168.1.61:80/AppCovid/Respuesta6no.php");
                }if(chkP7S.isChecked()){
                    enviarRespuestas("http://192.168.1.61:80/AppCovid/Respuesta7si.php");
=======
                    enviarRespuestas("http://192.168.1.2:8080/AppCovid/Respuesta6no.php");
                }
                if(chkP7S.isChecked()){
                    enviarRespuestas("http://192.168.1.2:8080/AppCovid/Respuesta7si.php");
>>>>>>> a944949526e6a4507a14e1797f571c2bfc1cb37e
                }
                if(chkP7N.isChecked()){
                    enviarRespuestas("http://192.168.1.61:80/AppCovid/Respuesta7no.php");
                }


            }
        });
    }

    private void validar(){
        
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
