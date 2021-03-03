package com.simplechat2.client;

import java.io.*;

public class Client {
    private String username;
    private String password;
    private ClientSocket cs;

    public Client(String usr,String pw, ClientSocket s){
        username=usr;
        password=pw;
        cs = s;
    }

    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }

}

