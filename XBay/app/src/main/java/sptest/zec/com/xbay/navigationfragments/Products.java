package sptest.zec.com.xbay.navigationfragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import sptest.zec.com.xbay.Adapters.ProductsAdapter;
import sptest.zec.com.xbay.Interfaces.CategoryViewAPI;
import sptest.zec.com.xbay.Models.CategoryViewModel;
import sptest.zec.com.xbay.Models.SubCategoryModel;
import sptest.zec.com.xbay.R;

/**
 * Created by Aleksandar on 9/19/2014.
 */
public class Products extends Fragment {

    @InjectView(R.id.categories_list)
    ListView mList;
    List<CategoryViewModel> categoryViewModels;

    private static final String ENDPOINT = "http://webmedia.mk/api-x-bay";
    private String i;
    private SubCategoryModel subCategory;
    private CategoryViewModel categoryView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.categories, container, false);
        ButterKnife.inject(this, mView);

        getActivity().setTitle(R.string.category_view);

        //get data from subcategory fragment
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            i = bundle.getString("json");
            subCategory = new Gson().fromJson(i, SubCategoryModel.class);
            System.out.println(subCategory.getSubCategoryId());
        }

        if (isOnline()) {
            requestData("http://webmedia.mk/api-x-bay/api/Product/Products");

        } else {
            Toast.makeText(getActivity(), "Network isn't available", Toast.LENGTH_LONG).show();
        }


        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //get the clicked item
                CategoryViewModel clickedItem = (CategoryViewModel) parent.getAdapter().getItem(position);
                String json = new Gson().toJson(clickedItem, CategoryViewModel.class);

                //send data to fragment and navigate to fragment
                Bundle data = new Bundle();
                data.putString("json", json);

                Fragment fragment = new ProductDescription();
                fragment.setArguments(data);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack("").commit();
            }
        });

        return mView;
    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    private void requestData(String uri) {

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .build();

        CategoryViewAPI api = adapter.create(CategoryViewAPI.class);

        api.getFeed(new Callback<List<CategoryViewModel>>() {
            @Override
            public void success(List<CategoryViewModel> categoryViewModel, Response response) {

                List<CategoryViewModel> catView = new ArrayList<CategoryViewModel>();

                //iterate through product list
                for (int x = 0; x < categoryViewModel.size(); x++) {

                    categoryView = categoryViewModel.get(x);

                     //when the clicked subcategory id equals the iterated subcategory id, get  product list
                    if (subCategory.getSubCategoryId() == categoryView.getSubCategoryId()) {
                        catView.add(categoryView);
                    }
                }
                categoryViewModels = catView;
                mList.setAdapter(new ProductsAdapter(getActivity(), R.layout.category_view, categoryViewModels));
                System.out.println("SUCCESS");
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println("FAIL");

            }
        });
    }
}

