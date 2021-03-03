package com.simplechat2.server;

import com.simplechat2.server.controller.ServerListener;
import com.simplechat2.server.controller.ServiceSocket;

public class Main {
    public static void main(String[] args){
        ServerListener sl;
        sl = new ServerListener("192.168.56.2",7500);
        sl.start();
        try{
            sl.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
