/*
Proyecto Intermodular 1 - Stockerty
v.1.0
Aplicación para la gestión logística
 */

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
        
        System.out.print(VERDE + "\n\t\tUsuario: " + RESET);
        int idUsuario = Integer.parseInt(sc.nextLine());
        
        Empleado usuarioActual = BD.conectar(idUsuario);

        // ERROR al crear usuario actual (tipo de empleado incorrecto)

        do {
            try {
                System.out.println(VERDE + "==============================================");
                System.out.println("\t\tMENU PRINCIPAL");
                System.out.println("==============================================");
                System.out.println(GRIS_OSCURO + "\n\t1-RECEPCIÓN");
                System.out.println("\n\t2-EXPEDICIÓN");
                System.out.println("\n\t3-PEDIDOS" + RESET);

                if(usuarioActual.getTipo().equals("gestor")) {
                    System.out.println(VERDE + "\n\t4-ALMACÉN");
                } else {
                    System.out.println(GRIS_OSCURO + "\n\t4-ALMACÉN");
                }  
                System.out.println(VERDE + "\n\t5-PRODUCTOS");
                System.out.println(GRIS_OSCURO + "\n\t6-HISTÓRICO");
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
                        if(usuarioActual.getTipo().equals("gestor")) {

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
                        } else {
                            opcion = 0;
                        }
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