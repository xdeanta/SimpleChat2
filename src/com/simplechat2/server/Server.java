package com.simplechat2.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        // write your code here
        ServiceSocket ss = new ServiceSocket("192.168.56.2",7500);
        Channel ch = new Channel();
        ServerListener sl = new ServerListener(ss,ch);
        sl.start();
        /*Scanner sc = new Scanner(System.in);
        String str;*/
        try{
            sl.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}
