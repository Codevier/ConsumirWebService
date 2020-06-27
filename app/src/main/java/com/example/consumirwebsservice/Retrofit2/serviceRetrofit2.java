package com.example.consumirwebsservice.Retrofit2;

import com.example.consumirwebsservice.ValidacionLogin;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface serviceRetrofit2 {
    //@GET("usersFake")
    //Call<List<BancoClass>> getUser();
    @GET("bankList")
    Call<List<BancoClass>> getListCall(@Header("Public-Merchant-Id") String idUser);

    @FormUrlEncoded
    @GET("login.php")
    public void logearse(@Field("usr") String idUser, @Field("pass") String passw);

    @GET("login.php")
    Call<String> getLogin(@Query("usr") String idUser, @Query("pass") String passw);

    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseBody> getLogin2(@Field("usr") String idUser, @Field("pass") String passw);
}
