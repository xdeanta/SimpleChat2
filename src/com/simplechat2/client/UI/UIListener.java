package com.simplechat2.client.UI;

import com.simplechat2.common.Message;

import javax.swing.*;

public class UIListener extends Thread{
    private JTextArea txtMsg;
    private SocketHandler sh;
    private String usr;
    private boolean exit;

    public UIListener(SocketHandler sh, JTextArea mensajes){
        this.sh=sh;
        txtMsg=mensajes;
        exit=false;
    }

    public void setExit(boolean e){
        exit=e;
    }

    public void setUsr(String usr) {
        this.usr=usr;
    }

    public void run(){
        Message m;
        while(!exit){
            m = null;
            m = (Message) sh.receiveObject();
            txtMsg.append(m.toString() + "\n");
            if(m.getMessage().matches("^"+usr))
                break;
        }
        //sh.closeConnection();
    }
}
