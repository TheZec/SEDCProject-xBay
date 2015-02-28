package com.zec.xbayuiprototype.app.actionbar;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;


import com.zec.xbayuiprototype.app.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ActionBarTabNavigationActivity extends FragmentActivity implements ActionBar.TabListener {

    @InjectView(R.id.pager)
    ViewPager mViewPager;
    private ActionBar mActionBar;
    private ActionBarNavigationAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar_tab_navigation);
        ButterKnife.inject(this);

        mActionBar = getActionBar();
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        mAdapter = new ActionBarNavigationAdapter(getSupportFragmentManager());

        //setting predefined-adapter
        mViewPager.setAdapter(mAdapter);

        // Adding Tabs
        mActionBar.addTab(mActionBar.newTab().setText(getString(R.string.categories)).setTabListener(this));
        mActionBar.addTab(mActionBar.newTab().setText(getString(R.string.account)).setTabListener(this));
        mActionBar.addTab(mActionBar.newTab().setText(getString(R.string.cart)).setTabListener(this));
        mActionBar.addTab(mActionBar.newTab().setText(getString(R.string.todays_best)).setTabListener(this));

        // on changing the page
        // make respected tab selected
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mActionBar.setSelectedNavigationItem(position);
            }
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {}

            @Override
            public void onPageScrollStateChanged(int arg0) {}
        });
    }

    // on tab selected
    // show respected fragment view
    @Override
    public void onTabSelected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {
         mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {}

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {}
}
