package com.simplechat2.server.controller;

import com.simplechat2.common.Client;

import java.sql.*;

public class ClientAuth {
    private Connection conx;
    private static final String url = "jdbc:sqlite:/home/xavier/db/users.db";

    public ClientAuth(){
        try{
            conx = DriverManager.getConnection(url);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean validateUser(Client c){
        boolean access=false;
        try {
            PreparedStatement pst = conx.prepareStatement("select id from users where username=? and password=?");
            pst.setString(1,c.getUsername());
            pst.setString(2,c.getPassword());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                access = true;
            }
            conx.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return access;
    }
}
