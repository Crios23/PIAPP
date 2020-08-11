package com.example.enzo.altoalcovid;

import android.Manifest;
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
import android.widget.EditText;
import android.widget.Spinner;
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
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DatosTiajeActivity extends AppCompatActivity {
    EditText edtNombrePC,edtApellidoPC,edtDniPC;
    Spinner spnDepaPC;

    TextView txtDireccion, txtLat,txtLon;
    Button btnguardar;

    Button btnSiguienteDPC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_tiaje);

        edtNombrePC=(EditText)findViewById(R.id.edtNombrePC);
        edtApellidoPC=(EditText)findViewById(R.id.edtApellidoPC);
        edtDniPC=(EditText)findViewById(R.id.edtDniPC);
        spnDepaPC=(Spinner)findViewById(R.id.spnDepaPC);
// ================================================ PARTE DE GPS ========================================\\
        txtDireccion=(TextView)findViewById(R.id.txtDireccion);
        txtLat=(TextView)findViewById(R.id.txtLat);
        txtLon=(TextView)findViewById(R.id.txtLon);
       // btnguardar=(Button)findViewById(R.id.btnGuardar);




        btnSiguienteDPC=(Button)findViewById(R.id.btnSiguienteDPC);

        btnSiguienteDPC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ingresarTriaje();

               // guardarGps();

            }
            });

        // ===================================== DATOS GPS ========================================\\
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
        } else {
            locationStart();
        }
      /*  btnSiguienteDPC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarGps();
            }
        });*/
        // ================ FIN GPS


    }

    public void ingresarTriaje(){
        String nombre=edtNombrePC.getText().toString();
        String apellidos=edtApellidoPC.getText().toString();
        String dni=edtDniPC.getText().toString();

        if(nombre.isEmpty()){
            Toast.makeText(getApplicationContext(), "Campo nombres es obligatorio", Toast.LENGTH_SHORT).show();
        }else if(apellidos.isEmpty()){
            Toast.makeText(getApplicationContext(), "Campo Apellidos es obligatorio", Toast.LENGTH_SHORT).show();
        }else if(dni.isEmpty()){
            Toast.makeText(this, "Campo DNI es obligatorio", Toast.LENGTH_SHORT).show();
        }else if(!dni.matches("^[0-9]{8}$")) {
            Toast.makeText(this, "Ingrese 8 dígitos", Toast.LENGTH_SHORT).show();
        }else if(spnDepaPC.getSelectedItem().equals("Seleccione")){
            Toast.makeText(this, "Seleccione un departamento", Toast.LENGTH_SHORT).show();
        } else{
            DatosPC("http://192.168.1.2:80/AppCovid/insertar_DatosPC.php");
            Intent intent = new Intent(DatosTiajeActivity.this, TriajeEmerActivity.class);
            startActivity(intent);
        }
    }


    private void DatosPC(String URL){
        StringRequest stringRequest= new StringRequest(Request.Method.POST, URL, new Response.Listener<String>(){
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
                //parametros.put("codigo",edtCodigo.getText().toString());
                parametros.put("nombre",edtNombrePC.getText().toString());
                parametros.put("apellidos",edtApellidoPC.getText().toString());
                parametros.put("dni",edtDniPC.getText().toString());
                parametros.put("departamento",spnDepaPC.getSelectedItem().toString());
                // ========== cambios para guardar la geolocalizacion
                parametros.put("direccion",txtDireccion.getText().toString());
                parametros.put("lat",txtLat.getText().toString());
                parametros.put("lon",txtLon.getText().toString());



                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
    //====================================== CODIGO PARA CAPTURAR LA GEOLOCALIZACION MEDIANTE EL GPS ======================\\

    private void locationStart() {
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        DatosTiajeActivity.Localizacion Local = new DatosTiajeActivity.Localizacion();
        Local.setDatosTiajeActivity(this);// ===============================fijarse aqui posible error
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
        DatosTiajeActivity datosTiajeActivity;
        public DatosTiajeActivity getDatosTiajeActivity() {
            return datosTiajeActivity;
        }
        public void setDatosTiajeActivity(DatosTiajeActivity datosTiajeActivity) {
            this.datosTiajeActivity = datosTiajeActivity;
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
            this.datosTiajeActivity.setLocation(loc);
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
    /*
    public void guardarGps(){
        RequestQueue queue = Volley.newRequestQueue(DatosTiajeActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Http.URL_WEB_SERVICE + "registrarGps.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject objResultado = new JSONObject(response);
                            String estadox = objResultado.get("estado").toString();
                            if (!estadox.contains("exito")) {
                                //  if(!response.equalsIgnoreCase("exito")){
                                Toast.makeText(DatosTiajeActivity.this, "Hubo un error", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(DatosTiajeActivity.this, "Coordenadas Registradas", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DatosTiajeActivity.this, "Invalid conexion", Toast.LENGTH_LONG).show();
            }
        })

                /*, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError  error) {
                Toast.makeText(MainActivity.this, "Invalid conexion", Toast.LENGTH_LONG).show();
            }
        }) {
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

*/


    //  ============================= FIN DE CAPTURA GPS

}
