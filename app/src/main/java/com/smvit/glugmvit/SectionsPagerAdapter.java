package com.smvit.glugmvit;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by VibhorSharma on 25-06-2017.
 * Adapter for the view pager in overview section
 * Modified by Susmit on 1/07/2017
 * Modified by Mukund on 15/08/2017
 * Modified by Susmit on 17/08/2017
 */

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new Tab_fragment_0();
        }
        else if (position == 1) {
            return new Tab_fragment_1();
        }
         else {
            return new Tab_fragment_2();
        }
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "EVENTS";
            case 1:
                return "PROJECTS";
            case 2:
                return "IDEAS";
        }
        return null;
    }
}
