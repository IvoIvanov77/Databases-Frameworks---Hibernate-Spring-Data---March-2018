package com.database_apps_introduction.exercises;

import com.sun.xml.internal.ws.util.StringUtils;

import java.sql.*;
import java.util.Scanner;

public class P08_IncreaseMinionsAge {


    private static final String SELECT_MINIONS_BY_ID_IN = "SELECT * FROM minions WHERE id IN ";
    private static final String SELECT_ALL_MINIONS = "SELECT * FROM minions";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String[] inputTokens = sc.nextLine().split("\\s");

        StringBuilder ids = new StringBuilder();
        ids.append("(");
        for (String inputToken : inputTokens) {
            ids.append(inputToken).append(", ");
        }
        ids.delete(ids.lastIndexOf(", "),ids.length());
        ids.append(")");
        System.out.println(ids.toString());

        try(
                Connection conn  = ConnectionUtil.getConnection("minionsdb");
                Statement minionsStatement = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
                Statement allMinionsStatement = conn.createStatement();
                ){

            String minionsSQL = SELECT_MINIONS_BY_ID_IN + ids.toString();

            ResultSet minions = minionsStatement.executeQuery(minionsSQL);

            while(minions.next()){
                String name = toTitleCase(minions.getString("name"));
                int age = minions.getInt("age") + 1;
                minions.updateString("name", name);
                minions.updateInt("age", age);
                minions.updateRow();
            }

            ResultSet allMinions = allMinionsStatement.executeQuery(SELECT_ALL_MINIONS);

            while(allMinions.next()){
                System.out.println(allMinions.getInt("id") + " " + allMinions.getString("name") + " "
                        + allMinions.getInt("age"));
            }


        }catch(SQLException | ClassNotFoundException se){
            se.printStackTrace();
        }
    }

    private static String toTitleCase(String input) {
        StringBuilder titleCase = new StringBuilder();
        boolean nextTitleCase = true;

        for (char c : input.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                c = Character.toTitleCase(c);
                nextTitleCase = false;
            }

            titleCase.append(c);
        }

        return titleCase.toString();
    }
}
