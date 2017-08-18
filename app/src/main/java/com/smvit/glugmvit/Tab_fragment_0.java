package com.smvit.glugmvit;

/**
 * Created by mukund on 8/14/2017.
 * class for the upcoming section
 * Modified by Susmit on 17/08/2017
 */

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mongodb.DBObject;
import com.mongodb.client.MongoCursor;

import org.bson.Document;

import java.util.ArrayList;

public class Tab_fragment_0 extends Fragment {
    SwipeRefreshLayout srl;
    LinearLayout ll;
    LayoutInflater li;
    boolean first;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        li=inflater;
        View view = inflater.inflate(R.layout.frag_0_tab_0,container, false);
        ll=(LinearLayout)view.findViewById(R.id.f0t0ll);
        first=true;
        srl=(SwipeRefreshLayout)view.findViewById(R.id.srl_t0);
        refresh();
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
        //Shared.TestCollection.insertOne(new Document("test2","val2"));
        mSyncTask s=new mSyncTask();
        s.execute();
    }

    public class mSyncTask extends AsyncTask<Void,Void,Boolean> {

        @Override
        protected void onPreExecute()
        {
            srl.setRefreshing(true);
            Shared.DbObjs.removeAll(Shared.DbObjs.subList(0,Shared.DbObjs.size()));
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            if(Shared.TestCollection.count()<=0)
                return false;
            MongoCursor cursor=Shared.TestCollection.find().iterator();
            while(cursor.hasNext())
            {
                Document c=(Document)cursor.next();
                Shared.DbObjs.add(0,new DbObject((String)c.get("name"),(String)c.get("value")));
                //Shared.UpcomingEventsList.add(new UpcomingEventsDataObject((String)c.get("title"),(String)c.get("presentor"),(String)c.get("desc"),(String)c.get("imglink")));
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
            //t1.setText(Shared.DbObjs.get(0).name);
            //t2.setText(Shared.DbObjs.get(0).description);
        }
    }

    void updateUECards()
    {
        for(int i=0;i<Shared.DbObjs.size();i++)
        {
            View cv=li.inflate(R.layout.upcoming_events_card_view,null);
            TextView t1=(TextView)cv.findViewById(R.id.txtName);
            t1.setText(Shared.DbObjs.get(i).name);
            TextView t2=(TextView)cv.findViewById(R.id.txtSurname);
            t2.setText(Shared.DbObjs.get(i).description);
            ll.addView(cv);
        }
    }
}
