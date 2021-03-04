package com.simplechat2.client;


import com.simplechat2.common.Message;
import com.simplechat2.common.TUser;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        String usr, pass,msg;
        Socket sock;
        ObjectInputStream ois;
        ObjectOutputStream oos;
        boolean logged;
        Message m;
        Scanner sc = new Scanner(System.in);
        System.out.print("Usuario: ");
        usr=sc.nextLine();
        System.out.print("\nContraseña: ");
        pass=sc.nextLine();
        System.out.println("");
        TUser c = new Client(usr,pass);
        try{
            sock=new Socket("192.168.56.2",7500);
            oos = new ObjectOutputStream(sock.getOutputStream());
            ois = new ObjectInputStream(sock.getInputStream());
            System.out.println("Conectado");
            oos.writeObject(c);
            logged=ois.readBoolean();
            if(logged){
                m=(Message) ois.readObject();
                System.out.println(m);
                while(true){
                    m=null;
                    msg=sc.nextLine();
                    m = new Message(c.getUsername(),msg);
                    oos.writeObject(m);
                    if(msg.equals(".bye")){
                        break;
                    }
                }
                sock.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e2){
            e2.printStackTrace();
        }
    }
}
