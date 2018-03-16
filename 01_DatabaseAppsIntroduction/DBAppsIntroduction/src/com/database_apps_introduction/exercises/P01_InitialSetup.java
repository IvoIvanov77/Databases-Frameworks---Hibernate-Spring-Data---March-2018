package com.database_apps_introduction.exercises;

import java.sql.*;

public class P01_InitialSetup {


    public static void main(String[] args) throws SQLException {

       createDatabase("minionsdb");
       seedDatabase("minionsdb");
    }

    private static void createDatabase(String dbName) throws SQLException {

        try (
                Connection conn = ConnectionUtil.getConnection();
                Statement stmt = conn.createStatement();
        ) {
            System.out.println("Creating statement...");

            String sql;
            sql = String.format("DROP DATABASE IF EXISTS %s", dbName);
            stmt.execute(sql);
            sql = String.format("CREATE DATABASE %s", dbName);
            stmt.execute(sql);
            stmt.close();
            conn.close();
            System.out.println("Database created successfully");
        } catch (SQLException  | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void seedDatabase(String dbName){

        try(
                Connection conn = ConnectionUtil.getConnection(dbName);
                Statement stmt = conn.createStatement();
        ){
            //Execute a query`
            System.out.println("Creating statement...");

            String sql;

            sql = "CREATE TABLE towns (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "name varchar(50), " +
                    "country varchar(50)" +
                    ")";
            stmt.execute(sql);
            sql = "CREATE TABLE minions " +
                    "(" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "name varchar(50), " +
                    "age int, " +
                    "town_id int, " +
                    "CONSTRAINT fk_Towns " +
                    "FOREIGN KEY (town_id) REFERENCES towns(id)" +
                    ")";
            stmt.execute(sql);

            sql = "CREATE TABLE villains " +
                    "(" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "name varchar(50), " +
                    "evilness_factor varchar(20)" +
                    ")";
            stmt.execute(sql);
            sql = "CREATE TABLE minions_villains" +
                    "(" +
                    "minion_id INT, " +
                    "villain_id INT, " +
                    "CONSTRAINT fk_Minions " +
                    "FOREIGN KEY (minion_id) REFERENCES Minions(id), " +
                    "CONSTRAINT  fk_Villains " +
                    "FOREIGN KEY (villain_id) REFERENCES villains(id)" +
                    ")";
            stmt.execute(sql);

            sql = "INSERT INTO towns (name, country) " +
                    "VALUES " +
                    "('Sofia','Bulgaria'), " +
                    "('Burgas','Bulgaria'), " +
                    "('Varna', 'Bulgaria'), " +
                    "('London','UK')," +
                    "('Liverpool','UK')," +
                    "('Ocean City','USA')," +
                    "('Paris','France')";
            stmt.execute(sql);

            sql = "INSERT INTO minions (name, age, town_id) " +
                    "VALUES " +
                    "('bob',10,1)," +
                    "('kevin',12,2)," +
                    "('steward',9,3), " +
                    "('rob',22,3), (" +
                    "'michael',5,2),('pep',3,2)";
            stmt.execute(sql);

            sql = "INSERT INTO villains (name, evilness_factor) " +
                    "VALUES " +
                    "('Gru','super evil')," +
                    "('Victor','evil')," +
                    "('Simon Cat','good')," +
                    "('Pusheen','super good')," +
                    "('Mammal','evil')";
            stmt.execute(sql);

            sql = "INSERT INTO minions_villains " +
                    "VALUES (1,2), " +
                    "(3,1)," +
                    "(1,3)," +
                    "(3,3)," +
                    "(4,1)," +
                    "(2,2)," +
                    "(1,1)," +
                    "(3,4)," +
                    "(1,4)," +
                    "(1,5)," +
                    "(5,1)," +
                    "(4,1)," +
                    "(3, 1)";
            stmt.execute(sql);

            //Clean-up environment
            stmt.close();
            conn.close();
        }catch(SQLException | ClassNotFoundException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }

    }

}
