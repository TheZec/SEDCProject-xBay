package sptest.zec.com.xbay.Interfaces;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import sptest.zec.com.xbay.Models.SubCategoryModel;

/**
 * Created by Aleksandar on 10/16/2014.
 */
public interface SubCategoryAPI {

    @GET("/api/SubCategory/SubCategory")
    public void getFeed(Callback<List<SubCategoryModel>> response);
}
