package com.smvit.glugmvit;

import android.support.v7.widget.CardView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by susmit on 18/8/17.
 */

public class CurrentProjectsDataObject
{
    String ProjectName;
    String Members;
    String desc;
    String GitLink;
    String imgLink;
    LinearLayout ll;
    CardView cv;
    TextView title,desctext,title_members,members;
    Button gitBtn;
    boolean clicked;

    CurrentProjectsDataObject(String... params)
    {
        ProjectName=params[0];
        desc=params[1];
        Members=params[2];
        GitLink=params[3];
        imgLink=params[4];
    }

    CurrentProjectsDataObject(String p,String m,String d,String g, String i)
    {
        ProjectName=p;
        Members=m;
        desc=d;
        GitLink=g;
        imgLink=i;
    }
}
