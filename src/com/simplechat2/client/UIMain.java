package com.simplechat2.client;

import com.simplechat2.client.UI.Login;
import com.simplechat2.common.Message;
import com.simplechat2.common.TUser;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class UIMain {
    public static void main(String[] args){
        String usr, pass,msg;
        Socket sock;
        SocketHandler sh;
        Listener ls;
        Login lg = new Login();
        try{
            sock = new Socket("192.168.56.2",7500);
            sh = new SocketHandler(sock);
            lg.setSh(sh);
            lg.setVisible(true);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
