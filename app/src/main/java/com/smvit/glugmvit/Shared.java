package com.smvit.glugmvit;

import android.content.Context;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;

/**
 * Created by susmit on 16/8/17.
 */

public class Shared
{
    static MongoClient client;
    static MongoDatabase db;
    static MongoCollection UECollection;
    static MongoCollection CPCollection;
    static MongoCollection TestCollection;
    static MongoCollection UsersCollection;
    static ArrayList<DbObject> DbObjs;
    static ArrayList<UpcomingEventsDataObject> UpcomingEventsList;
    static ArrayList<CurrentProjectsDataObject> CurrentProjectsList;
}
