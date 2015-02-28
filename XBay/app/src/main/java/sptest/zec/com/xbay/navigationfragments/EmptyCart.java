package sptest.zec.com.xbay.navigationfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import sptest.zec.com.xbay.R;

/**
 * Created by Aleksandar on 9/19/2014.
 */
public class EmptyCart extends Fragment {

    @InjectView(R.id.btn_cart_view)
    Button mCart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.empty_cart, container, false);
        ButterKnife.inject(this, mView);

        getActivity().setTitle(R.string.cart);

        mCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new Categories();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack("").commit();
            }
        });

        return mView;
    }
}

