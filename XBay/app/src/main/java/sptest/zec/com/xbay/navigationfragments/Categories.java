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

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import sptest.zec.com.xbay.Adapters.CategoriesAdapter;
import sptest.zec.com.xbay.Interfaces.CategoryAPI;
import sptest.zec.com.xbay.Models.CategoryModel;
import sptest.zec.com.xbay.R;

/**
 * Created by Aleksandar on 9/19/2014.
 */
public class Categories extends Fragment {

    @InjectView(R.id.categories_list)
    ListView mList;

    List<CategoryModel> categoryModel;
    //link no longer available
        private static final String ENDPOINT = "http://webmedia.mk/api-x-bay";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.categories, container, false);
        ButterKnife.inject(this, mView);

        getActivity().setTitle(R.string.all_categories);

        //if is online reqeuest data
        if (isOnline()) {
            requestData("http://webmedia.mk/api-x-bay/api/Category/Category");

        } else {
            Toast.makeText(getActivity(), "Network isn't available", Toast.LENGTH_LONG).show();
        }

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               //get clicked item
                CategoryModel clickedItem = (CategoryModel) parent.getAdapter().getItem(position);
                String json = new Gson().toJson(clickedItem, CategoryModel.class);

                //send clicked item to fragment and go to fragment
                Bundle data = new Bundle();
                data.putString("json", json);
                Fragment fragment = new SubCategories();
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

        CategoryAPI api = adapter.create(CategoryAPI.class);

        api.getFeed(new Callback<List<CategoryModel>>() {
            @Override
            public void success(List<CategoryModel> categoryModels, Response response) {

                //if the call is success load list
                categoryModel = categoryModels;
                mList.setAdapter(new CategoriesAdapter(getActivity(), R.layout.categories_list_items, categoryModel));
                System.out.println("SUCCESS");
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println("FAIL");

            }
        });
    }

   /* private ArrayList<NavigationDrawerListModel> generateData(){
        ArrayList<NavigationDrawerListModel> navItem = new ArrayList<NavigationDrawerListModel>();
        for(int i = 0; i < 13; i++){

            navItem.add(new NavigationDrawerListModel( mDrawerTitles[i], navMenuIcons.getResourceId(3, -1), true));

        }

        return navItem;
    }*/
}

