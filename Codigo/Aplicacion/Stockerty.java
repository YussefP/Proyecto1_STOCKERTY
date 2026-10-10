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

    // OPCIÓN 1 (ACTIVA): Conexión a la base de datos de la nube (aiven.io)

    private static final String HOST_NUBE = "stockerty-yusep-0335.e.aivencloud.com";
    private static final String PUERTO_NUBE = "13210";
    private static final String BD_NUBE = "defaultdb";

    private static final String URL = "jdbc:mysql://" + HOST_NUBE + ":" + PUERTO_NUBE + "/" + BD_NUBE + "?useSSL=true";
    private static final String USUARIO = "usuario_stockerty";
    private static final String CLAVE = "stockerty5";

    // OPCIÓN 2 (INACTIVA): Conexión local (Importando el script stockerty.sql)
    
    // stockerty.sql incluye la creación de la estructura de la base de datos y la creación del usuario, contraseña y privilegios.

    // Para usar la opción 2, descomenta las 3 variables siguientes y comenta las variables de la opción 1.
    /* 
    private static final String URL = "jdbc:mysql://localhost:3306/stockerty";
    private static final String USUARIO = "usuario_stockerty";
    private static final String CLAVE = "stockerty5";
    */

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

            System.out.println(" Error de conexión a la base de datos: " + e.getMessage());
        }

    }
}