package com.database_apps_introduction.exercises;


import java.sql.*;
import java.util.Scanner;

public class P09_IncreaseAgeStoredProcedure {


    private static final String GET_OLDER_SQL = "{CALL usp_get_older(?)}";
    private static final String SELECT_MINION_BY_ID = "SELECT name, age FROM minions WHERE id = ?";

    public static void main(String[] args) {

        try(Connection conn = ConnectionUtil.getConnection("minionsdb");){

            createProcedure(conn);

            Scanner sc = new Scanner(System.in);
            int id = sc.nextInt();

            CallableStatement getOlderStoredProcedure = conn.prepareCall(GET_OLDER_SQL);
            getOlderStoredProcedure.setInt(1, id);
            getOlderStoredProcedure.execute();

            PreparedStatement minionsStatement = conn.prepareStatement(SELECT_MINION_BY_ID);
            minionsStatement.setInt(1, id);
            ResultSet minions = minionsStatement.executeQuery();
            minions.first();
            System.out.println(minions.getString("name")+" "+minions.getInt("age"));

        }catch(SQLException | ClassNotFoundException se) {
            se.printStackTrace();
        }
    }

    private static void createProcedure(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();

        String queryDrop = "DROP PROCEDURE IF EXISTS usp_get_older";

        String queryCreate = "CREATE PROCEDURE usp_get_older (IN minionID INT) ";
        queryCreate += "BEGIN ";
        queryCreate += "UPDATE minions SET age = age + 1 WHERE id = minionID; ";
        queryCreate += "END";

        // drops the existing procedure if exists
        statement.execute(queryDrop);

        // then creates a new stored procedure
        statement.execute(queryCreate);

        statement.close();

        System.out.println("Stored procedure created successfully!");
    }
}
