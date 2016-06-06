package com.coolprimes.f1numbers;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created: Heimir Sverrisson on 01/05/2016.
 */
public class RetrofitREST {
    private final static String LOG_TAG = "f1numbers.RetrofitREST";
    private MainActivity m;
    private String url;
    private Gson gsonPostgresDate;

    public RetrofitREST(MainActivity m, String url){
        this.m = m;
        if(url.endsWith("/")){
            this.url = url;
        } else {
            this.url = url + "/";
        }
        gsonPostgresDate = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS").create();
    }

    private Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gsonPostgresDate))
                .build();
    }

    public void getDrivers(String jwt){
        Retrofit retrofit = getRetrofit();
        F1NumbersService f1NumbersService = retrofit.create(F1NumbersService.class);
        Call<TeamDriver[]> call = f1NumbersService.getDriversAndTeams(jwt);
        call.enqueue(new Callback<TeamDriver[]>() {
            @Override
            public void onResponse(Call<TeamDriver[]> call, Response<TeamDriver[]> response) {
                TeamDriver[] teamDrivers = response.body();
                if (teamDrivers != null) {
                    m.setTeamDrivers(teamDrivers);
                } else {
                    Toast.makeText(m, "No response from server!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TeamDriver[]> call, Throwable t) {
                Log.d(LOG_TAG, "Rest call failed: " + t.getMessage());
                Toast.makeText(m, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}