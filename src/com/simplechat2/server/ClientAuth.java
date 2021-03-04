package com.simplechat2.server;

import java.sql.*;

public class ClientAuth {
    private static Connection conx;

    public static void getConnection(User u){
        String url = "jdbc:sqlite:/home/xavier/db/users.db";
        try {
            conx = DriverManager.getConnection(url);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void checkUser(User u){
        try {
            PreparedStatement pst = conx.prepareStatement("select id from users where username=? and password=?");
            pst.setString(1,u.getUsername());
            pst.setString(2,u.getPassword());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                u.setAuthorized(true);
                u.setPassword("");
            }
            conx.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}