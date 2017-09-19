package com.smvit.glugmvit;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;

import org.bson.Document;

import java.util.ArrayList;

/**
 * Created by Abhijeet Singh on 18-Aug-17. For fixing Splash Screen load time.
 * Modified by Susmit on 19/08/17
 */

public class SplashActivity extends Activity {
    Button b1;
    Button b2;
    LoginTask loginTask;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        b2=(Button)findViewById(R.id.memberBtn);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginTask=new LoginTask();
                loginTask.execute();
            }
        });
        b2.startAnimation(AnimationUtils.loadAnimation(SplashActivity.this,R.anim.fade_in));
    }
    public class LoginTask extends AsyncTask<Void,Void,Void>
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
        protected Void doInBackground(Void... params)
        {
            //MongoClientURI uri = new MongoClientURI("mongodb://random:random@ds145273.mlab.com:45273/glugmvitappdb");
            MongoClientURI uri = new MongoClientURI("mongodb://mobile-user:password@ds139267.mlab.com:39267/glug");
            Shared.client = new MongoClient(uri);

            //Shared.db = Shared.client.getDatabase("glugmvitappdb");
            Shared.db=Shared.client.getDatabase("glug");
            //Shared.TestCollection = Shared.db.getCollection("TestCollection");
            //Shared.TestCollection=Shared.db.getCollection("events");
            //Shared.UECollection = Shared.db.getCollection("UpcomingEventsCollection");
            Shared.UECollection = Shared.db.getCollection("events");
            Shared.CPCollection = Shared.db.getCollection("projects");
            return null;
        }
        @Override
        protected void onPostExecute(Void v)
        {
            pd.dismiss();
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onPause()
    {
        super.onPause();
        if(loginTask!=null)
            loginTask.cancel(true);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        if(loginTask!=null)
            loginTask.cancel(true);
    }
}
