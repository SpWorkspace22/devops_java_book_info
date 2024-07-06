package com.pluralsight.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AbstractConnection {
    public Connection getConnection(){
        String URL="jdbc:mysql://localhost:3306/books_db";
        String USER="root";
        String PASS="pass";

        try {
            return DriverManager.getConnection(URL,USER,PASS);
        } catch (SQLException e) {
            System.out.println("Database Connection Failed");
            throw new RuntimeException(e);
        }
    };
}
