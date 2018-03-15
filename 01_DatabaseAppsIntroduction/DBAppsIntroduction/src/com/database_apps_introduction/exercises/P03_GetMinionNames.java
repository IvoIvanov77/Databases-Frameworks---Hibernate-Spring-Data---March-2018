package com.database_apps_introduction.exercises;

import java.sql.*;
import java.util.Scanner;

public class P03_GetMinionNames {

    public static void main(String[] args) {
        java.sql.Connection conn = null;
        PreparedStatement villainStatement = null;
        PreparedStatement minionsNamesStatement = null;
        Scanner sc = new Scanner(System.in);
        try{
            //Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(ConnectionUtil.DB_URL + "minionsdb", ConnectionUtil.USER,
                    ConnectionUtil.PASS);

            //Execute a query`
            System.out.println("Creating statement...");

           villainStatement = conn.prepareStatement("SELECT name\n" +
                   "FROM villains\n" +
                   "WHERE id = ?");

            minionsNamesStatement = conn.prepareStatement("SELECT m.name, m.age\n" +
                    "FROM minions AS m\n" +
                    "INNER JOIN minions_villains AS mv ON mv.minion_id = m.id\n" +
                    "WHERE mv.villain_id = ?");

            String id = sc.nextLine();
            ResultSet rs;
            villainStatement.setInt(1, Integer.parseInt(id));
            minionsNamesStatement.setInt(1, Integer.parseInt(id));
            rs = villainStatement.executeQuery();

            if(!rs.first()){
                System.out.println(String.format("No villain with ID %s exists in the database.", id));
                return;
            }
            System.out.println(String.format("Villain: %s", rs.getString("name")));
            rs = minionsNamesStatement.executeQuery();

            if(!rs.first()){
                System.out.println("<no minions>");
                return;
            }
            int counter = 1;

            do {
                System.out.println(String.format("%d. %s %s", counter ++,
                        rs.getString("name"),
                        rs.getString("age")));
            }
            while(rs.next());


            //Clean-up environment
            rs.close();
            minionsNamesStatement.close();
            conn.close();
        } catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            try{
                if(minionsNamesStatement!=null)
                    minionsNamesStatement.close();
            }catch(SQLException ignored){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }
}
