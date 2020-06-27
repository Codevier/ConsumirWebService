package com.example.consumirwebsservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.consumirwebsservice.Retrofit2.serviceRetrofit2;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class ValidacionLoginRetrofit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validacion_login_retrofit);
        Bundle bundle = this.getIntent().getExtras();
        String url="http://uealecpeterson.net/ws/";
        Gson gson= new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        serviceRetrofit2 service = retrofit.create(serviceRetrofit2.class);
        String nombr= (bundle.getString("NOMBRE"));
        String contra= (bundle.getString("CONTRASENA"));
        try {
            Call<String> call= service.getLogin(nombr,contra);
            call.enqueue(new Callback<String>() {
                             @Override
                             public void onResponse(Call<String> call, Response<String> response) {
                                 TextView txtSalud2 = (TextView)findViewById(R.id.txtSaludo);
                                 txtSalud2.setText("La respuesta del Web Service: " + response.body());
                             }
                             @Override
                             public void onFailure(Call<String> call, Throwable t) {
                                 TextView txtSaludo = (TextView)findViewById(R.id.txtSaludo);
                                 txtSaludo.setText("Error" +t.getMessage());
                             }
                         }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}