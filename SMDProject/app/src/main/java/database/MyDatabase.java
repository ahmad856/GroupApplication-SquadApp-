package database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Ahmad on 28-Mar-18.
 */

@Database(entities = {Message.class,Group.class},version = 1)
public abstract class MyDatabase extends RoomDatabase {
    private static MyDatabase INSTANCE;

    public abstract MessageDao messageDao();
    public abstract GroupDao groupDao();

    public static MyDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), MyDatabase.class, "user-database").build();
        }
        return INSTANCE;
    }
    public static void destroyInstance() {
        INSTANCE = null;
    }
}