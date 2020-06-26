package com.example.consumirwebsservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.consumirwebsservice.Retrofit2.BancoClass;
import com.example.consumirwebsservice.Retrofit2.serviceRetrofit2;
import com.example.consumirwebsservice.WebService.Asynchtask;
import com.example.consumirwebsservice.WebService.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://api-uat.kushkipagos.com/transfer-subscriptions/v1/bankList",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET","Public-Merchant-Id","86fb957359f04d9c8199c865b24cbd96");
        String url="https://api-uat.kushkipagos.com/transfer-subscriptions/v1/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        serviceRetrofit2 service = retrofit.create(serviceRetrofit2.class);
        Call<List<BancoClass>> call= service.getListCall("86fb957359f04d9c8199c865b24cbd96");
        call.enqueue(new Callback<List<BancoClass>>(){
            @Override
            public void onResponse(Call<List<BancoClass>> call, Response<List<BancoClass>> response){
                TextView txtSaludo2 = (TextView)findViewById(R.id.editTextTextMultiLine2);
                String listaBancos2="";
                for (BancoClass banco: response.body()){
                    listaBancos2=listaBancos2+banco.getName().toString();
                }
                txtSaludo2.setText(listaBancos2);


            }

            @Override
            public void onFailure(Call<List<BancoClass>> call, Throwable t){
                Log.e("error",t.toString());
            }
        });
        //new PeticionConRetrifit().execute();

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
    /*public static class PeticionConRetrifit extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            String url="https://api-uat.kushkipagos.com/transfer-subscriptions/v1/";
            //String url="https://androidtutorials.herokuapp.com/";
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            serviceRetrofit2 service= retrofit.create(serviceRetrofit2.class);
            //Call<List<BancoClass>> response = service.getUser();
            Call<List<BancoClass>> response = service.getListCall("86fb957359f04d9c8199c865b24cbd96");
            String listaBancos2="";
            try{
                for(BancoClass banco : response.execute().body()) {
                    listaBancos2=listaBancos2+banco.getName().toString();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
*/
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