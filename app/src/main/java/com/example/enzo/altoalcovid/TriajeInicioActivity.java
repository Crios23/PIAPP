package com.example.enzo.altoalcovid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class TriajeInicioActivity extends AppCompatActivity {

    Button btnIniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triaje_inicio);

        btnIniciar=(Button)findViewById(R.id.btnIniciar);

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(TriajeInicioActivity.this,DatosTiajeActivity.class);
                startActivity(intent);

            }
        });
/***
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(TriajeInicioActivity.this,TriajeEmerActivity.class);
                startActivity(intent);

            }
        });**/

    }
}
