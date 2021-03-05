package com.simplechat2.server;

import com.simplechat2.common.TUser;

import java.sql.*;

public class ClientAuth {
    private static Connection conx;

    public static void getConnection(){
        String url = "jdbc:sqlite:/home/xavier/db/users.db";
        try {
            conx = DriverManager.getConnection(url);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void checkUser(TUser u){
        getConnection();
        try {
            PreparedStatement pst = conx.prepareStatement("select id from users where username=? and password=?");
            pst.setString(1,u.getUsername());
            pst.setString(2,u.getPassword());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                System.out.println("Autorizado");
                u.setAuthorized(true);
                u.setPassword("");
            }
            conx.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
