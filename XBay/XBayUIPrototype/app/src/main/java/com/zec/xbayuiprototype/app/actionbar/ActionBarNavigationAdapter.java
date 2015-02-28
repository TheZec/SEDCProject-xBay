package com.zec.xbayuiprototype.app.actionbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.zec.xbayuiprototype.app.navigationfragments.NavigationFirstFragment;
import com.zec.xbayuiprototype.app.navigationfragments.NavigationFourthFragment;
import com.zec.xbayuiprototype.app.navigationfragments.NavigationSecondFragment;
import com.zec.xbayuiprototype.app.navigationfragments.NavigationThirdFragment;

/**
 * Created by Nikola Petrovski on 6/22/2014.
 */

/**Adapter for ViewPager*/
public class ActionBarNavigationAdapter extends FragmentPagerAdapter {

    public ActionBarNavigationAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                // fragment one
                return new NavigationFirstFragment();
            case 1:
                // fragment two
                return new NavigationSecondFragment();
            case 2:
                // fragment three
                return new NavigationThirdFragment();
            case 3:
                return  new NavigationFourthFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 4;
    }
}