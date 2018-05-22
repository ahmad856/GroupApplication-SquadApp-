package com.smdproject.smdproject;

import android.os.AsyncTask;
import java.util.ArrayList;
import database.Message;
import database.MyDatabase;

public class MessageGetAsyncTask extends AsyncTask<String,Void,ArrayList<Message>> {
    MainActivity c;
    String g;
    MessageGetAsyncTask(MainActivity con) {
        c = con;
    }

    @Override
    protected ArrayList<Message> doInBackground(String... strings) {
        MyDatabase myDb = MyDatabase.getAppDatabase(c);
        g=strings[0];
        return (ArrayList<Message>) myDb.messageDao().getAll(strings[0]);
    }

    @Override
    protected void onPostExecute(ArrayList<Message> gg){
        super.onPostExecute(gg);

        if(c.getCurrentGroup().getGroupId().equals(g)) {
            c.getCurrentGroup().getMessages().clear();
            for(Message g:gg)
                c.getCurrentGroup().getMessages().add(g);
        }
        c.update();
    }
}
