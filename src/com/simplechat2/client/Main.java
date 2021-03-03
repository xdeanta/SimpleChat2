package com.simplechat2.client;

import com.simplechat2.client.controller.ClientSocket;
import com.simplechat2.common.IOStream;
import com.simplechat2.client.model.CClient;

import javax.swing.*;

public class Main {
    public static void main(String[] args){
        //Scanner sc = new Scanner(System.in);
        String usr, pass;
        CClient c;
        ClientSocket cs = ClientSocket.getSocket("192.168.56.2", 7500);
        IOStream IOHandler = IOStream.createIO(cs.getIs(),cs.getOs());
        boolean connected;
        usr = JOptionPane.showInputDialog("Ingrese su usuario");
        pass = JOptionPane.showInputDialog("Ingrese su contraseña");
        /*System.out.println("Usuario: " + usr);
        System.out.println("Contraseña: " + pass);*/
        c = new CClient(usr,pass);
        //IOHandler.sendObject(c);
        connected=(Boolean) IOHandler.ReceiveObject();
        if(connected){
            System.out.println("Conectado");
        }else{
            System.out.println("La conexión fallo");
        }
    }
}
