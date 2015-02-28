package sptest.zec.com.xbay.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import sptest.zec.com.xbay.Adapters.CartAdapter;
import sptest.zec.com.xbay.Models.CategoryViewModel;
import sptest.zec.com.xbay.R;
import sptest.zec.com.xbay.Utils.SharedPrefCart;

public class Cart extends Activity {

    @InjectView(R.id.categories_list)
    ListView cartList;

    private SharedPrefCart sp;
    List<CategoryViewModel> cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);
        ButterKnife.inject(this);

        sp = SharedPrefCart.load(this);

        cart = sp.getCart(this);

        if(cart != null){
            cartList.setAdapter(new CartAdapter(this, R.layout.activity_cart, cart));
        }else{
            Intent i = new Intent(this, MyXBay.class);
            startActivity(i);
        }


    }


    @Override
    protected void onResume() {
        super.onResume();
        ButterKnife.inject(this);


    }
}
