package sptest.zec.com.xbay.navigationfragments;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import sptest.zec.com.xbay.Activities.MyXBay;
import sptest.zec.com.xbay.R;
import sptest.zec.com.xbay.Utils.SharedPrefUser;


public class HomePageFragment extends Fragment {

    @InjectView(R.id.button_my_account)
    Button mBtnAcc;
    @InjectView(R.id.button_all_categories)
    Button mBtnCat;
    @InjectView(R.id.button_cart)
    Button mBtnCart;
    @InjectView(R.id.horizontalScrollView)
    HorizontalScrollView hView;

    private SharedPrefUser sp;


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View mView = inflater.inflate(R.layout.activity_home_page, container, false);
        ButterKnife.inject(this, mView);

        sp = new SharedPrefUser(getActivity());

        mBtnAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println(sp.getUserDetails());
                //check if the user is loged in using sharedpreferences method. if is loged redirect to profile, else to log in
                sp.checkLogin();

            }
        });

        mBtnCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new Categories();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack("").commit();
            }
        });

        mBtnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(sp.isLoggedIn()){
                    Fragment fragment = new EmptyCart();
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack("").commit();
                }else{

                    Intent i = new Intent(getActivity(), MyXBay.class);
                    startActivity(i);

                }
            }
        });

        return mView;
    }
}