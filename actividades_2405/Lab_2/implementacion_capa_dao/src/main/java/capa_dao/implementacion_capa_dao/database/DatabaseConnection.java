package capa_dao.implementacion_capa_dao.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    // Información de la base de datos
    private static final String URL = "jdbc:mysql://172.20.64.1:3306/prueba";  // Asegúrate de que esté en el formato correcto
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    // Método para obtener la conexión
    public static Connection getConnection() throws SQLException {
        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Crear la tabla si no existe
            createTableIfNotExists(connection);

            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Error al conectar con la base de datos", e);
        }
    }

    // Método para cerrar la conexión
    public static void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para crear la tabla 'usuarios' si no existe
    private static void createTableIfNotExists(Connection connection) {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS usuario ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "nombre VARCHAR(100), "
                + "correo VARCHAR(100)"
                + ")";

        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
            System.out.println("Tabla 'usuario' verificada/creada con éxito.");
        } catch (SQLException e) {
            System.err.println("Error al intentar crear la tabla 'usuario': " + e.getMessage());
        }
    }
}
