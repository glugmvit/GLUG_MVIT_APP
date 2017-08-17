package com.smvit.glugmvit;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by susmit
 */

public class MainActivity extends AppCompatActivity {

    DrawerLayout dl;
    ListView lv;
    List<String> DrawerList;
    ContentFragment cf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        View v=getSupportActionBar().getCustomView();

        ImageButton ib=(ImageButton)v.findViewById(R.id.DrawerButton);

        getSupportActionBar().setDisplayShowTitleEnabled(true);
        dl=(DrawerLayout)findViewById(R.id.Drawer);
        lv=(ListView)findViewById(R.id.DrawerView);

        Shared.DbObjs=new ArrayList<>();

        Shared.appContext=getApplicationContext();

        DrawerList=new ArrayList<>();
        DrawerList.add("Overview");
        DrawerList.add("About");
        DrawerList.add("Members");
        lv.setAdapter(new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1,DrawerList));
        cf=new ContentFragment();
        lv.setAdapter(new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1,DrawerList));
        Bundle b=new Bundle();
        b.putInt("Num",0);
        cf.setArguments(b);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment,cf).commit();

        new AsyncTask<Void,Void,Void>()
        {
            ProgressDialog pd;
            @Override
            protected void onPreExecute()
            {
                pd=new ProgressDialog(MainActivity.this);
                pd.setTitle("Connecting...");
                pd.setMessage("Please Wait");
                pd.show();
            }
            @Override
            protected Void doInBackground(Void... params) {
                MongoClientURI uri=new MongoClientURI("mongodb://Susmit:abcd1234@ds145273.mlab.com:45273/glugmvitappdb");
                Shared.client=new MongoClient(uri);
                //credentials=MongoCredential.createCredential("testUser","testDb",new String("qwerty").toCharArray());
                Shared.db=Shared.client.getDatabase("glugmvitappdb");
                Shared.coll=Shared.db.getCollection("TestCollection");
                return null;
            }
            @Override
            protected void onPostExecute(Void v)
            {
                pd.dismiss();
            }
        }.execute();

        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dl.openDrawer(Gravity.START,true);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cf=new ContentFragment();
                Bundle b=new Bundle();
                b.putInt("Num",position);
                cf.setArguments(b);
                getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment,cf).commit();
                dl.closeDrawer(Gravity.START,true);
            }
        });
    }
}
