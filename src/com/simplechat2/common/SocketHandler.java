package com.simplechat2.common;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class SocketHandler {
    protected Socket socket;
    protected ServerSocket sSocket;
    protected InetSocketAddress addr;
    protected InputStream is;
    protected OutputStream os;

    public void getAddress(String ip, int port){
        addr = new InetSocketAddress(ip,port);
    }

    public Socket getSocket() {
        return socket;
    }

    public ServerSocket getsSocket() {
        return sSocket;
    }

    public InputStream getIs() {
        return is;
    }

    public OutputStream getOs() {
        return os;
    }

    public abstract void closeSocket();

}
