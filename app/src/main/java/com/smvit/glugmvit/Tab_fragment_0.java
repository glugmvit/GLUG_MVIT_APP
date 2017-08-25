package com.smvit.glugmvit;

/**
 * Created by mukund on 8/14/2017.
 * class for the upcoming section
 * Modified by Susmit on 17/08/2017
 */

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.transition.TransitionManager;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mongodb.client.MongoCursor;

import org.bson.Document;

public class Tab_fragment_0 extends Fragment {
    SwipeRefreshLayout srl;
    LayoutInflater li;
    boolean first;
    LinearLayout ll;
    mSyncTask s;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        li=inflater;
        View view = inflater.inflate(R.layout.frag_0_tab_0,container, false);
        first=true;
        srl=(SwipeRefreshLayout)view.findViewById(R.id.srl_t0);
        ll=(LinearLayout)srl.findViewById(R.id.f0t0ll);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
        return view;
    }

    void refresh()
    {
        s=new mSyncTask();
        s.execute();
    }

    public class mSyncTask extends AsyncTask<Void,Void,Boolean> {

        @Override
        protected void onPreExecute()
        {
            srl.setRefreshing(true);
            Shared.UpcomingEventsList.clear();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            if(Shared.UECollection.count()<=0)
                return false;
            MongoCursor cursor=Shared.UECollection.find().iterator();
            while(cursor.hasNext())
            {
                Document c=(Document)cursor.next();
                Shared.UpcomingEventsList.add(0,new UpcomingEventsDataObject((String)c.get("name"),(String)c.get("speaker"),(String)c.get("description"),(String)c.get("date"),(String)c.get("venue"),(String)c.get("extras"),null));
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean b)
        {
            srl.setRefreshing(false);
            if(ll.getChildCount()>0)
                ll.removeAllViews();
            if(!b) {
                ll.addView(li.inflate(R.layout.blank_f0_t0,null));
            }
            else
            {
                if(first)
                {
                    first=false;
                    ll.removeAllViews();
                }
                updateUECards();
            }
        }
    }

    void updateUECards()
    {
        for(int x=0;x<Shared.UpcomingEventsList.size();x++)
        {
            final int i=x;
            Shared.UpcomingEventsList.get(i).ll=(LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.upcoming_events_card_view,null);
            Shared.UpcomingEventsList.get(i).cv=(CardView)Shared.UpcomingEventsList.get(i).ll.findViewById(R.id.ue_card_view);

            Shared.UpcomingEventsList.get(i).Event=(TextView)Shared.UpcomingEventsList.get(i).ll.findViewById(R.id.txtName);
            Shared.UpcomingEventsList.get(i).Event.append(Shared.UpcomingEventsList.get(i).event);

            Shared.UpcomingEventsList.get(i).Desc=(TextView) Shared.UpcomingEventsList.get(i).ll.findViewById(R.id.ue_desc);
            Shared.UpcomingEventsList.get(i).Desc.setText(Shared.UpcomingEventsList.get(i).desc);

            Shared.UpcomingEventsList.get(i).Extras=(TextView)Shared.UpcomingEventsList.get(i).ll.findViewById(R.id.extras);
            Shared.UpcomingEventsList.get(i).Extras.setText(Shared.UpcomingEventsList.get(i).prerequisites);

            Shared.UpcomingEventsList.get(i).DnT=(TextView)Shared.UpcomingEventsList.get(i).ll.findViewById(R.id.dnt);
            Shared.UpcomingEventsList.get(i).DnT.setText(Shared.UpcomingEventsList.get(i).datentime);

            Shared.UpcomingEventsList.get(i).Venue=(TextView)Shared.UpcomingEventsList.get(i).ll.findViewById(R.id.venue);
            Shared.UpcomingEventsList.get(i).Venue.append(Shared.UpcomingEventsList.get(i).venue);

            Shared.UpcomingEventsList.get(i).Speaker=(TextView)Shared.UpcomingEventsList.get(i).ll.findViewById(R.id.speaker);
            Shared.UpcomingEventsList.get(i).Speaker.append(Shared.UpcomingEventsList.get(i).speaker);

            Shared.UpcomingEventsList.get(i).clicked=false;

            Shared.UpcomingEventsList.get(i).cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!Shared.UpcomingEventsList.get(i).clicked) {
                        Shared.UpcomingEventsList.get(i).clicked=true;
                        TransitionManager.beginDelayedTransition(Shared.UpcomingEventsList.get(i).cv);
                        Shared.UpcomingEventsList.get(i).Speaker.setVisibility(View.VISIBLE);
                        Shared.UpcomingEventsList.get(i).Venue.setVisibility(View.VISIBLE);
                        Shared.UpcomingEventsList.get(i).Extras.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        Shared.UpcomingEventsList.get(i).clicked=false;
                        TransitionManager.beginDelayedTransition(Shared.UpcomingEventsList.get(i).cv);
                        Shared.UpcomingEventsList.get(i).Speaker.setVisibility(View.GONE);
                        Shared.UpcomingEventsList.get(i).Venue.setVisibility(View.GONE);
                        Shared.UpcomingEventsList.get(i).Extras.setVisibility(View.GONE);
                    }
                }
            });
            ll.addView(Shared.UpcomingEventsList.get(i).ll);
        }
    }

    @Override
    public void onPause()
    {
        super.onPause();
        ll.removeAllViews();
        Shared.DbObjs.clear();
        s.cancel(true);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        s=new mSyncTask();
        s.execute();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        s.cancel(true);
    }
}
