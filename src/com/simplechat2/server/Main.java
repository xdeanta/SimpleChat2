package com.simplechat2.server;

import com.simplechat2.server.controller.ServerListener;
import com.simplechat2.server.controller.ServiceSocket;
import com.simplechat2.server.model.Channel;

public class Main {
    public static void main(String[] args){
        ServerListener sl;
        Channel ch = new Channel();
        sl = new ServerListener("192.168.56.2",7500,ch);
        sl.start();
        try{
            sl.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
