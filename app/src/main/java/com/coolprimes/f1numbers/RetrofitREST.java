package com.coolprimes.f1numbers;

import android.util.Log;
import android.widget.TextView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Header;

/**
 * Created by Heimir Sverrisson on 01/05/2016.
 */
public class RetrofitREST {
    private final static String LOG_TAG = "f1numbers.RetrofitREST";
    private TextView v;

    public RetrofitREST(TextView v){
        this.v = v;
        v.setText("Waiting for server response...");
    }


    public void getDriver(String jwt, int id){
        F1NumbersService f1NumbersService = F1NumbersService.retrofit.create(F1NumbersService.class);
        Call<Driver[]> call = f1NumbersService.getDriver(jwt, String.format(Locale.US, "eq.%d", id));
        call.enqueue(new Callback<Driver[]>() {
            @Override
            public void onResponse(Call<Driver[]> call, Response<Driver[]> response) {
                Driver[] drivers = response.body();
                Driver d = drivers[0];
                final String fullName = d.getFirstName() + " " + d.getLastName();
                final DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
                v.setText(fullName + ", born: " + df.format(d.getDateOfBirth()));
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

