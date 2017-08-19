package com.smvit.glugmvit;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCommandException;
import com.mongodb.MongoCredential;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;
import com.mongodb.event.ServerClosedEvent;
import com.mongodb.event.ServerDescriptionChangedEvent;
import com.mongodb.event.ServerListener;
import com.mongodb.event.ServerOpeningEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Abhijeet Singh on 18-Aug-17. For fixing Splash Screen load time.
 */

public class SplashActivity extends Activity {
    Button b1;
    Button b2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        setContentView(R.layout.activity_login_screen);
        b1=(Button)findViewById(R.id.adminBtn);
        b2=(Button)findViewById(R.id.memberBtn);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shared.ADMIN_ACCESS=true;
                new LoginTask().execute();
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shared.ADMIN_ACCESS=false;
                new LoginTask().execute();
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        b1.startAnimation(AnimationUtils.loadAnimation(SplashActivity.this,R.anim.fade_in));
        b2.startAnimation(AnimationUtils.loadAnimation(SplashActivity.this,R.anim.fade_in));
    }
    public class LoginTask extends AsyncTask<Void,Void,Boolean>
    {
        ProgressDialog pd;
        @Override
        protected void onPreExecute() {
            pd = new ProgressDialog(SplashActivity.this);
            pd.setTitle("Connecting...");
            pd.setMessage("Please Wait");
            pd.show();
        }
        @Override
        protected Boolean doInBackground(Void... params)
        {
            ArrayList<MongoCredential> list=new ArrayList<>();
            MongoClientURI uri = new MongoClientURI("mongodb://random:random@ds145273.mlab.com:45273/glugmvitappdb");
            list.add(uri.getCredentials());
            Shared.client = new MongoClient(new ServerAddress("ds145273.mlab.com",45273),list);

            Shared.db = Shared.client.getDatabase("glugmvitappdb");
            Shared.TestCollection = Shared.db.getCollection("TestCollection");
            Shared.UECollection = Shared.db.getCollection("UpcomingEventsCollection");
            Shared.CPCollection = Shared.db.getCollection("CurrentProjectsCollection");
            return true;
        }
        @Override
        protected void onPostExecute(Boolean b)
        {
            pd.dismiss();
        }
    }
}
