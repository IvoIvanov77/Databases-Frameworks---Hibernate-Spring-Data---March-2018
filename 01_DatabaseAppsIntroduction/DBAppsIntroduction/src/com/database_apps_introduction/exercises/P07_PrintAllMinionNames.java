package com.database_apps_introduction.exercises;

import java.sql.*;

public class P07_PrintAllMinionNames {


    public static final String MINION_NAMES = "SELECT name FROM minions";

    public static void main(String[] args) {

        try(
                Connection conn = ConnectionUtil.getConnection("minionsdb");
                PreparedStatement minionsStatement = conn
                        .prepareStatement(MINION_NAMES, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ){
            ResultSet minions = minionsStatement.executeQuery();
            int minionsCount = 0;
            while(minions.next()){
                minionsCount++;
            }

            minions.beforeFirst();

            int firstIndex = 1;
            int lastIndex = minionsCount;

            for (int i = 1; i < minionsCount+1; i++) {
                if(i%2 == 1){
                    minions.absolute(firstIndex);
                    firstIndex++;
                }else{
                    minions.absolute(lastIndex);
                    lastIndex--;
                }

                System.out.println(minions.getString("name"));
                minions.next();
            }

        }catch(SQLException | ClassNotFoundException se){
            se.printStackTrace();

        }
    }
}
