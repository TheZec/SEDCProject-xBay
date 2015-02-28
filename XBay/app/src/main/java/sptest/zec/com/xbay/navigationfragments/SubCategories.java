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
import sptest.zec.com.xbay.Adapters.SubCategoriesAdapter;
import sptest.zec.com.xbay.Interfaces.SubCategoryAPI;
import sptest.zec.com.xbay.Models.CategoryModel;
import sptest.zec.com.xbay.Models.SubCategoryModel;
import sptest.zec.com.xbay.R;

/**
 * Created by Aleksandar on 9/19/2014.
 */
public class SubCategories extends Fragment {


    @InjectView(R.id.categories_list)
    ListView mList;

    //link no longer available
    private static final String ENDPOINT = "http://webmedia.mk/api-x-bay";
    List<SubCategoryModel> subCategoryModel;
    private String i;
    private CategoryModel categoryModel;
    private SubCategoryModel subCategory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.categories, container, false);
        ButterKnife.inject(this, mView);

        getActivity().setTitle(R.string.sub_categories);

        //get data from category fragment
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            i = bundle.getString("json");
            categoryModel = new Gson().fromJson(i, CategoryModel.class);
            System.out.println(categoryModel.CategoryId);
        }

        if (isOnline()) {
            requestData("http://webmedia.mk/api-x-bay/api/SubCategory/SubCategory");

        } else {
            Toast.makeText(getActivity(), "Network isn't available", Toast.LENGTH_LONG).show();
        }

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //get the clicked item
                SubCategoryModel clickedItem = (SubCategoryModel) parent.getAdapter().getItem(position);
                String json = new Gson().toJson(clickedItem, SubCategoryModel.class);

                //send data to fragment and navigate to fragment
                Bundle data = new Bundle();
                data.putString("json", json);

                Fragment fragment = new Products();
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

        SubCategoryAPI api = adapter.create(SubCategoryAPI.class);

        api.getFeed(new Callback<List<SubCategoryModel>>() {
            @Override
            public void success(List<SubCategoryModel> subCategoryModels, Response response) {

                List<SubCategoryModel> subCategories = new ArrayList<SubCategoryModel>();

                //iterate through subcategory list
                for (int x = 0; x < subCategoryModels.size(); x++) {

                    subCategory = subCategoryModels.get(x);

                    //when the clicked category id equals the iterated category id, get  subcategopry list
                    if (categoryModel.CategoryId == subCategory.getCategoryId()) {
                        subCategories.add(subCategory);
                        System.out.println(subCategory.getSubCategoryName());

                    }
                }
                subCategoryModel = subCategories;
                mList.setAdapter(new SubCategoriesAdapter(getActivity(), R.layout.categories_list_items, subCategoryModel));
                System.out.println("SUCCESS");
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println("FAIL");

            }
        });
    }
}

