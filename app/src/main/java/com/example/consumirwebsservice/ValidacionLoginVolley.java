package com.example.consumirwebsservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

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

import java.util.HashMap;
import java.util.Map;

import static com.android.volley.Request.*;

public class ValidacionLoginVolley extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validacion_login_volley);
        Bundle bundle = this.getIntent().getExtras();
        String nombr = (bundle.getString("NOMBRE"));
        String contra = (bundle.getString("CONTRASENA"));
        String url = "http://uealecpeterson.net/ws/login.php?usr=" + nombr + "&pass=" + contra;
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                TextView txtSaludo3 = (TextView) findViewById(R.id.txtRespuesta);
                txtSaludo3.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                TextView txtSaludo3 = (TextView) findViewById(R.id.txtRespuesta);
                txtSaludo3.setText("erro: " + error.toString());
            }
        });
        queue.add(stringRequest);
    }
}