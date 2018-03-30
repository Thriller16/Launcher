package com.nexdev.enyason.launcherapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Enyason on 3/8/2018.
 */

public class CustomFragnebtPagerAdapter extends FragmentStatePagerAdapter {


    public CustomFragnebtPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {


        switch (position) {
            case 0:
                return new HomeFragment();

            case 1:
                return new AppsDrawerFragment();
            default:
                return null;



        }    }


    @Override
    public int getCount() {
        return 2;
    }


}
