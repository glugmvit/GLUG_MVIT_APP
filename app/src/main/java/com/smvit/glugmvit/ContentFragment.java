package com.smvit.glugmvit;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
                break;
            case 1:
                view = inflater.inflate(R.layout.fragment_1, container, false);
                break;
            case 2:
                view = inflater.inflate(R.layout.fragment_2, container, false);
        }
        return view;
    }
}
