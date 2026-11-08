/*
Proyecto Intermodular 1 - Stockerty
v.1.0
Aplicación para la gestión logística
 */

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Stockerty {

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
                System.out.println("\t\tGESTIONAR EMPLEADOS");
                System.out.println("==============================================");
                System.out.println("\n\t1-CREAR EMPLEADO");
                System.out.println("\n\t2-BORRAR EMPLEADO");
                System.out.println("\n\t3-MODIFICAR EMPLEADO");
                System.out.println("\n\t4-DATOS EMPLEADO");
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
                            System.out.println("\t\tCREAR EMPLEADO");
                            System.out.println("----------------------------------------------");

                            Empleado nuevoEmpleado = new Empleado("00000000X", "X", "X", "X", "X","x@x.xx", "700000000", 0, "operario");

                            do{
                                try {
                                    System.out.println("\n Introduce los datos del nuevo empleado");
                                    System.out.println("\n NIF o DNI: " + RESET);
                                    nuevoEmpleado.setNif(sc.nextLine());

                                    error = false;

                                    do{
                                        try {
                                            System.out.println(VERDE + "\n Nombre: " + RESET);
                                            nuevoEmpleado.setNombre(sc.nextLine());

                                            error = false;

                                            do{
                                                try {
                                                    System.out.println(VERDE + "\n Apellidos: " + RESET);
                                                    nuevoEmpleado.setApellidos(sc.nextLine());

                                                    error = false;

                                                    do{
                                                        try {
                                                            System.out.println(VERDE + "\n Puesto: " + RESET);
                                                            nuevoEmpleado.setPuesto(sc.nextLine());

                                                            error = false;

                                                            do{
                                                                try {
                                                                    System.out.println(VERDE + "\n Dirección: " + RESET);
                                                                    nuevoEmpleado.setDireccion(sc.nextLine());

                                                                    error = false;

                                                                    do{
                                                                        try {
                                                                            System.out.println(VERDE + "\n Email: " + RESET);
                                                                            nuevoEmpleado.setEmail(sc.nextLine());

                                                                            error = false;

                                                                            do{
                                                                                try {
                                                                                    System.out.println(VERDE + "\n Teléfono: " + RESET);
                                                                                    nuevoEmpleado.setTelefono(sc.nextLine());

                                                                                    error = false;

                                                                                    do{
                                                                                        try {
                                                                                            System.out.println(VERDE + "\n Departamento: " + RESET);
                                                                                            nuevoEmpleado.setIdDepartamento(Integer.parseInt(sc.nextLine()));

                                                                                            error = false;

                                                                                            do{
                                                                                                try {
                                                                                                    System.out.println(VERDE + "\n Tipo: " + RESET);
                                                                                                    nuevoEmpleado.setTipo(sc.nextLine());

                                                                                                    error = false;

                                                                                                } catch(Exception e) {
                                                                                                    System.out.println(ROJO + e.getMessage() + RESET);
                                                                                                    error = true;
                                                                                                }
                                                                                            } while(error);

                                                                                        } catch(Exception e) {
                                                                                            System.out.println(ROJO + e.getMessage() + RESET);
                                                                                            error = true;
                                                                                        }
                                                                                    } while(error);

                                                                                } catch(Exception e) {
                                                                                    System.out.println(ROJO + e.getMessage() + RESET);
                                                                                    error = true;
                                                                                }
                                                                            } while(error);

                                                                        } catch(Exception e) {
                                                                            System.out.println(ROJO + e.getMessage() + RESET);
                                                                            error = true;
                                                                        }
                                                                    } while(error);

                                                                } catch(Exception e) {
                                                                    System.out.println(ROJO + e.getMessage() + RESET);
                                                                    error = true;
                                                                }
                                                            } while(error);

                                                        } catch(Exception e) {
                                                            System.out.println(ROJO + e.getMessage() + RESET);
                                                            error = true;
                                                        }
                                                    } while(error);

                                                } catch(Exception e) {
                                                    System.out.println(ROJO + e.getMessage() + RESET);
                                                    error = true;
                                                }
                                            } while(error);

                                        } catch(Exception e) {
                                            System.out.println(ROJO + e.getMessage() + RESET);
                                            error = true;
                                        }
                                    } while(error);

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
                            System.out.println("\t\tBORRAR EMPLEADO");
                            System.out.println("----------------------------------------------" + RESET);

                            do {
                                try {
                                    System.out.print(VERDE + "\n Introduce el ID del empleado que quieres borrar: " + RESET);
                                    int id = Integer.parseInt(sc.nextLine());

                                    Empleado usuario1 = BD.mostrarEmpleado(id);

                                    System.out.print(VERDE + "\n ¿Borrar al empleado " + usuario1.getNombre() + " " + usuario1.getApellidos() + " de la base de datos? s/n: " + RESET);
                                    String validar = sc.nextLine().toLowerCase();

                                    if(validar.equals("s")) {
                                        BD.borrarEmpleado(id);
                                        error = false;

                                        if(opcion == 0) {
                                            nuevaPantalla();
                                        } else {
                                            System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                            sc.nextLine();
                                        }
                                    } else {
                                        opcion = 0;
                                        nuevaPantalla();
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
                            System.out.println("\t\tMODIFICAR EMPLEADO");
                            System.out.println("----------------------------------------------" + RESET);

                            do {
                                try {
                                    System.out.print(VERDE + "\n Introduce el ID del empleado que quieres modificar: " + RESET);
                                    int id = Integer.parseInt(sc.nextLine());

                                    Empleado usuario1 = BD.mostrarEmpleado(id);

                                    do {
                                        System.out.print(VERDE + "\n ¿Qué dato quieres modificar?: " + RESET);
                                        String dato = sc.nextLine().toLowerCase();
                                        
                                        usuario1 = BD.modificarEmpleado(usuario1, dato);
                                        error = false;

                                        System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                        sc.nextLine();

                                    } while(error);

                                } catch (Exception e) {
                                    System.out.println(ROJO + "\n\tError, se necesita un ID numérico" + RESET);
                                    error = true;
                                }
                            } while(error);
                            break;

                        case 4:
                            nuevaPantalla();
                            System.out.println(VERDE + "----------------------------------------------");
                            System.out.println("\t\tDATOS EMPLEADO");
                            System.out.println("----------------------------------------------" + RESET);

                            do {
                                try {
                                    System.out.print(VERDE + "\n Introduce el ID del empleado que quieres mostrar: " + RESET);
                                    int id = Integer.parseInt(sc.nextLine());

                                    Empleado usuario1 = BD.mostrarEmpleado(id);

                                    if(usuario1.getTipo().equals("gestor")) {
                                        System.out.println(VERDE + "\n\tEste empleado es administrador en la aplicación" + RESET);
                                    }
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


    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
    //========================================
    //          PROGRAMA PRINCIPAL
    //========================================

    public static void main(String[] args) { 
        
        nuevaPantalla();
        System.out.println(VERDE_NEGRITA + "\t\t\tSTOCKERTY" + RESET);
        System.out.print(VERDE + "\n\n\t\tPulsa ENTER para acceder al programa..." + RESET);

        Scanner sc = new Scanner(System.in);
        sc.nextLine();

        nuevaPantalla();
        
        Empleado usuarioActual = null;

        do {
            try {
                System.out.print(VERDE + "\n\t\tUsuario: " + RESET);
                int idUsuario = Integer.parseInt(sc.nextLine());
                
                usuarioActual = BD.conectar(idUsuario);

                if(usuarioActual == null) {
                    error = true;
                } else {
                    error = false;
                    nuevaPantalla();
                }
            } catch(Exception e) {
                System.out.println(e.getMessage());
                error = true;
            }
        } while(error);

        do {
            try {
                System.out.println(VERDE + "==============================================");
                System.out.println("\t\tMENU PRINCIPAL");
                System.out.println("==============================================");
                System.out.println(GRIS_OSCURO + "\n\t1-RECEPCIÓN");
                System.out.println("\n\t2-EXPEDICIÓN");
                System.out.println(VERDE + "\n\t3-PEDIDOS");
                System.out.println("\n\t4-ALMACÉN");

                if(usuarioActual.getTipo().equals("gestor")) {
                    System.out.println(VERDE + "\n\t5-PRODUCTOS");
                } else {
                    System.out.println(GRIS_OSCURO + "\n\t5-PRODUCTOS");
                }
                System.out.println(VERDE + "\n\t6-CLIENTES/PROVEEDORES");
                System.out.println(GRIS_OSCURO + "\n\t7-HISTÓRICO");
                System.out.println(VERDE + "\n\t8-ADMINISTRADOR");
                System.out.println("\n\t9-SALIR");
                System.out.println("==============================================");

                System.out.print("\n Introduce una opción: " + RESET);
                
                opcion = Integer.parseInt(sc.nextLine());

                if(opcion < 1 || opcion > 9) {
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
                        do {
                            nuevaPantalla();
                            System.out.println(VERDE + "==============================================");
                            System.out.println("\t\tPEDIDOS");
                            System.out.println("==============================================");

                            try {
                                System.out.println(VERDE + "\n\t1-CREAR PEDIDO");
                                System.out.println("\n\t2-ANULAR PEDIDO");
                                System.out.println("\n\t3-MODIFICAR PEDIDO");
                                System.out.println("\n\t4-VER PEDIDO");
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
                                            System.out.println("\t\tCREAR PEDIDO");
                                            System.out.println("----------------------------------------------" + RESET);
                                            
                                            LineaPedido lineaPedido = null;
                                            Pedido nuevoPedido = new Pedido("ls", usuarioActual.getId());
                                            int idPedido = 0;

                                            do{
                                                try {
                                                    System.out.println("\n Introduce los datos del nuevo pedido");

                                                    System.out.print("\n Tipo de pedido (ls/pc): " + RESET);
                                                    nuevoPedido.setTipo(sc.nextLine().toLowerCase());

                                                    error = false;

                                                    idPedido = BD.insertarPedido(nuevoPedido);
                                                    nuevaPantalla();

                                                    do{
                                                        try {
                                                            System.out.print(VERDE + "\n Producto: " + RESET);
                                                            int nuevoProducto = Integer.parseInt(sc.nextLine());

                                                            error = false;

                                                            int cantidad = 0;

                                                            do{
                                                                try {
                                                                    System.out.println(VERDE + "\n Cantidad: " + RESET);
                                                                    cantidad = Integer.parseInt(sc.nextLine());

                                                                    error = false;

                                                                } catch(Exception e) {
                                                                    System.out.println(ROJO + e.getMessage() + RESET);
                                                                    error = true;
                                                                }
                                                            } while(error);

                                                            lineaPedido = new LineaPedido(idPedido, nuevoProducto, cantidad);
                                                            
                                                            long idLineaPedido = BD.insertarLineaPedido(lineaPedido);

                                                        } catch(Exception e) {
                                                            System.out.println(ROJO + e.getMessage() + RESET);
                                                            error = true;
                                                        }
                                                    } while(error);

                                                } catch(Exception e) {
                                                    System.out.println(ROJO + e.getMessage() + RESET);
                                                    error = true;
                                                }
                                            } while(error);

                                            System.out.println(VERDE + "\n\t¡Pedido creado! Número de pedido: " + RESET + idPedido);
                                            List<LineaPedido> contenidoPedido = BD.mostrarLineasPedido(idPedido);
                                            break;

                                        case 2:
                                            
                                            nuevaPantalla();
                                            System.out.println(VERDE + "----------------------------------------------");
                                            System.out.println("\t\tANULAR PEDIDO");
                                            System.out.println("----------------------------------------------" + RESET);

                                            do {
                                                try {
                                                    System.out.print(VERDE + "\n Introduce el número de pedido que quieres anular: " + RESET);
                                                    idPedido = Integer.parseInt(sc.nextLine());

                                                    Pedido pedido = BD.mostrarPedido(idPedido);
                                                    contenidoPedido = BD.mostrarLineasPedido(idPedido);

                                                    System.out.print(VERDE + "\n ¿Anular el pedido " + pedido.getTipo().toUpperCase() + " " + pedido.getId() + " " + pedido.getEstado() + " de la base de datos? s/n: " + RESET);
                                                    String validar = sc.nextLine().toLowerCase();

                                                    if(validar.equals("s")) {
                                                        pedido = BD.anularPedido(pedido);
                                                        error = false;

                                                        if(opcion == 0) {
                                                            nuevaPantalla();
                                                        } else {
                                                            System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                                            sc.nextLine();
                                                        }
                                                    } else {
                                                        opcion = 0;
                                                        nuevaPantalla();
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
                                            System.out.println("\t\tMODIFICAR PEDIDO");
                                            System.out.println("----------------------------------------------" + RESET);

                                            do {
                                                try {
                                                    System.out.print(VERDE + "\n Introduce el pedido que quieres modificar: " + RESET);
                                                    idPedido = Integer.parseInt(sc.nextLine());

                                                    Pedido pedido = BD.mostrarPedido(idPedido);
                                                    List<LineaPedido> contenido = BD.mostrarLineasPedido(idPedido);

                                                    do {
                                                        System.out.print(VERDE + "\n ¿Qué dato quieres modificar?: " + RESET);
                                                        String dato = sc.nextLine().toLowerCase();

                                                        if (dato.equals("contenido")) {

                                                            do {
                                                                System.out.print(VERDE + "\n ¿Añadir línea (A), Borrar línea (B) o Modificar cantidad (M)?: " + RESET);
                                                                String accion = sc.nextLine();

                                                                if (accion.toLowerCase().equals("a")) {
                                                                    do{
                                                                        try {
                                                                            System.out.print(VERDE + "\n Producto: " + RESET);
                                                                            int nuevoProducto = Integer.parseInt(sc.nextLine());

                                                                            error = false;

                                                                            int cantidad = 0;

                                                                            do{
                                                                                try {
                                                                                    System.out.println(VERDE + "\n Cantidad: " + RESET);
                                                                                    cantidad = Integer.parseInt(sc.nextLine());

                                                                                    error = false;

                                                                                } catch(Exception e) {
                                                                                    System.out.println(ROJO + e.getMessage() + RESET);
                                                                                    error = true;
                                                                                }
                                                                            } while(error);

                                                                            lineaPedido = new LineaPedido(idPedido, nuevoProducto, cantidad);
                                                                            
                                                                            long idLineaPedido = BD.insertarLineaPedido(lineaPedido);

                                                                        } catch(Exception e) {
                                                                            System.out.println(ROJO + e.getMessage() + RESET);
                                                                            error = true;
                                                                        }
                                                                    } while(error);

                                                                } else if (accion.toLowerCase().equals("b")) {
                                                                    do{
                                                                        try {
                                                                            System.out.print(VERDE + "\n Producto: " + RESET);
                                                                            int idProducto = Integer.parseInt(sc.nextLine());

                                                                            error = false;

                                                                            int cantidad = 0;
                                                                            
                                                                            BD.borrarLineaPedido(idPedido, idProducto);

                                                                        } catch(Exception e) {
                                                                            System.out.println(ROJO + e.getMessage() + RESET);
                                                                            error = true;
                                                                        }
                                                                    } while(error);

                                                                } else if (accion.toLowerCase().equals("m")) {
                                                                    do{
                                                                        try {
                                                                            System.out.print(VERDE + "\n Producto: " + RESET);
                                                                            int idProducto = Integer.parseInt(sc.nextLine());

                                                                            error = false;

                                                                            int cantidad = 0;

                                                                            do{
                                                                                try {
                                                                                    System.out.println(VERDE + "\n Cantidad: " + RESET);
                                                                                    cantidad = Integer.parseInt(sc.nextLine());

                                                                                    error = false;

                                                                                } catch(Exception e) {
                                                                                    System.out.println(ROJO + e.getMessage() + RESET);
                                                                                    error = true;
                                                                                }
                                                                            } while(error);

                                                                            BD.modificarLineaPedido(idPedido, idProducto, cantidad);

                                                                        } catch(Exception e) {
                                                                            System.out.println(ROJO + e.getMessage() + RESET);
                                                                            error = true;
                                                                        }
                                                                    } while(error);
                                                                } else {
                                                                    System.out.println(ROJO + "\n\tIntroduce una opción válida" + RESET);
                                                                    error = true;
                                                                }
                                                            } while(error);

                                                        } else {
                                                            pedido = BD.modificarPedido(pedido, dato);
                                                            error = false;
                                                        }
                                                        System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                                        sc.nextLine();

                                                    } while(error);

                                                } catch (Exception e) {
                                                    System.out.println(ROJO + "\n\tError, se necesita un ID numérico" + RESET);
                                                    error = true;
                                                }
                                            } while(error);
                                            break;

                                        case 4: 
                                            nuevaPantalla();
                                            System.out.println(VERDE + "----------------------------------------------");
                                            System.out.println("\t\tVER PEDIDO");
                                            System.out.println("----------------------------------------------" + RESET);

                                            do {
                                                try {
                                                    System.out.print(VERDE + "\n Introduce el pedido que quieres mostrar: " + RESET);
                                                    idPedido = Integer.parseInt(sc.nextLine());

                                                    Pedido pedido = BD.mostrarPedido(idPedido);

                                                    List<LineaPedido> contenido = BD.mostrarLineasPedido(idPedido);
                                                    
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
                            System.out.println("\t\tALMACÉN");
                            System.out.println("==============================================");

                            try {
                                System.out.println(VERDE + "\n\t1-GESTIONAR SOPORTES");
                                System.out.println(GRIS_OSCURO + "\n\t2-UBICAR MERCANCÍA");
                                System.out.println("\n\t3-GESTIÓN ALMACÉN");
                                
                                if(usuarioActual.getTipo().equals("gestor")) {
                                    System.out.println(VERDE + "\n\t4-ESTRUCTURA ALMACÉN");
                                } else {
                                    System.out.println(GRIS_OSCURO + "\n\t4-ESTRUCTURA ALMACÉN" + RESET);
                                }
                                System.out.println(VERDE + "\n\t5-SALIR");
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
                                                System.out.println("\t\tGESTIONAR SOPORTES");
                                                System.out.println("==============================================");

                                                try {
                                                    System.out.println("\n\t1-CREAR SOPORTE");
                                                    System.out.println("\n\t2-MODIFICAR SOPORTE");
                                                    System.out.println("\n\t3-ELIMINAR SOPORTE");
                                                    System.out.println("\n\t4-MOSTRAR CONTENIDO");
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
                                                                System.out.println("\t\tCREAR SOPORTE");
                                                                System.out.println("----------------------------------------------" + RESET);
                                                                
                                                                do {
                                                                    try {
                                                                        System.out.print(VERDE + "\n ¿Qué tipo de soporte quieres crear? Bulto/Palet: " + RESET);
                                                                        String tipoSoporte = sc.nextLine().toLowerCase();

                                                                        Soporte soporte = new Soporte(tipoSoporte, LocalDateTime.now(), usuarioActual.getId());
                                                                        long idSoporte = BD.insertarSoporte(soporte);
                                                                        soporte.setId(idSoporte);
                                                                        soporte = BD.modificarSoporte(soporte, "abreviatura");
                                                                        nuevaPantalla();

                                                                        List<Producto> contenido = new ArrayList<>();

                                                                        opcion = 1;

                                                                        do {
                                                                            try {

                                                                                System.out.println(VERDE + "\n Añade productos");
                                                                                System.out.println("Introduce 0 para cerrar soporte");

                                                                                System.out.print("\n ID producto: " + RESET);
                                                                                int idProducto = Integer.parseInt(sc.nextLine());

                                                                                if (idProducto == 0) {

                                                                                    opcion = 0;
                                                                                } else {

                                                                                    Producto producto = BD.mostrarProducto(idProducto);

                                                                                    System.out.print(VERDE + "\n cantidad: " + RESET);
                                                                                    int cantidad = Integer.parseInt(sc.nextLine());
                                                                                    
                                                                                    producto.setStock(cantidad);

                                                                                    contenido.add(producto);

                                                                                    nuevaPantalla();
                                                                                    System.out.println(VERDE + "\nSoporte: " + RESET + soporte.getAbreviatura());

                                                                                    contenido.forEach(p -> System.out.println(" " + p.getId() + "\t" + p.getNombre() + "\t" + p.getStock() + " unidades"));

                                                                                    LlenaSoporte accion = new LlenaSoporte(usuarioActual.getId(), idSoporte, idProducto, cantidad, LocalDateTime.now());
                                                                                    BD.insertarLlenaSoporte(accion);
                                                                                }
                                                                            } catch(Exception e) {
                                                                                System.out.println(e.getMessage());
                                                                            }
                                                                        } while(opcion == 1);

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
                                                                System.out.println("\t\tMODIFICAR SOPORTE");
                                                                System.out.println("----------------------------------------------" + RESET);

                                                                do {
                                                                    try {
                                                                        System.out.print(VERDE + "\n Introduce el nº de soporte que quieres modificar: " + RESET);
                                                                        int id = Integer.parseInt(sc.nextLine());

                                                                        Soporte soporte1 = BD.mostrarSoporte(id);

                                                                        do {
                                                                            System.out.print(VERDE + "\n ¿Qué dato quieres modificar?: " + RESET);
                                                                            String dato = sc.nextLine().toLowerCase();
                                                                            
                                                                            soporte1 = BD.modificarSoporte(soporte1, dato);
                                                                            error = false;

                                                                            System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                                                            sc.nextLine();

                                                                        } while(error);

                                                                    } catch (Exception e) {
                                                                        System.out.println(ROJO + "\n\tError, se necesita un ID numérico" + RESET);
                                                                        error = true;
                                                                    }
                                                                } while(error);
                                                                break;
                                                        
                                                            case 3:
                                                                nuevaPantalla();
                                                                System.out.println(VERDE + "----------------------------------------------");
                                                                System.out.println("\t\tELIMINAR SOPORTE");
                                                                System.out.println("----------------------------------------------" + RESET);

                                                                do {
                                                                    try {
                                                                        System.out.print(VERDE + "\n Introduce el nº de soporte que quieres borrar: " + RESET);
                                                                        int id = Integer.parseInt(sc.nextLine());

                                                                        Soporte soporte1 = BD.mostrarSoporte(id);

                                                                        System.out.print(VERDE + "\n ¿Borrar el soporte " + soporte1.getAbreviatura() + " de la base de datos? s/n: " + RESET);
                                                                        String validar = sc.nextLine().toLowerCase();

                                                                        if(validar.equals("s")) {
                                                                            BD.borrarSoporte(id);
                                                                            error = false;

                                                                            if(opcion == 0) {
                                                                                nuevaPantalla();
                                                                            } else {
                                                                                System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                                                                sc.nextLine();
                                                                            }
                                                                        } else {
                                                                            opcion = 0;
                                                                            nuevaPantalla();
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
                                                                System.out.println("\t\tMOSTRAR CONTENIDO");
                                                                System.out.println("----------------------------------------------" + RESET);

                                                                do {
                                                                    try {
                                                                        System.out.print(VERDE + "\n Introduce el nº de soporte que quieres mostrar: " + RESET);
                                                                        int id = Integer.parseInt(sc.nextLine());

                                                                        List<LlenaSoporte> lista;
                                                                        lista = BD.mostrarContenido(id);

                                                                        lista.forEach(p -> System.out.println(" " + p.getIdProducto() + "\t" + p.getCantidad() + " unidades"));

                                                                        error = false;

                                                                        System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                                                        sc.nextLine();

                                                                    } catch (Exception e) {
                                                                        System.out.println(ROJO + "\n\tError, se necesita un ID numérico" + e.getMessage() + RESET);
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
                                                System.out.println("\t\tUBICAR MERCANCÍA");
                                                System.out.println("==============================================");

                                                try {
                                                    System.out.println(GRIS_OSCURO + "\n\t1-UBICAR SOPORTE");
                                                    System.out.println("\n\t2-UBICAR PRODUCTOS");
                                                    System.out.println("\n\t3-MODIFICAR UBICACIÓN");
                                                    System.out.println("\n\t4-LIBERAR UBICACIÓN");
                                                    System.out.println("\n\t5-MOSTRAR UBICACIÓN");
                                                    System.out.println(VERDE + "\n\t6-SALIR");
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
                                                                System.out.println("\t\tUBICAR SOPORTE");
                                                                System.out.println("----------------------------------------------" + RESET);

                                                                do {
                                                                    try {
                                                                        System.out.print(VERDE + "\n Introduce el nº del soporte que quieres ubicar: " + RESET);
                                                                        int numSoporte = Integer.parseInt(sc.nextLine());
                                                                        Long idSoporte = 10002026000000000L + numSoporte; 

                                                                        System.out.print(VERDE + "\n Introduce la nueva ubicación: " + RESET);
                                                                        String nombreUbicacion = sc.nextLine();

                                                                        Ubicacion ubicacionSoporte = BD.mostrarUbicacion(nombreUbicacion);
                                                                        nuevaPantalla();

                                                                        UbicaSoporte ubicaSoporte = new UbicaSoporte(usuarioActual.getId(), ubicacionSoporte.getId(), idSoporte, LocalDateTime.now(), "ubica");

                                                                        Long accion = BD.insertarUbicaSoporte(ubicaSoporte);
                                                                        System.out.println(VERDE + "\n Nº de Identificación de la acción: " + RESET + accion);

                                                                        Soporte soporte = BD.mostrarSoporte(numSoporte);
                                                                        nuevaPantalla();

                                                                        BD.modificarSoporte(soporte, "ubicacion", nombreUbicacion);
                                                                        nuevaPantalla();

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
                                                                System.out.println("\t\tUBICAR PRODUCTOS");
                                                                System.out.println("----------------------------------------------" + RESET);

                                                                do {
                                                                    try {
                                                                        System.out.print(VERDE + "\n Introduce el ID del producto que quieres ubicar: " + RESET);
                                                                        int idProducto = Integer.parseInt(sc.nextLine());

                                                                        System.out.print(VERDE + "\n Introduce cantidad: " + RESET);
                                                                        int cantidad = Integer.parseInt(sc.nextLine());

                                                                        System.out.print(VERDE + "\n Introduce la nueva ubicación: " + RESET);
                                                                        String nombreUbicacion = sc.nextLine();

                                                                        Ubicacion ubicacionProducto = BD.mostrarUbicacion(nombreUbicacion);
                                                                        nuevaPantalla();

                                                                        UbicaProducto ubicaProducto = new UbicaProducto(usuarioActual.getId(), ubicacionProducto.getId(), idProducto, cantidad, LocalDateTime.now(), "ubica");

                                                                        Long accion = BD.insertarUbicaProducto(ubicaProducto);
                                                                        System.out.println(VERDE + "\n Nº de Identificación de la acción: " + RESET + accion);

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
                                                                System.out.println("\t\tMODIFICAR UBICACIÓN");
                                                                System.out.println("----------------------------------------------" + RESET);

                                                                do {
                                                                    try {
                                                                        System.out.print(VERDE + "\n Introduce el nombre de la ubicación que quieres modificar: " + RESET);
                                                                        String nombreUbicacion = sc.nextLine();



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
                                                                System.out.println("\t\tLIBERAR UBICACIÓN");
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
                                                                System.out.println("\t\tMOSTRAR UBICACIÓN");
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
                                                System.out.println("\t\tGESTIÓN ALMACÉN");
                                                System.out.println("==============================================");

                                                try {
                                                    System.out.println(GRIS_OSCURO + "\n\t1-LISTAR UBICACIONES");
                                                    System.out.println("\n\t2-LISTAR SOPORTES");
                                                    System.out.println("\n\t3-LISTAR PRODUCTOS");
                                                    System.out.println("\n\t4-LISTAR FECHAS");
                                                    System.out.println(VERDE + "\n\t5-SALIR");
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
                                                                System.out.println("\t\tLISTAR UBICACIONES");
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
                                                                System.out.println("\t\tLISTAR SOPORTES");
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
                                                                System.out.println("\t\tLISTAR MERCANCÍA");
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
                                                                System.out.println("\t\tLISTAR FECHAS");
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
                                                System.out.println("\t\tESTRUCTURA ALMACÉN");
                                                System.out.println("==============================================");

                                                try {
                                                    System.out.println(VERDE + "\n\t1-CREAR ELEMENTOS");
                                                    System.out.println("\n\t2-MODIFICAR ELEMENTOS");
                                                    System.out.println("\n\t3-ELIMINAR ELEMENTOS");
                                                    System.out.println(GRIS_OSCURO + "\n\t4-VER ESTRUCTURA");
                                                    System.out.println(VERDE + "\n\t5-SALIR");
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
                                                                System.out.println("\t\tCREAR ELEMENTOS");
                                                                System.out.println("----------------------------------------------" + RESET);

                                                                do {
                                                                    try {
                                                                        System.out.print(VERDE + "\n ¿Qué elemento quieres crear? Zona/Pasillo/Rack: " + RESET);
                                                                        String elemento = sc.nextLine().toLowerCase();

                                                                        switch (elemento) {
                                                                            case "zona":
                                                                                System.out.print(VERDE + "\n Nombre de la nueva zona: " + RESET);
                                                                                String nombreZona = sc.nextLine();

                                                                                System.out.print(VERDE + "\n ID del almacén al que pertenece: " + RESET);
                                                                                int idAlmacen = Integer.parseInt(sc.nextLine());

                                                                                Zona zona = new Zona(nombreZona, idAlmacen);
                                                                                BD.insertarZona(zona);

                                                                                break;

                                                                            case "pasillo":
                                                                                System.out.print(VERDE + "\n Nombre del nuevo pasillo: " + RESET);
                                                                                String nombrePasillo = sc.nextLine();

                                                                                System.out.print(VERDE + "\n ID de la zona a la que pertenece: " + RESET);
                                                                                int idZona = Integer.parseInt(sc.nextLine());

                                                                                Pasillo pasillo = new Pasillo(nombrePasillo, idZona);
                                                                                BD.insertarPasillo(pasillo);

                                                                                break;

                                                                            case "rack":
                                                                                System.out.print(VERDE + "\n Nombre del nuevo rack: " + RESET);
                                                                                String nombreRack = sc.nextLine();

                                                                                System.out.print(VERDE + "\n ID del pasillo al que pertenece: " + RESET);
                                                                                int idPasillo = Integer.parseInt(sc.nextLine());

                                                                                System.out.print(VERDE + "\n Nº de alturas: " + RESET);
                                                                                int alturasRack = Integer.parseInt(sc.nextLine());

                                                                                System.out.print(VERDE + "\n Nº de direcciones por altura: " + RESET);
                                                                                int direccionesAltura = Integer.parseInt(sc.nextLine());

                                                                                Rack rack = new Rack(nombreRack, idPasillo);
                                                                                int idRack = BD.insertarRack(rack);

                                                                                int nivelesAltura = alturasRack;
                                                                                int contador = 0;

                                                                                do {
                                                                                    Altura altura = new Altura(contador, idRack);
                                                                                    int idAltura = BD.insertarAltura(altura);

                                                                                    int posicionDireccion = direccionesAltura;
                                                                                    int contador2 = 1;

                                                                                    do {
                                                                                        String posicion = null;
                                                                                        switch(contador2) {
                                                                                            case 1:
                                                                                                posicion = "A";
                                                                                                break;

                                                                                            case 2:
                                                                                                posicion = "B";
                                                                                                break;

                                                                                            case 3:
                                                                                                posicion = "C";
                                                                                                break;

                                                                                            case 4:
                                                                                                posicion = "D";
                                                                                                break;

                                                                                            case 5:
                                                                                                posicion = "E";
                                                                                                break;

                                                                                            case 6:
                                                                                                posicion = "F";
                                                                                                break;

                                                                                            case 7:
                                                                                                posicion = "G";
                                                                                                break;

                                                                                            default:
                                                                                                break;
                                                                                        }
                                                                                        Direccion direccion = new Direccion(posicion, idAltura);
                                                                                        int idDireccion = BD.insertarDireccion(direccion);

                                                                                        String nombreUbicacion = nombreRack + "-" + contador + "-" + posicion;
                                                                                        Ubicacion ubicacion = new Ubicacion(nombreUbicacion, idRack, idAltura, idDireccion);

                                                                                        BD.insertarUbicacion(ubicacion);

                                                                                        if(contador2 != posicionDireccion) {
                                                                                            contador2++;
                                                                                        }
                                                                                    } while(contador2 != posicionDireccion);

                                                                                    contador++;

                                                                                } while(contador != nivelesAltura);

                                                                                break;

                                                                            default:
                                                                                break;
                                                                        }
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
                                                                System.out.println("\t\tMODIFICAR ELEMENTOS");
                                                                System.out.println("----------------------------------------------" + RESET);

                                                                do {
                                                                    try {
                                                                        System.out.print(VERDE + "\n ¿Qué elemento quieres modificar? Zona/Pasillo/Rack: " + RESET);
                                                                        String elemento = sc.nextLine().toLowerCase();

                                                                        switch (elemento) {
                                                                            case "zona":
                                                                                System.out.print(VERDE + "\n ID zona: " + RESET);
                                                                                int idZona = Integer.parseInt(sc.nextLine());

                                                                                Zona zona1 = BD.mostrarZona(idZona);

                                                                                System.out.print(VERDE + "\n ¿Qué dato quieres modificar?: " + RESET);
                                                                                String datoZona = sc.nextLine().toLowerCase();

                                                                                BD.modificarZona(zona1, datoZona);
                                                                                error = false;

                                                                                if(opcion == 0) {
                                                                                    nuevaPantalla();
                                                                                } else {
                                                                                    System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                                                                    sc.nextLine();
                                                                                }
                                                                                
                                                                                break;

                                                                            case "pasillo":
                                                                                System.out.print(VERDE + "\n ID pasillo: " + RESET);
                                                                                int idPasillo = Integer.parseInt(sc.nextLine());

                                                                                Pasillo pasillo1 = BD.mostrarPasillo(idPasillo);

                                                                                System.out.print(VERDE + "\n ¿Qué dato quieres modificar?: " + RESET);
                                                                                String datoPasillo = sc.nextLine().toLowerCase();

                                                                                BD.modificarPasillo(pasillo1, datoPasillo);
                                                                                error = false;

                                                                                if(opcion == 0) {
                                                                                    nuevaPantalla();
                                                                                } else {
                                                                                    System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                                                                    sc.nextLine();
                                                                                }
                                                                                break;

                                                                            case "rack":
                                                                                System.out.print(VERDE + "\n ID rack: " + RESET);
                                                                                int idRack = Integer.parseInt(sc.nextLine());

                                                                                Rack rack1 = BD.mostrarRack(idRack);

                                                                                System.out.print(VERDE + "\n ¿Qué dato quieres modificar?: " + RESET);
                                                                                String datoRack = sc.nextLine().toLowerCase();

                                                                                BD.modificarRack(rack1, datoRack);
                                                                                error = false;

                                                                                if(opcion == 0) {
                                                                                    nuevaPantalla();
                                                                                } else {
                                                                                    System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                                                                    sc.nextLine();
                                                                                }
                                                                                break;

                                                                            default:
                                                                                break;
                                                                        }

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
                                                                System.out.println("\t\tELIMINAR ELEMENTOS");
                                                                System.out.println("----------------------------------------------" + RESET);

                                                                do {
                                                                    try {
                                                                        System.out.print(VERDE + "\n ¿Qué elemento quieres modificar? Zona/Pasillo/Rack: " + RESET);
                                                                        String elemento = sc.nextLine().toLowerCase();

                                                                        switch (elemento) {
                                                                            case "zona":
                                                                                System.out.print(VERDE + "\n ID zona: " + RESET);
                                                                                int idZona = Integer.parseInt(sc.nextLine());

                                                                                Zona zona1 = BD.mostrarZona(idZona);

                                                                                System.out.print(VERDE + "\n ¿Borrar la zona " + zona1.getId() + " " + zona1.getNombre() + " de la base de datos? s/n: " + RESET);
                                                                                String validarZona = sc.nextLine().toLowerCase();

                                                                                if(validarZona.equals("s")) {
                                                                                    BD.borrarZona(zona1.getId());
                                                                                    error = false;

                                                                                    if(opcion == 0) {
                                                                                        nuevaPantalla();
                                                                                    } else {
                                                                                        System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                                                                        sc.nextLine();
                                                                                    }
                                                                                } else {
                                                                                    opcion = 0;
                                                                                    nuevaPantalla();
                                                                                }
                                                                                break;

                                                                            case "pasillo":
                                                                                System.out.print(VERDE + "\n ID pasillo: " + RESET);
                                                                                int idPasillo = Integer.parseInt(sc.nextLine());

                                                                                Pasillo pasillo1 = BD.mostrarPasillo(idPasillo);

                                                                                System.out.print(VERDE + "\n ¿Borrar el pasillo " + pasillo1.getId() + " " + pasillo1.getNombre() + " de la base de datos? s/n: " + RESET);
                                                                                String validarPasillo = sc.nextLine().toLowerCase();

                                                                                if(validarPasillo.equals("s")) {
                                                                                    BD.borrarPasillo(pasillo1.getId());
                                                                                    error = false;

                                                                                    if(opcion == 0) {
                                                                                        nuevaPantalla();
                                                                                    } else {
                                                                                        System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                                                                        sc.nextLine();
                                                                                    }
                                                                                } else {
                                                                                    opcion = 0;
                                                                                    nuevaPantalla();
                                                                                }
                                                                                break;

                                                                            case "rack":
                                                                                System.out.print(VERDE + "\n ID rack: " + RESET);
                                                                                int idRack = Integer.parseInt(sc.nextLine());

                                                                                Rack rack1 = BD.mostrarRack(idRack);

                                                                                System.out.print(VERDE + "\n ¿Borrar el rack " + rack1.getId() + " " + rack1.getNombre() + " de la base de datos? s/n: " + RESET);
                                                                                String validarRack = sc.nextLine().toLowerCase();

                                                                                if(validarRack.equals("s")) {
                                                                                    BD.borrarRack(rack1.getId());
                                                                                    error = false;

                                                                                    if(opcion == 0) {
                                                                                        nuevaPantalla();
                                                                                    } else {
                                                                                        System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                                                                        sc.nextLine();
                                                                                    }
                                                                                } else {
                                                                                    opcion = 0;
                                                                                    nuevaPantalla();
                                                                                }
                                                                                break;

                                                                            default:
                                                                                break;
                                                                        }

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
                                                                System.out.println("\t\tVER ESTRUCTURA");
                                                                System.out.println("----------------------------------------------" + RESET);

                                                                do {
                                                                    try {
                                                                        System.out.print(VERDE + "\n ID del almacén: " + RESET);
                                                                        // int idAlmacen = Integer.parseInt(sc.nextLine());

                                                                        // BD.verEstructura(idAlmacen);
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

                        if(usuarioActual.getTipo().equals("gestor")) {

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
                                                
                                                Producto nuevoProducto = new Producto("0000000000000", "X", "X", "0.00", 0,0);

                                                do{
                                                    try {
                                                        System.out.println("\n Introduce los datos del nuevo producto");
                                                        System.out.println("\n EAN: " + RESET);
                                                        nuevoProducto.setEan(sc.nextLine());

                                                        error = false;

                                                        do{
                                                            try {
                                                                System.out.println(VERDE + "\n Nombre: " + RESET);
                                                                nuevoProducto.setNombre(sc.nextLine());

                                                                error = false;

                                                                do{
                                                                    try {
                                                                        System.out.println(VERDE + "\n Descripción: " + RESET);
                                                                        nuevoProducto.setDescripcion(sc.nextLine());

                                                                        error = false;

                                                                        do{
                                                                            try {
                                                                                System.out.println(VERDE + "\n Precio: " + RESET);
                                                                                nuevoProducto.setPrecio(sc.nextLine());

                                                                                error = false;

                                                                                do{
                                                                                    try {
                                                                                        System.out.println(VERDE + "\n Stock: " + RESET);
                                                                                        nuevoProducto.setStock(Integer.parseInt(sc.nextLine()));

                                                                                        error = false;

                                                                                        do{
                                                                                            try {
                                                                                                System.out.println(VERDE + "\n Stock mínimo: " + RESET);
                                                                                                nuevoProducto.setStockMinimo(Integer.parseInt(sc.nextLine()));

                                                                                                error = false;

                                                                                            } catch(Exception e) {
                                                                                                System.out.println(ROJO + e.getMessage() + RESET);
                                                                                                error = true;
                                                                                            }
                                                                                        } while(error);

                                                                                    } catch(Exception e) {
                                                                                        System.out.println(ROJO + e.getMessage() + RESET);
                                                                                        error = true;
                                                                                    }
                                                                                } while(error);

                                                                            } catch(Exception e) {
                                                                                System.out.println(ROJO + e.getMessage() + RESET);
                                                                                error = true;
                                                                            }
                                                                        } while(error);

                                                                    } catch(Exception e) {
                                                                        System.out.println(ROJO + e.getMessage() + RESET);
                                                                        error = true;
                                                                    }
                                                                } while(error);

                                                            } catch(Exception e) {
                                                                System.out.println(ROJO + e.getMessage() + RESET);
                                                                error = true;
                                                            }
                                                        } while(error);

                                                    } catch(Exception e) {
                                                        System.out.println(ROJO + e.getMessage() + RESET);
                                                        error = true;
                                                    }
                                                } while(error);
                                                

                                                BD.insertarProducto(nuevoProducto);
                                                break;

                                            case 2:
                                                
                                                nuevaPantalla();
                                                System.out.println(VERDE + "----------------------------------------------");
                                                System.out.println("\t\tELIMINAR PRODUCTO");
                                                System.out.println("----------------------------------------------" + RESET);

                                                do {
                                                    try {
                                                        System.out.print(VERDE + "\n Introduce el ID del producto que quieres borrar: " + RESET);
                                                        int id = Integer.parseInt(sc.nextLine());

                                                        Producto producto1 = BD.mostrarProducto(id);

                                                        System.out.print(VERDE + "\n ¿Borrar el producto " + producto1.getEan() + " " + producto1.getNombre() + " de la base de datos? s/n: " + RESET);
                                                        String validar = sc.nextLine().toLowerCase();

                                                        if(validar.equals("s")) {
                                                            BD.borrarProducto(id);
                                                            error = false;

                                                            if(opcion == 0) {
                                                                nuevaPantalla();
                                                            } else {
                                                                System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                                                sc.nextLine();
                                                            }
                                                        } else {
                                                            opcion = 0;
                                                            nuevaPantalla();
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
                                                        System.out.print(VERDE + "\n Introduce el ID del producto que quieres modificar: " + RESET);
                                                        int id = Integer.parseInt(sc.nextLine());

                                                        Producto producto1 = BD.mostrarProducto(id);

                                                        do {
                                                            System.out.print(VERDE + "\n ¿Qué dato quieres modificar?: " + RESET);
                                                            String dato = sc.nextLine().toLowerCase();
                                                            
                                                            producto1 = BD.modificarProducto(producto1, dato);
                                                            error = false;

                                                            System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                                            sc.nextLine();

                                                        } while(error);

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
                                                        System.out.print(VERDE + "\n Introduce el ID del producto que quieres mostrar: " + RESET);
                                                        int id = Integer.parseInt(sc.nextLine());

                                                        Producto producto1 = BD.mostrarProducto(id);

                                                        if(producto1.getStock() > 0) {
                                                            System.out.println(VERDE + "\n\tExiste stock del producto" + RESET);
                                                        }
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
                        } else {
                            opcion = 0;
                        }
                        break;

                    case 6:

                        do {
                            nuevaPantalla();
                            System.out.println(VERDE + "==============================================");
                            System.out.println("\t\tCLIENTES/PROVEEDORES");
                            System.out.println("==============================================");

                            try {
                                System.out.println(VERDE + "\n\t1-INTRODUCIR CLIENTE/PROVEEDOR");
                                System.out.println("\n\t2-ELIMINAR CLIENTE/PROVEEDOR");
                                System.out.println("\n\t3-MODIFICAR CLIENTE/PROVEEDOR");
                                System.out.println("\n\t4-DATOS CLIENTE/PROVEEDOR");
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
                                            System.out.println("\t\tINTRODUCIR CLIENTE/PROVEEDOR");
                                            System.out.println("----------------------------------------------");

                                            do {
                                                System.out.print("\n Elige entre cliente (c) o proveedor (p): " + RESET);
                                                String tipo = sc.nextLine().toLowerCase();

                                                if(tipo.equals("c")) {

                                                    Cliente cliente = new Cliente("00000000X", "X", "X", "X","x@x.xx", "700000000");

                                                    do{
                                                        try {
                                                            System.out.println("\n Introduce los datos del nuevo cliente");
                                                            System.out.println("\n NIF o DNI: " + RESET);
                                                            cliente.setNif(sc.nextLine());

                                                            error = false;

                                                            do{
                                                                try {
                                                                    System.out.println(VERDE + "\n Nombre: " + RESET);
                                                                    cliente.setNombre(sc.nextLine());

                                                                    error = false;

                                                                    do{
                                                                        try {
                                                                            System.out.println(VERDE + "\n Apellidos: " + RESET);
                                                                            cliente.setApellidos(sc.nextLine());

                                                                            error = false;

                                                                            do{
                                                                                try {
                                                                                    System.out.println(VERDE + "\n Dirección: " + RESET);
                                                                                    cliente.setDireccion(sc.nextLine());

                                                                                    error = false;

                                                                                    do{
                                                                                        try {
                                                                                            System.out.println(VERDE + "\n Email: " + RESET);
                                                                                            cliente.setEmail(sc.nextLine());

                                                                                            error = false;

                                                                                            do{
                                                                                                try {
                                                                                                    System.out.println(VERDE + "\n Teléfono: " + RESET);
                                                                                                    cliente.setTelefono(sc.nextLine());

                                                                                                    error = false;

                                                                                                } catch(Exception e) {
                                                                                                    System.out.println(ROJO + e.getMessage() + RESET);
                                                                                                    error = true;
                                                                                                }
                                                                                            } while(error);

                                                                                        } catch(Exception e) {
                                                                                            System.out.println(ROJO + e.getMessage() + RESET);
                                                                                            error = true;
                                                                                        }
                                                                                    } while(error);

                                                                                } catch(Exception e) {
                                                                                    System.out.println(ROJO + e.getMessage() + RESET);
                                                                                    error = true;
                                                                                }
                                                                            } while(error);

                                                                        } catch(Exception e) {
                                                                            System.out.println(ROJO + e.getMessage() + RESET);
                                                                            error = true;
                                                                        }
                                                                    } while(error);

                                                                } catch(Exception e) {
                                                                    System.out.println(ROJO + e.getMessage() + RESET);
                                                                    error = true;
                                                                }
                                                            } while(error);

                                                        } catch(Exception e) {
                                                            System.out.println(ROJO + e.getMessage() + RESET);
                                                            error = true;
                                                        }
                                                    } while(error);

                                                    BD.insertarCliente(cliente);
                                                    
                                                } else if (tipo.equals("p")) {

                                                    Proveedor proveedor = new Proveedor(0, "X00000000", "X", "666999777","x@x.xx", "X");

                                                    do{
                                                        try {
                                                            System.out.println("\n Introduce los datos del nuevo proveedor");
                                                            System.out.println("\n CIF: " + RESET);
                                                            proveedor.setCif(sc.nextLine());

                                                            error = false;

                                                            do{
                                                                try {
                                                                    System.out.println(VERDE + "\n Nombre: " + RESET);
                                                                    proveedor.setNombre(sc.nextLine());

                                                                    error = false;

                                                                    do{
                                                                        try {
                                                                            System.out.println(VERDE + "\n Teléfono: " + RESET);
                                                                            proveedor.setTelefono(sc.nextLine());

                                                                            error = false;

                                                                            do{
                                                                                try {
                                                                                    System.out.println(VERDE + "\n Email: " + RESET);
                                                                                    proveedor.setEmail(sc.nextLine());

                                                                                    error = false;

                                                                                    do{
                                                                                        try {
                                                                                            System.out.println(VERDE + "\n Dirección: " + RESET);
                                                                                            proveedor.setDireccion(sc.nextLine());

                                                                                            error = false;

                                                                                        } catch(Exception e) {
                                                                                            System.out.println(ROJO + e.getMessage() + RESET);
                                                                                            error = true;
                                                                                        }
                                                                                    } while(error);

                                                                                } catch(Exception e) {
                                                                                    System.out.println(ROJO + e.getMessage() + RESET);
                                                                                    error = true;
                                                                                }
                                                                            } while(error);

                                                                        } catch(Exception e) {
                                                                            System.out.println(ROJO + e.getMessage() + RESET);
                                                                            error = true;
                                                                        }
                                                                    } while(error);

                                                                } catch(Exception e) {
                                                                    System.out.println(ROJO + e.getMessage() + RESET);
                                                                    error = true;
                                                                }
                                                            } while(error);

                                                        } catch(Exception e) {
                                                            System.out.println(ROJO + e.getMessage() + RESET);
                                                            error = true;
                                                        }
                                                    } while(error);

                                                    BD.insertarProveedor(proveedor);
                                                    
                                                } else {
                                                    System.out.println(ROJO + "\t\t¡Introduce una opcion válida!" + RESET);
                                                    error = true;
                                                }
                                            } while(error);
                                            break;

                                        case 2:
                                            nuevaPantalla();
                                            System.out.println(VERDE + "----------------------------------------------");
                                            System.out.println("\t\tELIMINAR CLIENTE/PROVEEDOR");
                                            System.out.println("----------------------------------------------" + RESET);

                                            do {
                                                System.out.print("\n Elige entre cliente (c) o proveedor (p): " + RESET);
                                                String tipo = sc.nextLine().toLowerCase();

                                                if(tipo.equals("c")) {

                                                    do {
                                                        try {
                                                            System.out.print(VERDE + "\n Introduce el NIF del cliente que quieres borrar: " + RESET);
                                                            String nifCliente = sc.nextLine();

                                                            Cliente cliente1 = BD.mostrarCliente(nifCliente);

                                                            System.out.print(VERDE + "\n ¿Borrar al cliente " + cliente1.getNombre() + " " + cliente1.getApellidos() + " " + cliente1.getNif() + " de la base de datos? s/n: " + RESET);
                                                            String validar = sc.nextLine().toLowerCase();

                                                            if(validar.equals("s")) {
                                                                BD.borrarCliente(nifCliente);
                                                                error = false;

                                                                if(opcion == 0) {
                                                                    nuevaPantalla();
                                                                } else {
                                                                    System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                                                    sc.nextLine();
                                                                }
                                                            } else {
                                                                opcion = 0;
                                                                nuevaPantalla();
                                                            }
                                                        } catch (Exception e) {
                                                            System.out.println(ROJO + "\n\t" + e.getMessage() + RESET);
                                                            error = true;
                                                        }
                                                    } while(error);

                                                } else if(tipo.equals("p")) {

                                                    do {
                                                        try {
                                                            System.out.print(VERDE + "\n Introduce el CIF del proveedor que quieres borrar: " + RESET);
                                                            String cifProveedor = sc.nextLine();

                                                            Proveedor proveedor1 = BD.mostrarProveedor(cifProveedor);

                                                            System.out.print(VERDE + "\n ¿Borrar al proveedor " + proveedor1.getId() + " " + proveedor1.getNombre() + " " + proveedor1.getCif() + " de la base de datos? s/n: " + RESET);
                                                            String validar = sc.nextLine().toLowerCase();

                                                            if(validar.equals("s")) {
                                                                BD.borrarProveedor(cifProveedor);
                                                                error = false;

                                                                if(opcion == 0) {
                                                                    nuevaPantalla();
                                                                } else {
                                                                    System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                                                    sc.nextLine();
                                                                }
                                                            } else {
                                                                opcion = 0;
                                                                nuevaPantalla();
                                                            }
                                                        } catch (Exception e) {
                                                            System.out.println(ROJO + "\n\t" + e.getMessage() + RESET);
                                                            error = true;
                                                        }
                                                    } while(error);

                                                } else {
                                                    System.out.println(ROJO + "\t\t¡Introduce una opcion válida!" + RESET);
                                                    error = true;
                                                }
                                            } while(error);
                                            break;
                                    
                                        case 3:
                                            nuevaPantalla();
                                            System.out.println(VERDE + "----------------------------------------------");
                                            System.out.println("\t\tMODIFICAR CLIENTE/PROVEEDOR");
                                            System.out.println("----------------------------------------------" + RESET);

                                            do {
                                                System.out.print("\n Elige entre cliente (c) o proveedor (p): " + RESET);
                                                String tipo = sc.nextLine().toLowerCase();

                                                if(tipo.equals("c")) {
                                                    do {
                                                        try {
                                                            System.out.print(VERDE + "\n Introduce el NIF del cliente que quieres modificar: " + RESET);
                                                            String nifCliente = sc.nextLine();

                                                            Cliente cliente1 = BD.mostrarCliente(nifCliente);

                                                            do {
                                                                System.out.print(VERDE + "\n ¿Qué dato quieres modificar?: " + RESET);
                                                                String dato = sc.nextLine().toLowerCase();
                                                                
                                                                cliente1 = BD.modificarCliente(cliente1, dato);
                                                                error = false;

                                                                System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                                                sc.nextLine();

                                                            } while(error);

                                                        } catch (Exception e) {
                                                            System.out.println(ROJO + "\n\t" + e.getMessage() + RESET);
                                                            error = true;
                                                        }
                                                    } while(error);

                                                } else if(tipo.equals("p")) {

                                                    do {
                                                        try {
                                                            System.out.print(VERDE + "\n Introduce el CIF del proveedor que quieres modificar: " + RESET);
                                                            String cifProveedor = sc.nextLine();

                                                            Proveedor proveedor1 = BD.mostrarProveedor(cifProveedor);

                                                            do {
                                                                System.out.print(VERDE + "\n ¿Qué dato quieres modificar?: " + RESET);
                                                                String dato = sc.nextLine().toLowerCase();
                                                                
                                                                proveedor1 = BD.modificarProveedor(proveedor1, dato);
                                                                error = false;

                                                                System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                                                sc.nextLine();

                                                            } while(error);

                                                        } catch (Exception e) {
                                                            System.out.println(ROJO + "\n\t" + e.getMessage() + RESET);
                                                            error = true;
                                                        }
                                                    } while(error);
                                                } else {
                                                    System.out.println(ROJO + "\t\t¡Introduce una opcion válida!" + RESET);
                                                    error = true;
                                                }
                                            } while(error);
                                            break;

                                        case 4:
                                            nuevaPantalla();
                                            System.out.println(VERDE + "----------------------------------------------");
                                            System.out.println("\t\tDATOS CLIENTE/PROVEEDOR");
                                            System.out.println("----------------------------------------------" + RESET);

                                            do {
                                                System.out.print("\n Elige entre cliente (c) o proveedor (p): " + RESET);
                                                String tipo = sc.nextLine().toLowerCase();

                                                if(tipo.equals("c")) {
                                                    do {
                                                        try {
                                                            System.out.print(VERDE + "\n Introduce el NIF del cliente que quieres mostrar: " + RESET);
                                                            String nifCliente = sc.nextLine();

                                                            Cliente cliente1 = BD.mostrarCliente(nifCliente);

                                                            error = false;

                                                            System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                                            sc.nextLine();

                                                        } catch (Exception e) {
                                                            System.out.println(ROJO + "\n\t" + e.getMessage() + RESET);
                                                            error = true;
                                                        }
                                                    } while(error);

                                                } else if(tipo.equals("p")) {

                                                    do {
                                                        try {
                                                            System.out.print(VERDE + "\n Introduce el cif del proveedor que quieres mostrar: " + RESET);
                                                            String cifProveedor = sc.nextLine();

                                                            Proveedor proveedor1 = BD.mostrarProveedor(cifProveedor);

                                                            error = false;

                                                            System.out.println(VERDE + "\n Pulsa ENTER para continuar..." + RESET);
                                                            sc.nextLine();

                                                        } catch (Exception e) {
                                                            System.out.println(ROJO + "\n\t" + e.getMessage() + RESET);
                                                            error = true;
                                                        }
                                                    } while(error);
                                                } else {
                                                    System.out.println(ROJO + "\t\t¡Introduce una opcion válida!" + RESET);
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

                    case 7:
                        opcion = 0;
                        break;

                    case 8:
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
                                            if(usuarioActual.getTipo().equals("gestor")) {
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
                    case 9:
                        salir();

                    default:
                        break;
                }
            } catch(Exception e) {

                nuevaPantalla();
                System.out.println(ROJO + "\tError, introduce un número [1-9]\n" + RESET);
            }
            
        } while(opcion < 1 || opcion > 8);

        sc.close();
    }
}