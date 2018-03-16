package com.database_apps_introduction.exercises;

import java.sql.*;

public class P07_PrintAllMinionNames {


    public static void main(String[] args) {
        Connection conn = null;

        try{

            conn = ConnectionUtil.getConnection("minionsdb");

            String minionNames = "SELECT name FROM minions";
            PreparedStatement minionsStatement = conn
                    .prepareStatement(minionNames, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

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
}
