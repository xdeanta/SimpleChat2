package com.simplechat2.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        ClientSocket s = new ClientSocket("192.168.56.2",7500);
        s.getIO();
        Scanner sc = new Scanner(System.in);
        String user;
        Client c;
        Listener ls;
        String str;
        try {
            /*System.out.println("Nombre de usuario:");
            user = sc.nextLine();
            System.out.println("Creando cliente, usuario: " + user);*/
            c = new Client("xavier",s);
            System.out.println("usuario: " + c.getUsername());
            s.getDataOutputStream().writeUTF(c.getUsername());
            ls=new Listener(s);
            ls.start();
            while(true){
                System.out.println("Dentro while");
                str = sc.nextLine();
                if(str.equals("bye")){
                    break;
                }
                s.getDataOutputStream().writeUTF(str);
            }
            s.closeSocket();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
