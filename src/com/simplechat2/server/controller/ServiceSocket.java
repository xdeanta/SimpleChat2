package com.simplechat2.server.controller;

import com.simplechat2.common.SocketHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

public class ServiceSocket extends SocketHandler {
    private static ServiceSocket ss;

    private ServiceSocket(String ip, int port){
        try {
            sSocket = new ServerSocket();
            addr = new InetSocketAddress(ip,port);
            //getAddress(ip, port);
            sSocket.bind(addr);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static ServiceSocket getServiceSocket(String ip, int port){
        if(ss == null){
            ss = new ServiceSocket(ip,port);
        }
        return ss;
    }

    public void acceptConnection(){
        try {
            socket = sSocket.accept();
            is = socket.getInputStream();
            os = socket.getOutputStream();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void closeSocket(){
        try{
            socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void closeServiceSocket(){
        try{
            sSocket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
