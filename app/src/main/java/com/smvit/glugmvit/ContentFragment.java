package com.smvit.glugmvit;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by susmit on 24/6/17.
 */

public class ContentFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle b=getArguments();
        View view = null;
        switch(b.getInt("Num"))
        {
            case 0:
                view = inflater.inflate(R.layout.fragment_0, container, false);
                setView(view,getChildFragmentManager());
                break;
            case 1:
                view = inflater.inflate(R.layout.fragment_1, container, false);
                break;
            case 2:
                view = inflater.inflate(R.layout.fragment_2, container, false);
        }
        return view;
    }

    /**
     * Created by VibhorSharma 30/06/17
     * Takes a view and fragment manager and sets the adapter to view_pager and the view_pager to TabLayout
     * @param view
     * @param fragmentManager
     */
    public void setView(View view, FragmentManager fragmentManager)
    {
        SectionsPagerAdapter mSectionsPagerAdapter;
        ViewPager mViewPager;
        mSectionsPagerAdapter = new SectionsPagerAdapter(fragmentManager);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) view.findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.OverviewTabs);
        tabLayout.setTabTextColors(0xFFD7D7D7,0xFFFFFFFF);
        //No.of tabs scrolled before former tab refreshes.
        mViewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(mViewPager);
    }
}

