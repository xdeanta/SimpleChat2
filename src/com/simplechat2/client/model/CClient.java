package com.simplechat2.client.model;

import com.simplechat2.common.Client;

import java.io.Serializable;

public class CClient extends Client implements Serializable {
    public CClient(String usr, String pass){
        super(usr,pass);
    }
}
