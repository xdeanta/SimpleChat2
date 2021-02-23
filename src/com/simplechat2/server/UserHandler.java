package com.simplechat2.server;

public class UserHandler extends Thread{
    User u;
    Channel ch;
    ServiceSocket socket;

    public UserHandler(User u, Channel ch, ServiceSocket socket){
        this.u=u;
        this.ch=ch;
        this.socket=socket;
    }
    public boolean sendMessage(){
        boolean disconnect=false;
        String str;
        str=u.receiveString();
        if(str.equals("bye")){
            disconnect=true;
        }
        ch.postMessage(str);
        ch.broadcastMessage();
        return disconnect;
    }

    public void run(){
        while(true){
            if(sendMessage()){
                ch.removeUser(u);
                socket.closeSocket();
                break;
            }
        }
    }
}
