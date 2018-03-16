package com.database_apps_introduction.exercises;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class P06_RemoveVillain {

    public static void main(String[] args) {
        Connection conn = null;

        try{

            conn = ConnectionUtil.getConnection("minionsdb");

            conn.setAutoCommit(false);
            Scanner sc = new Scanner(System.in);
            int id = sc.nextInt();

            String villainNameSQL = "SELECT name FROM villains WHERE id = "  +id;

            String deleteVillainSQL = "DELETE FROM villains where id = "+id;
            String countMinionsSQL = "SELECT COUNT(minion_id) AS c FROM villains v\n" +
                    "JOIN minions_villains mv ON v.id = mv.villain_id\n" +
                    "WHERE v.id = "+id;
            String releaseMinionsSQL = "DELETE FROM minions_villains WHERE villain_id = "+id;

            Statement statement = conn.createStatement();
            ResultSet villain = statement.executeQuery(villainNameSQL);
            if(!villain.isBeforeFirst()){
                System.out.println("No such villain was found");
                return;
            }
            villain.first();
            String villainName = villain.getString("name");
            int minionsFound = 0;
            ResultSet minionsForVillain = statement.executeQuery(countMinionsSQL);
            if(minionsForVillain.isBeforeFirst()){
                minionsForVillain.first();
                minionsFound = minionsForVillain.getInt("c");
            }

            statement.executeUpdate(releaseMinionsSQL);


            statement.executeUpdate(deleteVillainSQL);
            System.out.println(villainName+" was deleted");

            System.out.println(minionsFound +" minions released");

        } catch(Exception e){
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally{
            try {
                conn.commit();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
