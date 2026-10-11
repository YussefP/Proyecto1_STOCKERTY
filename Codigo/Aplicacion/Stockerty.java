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

    // Variable para opcion de menú

    public static int opcion;

    //============================================
    //                  Métodos
    //============================================

    /**
     * Método para conectar con la base de datos
     */
    public static void conectar() {

        Scanner sc = new Scanner(System.in);

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE)) {

            boolean existe = false;
            String nombre = "";
            int idUsuario;

            do {
                System.out.print(VERDE + "\n usuario: " + RESET);
                String usuario = sc.nextLine();
                System.out.println(usuario);
                String sql = "SELECT * FROM empleado";
                
                try (PreparedStatement ps = conexion.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery()) {
                        
                    while (rs.next()) {
                        
                        if(Integer.parseInt(usuario) == rs.getInt("id")) {
                            
                            nombre = rs.getString("nombre").toUpperCase();
                            idUsuario = Integer.parseInt(usuario);
                            existe = true;
                            
                            do {
                                System.out.print(VERDE + "\n contraseña: " + RESET);
                                String clave = sc.nextLine();

                                String sql2 = "SELECT * FROM empleado WHERE id = ?";

                                try (PreparedStatement pst = conexion.prepareStatement(sql2)) {

                                    pst.setInt(1, idUsuario);
                                    
                                    ResultSet rst = pst.executeQuery();

                                    while (rst.next()) {
                                        
                                        if(clave.equals(rst.getString("contrasenya"))) {
                                            
                                            existe = true;
                                            String tipo = rst.getString("tipo");
                                            
                                            if(tipo.equals("gestor")) {
                                                esGestor = true;
                                            }
                                        } else {
                                            existe = false;
                                        }   
                                    }
                                } 
                            } while (!existe);
                        } else {
                            ;
                        }   
                    }
                    
                } 
            } while (!existe);

            nuevaPantalla();
            System.out.println(VERDE + "\tHOLA " + nombre + "\n" + RESET);
        
        } catch(SQLException e) {

            System.out.println(" Error de conexión a la base de datos: " + e.getMessage());
            System.exit(0);
        } 
    }

    /**
     * Método para limpiar la pantalla
     */
    public static void nuevaPantalla() {

        System.out.println("\033[H\033[2J");
        System.out.flush();
        System.out.println("\n\n");
    }

    /**
     * Método para abandonar el programa
     */
    public static void salir() {

        nuevaPantalla();
        System.out.print("\n\n\t¿Salir del programa? s/n: ");
        Scanner sc = new Scanner(System.in);
        String salida = sc.nextLine();

        if(salida.toLowerCase().equals("s")) {
            System.out.println(VERDE + "\n\t¡HASTA PRONTO!\n" + RESET);
            System.exit(0);

        } else {
            opcion = 0;
            nuevaPantalla();
        }
    }

    public static void gestionarUsuarios() {

        do {
            try {
                nuevaPantalla();
                System.out.println(VERDE + "==============================================");
                System.out.println("\t\tGESTIONAR USUARIOS");
                System.out.println("==============================================");
                System.out.println("\n\t1-CREAR USUARIO");
                System.out.println("\n\t2-BORRAR USUARIO");
                System.out.println("\n\t3-MODIFICAR USUARIO");
                System.out.println("\n\t4-VER USUARIOS");
                System.out.println(GRIS_OSCURO + "\n\t5-OTRO (deshabilitado)" + RESET);
                System.out.println(VERDE + "\n\t6-SALIR");

                System.out.print("\n Introduce una opción: " + RESET);
                
                Scanner sc = new Scanner(System.in);
                opcion = Integer.parseInt(sc.nextLine());

                if(opcion < 1 || opcion > 6) {
                    nuevaPantalla();
                    System.out.println(ROJO + "\tError, opción no válida\n" + RESET); 
                }
            } catch(Exception e) {

                nuevaPantalla();
                System.out.println(ROJO + "\tError, introduce un número [1-6]\n" + RESET);
            }
            switch (opcion) {
                case 1:
                    try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE)) {

                        boolean error = false;
                        do {
                            try {
                                System.out.print(VERDE + "\n NUEVO EMPLEADO ");
                                Scanner sc = new Scanner(System.in);

                                System.out.print("\n Nombre: " + RESET);
                                String nombreEmpleado = sc.nextLine();

                                System.out.print(VERDE + "\n Apellidos: " + RESET);
                                String apellidosEmpleado = sc.nextLine();

                                System.out.print(VERDE + "\n NIF/DNI: " + RESET);
                                String nifEmpleado = sc.nextLine();

                                System.out.print(VERDE + "\n Dirección: " + RESET);
                                String direccionEmpleado = sc.nextLine();

                                System.out.print(VERDE + "\n Email: " + RESET);
                                String emailEmpleado = sc.nextLine();

                                System.out.print(VERDE + "\n Teléfono: " + RESET);
                                String telefonoEmpleado = sc.nextLine();

                                System.out.print(VERDE + "\n Puesto: " + RESET);
                                String puestoEmpleado = sc.nextLine();

                                System.out.print(VERDE + "\n Departamento: " + RESET);
                                int departamentoEmpleado = Integer.parseInt(sc.nextLine());

                                System.out.print(VERDE + "\n Privilegios (gestor/operario): " + RESET);
                                String tipoEmpleado = sc.nextLine();

                                String sql = "INSERT INTO empleado (contrasenya, nif, nombre, apellidos, puesto, direccion, email, telefono, id_departamento, tipo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

                                try (PreparedStatement ps = conexion.prepareStatement(sql)) {

                                    ps.setString(1, apellidosEmpleado.replaceAll("\\s+", ""));
                                    ps.setString(2, nifEmpleado);
                                    ps.setString(3, nombreEmpleado);
                                    ps.setString(4, apellidosEmpleado);
                                    ps.setString(5, puestoEmpleado);
                                    ps.setString(6, direccionEmpleado);
                                    ps.setString(7, emailEmpleado);
                                    ps.setString(8, telefonoEmpleado);
                                    ps.setInt(9, departamentoEmpleado);
                                    ps.setString(10, tipoEmpleado);

                                    int filas = ps.executeUpdate();

                                    int idEmpleado = 0;

                                    sql = "SELECT * FROM empleado";

                                    try (PreparedStatement pst = conexion.prepareStatement(sql);
                                        ResultSet rs = pst.executeQuery()) {

                                        while (rs.next()) {

                                        idEmpleado = rs.getInt("id");
                                        }
                                    }

                                    if(filas > 0) {
                                        System.out.println(VERDE + "\n\t¡Nuevo empleado creado!" + RESET);
                                        System.out.println("\n ID   ->  " + idEmpleado);
                                        System.out.println("\n Clave   ->  " + apellidosEmpleado.replaceAll("\\s+", ""));
                                        System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                        sc.nextLine();

                                    } else {
                                        System.out.println(ROJO + "\n\tError al crear el empleado" + RESET);
                                    }
                                }
                                
                            } catch(Exception e) {
                                System.out.println(ROJO + "\n\tError en la introducción de datos" + e.getMessage() + RESET);
                                error = true;
                            }

                        } while(error);
                    
                    } catch(SQLException e) {

                        System.out.println(" Error de conexión a la base de datos: " + e.getMessage());
                    }

                case 2:
                    break;
            
                case 3:
                    break;

                case 4:
                    break;

                case 5:
                    break;

                case 6:
                   salir();

                default:
                    break;
            }
        } while(opcion < 1 || opcion > 6);
    }

    //========================================
    //          PROGRAMA PRINCIPAL
    //========================================
    public static void main(String[] args) { 
        
        nuevaPantalla();
        System.out.println(VERDE_NEGRITA + "\t\tSTOCKERTY" + RESET);
        System.out.println(VERDE + "\n\n Pulsa ENTER para acceder al programa..." + RESET);

        Scanner sc = new Scanner(System.in);
        sc.nextLine();

        nuevaPantalla();
        conectar();

        do {
            try {
                System.out.println(VERDE + "==============================================");
                System.out.println("\t\tMENU PRINCIPAL");
                System.out.println("==============================================");
                System.out.println(GRIS_OSCURO + "\n\t1-RECEPCIÓN (deshabilitado)");
                System.out.println("\n\t2-EXPEDICIÓN (deshabilitado)");
                System.out.println("\n\t3-PEDIDOS (deshabilitado)");
                System.out.println("\n\t4-ALMACÉN (deshabilitado)");
                System.out.println("\n\t5-HISTÓRICO (deshabilitado)" + RESET);
                System.out.println(VERDE + "\n\t6-ADMINISTRADOR");
                System.out.println("\n\t7-SALIR");

                System.out.print("\n Introduce una opción: " + RESET);
                
                opcion = Integer.parseInt(sc.nextLine());

                if(opcion < 1 || opcion > 7) {
                    nuevaPantalla();
                    System.out.println(ROJO + "\tError, opción no válida\n" + RESET); 
                }
            } catch(Exception e) {

                nuevaPantalla();
                System.out.println(ROJO + "\tError, introduce un número [1-7]\n" + RESET);
            }
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

                    do {
                        nuevaPantalla();
                        System.out.println(VERDE + "==============================================");
                        System.out.println("\t\tADMINISTRADOR");
                        System.out.println("==============================================");

                        if (esGestor) {
                            try {
                                System.out.println(GRIS_OSCURO + "\n\t1-PERFIL (deshabilitado)");
                                System.out.println("\n\t2-CAMBIAR CONTRASEÑA (deshabilitado)" + RESET);
                                System.out.println(VERDE + "\n\t3-GESTIONAR USUARIOS");
                                System.out.println("\n\t4-SALIR ");

                                System.out.print("\n Introduce una opción: " + RESET);

                                opcion = Integer.parseInt(sc.nextLine());

                                if(opcion < 1 || opcion > 4) {
                                    nuevaPantalla();
                                    System.out.println(ROJO + "\n\tError, introduce una opción válida" + RESET);
                                }
                            } catch(Exception e) {
                                nuevaPantalla();
                                System.out.println(ROJO + "\n\tError, introduce un número [1-4]" + RESET);
                            }
                            switch (opcion) {
                                case 1:
                                    break;

                                case 2:
                                    break;
                            
                                case 3:
                                    gestionarUsuarios();
                                    break;

                                case 4: 
                                    salir();

                                default:
                                    break;
                            }  
                        } else {
                            System.out.println("\n\t1-PERFIL (deshabilitado)");
                            System.out.println("\n\t2-CAMBIAR CONTRASEÑA (deshabilitado)");
                            System.out.println("\n\t3-SALIR (deshabilitado)");

                            System.out.print("\n Introduce una opción: " + RESET);
                        }
                    } while(opcion > 0 || opcion < 5);
                case 7:
                    salir();

                default:
                    break;
            }
        } while(opcion < 1 || opcion > 7);

        sc.close();
    }
}