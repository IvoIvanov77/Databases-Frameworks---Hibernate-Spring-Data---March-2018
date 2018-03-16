package com.database_apps_introduction.exercises;

import java.sql.*;
import java.util.Scanner;

public class P03_GetMinionNames {

    public static final String SELECT_VILLIAN_NAME_BY_ID = "SELECT name\n" +
            "FROM villains\n" +
            "WHERE id = ?";
    public static final String SELECT_MINIONS_BY_VILLIAN = "SELECT m.name, m.age\n" +
            "FROM minions AS m\n" +
            "INNER JOIN minions_villains AS mv ON mv.minion_id = m.id\n" +
            "WHERE mv.villain_id = ?";

    public static void main(String[] args) {



        try(
                Connection conn = ConnectionUtil.getConnection("minionsdb");
                PreparedStatement villainStatement = conn.prepareStatement(SELECT_VILLIAN_NAME_BY_ID);
                PreparedStatement minionsNamesStatement = conn.prepareStatement(SELECT_MINIONS_BY_VILLIAN);
                ){


            Scanner sc = new Scanner(System.in);

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
            villainStatement.close();
            conn.close();
        } catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    }
}
