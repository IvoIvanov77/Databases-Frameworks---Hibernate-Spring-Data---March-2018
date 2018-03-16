package com.database_apps_introduction.exercises;

import com.sun.xml.internal.ws.util.StringUtils;

import java.sql.*;
import java.util.Scanner;

public class P08_IncreaseMinionsAge {


    public static void main(String[] args) {
        Connection conn = null;

        try{
            conn = ConnectionUtil.getConnection("minionsdb");

            Scanner sc = new Scanner(System.in);
            String[] inputTokens = sc.nextLine().split("\\s");
            
            StringBuilder ids = new StringBuilder();
            ids.append("(");
            for (String inputToken : inputTokens) {
                ids.append(inputToken + ", ");
            }
            ids.delete(ids.lastIndexOf(", "),ids.length());
            ids.append(")");
            System.out.println(ids.toString());

            String minionsSQL = "SELECT * FROM minions WHERE id IN "+ids.toString();
            Statement minionsStatement = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);

            ResultSet minions = minionsStatement.executeQuery(minionsSQL);

            while(minions.next()){
                String name = toTitleCase(minions.getString("name"));
                int age = minions.getInt("age") + 1;
                minions.updateString("name", name);
                minions.updateInt("age", age);
                minions.updateRow();
            }

            String allMinionsSQL = "SELECT * FROM minions";
            Statement allMinionsStatement = conn.createStatement();

            ResultSet allMinions = allMinionsStatement.executeQuery(allMinionsSQL);

            while(allMinions.next()){
                System.out.println(allMinions.getInt("id")+" "+allMinions.getString("name")+" "+allMinions.getInt("age"));
            }


        }catch(SQLException se){
            se.printStackTrace();
        }finally{
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
