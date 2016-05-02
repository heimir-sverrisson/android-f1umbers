package com.coolprimes.f1numbers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by Heimir Sverrisson on 01/05/2016.
 */
public interface F1NumbersService {
    public static final String BASE_URL = "http://192.168.1.10:3000/";
    public Gson gsonPostgresDate = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS").create();

    @Headers({"Cache-Control: max-age=640000", "User-Agent: F1NumbersAndroid"})
    @GET("drivers") // ?id=eq.{id}
    Call<Driver[]> getDriver(@Header("Authorization") String jwt ,@Query("id") String id);

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gsonPostgresDate))
            .build();

}
