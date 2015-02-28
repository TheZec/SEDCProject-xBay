package sptest.zec.com.xbay.Activities;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import sptest.zec.com.xbay.Adapters.ActionBarNavigationAdapter;
import sptest.zec.com.xbay.R;

/**
 * Created by Aleksandar on 9/19/2014.
 */
public class MyXBay extends ActionBarActivity implements ActionBar.TabListener {

    @InjectView(R.id.pager)
    ViewPager mViewPager;
    private ActionBar mActionBar;
    private ActionBarNavigationAdapter mAdapter;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_xbay);
        ButterKnife.inject(this);

        this.setTitle(R.string.title_activity_my_xbay);

        mActionBar = getActionBar();
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        mActionBar.setStackedBackgroundDrawable(new ColorDrawable(getResources().getColor(
                R.color.white)));
        mActionBar.setSplitBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.background_blue)));
        mActionBar.setHomeAsUpIndicator(R.color.background_blue);
        mAdapter = new ActionBarNavigationAdapter(getSupportFragmentManager());

        //setting predefined-adapter
        mViewPager.setAdapter(mAdapter);

        // Adding Tabs
        mActionBar.addTab(mActionBar.newTab().setText(getString(R.string.login)).setTabListener(this));
        mActionBar.addTab(mActionBar.newTab().setText(getString(R.string.register)).setTabListener(this));

        // on changing the page
        // make respected tab selected
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mActionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });

    }

    // on tab selected
    // show respected fragment view
    @Override
    public void onTabSelected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {
    }

}


