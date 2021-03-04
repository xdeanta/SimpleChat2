package com.simplechat2.server;

import com.simplechat2.common.Message;
import com.simplechat2.common.TUser;

import java.util.ArrayList;

public class Channel {
    private String name;
    private ArrayList<TUser> users;
    private ArrayList<Message> msgs;
    private ArrayList<ClientHandler> tclients;

    public Channel(ArrayList<ClientHandler> tclients) {
        name="#main";
        users = new ArrayList<>();
        msgs = new ArrayList<>();
        this.tclients=tclients;
    }

    public synchronized void addUser(TUser u){
        users.add(u);
    }

    public synchronized void removeUser(TUser u){
        users.remove(u);
    }

    public synchronized void addMessage(Message m){
        msgs.add(m);
    }

    public synchronized void broadcastMessage(){
        for(int i = 0; i < users.size(); i++){
            tclients.get(i).sendMessage(msgs.get(msgs.size()-1));
        }
    }
}
