package com.example.enzo.altoalcovid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


public class InicioActivity extends AppCompatActivity {

    EditText edtNombre,edtApellido, edtDni,edtCelular,edtCorreo;
    Button btnGuardar;
    RequestQueue requestQueue;

    Spinner spn_Departamento, spn_Provincia, spn_Distrito;
/*
    ArrayList<String>arrayList_Depart;
    ArrayAdapter<String>arrayAdapter_Depart;
*/
    ArrayList<String>arrayList_Seleccione,arrayList_PAmazonas,arrayList_PAncash,arrayList_PApurimac,arrayList_PArequipa,arrayList_PAyacucho,arrayList_PCajamarca
                    ,arrayList_PCusco,arrayList_PHuancavelica,arrayList_PHuanuco,arrayList_PIca,arrayList_PJunin,arrayList_PLibertad,arrayList_Lambayeque,arrayList_PLima,arrayList_PLoreto
        ,arrayList_PMDios,arrayList_PMoquegua,arrayList_PPasco,arrayList_PPiura,arrayList_PPuno,arrayList_PSMartin,arrayList_PTacna,arrayList_PTumbes,arrayList_PUcayali;
    ArrayAdapter<String>arrayAdapter_Prov;

    ArrayList<String>arrayList_DSeleccione,arrayList_DBagua,arrayList_DChachapoyas,arrayList_DBongara,arrayList_DMaynas,arrayList_DDatem, arrayList_DPutumayo,arrayList_DLima;
    ArrayAdapter<String>arrayAdapter_Dist;




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
        // datos adicionales ============================

        spn_Departamento=(Spinner)findViewById(R.id.spn_Departamento) ;
        spn_Provincia=(Spinner)findViewById(R.id.spn_Provincia) ;
        spn_Distrito=(Spinner)findViewById(R.id.spn_Distrito);

        // =========== proceso de combo Provincias
        arrayList_Seleccione=new ArrayList<>();
        arrayList_Seleccione.add("Seleccione");
        arrayList_PAmazonas=new ArrayList<>();
        arrayList_PAmazonas.add("Seleccione");

        arrayList_PAmazonas.add("Chachapoyas");
                arrayList_DChachapoyas=new ArrayList<>();arrayList_DChachapoyas.add("Seleccione");arrayList_DChachapoyas.add("Chachapoyas");arrayList_DChachapoyas.add("Asuncion");
                arrayList_DChachapoyas.add("Balsas");arrayList_DChachapoyas.add("Cheto");

        arrayList_PAmazonas.add("Bagua");
                arrayList_DBagua=new ArrayList<>();arrayList_DBagua.add("Seleccione");arrayList_DBagua.add("Peaca");arrayList_DBagua.add("Aramango");
                arrayList_DBagua.add("Copallin");arrayList_DBagua.add("Imaza");

        arrayList_PAmazonas.add("Bongara");
                arrayList_DBongara=new ArrayList<>();arrayList_DBongara.add("Seleccione");arrayList_DBongara.add("Jumbilla");arrayList_DBongara.add("Chisquilla");
                arrayList_DBongara.add("Churuja");arrayList_DBongara.add("Corosha");

        arrayList_PAmazonas.add("Utcubamba");
        arrayList_PAmazonas.add("Condorcanqui");
        arrayList_PAmazonas.add("Luya");
        arrayList_PAmazonas.add("Rodriguez De Mendo ");

        arrayList_PAncash=new ArrayList<>();
        arrayList_PAncash.add("Seleccione");
        arrayList_PAncash.add("Huaraz");
        arrayList_PAncash.add("Aija");
        arrayList_PAncash.add("Antonio Raimonti");
        arrayList_PAncash.add("Asuncion");
        arrayList_PAncash.add("Bolognesi");
        arrayList_PAncash.add("Carhuaz");

        arrayList_PApurimac=new ArrayList<>();
        arrayList_PApurimac.add("Seleccione");
        arrayList_PApurimac.add("Abancay");
        arrayList_PApurimac.add("Andahuaylas");
        arrayList_PApurimac.add("Antabamba");
        arrayList_PApurimac.add("Cotabambas");

