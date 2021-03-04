package com.simplechat2.server.model;

import com.simplechat2.common.Client;
import com.simplechat2.common.Message;
import com.simplechat2.server.controller.ClientHandler;

import java.util.ArrayList;

public class Channel {
    private static final String name = "#main";
    private ArrayList<Message> msgs;
    private ArrayList<SClient> clients;

    public Channel(){
        msgs = new ArrayList<>();
        clients = new ArrayList<>();
    }

    public synchronized void joinUser(SClient ch){
        clients.add(ch);
    }

    public void broadcastMessage(){
        SClient ch;
        for(int i = 0; i < clients.size(); i++){
            ch=clients.get(i);
            ch.receiveMessage(msgs.get(msgs.size()-1));
        }
    }

    public synchronized void removeUser(SClient ch){
        clients.remove(ch);
    }

    public synchronized void addMessage(Message m){
        msgs.add(m);
        System.out.println(msgs);
    }
}
