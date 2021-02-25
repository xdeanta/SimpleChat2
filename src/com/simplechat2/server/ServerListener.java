package com.simplechat2.server;

import java.io.IOException;
import java.net.SocketException;

public class ServerListener extends Thread{
    private ServiceSocket socket;
    private Channel ch;

    public ServerListener(ServiceSocket s, Channel ch){
        socket=s;
        this.ch=ch;
    }

    public void joinUser(User u){
        ch.AddUser(u);
    }

    public void run(){
        User u;
        UserHandler uh;
        String usr;
        while(true){
            System.out.println("Escuchando");
            socket.acceptConnection();
            System.out.println("Conexion aceptada");
            try {
                System.out.println("esperando nombre");
                usr=socket.getDataInputStream().readUTF();
                System.out.println("Usuario: " +usr);
                u = new User(usr, socket);
                joinUser(u);
                uh=new UserHandler(u, ch,socket);
                uh.start();
                usr=null;
                uh=null;
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        //socket.closeSocket();
    }
}