        arrayList_PArequipa=new ArrayList<>();
        arrayList_PArequipa.add("Seleccione");
        arrayList_PArequipa.add("Arequipa");
        arrayList_PArequipa.add("Camana");
        arrayList_PArequipa.add("Caraveli");
        arrayList_PArequipa.add("Castilla");

        arrayList_PAyacucho=new ArrayList<>();
        arrayList_PAyacucho.add("Seleccione");
        arrayList_PAyacucho.add("Huamanga");
        arrayList_PAyacucho.add("Cangallo");
        arrayList_PAyacucho.add("Huanca Sancos");
        arrayList_PAyacucho.add("Huanta");

        arrayList_PCajamarca=new ArrayList<>();
        arrayList_PCajamarca.add("Seleccione");
        arrayList_PCajamarca.add("Cajamarca");
        arrayList_PCajamarca.add("Cajabamba");
        arrayList_PCajamarca.add("Celendin");
        arrayList_PCajamarca.add("Hualgayoc");
        arrayList_PCajamarca.add("Jaen");
        arrayList_PCajamarca.add("Chota");
        arrayList_PCajamarca.add("San Ignacio");


        arrayList_PCusco=new ArrayList<>();
        arrayList_PCusco.add("Seleccione");
        arrayList_PCusco.add("Cusco");
        arrayList_PCusco.add("Acomayo");
        arrayList_PCusco.add("Anta");
        arrayList_PCusco.add("Calca");
        arrayList_PCusco.add("Canas");
        arrayList_PCusco.add("Canchis");
        arrayList_PCusco.add("Chumbivilcas");
        arrayList_PCusco.add("Espinar");

        arrayList_PHuancavelica=new ArrayList<>();
        arrayList_PHuancavelica.add("Seleccione");
        arrayList_PHuancavelica.add("Hancavelica");
        arrayList_PHuancavelica.add("Acobamba");
        arrayList_PHuancavelica.add("Angaraes");
        arrayList_PHuancavelica.add("Huaytara");

        arrayList_PHuanuco=new ArrayList<>();
        arrayList_PHuanuco.add("Seleccione");
        arrayList_PHuanuco.add("Huanuco");
        arrayList_PHuanuco.add("Ambo");
        arrayList_PHuanuco.add("Dos de Mayo");
        arrayList_PHuanuco.add("Huamalies");

        arrayList_PIca=new ArrayList<>();
        arrayList_PIca.add("Seleccione");
        arrayList_PIca.add("Ica");
        arrayList_PIca.add("Chincha");
        arrayList_PIca.add("Nazca");
        arrayList_PIca.add("Palpa");
        arrayList_PIca.add("Pisco");

        arrayList_PJunin=new ArrayList<>();
        arrayList_PJunin.add("Seleccione");
        arrayList_PJunin.add("Huancayo");
        arrayList_PJunin.add("Concepcion");
        arrayList_PJunin.add("Chanchamayo");
        arrayList_PJunin.add("Junin");
        arrayList_PJunin.add("Satipo");
        arrayList_PJunin.add("Jauja");
        arrayList_PJunin.add("Tarma");

        arrayList_PLibertad=new ArrayList<>();
        arrayList_PLibertad.add("Seleccione");
        arrayList_PLibertad.add("Trujillo");
        arrayList_PLibertad.add("Ascope");
        arrayList_PLibertad.add("Pataz");
        arrayList_PLibertad.add("Bolivar");
        arrayList_PLibertad.add("Chepen");

        arrayList_Lambayeque=new ArrayList<>();
        arrayList_Lambayeque.add("Seleccione");
        arrayList_Lambayeque.add("Lambayeque");
        arrayList_Lambayeque.add("Chiclayo");
        arrayList_Lambayeque.add("Ferreñafe");

        arrayList_PLima=new ArrayList<>();
        arrayList_PLima.add("Seleccione");
        arrayList_PLima.add("Lima");

