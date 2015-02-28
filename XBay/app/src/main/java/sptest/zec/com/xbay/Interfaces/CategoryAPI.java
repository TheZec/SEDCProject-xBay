package sptest.zec.com.xbay.Interfaces;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import sptest.zec.com.xbay.Models.CategoryModel;

/**
 * Created by Aleksandar on 9/29/2014.
 */
public interface CategoryAPI {

    @GET("/api/Category/Category")
    public void getFeed(Callback<List<CategoryModel>> response);
}
