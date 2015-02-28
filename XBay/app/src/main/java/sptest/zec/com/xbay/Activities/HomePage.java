package sptest.zec.com.xbay.Activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import sptest.zec.com.xbay.Adapters.NavigationdDrawerAdapter;
import sptest.zec.com.xbay.Models.NavigationDrawerListModel;
import sptest.zec.com.xbay.R;
import sptest.zec.com.xbay.Utils.SharedPrefUser;
import sptest.zec.com.xbay.navigationfragments.EmptyCart;
import sptest.zec.com.xbay.navigationfragments.HomePageFragment;


public class HomePage extends ActionBarActivity {

    @InjectView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @InjectView(R.id.drawer_list_menu)
    ListView mDraListView;

    private String[] mDrawerTitles;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private TypedArray navMenuIcons;
    private ActionBarDrawerToggle mDrawerToggle;
    private SharedPrefUser sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_drawer);
        ButterKnife.inject(this);

        sp = new SharedPrefUser(this);

        //getting array from array resources
        mDrawerTitles = getResources().getStringArray(R.array.navigation_drawer_items);

        navMenuIcons = getResources().obtainTypedArray(R.array.navigatin_drawer_icons);

        //setting predefined Array adapter for the list view
        mDraListView.setAdapter(new NavigationdDrawerAdapter(this, generateData()));

        //setting on list click listener
        mDraListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                selectDrawerItem(position);
            }
        });

        //Listen for Open and Close Events
        mTitle = mDrawerTitle = getTitle();

        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                R.drawable.ic_drawer, //nav menu toggle icon
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ) {
            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        if (savedInstanceState == null) {
            // on first time display view for first nav item
            selectDrawerItem(0);
        }
    }

    private void selectDrawerItem(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new HomePageFragment();
                break;
            case 1:
                sp.checkLogin();
                break;
            case 2:
                if(sp.isLoggedIn()){
                    fragment = new EmptyCart();
                }else{
                    Intent i = new Intent(this, MyXBay.class);
                    startActivity(i);
                }
                break;
            default:
                break;
        }

        if (fragment != null) {
        //insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();

        //highlight the selected item, update the title, and close the drawer
        mDraListView.setItemChecked(position, true);
        setTitle(mDrawerTitles[position]);
        mDrawerLayout.closeDrawer(mDraListView);
    } else {
        // error while creating fragment
    }
}

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...
        return super.onOptionsItemSelected(item);
    }

    private ArrayList<NavigationDrawerListModel> generateData() {
        ArrayList<NavigationDrawerListModel> navItem = new ArrayList<NavigationDrawerListModel>();
        for (int i = 0; i < 8; i++) {

            navItem.add(new NavigationDrawerListModel(mDrawerTitles[i], navMenuIcons.getResourceId(i, -1), true));

        }

        return navItem;
    }
}