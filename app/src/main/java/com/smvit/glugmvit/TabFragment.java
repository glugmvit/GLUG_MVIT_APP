package com.smvit.glugmvit;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by susmit on 1/7/17.
 * This class is used to set the content of the tabs in the "overview" section
 */

public class TabFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle b=getArguments();
        View view = null;
        switch(b.getInt("TabNum"))
        {
            case 0:
                view = inflater.inflate(R.layout.frag_0_tab_0, container, false);
                break;
            case 1:
                view = inflater.inflate(R.layout.frag_0_tab_1, container, false);
                break;
            case 2:
                view = inflater.inflate(R.layout.frag_0_tab_2, container, false);
        }
        return view;
    }
}
