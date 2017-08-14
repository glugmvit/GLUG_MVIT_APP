package com.smvit.glugmvit;

/**
 * Created by mukund on 8/14/2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;


public class Tab_fragment_1 extends Fragment {
    ImageView show,hide;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_0_tab_1, container, false);


             show = (ImageView) view.findViewById(R.id.show);
             hide = (ImageView) view.findViewById(R.id.hide);



        show.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //  System.out.println("Show button");
                show.setVisibility(View.INVISIBLE);
                hide.setVisibility(View.VISIBLE);
                //descText.setMaxLines(Integer.MAX_VALUE);

            }
        });

        hide.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //System.out.println("Hide button");
                hide.setVisibility(View.INVISIBLE);
                show.setVisibility(View.VISIBLE);
                //    descText.setMaxLines(5);

            }
        });


        return view;
    }














}
