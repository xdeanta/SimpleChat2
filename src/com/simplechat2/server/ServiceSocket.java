package com.simplechat2.server;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.ServerSocket;

public class ServiceSocket {
    private ServerSocket sSocket;
    private Socket socket;
    private InetSocketAddress addr;
    private InputStream is;
    private OutputStream os;

    public ServiceSocket(String ip, int port){
        addr=new InetSocketAddress(ip,port);
        try {
            sSocket = new ServerSocket();
            sSocket.bind(addr);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void acceptConnection(){
        try {
            socket = sSocket.accept();
            is=socket.getInputStream();
            os=socket.getOutputStream();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public ObjectInputStream getObjInputStream(){
        try{
            return new ObjectInputStream(is);
        }catch (IOException e){
            return null;
        }
    }
    public ObjectOutputStream getObjOutputStream(){
        try{
            return new ObjectOutputStream(os);
        }catch (IOException e){
            return null;
        }
    }

    public DataInputStream getDataInputStream(){
        return new DataInputStream(is);
    }
    public DataOutputStream getDataOutputStream(){
        return new DataOutputStream(os);
    }

    public void closeSocket(){
        try {
            sSocket.close();
            socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void closeCSocket(){
        try {
            socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
