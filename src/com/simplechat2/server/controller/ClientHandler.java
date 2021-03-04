package com.simplechat2.server.controller;

import com.simplechat2.common.Client;
import com.simplechat2.common.IOStream;
import com.simplechat2.common.Message;
import com.simplechat2.server.model.Channel;
import com.simplechat2.server.model.SClient;

public class ClientHandler extends Thread{
    private SClient client;
    private Channel ch;
    private IOStream handler;
    private boolean disconnect;

    public ClientHandler (Client c, IOStream ios, Channel ch){
        client = new SClient(c.getUsername(),c.getPassword());
        this.ch=ch;
        handler = ios;
        disconnect=false;
    }

    public void sendMessage(){
        Message m;
        m=(Message) handler.ReceiveObject();
        if(m.getMessage().equals("bye")){
            disconnect=true;
        }
        ch.addMessage(m);
        ch.broadcastMessage();
    }

    public void receiveMessage(Message m){
        handler.sendObject(m);
    }

    public void run(){
        while(!disconnect){
            sendMessage();
        }
        ch.removeUser(this);
        //handler.closeStream();
    }

    public String toString(){
        return "Cliente: " + client.getUsername();
    }
}
