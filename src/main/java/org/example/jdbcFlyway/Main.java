package org.example.jdbcFlyway;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
       Connection connection = Database.getInstance().getConnection();
    }
}