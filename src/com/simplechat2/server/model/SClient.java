package com.simplechat2.server.model;

import com.simplechat2.common.Client;
import com.simplechat2.common.IOStream;
import com.simplechat2.common.Message;

public class SClient extends Client {
    private IOStream ios;
    public SClient(String usr, String pass, IOStream ios){
        super (usr,pass);
        this.ios=ios;
    }

    public void receiveMessage(Message m){
        ios.sendObject(m);
    }
}
