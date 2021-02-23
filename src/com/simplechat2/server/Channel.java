package com.simplechat2.server;

import java.util.ArrayList;

public class Channel {
    private String name;
    private ArrayList<User> users;
    private ArrayList<String> messages;

    public Channel(){
        name="#main";
        users=new ArrayList<>();
        messages=new ArrayList<>();
    }

    public synchronized void postMessage(String msg){
        messages.add(msg);
    }

    public void broadcastMessage(){
        User u;
        for(int i=0;i<users.size();i++){
            u=users.get(i);
            u.sendString(messages.get(messages.size()-1));
        }
    }

    public synchronized void removeUser(User u){
        users.remove(u);
    }

    public synchronized void AddUser(User u){
        users.add(u);
    }
}
