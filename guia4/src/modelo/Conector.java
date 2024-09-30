package modelo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conector {
    private static final String URL = "jdbc:mysql://localhost:3306/bolsa_empleo";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private Connection connection;

    public Connection conectar() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return connection;
    }

    public void desconectar() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Desconexión de la base de datos.");
            }
        } catch (SQLException e) {
            System.err.println("Error al desconectar la base de datos: " + e.getMessage());
        }
    }
}
