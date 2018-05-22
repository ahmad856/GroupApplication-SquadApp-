package database;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;

@Dao
public interface MessageDao {
    @Query("SELECT * FROM message where g_id LIKE  :groupId")
    List<Message> getAll(String groupId);

    @Query("SELECT COUNT(*) from message")
    int count();

    @Insert
    void insertAll(Message... messages);

    @Delete
    void delete(Message message);
}
