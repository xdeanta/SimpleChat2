package com.simplechat2.client.controller;

import com.simplechat2.common.IOStream;
import com.simplechat2.common.Message;

import java.io.InputStream;
import java.io.OutputStream;

public class Listener extends Thread{
    private IOStream input;

    public Listener(InputStream in, OutputStream out){
        input = IOStream.createIO(in,out);
    }

    public void run(){
        Message msg;
        while(true){
            msg=(Message) input.ReceiveObject();
            System.out.println(msg);
        }
    }
}
