package com.zec.xbayuiprototype.app.navigationfragments;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.zec.xbayuiprototype.app.Activities.ProductSample;
import com.zec.xbayuiprototype.app.Adapters.HomeListAdapter;
import com.zec.xbayuiprototype.app.Models.ListMenu;
import com.zec.xbayuiprototype.app.R;

import java.util.ArrayList;



/**
 * Created by Nikola Petrovski on 6/16/2014.
 */


public class NavigationFirstFragment extends Fragment {
    /**
     * Annotate fields with @InjectView and a view ID for Butter Knife to find and automatically cast the corresponding view in your layout.
     */
   /* @InjectView(R.id.cat_list)
    RelativeLayout list;*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.activity_categories, container, false);
/**     Instead of slow reflection, code is generated to perform the view look-ups.
 Calling inject delegates to this generated code that you can see and debug.*/
        //ButterKnife.inject(this, mView);


       ListView  mList = (ListView)mView.findViewById(R.id.cat_list);
        Resources res = getResources();
        String[] categories = res.getStringArray(R.array.kategorii);


        ArrayAdapter<String> homeListAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, categories);
        mList.setAdapter(homeListAdapter);

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), ProductSample.class);
                startActivity(i);
            }
        });

        //setting text and background color to the layout elements
        // mText.setText(getActivity().getString(R.string.txt_fragment_one));

        return mView;

    }


//    public ArrayList setItem() {
//
//        ArrayList array = new ArrayList();
//        ListMenu listMenu = new ListMenu();
//        listMenu.setItem("Electronics");
//        array.add(listMenu);
//
//        listMenu = new ListMenu();
//        listMenu.setItem("Fashion");
//        array.add(listMenu);
//
//        listMenu = new ListMenu();
//        listMenu.setItem("Entertainment");
//        array.add(listMenu);
//
//        listMenu = new ListMenu();
//        listMenu.setItem("Motors");
//        array.add(listMenu);
//
//        listMenu = new ListMenu();
//        listMenu.setItem("Home and Garden");
//        array.add(listMenu);
//
//        listMenu = new ListMenu();
//        listMenu.setItem("Sport");
//        array.add(listMenu);
//
//        listMenu = new ListMenu();
//        listMenu.setItem("Toys");
//        array.add(listMenu);
//
//        listMenu = new ListMenu();
//        listMenu.setItem("Hobies");
//        array.add(listMenu);
//
//        listMenu = new ListMenu();
//        listMenu.setItem("Collectibles and art");
//        array.add(listMenu);
//
//        listMenu = new ListMenu();
//        listMenu.setItem("Other categories");
//        array.add(listMenu);
//
//        return array;
//    }
}
