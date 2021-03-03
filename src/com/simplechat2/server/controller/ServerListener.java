package com.simplechat2.server.controller;

import com.simplechat2.common.Client;
import com.simplechat2.common.IOStream;
import com.simplechat2.server.model.sClient;

public class ServerListener extends Thread{
    private IOStream handler;
    private ServiceSocket ss;

    public ServerListener(String ip, int port){
        ss = ServiceSocket.getServiceSocket(ip,port);
    }

    public void run(){
        Client c;
        ClientAuth ca = new ClientAuth();
        boolean connected = false;
        while (true){
            System.out.println("Escuchando...");
            ss.acceptConnection();
            handler=IOStream.createIO(ss.getIs(),ss.getOs());
            c=(Client) handler.ReceiveObject();
            connected=ca.validateUser(c);
            if(connected){
                System.out.println("Cliente conectado");
            }else{
                System.out.println("Conexion rechazada");
            }
            handler.sendObject(connected);
        }
    }
}
