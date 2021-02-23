package com.simplechat2.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        ClientSocket s = new ClientSocket("192.168.56.2",7500);
        Scanner sc = new Scanner(System.in);
        Client c = new Client(sc.nextLine(), s);
        try {
            s.getDataOutputStream().writeUTF(c.getUsername());
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
