package com.automation.utils;

import java.sql.*;

public class DatabaseDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        String url = "jdbc:mysql://18.118.14.155:3306/digitalbank";
        //1. Register driver
        Class.forName("com.mysql.cj.jdbc.Driver"); //driver

        //2. Create a connection
        Connection con = DriverManager.getConnection(url, "digitaluser", "Demo123!");

        //3. Create object of statement

        Statement stmt = con.createStatement();

        //4. Execute Query
        ResultSet rs = stmt.executeQuery("select name from account;");

        while(rs.next()){
            System.out.println(rs.getString("name"));

        }

    }
}
