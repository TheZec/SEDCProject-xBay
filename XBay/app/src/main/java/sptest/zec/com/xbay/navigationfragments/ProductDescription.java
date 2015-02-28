package sptest.zec.com.xbay.navigationfragments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import sptest.zec.com.xbay.Activities.Cart;
import sptest.zec.com.xbay.Interfaces.CategoryViewAPI;
import sptest.zec.com.xbay.Models.CategoryViewModel;
import sptest.zec.com.xbay.R;
import sptest.zec.com.xbay.Utils.SharedPrefCart;

/**
 * Created by Aleksandar on 9/19/2014.
 */
public class ProductDescription extends Fragment {

    @InjectView(R.id.product_name)
    TextView productName;
    @InjectView(R.id.product_price)
    TextView productPrice;
    @InjectView(R.id.img_product)
    ImageView productImg;
    @InjectView(R.id.add_to_cart)
    Button btnAddToCart;
    @InjectView(R.id.txt_condition)
    TextView txtCondition;
    @InjectView(R.id.item_description)
    TextView itemDesc;

    private static final String ENDPOINT = "http://webmedia.mk/api-x-bay";
    List<CategoryViewModel> categoryViewModels;
    private String i;
    private CategoryViewModel products;
    private CategoryViewModel categoryView;
    private Context context;
    private SharedPrefCart sp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.product_description, container, false);
        ButterKnife.inject(this, mView);

        sp = SharedPrefCart.load(getActivity());

        getActivity().setTitle(R.string.product_desc);

        //get data from subcategory fragment
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            i = bundle.getString("json");
            products = new Gson().fromJson(i, CategoryViewModel.class);
            System.out.println(products.getProductId());
        }

        if (isOnline()) {
            requestData("http://webmedia.mk/api-x-bay/api/Product/products");

        } else {
            Toast.makeText(getActivity(), "Network isn't available", Toast.LENGTH_LONG).show();
        }

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // sp.addToCart(context, categoryView);
                //products.setCart(true);
                Intent i = new Intent(getActivity(), Cart.class);
                startActivity(i);
                Toast.makeText(getActivity(), "Curently not available :) ", Toast.LENGTH_SHORT).show();

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

                //iterate through product list
                for (int x = 0; x < categoryViewModel.size(); x++) {

                    categoryView = categoryViewModel.get(x);

                    //when the clicked product id equals the iterated product id, get that product
                    if (products.getProductId() == categoryView.getProductId()) {
                        productName.setText(categoryView.getProductName());
                        productPrice.setText("US $" + categoryView.getPrice());
                        txtCondition.setText(categoryView.getItemCondition());
                        itemDesc.setText(categoryView.getDescription());
                        System.out.println(categoryView.getDescription());

                        String path = "http://www.webmedia.mk/xbay/";
                        Picasso.with(context)
                                .load(path + categoryView.getProductImageUrl().replaceAll("~/", ""))
                                .resize(640, 500)
                                .placeholder(R.id.img_product)
                                .into(productImg);

                    }
                }

                System.out.println("SUCCESS");
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println("FAIL");

            }
        });
    }
}

