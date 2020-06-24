package com.example.consumirwebsservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.consumirwebsservice.WebService.Asynchtask;
import com.example.consumirwebsservice.WebService.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://api-uat.kushkipagos.com/transfer-subscriptions/v1/bankList",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET","Public-Merchant-Id","86fb957359f04d9c8199c865b24cbd96");
    }
    public void Enviar(View view){
        //Creamos el Intent
        Intent intent = new Intent(MainActivity.this, ValidacionLogin.class);
        EditText txtNombre = (EditText)findViewById(R.id.txtName);
        EditText txtContraseña = (EditText)findViewById(R.id.txtPassword);
        //Creamos la información a pasar entre actividades - Pares Key-Value
        Bundle b = new Bundle();
        b.putString("NOMBRE", txtNombre.getText().toString());
        b.putString("CONTRASENA", txtContraseña.getText().toString());
        //Añadimos la información al intent
        intent.putExtras(b);
        // Iniciamos la nueva actividad
        startActivity(intent);
    }

    @Override
    public void processFinish(String result) throws JSONException {
        TextView txtSaludo = (TextView)findViewById(R.id.editTextTextMultiLine);
        //ArrayList<String> lstBancos = new ArrayList<String> ();
        String listaBancos="";
        JSONArray JSONlista =  new JSONArray(result);
        for(int i=0; i< JSONlista.length();i++) {
            JSONObject banco = JSONlista.getJSONObject(i);
            listaBancos=listaBancos+banco.getString("name").toString();
            //lstBancos.add(banco.getString("name").toString());
        }
        txtSaludo.setText(listaBancos);
    }
}