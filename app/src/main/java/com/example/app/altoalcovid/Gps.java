package com.example.enzo.altoalcovid;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.Year;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Gps extends AppCompatActivity {

    TextView txtDireccion, txtLat,txtLon, txtFecha;
    Button btnguardar;// btnFecha;
    private int dia,mes,año;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

        txtDireccion=(TextView)findViewById(R.id.txtDireccion);
        txtLat=(TextView)findViewById(R.id.txtLat);
        txtLon=(TextView)findViewById(R.id.txtLon);

        btnguardar=(Button)findViewById(R.id.btnGuardar);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
        } else {
            locationStart();
        }
        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarGps();
            }
        });

        txtFecha=(EditText)findViewById(R.id.txtFecha);
      /*  txtFecha.setOnClickListener(this);
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.etPlannedDate:
                    showDatePickerDialog();
                    break;
            }
        }




    /*    //btnFecha=(Button)findViewById(R.id.btnFecha);
        txtFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (v==txtFecha ){

                   final Calendar c= Calendar.getInstance();
                   dia=c.get(Calendar.DAY_OF_MONTH);
                   mes=c.get(Calendar.MONTH);
                   año=c.get(Calendar.YEAR);

                   DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                       @Override
                       public void onDateSet(DatePicker datePicker, int a, int m, int d) {
                           txtFecha.setText(DayOfWeek+"/"+(YearMonth+1)+"/"+ Year);


                       }
                   },dia,mes,año);
                   datePickerDialog.show();
               }

            }
        });


*/

    }


    private void locationStart() {
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Localizacion Local = new Localizacion();
        Local.setGps(this);// ===============================fijarse aqui posible error
        final boolean gpsEnabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled) {
            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
            return;
        }
        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, (LocationListener) Local);
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) Local);
        txtLat.setText("Localización agregada");
        txtDireccion.setText("");
    }
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                locationStart();
                return;
            }
        }
    }
    public void setLocation(Location loc) {
        //Obtener la direccion de la calle a partir de la latitud y la longitud
        if (loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0) {
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(
                        loc.getLatitude(), loc.getLongitude(), 1);
                if (!list.isEmpty()) {
                    Address DirCalle = list.get(0);
                    txtDireccion.setText(DirCalle.getAddressLine(0));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /* Aqui empieza la Clase Localizacion */
    public class Localizacion implements LocationListener {
        Gps gps;
        public Gps getMainActivity() {
            return gps;
        }
        public void setGps(Gps gps) {
            this.gps = gps;
        }
        @Override
        public void onLocationChanged(Location loc) {
            // Este metodo se ejecuta cada vez que el GPS recibe nuevas coordenadas
            // debido a la deteccion de un cambio de ubicacion
            loc.getLatitude();
            loc.getLongitude();
            String Text1 = String.valueOf(loc.getLatitude());
            String Text2 = String.valueOf(loc.getLongitude());
            txtLat.setText(Text1);
            txtLon.setText(Text2);
            this.gps.setLocation(loc);
        }
        @Override
        public void onProviderDisabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es desactivado
            txtLat.setText("GPS Desactivado");
        }
        @Override
        public void onProviderEnabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es activado
            txtLat.setText("GPS Activado");
        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
                case LocationProvider.AVAILABLE:
                     Log.d("debug", "LocationProvider.AVAILABLE");
                    break;
                case LocationProvider.OUT_OF_SERVICE:
                    Log.d("debug", "LocationProvider.OUT_OF_SERVICE");
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    Log.d("debug", "LocationProvider.TEMPORARILY_UNAVAILABLE");
                    break;
            }
        }
    }
    public void guardarGps(){
        RequestQueue queue = Volley.newRequestQueue(Gps.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Http.URL_WEB_SERVICE + "registrarGps.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject objResultado = new JSONObject(response);
                            String estadox = objResultado.get("estado").toString();
                            if (!estadox.contains("exito")) {
                                //  if(!response.equalsIgnoreCase("exito")){
                                Toast.makeText(Gps.this, "Hubo un error", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(Gps.this, "Coordenadas Registradas", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Gps.this, "Invalid conexion", Toast.LENGTH_LONG).show();
            }
        })

                /*, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError  error) {
                Toast.makeText(MainActivity.this, "Invalid conexion", Toast.LENGTH_LONG).show();
            }
        })*/ {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("direccion", txtDireccion.getText().toString().trim());
                params.put("lat",txtLat.getText().toString().trim());
                params.put("lon",txtLon.getText().toString().trim());
                return params;
            }
        };
        queue.add(stringRequest);
    }

}
