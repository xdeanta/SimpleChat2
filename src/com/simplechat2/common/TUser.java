package com.simplechat2.common;

import java.io.Serializable;

public abstract class TUser implements Serializable {
    private String username;
    private boolean authorized;
    private String password;

    public TUser(String usr, String password){
        username=usr;
        authorized=false;
        this.password=password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
