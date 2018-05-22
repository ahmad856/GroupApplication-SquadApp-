package database;

import java.io.Serializable;

/**
 * Created by Ahmad on 28-Mar-18.
 */


public class Event implements Serializable{
    private String eid;
    private String gid;
    private String name;
    private String description;
    private String stamp;
    private String location;
    private String address;

    public Event(){

    }

    public Event(String id,String ad, String name,String desc, String stamp, String location) {
        this.name = name;
        this.stamp = stamp;
        this.address=ad;
        this.location = location;
        this.gid = id;
        this.description = desc;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStamp() {
        return stamp;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}