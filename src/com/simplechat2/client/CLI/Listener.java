package com.simplechat2.client.CLI;

import com.simplechat2.common.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Listener extends Thread{
    private ObjectInputStream ois;
    private Socket s;
    private String usr;
    private boolean exit;

    public Listener(ObjectInputStream ois, Socket s, String usr){
        this.ois=ois;
        this.s=s;
        this.usr=usr;
        exit=false;
    }

    public void setExit(boolean e){
        exit=e;
    }

    public void run(){
        Message m;
        while(!exit){
            try {
                m = null;
                m = (Message) ois.readObject();
                System.out.println(m);
                if(m.getMessage().matches("^"+usr))
                    break;
            }catch (IOException e){
                e.printStackTrace();
                currentThread().interrupt();
            }catch (ClassNotFoundException e2){
                e2.printStackTrace();
                currentThread().interrupt();
            }
        }
        try {
            s.close();
            ois.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
