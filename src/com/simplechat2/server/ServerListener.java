package com.simplechat2.server;

import java.io.IOException;

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
        while(true){
            socket.acceptConnection();
            try {
                u = new User(socket.getDataInputStream().readUTF(), socket);
                joinUser(u);
                uh=new UserHandler(u, ch,socket);
            }catch (IOException e){
                e.printStackTrace();
                this.interrupt();
            }
        }
        //socket.closeSocket();
    }
}
