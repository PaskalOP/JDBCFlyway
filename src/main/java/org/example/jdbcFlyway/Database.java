package org.example.jdbcFlyway;

import org.example.jdbcFlyway.servises.PropertyReader;
import org.flywaydb.core.Flyway;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final Database INSTANCE = new Database();
    private static Connection connection;
    private Database(){
        try {
            String url = PropertyReader.urlForPostgres();
            String userName = PropertyReader.userForPostgres();
            String password = PropertyReader.passwordForPostgres();
            connection = DriverManager.getConnection(url,userName,password);
            flywayMigration(url,userName, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //метод для виклику конструктора і отримання екземпляру класу
    public static Database getInstance(){
        return INSTANCE;
    }

    public static Connection getConnection(){
        return connection;
    }

    private void flywayMigration(String connectUrl, String userName, String password){
        Flyway flyway = Flyway.configure().dataSource(connectUrl, userName,password).load();
        flyway.migrate();

    }

}
