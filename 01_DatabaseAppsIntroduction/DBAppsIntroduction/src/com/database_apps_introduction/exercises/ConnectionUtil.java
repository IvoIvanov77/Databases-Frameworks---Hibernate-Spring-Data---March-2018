package com.database_apps_introduction.exercises;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class ConnectionUtil {

    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/";

    //  Database credentials
    private static final String USER = "root";
    private static final String PASS = "";




    static Connection getConnection() throws ClassNotFoundException, SQLException {
       return getConnection(null);
    }

    static Connection getConnection(String dbName) throws ClassNotFoundException, SQLException {

        Class.forName(JDBC_DRIVER);

        //Open a connection
        System.out.println("Connecting to database...");

        if(dbName != null){
            return DriverManager.getConnection(ConnectionUtil.DB_URL + dbName, ConnectionUtil.USER,
                        ConnectionUtil.PASS);
        }else {
            return DriverManager.getConnection(ConnectionUtil.DB_URL , ConnectionUtil.USER,
                        ConnectionUtil.PASS);
        }


    }

    private ConnectionUtil() {
    }
}