        arrayList_DLima=new ArrayList<>();
        arrayList_DLima.add("Seleccione");
        arrayList_DLima.add("Lima");arrayList_DLima.add("Ancon");arrayList_DLima.add("Ate");arrayList_DLima.add("Barranco");arrayList_DLima.add("Breña");arrayList_DLima.add("Carabayllo");
        arrayList_DLima.add("Chaclacayo");arrayList_DLima.add("Chorrillos");arrayList_DLima.add("Cieneguilla");arrayList_DLima.add("Comas");arrayList_DLima.add("El Agustino");
        arrayList_DLima.add("Independencia");arrayList_DLima.add("Jesus Maria");arrayList_DLima.add("La Molina");arrayList_DLima.add("La Victoria");arrayList_DLima.add("Lince");
        arrayList_DLima.add("Los Olivos");arrayList_DLima.add("Lurigancho");arrayList_DLima.add("Lurin");arrayList_DLima.add("Magdalena Del Mar");arrayList_DLima.add("Magdalena Vieja");
        arrayList_DLima.add("Miraflores");arrayList_DLima.add("Pachacamac");arrayList_DLima.add("Pucusana");arrayList_DLima.add("Puente Piedra");arrayList_DLima.add("Punta Hermosa" );
        arrayList_DLima.add("Punta Negra");arrayList_DLima.add("Rimac");arrayList_DLima.add("San Bartolo");arrayList_DLima.add("San Borja");arrayList_DLima.add("San Isidro");arrayList_DLima.add("San Juan De Lurigancho");
        arrayList_DLima.add("San Juan De Miraflores");arrayList_DLima.add("San Luis");arrayList_DLima.add("San Martin De Porres");arrayList_DLima.add("San Miguel");arrayList_DLima.add("Santa Anita");
        arrayList_DLima.add("Santa Maria Del Mar");arrayList_DLima.add("Santa Rosa" );arrayList_DLima.add("Santiago De Surco");arrayList_DLima.add("Surquillo");arrayList_DLima.add("Villa El Salvador");arrayList_DLima.add("Villa Maria Del Triunfo");


        arrayList_PLima.add("Barranca");
        arrayList_PLima.add("Cajatambo");
        arrayList_PLima.add("Canta");
        arrayList_PLima.add("Cañete");
        arrayList_PLima.add("Huaral");
// ********************************* Loreto ****************************************
        arrayList_PLoreto=new ArrayList<>();
        arrayList_PLoreto.add("Seleccione");
        arrayList_PLoreto.add("Maynas");
        arrayList_DMaynas=new ArrayList<>();arrayList_DMaynas.add("Seleccione");arrayList_DMaynas.add("Iquitos");arrayList_DMaynas.add("Napo");arrayList_DMaynas.add("Belen");
        arrayList_PLoreto.add("Datem");
        arrayList_DDatem=new ArrayList<>();arrayList_DDatem.add("Seleccione");arrayList_DDatem.add("Andoas");arrayList_DDatem.add("Barranca");arrayList_DDatem.add("Cahuapanas");
        arrayList_PLoreto.add("Putumayo");
        arrayList_DPutumayo=new ArrayList<>();arrayList_DPutumayo.add("Seleccione");arrayList_DPutumayo.add("Putumayo");arrayList_DPutumayo.add("Rosa Panduro");arrayList_DPutumayo.add("Tnt. Manuel Clavero");
// *************************************
        arrayList_PMDios=new ArrayList<>();
        arrayList_PMDios.add("");

        arrayList_PMoquegua=new ArrayList<>();
        arrayList_PMoquegua.add("");

        arrayList_PPasco=new ArrayList<>();
        arrayList_PPasco.add("");

        arrayList_PPiura=new ArrayList<>();
        arrayList_PPiura.add("");

        arrayList_PPuno=new ArrayList<>();
        arrayList_PPuno.add("");

        arrayList_PSMartin=new ArrayList<>();
        arrayList_PSMartin.add("");

        arrayList_PTacna=new ArrayList<>();
        arrayList_PTacna.add("");

        arrayList_PTumbes=new ArrayList<>();
        arrayList_PTumbes.add("");

        arrayList_PUcayali=new ArrayList<>();
        arrayList_PUcayali.add("");


