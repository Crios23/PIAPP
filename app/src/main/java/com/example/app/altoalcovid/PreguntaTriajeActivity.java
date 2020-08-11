package com.example.enzo.altoalcovid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

    RadioGroup rg1,rg2,rg3,rg4,rg5,rg6,rg7;


    RadioButton Rb1Si, Rb1No,rbg,
            Rb2No, Rb2Si,
            Rb3No, Rb3Si,
            Rb4No, Rb4Si,
            Rb5No, Rb5Si,
            Rb6No, Rb6Si,
            Rb7No, Rb7Si;
    Button btnTFinal;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta_triaje);
        rg1=(RadioGroup)findViewById(R.id.Rg1);
        rg2=(RadioGroup)findViewById(R.id.Rg2);
        rg3=(RadioGroup)findViewById(R.id.Rg3);
        rg4=(RadioGroup)findViewById(R.id.Rg4);
        rg5=(RadioGroup)findViewById(R.id.Rg5);
        rg6=(RadioGroup)findViewById(R.id.Rg6);
        rg7=(RadioGroup)findViewById(R.id.Rg7);


        Rb1No=(RadioButton)findViewById(R.id.Rb1No);
        Rb1Si=(RadioButton)findViewById(R.id.Rb1Si);
        Rb2No=(RadioButton)findViewById(R.id.Rb2No);
        Rb2Si=(RadioButton)findViewById(R.id.Rb2Si);
        Rb3No=(RadioButton)findViewById(R.id.Rb3No);
        Rb3Si=(RadioButton)findViewById(R.id.Rb3Si);
        Rb4No=(RadioButton)findViewById(R.id.Rb4No);
        Rb4Si=(RadioButton)findViewById(R.id.Rb4Si);
        Rb5No=(RadioButton)findViewById(R.id.Rb5No);
        Rb5Si=(RadioButton)findViewById(R.id.Rb5Si);
        Rb6No=(RadioButton)findViewById(R.id.Rb6No);
        Rb6Si=(RadioButton)findViewById(R.id.Rb6Si);
        Rb7No=(RadioButton)findViewById(R.id.Rb7No);
        Rb7Si=(RadioButton)findViewById(R.id.Rb7Si);
        btnTFinal=(Button)findViewById(R.id.btnTFinal);



        btnTFinal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(Rb1No.isChecked()){
                    enviarRespuestas("http://192.168.1.2:80/AppCovid/Respuesta1no.php");
                }if(Rb1Si.isChecked()){
                    enviarRespuestas("http://192.168.1.2:80/AppCovid/Respuesta1si.php");
                }if(Rb2Si.isChecked()){
                    enviarRespuestas("http://192.168.1.2:80/AppCovid/Respuesta2si.php");
                }
                if(Rb2No.isChecked()){
                    enviarRespuestas("http://192.168.1.2:80/AppCovid/Respuesta2no.php");
                }if(Rb3Si.isChecked()){
                    enviarRespuestas("http://192.168.1.2:80/AppCovid/Respuesta3si.php");
                }
                if(Rb3No.isChecked()){
                    enviarRespuestas("http://192.168.1.2:80/AppCovid/Respuesta3no.php");
                }if(Rb4Si.isChecked()){
                    enviarRespuestas("http://192.168.1.2:80/AppCovid/Respuesta4si.php");
                }
                if(Rb4No.isChecked()){
                    enviarRespuestas("http://192.168.1.2:80/AppCovid/Respuesta4no.php");
                }if(Rb5Si.isChecked()){
                    enviarRespuestas("http://192.168.1.2:80/AppCovid/Respuesta5si.php");
                }if(Rb5No.isChecked()){
                    enviarRespuestas("http://192.168.1.2:80/AppCovid/Respuesta5no.php");
                }if(Rb6Si.isChecked()){
                    enviarRespuestas("http://192.168.1.2:80/AppCovid/Respuesta6si.php");
                }if(Rb6No.isChecked()){
                    enviarRespuestas("http://192.168.1.2:80/AppCovid/Respuesta6no.php");
                }if(Rb7Si.isChecked()){
                    enviarRespuestas("http://192.168.1.2:80/AppCovid/Respuesta7si.php");
                }if(Rb7No.isChecked()){
                    enviarRespuestas("http://192.168.1.2:80/AppCovid/Respuesta7no.php");
                }


            }
        });
}

    /*public void insertarTriaje(){



        int tf=rg1.getCheckedRadioButtonId();
        View Rbg=(RadioGroup)findViewById(tf);

        int indice=rg1.indexOfChild(Rbg);

        if(indice==0){
            Toast.makeText(this,"Hay campos vacios",Toast.LENGTH_SHORT).show();
        }/*else if(rg2.getCheckedRadioButtonId()==-1){
            Toast.makeText(this,"Hay campos vacios",Toast.LENGTH_SHORT).show();
        }else if(rg3.getCheckedRadioButtonId()==-1){
            Toast.makeText(this,"Hay campos vacios",Toast.LENGTH_SHORT).show();
        }else if(rg4.getCheckedRadioButtonId()==-1){
            Toast.makeText(this,"Hay campos vacios",Toast.LENGTH_SHORT).show();
        }else if(rg5.getCheckedRadioButtonId()==-1){
            Toast.makeText(this,"Hay campos vacios",Toast.LENGTH_SHORT).show();
        }else if(rg6.getCheckedRadioButtonId()==-1){
            Toast.makeText(this,"Hay campos vacios",Toast.LENGTH_SHORT).show();
        }else if(rg7.getCheckedRadioButtonId()==-1){
            Toast.makeText(this,"Hay campos vacios",Toast.LENGTH_SHORT).show();
        }*/
       //else{
            //enviarRespuestas("http://192.168.1.2:80/AppCovid/RespuestaTriajes.php");
      // }
    //}

    private void enviarRespuestas (String URL){
        StringRequest stringRequest= new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "OPERACION EXITOSA", Toast.LENGTH_SHORT).show();
            }}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>parametros= new HashMap<String,String>();

                parametros.put("antecedentes",rbg.getText().toString());

                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
