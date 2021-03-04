package com.simplechat2.server.controller;

import com.simplechat2.common.Client;
import com.simplechat2.common.IOStream;
import com.simplechat2.common.Message;
import com.simplechat2.server.model.Channel;
import com.simplechat2.server.model.SClient;

import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends Thread{
    private SClient client;
    private Channel ch;
    private IOStream handler;
    private Socket socket;
    private boolean disconnect;

    public ClientHandler (Client c, Socket s, Channel ch){
        this.ch=ch;
        socket=s;
        try {
            handler = new IOStream(s.getInputStream(),s.getOutputStream());
        }catch (IOException e){
            e.printStackTrace();
        }
        client = new SClient(c.getUsername(),c.getPassword(), handler);
        disconnect=false;
    }

    public void sendMessage(){
        Message m;
        m=(Message) handler.ReceiveObject();
        ch.addMessage(m);
        ch.broadcastMessage();
        if(m.getMessage().equals("bye")){
            disconnect=true;
        }
    }

    public void receiveMessage(Message m){
        handler.sendObject(m);
    }

    public void run(){
        while(!disconnect){
            sendMessage();
        }
        ch.removeUser(this);
        handler.closeStream();
        try {
            socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public String toString(){
        return "Cliente: " + client.getUsername();
    }

    public SClient getClient() {
        return client;
    }
}
