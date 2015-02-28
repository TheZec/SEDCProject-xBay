package com.zec.xbayuiprototype.app.navigationfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.zec.xbayuiprototype.app.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Nikola Petrovski on 6/16/2014.
 */


public class NavigationFourthFragment extends Fragment {
    /**
     * Annotate fields with @InjectView and a view ID for Butter Knife to find and automatically cast the corresponding view in your layout.
     */
    @InjectView(R.id.fragmentLayout)
    RelativeLayout mLayout;
    @InjectView(R.id.fragmentText)
    TextView mText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_navigation, container, false);
/**     Instead of slow reflection, code is generated to perform the view look-ups.
 Calling inject delegates to this generated code that you can see and debug.*/
        ButterKnife.inject(this, mView);

        //setting text and background color to the layout elements
       // mText.setText(getActivity().getString(R.string.txt_fragment_one));
        mLayout.setBackgroundColor(getResources().getColor(R.color.background));
        return mView;
    }
}