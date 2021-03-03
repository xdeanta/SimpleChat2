package com.simplechat2.common;

import java.io.Serializable;

public class Message implements Serializable {
    protected String username;
    protected String message;

    public Message(Client c, String msg){
        username=c.getUsername();
        message=msg;
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString(){
        return "<" + username +">: " + message;
    }
}
