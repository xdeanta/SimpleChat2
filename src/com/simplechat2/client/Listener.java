package com.simplechat2.client;

import com.simplechat2.common.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Listener extends Thread{
    private ObjectInputStream ois;
    private Socket s;

    public Listener(ObjectInputStream ois, Socket s){
        this.ois=ois;
        this.s=s;
    }

    public void run(){
        Message m;
        while(true){
            try {
                m = null;
                m = (Message) ois.readObject();
                System.out.println(m);
            }catch (IOException e){
                e.printStackTrace();
                currentThread().interrupt();
            }catch (ClassNotFoundException e2){
                e2.printStackTrace();
                currentThread().interrupt();
            }
        }
    }
}
