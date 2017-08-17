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
import android.widget.TextView;

import com.mongodb.client.FindIterable;

import org.bson.Document;

public class Tab_fragment_0 extends Fragment {
    TextView t1;
    TextView t2;
    SwipeRefreshLayout srl;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_0_tab_0,container, false);
        t1=(TextView)view.findViewById(R.id.cv1t1);
        t2=(TextView)view.findViewById(R.id.cv1t2);
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
        mSyncTask s=new mSyncTask();
        s.execute();
    }
    public class mSyncTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute()
        {
            srl.setRefreshing(true);
        }

        @Override
        protected Void doInBackground(Void... params) {
            Shared.tables_in_collection= (int) Shared.coll.count();
            for(int i=0;i<Shared.tables_in_collection && i<5;i++)
            {
                FindIterable fi=Shared.coll.find();
                Document c=(Document)fi.first();
                Shared.DbObjs.add(new DbObject((String)c.get("name"),(String)c.get("value")));
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void v)
        {
            srl.setRefreshing(false);
            t1.setText(Shared.DbObjs.get(0).name);
            t2.setText(Shared.DbObjs.get(0).description);
        }
    }
}
