package sptest.zec.com.xbay.Interfaces;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Aleksandar on 9/29/2014.
 */
public interface LogInAPI {

    //using query when url have ? to send parametars
    @GET("/api/Account/Validate")
    public void getFeed(@Query("username") String UserName, @Query("password") String Password, Callback<Boolean> response);


}
