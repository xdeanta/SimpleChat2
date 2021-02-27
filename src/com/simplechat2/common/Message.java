package com.simplechat2.common;

import java.io.Serializable;

public class Message implements Serializable {
    private String user;
    private String msg;

    public Message(String user, String msg) {
        this.user = user;
        this.msg = msg;
    }
    @Override
    public String toString(){
        return "<" + user + ">: " + msg + "\n";
    }
}
