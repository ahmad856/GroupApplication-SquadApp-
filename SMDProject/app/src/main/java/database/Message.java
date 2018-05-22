package database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ahmad on 28-Mar-18.
 */

@Entity(tableName = "message")
public class Message implements Serializable{
    @PrimaryKey(autoGenerate = true)
    private int mid;

    @ColumnInfo(name = "g_id")
    private String gid;

    @ColumnInfo(name = "u_id")
    private String senderid;

    @ColumnInfo(name = "text")
    private String text;

    @ColumnInfo(name = "dtime")
    @TypeConverters({TimestampConverter.class})
    private Date stamp;

    @Ignore
    private Group group;
    @Ignore
    private User sender;

    public Message(){

    }

    @Ignore
    public Message(Group group, User sender, String text, Date stamp)  {
        this.group = group;
        this.sender = sender;
        this.text = text;
        this.stamp = stamp;
        this.gid = group.getGroupId();
        this.senderid = sender.getUid();
    }

    public int getMid() {
        return mid;
    }

    public String getGid() {
        return gid;
    }

    public String getSenderid() {
        return senderid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public void setSenderid(String senderid) {
        this.senderid = senderid;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getStamp() {
        return stamp;
    }

    public void setStamp(Date stamp) {
        this.stamp = stamp;
    }
}
