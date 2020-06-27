package com.example.consumirwebsservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.consumirwebsservice.Retrofit2.serviceRetrofit2;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import retrofit2.http.Body;

public class ValidacionLoginRetrofit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validacion_login_retrofit);
        //Recuperamos la información pasada en el intent
        Bundle bundle = this.getIntent().getExtras();
        //Construimos el mensaje a mostrartx
        String url="http://uealecpeterson.net/ws/";
        Gson gson= new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
                //.addConverterFactory(GsonConverterFactory.create(RxJava2CallAdapterFactory.create()))
        serviceRetrofit2 service = retrofit.create(serviceRetrofit2.class);
        //String json = retrofitService().getInfo().execute().body().string();
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
        //Call<ValidacionLogin> call =service.getLogin2(nombr,contra);

    }
}