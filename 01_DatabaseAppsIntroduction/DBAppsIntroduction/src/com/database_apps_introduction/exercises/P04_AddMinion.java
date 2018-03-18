package com.database_apps_introduction.exercises;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class P04_AddMinion {

    private static final String GET_TOWN_ID = "SELECT id FROM towns AS t WHERE t.name = ?";
    private static final String INSERT_TOWN = "INSERT INTO towns(name) VALUES (?)";
    private static final String GET_VILLAIN_ID = "SELECT id FROM villains AS v WHERE v.name = ?";
    private static final String INSERT_VILLAIN = "INSERT INTO villains(`name`, evilness_factor) " +
            "VALUES (?, ?)";
    private static final String INSERT_MINION = "INSERT INTO minions (name, age, town_id) " +
            "VALUES (?, ?, ?)";
    private static final String GET_MINION_ID = "SELECT id FROM minions where name = ?";
    private static final String INSERT_INTO_MINIONS_VILLAINS =
            "INSERT INTO minions_villains (minion_id, villain_id) VALUES (?, ?)";

    public static void main(String[] args) {
        java.sql.Connection conn = null;
        PreparedStatement addMinionsStatement = null;
        PreparedStatement addVillainStatement = null;
        PreparedStatement addTownStatement = null;
        PreparedStatement getTownIdStatement = null;
        PreparedStatement getVillainIdStatement = null;
        PreparedStatement getNewMinionIdStatement = null;
        PreparedStatement addMinionToVillainStatement = null;

        Scanner sc = new Scanner(System.in);

        String[] minionsInput = sc.nextLine().split("\\s+");
        String[] villainInput = sc.nextLine().split("\\s+");

        String minionName = minionsInput[1];
        int minionAge = Integer.parseInt(minionsInput[2]);
        String townName = minionsInput[3];
        String villainName = villainInput[1];

        try{
            conn = ConnectionUtil.getConnection("minionsdb");
            conn.setAutoCommit(false);

            //Execute a query`
            System.out.println("Creating statement...");

            getTownIdStatement = conn.prepareStatement(GET_TOWN_ID);
            getTownIdStatement.setString(1, townName);

            ResultSet rs;
            rs = getTownIdStatement.executeQuery();
            if(!rs.isBeforeFirst()){
                addTownStatement = conn.prepareStatement(INSERT_TOWN);
                addTownStatement.setString(1, townName);
                addTownStatement.executeUpdate();
                System.out.printf("Town %s was added to the database.%n", townName);
            }
            rs = getTownIdStatement.executeQuery();
            rs.first();
            int townId = rs.getInt("id");

            getVillainIdStatement = conn.prepareStatement(GET_VILLAIN_ID);
            getVillainIdStatement.setString(1, villainName);

            rs = getVillainIdStatement.executeQuery();
            if(!rs.isBeforeFirst()){
                addVillainStatement = conn.prepareStatement(INSERT_VILLAIN);
                addVillainStatement.setString(1, villainName);
                addVillainStatement.setString(2,"evil");
                addVillainStatement.executeUpdate();
                System.out.printf("Villain %s was added to the database.%n", villainName);
            }

            rs = getVillainIdStatement.executeQuery();
            rs.first();
            int villainId = rs.getInt("id");

            //add minion
            addMinionsStatement = conn.prepareStatement(INSERT_MINION);
            addMinionsStatement.setString(1, minionName);
            addMinionsStatement.setInt(2, minionAge);
            addMinionsStatement.setInt(3, townId);
            addMinionsStatement.executeUpdate();

            //get minion ID
            getNewMinionIdStatement = conn.prepareStatement(GET_MINION_ID);
            getNewMinionIdStatement.setString(1, minionName);
            ResultSet minions = getNewMinionIdStatement.executeQuery();
            minions.first();
            int minionId = minions.getInt("id");

            //add record in villains_minions table

            addMinionToVillainStatement = conn.prepareStatement(
                    INSERT_INTO_MINIONS_VILLAINS);
            addMinionToVillainStatement.setInt(1, minionId);
            addMinionToVillainStatement.setInt(2, villainId);
            addMinionToVillainStatement.executeUpdate();
            System.out.printf("Successfully added %s to be minion of %s", minionName, villainName);


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
