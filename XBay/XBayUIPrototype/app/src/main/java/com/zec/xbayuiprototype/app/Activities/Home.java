package com.zec.xbayuiprototype.app.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

import com.zec.xbayuiprototype.app.Adapters.HomeListAdapter;
import com.zec.xbayuiprototype.app.Models.ListMenu;
import com.zec.xbayuiprototype.app.R;
import com.zec.xbayuiprototype.app.SetItems;

import java.util.ArrayList;


public class Home extends Activity implements SetItems{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageView imgView = (ImageView) findViewById(R.id.imageView);
        SearchView sView = (SearchView) findViewById(R.id.searchView);

        ArrayList items = setItem();
        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new HomeListAdapter(this, items ));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position){
                    case 0:
                        Intent iCategories = new Intent(Home.this, Categories.class);
                        startActivity(iCategories);
                        break;
                    case 1:
                        Intent iAccount = new Intent(Home.this, Account.class);
                        startActivity(iAccount);
                        break;
                    case 2:
                        Intent iCart = new Intent(Home.this, Cart.class);
                        startActivity(iCart);
                        break;
                    case 3:
                        Intent iTodaysBest = new Intent(Home.this, TodaysBest.class);
                        startActivity(iTodaysBest);
                        break;

                }




            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
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
        ArrayList arrayList = new ArrayList();
        ListMenu listMenu = new ListMenu();
        listMenu.setItem("Categories");
        arrayList.add(listMenu);

        listMenu = new ListMenu();
        listMenu.setItem("Account");
        arrayList.add(listMenu);

        listMenu = new ListMenu();
        listMenu.setItem("Cart");
        arrayList.add(listMenu);

        listMenu = new ListMenu();
        listMenu.setItem("Todays best");
        arrayList.add(listMenu);

        return arrayList;
    }
}
