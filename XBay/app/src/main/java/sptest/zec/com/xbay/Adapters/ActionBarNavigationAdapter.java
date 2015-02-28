package sptest.zec.com.xbay.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import sptest.zec.com.xbay.navigationfragments.LogInFragment;
import sptest.zec.com.xbay.navigationfragments.RegisterFragment;


public class ActionBarNavigationAdapter extends FragmentPagerAdapter {

    public ActionBarNavigationAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                // fragment one
                return new LogInFragment();
            case 1:
                // fragment two
                return new RegisterFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 2;
    }
}