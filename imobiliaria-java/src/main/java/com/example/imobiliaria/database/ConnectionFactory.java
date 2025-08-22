package com.example.imobiliaria.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String URL = "jdbc:mysql://localhost:3306/imobiliaria_db";
    // IMPORTANTE: Altere o usuário e a senha para os do seu banco de dados local
    private static final String USER = "root";
    private static final String PASSWORD = "univille";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados", e);
        }
    }
}
