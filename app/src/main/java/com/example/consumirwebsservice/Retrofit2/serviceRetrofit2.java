package com.example.consumirwebsservice.Retrofit2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface serviceRetrofit2 {
    //@GET("usersFake")
    //Call<List<BancoClass>> getUser();
    @GET("bankList")
    Call<List<BancoClass>> getListCall(@Header("Public-Merchant-Id") String idUser);

    @GET("findUser")
    Call<BancoClass> getFind(@Query("id") String idUser);
}
