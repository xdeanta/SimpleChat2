package com.simplechat2.client;

import java.io.IOException;

public class Listener extends Thread{
    private ClientSocket csocket;
    public Listener(ClientSocket socket){
        csocket=socket;
    }
    public void run(){
        String mensaje;
        try{
            while(true){
                mensaje = csocket.getDataInputStream().readUTF();
                if(mensaje.equals("bye")){
                    break;
                }
                System.out.println(mensaje);
            }
        }catch (IOException e){
            e.printStackTrace();
            this.interrupt();
        }
    }
}
