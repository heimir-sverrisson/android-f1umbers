package com.coolprimes.f1numbers;

import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
    private TextView v;
    private String url;
    private Gson gsonPostgresDate;

    public RetrofitREST(TextView v, String url){
        this.v = v;
        this.url = url;
        gsonPostgresDate = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS").create();
        v.setText(R.string.waiting_for_server_response);
    }

    private Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gsonPostgresDate))
                .build();
    }

    public void getDriver(String jwt, int id){
        Retrofit retrofit = getRetrofit();
        F1NumbersService f1NumbersService = retrofit.create(F1NumbersService.class);
        Call<Driver[]> call = f1NumbersService.getDriver(jwt, String.format(Locale.US, "eq.%d", id));
        call.enqueue(new Callback<Driver[]>() {
            @Override
            public void onResponse(Call<Driver[]> call, Response<Driver[]> response) {
                Driver[] drivers = response.body();
                if (drivers != null) {
                    Driver d = drivers[0];
                    final String fullName = d.getFirstName() + " " + d.getLastName();
                    final DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
                    v.setText(fullName + ", born: " + df.format(d.getDateOfBirth()));
                } else {
                    v.setText(R.string.no_response);
                }
            }

            @Override
            public void onFailure(Call<Driver[]> call, Throwable t) {
                Date now = new Date();
                Log.d(LOG_TAG, "Rest call failed: " + t.getMessage());
                v.setText(t.getMessage());
            }
        });
    }
}