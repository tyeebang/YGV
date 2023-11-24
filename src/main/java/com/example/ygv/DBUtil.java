package com.example.ygv;

import java.sql.*;

public class DBUtil {
    public Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/ygv";
        String userName = "root";
        String password = "";
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
