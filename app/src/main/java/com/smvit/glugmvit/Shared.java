package com.smvit.glugmvit;

import android.content.Context;

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
    static MongoCollection coll;
    static List<DbObject> DbObjs;
    static Context appContext;
    static int tables_in_collection;
}
