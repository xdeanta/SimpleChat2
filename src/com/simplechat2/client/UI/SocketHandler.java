package com.simplechat2.client.UI;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketHandler {
    private Socket sock;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    public SocketHandler(Socket sock){
        this.sock=sock;
        try{
            oos = new ObjectOutputStream(this.sock.getOutputStream());
            ois = new ObjectInputStream(this.sock.getInputStream());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void sendObject(Object o){
        try{
            oos.writeObject(o);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public Object receiveObject(){
        Object ret = null;
        try {
            ret= ois.readObject();
        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e2){
            e2.printStackTrace();
        }
        return ret;
    }

    public void closeConnection(){
        try{
            oos.close();
            ois.close();
            sock.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean receiveBool(){
        boolean bool=false;
        try {
            bool = ois.readBoolean();
        }catch (IOException e){
            e.printStackTrace();
        }
        return bool;
    }
}
