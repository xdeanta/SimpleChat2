package com.simplechat2.client;

import com.simplechat2.client.controller.ClientSocket;
import com.simplechat2.client.controller.Listener;
import com.simplechat2.common.IOStream;
import com.simplechat2.client.model.CClient;
import com.simplechat2.common.Message;

import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String usr, pass, msg="";
        CClient c;
        Listener ls;
        ClientSocket cs = ClientSocket.getSocket("192.168.56.2", 7500);
        //IOStream IOHandler = IOStream.createIO(cs.getIs(),cs.getOs());
        IOStream IOHandler = new IOStream(cs.getIs(),cs.getOs());
        ls = new Listener(IOHandler);
        boolean connected;
        usr = JOptionPane.showInputDialog("Ingrese su usuario");
        pass = JOptionPane.showInputDialog("Ingrese su contraseña");
        /*System.out.println("Usuario: " + usr);
        System.out.println("Contraseña: " + pass);*/
        c = new CClient(usr,pass);
        Message m=null;
        IOHandler.sendObject(c);
        connected=(Boolean) IOHandler.ReceiveObject();
        if(connected){
            System.out.println("Conectado");
            ls.start();
            while(!msg.equals("bye")){
                msg=sc.nextLine();
                m = new Message(c, msg);
                IOHandler.sendObject(m);
            }
            ls.interrupt();
        }else{
            System.out.println("La conexión fallo");
        }
    }
}
