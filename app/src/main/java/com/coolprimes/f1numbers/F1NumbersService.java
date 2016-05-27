package com.coolprimes.f1numbers;

/**
 * Created: Heimir Sverrisson on 01/05/2016.
 */
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface F1NumbersService {
    @Headers({"Cache-Control: max-age=640000", "User-Agent: F1NumbersAndroid"})
    @GET("drivers") // ?id=eq.{id}
    Call<Driver[]> getDriver(@Header("Authorization") String jwt ,@Query("id") String id);
}
