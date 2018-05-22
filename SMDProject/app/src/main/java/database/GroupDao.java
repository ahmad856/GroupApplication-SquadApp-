package database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;

@Dao
public interface GroupDao {
    @Query("SELECT * FROM groups")
    List<Group> getAll();

    @Insert
    void insertAll(Group... groups);

    @Delete
    void delete(Group group);
}