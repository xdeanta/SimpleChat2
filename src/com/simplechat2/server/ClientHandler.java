package com.simplechat2.server;

import com.simplechat2.common.Message;
import com.simplechat2.common.TUser;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler extends Thread{
    private final ObjectOutputStream oos;
    private final ObjectInputStream ios;
    private final Socket s;
    private final Channel ch;

    public ClientHandler (Socket s, ObjectOutputStream oos, ObjectInputStream ios, Channel ch){
        this.s=s;
        this.ios=ios;
        this.oos=oos;
        this.ch=ch;
    }

    public void sendMessage(Message m){
        try {
            oos.writeObject(m);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void run(){

        try {
            User u = (User)ios.readObject();
            Message m = null;
            ClientAuth.checkUser(u);
            if(u.isAuthorized()){
                oos.writeBoolean(true);
                m= new Message("Server", "Bienvenido " + u.getUsername());
                oos.writeObject(m);
                ch.addUser(u);
                while(true){
                    m=null;
                    m=(Message) ios.readObject();
                    if(m.getMessage().equals(".bye")){
                        ch.removeUser(u);
                        m = new Message("Server", u.getUsername() + "has left the chat.");
                        ch.addMessage(m);
                        ch.broadcastMessage();
                        break;
                    }else{
                        ch.addMessage(m);
                        ch.broadcastMessage();
                    }
                }
            }else{
                oos.writeBoolean(false);
                s.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e2){
            e2.printStackTrace();
        }
    }
}
