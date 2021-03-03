package com.simplechat2.server.controller;

import com.simplechat2.common.Client;
import com.simplechat2.common.IOStream;
import com.simplechat2.server.model.Channel;

public class ServerListener extends Thread{
    private IOStream handler;
    private ServiceSocket ss;
    private Channel ch;

    public ServerListener(String ip, int port, Channel ch){
        ss = ServiceSocket.getServiceSocket(ip,port);
        this.ch=ch;
    }

    public void run(){
        Client c;
        ClientAuth ca = new ClientAuth();
        ClientHandler clh;
        boolean connected = false;
        while (true){
            System.out.println("Escuchando...");
            ss.acceptConnection();
            handler=IOStream.createIO(ss.getIs(),ss.getOs());
            c=(Client) handler.ReceiveObject();
            connected=ca.validateUser(c);
            if(connected){
                System.out.println("Cliente conectado");
                clh = new ClientHandler(c,handler,ch);
                ch.joinUser(clh);
                clh.start();
            }else{
                System.out.println("Conexion rechazada");
            }
            handler.sendObject(connected);
        }
    }
}
