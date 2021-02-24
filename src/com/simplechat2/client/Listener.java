package com.simplechat2.client;

import java.io.IOException;

public class Listener extends Thread{
    private ClientSocket csocket;
    public Listener(ClientSocket socket){
        csocket=socket;
    }
    public void run(){
        try{
            while(true){
                System.out.println(csocket.getDataInputStream().readUTF());
            }
        }catch (IOException e){
            e.printStackTrace();
            this.interrupt();
        }
    }
}
