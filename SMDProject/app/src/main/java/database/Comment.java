package database;

import java.io.Serializable;

/**
 * Created by Ahmad on 28-Mar-18.
 */

public class Comment implements Serializable{
    private String cid;
    private String postid;
    private String userid;
    private String text;
    private String stamp;

    public Comment(String text, String commentator, String stamp, String p) {
        this.text = text;
        this.userid = commentator;
        this.postid = p;
        this.stamp = stamp;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getStamp() {
        return stamp;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }
}