package com.zec.xbayuiprototype.app.navigationfragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.zec.xbayuiprototype.app.Activities.SignIn;
import com.zec.xbayuiprototype.app.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Nikola Petrovski on 6/16/2014.
 */
public class NavigationSecondFragment extends Fragment {
    /**
     * Annotate fields with @InjectView and a view ID for Butter Knife to find and automatically cast the corresponding view in your layout.
     */
    @InjectView(R.id.editTxtEmail)
    EditText editTxtEmail;
    @InjectView(R.id.editTxtPassword)
    EditText editTxtPass;
    @InjectView(R.id.btnLogIn)
    Button btnLogIn;
    @InjectView(R.id.btnSignIn)
    Button btnSign;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.activity_account, container, false);
/**     Instead of slow reflection, code is generated to perform the view look-ups.
 Calling inject delegates to this generated code that you can see and debug.*/
        ButterKnife.inject(this, mView);

        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), SignIn.class);
                startActivity(i);
            }
        });

        //setting text and background color to the layout elements
       // mText.setText(getActivity().getString(R.string.txt_fragment_two));
       // mLayout.setBackgroundColor(getResources().getColor(R.color.background));
        return mView;
    }
}