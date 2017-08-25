package com.smvit.glugmvit;

import android.support.v7.widget.CardView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by susmit on 18/8/17.
 */

public class UpcomingEventsDataObject
{
    String event;
    String speaker;
    String desc;
    String datentime;
    String venue;
    String prerequisites;
    String imgLink;
    ImageView img;
    TextView Event;
    TextView Speaker;
    TextView Desc;
    TextView Venue;
    TextView DnT;
    TextView Extras;
    CardView cv;
    LinearLayout ll;
    boolean clicked;
    UpcomingEventsDataObject(String... params)
    {
        event=params[0];
        speaker=params[1];
        desc=params[2];
        datentime=params[3];
        venue=params[4];
        prerequisites=params[5];
        imgLink=params[6];
    }
}
