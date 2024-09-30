package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BolsaDeEmpleo {
    private Conector conexion;

    public BolsaDeEmpleo() {
        conexion = new Conector();
    }

    // Método para agregar un aspirante a la base de datos
    public boolean agregarAspirante(Aspirante aspirante) {
        String sql = "INSERT INTO aspirantes (cedula, nombre, edad, experiencia, profesion, telefono) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = conexion.conectar();
             PreparedStatement stmt = conn != null ? conn.prepareStatement(sql) : null) {
            if (stmt != null) {
                stmt.setString(1, aspirante.getCedula());
                stmt.setString(2, aspirante.getNombre());
                stmt.setInt(3, aspirante.getEdad());
                stmt.setInt(4, aspirante.getExperiencia());
                stmt.setString(5, aspirante.getProfesion());
                stmt.setString(6, aspirante.getTelefono());
                return stmt.executeUpdate() > 0;
            } else {
                System.err.println("fallo en crear statement: sin conexion");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Error al agregar aspirante: " + e.getMessage());
            return false;
        }
    }

    // Obtener lista de aspirantes
    public List<Aspirante> obtenerAspirantes() {
        List<Aspirante> aspirantes = new ArrayList<>();
        String sql = "SELECT * FROM aspirantes";
        try (Connection conn = conexion.conectar();
             Statement stmt = conn != null ? conn.createStatement() : null;
             ResultSet rs = stmt != null ? stmt.executeQuery(sql) : null) {
            if (rs != null) {
                while (rs.next()) {
                    Aspirante aspirante = new Aspirante(
                            rs.getString("cedula"),
                            rs.getString("nombre"),
                            rs.getInt("edad"),
                            rs.getInt("experiencia"),
                            rs.getString("profesion"),
                            rs.getString("telefono")
                    );
                    aspirantes.add(aspirante);
                }
            } else {
                System.err.println("Failed to execute query: el Statement is null.");
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener aspirantes: " + e.getMessage());
        }
        return aspirantes;
    }

    // Buscar un aspirante por cédula
    public Aspirante buscarAspirantePorCedula(String cedula) {
        String sql = "SELECT * FROM aspirantes WHERE cedula = ?";
        try (Connection conn = conexion.conectar();
             PreparedStatement stmt = conn != null ? conn.prepareStatement(sql) : null) {
            if (stmt != null) {
                stmt.setString(1, cedula);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs != null && rs.next()) {
                        return new Aspirante(
                                rs.getString("cedula"),
                                rs.getString("nombre"),
                                rs.getInt("edad"),
                                rs.getInt("experiencia"),
                                rs.getString("profesion"),
                                rs.getString("telefono")
                        );
                    }
                }
            } else {
                System.err.println("Failed to create statement: Connection is null.");
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar aspirante: " + e.getMessage());
        }
        return null;
    }

    // Eliminar un aspirante por cédula
    public boolean eliminarAspirante(String cedula) {
        String sql = "DELETE FROM aspirantes WHERE cedula = ?";
        try (Connection conn = conexion.conectar();
             PreparedStatement stmt = conn != null ? conn.prepareStatement(sql) : null) {
            if (stmt != null) {
                stmt.setString(1, cedula);
                return stmt.executeUpdate() > 0;
            } else {
                System.err.println("Failed to create statement: Connection is null.");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar aspirante: " + e.getMessage());
            return false;
        }
    }

    // Calcular promedio de edad
    public double calcularPromedioEdad() {
        String sql = "SELECT AVG(edad) AS promedio_edad FROM aspirantes";
        try (Connection conn = conexion.conectar();
             Statement stmt = conn != null ? conn.createStatement() : null;
             ResultSet rs = stmt != null ? stmt.executeQuery(sql) : null) {
            if (rs != null && rs.next()) {
                return rs.getDouble("promedio_edad");
            }
        } catch (SQLException e) {
            System.err.println("Error al calcular promedio de edad: " + e.getMessage());
        }
        return 0;
    }

    // Ordenar aspirantes por experiencia
    public List<Aspirante> ordenarPorExperiencia() {
        List<Aspirante> aspirantes = new ArrayList<>();
        String sql = "SELECT * FROM aspirantes ORDER BY experiencia DESC";
        try (Connection conn = conexion.conectar();
             Statement stmt = conn != null ? conn.createStatement() : null;
             ResultSet rs = stmt != null ? stmt.executeQuery(sql) : null) {
            if (rs != null) {
                while (rs.next()) {
                    Aspirante aspirante = new Aspirante(
                            rs.getString("cedula"),
                            rs.getString("nombre"),
                            rs.getInt("edad"),
                            rs.getInt("experiencia"),
                            rs.getString("profesion"),
                            rs.getString("telefono")
                    );
                    aspirantes.add(aspirante);
                }
            } else {
                System.err.println("Failed to execute query: Statement is null.");
            }
        } catch (SQLException e) {
            System.err.println("Error al ordenar aspirantes: " + e.getMessage());
        }
        return aspirantes;
    }
}