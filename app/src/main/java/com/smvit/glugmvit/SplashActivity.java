package com.smvit.glugmvit;

import android.app.Activity;
import android.app.Dialog;
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
import android.widget.EditText;

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
    EditText usre;
    EditText passe;
    LoginTask loginTask;
    AuthTask authTask;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        b1=(Button)findViewById(R.id.adminBtn);
        b2=(Button)findViewById(R.id.memberBtn);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog ad=new AlertDialog.Builder(SplashActivity.this).setView(R.layout.auth_dialog).setPositiveButton("Login", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Dialog d=(Dialog)dialog;
                        usre=(EditText)d.findViewById(R.id.username);
                        passe=(EditText)d.findViewById(R.id.password);
                        String usr=usre.getText().toString();
                        String pass=passe.getText().toString();
                        authTask=new AuthTask();
                        authTask.execute(usr,pass);
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create();
                ad.show();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shared.ADMIN_ACCESS=false;
                loginTask=new LoginTask();
                loginTask.execute();
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        b1.startAnimation(AnimationUtils.loadAnimation(SplashActivity.this,R.anim.fade_in));
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
            MongoClientURI uri = new MongoClientURI("mongodb://random:random@ds145273.mlab.com:45273/glugmvitappdb");
            Shared.client = new MongoClient(uri);

            Shared.db = Shared.client.getDatabase("glugmvitappdb");
            Shared.TestCollection = Shared.db.getCollection("TestCollection");
            Shared.UECollection = Shared.db.getCollection("UpcomingEventsCollection");
            Shared.CPCollection = Shared.db.getCollection("CurrentProjectsCollection");
            return null;
        }
        @Override
        protected void onPostExecute(Void v)
        {
            pd.dismiss();
        }
    }

    public class AuthTask extends AsyncTask<String,Void,Boolean>
    {
        ProgressDialog pd;
        String tmp;

        @Override
        protected void onPreExecute() {
            pd = new ProgressDialog(SplashActivity.this);
            pd.setTitle("Connecting...");
            pd.setMessage("Please Wait");
            pd.show();
        }
        @Override
        protected Boolean doInBackground(String... params)
        {
            ArrayList<MongoCredential> list=new ArrayList<>();
            MongoClientURI uri = new MongoClientURI("mongodb://random:random@ds145273.mlab.com:45273/glugmvitappdb");
            list.add(uri.getCredentials());
            Shared.client = new MongoClient(new ServerAddress("ds145273.mlab.com",45273),list);

            Shared.db = Shared.client.getDatabase("glugmvitappdb");
            Shared.UsersCollection = Shared.db.getCollection("Users");
            FindIterable fi=Shared.UsersCollection.find();

                Document auth=(Document)fi.iterator().next();
                tmp=(String)auth.get(params[0]);

                if(tmp!=null && tmp.equals(params[1]))
                {
                    Log.e("Test","Connected");
                    uri = new MongoClientURI("mongodb://"+params[0]+":"+params[1]+"@ds145273.mlab.com:45273/glugmvitappdb");
                    Shared.client=new MongoClient(uri);
                    Shared.db=Shared.client.getDatabase("glugmvitappdb");
                    Shared.TestCollection = Shared.db.getCollection("TestCollection");
                    Shared.UECollection = Shared.db.getCollection("UpcomingEventsCollection");
                    Shared.CPCollection = Shared.db.getCollection("CurrentProjectsCollection");
                    return true;
                }

            return false;
        }

        @Override
        protected void onPostExecute(Boolean b)
        {
            pd.dismiss();
            if(b)
            {
                Shared.ADMIN_ACCESS=true;
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            }
            else
            {
                Shared.ADMIN_ACCESS=false;
                AlertDialog ad=new AlertDialog.Builder(SplashActivity.this).setMessage("Authorization Failed").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
                ad.show();
            }
        }
    }
    @Override
    public void onPause()
    {
        super.onPause();
        if(loginTask!=null)
            loginTask.cancel(true);
        if(authTask!=null)
            authTask.cancel(true);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        if(loginTask!=null)
            loginTask.cancel(true);
        if(authTask!=null)
            authTask.cancel(true);
    }
}
