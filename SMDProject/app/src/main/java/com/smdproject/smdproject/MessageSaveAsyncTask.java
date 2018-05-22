package com.smdproject.smdproject;

import android.content.Context;
import android.os.AsyncTask;
import database.Message;
import database.MyDatabase;

public class MessageSaveAsyncTask extends AsyncTask<Message,Void,Void> {
    Context c;

    MessageSaveAsyncTask(Context con) {
        c = con;
    }

    @Override
    protected Void doInBackground(Message... messages) {
        MyDatabase myDb = MyDatabase.getAppDatabase(c);
        myDb.messageDao().insertAll(messages);
        return null;
    }
}
