package com.smdproject.smdproject;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import database.Group;
import database.MyDatabase;

public class GroupGetAsyncTask extends AsyncTask<Void,Void,ArrayList<Group>> {
    MainActivity c;

    GroupGetAsyncTask(MainActivity con) {
        c = con;
    }

    @Override
    protected ArrayList<Group> doInBackground(Void... voids) {
        MyDatabase myDb = MyDatabase.getAppDatabase(c);
        return (ArrayList<Group>) myDb.groupDao().getAll();
    }

    @Override
    protected void onPostExecute(ArrayList<Group> gg){
        super.onPostExecute(gg);
        c.setJoined(gg);
    }
}
