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
import java.util.Scanner;

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
    // Para usar la opción 2, descomenta las 3 variables siguientes y comenta las 6 variables de la opción 1.
    /* 
    private static final String URL = "jdbc:mysql://localhost:3306/stockerty";
    private static final String USUARIO = "usuario_stockerty";
    private static final String CLAVE = "stockerty5";
    */

    public static void conectar() {

        Scanner sc = new Scanner(System.in);

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE)) {

            boolean existe = false;
            String nombre = "";

            do {

                System.out.print("\n usuario: ");
                String usuario = sc.nextLine();

                String sql = "SELECT * FROM empleado";

                try (PreparedStatement ps = conexion.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery()) {

                    while (rs.next()) {

                        if(Integer.parseInt(usuario) == rs.getInt("id")) {

                            nombre = rs.getString("nombre").toUpperCase();
                            existe = true;
                        } else {

                            break;
                        }   
                    }
                } 
            } while (!existe);

            do {

                System.out.print("\n contraseña: ");
                String clave = sc.nextLine();

                String sql = "SELECT * FROM empleado";

                try (PreparedStatement ps = conexion.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery()) {

                    while (rs.next()) {

                        if(clave.equals(rs.getString("contrasenya"))) {

                            existe = true;
                        } else {

                            existe = false;
                        }   
                    }
                } 
            } while (!existe);

            System.out.println("\n\tHOLA " + nombre + "\n");
        
        } catch(SQLException e) {

            System.out.println(" Error de conexión a la base de datos: " + e.getMessage());
        }
        
    }
    public static void main(String[] args) { 
        
        System.out.println("\n\tSTOCKERTY");
        
        conectar();

        System.out.println("\n 1 - Recepción (deshabilitado)");
        System.out.println("\n 2 - Expedición (deshabilitado)");
        System.out.println("\n 3 - Pedidos (deshabilitado)");
        System.out.println("\n 4 - Almacén (deshabilitado)");
        System.out.println("\n 5 - Histórico (deshabilitado)");
        System.out.println("\n 6 - Administrador");
        System.out.println("\n 7 - Salir");

        System.out.print("\n Introduce una opción: ");
        Scanner sc = new Scanner(System.in);
        int opcion = sc.nextInt();

        switch (opcion) {
            case 1:
                break;

            case 2:
                break;
        
            case 3:
                break;

            case 4:
                break;

            case 5:
                break;

            case 6:

                System.out.println("\n\t - Administrador -");
                break;

            case 7:
                
            System.exit(0);

            default:
                break;
        }

        sc.close();
    }
}