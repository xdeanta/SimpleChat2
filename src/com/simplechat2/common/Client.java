package com.simplechat2.common;

import java.io.Serializable;

public abstract class Client implements Serializable {
    protected String username;
    protected String password;

    public Client(String usr, String pass){
        username=usr;
        password=pass;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
