/*
Proyecto Intermodular 1 - Stockerty
v.1.0
Aplicación para la gestión logística
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Stockerty {

    private static final String URL = "jdbc:mysql://localhost:3306/stockerty";
    private static final String USUARIO = "root";
    private static final String CLAVE = ""; // Buscar forma de no tener que introducir contraseña visible
    public static void main(String[] args) {
        
        System.out.println("\n Comienzo");

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE)) {

            System.out.println("\n Conexión a MySQL...");
            System.out.println("\n ¡Completada!");

            String sql = "SHOW TABLES";

            try (PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

                System.out.println("\n Tablas de la base de datos Stockerty:");
                while (rs.next()) {

                    String tabla = rs.getString(1);
                    System.out.println(" - " + tabla);
                }
            }

        } catch(SQLException e) {

            System.out.println(" Error de conexión a la base de datos");
        }

    }
}