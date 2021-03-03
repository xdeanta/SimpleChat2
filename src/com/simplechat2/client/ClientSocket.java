package com.simplechat2.client;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.ServerSocket;

public class ClientSocket {
    private Socket socket;
    private InetSocketAddress addr;
    private InputStream is;
    private OutputStream os;

    public ClientSocket(String ip, int port){
        addr=new InetSocketAddress(ip,port);
        try {
            socket = new Socket();
            socket.connect(addr);
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

    /*public DataInputStream getDataInputStream(){
        return new DataInputStream(is);
    }
    public DataOutputStream getDataOutputStream(){
        return new DataOutputStream(os);
    }*/

    public void closeSocket(){
        try{
            socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void getIO(){
        try {
            is = socket.getInputStream();
            os = socket.getOutputStream();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
