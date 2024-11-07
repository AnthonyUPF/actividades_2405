package capa_dao.ejercicio1_capa_dao.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private static Connection connection;

    private static final String URL = "jdbc:mysql://172.20.64.1:3306/ejercicio_1";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private DatabaseConnection() {}

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                createTables();
            } catch (ClassNotFoundException | SQLException e) {
                throw new SQLException("Error al conectar con la base de datos", e);
            }
        }
        return connection;
    }

    private static void createTables() {
        String createProductoTableSQL = "CREATE TABLE IF NOT EXISTS producto (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "nombre VARCHAR(100), " +
                "precio DOUBLE)";
        String createClienteTableSQL = "CREATE TABLE IF NOT EXISTS cliente (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "nombre VARCHAR(100), " +
                "correo VARCHAR(100))";

        try (Statement statement = connection.createStatement()) {
            statement.execute(createProductoTableSQL);
            statement.execute(createClienteTableSQL);
            System.out.println("Tablas 'producto' y 'cliente' verificadas/creadas con éxito.");
        } catch (SQLException e) {
            System.err.println("Error al intentar crear las tablas: " + e.getMessage());
        }
    }
}
