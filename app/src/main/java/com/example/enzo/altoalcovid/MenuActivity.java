package com.example.enzo.altoalcovid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ImageButton imgb= (ImageButton)findViewById(R.id.imbTriaje);
        imgb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MenuActivity.this,TriajeInicioActivity.class);
                startActivity(intent);
            }
        });

        ImageButton imgbc=(ImageButton)findViewById(R.id.imbConsulta);
        imgbc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MenuActivity.this,ConsultaActivity.class);
                startActivity(intent);

            }
        });

        ImageButton imgba=(ImageButton)findViewById(R.id.imbAlerta);
        imgba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MenuActivity.this,PruebaActivity.class);
                startActivity(intent);

            }
        });

    }
}
