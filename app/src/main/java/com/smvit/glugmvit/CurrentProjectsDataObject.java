package com.smvit.glugmvit;

/**
 * Created by susmit on 18/8/17.
 */

public class CurrentProjectsDataObject
{
    String ProjectName;
    String Collaborator;
    String Members;
    String desc;
    String GitLink;
    String imgLink;
    CurrentProjectsDataObject(String... params)
    {
        ProjectName=params[0];
        Collaborator=params[1];
        Members=params[2];
        desc=params[3];
        GitLink=params[4];
        imgLink=params[5];
    }
    CurrentProjectsDataObject(String p,String c,String m,String d,String g, String i)
    {
        ProjectName=p;
        Collaborator=c;
        Members=m;
        desc=d;
        GitLink=g;
        imgLink=i;
    }
}
