package com.simplechat2.server;

import java.sql.*;

public class ClientAuth{
    private Connection conx;
    private User u;
    private boolean access;

    public ClientAuth(User u){
        access = false;
        String url = "jdbc:sqlite:/home/xavier/db/users.db";
        this.u=u;
        try {
            conx = DriverManager.getConnection(url);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void checkUser(){
        try {
            PreparedStatement pst = conx.prepareStatement("select id from users where username=? and password=?");
            pst.setString(1,u.getUsername());
            pst.setString(2,u.getPasswd());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                access = true;
            }
            conx.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean getAccess(){
        return access;
    }
}
