package com.example.consumirwebsservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}