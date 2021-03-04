package com.simplechat2.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        Socket s = null;
        ServerSocket server = null;
        ArrayList<ClientHandler> clients;
        clients=new ArrayList<>();
        Channel ch = new Channel(clients);
        try {
            s = null;
            server = new ServerSocket();
            InetSocketAddress addr = new InetSocketAddress("192.168.56.2",7500);
            server.bind(addr);

            while(true){
                System.out.println("Escuchando..");
                s = server.accept();
                System.out.println("Conexion aceptada");
                ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                if(clients.size() < 10){
                    ClientHandler client = new ClientHandler(s,oos,ois,ch);
                    clients.add(client);
                    client.start();
                }

            }
        }catch (IOException e){
            try {
                s.close();
            }catch (IOException e2){
                e2.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
