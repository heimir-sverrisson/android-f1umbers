package com.coolprimes.f1numbers;

/**
 * Created: Heimir Sverrisson on 01/05/2016.
 */
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface F1NumbersService {
    @Headers({"Cache-Control: max-age=640001", "User-Agent: F1NumbersAndroid"})
    @GET("vw_drivers_and_teams")
    Call<TeamDriver[]> getDriversAndTeams(@Header("Authorization") String jwt);
    // Call<TeamDriver[]> getDriver(@Header("Authorization") String jwt ,@Query("id") String id);
}
