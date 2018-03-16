package com.database_apps_introduction.exercises;

import java.sql.*;

public class P02_GetVillainsNames {

    private static final String SQL = "SELECT v.name, COUNT(mv.minion_id) AS minions_count\n" +
            "FROM minions_villains AS mv\n" +
            "INNER JOIN villains AS v ON mv.villain_id = v.id\n" +
            "GROUP BY mv.villain_id\n" +
            "HAVING minions_count > 3\n" +
            "ORDER BY minions_count DESC";

    public static void main(String[] args) {

        try(Connection conn = ConnectionUtil.getConnection("minionsdb");
            Statement stmt = conn.createStatement();){

            ResultSet rs = stmt.executeQuery(SQL);

            while(rs.next()){
                System.out.println(rs.getString("name") + " " + rs.getString("minions_count"));
            }

            //Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException | ClassNotFoundException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }
}
