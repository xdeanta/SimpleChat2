package com.simplechat2.client.controller;

import com.simplechat2.common.SocketHandler;

import java.io.IOException;
import java.net.Socket;

public class ClientSocket extends SocketHandler {
    private static ClientSocket instance;

    private ClientSocket(String ip, int port){
        socket = new Socket();
        getAddress(ip,port);
        try {
            socket.connect(addr);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static ClientSocket getSocket(String ip, int port){
        if(instance == null){
            instance = new ClientSocket(ip,port);
        }
        return instance;
    }

    public void closeSocket(){
        try{
            socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