        spn_Departamento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicion, long l) {
                if (posicion==0){
                    arrayAdapter_Prov=new ArrayAdapter<>(getApplicationContext(),R.layout.textview_black,arrayList_Seleccione);
                }if (posicion==1){
                    arrayAdapter_Prov=new ArrayAdapter<>(getApplicationContext(),R.layout.textview_black,arrayList_PAmazonas);
                }if (posicion==2){
                    arrayAdapter_Prov=new ArrayAdapter<>(getApplicationContext(),R.layout.textview_black,arrayList_PAncash);
                }if (posicion==3){
                    arrayAdapter_Prov=new ArrayAdapter<>(getApplicationContext(),R.layout.textview_black,arrayList_PApurimac);
                }if (posicion==4){
                    arrayAdapter_Prov=new ArrayAdapter<>(getApplicationContext(),R.layout.textview_black,arrayList_PArequipa);
                }if (posicion==5){
                    arrayAdapter_Prov=new ArrayAdapter<>(getApplicationContext(),R.layout.textview_black,arrayList_PAyacucho);
                }if (posicion==6){
                    arrayAdapter_Prov=new ArrayAdapter<>(getApplicationContext(),R.layout.textview_black,arrayList_PCajamarca);
                }if (posicion==7){
                    arrayAdapter_Prov=new ArrayAdapter<>(getApplicationContext(),R.layout.textview_black,arrayList_PCusco);
                }if (posicion==8){
                    arrayAdapter_Prov=new ArrayAdapter<>(getApplicationContext(),R.layout.textview_black,arrayList_PHuancavelica);
                }if (posicion==9){
                    arrayAdapter_Prov=new ArrayAdapter<>(getApplicationContext(),R.layout.textview_black,arrayList_PHuanuco);
                }if (posicion==10){
                    arrayAdapter_Prov=new ArrayAdapter<>(getApplicationContext(),R.layout.textview_black,arrayList_PIca);
                }if (posicion==11){
                    arrayAdapter_Prov=new ArrayAdapter<>(getApplicationContext(),R.layout.textview_black,arrayList_PJunin);
                }if (posicion==12){
                    arrayAdapter_Prov=new ArrayAdapter<>(getApplicationContext(),R.layout.textview_black,arrayList_PLibertad);
                }if (posicion==13){
                    arrayAdapter_Prov=new ArrayAdapter<>(getApplicationContext(),R.layout.textview_black,arrayList_Lambayeque);
                }if (posicion==14){
                    arrayAdapter_Prov=new ArrayAdapter<>(getApplicationContext(),R.layout.textview_black,arrayList_PLima);
                }if (posicion==15){
                    arrayAdapter_Prov=new ArrayAdapter<>(getApplicationContext(),R.layout.textview_black,arrayList_PLoreto);
                }if (posicion==16){
                    arrayAdapter_Prov=new ArrayAdapter<>(getApplicationContext(),R.layout.textview_black,arrayList_PMDios);
                }if (posicion==17){
                    arrayAdapter_Prov=new ArrayAdapter<>(getApplicationContext(),R.layout.textview_black,arrayList_PMoquegua);
                }
                if (posicion==18){
                    arrayAdapter_Prov=new ArrayAdapter<>(getApplicationContext(),R.layout.textview_black,arrayList_PPasco);
                }if (posicion==19){
                    arrayAdapter_Prov=new ArrayAdapter<>(getApplicationContext(),R.layout.textview_black,arrayList_PPiura);
                }if (posicion==20){
                    arrayAdapter_Prov=new ArrayAdapter<>(getApplicationContext(),R.layout.textview_black,arrayList_PPuno);
                }if (posicion==21){
                    arrayAdapter_Prov=new ArrayAdapter<>(getApplicationContext(),R.layout.textview_black,arrayList_PSMartin);
                }if (posicion==22){
                    arrayAdapter_Prov=new ArrayAdapter<>(getApplicationContext(),R.layout.textview_black,arrayList_PTacna);
                }if (posicion==23){
                    arrayAdapter_Prov=new ArrayAdapter<>(getApplicationContext(),R.layout.textview_black,arrayList_PTumbes);
                }if (posicion==24){
                    arrayAdapter_Prov=new ArrayAdapter<>(getApplicationContext(),R.layout.textview_black,arrayList_PUcayali);
                }
                spn_Provincia.setAdapter(arrayAdapter_Prov);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
            spn_Provincia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int posicion, long l) {
                    if (spn_Provincia.getSelectedItem().equals("Seleccione")) {
                        arrayAdapter_Dist = new ArrayAdapter<>(getApplicationContext(), R.layout.textview_black, arrayList_Seleccione);
                    }if (spn_Provincia.getSelectedItem().equals("Chachapoyas")) {
                        arrayAdapter_Dist = new ArrayAdapter<>(getApplicationContext(), R.layout.textview_black, arrayList_DChachapoyas);
                    }
                    if (spn_Provincia.getSelectedItem().equals("Bagua")) {
                        arrayAdapter_Dist = new ArrayAdapter<>(getApplicationContext(), R.layout.textview_black, arrayList_DBagua);
                    }if (spn_Provincia.getSelectedItem().equals("Bongara")) {
                        arrayAdapter_Dist = new ArrayAdapter<>(getApplicationContext(), R.layout.textview_black, arrayList_DBongara);
                    }if (spn_Provincia.getSelectedItem().equals("Lima")) {
                        arrayAdapter_Dist = new ArrayAdapter<>(getApplicationContext(), R.layout.textview_black, arrayList_DLima);
                    }
                    if (spn_Provincia.getSelectedItem().equals("Maynas")) {
                        arrayAdapter_Dist = new ArrayAdapter<>(getApplicationContext(), R.layout.textview_black,arrayList_DMaynas);
                    }if (spn_Provincia.getSelectedItem().equals("Datem")) {
                        arrayAdapter_Dist = new ArrayAdapter<>(getApplicationContext(), R.layout.textview_black,arrayList_DDatem);
                    }if (spn_Provincia.getSelectedItem().equals("Putumayo")) {
                    arrayAdapter_Dist = new ArrayAdapter<>(getApplicationContext(), R.layout.textview_black,arrayList_DPutumayo);
                }
                    spn_Distrito.setAdapter(arrayAdapter_Dist);

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

        //===============================
       btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validaciones();
            }
        });

    }
    private boolean isValidEmailId(String email){
        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

    private void validaciones(){
                String nombres=edtNombre.getText().toString();
                String apellidos=edtApellido.getText().toString();
                String dni=edtDni.getText().toString();
                String celular=edtCelular.getText().toString();
                String correo=edtCorreo.getText().toString();

                if(nombres.isEmpty()){
                    Toast.makeText(this, "Campo Nombres es obligatorio", Toast.LENGTH_SHORT).show();
                }else if(apellidos.isEmpty()){
                    Toast.makeText(this, "Campo Apellidos es obligatorio", Toast.LENGTH_SHORT).show();
                }else if(dni.isEmpty()){
                    Toast.makeText(this, "Campo DNI es obligatorio", Toast.LENGTH_SHORT).show();
                }else if(!dni.matches("^[0-9]{8}$")) {
                    Toast.makeText(this, "Ingrese 8 dígitos", Toast.LENGTH_SHORT).show();
                }else if(celular.isEmpty()){
                    Toast.makeText(this, "Campo celular es obligatorio" , Toast.LENGTH_SHORT).show();
                }else if(!celular.matches("^[0-9]{9,15}$")){
                    Toast.makeText(this, "Ingrese Mínimo 9 digitos y Máximo 15 dígitos" , Toast.LENGTH_SHORT).show();
                }else if(correo.isEmpty() ){
                    Toast.makeText(this, "Campo correo es obligatorio", Toast.LENGTH_SHORT).show();
                }else if(!isValidEmailId(correo)){
                    Toast.makeText(this, "Email Invalido", Toast.LENGTH_SHORT).show();
                }else if(spn_Departamento.getSelectedItem().equals("Seleccione")){
                    Toast.makeText(this, "Seleccione un departamento", Toast.LENGTH_SHORT).show();
                }
                else if(spn_Provincia.getSelectedItem().equals("Seleccione")){
                    Toast.makeText(this, "Seleccione una provincia", Toast.LENGTH_SHORT).show();
                }
                else if(spn_Distrito.getSelectedItem().equals("Seleccione")){
                    Toast.makeText(this, "Seleccione un distrito", Toast.LENGTH_SHORT).show();
                }
                else{
                    guardarRegistro("http://192.168.1.2:80/AppCovid/insertar_usuario.php");
                    Intent intent = new Intent(InicioActivity.this, MenuActivity.class);
                    startActivity(intent);
                }
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

                //======================= otros==================================================

                parametros.put("departamento", String.valueOf(spn_Departamento.getSelectedItemPosition()));
                parametros.put("provincia", String.valueOf(spn_Provincia.getSelectedItemPosition()));
                parametros.put("distrito", String.valueOf(spn_Distrito.getSelectedItemPosition()));

                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
}
