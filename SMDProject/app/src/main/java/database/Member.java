package database;

import java.io.Serializable;

public class Member implements Serializable{
    private User user;
    private String gid;
    
    public Member(){
        
    }

    public Member(User u,String g){
        user=u;
        gid=g;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User User) {
        this.user = User;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }
}
