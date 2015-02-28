package sptest.zec.com.xbay.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sptest.zec.com.xbay.Models.CategoryViewModel;

public class SharedPrefCart {

    public static final String CART = "Product";
    private static final String PREFS_NAME = "PRODUCT_CART";
    private final SharedPreferences sharedPreference;


    public SharedPrefCart(SharedPreferences sharedPreferences) {
        this.sharedPreference = sharedPreferences;
    }

    public static SharedPrefCart load(Context context) {
        return new SharedPrefCart(context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE));
    }

    // This four methods are used for maintaining favorites.
    public void saveCart(Context context, List<CategoryViewModel> cartList) {
        SharedPreferences settings;
        Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonCart = gson.toJson(cartList);

        editor.putString(CART, jsonCart);

        editor.commit();
    }

    public void addToCart(Context context, CategoryViewModel cart) {
        List<CategoryViewModel> favorites = getCart(context);
        if (favorites == null)
            favorites = new ArrayList<CategoryViewModel>();
        if (!favorites.contains(cart)) {
            favorites.add(cart);
            cart.setCart(true);

        }
        saveCart(context, favorites);
    }

    public void removeFromCart(Context context, CategoryViewModel cart) {

        List<CategoryViewModel> favorites = getCart(context);
        if (favorites != null) {
            for (int i = 0; i < favorites.size(); i++) {
                CategoryViewModel re = favorites.get(i);
                if (re.getProductId() == cart.getProductId()) {
                    favorites.remove(cart);
                    cart.setCart(false);
                    break;
                }
            }
        }
        saveCart(context, favorites);
    }

    public ArrayList<CategoryViewModel> getCart(Context context) {
        SharedPreferences settings;
        List<CategoryViewModel> cartList;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(CART)) {
            String jsonCart = settings.getString(CART, null);
            Gson gson = new Gson();
            CategoryViewModel[] cartItems = gson.fromJson(jsonCart,
                    CategoryViewModel[].class);

            cartList = Arrays.asList(cartItems);
            cartList = new ArrayList<CategoryViewModel>(cartList);
        } else
            return null;

        return (ArrayList<CategoryViewModel>) cartList;
    }
}