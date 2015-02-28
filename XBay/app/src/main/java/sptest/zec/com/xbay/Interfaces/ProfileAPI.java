package sptest.zec.com.xbay.Interfaces;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import sptest.zec.com.xbay.Models.ProfileModel;

/**
 * Created by Aleksandar on 9/29/2014.
 */
public interface ProfileAPI {

    //using query when url have ? to send parametars
    @GET("/api/Profile/GetUserProfile")
    public void getFeed(@Query("username") String UserName, @Query("password") String Password, Callback<ProfileModel> response);


}
