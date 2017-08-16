package com.smvit.glugmvit;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mongodb.client.FindIterable;

import org.bson.Document;

/**
 * Created by susmit on 1/7/17.
 * This class is used to set the content of the tabs in the "overview" section
 */

public class TabFragment extends Fragment
{
    TextView t1;
    TextView t2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle b=getArguments();
        View view = null;
        switch(b.getInt("TabNum"))
        {
            case 0:
                view = inflater.inflate(R.layout.frag_0_tab_0, container, false);
                CardView cv1=(CardView)view.findViewById(R.id.cv1);
                t1=(TextView)view.findViewById(R.id.cv1t1);
                t2=(TextView)view.findViewById(R.id.cv1t2);
                refresh();
                break;
            case 1:
                view = inflater.inflate(R.layout.frag_0_tab_1, container, false);
                break;
            case 2:
                view = inflater.inflate(R.layout.frag_0_tab_2, container, false);
        }
        return view;
    }
    void refresh()
    {
        mSyncTask s=new mSyncTask();
        s.execute();
    }
    public class mSyncTask extends AsyncTask<Void,Void,Void> {

        ProgressDialog pd;

        @Override
        protected void onPreExecute()
        {
            //pd=new ProgressDialog(Shared.appContext);
            //pd.setTitle("Syncing...");
            //pd.setMessage("Please Wait");
            //pd.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            Shared.tables_in_collection= (int) Shared.coll.count();
            for(int i=0;i<Shared.tables_in_collection && i<5;i++)
            {
                FindIterable<Document> fi=Shared.coll.find();
                Document c=fi.first();
                Shared.DbObjs.add(new DbObject((String)c.get("name"),(String)c.get("value")));
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void v)
        {
            //pd.dismiss();
            t1.setText(Shared.DbObjs.get(0).name);
            t2.setText(Shared.DbObjs.get(0).description);
        }
    }

}
