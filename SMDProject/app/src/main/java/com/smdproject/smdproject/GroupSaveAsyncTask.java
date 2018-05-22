package com.smdproject.smdproject;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;

import database.Group;
import database.MyDatabase;

public class GroupSaveAsyncTask extends AsyncTask <Group,Void,Void>{
    Context c;

    GroupSaveAsyncTask(Context con) {
        c = con;
    }

    @Override
    protected Void doInBackground(Group... groups) {
        MyDatabase myDb = MyDatabase.getAppDatabase(c);

        ArrayList<Group> gg= (ArrayList<Group>) myDb.groupDao().getAll();
        for(int i=0;i<gg.size();i++){
            if(gg==null)return null;
            if(gg.get(i)==null)return null;
            if(gg.get(i).getGroupId().equals(groups[0].getGroupId()))
                return null;
        }

        myDb.groupDao().insertAll(groups);
        return null;
    }
}