package com.database_apps_introduction.lab;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class DiabloDB {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/diablo", props);

        PreparedStatement stmt =
                connection.prepareStatement("SELECT CONCAT(u.first_name, ' ', u.last_name) " +
                        "AS full_name, COUNT(ug.id) AS games_count\n" +
                        "FROM users AS u\n" +
                        "INNER JOIN users_games AS ug ON ug.user_id = u.id\n" +
                        "WHERE u.user_name = ?");

        String userName = sc.nextLine();
        stmt.setString(1, userName);
        ResultSet rs = stmt.executeQuery();

        rs.next();
        String fullName = rs.getString("full_name");
        if(fullName == null){
            System.out.println("No such user exists");
        }else {
            System.out.println(String.format("%s has played %d games",
                    fullName,
                    rs.getInt("games_count")) );

        }
        connection.close();
    }
}
