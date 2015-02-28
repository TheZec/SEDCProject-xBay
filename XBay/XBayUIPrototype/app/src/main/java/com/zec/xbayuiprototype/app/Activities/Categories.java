package com.zec.xbayuiprototype.app.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zec.xbayuiprototype.app.Adapters.HomeListAdapter;
import com.zec.xbayuiprototype.app.Models.ListMenu;
import com.zec.xbayuiprototype.app.R;
import com.zec.xbayuiprototype.app.SetItems;

import java.util.ArrayList;


public class Categories extends Activity implements SetItems {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);


        ArrayList list = setItem();
        ListView listView = (ListView) findViewById(R.id.cat_list);
        listView.setAdapter(new HomeListAdapter(this, list));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        Intent i = new Intent(Categories.this, ProductSample.class);
                        startActivity(i);
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.categories, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public ArrayList setItem() {

        ArrayList array = new ArrayList();
        ListMenu listMenu = new ListMenu();
        listMenu.setItem("Electronics");
        array.add(listMenu);

        listMenu = new ListMenu();
        listMenu.setItem("Fashion");
        array.add(listMenu);

        listMenu = new ListMenu();
        listMenu.setItem("Entertainment");
        array.add(listMenu);

        listMenu = new ListMenu();
        listMenu.setItem("Motors");
        array.add(listMenu);

        listMenu = new ListMenu();
        listMenu.setItem("Home and Garden");
        array.add(listMenu);

        listMenu = new ListMenu();
        listMenu.setItem("Sport");
        array.add(listMenu);

        listMenu = new ListMenu();
        listMenu.setItem("Toys");
        array.add(listMenu);

        listMenu = new ListMenu();
        listMenu.setItem("Hobies");
        array.add(listMenu);

        listMenu = new ListMenu();
        listMenu.setItem("Collectibles and art");
        array.add(listMenu);

        listMenu = new ListMenu();
        listMenu.setItem("Other categories");
        array.add(listMenu);

        return array;
    }
}
