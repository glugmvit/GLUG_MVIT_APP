package com.smvit.glugmvit;

/**
 * Created by mukund on 8/14/2017.
 * class for the current project
 * Modified by Susmit on 16/08/17
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class Tab_fragment_1 extends Fragment {
    ImageView show;
    TextView desctext,title_members,members;
    Button contribute;
    boolean showing;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_0_tab_1, container, false);

        showing=false;
        contribute = (Button) view.findViewById(R.id.contribute);
        show = (ImageView) view.findViewById(R.id.show);
        desctext=(TextView) view.findViewById(R.id.project_description);
        title_members=(TextView) view.findViewById(R.id.title_members);
        members=(TextView) view.findViewById(R.id.members);
        LinearLayout fixedPart=(LinearLayout)view.findViewById(R.id.fixedPart);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation a;
                if(!showing) {
                    showing=true;
                    show.setImageResource(R.mipmap.arrow_up);
                    desctext.setVisibility(View.VISIBLE);
                    title_members.setVisibility(View.VISIBLE);
                    members.setVisibility(View.VISIBLE);
                }
                else
                {
                    showing=false;
                    show.setImageResource(R.mipmap.arrow_down);
                    desctext.setVisibility(View.GONE);
                    title_members.setVisibility(View.GONE);
                    members.setVisibility(View.GONE);
                }
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
