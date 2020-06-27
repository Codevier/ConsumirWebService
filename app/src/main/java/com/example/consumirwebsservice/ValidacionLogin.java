package com.example.consumirwebsservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.consumirwebsservice.WebService.Asynchtask;
import com.example.consumirwebsservice.WebService.WebService;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class ValidacionLogin extends AppCompatActivity implements Asynchtask
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validacion_login);
        Bundle bundle = this.getIntent().getExtras();
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("http://uealecpeterson.net/ws/login.php?usr="
                + bundle.getString("NOMBRE") + "&pass=" + bundle.getString("CONTRASENA"),
                datos, ValidacionLogin.this, ValidacionLogin.this);
        ws.execute("GET");
    }
    @Override
    public void processFinish(String result) throws JSONException {
        TextView txtSaludo = (TextView)findViewById(R.id.txtSaludo);
        txtSaludo.setText("La respuesta del Web Service:" + result);
    }
}