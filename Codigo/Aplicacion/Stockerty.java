/*
Proyecto Intermodular 1 - Stockerty
v.1.0
Aplicación para la gestión logística
 */
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.Arrays;

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

    // Variables para opcion de menú

    public static int opcion;
    public static boolean error = false;

    //============================================
    //                  Métodos
    //============================================

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

    /**
     * Método para la opción de menú Gestionar Usuarios (sólo administradores/gestores)
     */
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
                System.out.println("\n\t4-VER USUARIO");
                System.out.println(GRIS_OSCURO + "\n\t5-OTRO (deshabilitado)" + RESET);
                System.out.println(VERDE + "\n\t6-SALIR");
                System.out.println("\n <- Volver [0]");
                System.out.println("==============================================");

                System.out.print("\n Introduce una opción: " + RESET);
                
                Scanner sc = new Scanner(System.in);
                opcion = Integer.parseInt(sc.nextLine());

                if(opcion < 1 || opcion > 6) {
                    if(opcion == 0) {
                        nuevaPantalla();
                        break;
                    } else {
                        nuevaPantalla();
                        System.out.println(ROJO + "\tError, opción no válida\n" + RESET); 
                    } 
                } else {
                    switch (opcion) {
                        case 1:
                            nuevaPantalla();
                            System.out.println(VERDE + "----------------------------------------------");
                            System.out.println("\t\tCREAR USUARIO");
                            System.out.println("----------------------------------------------");

                            Empleado nuevoEmpleado = new Empleado("00000000X", "X", "X", "X", "X","x@x.xx", "700000000", 0, "operario");

                            do{
                                try {

                                    System.out.println("\n Introduce los datos del nuevo usuario");
                                    System.out.println("\n NIF o DNI: " + RESET);
                                    nuevoEmpleado.setNif(sc.nextLine());

                                    error = false;

                                } catch(Exception e) {
                                    System.out.println(ROJO + e.getMessage() + RESET);
                                    error = true;
                                }
                            } while(error);
                            

                            BD.insertarEmpleado(nuevoEmpleado);
                            break;

                        case 2:
                            nuevaPantalla();
                            System.out.println(VERDE + "----------------------------------------------");
                            System.out.println("\t\tBORRAR USUARIO");
                            System.out.println("----------------------------------------------" + RESET);

                            do {
                                try {
                                    System.out.print(VERDE + "\n Introduce el ID del usuario que quieres borrar: " + RESET);
                                    int id = Integer.parseInt(sc.nextLine());

                                    // borrarUsuario(id);
                                    error = false;

                                    if(opcion == 0) {
                                        nuevaPantalla();
                                    } else {
                                        System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                        sc.nextLine();
                                    }
                                } catch (Exception e) {
                                    System.out.println(ROJO + "\n\tError, se necesita un ID numérico" + RESET);
                                    error = true;
                                }
                            } while(error);
                            break;
                    
                        case 3:
                            nuevaPantalla();
                            System.out.println(VERDE + "----------------------------------------------");
                            System.out.println("\t\tMODIFICAR USUARIO");
                            System.out.println("----------------------------------------------" + RESET);

                            do {
                                try {
                                    System.out.print(VERDE + "\n Introduce el ID del usuario que quieres modificar: " + RESET);
                                    int id = Integer.parseInt(sc.nextLine());

                                    // modificarUsuario(id);
                                    error = false;

                                    System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                    sc.nextLine();

                                } catch (Exception e) {
                                    System.out.println(ROJO + "\n\tError, se necesita un ID numérico" + RESET);
                                    error = true;
                                }
                            } while(error);
                            break;

                        case 4:
                            nuevaPantalla();
                            System.out.println(VERDE + "----------------------------------------------");
                            System.out.println("\t\tVER USUARIO");
                            System.out.println("----------------------------------------------" + RESET);

                            do {
                                try {
                                    System.out.print(VERDE + "\n Introduce el ID del usuario que quieres mostrar: " + RESET);
                                    int id = Integer.parseInt(sc.nextLine());

                                    // mostrarUsuario(id);
                                    error = false;

                                    System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                    sc.nextLine();

                                } catch (Exception e) {
                                    System.out.println(ROJO + "\n\tError, se necesita un ID numérico" + RESET);
                                    error = true;
                                }
                            } while(error);
                            break;

                        case 5:
                            break;

                        case 6:
                        salir();

                        default:
                            break;
                    }
                }
            } catch(Exception e) {

                nuevaPantalla();
                System.out.println(ROJO + "\tError, introduce un número [1-6]\n" + RESET);
            }
            if(opcion == 0) break;

        } while(opcion > 0 || opcion < 7);
    }

    /**
     * Método para introducir un nuevo producto en la base de datos
     */
    public static void introducirProducto() {

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE)) {

            do {
                try {
                    System.out.print(VERDE + "\n NUEVO PRODUCTO ");
                    Scanner sc = new Scanner(System.in);

                    System.out.print("\n EAN: " + RESET);
                    String eanProducto = sc.nextLine();

                    System.out.print(VERDE + "\n Nombre: " + RESET);
                    String nombreProducto = sc.nextLine();

                    System.out.print(VERDE + "\n Descripción: " + RESET);
                    String descripcionProducto = sc.nextLine();

                    System.out.print(VERDE + "\n Precio: " + RESET);
                    String precio = sc.nextLine();
                    BigDecimal precioProducto = new BigDecimal(precio);

                    System.out.print(VERDE + "\n Stock: " + RESET);
                    int stockProducto = Integer.parseInt(sc.nextLine());

                    System.out.print(VERDE + "\n Stock mínimo: " + RESET);
                    int stockMinimo = Integer.parseInt(sc.nextLine());

                    String sql = "INSERT INTO producto (ean, nombre, descripcion, precio, stock, stock_minimo) VALUES (?, ?, ?, ?, ?, ?);";

                    try (PreparedStatement ps = conexion.prepareStatement(sql)) {

                        ps.setString(1, eanProducto);
                        ps.setString(2, nombreProducto);
                        ps.setString(3, descripcionProducto);
                        ps.setBigDecimal(4, precioProducto);
                        ps.setInt(5, stockProducto);
                        ps.setInt(6, stockMinimo);

                        int filas = ps.executeUpdate();

                        int idProducto = 0;

                        sql = "SELECT id FROM producto";

                        try (PreparedStatement pst = conexion.prepareStatement(sql);
                            ResultSet rs = pst.executeQuery()) {

                            while (rs.next()) {

                            idProducto = rs.getInt("id");
                            }
                        }
                        if(filas > 0) {
                            System.out.println(VERDE + "\n\t¡Nuevo producto insertado!");
                            System.out.println("\n---------------------------------------------------");
                            System.out.println(" ID   ->  " + RESET + idProducto);
                            System.out.println(VERDE + " Nombre   ->  " + RESET + nombreProducto);
                            System.out.println(VERDE + " Stock   ->  " + RESET + stockProducto);
                            System.out.println(VERDE + "---------------------------------------------------" + RESET);

                        } else {
                            System.out.println(ROJO + "\n\tError al introducir el producto" + RESET);
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
    }

    /**
     * Método para borrar un producto introduciendo su número ID
     * @param id
     */
    public static void borrarProducto(int id) {

        mostrarProducto(id);

        if(error) {
            ;
        } else {
            System.out.print(VERDE + "\n ¿Estas seguro de eliminar el producto " + id + "? s/n: " + RESET);
            Scanner sc = new Scanner(System.in);
            String validar = sc.nextLine();

            if(validar.toLowerCase().equals("s")) {

                String sql = "DELETE FROM producto WHERE id = " + id;
            
                try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
                    PreparedStatement ps = conexion.prepareStatement(sql)) {

                    int filas = ps.executeUpdate();

                    if (filas > 0) {
                        System.out.println(VERDE + "\n ¡Producto eliminado con éxito!" + RESET);
                    } else {
                        System.out.println(ROJO + "\n El id no pertenece a ningún producto" + RESET);
                    }
                } catch(SQLException e) {
                    System.out.println(ROJO + "\n\tError de conexión con la base de datos: " + e.getMessage() + RESET);
                }
            } else {
                opcion = 0;
            }
        }
        error = false;
    }

    /**
     * Método para mostrar los datos de un producto mediante la introducción de su número ID
     * @param id
     */
    public static void mostrarProducto(int id) {

        String sql = "SELECT * FROM producto WHERE id = " + id;

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            String[] columnas = {"id", "ean", "nombre", "descripcion", "precio", "stock", "stock_minimo", "id_ubicacion"};
            
            System.out.println(VERDE + "\n-----------------------------------------------------------------------" + RESET);
            
            ResultSet rs = ps.executeQuery();

            if(!rs.next()){
                System.out.println(ROJO + "\n\tNo se ha encontrado un producto con este ID\n" + RESET);
                error = true;
            } else {
                for (String columna : columnas) {

                    rs = ps.executeQuery();

                    if(rs.next()) {
                        System.out.print(VERDE + " " + columna.toUpperCase() + "\t" + RESET);

                        for (String column : columnas) {

                            if(columna.equals(column)) {

                                if(columna.length() < 8) {
                                    System.out.println(VERDE + "\t\t->\t" + RESET + rs.getString(column));
                                } else if(columna.length() == 8 || columna.length() < 15) {
                                    System.out.println(VERDE + "\t->\t" + RESET + rs.getString(column));
                                } else {
                                    System.out.println(VERDE + "->\t" + RESET + rs.getString(column));
                                }
                            } else {
                                ;
                            }
                        }
                    } else {
                        System.out.println(ROJO + "\n\tNo se ha encontrado un producto con este ID" + RESET);
                        error = true;
                    }
                }
            } 
            System.out.println(VERDE + "-----------------------------------------------------------------------" + RESET);
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError en la conexión de la base de datos: " + e.getMessage() + RESET);
        }
    }

    /**
     * Método para modificar uno de los datos del producto introduciendo su número ID
     * @param id
     */
    public static void modificarProducto(int id) {

        mostrarProducto(id);

        if(error) {
            ;
        } else {
            do {
                try {
                    System.out.print(VERDE + "\n ¿Qué dato quieres modificar?: " + RESET);
                    Scanner sc = new Scanner(System.in);
                    String dato = sc.nextLine().toLowerCase();
                    
                    String[] columnas = {"id", "ean", "nombre", "descripcion", "precio", "stock", "stock_minimo", "id_ubicacion"};
                    
                    for(String column : columnas) {

                        if(dato.equals(column)) {

                            if(dato.equals("id")) {

                                System.out.println(ROJO + "\n\tNo se puede modificar el ID del producto" + RESET);
                                error = true;

                            } else if(dato.equals("ean") || dato.equals("stock") || dato.equals("stock_minimo") || dato.equals("id_ubicacion")) {

                                // Si el dato es Stock, enlazar con forzar stock (nueva tabla modificaciones de stock o almacenar idUsuario)

                                System.out.print(VERDE + "\n Nuevo " + dato + ": " + RESET);
                                int valor = Integer.parseInt(sc.nextLine());

                                String sql = "UPDATE producto SET " + dato + " = " + valor + " WHERE id = " + id;

                                try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
                                    PreparedStatement ps = conexion.prepareStatement(sql)) {

                                    int filas = ps.executeUpdate();

                                    if (filas > 0) {
                                        System.out.println(VERDE + "\n ¡Dato modificado con éxito!" + RESET);
                                        error = false;
                                    } else {
                                        System.out.println(ROJO + "\n Error al modificar el dato" + RESET);
                                        error = true;
                                    }
                                } catch(SQLException e) {
                                    System.out.println(ROJO + "\n\tError de conexión con la base de datos: " + e.getMessage() + RESET);
                                }
                            } else if(dato.equals("precio")) {

                                System.out.print(VERDE + "\n Nuevo " + dato + ": " + RESET);
                                String precioNuevo = sc.nextLine();
                                BigDecimal valor = new BigDecimal(precioNuevo);

                                String sql = "UPDATE producto SET " + dato + " = " + valor + " WHERE id = " + id;

                                try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
                                    PreparedStatement ps = conexion.prepareStatement(sql)) {

                                    int filas = ps.executeUpdate();

                                    if (filas > 0) {
                                        System.out.println(VERDE + "\n ¡Dato modificado con éxito!" + RESET);
                                        error = false;
                                    } else {
                                        System.out.println(ROJO + "\n Error al modificar el dato" + RESET);
                                        error = true;
                                    }
                                } catch(SQLException e) {
                                    System.out.println(ROJO + "\n\tError de conexión con la base de datos: " + e.getMessage() + RESET);
                                }

                            }else {
                                System.out.print(VERDE + "\n Nuevo " + dato + ": " + RESET);
                                String info = sc.nextLine();

                                String sql = "UPDATE empleado SET " + dato + " = ? WHERE id = " + id;

                                try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
                                    PreparedStatement ps = conexion.prepareStatement(sql)) {

                                    ps.setString(1, info);
                                    int filas = ps.executeUpdate();

                                    if (filas > 0) {
                                        System.out.println(VERDE + "\n ¡Dato modificado con éxito!" + RESET);
                                        error = false;
                                    } else {
                                        System.out.println(ROJO + "\n Error al modificar el dato" + RESET);
                                        error = true;
                                    }
                                } catch(SQLException e) {
                                    System.out.println(ROJO + "\n\tError de conexión con la base de datos: " + e.getMessage() + RESET);
                                    error = true;
                                    break;
                                }
                            }
                            break;
                        } else {
                            ;
                        }
                    }
                    if(!Arrays.asList(columnas).contains(dato)) {
                        System.out.println(ROJO + "\n\tDato no encontrado" + RESET);
                        error = true;
                    } 
                } catch(Exception e) {
                    System.out.println(ROJO + "\n\tError, introduce una opción válida" + RESET);
                    error = true;
                }
            } while(error);
        }
        error = false;
    }


    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
    //========================================
    //          PROGRAMA PRINCIPAL
    //========================================

    public static void main(String[] args) { 
        
        nuevaPantalla();
        System.out.println(VERDE_NEGRITA + "\t\t\tSTOCKERTY" + RESET);
        System.out.println(VERDE + "\n\n\t\tPulsa ENTER para acceder al programa..." + RESET);

        Scanner sc = new Scanner(System.in);
        sc.nextLine();

        nuevaPantalla();
        
        System.out.print(VERDE + "\n\t\tUsuario: " + RESET);
        int idUsuario = Integer.parseInt(sc.nextLine());
        
        Empleado usuarioActual = BD.conectar(idUsuario);

        do {
            try {
                System.out.println(VERDE + "==============================================");
                System.out.println("\t\tMENU PRINCIPAL");
                System.out.println("==============================================");
                System.out.println(GRIS_OSCURO + "\n\t1-RECEPCIÓN (deshabilitado)");
                System.out.println("\n\t2-EXPEDICIÓN (deshabilitado)");
                System.out.println("\n\t3-PEDIDOS (deshabilitado)");
                System.out.println(VERDE + "\n\t4-ALMACÉN");
                System.out.println("\n\t5-PRODUCTOS");
                System.out.println(GRIS_OSCURO + "\n\t6-HISTÓRICO (deshabilitado)");
                System.out.println(VERDE + "\n\t7-ADMINISTRADOR");
                System.out.println("\n\t8-SALIR");
                System.out.println("==============================================");

                System.out.print("\n Introduce una opción: " + RESET);
                
                opcion = Integer.parseInt(sc.nextLine());

                if(opcion < 1 || opcion > 8) {
                    nuevaPantalla();
                    System.out.println(ROJO + "\tError, opción no válida\n" + RESET); 
                }
                switch (opcion) {
                    case 1:
                        opcion = 0;
                        break;

                    case 2:
                        opcion = 0;
                        break;
                
                    case 3:
                        opcion = 0;
                        break;

                    case 4:
                        do {
                            nuevaPantalla();
                            System.out.println(VERDE + "==============================================");
                            System.out.println("\t\tALMACÉN");
                            System.out.println("==============================================");

                            try {
                                System.out.println(VERDE + "\n\t1-CREAR ESTRUCTURA");
                                System.out.println("\n\t2-ELIMINAR ALMACÉN");
                                System.out.println("\n\t3-MODIFICAR ELEMENTOS");
                                System.out.println("\n\t4-VER ALMACÉN");
                                System.out.println("\n\t5-SALIR");
                                System.out.println("\n <- Volver [0]");
                                System.out.println("==============================================");

                                System.out.print("\n Introduce una opción: " + RESET);
                                
                                opcion = Integer.parseInt(sc.nextLine());

                                if(opcion < 1 || opcion > 5) {
                                    if(opcion == 0) {
                                        nuevaPantalla();
                                        break;
                                    } else {
                                        nuevaPantalla();
                                        System.out.println(ROJO + "\n\tError, introduce una opción válida" + RESET);
                                    }
                                    
                                } else {
                                    switch (opcion) {
                                        case 1:
                                            do {
                                                nuevaPantalla();
                                                System.out.println(VERDE + "==============================================");
                                                System.out.println("\t\tCREAR ESTRUCTURA");
                                                System.out.println("==============================================");

                                                try {
                                                    System.out.println("\n\t1-CREAR ZONA");
                                                    System.out.println("\n\t2-CREAR PASILLO");
                                                    System.out.println("\n\t3-CREAR RACK");
                                                    System.out.println("\n\t4-CREAR UBICACIÓN");
                                                    System.out.println("\n\t5-SALIR");
                                                    System.out.println("\n <- Volver [0]");
                                                    System.out.println("==============================================");

                                                    System.out.print("\n Introduce una opción: " + RESET);
                                                    
                                                    opcion = Integer.parseInt(sc.nextLine());

                                                    if(opcion < 1 || opcion > 5) {
                                                        if(opcion == 0) {
                                                            nuevaPantalla();
                                                            break;
                                                        } else {
                                                            nuevaPantalla();
                                                            System.out.println(ROJO + "\n\tError, introduce una opción válida" + RESET);
                                                        }
                                                        
                                                    } else {
                                                        switch (opcion) {
                                                            case 1:
                                                            
                                                                nuevaPantalla();
                                                                System.out.println(VERDE + "----------------------------------------------");
                                                                System.out.println("\t\tCREAR ZONA");
                                                                System.out.println("----------------------------------------------" + RESET);
                                                                
                                                                do {
                                                                    try {
                                                                        // crearZona();
                                                                        error = false;

                                                                        System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                                                        sc.nextLine();

                                                                    } catch (Exception e) {
                                                                        System.out.println(ROJO + "\n\tError, introduce una opción válida" + RESET);
                                                                        error = true;
                                                                    }
                                                                } while(error);
                                                                break;

                                                            case 2:
                                                                
                                                                nuevaPantalla();
                                                                System.out.println(VERDE + "----------------------------------------------");
                                                                System.out.println("\t\tCREAR PASILLO");
                                                                System.out.println("----------------------------------------------" + RESET);

                                                                do {
                                                                    try {
                                                                        // crearPasillo();
                                                                        error = false;

                                                                        System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                                                        sc.nextLine();

                                                                    } catch (Exception e) {
                                                                        System.out.println(ROJO + "\n\tError, introduce una opción válida" + RESET);
                                                                        error = true;
                                                                    }
                                                                } while(error);
                                                                break;
                                                        
                                                            case 3:
                                                                nuevaPantalla();
                                                                System.out.println(VERDE + "----------------------------------------------");
                                                                System.out.println("\t\tCREAR RACK");
                                                                System.out.println("----------------------------------------------" + RESET);

                                                                do {
                                                                    try {
                                                                        // crearRack();
                                                                        error = false;

                                                                        System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                                                        sc.nextLine();

                                                                    } catch (Exception e) {
                                                                        System.out.println(ROJO + "\n\tError, introduce una opción válida" + RESET);
                                                                        error = true;
                                                                    }
                                                                } while(error);
                                                                break;

                                                            case 4: 
                                                                nuevaPantalla();
                                                                System.out.println(VERDE + "----------------------------------------------");
                                                                System.out.println("\t\tCREAR UBICACIÓN");
                                                                System.out.println("----------------------------------------------" + RESET);

                                                                do {
                                                                    try {
                                                                        // crearUbicacion();
                                                                        error = false;

                                                                        System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                                                        sc.nextLine();

                                                                    } catch (Exception e) {
                                                                        System.out.println(ROJO + "\n\tError, introduce una opción válida" + RESET);
                                                                        error = true;
                                                                    }
                                                                } while(error);
                                                                break;

                                                            case 5:
                                                                salir();

                                                            default:
                                                                break;
                                                        }
                                                    }
                                                } catch(Exception e) {
                                                    nuevaPantalla();
                                                    System.out.println(ROJO + "\n\tError, introduce un número [1-5]" + RESET);
                                                }
                                                if(opcion == 0) break;
                                            } while(opcion > 0 || opcion < 6);
                                            break;

                                        case 2:
                                            do {
                                                nuevaPantalla();
                                                System.out.println(VERDE + "==============================================");
                                                System.out.println("\t\tELIMINAR ALMACÉN");
                                                System.out.println("==============================================");

                                                try {
                                                    System.out.println(VERDE + "\n\t1-ELIMINAR ALMACÉN COMPLETO");
                                                    System.out.println("\n\t2-ELIMINAR ZONA");
                                                    System.out.println("\n\t3-ELIMINAR PASILLO");
                                                    System.out.println("\n\t4-ELIMINAR RACK");
                                                    System.out.println("\n\t5-ELIMINAR UBICACIÓN");
                                                    System.out.println("\n\t6-SALIR");
                                                    System.out.println("\n <- Volver [0]");
                                                    System.out.println("==============================================");

                                                    System.out.print("\n Introduce una opción: " + RESET);
                                                    
                                                    opcion = Integer.parseInt(sc.nextLine());

                                                    if(opcion < 1 || opcion > 6) {
                                                        if(opcion == 0) {
                                                            nuevaPantalla();
                                                            break;
                                                        } else {
                                                            nuevaPantalla();
                                                            System.out.println(ROJO + "\n\tError, introduce una opción válida" + RESET);
                                                        }
                                                        
                                                    } else {
                                                        switch (opcion) {
                                                            case 1:
                                                                nuevaPantalla();
                                                                System.out.println(VERDE + "----------------------------------------------");
                                                                System.out.println("\t\tELIMINAR ALMACÉN COMPLETO");
                                                                System.out.println("----------------------------------------------" + RESET);

                                                                do {
                                                                    try {
                                                                        System.out.print(VERDE + "\n Introduce el ID del almacén que quieres eliminar: " + RESET);
                                                                        // int idAlmacen = Integer.parseInt(sc.nextLine());

                                                                        // borrarAlmacen(idAlmacen);
                                                                        error = false;

                                                                        if(opcion == 0) {
                                                                            nuevaPantalla();
                                                                        } else {
                                                                            System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                                                            sc.nextLine();
                                                                        }
                                                                    } catch (Exception e) {
                                                                        System.out.println(ROJO + "\n\tError, se necesita un ID numérico" + RESET);
                                                                        error = true;
                                                                    }
                                                                } while(error);
                                                                break;

                                                            case 2:
                                                                nuevaPantalla();
                                                                System.out.println(VERDE + "----------------------------------------------");
                                                                System.out.println("\t\tELIMINAR ZONA");
                                                                System.out.println("----------------------------------------------" + RESET);

                                                                do {
                                                                    try {
                                                                        System.out.print(VERDE + "\n Introduce el ID de la zona que quieres eliminar: " + RESET);
                                                                        // int idZona = Integer.parseInt(sc.nextLine());

                                                                        // borrarZona(idZona);
                                                                        error = false;

                                                                        if(opcion == 0) {
                                                                            nuevaPantalla();
                                                                        } else {
                                                                            System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                                                            sc.nextLine();
                                                                        }
                                                                    } catch (Exception e) {
                                                                        System.out.println(ROJO + "\n\tError, se necesita un ID numérico" + RESET);
                                                                        error = true;
                                                                    }
                                                                } while(error);
                                                                break;
                                                        
                                                            case 3:
                                                                nuevaPantalla();
                                                                System.out.println(VERDE + "----------------------------------------------");
                                                                System.out.println("\t\tELIMINAR PASILLO");
                                                                System.out.println("----------------------------------------------" + RESET);

                                                                do {
                                                                    try {
                                                                        System.out.print(VERDE + "\n Introduce el ID del pasillo que quieres eliminar: " + RESET);
                                                                        // int idPasillo = Integer.parseInt(sc.nextLine());

                                                                        // borrarPasillo(idPasillo);
                                                                        error = false;

                                                                        if(opcion == 0) {
                                                                            nuevaPantalla();
                                                                        } else {
                                                                            System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                                                            sc.nextLine();
                                                                        }
                                                                    } catch (Exception e) {
                                                                        System.out.println(ROJO + "\n\tError, se necesita un ID numérico" + RESET);
                                                                        error = true;
                                                                    }
                                                                } while(error);
                                                                break;

                                                            case 4: 
                                                                nuevaPantalla();
                                                                System.out.println(VERDE + "----------------------------------------------");
                                                                System.out.println("\t\tELIMINAR RACK");
                                                                System.out.println("----------------------------------------------" + RESET);

                                                                do {
                                                                    try {
                                                                        System.out.print(VERDE + "\n Introduce el ID del rack que quieres eliminar: " + RESET);
                                                                        // int idRack = Integer.parseInt(sc.nextLine());

                                                                        // borrarRack(idRack);
                                                                        error = false;

                                                                        if(opcion == 0) {
                                                                            nuevaPantalla();
                                                                        } else {
                                                                            System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                                                            sc.nextLine();
                                                                        }
                                                                    } catch (Exception e) {
                                                                        System.out.println(ROJO + "\n\tError, se necesita un ID numérico" + RESET);
                                                                        error = true;
                                                                    }
                                                                } while(error);
                                                                break;

                                                            case 5:
                                                                nuevaPantalla();
                                                                System.out.println(VERDE + "----------------------------------------------");
                                                                System.out.println("\t\tELIMINAR UBICACIÓN");
                                                                System.out.println("----------------------------------------------" + RESET);

                                                                do {
                                                                    try {
                                                                        System.out.print(VERDE + "\n Introduce el ID de la ubicación que quieres eliminar: " + RESET);
                                                                        // int idUbicacion = Integer.parseInt(sc.nextLine());

                                                                        // borrarUbicacion(idUbicacion);
                                                                        error = false;

                                                                        if(opcion == 0) {
                                                                            nuevaPantalla();
                                                                        } else {
                                                                            System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                                                            sc.nextLine();
                                                                        }
                                                                    } catch (Exception e) {
                                                                        System.out.println(ROJO + "\n\tError, se necesita un ID numérico" + RESET);
                                                                        error = true;
                                                                    }
                                                                } while(error);
                                                                break;

                                                            case 6:
                                                                salir();

                                                            default:
                                                                break;
                                                        }
                                                    }
                                                } catch(Exception e) {
                                                    nuevaPantalla();
                                                    System.out.println(ROJO + "\n\tError, introduce un número [1-6]" + RESET);
                                                }
                                                if(opcion == 0) break;
                                            } while(opcion > 0 || opcion < 7);
                                            break;
                                    
                                        case 3:
                                            do {
                                                nuevaPantalla();
                                                System.out.println(VERDE + "==============================================");
                                                System.out.println("\t\tMODIFICAR ELEMENTOS");
                                                System.out.println("==============================================");

                                                try {
                                                    System.out.println(VERDE + "\n\t1-MODIFICAR ZONA");
                                                    System.out.println("\n\t2-MODIFICAR PASILLO");
                                                    System.out.println("\n\t3-MODIFICAR RACK");
                                                    System.out.println("\n\t4-MODIFICAR UBICACIÓN");
                                                    System.out.println("\n\t5-SALIR");
                                                    System.out.println("\n <- Volver [0]");
                                                    System.out.println("==============================================");

                                                    System.out.print("\n Introduce una opción: " + RESET);
                                                    
                                                    opcion = Integer.parseInt(sc.nextLine());

                                                    if(opcion < 1 || opcion > 5) {
                                                        if(opcion == 0) {
                                                            nuevaPantalla();
                                                            break;
                                                        } else {
                                                            nuevaPantalla();
                                                            System.out.println(ROJO + "\n\tError, introduce una opción válida" + RESET);
                                                        }
                                                        
                                                    } else {
                                                        switch (opcion) {
                                                            case 1:
                                                                nuevaPantalla();
                                                                System.out.println(VERDE + "----------------------------------------------");
                                                                System.out.println("\t\tMODIFICAR ZONA");
                                                                System.out.println("----------------------------------------------" + RESET);

                                                                do {
                                                                    try {
                                                                        System.out.print(VERDE + "\n ID de la zona a modificar: " + RESET);
                                                                        // int idZona = Integer.parseInt(sc.nextLine());

                                                                        // modificarZona(idZona);
                                                                        error = false;

                                                                        System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                                                        sc.nextLine();

                                                                    } catch (Exception e) {
                                                                        System.out.println(ROJO + "\n\tError, se necesita un ID numérico" + RESET);
                                                                        error = true;
                                                                    }
                                                                } while(error);
                                                                break;

                                                            case 2:
                                                                
                                                                nuevaPantalla();
                                                                System.out.println(VERDE + "----------------------------------------------");
                                                                System.out.println("\t\tMODIFICAR PASILLO");
                                                                System.out.println("----------------------------------------------" + RESET);

                                                                do {
                                                                    try {
                                                                        System.out.print(VERDE + "\n ID del pasillo a modificar: " + RESET);
                                                                        // int idPasillo = Integer.parseInt(sc.nextLine());

                                                                        // modificarPasillo(idPasillo);
                                                                        error = false;

                                                                        System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                                                        sc.nextLine();

                                                                    } catch (Exception e) {
                                                                        System.out.println(ROJO + "\n\tError, se necesita un ID numérico" + RESET);
                                                                        error = true;
                                                                    }
                                                                } while(error);
                                                                break;
                                                        
                                                            case 3:
                                                                nuevaPantalla();
                                                                System.out.println(VERDE + "----------------------------------------------");
                                                                System.out.println("\t\tMODIFICAR RACK");
                                                                System.out.println("----------------------------------------------" + RESET);

                                                                do {
                                                                    try {
                                                                        System.out.print(VERDE + "\n ID del rack a modificar: " + RESET);
                                                                        // int idRack = Integer.parseInt(sc.nextLine());

                                                                        // modificarRack(idRack);
                                                                        error = false;

                                                                        System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                                                        sc.nextLine();

                                                                    } catch (Exception e) {
                                                                        System.out.println(ROJO + "\n\tError, se necesita un ID numérico" + RESET);
                                                                        error = true;
                                                                    }
                                                                } while(error);
                                                                break;

                                                            case 4: 
                                                                nuevaPantalla();
                                                                System.out.println(VERDE + "----------------------------------------------");
                                                                System.out.println("\t\tMODIFICAR UBICACIÓN");
                                                                System.out.println("----------------------------------------------" + RESET);

                                                                do {
                                                                    try {
                                                                        System.out.print(VERDE + "\n ID de la ubicación a modificar: " + RESET);
                                                                        // int idUbicación = Integer.parseInt(sc.nextLine());

                                                                        // modificarUbicacion(idUbicacion);
                                                                        error = false;

                                                                        System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                                                        sc.nextLine();

                                                                    } catch (Exception e) {
                                                                        System.out.println(ROJO + "\n\tError, se necesita un ID numérico" + RESET);
                                                                        error = true;
                                                                    }
                                                                } while(error);
                                                                break;

                                                            case 5:
                                                                salir();

                                                            default:
                                                                break;
                                                        }
                                                    }
                                                } catch(Exception e) {
                                                    nuevaPantalla();
                                                    System.out.println(ROJO + "\n\tError, introduce un número [1-5]" + RESET);
                                                }
                                                if(opcion == 0) break;
                                            } while(opcion > 0 || opcion < 6);
                                            break;

                                        case 4: 
                                            do {
                                                nuevaPantalla();
                                                System.out.println(VERDE + "==============================================");
                                                System.out.println("\t\tVER ALMACÉN");
                                                System.out.println("==============================================");

                                                try {
                                                    System.out.println(VERDE + "\n\t1-VER ESTRUCTURA COMPLETA");
                                                    System.out.println("\n\t2-VER ESTRUCTURA DE ZONA");
                                                    System.out.println("\n\t3-VER ESTRUCTURA DE PASILLO");
                                                    System.out.println("\n\t4-VER ESTRUCTURA DE RACK");
                                                    System.out.println("\n\t5-SALIR");
                                                    System.out.println("\n <- Volver [0]");
                                                    System.out.println("==============================================");

                                                    System.out.print("\n Introduce una opción: " + RESET);
                                                    
                                                    opcion = Integer.parseInt(sc.nextLine());

                                                    if(opcion < 1 || opcion > 5) {
                                                        if(opcion == 0) {
                                                            nuevaPantalla();
                                                            break;
                                                        } else {
                                                            nuevaPantalla();
                                                            System.out.println(ROJO + "\n\tError, introduce una opción válida" + RESET);
                                                        }
                                                        
                                                    } else {
                                                        switch (opcion) {
                                                            case 1:
                                                                nuevaPantalla();
                                                                System.out.println(VERDE + "----------------------------------------------");
                                                                System.out.println("\t\tVER ESTRUCTURA COMPLETA");
                                                                System.out.println("----------------------------------------------" + RESET);

                                                                do {
                                                                    try {
                                                                        System.out.print(VERDE + "\n ID del almacén: " + RESET);
                                                                        // int idAlmacen = Integer.parseInt(sc.nextLine());

                                                                        // mostrarAlmacen(idAlmacen);
                                                                        error = false;

                                                                        System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                                                        sc.nextLine();

                                                                    } catch (Exception e) {
                                                                        System.out.println(ROJO + "\n\tError, se necesita un ID numérico" + RESET);
                                                                        error = true;
                                                                    }
                                                                } while(error);
                                                                break;

                                                            case 2:
                                                                
                                                                nuevaPantalla();
                                                                System.out.println(VERDE + "----------------------------------------------");
                                                                System.out.println("\t\tVER ESTRUCTURA DE ZONA");
                                                                System.out.println("----------------------------------------------" + RESET);

                                                                do {
                                                                    try {
                                                                        System.out.print(VERDE + "\n ID de la zona: " + RESET);
                                                                        // int idZona = Integer.parseInt(sc.nextLine());

                                                                        // mostrarZona(idZona);
                                                                        error = false;

                                                                        System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                                                        sc.nextLine();

                                                                    } catch (Exception e) {
                                                                        System.out.println(ROJO + "\n\tError, se necesita un ID numérico" + RESET);
                                                                        error = true;
                                                                    }
                                                                } while(error);
                                                                break;
                                                        
                                                            case 3:
                                                                nuevaPantalla();
                                                                System.out.println(VERDE + "----------------------------------------------");
                                                                System.out.println("\t\tVER ESTRUCTURA DE PASILLO");
                                                                System.out.println("----------------------------------------------" + RESET);

                                                                do {
                                                                    try {
                                                                        System.out.print(VERDE + "\n ID del pasillo: " + RESET);
                                                                        // int idPasillo = Integer.parseInt(sc.nextLine());

                                                                        // mostrarPasillo(idPasillo);
                                                                        error = false;

                                                                        System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                                                        sc.nextLine();

                                                                    } catch (Exception e) {
                                                                        System.out.println(ROJO + "\n\tError, se necesita un ID numérico" + RESET);
                                                                        error = true;
                                                                    }
                                                                } while(error);
                                                                break;

                                                            case 4: 
                                                                nuevaPantalla();
                                                                System.out.println(VERDE + "----------------------------------------------");
                                                                System.out.println("\t\tVER ESTRUCTURA DE RACK");
                                                                System.out.println("----------------------------------------------" + RESET);

                                                                do {
                                                                    try {
                                                                        System.out.print(VERDE + "\n ID del rack: " + RESET);
                                                                        // int idRack = Integer.parseInt(sc.nextLine());

                                                                        // mostrarRack(idRack);
                                                                        error = false;

                                                                        System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                                                        sc.nextLine();

                                                                    } catch (Exception e) {
                                                                        System.out.println(ROJO + "\n\tError, se necesita un ID numérico" + RESET);
                                                                        error = true;
                                                                    }
                                                                } while(error);
                                                                break;

                                                            case 5:
                                                                salir();

                                                            default:
                                                                break;
                                                        }
                                                    }
                                                } catch(Exception e) {
                                                    nuevaPantalla();
                                                    System.out.println(ROJO + "\n\tError, introduce un número [1-5]" + RESET);
                                                }
                                                if(opcion == 0) break;
                                            } while(opcion > 0 || opcion < 6);
                                            break;

                                        case 5:
                                            salir();

                                        default:
                                            break;
                                    }
                                }
                            } catch(Exception e) {
                                nuevaPantalla();
                                System.out.println(ROJO + "\n\tError, introduce un número [1-5]" + RESET);
                            }
                            if(opcion == 0) break;
                        } while(opcion > 0 || opcion < 6);
                        break;

                    case 5:

                        do {
                            nuevaPantalla();
                            System.out.println(VERDE + "==============================================");
                            System.out.println("\t\tPRODUCTOS");
                            System.out.println("==============================================");

                            try {
                                System.out.println(VERDE + "\n\t1-INTRODUCIR PRODUCTO");
                                System.out.println("\n\t2-ELIMINAR PRODUCTO");
                                System.out.println("\n\t3-MODIFICAR PRODUCTO");
                                System.out.println("\n\t4-VER PRODUCTO");
                                System.out.println("\n\t5-SALIR");
                                System.out.println("\n <- Volver [0]");
                                System.out.println("==============================================");

                                System.out.print("\n Introduce una opción: " + RESET);
                                
                                opcion = Integer.parseInt(sc.nextLine());

                                if(opcion < 1 || opcion > 5) {
                                    if(opcion == 0) {
                                        nuevaPantalla();
                                        break;
                                    } else {
                                        nuevaPantalla();
                                        System.out.println(ROJO + "\n\tError, introduce una opción válida" + RESET);
                                    }
                                    
                                } else {
                                    switch (opcion) {
                                        case 1:
                                            nuevaPantalla();
                                            System.out.println(VERDE + "----------------------------------------------");
                                            System.out.println("\t\tINTRODUCIR PRODUCTO");
                                            System.out.println("----------------------------------------------" + RESET);
                                            
                                            do {
                                                try {
                                                    introducirProducto();
                                                    error = false;

                                                    System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                                    sc.nextLine();

                                                } catch (Exception e) {
                                                    System.out.println(ROJO + "\n\tError, introduce una opción válida" + RESET);
                                                    error = true;
                                                }
                                            } while(error);
                                            break;

                                        case 2:
                                            
                                            nuevaPantalla();
                                            System.out.println(VERDE + "----------------------------------------------");
                                            System.out.println("\t\tELIMINAR PRODUCTO");
                                            System.out.println("----------------------------------------------" + RESET);

                                            do {
                                                try {
                                                    System.out.print(VERDE + "\n ID del producto que quieres eliminar: " + RESET);
                                                    int idProducto = Integer.parseInt(sc.nextLine());

                                                    borrarProducto(idProducto);
                                                    error = false;

                                                    if(opcion == 0) {
                                                        nuevaPantalla();
                                                    } else {
                                                        System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                                        sc.nextLine();
                                                    }
                                                } catch (Exception e) {
                                                    System.out.println(ROJO + "\n\tError, se necesita un ID numérico" + RESET);
                                                    error = true;
                                                }
                                            } while(error);
                                            break;
                                    
                                        case 3:
                                            nuevaPantalla();
                                            System.out.println(VERDE + "----------------------------------------------");
                                            System.out.println("\t\tMODIFICAR PRODUCTO");
                                            System.out.println("----------------------------------------------" + RESET);

                                            do {
                                                try {
                                                    System.out.print(VERDE + "\n ID del producto a modificar: " + RESET);
                                                    int idProducto = Integer.parseInt(sc.nextLine());

                                                    modificarProducto(idProducto);
                                                    error = false;

                                                    System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                                    sc.nextLine();

                                                } catch (Exception e) {
                                                    System.out.println(ROJO + "\n\tError, se necesita un ID numérico" + RESET);
                                                    error = true;
                                                }
                                            } while(error);
                                            break;

                                        case 4: 
                                            nuevaPantalla();
                                            System.out.println(VERDE + "----------------------------------------------");
                                            System.out.println("\t\tVER PRODUCTO");
                                            System.out.println("----------------------------------------------" + RESET);

                                            do {
                                                try {
                                                    System.out.print(VERDE + "\n ID del producto: " + RESET);
                                                    int idProducto = Integer.parseInt(sc.nextLine());

                                                    mostrarProducto(idProducto);
                                                    error = false;

                                                    System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                                    sc.nextLine();

                                                } catch (Exception e) {
                                                    System.out.println(ROJO + "\n\tError, se necesita un ID numérico" + RESET);
                                                    error = true;
                                                }
                                            } while(error);
                                            break;

                                        case 5:
                                            salir();

                                        default:
                                            break;
                                    }
                                }
                            } catch(Exception e) {
                                nuevaPantalla();
                                System.out.println(ROJO + "\n\tError, introduce un número [1-5]" + RESET);
                            }
                            if(opcion == 0) break;
                        } while(opcion > 0 || opcion < 6);
                        break;

                    case 6:
                        opcion = 0;
                        break;

                    case 7:
                        nuevaPantalla();
                        do {
                            System.out.println(VERDE + "==============================================");
                            System.out.println("\t\tADMINISTRADOR");
                            System.out.println("==============================================");

                            try {
                                System.out.println(VERDE + "\n\t1-PERFIL");
                                System.out.println("\n\t2-CAMBIAR CONTRASEÑA");
                                if(usuarioActual.getTipo().equals("gestor")) {
                                    System.out.println("\n\t3-GESTIONAR USUARIOS");
                                } else {
                                    System.out.println(GRIS_OSCURO + "\n\t3-GESTIONAR USUARIOS" + VERDE);
                                }
                                System.out.println("\n\t4-SALIR ");
                                System.out.println("\n <- Volver [0]");
                                System.out.println("==============================================");

                                System.out.print("\n Introduce una opción: " + RESET);

                                opcion = Integer.parseInt(sc.nextLine());

                                if(opcion < 1 || opcion > 4) {
                                    if(opcion == 0) {
                                        nuevaPantalla();
                                        break;
                                    } else {
                                        nuevaPantalla();
                                        System.out.println(ROJO + "\tError, opción no válida\n" + RESET);
                                    }
                                } else {
                                    switch (opcion) {
                                        case 1:
                                            nuevaPantalla();
                                            System.out.println(VERDE + "----------------------------------------------");
                                            System.out.println("\t\tPERFIL");
                                            System.out.println("----------------------------------------------" + RESET);
                                            
                                            do {
                                                try {
                                                    // mostrarUsuario(idUsuario);
                                                    error = false;

                                                    System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                                    sc.nextLine();
                                                    
                                                } catch (Exception e) {
                                                    System.out.println(ROJO + "\n\tError, introduce una opción válida" + RESET);
                                                    error = true;
                                                }
                                            } while(error);
                                            break;

                                        case 2:
                                            nuevaPantalla();
                                            System.out.println(VERDE + "----------------------------------------------");
                                            System.out.println("\t\tCAMBIAR CONTRASEÑA");
                                            System.out.println("----------------------------------------------" + RESET);
                                            
                                            do {
                                                try {
                                                    // modificarContrasenya(idUsuario);
                                                    error = false;

                                                    System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                                    sc.nextLine();

                                                } catch (Exception e) {
                                                    System.out.println(ROJO + "\n\tError, introduce una opción válida" + RESET);
                                                    error = true;
                                                }
                                            } while(error);
                                            break;
                                    
                                        case 3:
                                            if(esGestor) {
                                                gestionarUsuarios();
                                                break;
                                            } else {
                                                opcion = 0;
                                                nuevaPantalla();
                                                System.out.println(ROJO + "\tError, opción no disponible\n" + RESET);
                                            }
                                            break;

                                        case 4: 
                                            salir();

                                        default:
                                            break;
                                    }
                                }
                            } catch(Exception e) {
                                nuevaPantalla();
                                System.out.println(ROJO + "\n\tError, introduce un número [1-4]" + RESET);
                            }  
                        } while(opcion > 0 || opcion < 5);
                        break;
                    case 8:
                        salir();

                    default:
                        break;
                }
            } catch(Exception e) {

                nuevaPantalla();
                System.out.println(ROJO + "\tError, introduce un número [1-8]\n" + RESET);
            }
            
        } while(opcion < 1 || opcion > 7);

        sc.close();
    }
}