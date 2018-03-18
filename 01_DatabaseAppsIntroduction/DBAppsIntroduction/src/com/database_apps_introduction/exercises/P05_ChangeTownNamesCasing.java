package com.database_apps_introduction.exercises;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P05_ChangeTownNamesCasing {

    public static final String SELECT_TOWNS_BY_COUNTRY = "SELECT id, name FROM towns WHERE country = ?";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String countryName = sc.nextLine();

        try(
                Connection conn = ConnectionUtil.getConnection("minionsdb");
                PreparedStatement townsFromCountryStatement =
                        conn.prepareStatement(SELECT_TOWNS_BY_COUNTRY,
                                ResultSet.TYPE_SCROLL_INSENSITIVE,
                                ResultSet.CONCUR_UPDATABLE);
                ){

            townsFromCountryStatement.setString(1, countryName);
            ResultSet towns = townsFromCountryStatement.executeQuery();
            if(!towns.isBeforeFirst()){
                System.out.println("No town names were affected.");
                return;
            }

            while(towns.next()){
                String town = towns.getString("name");
                towns.updateString("name", town.toUpperCase());
                towns.updateRow();
            }

            List<String> townsUppercase = new ArrayList<>();

            towns.beforeFirst();
            while(towns.next()){
                townsUppercase.add(towns.getString("name"));
            }
            System.out.println(townsUppercase.size()+" town names were affected.");
            System.out.println(townsUppercase);
        }catch(SQLException | ClassNotFoundException se){
            se.printStackTrace();

        }
    }
}
