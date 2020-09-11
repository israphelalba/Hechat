package com.example.hechat;
import java.io.Serializable;

public class User implements Serializable{
private String id, mail;
public User(){

}

    public User(String id, String mail) {
        this.id = id;
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }

    public String getId(){
    return id;
}

    public void setId(String id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
