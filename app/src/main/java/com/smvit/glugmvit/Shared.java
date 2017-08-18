package com.smvit.glugmvit;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.List;

/**
 * Created by susmit on 16/8/17.
 */

public class Shared
{
    static MongoClient client;
    static MongoDatabase db;
    static MongoCredential credentials;
    static MongoCollection UECollection;
    static MongoCollection CPCollection;
    static MongoCollection TestCollection;
    static List<DbObject> DbObjs;
    static List<UpcomingEventsDataObject> UpcomingEventsList;
    static List<CurrentProjectsDataObject> CurrentProjectsList;
    static Context appContext;
    static int tables_in_collection;
    static List<Fragment> DrawerFragments;
}
