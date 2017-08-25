package com.smvit.glugmvit;

/**
 * Created by mukund on 8/14/2017.
 * class for the current project
 * Modified by Susmit on 16/08/17
 */

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.transition.TransitionManager;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mongodb.client.MongoCursor;

import org.bson.Document;


public class Tab_fragment_1 extends Fragment {
    LinearLayout ll;
    SwipeRefreshLayout psrl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_0_tab_1, container, false);
        psrl= (SwipeRefreshLayout) view.findViewById(R.id.psrl);
        ll=(LinearLayout)psrl.findViewById(R.id.pll);
        mSyncTask mst=new mSyncTask();
        mst.execute();
        return view;
    }

    public class mSyncTask extends AsyncTask<Void,Void,Boolean>
    {
        LayoutInflater li;
        boolean first;
        @Override
        protected void onPreExecute()
        {
            first=false;
            li=LayoutInflater.from(getActivity());
            psrl.setRefreshing(true);
            Shared.CurrentProjectsList.clear();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            if(Shared.CPCollection.count()<=0)
                return false;
            MongoCursor cursor=Shared.CPCollection.find().iterator();
            while(cursor.hasNext())
            {
                Document c=(Document)cursor.next();
                Shared.CurrentProjectsList.add(0,new CurrentProjectsDataObject((String)c.get("project_name"),(String)c.get("project_description"),(String)c.get("members"),(String)c.get("github"),null));
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean b)
        {
            psrl.setRefreshing(false);
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
                updatePCards();
            }
        }
    }

    void updatePCards()
    {
        for(int x=0;x<Shared.CurrentProjectsList.size();x++)
        {
            final int i=x;
            Shared.CurrentProjectsList.get(i).ll=(LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.project_card_view,null);
            Shared.CurrentProjectsList.get(i).cv=(CardView)Shared.CurrentProjectsList.get(i).ll.findViewById(R.id.card_view);

            Shared.CurrentProjectsList.get(i).title=(TextView)Shared.CurrentProjectsList.get(i).ll.findViewById(R.id.project_title);
            Shared.CurrentProjectsList.get(i).title.setText(Shared.CurrentProjectsList.get(i).ProjectName);

            Shared.CurrentProjectsList.get(i).gitBtn=(Button) Shared.CurrentProjectsList.get(i).ll.findViewById(R.id.github_icon);

            Shared.CurrentProjectsList.get(i).desctext=(TextView)Shared.CurrentProjectsList.get(i).ll.findViewById(R.id.project_description);
            Shared.CurrentProjectsList.get(i).desctext.setText(Shared.CurrentProjectsList.get(i).Members);

            Shared.CurrentProjectsList.get(i).title_members=(TextView)Shared.CurrentProjectsList.get(i).ll.findViewById(R.id.title_members);
            Shared.CurrentProjectsList.get(i).members=(TextView)Shared.CurrentProjectsList.get(i).ll.findViewById(R.id.members);

            Shared.CurrentProjectsList.get(i).members.setText(Shared.CurrentProjectsList.get(i).desc);

            Shared.CurrentProjectsList.get(i).clicked=false;

            Shared.CurrentProjectsList.get(i).cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!Shared.CurrentProjectsList.get(i).clicked) {
                        Shared.CurrentProjectsList.get(i).clicked=true;
                        TransitionManager.beginDelayedTransition(Shared.CurrentProjectsList.get(i).cv);
                        Shared.CurrentProjectsList.get(i).title_members.setVisibility(View.VISIBLE);
                        Shared.CurrentProjectsList.get(i).members.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        Shared.CurrentProjectsList.get(i).clicked=false;
                        TransitionManager.beginDelayedTransition(Shared.CurrentProjectsList.get(i).cv);
                        Shared.CurrentProjectsList.get(i).title_members.setVisibility(View.GONE);
                        Shared.CurrentProjectsList.get(i).members.setVisibility(View.GONE);
                    }
                }
            });
            Shared.CurrentProjectsList.get(i).gitBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(Shared.CurrentProjectsList.get(i).GitLink)));
                }
            });
            ll.addView(Shared.CurrentProjectsList.get(i).ll);
        }
    }
}
