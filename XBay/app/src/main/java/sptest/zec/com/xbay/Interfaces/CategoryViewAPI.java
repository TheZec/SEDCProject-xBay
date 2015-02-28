package sptest.zec.com.xbay.Interfaces;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import sptest.zec.com.xbay.Models.CategoryViewModel;

/**
 * Created by Aleksandar on 10/21/2014.
 */
public interface CategoryViewAPI {

    @GET("/api/Product/Products")
    public void getFeed(Callback<List<CategoryViewModel>> response);
}
