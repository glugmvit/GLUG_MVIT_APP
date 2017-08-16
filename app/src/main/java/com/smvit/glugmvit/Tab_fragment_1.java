package com.smvit.glugmvit;

/**
 * Created by mukund on 8/14/2017.
 * class for the current project section
 */

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class Tab_fragment_1 extends Fragment {
    ImageView show,hide;
    TextView desctext,title_members,members;
    Button contribute;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_0_tab_1, container, false);






        contribute = (Button) view.findViewById(R.id.contribute);
             show = (ImageView) view.findViewById(R.id.show);
             hide = (ImageView) view.findViewById(R.id.hide);
             desctext=(TextView) view.findViewById(R.id.project_description);
        title_members=(TextView) view.findViewById(R.id.title_members);
        members=(TextView) view.findViewById(R.id.members);


        show.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                show.setVisibility(View.INVISIBLE);
                hide.setVisibility(View.VISIBLE);
                desctext.setMaxLines(Integer.MAX_VALUE);
                title_members.setMaxLines(Integer.MAX_VALUE);
                members.setMaxLines(Integer.MAX_VALUE);

            }
        });

        hide.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                hide.setVisibility(View.INVISIBLE);
                show.setVisibility(View.VISIBLE);
                desctext.setMaxLines(0);
                title_members.setMaxLines(0);
                members.setMaxLines(0);

            }
        });
        contribute.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

         /*link to login page and ask for sign up
         if alredy signed in, email the user information to glug mvit organization
          */
            }

        });
        return view;
    }
}
