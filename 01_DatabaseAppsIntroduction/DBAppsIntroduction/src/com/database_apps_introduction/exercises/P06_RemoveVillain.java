package com.database_apps_introduction.exercises;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class P06_RemoveVillain {

    private static final String VILLAIN_NAME_BY_ID = "SELECT name FROM villains WHERE id = %d";
    private static final String DELETE_VILLAIN_BY_ID = "DELETE FROM villains where id = %d";
    private static final String MINION_COUNT_BY_VILLAIN = "SELECT COUNT(minion_id) AS minions_count FROM villains v\n" +
            "JOIN minions_villains mv ON v.id = mv.villain_id\n" +
            "WHERE v.id = %d";
    private static final String DELETE_MINIONS_BY_VILLAIN = "DELETE FROM minions_villains WHERE villain_id = %d";
    private static final String NO_SUCH_VILLAIN_MESSAGE = "No such villain was found";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();

        try(Connection conn = ConnectionUtil.getConnection("minionsdb");){

            conn.setAutoCommit(false);

            try(Statement statement = conn.createStatement();) {

                String villainName = getVillainName(statement, id);

                int minionsFound = getMinionsFound(id, statement);

                statement.executeUpdate(String.format(DELETE_MINIONS_BY_VILLAIN, id));
                statement.executeUpdate(String.format(DELETE_VILLAIN_BY_ID, id));

                System.out.println(villainName+" was deleted");
                System.out.println(minionsFound +" minions released");

                conn.commit();

            }catch (SQLException ex){
                conn.rollback();
                ex.printStackTrace();
            }finally {
                conn.setAutoCommit(true);
            }

        } catch(SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
        }
    }

    private static int getMinionsFound(int id, Statement statement) throws SQLException {
        int minionsFound = 0;
        try(ResultSet minionsForVillain = statement.executeQuery(String.format(MINION_COUNT_BY_VILLAIN, id))) {
            if(minionsForVillain.isBeforeFirst()){
                minionsForVillain.first();
                //columnLabel = alias or columnName
                minionsFound = minionsForVillain.getInt("minions_count");
            }
        }
        return minionsFound;
    }

    private static String getVillainName(Statement statement, int id) throws SQLException {
        try(ResultSet villain = statement.executeQuery(String.format(VILLAIN_NAME_BY_ID, id))) {
            if(!villain.isBeforeFirst()){
                return NO_SUCH_VILLAIN_MESSAGE;
            }
            villain.first();
            return villain.getString("name");
        }
    }



}
