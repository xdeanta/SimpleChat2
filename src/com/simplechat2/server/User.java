package com.simplechat2.server;


import java.io.*;

public class User {
    private String username;
    private ObjectInputStream cObjInput;
    private ObjectOutputStream cObjOutput;
    private DataInputStream cDataInput;
    private DataOutputStream cDataOutput;

    public User(String usr, ServiceSocket s){
        username=usr;
        cObjInput=s.getObjInputStream();
        cObjOutput=s.getObjOutputStream();
        cDataInput=s.getDataInputStream();
        cDataOutput=s.getDataOutputStream();
    }

    public void sendObject(Object o){
        try {
            cObjOutput.writeObject(o);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public Object receiveObject(){
        Object obj;
        try {
            obj=cObjInput.readObject();
        }catch (IOException e){
            e.printStackTrace();
            obj=null;
        }catch (ClassNotFoundException e2){
            e2.printStackTrace();
            obj=null;
        }
        return obj;
    }

    public void sendString(String str){
        try {
            cDataOutput.writeUTF(str);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public String receiveString(){
        String str;
        try {
            str=cDataInput.readUTF();
        }catch (IOException e){
            e.printStackTrace();
            str=null;
        }
        return str;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
