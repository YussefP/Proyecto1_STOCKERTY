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

    // Comandos ANSI para el diseño de la consola

    public static final String NEGRITA = "\u001B[1m";
    public static final String NARANJA = "\u001B[1;38;5:208m";
    public static final String VERDE = "\u001B[32m";
    public static final String VERDE_NEGRITA = "\u001B[1;32m";
    public static final String ROJO = "\u001B[31m";
    public static final String ROJO_NEGRITA = "\u001B[1;31m";
    public static final String GRIS_OSCURO = "\u001B[90m";
    public static final String GRIS_NEGRITA = "\u001B[1;90m";
    public static final String RESET = "\u001B[0m";

    // Variable para diferenciar privilegios

    public static boolean esGestor = false;

    // Métodos

    /**
     * Método para conectar con la base de datos
     */
    public static void conectar() {

        Scanner sc = new Scanner(System.in);

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE)) {

            boolean existe = false;
            String nombre = "";

            do {

                System.out.print(VERDE + "\n usuario: " + RESET);
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

                System.out.print(VERDE + "\n contraseña: " + RESET);
                String clave = sc.nextLine();

                String sql = "SELECT * FROM empleado";

                try (PreparedStatement ps = conexion.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery()) {

                    while (rs.next()) {

                        if(clave.equals(rs.getString("contrasenya"))) {

                            existe = true;
                            String tipo = rs.getString("tipo");

                            if(tipo.equals("gestor")) {
                                esGestor = true;
                            } else {
                                break;
                            }
                        } else {

                            existe = false;
                        }   
                    }
                } 
            } while (!existe);



            nuevaPantalla();
            System.out.println(VERDE + "\n\tHOLA " + nombre + "\n" + RESET);
        
        } catch(SQLException e) {

            System.out.println(" Error de conexión a la base de datos: " + e.getMessage());
        }
        
    }

    public static void nuevaPantalla() {

        System.out.println("\033[H\033[2J");
        System.out.flush();

    }
    public static void main(String[] args) { 
        
        System.out.println(VERDE_NEGRITA + "\n\n\t\tSTOCKERTY" + RESET);
        
        Scanner sc = new Scanner(System.in);

        System.out.println(VERDE + "\n\n Pulsa ENTER para acceder al programa..." + RESET);
        sc.nextLine();

        nuevaPantalla();
        
        conectar();
        System.out.println(VERDE + "==============================================");
        System.out.println("\t\tMENU PRINCIPAL");
        System.out.println("==============================================");
        System.out.println("\n\t1-RECEPCIÓN (deshabilitado)");
        System.out.println("\n\t2-EXPEDICIÓN (deshabilitado)");
        System.out.println("\n\t3-PEDIDOS (deshabilitado)");
        System.out.println("\n\t4-ALMACÉN (deshabilitado)");
        System.out.println("\n\t5-HISTÓRICO (deshabilitado)");
        System.out.println("\n\t6-ADMINISTRADOR");
        System.out.println("\n\t7-SALIR");

        System.out.print("\n Introduce una opción: " + RESET);
        
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

                nuevaPantalla();
                System.out.println(VERDE + "==============================================");
                System.out.println("\t\tADMINISTRADOR");
                System.out.println("==============================================");

                if (esGestor) {
                    System.out.println("\n\t1-PERFIL (deshabilitado)");
                    System.out.println("\n\t2-CAMBIAR CONTRASEÑA (deshabilitado)");
                    System.out.println("\n\t3-GESTIONAR USUARIOS (deshabilitado)");
                    System.out.println("\n\t4-SALIR (deshabilitado)");

                    System.out.print("\n Introduce una opción: " + RESET);
                    int admOpcion = sc.nextInt();

                    switch (admOpcion) {
                    case 1:
                        break;

                    case 2:
                        break;
                
                    case 3:

                        nuevaPantalla();
                        System.out.println(VERDE + "==============================================");
                        System.out.println("\t\tGESTIONAR USUARIOS");
                        System.out.println("==============================================" + RESET);

                    case 4:
                        
                        System.exit(0);
                        break;
                    }

                } else {
                    System.out.println("\n\t1-PERFIL (deshabilitado)");
                    System.out.println("\n\t2-CAMBIAR CONTRASEÑA (deshabilitado)");
                    System.out.println("\n\t3-SALIR (deshabilitado)");

                    System.out.print("\n Introduce una opción: " + RESET);
                }

            case 7:
                
            System.exit(0);

            default:
                break;
        }

        sc.close();
    }
}