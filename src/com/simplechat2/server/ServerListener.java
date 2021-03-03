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
        String usr, pass;
        ClientAuth ca;
        boolean answer;
        while(true){
            System.out.println("Escuchando");
            socket.acceptConnection();
            System.out.println("Conexion aceptada");
            try {
                System.out.println("esperando nombre");
                usr=socket.getObjInputStream().readUTF();
                System.out.println("Usuario: " +usr);
                pass=socket.getObjInputStream().readUTF();
                System.out.println("contraseña: " +pass);
                u = new User(usr,pass, socket);
                ca = new ClientAuth(u);
                ca.checkUser();
                if(ca.getAccess()) {
                    answer=true;
                    joinUser(u);
                    uh = new UserHandler(u, ch, socket);
                    uh.start();
                }else{
                    answer=false;
                    socket.getObjOutputStream().writeBoolean(answer);
                    System.out.println("Conexion rechazada");
                    socket.closeCSocket();
                }
                usr=null;
                uh=null;
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        //socket.closeSocket();
    }
}
