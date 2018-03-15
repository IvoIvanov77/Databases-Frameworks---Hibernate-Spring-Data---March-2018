package com.database_apps_introduction.exercises;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class ConnectionUtil {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";

    static Connection getConnection(){
       return getConnection(null);
    }


    static Connection getConnection(String dbName){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //Open a connection
        System.out.println("Connecting to database...");
        try {
            if(dbName != null){
                return DriverManager.getConnection(ConnectionUtil.DB_URL + dbName, ConnectionUtil.USER,
                        ConnectionUtil.PASS);
            }else {
                return DriverManager.getConnection(ConnectionUtil.DB_URL , ConnectionUtil.USER,
                        ConnectionUtil.PASS);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ConnectionUtil() {
    }
}
