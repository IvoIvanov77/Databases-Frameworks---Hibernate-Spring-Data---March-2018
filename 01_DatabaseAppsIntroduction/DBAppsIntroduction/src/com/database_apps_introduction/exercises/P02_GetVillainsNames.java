package com.database_apps_introduction.exercises;

import java.sql.*;

public class P02_GetVillainsNames {

    public static void main(String[] args) {
        Connection conn = ConnectionUtil.getConnection("minionsdb");
        Statement stmt = null;
        try{
            //Execute a query`
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;

            sql = "SELECT v.name, COUNT(mv.minion_id) AS minions_count\n" +
                    "FROM minions_villains AS mv\n" +
                    "INNER JOIN villains AS v ON mv.villain_id = v.id\n" +
                    "GROUP BY mv.villain_id\n" +
                    "HAVING minions_count > 3\n" +
                    "ORDER BY minions_count DESC";

            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                System.out.println(rs.getString("name") + " " + rs.getString("minions_count"));
            }

            //Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }finally{
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
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
