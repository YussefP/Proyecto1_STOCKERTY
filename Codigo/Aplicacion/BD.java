
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.ResultSet;

public class BD {
    
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

    // Métodos de la clase

    // Empleado ==========================================================================================================================================================
    /**
     * Método para acceder al programa principal, conectar con la base de datos y comprobar que el usuario y contraseña existen en la base de datos
     * @param usuario
     * @return
     */
    public static Empleado conectar(int id) {

        Scanner sc = new Scanner(System.in);

        Empleado usuario = null;
        boolean existe = false;

        String sql = "SELECT * FROM empleado WHERE id = ?";

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
                    
            while (rs.next()) {
                
                if(id == rs.getInt("id")) {
                    
                    existe = true;
                    
                    do {
                        System.out.print(VERDE + "\n\t\tContraseña: " + RESET);
                        String clave = sc.nextLine();
                            
                        if(clave.equals(rs.getString("contrasenya"))) {
                            
                            existe = true;

                            int idUsuario = 0, idDepartamento = 0;
                            String contrasenya = "", nif = "", nombre = "", apellidos = "", puesto = "", direccion = "", email = "", telefono = "", tipo = "";
                                
                            idUsuario = rs.getInt("id");
                            contrasenya = rs.getString("contrasenya");
                            nif = rs.getString("nif");
                            nombre = rs.getString("nombre");
                            apellidos = rs.getString("apellidos");
                            puesto = rs.getString("puesto");
                            direccion = rs.getString("direccion");
                            email = rs.getString("email");
                            telefono = rs.getString("telefono");
                            idDepartamento = rs.getInt("id_departamento");
                            tipo = rs.getString("tipo");

                            usuario = new Empleado(idUsuario, contrasenya, nif, nombre, apellidos, puesto, direccion, email, telefono, idDepartamento, tipo);

                        } else {
                            existe = false;
                        }   
                    } while (!existe);

                } else {
                    System.out.println(ROJO + "\n\tEl ID de usuario no existe" + RESET);
                }   
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión a la base de datos: " + e.getMessage() + RESET);
        }
        return usuario;
    }

    /**
     * Método para insertar un nuevo empleado en la base de datos
     * @param empleado
     * @return
     */
    public static String insertarEmpleado(Empleado empleado) {

        String sql = "INSERT INTO empleado (contrasenya, nif, nombre, apellidos, puesto, direccion, email, telefono, id_departamento, tipo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        String infoEmpleado = "";

        String sql2 = "SELECT id, contrasenya FROM empleado ORDER BY id DESC LIMIT 1";

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, empleado.getApellidos().toLowerCase().replaceAll("\\s+", ""));
            ps.setString(2, empleado.getNif());
            ps.setString(3, empleado.getNombre());
            ps.setString(4, empleado.getApellidos());
            ps.setString(5, empleado.getPuesto());
            ps.setString(6, empleado.getDireccion());
            ps.setString(7, empleado.getEmail());
            ps.setString(8, empleado.getTelefono());
            ps.setInt(9, empleado.getIdDepartamento());
            ps.setString(10, empleado.getTipo());

            int filas = ps.executeUpdate();

            if(filas > 0) {
                System.out.println(VERDE + "\n\t¡Nuevo empleado creado!");

                try (PreparedStatement pst = conexion.prepareStatement(sql2);
                    ResultSet rs = pst.executeQuery()) {

                    while (rs.next()) {

                        infoEmpleado = rs.getString("id" + " " + "contrasenya");
                    }
                }
            } else {
                System.out.println(ROJO + "\n\tError al crear el empleado" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión a la base de datos: " + e.getMessage() + RESET);
        }
        return infoEmpleado;
    }    

    /**
     * Método para borrar un empleado existente en la base de datos
     * @param id
     */
    public static void borrarEmpleado(int id) {

        String sql = "DELETE FROM empleado WHERE id = ?";
            
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);
            
            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println(VERDE + "\n\t¡Empleado eliminado con éxito!" + RESET);
            } else {
                System.out.println(ROJO + "\n\tEl id no pertenece a ningún empleado" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión con la base de datos: " + e.getMessage() + RESET);
        }    
    }

    /**
     * Método para modificar un empleado de la base de datos
     * @param empleado
     * @param dato
     * @return
     */
    public static Empleado modificarEmpleado(Empleado empleado, String dato) {

        String sql = "UPDATE empleado SET " + dato + " = ? WHERE id = ?";
            
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(2, empleado.getId());

            System.out.print(VERDE + "\n Nuevo " + dato + ": " + RESET);
            Scanner sc = new Scanner(System.in);

            String info = sc.nextLine();
            
            switch(dato) {
                case "contrasenya":
                    empleado.setContrasenya(info);
                    ps.setString(1, info);
                    break;

                case "nif":
                    empleado.setNif(info);
                    ps.setString(1, info);
                    break;

                case "nombre":
                    empleado.setNombre(info);
                    ps.setString(1, info);
                    break;

                case "apellidos":
                    empleado.setApellidos(info);
                    ps.setString(1, info);
                    break;

                case "puesto":
                    empleado.setPuesto(info);
                    ps.setString(1, info);
                    break;

                case "direccion":
                    empleado.setDireccion(info);
                    ps.setString(1, info);
                    break;

                case "email":
                    empleado.setEmail(info);
                    ps.setString(1, info);
                    break;

                case "telefono":
                    empleado.setTelefono(info);
                    ps.setString(1, info);
                    break;

                case "id_departamento":
                    empleado.setIdDepartamento(Integer.parseInt(info));
                    ps.setString(1, info);
                    break;

                case "tipo":
                    empleado.setTipo(info);
                    ps.setString(1, info);
                    break;

                default:
                    break;
            }
            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println(VERDE + "\n\t¡Dato modificado con éxito!" + RESET);
            } else {
                System.out.println(ROJO + "\n\tError al modificar el dato" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión con la base de datos: " + e.getMessage() + RESET);
        } catch(Exception ex) {
            System.out.println(ROJO + ex.getMessage() + RESET);
        }
        return empleado;
    }

    /**
     * Método para mostrar cualquier empleado de la base de datos mediante su número ID
     * @param id
     */
    public static Empleado mostrarEmpleado(int id) {

        String sql = "SELECT * FROM empleado WHERE id = ?";

        Empleado empleado = null;

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            String[] columnas = {"id", "contrasenya", "nif", "nombre", "apellidos", "puesto", "direccion", "email", "telefono", "id_departamento", "tipo"};
            
            System.out.println(VERDE + "\n-----------------------------------------------------------------------" + RESET);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
                    
            while (rs.next()) {
                
                empleado = new Empleado(0, "X", "00000000X", "X", "X", "X", "X","x@x.xx", "700000000", 0, "operario");

                for (String columna : columnas) {

                    System.out.print(VERDE + " " + columna.toUpperCase() + "\t" + RESET);

                    if(columna.length() < 8) {
                        System.out.println(VERDE + "\t\t->\t" + RESET + rs.getString(columna));
                    } else if(columna.length() == 8 || columna.length() < 15) {
                        System.out.println(VERDE + "\t->\t" + RESET + rs.getString(columna));
                    } else {
                        System.out.println(VERDE + "->\t" + RESET + rs.getString(columna));
                    }

                    switch(columna) {
                        case "id":
                            empleado.setId(rs.getInt(columna));
                            break;

                        case "contrasenya":
                            empleado.setContrasenya(rs.getString(columna));
                            break;

                        case "nif":
                            empleado.setNif(rs.getString(columna));
                            break;

                        case "nombre":
                            empleado.setNombre(rs.getString(columna));
                            break;

                        case "apellidos":
                            empleado.setApellidos(rs.getString(columna));
                            break;

                        case "puesto":
                            empleado.setPuesto(rs.getString(columna));
                            break;

                        case "direccion":
                            empleado.setDireccion(rs.getString(columna));
                            break;

                        case "email":
                            empleado.setEmail(rs.getString(columna));
                            break;

                        case "telefono":
                            empleado.setTelefono(rs.getString(columna));
                            break;

                        case "id_departamento":
                            empleado.setIdDepartamento(rs.getInt(columna));
                            break;

                        case "tipo":
                            empleado.setTipo(rs.getString(columna));
                            break;

                        default:
                            break;
                    }
                }
            }
            System.out.println(VERDE + "-----------------------------------------------------------------------" + RESET);   
            
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión a la base de datos: " + e.getMessage() + RESET);
        }
        return empleado;
    }

    // Producto ==========================================================================================================================================================

    /**
     * Método para insertar un nuevo producto en la base de datos
     * @param producto
     */
    public static void insertarProducto(Producto producto) {

        String sql = "INSERT INTO producto (ean, nombre, descripcion, precio, stock, stock_minimo) VALUES (?, ?, ?, ?, ?, ?);";

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, producto.getEan());
            ps.setString(2, producto.getNombre());
            ps.setString(3, producto.getDescripcion());
            ps.setBigDecimal(4, producto.getPrecio());
            ps.setInt(5, producto.getStock());
            ps.setInt(6, producto.getStockMinimo());

            int filas = ps.executeUpdate();

            if(filas > 0) {
                System.out.println(VERDE + "\n\t¡Nuevo producto creado!");
            } else {
                System.out.println(ROJO + "\n\tError al crear el producto" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión a la base de datos: " + e.getMessage() + RESET);
        }
    }

    /**
     * Método para eliminar un producto de la base de datos
     * @param id
     */
    public static void borrarProducto(int id) {

        String sql = "DELETE FROM producto WHERE id = ?";
            
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println(VERDE + "\n\t¡Producto eliminado con éxito!" + RESET);
            } else {
                System.out.println(ROJO + "\n\tEl id no pertenece a ningún producto" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión con la base de datos: " + e.getMessage() + RESET);
        }    
    }

    /**
     * Método para modificar un producto de la base de datos
     * @param producto
     * @param dato
     * @return
     */
    public static Producto modificarProducto(Producto producto, String dato) {

        String sql = "UPDATE empleado SET " + dato + " = ? WHERE id = ?";
            
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(2, producto.getId());

            System.out.print(VERDE + "\n Nuevo " + dato + ": " + RESET);
            Scanner sc = new Scanner(System.in);

            String info = sc.nextLine();
            
            switch(dato) {
                case "ean":
                    producto.setEan(info);
                    ps.setString(1, info);
                    break;

                case "nombre":
                    producto.setNombre(info);
                    ps.setString(1, info);
                    break;

                case "descripcion":
                    producto.setDescripcion(info);
                    ps.setString(1, info);
                    break;

                case "precio":
                    producto.setPrecio(info);
                    ps.setString(1, info);
                    break;

                case "stock":
                    producto.setStock(Integer.parseInt(info));
                    ps.setString(1, info);
                    break;

                case "stock_minimo":
                    producto.setStockMinimo(Integer.parseInt(info));
                    ps.setString(1, info);
                    break;

                default:
                    break;
            }
            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println(VERDE + "\n\t¡Dato modificado con éxito!" + RESET);
            } else {
                System.out.println(ROJO + "\n\tError al modificar el dato" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión con la base de datos: " + e.getMessage() + RESET);
        } catch(Exception ex) {
            System.out.println(ROJO + ex.getMessage() + RESET);
        }
        return producto;
    }

    /**
     * Método para mostrar un producto de la base de datos
     * @param id
     */
    public static Producto mostrarProducto(int id) {

        String sql = "SELECT * FROM producto WHERE id = ?";

        Producto producto = null;

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            String[] columnas = {"id", "ean", "nombre", "descripcion", "precio", "stock", "stock_minimo", "id_ubicacion"};
            
            System.out.println(VERDE + "\n-----------------------------------------------------------------------" + RESET);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
                    
            while (rs.next()) {
                
                producto = new Producto(0, "0000000000000", "X", "X", "0.00", 0, 0);
                
                for (String columna : columnas) {

                    System.out.print(VERDE + " " + columna.toUpperCase() + "\t" + RESET);

                    if(columna.length() < 8) {
                        System.out.println(VERDE + "\t\t->\t" + RESET + rs.getString(columna));
                    } else if(columna.length() == 8 || columna.length() < 15) {
                        System.out.println(VERDE + "\t->\t" + RESET + rs.getString(columna));
                    } else {
                        System.out.println(VERDE + "->\t" + RESET + rs.getString(columna));
                    }

                    switch(columna) {
                        case "id":
                            producto.setId(rs.getInt(columna));
                            break;

                        case "ean":
                            producto.setEan(rs.getString(columna));
                            break;

                        case "nombre":
                            producto.setNombre(rs.getString(columna));
                            break;

                        case "descripcion":
                            producto.setNombre(rs.getString(columna));
                            break;

                        case "precio":
                            producto.setPrecio(rs.getString(columna));
                            break;

                        case "stock":
                            producto.setStock(rs.getInt(columna));
                            break;

                        case "stock_minimo":
                            producto.setStockMinimo(rs.getInt(columna));
                            break;

                        default:
                            break;
                    }
                }
            System.out.println(VERDE + "-----------------------------------------------------------------------" + RESET);   
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión a la base de datos: " + e.getMessage() + RESET);
        }
        return producto;
    }

    // Zona ==========================================================================================================================================================

    /**
     * Método para insertar un nueva zona en la base de datos
     * @param zona
     */
    public static void insertarZona(Zona zona) {

        String sql = "INSERT INTO zona (nombre, id_almacen) VALUES (?, ?);";

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, zona.getNombre());
            ps.setInt(2, zona.getIdAlmacen());

            int filas = ps.executeUpdate();

            if(filas > 0) {
                System.out.println(VERDE + "\n\t¡Nueva zona creada!");
            } else {
                System.out.println(ROJO + "\n\tError al crear la zona" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión a la base de datos: " + e.getMessage() + RESET);
        }
    }

    /**
     * Método para eliminar una zona de la base de datos
     * @param id
     */
    public static void borrarZona(int id) {

        String sql = "DELETE FROM zona WHERE id = ?";
            
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println(VERDE + "\n\t¡Zona eliminada con éxito!" + RESET);
            } else {
                System.out.println(ROJO + "\n\tEl id no pertenece a ninguna zona" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión con la base de datos: " + e.getMessage() + RESET);
        }    
    }

    /**
     * Método para modificar una zona de la base de datos
     * @param zona
     * @param dato
     * @return
     */
    public static Zona modificarZona(Zona zona, String dato) {

        String sql = "UPDATE zona SET " + dato + " = ? WHERE id = ?";
            
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(2, zona.getId());

            System.out.print(VERDE + "\n Nuevo " + dato + ": " + RESET);
            Scanner sc = new Scanner(System.in);

            String info = sc.nextLine();
            
            switch(dato) {
                case "nombre":
                    zona.setNombre(info);
                    ps.setString(1, info);
                    break;

                case "id_almacen":
                    zona.setIdAlmacen(Integer.parseInt(info));
                    ps.setString(1, info);
                    break;

                default:
                    break;
            }
            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println(VERDE + "\n\t¡Dato modificado con éxito!" + RESET);
            } else {
                System.out.println(ROJO + "\n\tError al modificar el dato" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión con la base de datos: " + e.getMessage() + RESET);
        } catch(Exception ex) {
            System.out.println(ROJO + ex.getMessage() + RESET);
        }
        return zona;
    }

    /**
     * Método para mostrar una zona de la base de datos
     * @param id
     */
    public static Zona mostrarZona(int id) {

        String sql = "SELECT * FROM zona WHERE id = ?";

        Zona zona = null;

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            String[] columnas = {"id", "nombre", "id_almacen"};
            
            System.out.println(VERDE + "\n-----------------------------------------------" + RESET);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
                    
            while (rs.next()) {
                
                zona = new Zona(0, "X", 0);
                
                for (String columna : columnas) {

                    System.out.print(VERDE + " " + columna.toUpperCase() + "\t" + RESET);

                    if(columna.length() < 8) {
                        System.out.println(VERDE + "\t\t->\t" + RESET + rs.getString(columna));
                    } else if(columna.length() == 8 || columna.length() < 15) {
                        System.out.println(VERDE + "\t->\t" + RESET + rs.getString(columna));
                    } else {
                        System.out.println(VERDE + "->\t" + RESET + rs.getString(columna));
                    }

                    switch(columna) {
                        case "id":
                            zona.setId(rs.getInt(columna));
                            break;

                        case "nombre":
                            zona.setNombre(rs.getString(columna));
                            break;

                        case "id_almacen":
                            zona.setIdAlmacen(rs.getInt(columna));
                            break;

                        default:
                            break;
                    }
                }
            System.out.println(VERDE + "-----------------------------------------------" + RESET);   
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión a la base de datos: " + e.getMessage() + RESET);
        }
        return zona;
    }

    // Pasillo ==========================================================================================================================================================

    /**
     * Método para insertar un nuevo pasillo en la base de datos
     * @param pasillo
     */
    public static void insertarPasillo(Pasillo pasillo) {

        String sql = "INSERT INTO pasillo (nombre, id_zona) VALUES (?, ?);";

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, pasillo.getNombre());
            ps.setInt(2, pasillo.getIdZona());

            int filas = ps.executeUpdate();

            if(filas > 0) {
                System.out.println(VERDE + "\n\t¡Nuevo pasillo creado!");
            } else {
                System.out.println(ROJO + "\n\tError al crear el pasillo" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión a la base de datos: " + e.getMessage() + RESET);
        }
    }

    /**
     * Método para eliminar un pasillo de la base de datos
     * @param id
     */
    public static void borrarPasillo(int id) {

        String sql = "DELETE FROM pasillo WHERE id = ?";
            
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println(VERDE + "\n\t¡Pasillo eliminado con éxito!" + RESET);
            } else {
                System.out.println(ROJO + "\n\tEl id no pertenece a ningún pasillo" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión con la base de datos: " + e.getMessage() + RESET);
        }    
    }

    /**
     * Método para modificar un pasillo de la base de datos
     * @param pasillo
     * @param dato
     * @return
     */
    public static Pasillo modificarPasillo(Pasillo pasillo, String dato) {

        String sql = "UPDATE pasillo SET " + dato + " = ? WHERE id = ?";
            
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(2, pasillo.getId());

            System.out.print(VERDE + "\n Nuevo " + dato + ": " + RESET);
            Scanner sc = new Scanner(System.in);

            String info = sc.nextLine();
            
            switch(dato) {
                case "nombre":
                    pasillo.setNombre(info);
                    ps.setString(1, info);
                    break;

                case "id_zona":
                    pasillo.setIdZona(Integer.parseInt(info));
                    ps.setString(1, info);
                    break;

                default:
                    break;
            }
            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println(VERDE + "\n\t¡Dato modificado con éxito!" + RESET);
            } else {
                System.out.println(ROJO + "\n\tError al modificar el dato" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión con la base de datos: " + e.getMessage() + RESET);
        } catch(Exception ex) {
            System.out.println(ROJO + ex.getMessage() + RESET);
        }
        return pasillo;
    }

    /**
     * Método para mostrar un pasillo de la base de datos
     * @param id
     */
    public static Pasillo mostrarPasillo(int id) {

        String sql = "SELECT * FROM pasillo WHERE id = ?";

        Pasillo pasillo = null;

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            String[] columnas = {"id", "nombre", "id_zona"};
            
            System.out.println(VERDE + "\n-----------------------------------------------" + RESET);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
                    
            while (rs.next()) {
                
                pasillo = new Pasillo(0, "X", 0);
                
                for (String columna : columnas) {

                    System.out.print(VERDE + " " + columna.toUpperCase() + "\t" + RESET);

                    if(columna.length() < 8) {
                        System.out.println(VERDE + "\t\t->\t" + RESET + rs.getString(columna));
                    } else if(columna.length() == 8 || columna.length() < 15) {
                        System.out.println(VERDE + "\t->\t" + RESET + rs.getString(columna));
                    } else {
                        System.out.println(VERDE + "->\t" + RESET + rs.getString(columna));
                    }

                    switch(columna) {
                        case "id":
                            pasillo.setId(rs.getInt(columna));
                            break;

                        case "nombre":
                            pasillo.setNombre(rs.getString(columna));
                            break;

                        case "id_zona":
                            pasillo.setIdZona(rs.getInt(columna));
                            break;

                        default:
                            break;
                    }
                }
            System.out.println(VERDE + "-----------------------------------------------" + RESET);   
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión a la base de datos: " + e.getMessage() + RESET);
        }
        return pasillo;
    }

    // Rack ==========================================================================================================================================================

    /**
     * Método para insertar un nuevo rack en la base de datos
     * @param rack
     */
    public static int insertarRack(Rack rack) {

        String sql = "INSERT INTO rack (nombre, id_pasillo) VALUES (?, ?);";

        int idRack = 0;

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, rack.getNombre());
            ps.setInt(2, rack.getIdPasillo());

            int filas = ps.executeUpdate();

            if(filas > 0) {
                System.out.println(VERDE + "\n\t¡Nuevo rack creado!");

                String sql2 = "SELECT id FROM rack ORDER BY id DESC LIMIT 1";

                try (PreparedStatement pst = conexion.prepareStatement(sql2);
                    ResultSet rs = pst.executeQuery()) {
                    
                    while (rs.next()) {

                        idRack = rs.getInt("id");
                    }
                }
            } else {
                System.out.println(ROJO + "\n\tError al crear el rack" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión a la base de datos: " + e.getMessage() + RESET);
        }

        return idRack;
    }

    /**
     * Método para eliminar un rack de la base de datos
     * @param id
     */
    public static void borrarRack(int id) {

        String sql = "DELETE FROM rack WHERE id = ?";
            
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println(VERDE + "\n\t¡Rack eliminado con éxito!" + RESET);
            } else {
                System.out.println(ROJO + "\n\tEl id no pertenece a ningún rack" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión con la base de datos: " + e.getMessage() + RESET);
        }    
    }

    /**
     * Método para modificar un rack de la base de datos
     * @param rack
     * @param dato
     * @return
     */
    public static Rack modificarRack(Rack rack, String dato) {

        String sql = "UPDATE rack SET " + dato + " = ? WHERE id = ?";
            
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(2, rack.getId());

            System.out.print(VERDE + "\n Nuevo " + dato + ": " + RESET);
            Scanner sc = new Scanner(System.in);

            String info = sc.nextLine();
            
            switch(dato) {
                case "nombre":
                    rack.setNombre(info);
                    ps.setString(1, info);
                    break;

                case "id_pasillo":
                    rack.setIdPasillo(Integer.parseInt(info));
                    ps.setString(1, info);
                    break;

                default:
                    break;
            }
            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println(VERDE + "\n\t¡Dato modificado con éxito!" + RESET);
            } else {
                System.out.println(ROJO + "\n\tError al modificar el dato" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión con la base de datos: " + e.getMessage() + RESET);
        } catch(Exception ex) {
            System.out.println(ROJO + ex.getMessage() + RESET);
        }
        return rack;
    }

    /**
     * Método para mostrar un rack de la base de datos
     * @param id
     */
    public static Rack mostrarRack(int id) {

        String sql = "SELECT * FROM rack WHERE id = ?";

        Rack rack = null;

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            String[] columnas = {"id", "nombre", "id_pasillo"};
            
            System.out.println(VERDE + "\n-----------------------------------------------" + RESET);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
                    
            while (rs.next()) {
                
                rack = new Rack(0, "X", 0);
                
                for (String columna : columnas) {

                    System.out.print(VERDE + " " + columna.toUpperCase() + "\t" + RESET);

                    if(columna.length() < 8) {
                        System.out.println(VERDE + "\t\t->\t" + RESET + rs.getString(columna));
                    } else if(columna.length() == 8 || columna.length() < 15) {
                        System.out.println(VERDE + "\t->\t" + RESET + rs.getString(columna));
                    } else {
                        System.out.println(VERDE + "->\t" + RESET + rs.getString(columna));
                    }

                    switch(columna) {
                        case "id":
                            rack.setId(rs.getInt(columna));
                            break;

                        case "nombre":
                            rack.setNombre(rs.getString(columna));
                            break;

                        case "id_pasillo":
                            rack.setIdPasillo(rs.getInt(columna));
                            break;

                        default:
                            break;
                    }
                }
            System.out.println(VERDE + "-----------------------------------------------" + RESET);   
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión a la base de datos: " + e.getMessage() + RESET);
        }
        return rack;
    }

    // Altura ==========================================================================================================================================================

    /**
     * Método para insertar un nueva altura en la base de datos
     * @param altura
     */
    public static int insertarAltura(Altura altura) {

        String sql = "INSERT INTO altura (nivel, id_rack) VALUES (?, ?);";

        int idAltura = 0;

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, altura.getNivel());
            ps.setInt(2, altura.getIdRack());

            int filas = ps.executeUpdate();

            if(filas > 0) {
                System.out.println(VERDE + "\n\t¡Nueva altura creada!");

                String sql2 = "SELECT id FROM altura ORDER BY id DESC LIMIT 1";

                try (PreparedStatement pst = conexion.prepareStatement(sql2);
                    ResultSet rs = pst.executeQuery()) {
                    
                    while (rs.next()) {

                        idAltura = rs.getInt("id");
                    }
                }
            } else {
                System.out.println(ROJO + "\n\tError al crear la altura" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión a la base de datos: " + e.getMessage() + RESET);
        }
        return idAltura;
    }

    /**
     * Método para eliminar una altura de la base de datos
     * @param id
     */
    public static void borrarAltura(int id) {

        String sql = "DELETE FROM altura WHERE id = ?";
            
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println(VERDE + "\n\t¡Altura eliminada con éxito!" + RESET);
            } else {
                System.out.println(ROJO + "\n\tEl id no pertenece a ninguna altura" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión con la base de datos: " + e.getMessage() + RESET);
        }    
    }

    /**
     * Método para modificar una altura de la base de datos
     * @param altura
     * @param dato
     * @return
     */
    public static Altura modificarAltura(Altura altura, String dato) {

        String sql = "UPDATE altura SET " + dato + " = ? WHERE id = ?";
            
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(2, altura.getId());

            System.out.print(VERDE + "\n Nuevo " + dato + ": " + RESET);
            Scanner sc = new Scanner(System.in);

            String info = sc.nextLine();
            
            switch(dato) {
                case "nivel":
                    altura.setNivel(Integer.parseInt(info));
                    ps.setString(1, info);
                    break;

                case "id_rack":
                    altura.setIdRack(Integer.parseInt(info));
                    ps.setString(1, info);
                    break;

                default:
                    break;
            }
            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println(VERDE + "\n\t¡Dato modificado con éxito!" + RESET);
            } else {
                System.out.println(ROJO + "\n\tError al modificar el dato" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión con la base de datos: " + e.getMessage() + RESET);
        } catch(Exception ex) {
            System.out.println(ROJO + ex.getMessage() + RESET);
        }
        return altura;
    }

    /**
     * Método para mostrar una altura de la base de datos
     * @param id
     */
    public static Altura mostrarAltura(int id) {

        String sql = "SELECT * FROM altura WHERE id = ?";

        Altura altura = null;

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            String[] columnas = {"id", "nivel", "id_rack"};
            
            System.out.println(VERDE + "\n-----------------------------------------------" + RESET);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
                    
            while (rs.next()) {
                
                altura = new Altura(0, 0, 0);
                
                for (String columna : columnas) {

                    System.out.print(VERDE + " " + columna.toUpperCase() + "\t" + RESET);

                    if(columna.length() < 8) {
                        System.out.println(VERDE + "\t\t->\t" + RESET + rs.getString(columna));
                    } else if(columna.length() == 8 || columna.length() < 15) {
                        System.out.println(VERDE + "\t->\t" + RESET + rs.getString(columna));
                    } else {
                        System.out.println(VERDE + "->\t" + RESET + rs.getString(columna));
                    }

                    switch(columna) {
                        case "id":
                            altura.setId(rs.getInt(columna));
                            break;

                        case "nivel":
                            altura.setNivel(rs.getInt(columna));
                            break;

                        case "id_rack":
                            altura.setIdRack(rs.getInt(columna));
                            break;

                        default:
                            break;
                    }
                }
            System.out.println(VERDE + "-----------------------------------------------" + RESET);   
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión a la base de datos: " + e.getMessage() + RESET);
        }
        return altura;
    }

    // Dirección ==========================================================================================================================================================

    /**
     * Método para insertar una nueva dirección en la base de datos
     * @param direccion
     */
    public static int insertarDireccion(Direccion direccion) {

        String sql = "INSERT INTO direccion (posicion, id_altura) VALUES (?, ?);";

        int idDireccion = 0;

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, direccion.getPosicion());
            ps.setInt(2, direccion.getIdAltura());

            int filas = ps.executeUpdate();

            if(filas > 0) {
                System.out.println(VERDE + "\n\t¡Nueva direccion creada!");

                 String sql2 = "SELECT id FROM direccion ORDER BY id DESC LIMIT 1";

                try (PreparedStatement pst = conexion.prepareStatement(sql2);
                    ResultSet rs = pst.executeQuery()) {
                    
                    while (rs.next()) {

                        idDireccion = rs.getInt("id");
                    }
                }
            } else {
                System.out.println(ROJO + "\n\tError al crear la direccion" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión a la base de datos: " + e.getMessage() + RESET);
        }
        return idDireccion;
    }

    /**
     * Método para eliminar una direccion de la base de datos
     * @param id
     */
    public static void borrarDireccion(int id) {

        String sql = "DELETE FROM direccion WHERE id = ?";
            
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println(VERDE + "\n\t¡Direccion eliminada con éxito!" + RESET);
            } else {
                System.out.println(ROJO + "\n\tEl id no pertenece a ninguna dirección" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión con la base de datos: " + e.getMessage() + RESET);
        }    
    }

    /**
     * Método para modificar una dirección de la base de datos
     * @param direccion
     * @param dato
     * @return
     */
    public static Direccion modificarDireccion(Direccion direccion, String dato) {

        String sql = "UPDATE direccion SET " + dato + " = ? WHERE id = ?";
            
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(2, direccion.getId());

            System.out.print(VERDE + "\n Nuevo " + dato + ": " + RESET);
            Scanner sc = new Scanner(System.in);

            String info = sc.nextLine();
            
            switch(dato) {
                case "posicion":
                    direccion.setPosicion(info);
                    ps.setString(1, info);
                    break;

                case "id_altura":
                    direccion.setIdAltura(Integer.parseInt(info));
                    ps.setString(1, info);
                    break;

                default:
                    break;
            }
            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println(VERDE + "\n\t¡Dato modificado con éxito!" + RESET);
            } else {
                System.out.println(ROJO + "\n\tError al modificar el dato" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión con la base de datos: " + e.getMessage() + RESET);
        } catch(Exception ex) {
            System.out.println(ROJO + ex.getMessage() + RESET);
        }
        return direccion;
    }

    /**
     * Método para mostrar una dirección de la base de datos
     * @param id
     */
    public static Direccion mostrarDireccion(int id) {

        String sql = "SELECT * FROM direccion WHERE id = ?";

        Direccion direccion = null;

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            String[] columnas = {"id", "posicion", "id_altura"};
            
            System.out.println(VERDE + "\n-----------------------------------------------" + RESET);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
                    
            while (rs.next()) {
                
                direccion = new Direccion(0, "X", 0);
                
                for (String columna : columnas) {

                    System.out.print(VERDE + " " + columna.toUpperCase() + "\t" + RESET);

                    if(columna.length() < 8) {
                        System.out.println(VERDE + "\t\t->\t" + RESET + rs.getString(columna));
                    } else if(columna.length() == 8 || columna.length() < 15) {
                        System.out.println(VERDE + "\t->\t" + RESET + rs.getString(columna));
                    } else {
                        System.out.println(VERDE + "->\t" + RESET + rs.getString(columna));
                    }

                    switch(columna) {
                        case "id":
                            direccion.setId(rs.getInt(columna));
                            break;

                        case "posicion":
                            direccion.setPosicion(rs.getString(columna));
                            break;

                        case "id_altura":
                            direccion.setIdAltura(rs.getInt(columna));
                            break;

                        default:
                            break;
                    }
                }
            System.out.println(VERDE + "-----------------------------------------------" + RESET);   
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión a la base de datos: " + e.getMessage() + RESET);
        }
        return direccion;
    }

    /*
    public void verEstructura(int idAlmacen) {

        String sql = "SELECT * FROM almacen WHERE id_almacen = ?";
    }
    */

    // Ubicación ==========================================================================================================================================================

    /**
     * Método para insertar un nueva ubicación en la base de datos
     * @param ubicacion
     */
    public static void insertarUbicacion(Ubicacion ubicacion) {

        String sql = "INSERT INTO ubicacion (nombre, id_rack, id_altura, id_direccion) VALUES (?, ?, ?, ?);";

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, ubicacion.getNombre());
            ps.setInt(2, ubicacion.getIdRack());
            ps.setInt(3, ubicacion.getIdAltura());
            ps.setInt(4, ubicacion.getIdDireccion());

            int filas = ps.executeUpdate();

            if(filas > 0) {
                System.out.println(VERDE + "\n\t¡Nueva ubicación creada!");
            } else {
                System.out.println(ROJO + "\n\tError al crear la ubicación" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión a la base de datos: " + e.getMessage() + RESET);
        }
    }

    /**
     * Método para eliminar una ubicación de la base de datos
     * @param id
     */
    public static void borrarUbicacion(int id) {

        String sql = "DELETE FROM ubicacion WHERE id = ?";
            
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println(VERDE + "\n\t¡Ubicación eliminada con éxito!" + RESET);
            } else {
                System.out.println(ROJO + "\n\tEl id no pertenece a ninguna ubicación" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión con la base de datos: " + e.getMessage() + RESET);
        }    
    }

    /**
     * Método para modificar una ubicación de la base de datos
     * @param ubicacion
     * @param dato
     * @return
     */
    public static Ubicacion modificarUbicacion(Ubicacion ubicacion, String dato) {

        String sql = "UPDATE ubicacion SET " + dato + " = ? WHERE id = ?";
            
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(2, ubicacion.getId());

            System.out.print(VERDE + "\n Nuevo " + dato + ": " + RESET);
            Scanner sc = new Scanner(System.in);

            String info = sc.nextLine();
            
            switch(dato) {
                case "nombre":
                    ubicacion.setNombre(info);
                    ps.setString(1, info);
                    break;

                case "id_rack":
                    ubicacion.setIdRack(Integer.parseInt(info));
                    ps.setString(1, info);
                    break;

                case "id_altura":
                    ubicacion.setIdAltura(Integer.parseInt(info));
                    ps.setString(1, info);
                    break;

                case "id_direccion":
                    ubicacion.setIdDireccion(Integer.parseInt(info));
                    ps.setString(1, info);
                    break;

                default:
                    break;
            }
            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println(VERDE + "\n\t¡Dato modificado con éxito!" + RESET);
            } else {
                System.out.println(ROJO + "\n\tError al modificar el dato" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión con la base de datos: " + e.getMessage() + RESET);
        } catch(Exception ex) {
            System.out.println(ROJO + ex.getMessage() + RESET);
        }
        return ubicacion;
    }

    /**
     * Método para mostrar una ubicación de la base de datos
     * @param id
     */
    public static Ubicacion mostrarUbicacion(String nombre) {

        String sql = "SELECT * FROM ubicacion WHERE nombre = ?";

        Ubicacion ubicacion = null;

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            String[] columnas = {"id", "nombre", "id_rack", "id_altura", "id_direccion"};
            
            System.out.println(VERDE + "\n-------------------------------------------------------------------" + RESET);

            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
                    
            while (rs.next()) {
                
                ubicacion = new Ubicacion(0, "X", 0, 0, 0);
                
                for (String columna : columnas) {

                    System.out.print(VERDE + " " + columna.toUpperCase() + "\t" + RESET);

                    if(columna.length() < 8) {
                        System.out.println(VERDE + "\t\t->\t" + RESET + rs.getString(columna));
                    } else if(columna.length() == 8 || columna.length() < 15) {
                        System.out.println(VERDE + "\t->\t" + RESET + rs.getString(columna));
                    } else {
                        System.out.println(VERDE + "->\t" + RESET + rs.getString(columna));
                    }

                    switch(columna) {
                        case "id":
                            ubicacion.setId(rs.getInt(columna));
                            break;

                        case "nombre":
                            ubicacion.setNombre(rs.getString(columna));
                            break;

                        case "id_rack":
                            ubicacion.setIdRack(rs.getInt(columna));
                            break;

                        case "id_altura":
                            ubicacion.setIdAltura(rs.getInt(columna));
                            break;

                        case "id_direccion":
                            ubicacion.setIdDireccion(rs.getInt(columna));
                            break;

                        default:
                            break;
                    }
                }
            System.out.println(VERDE + "-------------------------------------------------------------------" + RESET);   
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión a la base de datos: " + e.getMessage() + RESET);
        }
        return ubicacion;
    }

    // Soporte ==========================================================================================================================================================

    /**
     * Método para insertar un nuevo soporte en la base de datos, devuelve el nº ID creado
     * @param soporte
     * @return
     */
    public static long insertarSoporte(Soporte soporte) {

        String sql = "INSERT INTO soporte (tipo, fecha, id_empleado) VALUES (?, ?, ?);";

        long idSoporte = 0;

        String sql2 = "SELECT id FROM soporte ORDER BY id DESC LIMIT 1";

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, soporte.getTipo());
            ps.setObject(2, soporte.getFecha());
            ps.setInt(3, soporte.getIdEmpleado());

            int filas = ps.executeUpdate();

            if(filas > 0) {
                System.out.println(VERDE + "\n\t¡Nuevo soporte creado!");

                try (PreparedStatement pst = conexion.prepareStatement(sql2);
                    ResultSet rs = pst.executeQuery()) {

                    while (rs.next()) {

                        idSoporte = rs.getLong("id");
                    }
                }
            } else {
                System.out.println(ROJO + "\n\tError al crear el soporte" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión a la base de datos: " + e.getMessage() + RESET);
        }

        return idSoporte;
    }

    /**
     * Método para eliminar un soporte de la base de datos
     * @param abreviatura
     */
    public static void borrarSoporte(int abreviatura) {

        String sql = "DELETE FROM soporte WHERE abreviatura = ?";
            
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, abreviatura);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println(VERDE + "\n\t¡Soporte eliminado con éxito!" + RESET);
            } else {
                System.out.println(ROJO + "\n\tEl id no pertenece a ningún soporte" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión con la base de datos: " + e.getMessage() + RESET);
        }    
    }

    /**
     * Método para modificar un soporte de la base de datos
     * @param soporte
     * @param dato
     * @return
     */
    public static Soporte modificarSoporte(Soporte soporte, String dato) {

        String sql = "UPDATE soporte SET " + dato + " = ? WHERE id = ?";
            
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setLong(2, soporte.getId());

            if (dato.equals("abreviatura")) {

                int abrev = (int)(soporte.getId() % 100000);
                soporte.setAbreviatura(abrev);
                ps.setInt(1, soporte.getAbreviatura());
                
            } else {
                System.out.print(VERDE + "\n Nuevo " + dato + ": " + RESET);
                Scanner sc = new Scanner(System.in);

                String info = sc.nextLine();
                
                switch(dato) {
                    case "tipo":
                        soporte.setTipo(info);
                        ps.setString(1, info);
                        break;

                    case "id_ubicacion":
                        soporte.setUbicacion(info);
                        ps.setString(1, info);
                        break;

                    case "id_empleado":
                        soporte.setIdEmpleado(Integer.parseInt(info));
                        ps.setString(1, info);
                        break;

                    default:
                        break;
                }
            }
            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println(VERDE + "\n\t¡Dato modificado con éxito!" + RESET);
            } else {
                System.out.println(ROJO + "\n\tError al modificar el dato" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión con la base de datos: " + e.getMessage() + RESET);
        } catch(Exception ex) {
            System.out.println(ROJO + ex.getMessage() + RESET);
        }
        return soporte;
    }

    /**
     * Método para modificar la ubicación de un soporte de la base de datos
     * @param soporte
     * @param dato
     * @return
     */
    public static Soporte modificarSoporte(Soporte soporte, String dato, String nombre) {

        String sql = "UPDATE soporte SET " + dato + " = ? WHERE id = ?";
            
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setLong(2, soporte.getId());

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println(VERDE + "\n\t¡Dato modificado con éxito!" + RESET);
            } else {
                System.out.println(ROJO + "\n\tError al modificar el dato" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión con la base de datos: " + e.getMessage() + RESET);
        } catch(Exception ex) {
            System.out.println(ROJO + ex.getMessage() + RESET);
        }
        return soporte;
    }

    /**
     * Método para mostrar un soporte de la base de datos
     * @param abreviatura
     */
    public static Soporte mostrarSoporte(int abreviatura) {

        String sql = "SELECT * FROM soporte WHERE abreviatura = ?";

        Soporte soporte = null;

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            String[] columnas = {"id", "abreviatura", "tipo", "id_ubicacion", "fecha", "id_empleado"};
            LocalDateTime fecha = LocalDateTime.now();
            List<Producto> lista = new ArrayList<>();
            
            System.out.println(VERDE + "\n-------------------------------------------------------------------" + RESET);

            ps.setInt(1, abreviatura);
            ResultSet rs = ps.executeQuery();
                    
            while (rs.next()) {
                
                soporte = new Soporte(4L, 30567, "bulto", "X", fecha, 0, lista);
                
                for (String columna : columnas) {

                    System.out.print(VERDE + " " + columna.toUpperCase() + "\t" + RESET);

                    if(columna.length() < 8) {
                        System.out.println(VERDE + "\t\t->\t" + RESET + rs.getString(columna));
                    } else if(columna.length() == 8 || columna.length() < 15) {
                        System.out.println(VERDE + "\t->\t" + RESET + rs.getString(columna));
                    } else {
                        System.out.println(VERDE + "->\t" + RESET + rs.getString(columna));
                    }

                    switch(columna) {
                        case "id":
                            soporte.setId(rs.getLong(columna));
                            break;

                        case "abreviatura":
                            soporte.setAbreviatura(rs.getInt(columna));
                            break;

                        case "tipo":
                            soporte.setTipo(rs.getString(columna));
                            break;

                        case "id_ubicacion":
                            soporte.setUbicacion(rs.getString(columna));
                            break;

                        case "fecha":
                            soporte.setFecha(LocalDateTime.parse(rs.getString(columna).replace(" ", "T")));
                            break;

                        case "id_empleado":
                            soporte.setIdEmpleado(rs.getInt(columna));
                            break;

                        default:
                            break;
                    }
                }
            System.out.println(VERDE + "-------------------------------------------------------------------" + RESET);   
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión a la base de datos: " + e.getMessage() + RESET);
        }
        return soporte;
    }

    /**
     * Método para mostrar el contenido de un soporte de la base de datos
     * @param abreviatura
     * @return
     */
    public static List<LlenaSoporte> mostrarContenido(int abreviatura) {

        String sql = "SELECT * FROM llena_soporte WHERE id_soporte = ?";

        LlenaSoporte acciones = null;
        long idSoporte = 10002026000000000L + abreviatura;
        List<LlenaSoporte> lista = new ArrayList<>();

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            String[] columnas = {"id", "id_empleado", "id_soporte", "id_producto", "cantidad", "fecha"};
            LocalDateTime fecha = LocalDateTime.now();
            
            System.out.println(VERDE + "\n-------------------------------------------------------------------" + RESET);

            ps.setLong(1, idSoporte);
            ResultSet rs = ps.executeQuery();
                    
            while (rs.next()) {
                
                acciones = new LlenaSoporte(4L, 0, 0, 0, 0, fecha);
                
                for (String columna : columnas) {

                    switch(columna) {
                        case "id":
                            acciones.setId(rs.getLong(columna));
                            break;

                        case "id_empleado":
                            acciones.setIdEmpleado(rs.getInt(columna));
                            break;

                        case "id_soporte":
                            acciones.setIdSoporte(rs.getLong(columna));
                            break;

                        case "id_producto":
                            acciones.setIdProducto(rs.getInt(columna));
                            break;

                        case "cantidad":
                            acciones.setCantidad(rs.getInt(columna));
                            break;

                        case "fecha":
                            acciones.setFecha(LocalDateTime.parse(rs.getString(columna).replace(" ", "T")));
                            break;

                        default:
                            break;
                    }
                }
                lista.add(acciones);

            System.out.println(VERDE + "-------------------------------------------------------------------" + RESET);   
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión a la base de datos: " + e.getMessage() + RESET);
        }
        return lista;
    }

    // LlenaSoporte ==========================================================================================================================================================

    /**
     * Método para insertar un nueva acción de llenado de soporte en la base de datos
     * @param accion
     */
    public static void insertarLlenaSoporte(LlenaSoporte accion) {

        String sql = "INSERT INTO llena_soporte (id_empleado, id_soporte, id_producto, cantidad, fecha) VALUES (?, ?, ?, ?, ?);";

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, accion.getIdEmpleado());
            ps.setLong(2, accion.getIdSoporte());
            ps.setInt(3, accion.getIdProducto());
            ps.setInt(4, accion.getCantidad());
            ps.setObject(5, accion.getFecha());

            int filas = ps.executeUpdate();

            if(filas > 0) {
                System.out.println(VERDE + "\n\t¡Añadido!");
            } else {
                System.out.println(ROJO + "\n\tError al crear la acción de llenado" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión a la base de datos: " + e.getMessage() + RESET);
        }
    }

    /**
     * Método para eliminar una acción de llenado de soporte de la base de datos
     * @param id
     */
    public static void borrarLlenaSoporte(long id) {

        String sql = "DELETE FROM llena_soporte WHERE id = ?";
            
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setLong(1, id);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println(VERDE + "\n\t¡Acción de llenado eliminada con éxito!" + RESET);
            } else {
                System.out.println(ROJO + "\n\tEl id no pertenece a ninguna acción de llenado" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión con la base de datos: " + e.getMessage() + RESET);
        }    
    }

    /**
     * Método para mostrar una accion de llenado de la base de datos
     * @param id
     */
    public static LlenaSoporte mostrarLlenaSoporte(long id) {

        String sql = "SELECT * FROM llena_soporte WHERE id = ?";

        LlenaSoporte accion = null;

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            String[] columnas = {"id", "id_empleado", "id_soporte", "id_producto", "cantidad", "fecha"};
            LocalDateTime fecha = LocalDateTime.now();
            
            System.out.println(VERDE + "\n---------------------------------------------------------------------------------------" + RESET);

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
                    
            while (rs.next()) {
                
                accion = new LlenaSoporte(4L, 0, 0, 0, 0, fecha);
                
                for (String columna : columnas) {

                    System.out.print(VERDE + " " + columna.toUpperCase() + "\t" + RESET);

                    if(columna.length() < 8) {
                        System.out.println(VERDE + "\t\t->\t" + RESET + rs.getString(columna));
                    } else if(columna.length() == 8 || columna.length() < 15) {
                        System.out.println(VERDE + "\t->\t" + RESET + rs.getString(columna));
                    } else {
                        System.out.println(VERDE + "->\t" + RESET + rs.getString(columna));
                    }

                    switch(columna) {
                        case "id":
                            accion.setId(rs.getLong(columna));
                            break;

                        case "id_empleado":
                            accion.setIdEmpleado(rs.getInt(columna));
                            break;

                        case "id_soporte":
                            accion.setIdSoporte(rs.getInt(columna));
                            break;

                        case "id_producto":
                            accion.setIdProducto(rs.getInt(columna));
                            break;

                        case "cantidad":
                            accion.setCantidad(rs.getInt(columna));
                            break;

                        case "fecha":
                            accion.setFecha(LocalDateTime.parse(rs.getString(columna).replace(" ", "T")));

                        default:
                            break;
                    }
                }
            System.out.println(VERDE + "---------------------------------------------------------------------------------------" + RESET);   
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión a la base de datos: " + e.getMessage() + RESET);
        }
        return accion;
    }

    // UbicaSoporte ==========================================================================================================================================================

    /**
     * Método para insertar un nueva acción de ubicar soporte en la base de datos, devuelve el nº ID de la acción
     * @param ubicaSoporte
     * @return 
     */
    public static long insertarUbicaSoporte(UbicaSoporte ubicaSoporte) {

        String sql = "INSERT INTO ubica_soporte (id_empleado, id_ubicacion, id_soporte, fecha, tipo) VALUES (?, ?, ?, ?, ?);";

        long idUbicaSoporte = 0;

        String sql2 = "SELECT id FROM ubica_soporte ORDER BY id DESC LIMIT 1";

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, ubicaSoporte.getIdEmpleado());
            ps.setInt(2, ubicaSoporte.getIdUbicacion());
            ps.setLong(3, ubicaSoporte.getIdSoporte());
            ps.setObject(4, ubicaSoporte.getFecha());
            ps.setString(5, ubicaSoporte.getTipo());

            int filas = ps.executeUpdate();

            if(filas > 0) {
                System.out.println(VERDE + "\n\t¡Acción de soporte realizada!");

                try (PreparedStatement pst = conexion.prepareStatement(sql2);
                    ResultSet rs = pst.executeQuery()) {

                    while (rs.next()) {

                        idUbicaSoporte = rs.getLong("id");
                    }
                }
            } else {
                System.out.println(ROJO + "\n\tError en la acción del soporte" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión a la base de datos: " + e.getMessage() + RESET);
        }

        return idUbicaSoporte;
    }

    /**
     * Método para mostrar las acciones de un soporte de la base de datos
     * @param abreviatura
     */
    public static List<UbicaSoporte> mostrarUbicaSoporte(long idSoporte) {

        String sql = "SELECT * FROM ubica_soporte WHERE id_soporte = ?";

        UbicaSoporte ubicaSoporte;
        List<UbicaSoporte> listado = new ArrayList<>();

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            String[] columnas = {"id", "id_empleado", "id_ubicacion", "id_soporte", "fecha", "tipo"};
            LocalDateTime fecha = LocalDateTime.now();

            ps.setLong(1, idSoporte);
            ResultSet rs = ps.executeQuery();
                    
            while (rs.next()) {
                
                ubicaSoporte = new UbicaSoporte(4L, 0, 0, 5L, fecha, "ubica");
                
                for (String columna : columnas) {

                    switch(columna) {
                        case "id":
                            ubicaSoporte.setId(rs.getLong(columna));
                            break;

                        case "id_empleado":
                            ubicaSoporte.setIdEmpleado(rs.getInt(columna));
                            break;

                        case "id_ubicacion":
                            ubicaSoporte.setIdUbicacion(rs.getInt(columna));
                            break;

                        case "id_soporte":
                            ubicaSoporte.setIdSoporte(rs.getLong(columna));
                            break;

                        case "fecha":
                            ubicaSoporte.setFecha(LocalDateTime.parse(rs.getString(columna).replace(" ", "T")));
                            break;

                        case "tipo":
                            ubicaSoporte.setTipo(rs.getString(columna));

                        default:
                            break;
                    }
                }
                listado.add(ubicaSoporte);   
            }
            System.out.println(VERDE + "\n\tListado de acciones completado!" + RESET);
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión a la base de datos: " + e.getMessage() + RESET);
        }
        return listado;
    }

    // mostrar las acciones de una ubicacion en un listado

    // mostrar las acciones de un empleado en un listado

    // UbicaProducto ==========================================================================================================================================================

    /**
     * Método para insertar un nueva acción de ubicar producto en la base de datos, devuelve el nº ID de la acción
     * @param ubicaProducto
     * @return 
     */
    public static long insertarUbicaProducto(UbicaProducto ubicaProducto) {

        String sql = "INSERT INTO ubica_producto (id_empleado, id_ubicacion, id_soporte, cantidad, fecha, tipo) VALUES (?, ?, ?, ?, ?, ?);";

        long idUbicaProducto = 0;

        String sql2 = "SELECT id FROM ubica_producto ORDER BY id DESC LIMIT 1";

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, ubicaProducto.getIdEmpleado());
            ps.setInt(2, ubicaProducto.getIdUbicacion());
            ps.setInt(3, ubicaProducto.getIdProducto());
            ps.setInt(4, ubicaProducto.getCantidad());
            ps.setObject(5, ubicaProducto.getFecha());
            ps.setString(6, ubicaProducto.getTipo());

            int filas = ps.executeUpdate();

            if(filas > 0) {
                System.out.println(VERDE + "\n\t¡Acción de producto realizada!");

                try (PreparedStatement pst = conexion.prepareStatement(sql2);
                    ResultSet rs = pst.executeQuery()) {

                    while (rs.next()) {

                        idUbicaProducto = rs.getLong("id");
                    }
                }
            } else {
                System.out.println(ROJO + "\n\tError en la acción del producto" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión a la base de datos: " + e.getMessage() + RESET);
        }

        return idUbicaProducto;
    }

    /**
     * Método para mostrar las acciones de un producto de la base de datos
     * @param idProducto
     */
    public static List<UbicaProducto> mostrarUbicaProducto(int idProducto) {

        String sql = "SELECT * FROM ubica_producto WHERE id_producto = ?";

        UbicaProducto ubicaProducto;
        List<UbicaProducto> listado = new ArrayList<>();

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            String[] columnas = {"id", "id_empleado", "id_ubicacion", "id_producto", "cantidad", "fecha", "tipo"};
            LocalDateTime fecha = LocalDateTime.now();

            ps.setInt(1, idProducto);
            ResultSet rs = ps.executeQuery();
                    
            while (rs.next()) {
                
                ubicaProducto = new UbicaProducto(4L, 0, 0, 0, 0, fecha, "ubica");
                
                for (String columna : columnas) {

                    switch(columna) {
                        case "id":
                            ubicaProducto.setId(rs.getLong(columna));
                            break;

                        case "id_empleado":
                            ubicaProducto.setIdEmpleado(rs.getInt(columna));
                            break;

                        case "id_ubicacion":
                            ubicaProducto.setIdUbicacion(rs.getInt(columna));
                            break;

                        case "id_producto":
                            ubicaProducto.setIdProducto(rs.getInt(columna));
                            break;

                        case "fecha":
                            ubicaProducto.setFecha(LocalDateTime.parse(rs.getString(columna).replace(" ", "T")));
                            break;

                        case "tipo":
                            ubicaProducto.setTipo(rs.getString(columna));

                        default:
                            break;
                    }
                }
                listado.add(ubicaProducto);   
            }
            System.out.println(VERDE + "\n\tListado de acciones completado!" + RESET);
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión a la base de datos: " + e.getMessage() + RESET);
        }
        return listado;
    }

    // mostrar las acciones de una ubicacion en un listado

    // mostrar las acciones de un empleado en un listado

    // Cliente ==========================================================================================================================================================

    /**
     * Método para insertar un nuevo cliente en la base de datos
     * @param cliente
     */
    public static void insertarCliente(Cliente cliente) {

        String sql = "INSERT INTO cliente (nif, nombre, apellidos, direccion, email, telefono) VALUES (?, ?, ?, ?, ?, ?);";

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, cliente.getNif());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getApellidos());
            ps.setString(4, cliente.getDireccion());
            ps.setString(5, cliente.getEmail());
            ps.setString(6, cliente.getTelefono());

            int filas = ps.executeUpdate();

            if(filas > 0) {
                System.out.println(VERDE + "\n\t¡Nuevo cliente creado!");
            } else {
                System.out.println(ROJO + "\n\tError al crear el cliente" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión a la base de datos: " + e.getMessage() + RESET);
        }
    }    

    /**
     * Método para borrar un cliente existente en la base de datos
     * @param nif
     */
    public static void borrarCliente(String nif) {

        String sql = "DELETE FROM cliente WHERE nif = ?";
            
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, nif);
            
            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println(VERDE + "\n\t¡Cliente eliminado con éxito!" + RESET);
            } else {
                System.out.println(ROJO + "\n\tEl nif no pertenece a ningún cliente" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión con la base de datos: " + e.getMessage() + RESET);
        }    
    }

    /**
     * Método para modificar un cliente de la base de datos
     * @param cliente
     * @param dato
     * @return
     */
    public static Cliente modificarCliente(Cliente cliente, String dato) {

        String sql = "UPDATE cliente SET " + dato + " = ? WHERE nif = ?";
            
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(2, cliente.getNif());

            System.out.print(VERDE + "\n Nuevo " + dato + ": " + RESET);
            Scanner sc = new Scanner(System.in);

            String info = sc.nextLine();
            
            switch(dato) {

                case "nif":
                    cliente.setNif(info);
                    ps.setString(1, info);
                    break;

                case "nombre":
                    cliente.setNombre(info);
                    ps.setString(1, info);
                    break;

                case "apellidos":
                    cliente.setApellidos(info);
                    ps.setString(1, info);
                    break;

                case "direccion":
                    cliente.setDireccion(info);
                    ps.setString(1, info);
                    break;

                case "email":
                    cliente.setEmail(info);
                    ps.setString(1, info);
                    break;

                case "telefono":
                    cliente.setTelefono(info);
                    ps.setString(1, info);
                    break;

                default:
                    break;
            }
            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println(VERDE + "\n\t¡Dato modificado con éxito!" + RESET);
            } else {
                System.out.println(ROJO + "\n\tError al modificar el dato" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión con la base de datos: " + e.getMessage() + RESET);
        } catch(Exception ex) {
            System.out.println(ROJO + ex.getMessage() + RESET);
        }
        return cliente;
    }

    /**
     * Método para mostrar cualquier cliente de la base de datos mediante su número ID
     * @param nif
     * @return
     */
    public static Cliente mostrarCliente(String nif) {

        String sql = "SELECT * FROM cliente WHERE nif = ?";

        Cliente cliente = null;

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            String[] columnas = {"nif", "nombre", "apellidos", "direccion", "email", "telefono"};
            
            System.out.println(VERDE + "\n-----------------------------------------------------------------------" + RESET);

            ps.setString(1, nif);
            ResultSet rs = ps.executeQuery();
                    
            while (rs.next()) {
                
                cliente = new Cliente("00000000X", "X", "X", "X","x@x.xx", "700000000");

                for (String columna : columnas) {

                    System.out.print(VERDE + " " + columna.toUpperCase() + "\t" + RESET);

                    if(columna.length() < 8) {
                        System.out.println(VERDE + "\t\t->\t" + RESET + rs.getString(columna));
                    } else if(columna.length() == 8 || columna.length() < 15) {
                        System.out.println(VERDE + "\t->\t" + RESET + rs.getString(columna));
                    } else {
                        System.out.println(VERDE + "->\t" + RESET + rs.getString(columna));
                    }

                    switch(columna) {

                        case "nif":
                            cliente.setNif(rs.getString(columna));
                            break;

                        case "nombre":
                            cliente.setNombre(rs.getString(columna));
                            break;

                        case "apellidos":
                            cliente.setApellidos(rs.getString(columna));
                            break;

                        case "direccion":
                            cliente.setDireccion(rs.getString(columna));
                            break;

                        case "email":
                            cliente.setEmail(rs.getString(columna));
                            break;

                        case "telefono":
                            cliente.setTelefono(rs.getString(columna));
                            break;

                        default:
                            break;
                    }
                }
            }
            System.out.println(VERDE + "-----------------------------------------------------------------------" + RESET);   
            
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión a la base de datos: " + e.getMessage() + RESET);
        }
        return cliente;
    }

    // Proveedor ==========================================================================================================================================================

    /**
     * Método para insertar un nuevo proveedor en la base de datos
     * @param proveedor
     */
    public static void insertarProveedor(Proveedor proveedor) {

        String sql = "INSERT INTO cliente (cif, nombre, telefono, email, direccion) VALUES (?, ?, ?, ?, ?);";

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, proveedor.getCif());
            ps.setString(2, proveedor.getNombre());
            ps.setString(3, proveedor.getTelefono());
            ps.setString(4, proveedor.getEmail());
            ps.setString(5, proveedor.getDireccion());

            int filas = ps.executeUpdate();

            if(filas > 0) {
                System.out.println(VERDE + "\n\t¡Nuevo proveedor creado!");
            } else {
                System.out.println(ROJO + "\n\tError al crear el proveedor" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión a la base de datos: " + e.getMessage() + RESET);
        }
    }    

    /**
     * Método para borrar un proveedor existente en la base de datos
     * @param cif
     */
    public static void borrarProveedor(String cif) {

        String sql = "DELETE FROM proveedor WHERE cif = ?";
            
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, cif);
            
            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println(VERDE + "\n\t¡Proveedor eliminado con éxito!" + RESET);
            } else {
                System.out.println(ROJO + "\n\tEl cif no pertenece a ningún proveedor" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión con la base de datos: " + e.getMessage() + RESET);
        }    
    }

    /**
     * Método para modificar un proveedor de la base de datos
     * @param proveedor
     * @param dato
     * @return
     */
    public static Proveedor modificarProveedor(Proveedor proveedor, String dato) {

        String sql = "UPDATE proveedor SET " + dato + " = ? WHERE cif = ?";
            
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(2, proveedor.getCif());

            System.out.print(VERDE + "\n Nuevo " + dato + ": " + RESET);
            Scanner sc = new Scanner(System.in);

            String info = sc.nextLine();
            
            switch(dato) {

                case "nif":
                    proveedor.setCif(info);
                    ps.setString(1, info);
                    break;

                case "nombre":
                    proveedor.setNombre(info);
                    ps.setString(1, info);
                    break;

                case "telefono":
                    proveedor.setTelefono(info);
                    ps.setString(1, info);
                    break;

                case "email":
                    proveedor.setEmail(info);
                    ps.setString(1, info);
                    break;

                case "direccion":
                    proveedor.setDireccion(info);
                    ps.setString(1, info);
                    break;

                default:
                    break;
            }
            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println(VERDE + "\n\t¡Dato modificado con éxito!" + RESET);
            } else {
                System.out.println(ROJO + "\n\tError al modificar el dato" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión con la base de datos: " + e.getMessage() + RESET);
        } catch(Exception ex) {
            System.out.println(ROJO + ex.getMessage() + RESET);
        }
        return proveedor;
    }

    /**
     * Método para mostrar cualquier proveedor de la base de datos mediante su número CIF
     * @param cif
     * @return
     */
    public static Proveedor mostrarProveedor(String cif) {

        String sql = "SELECT * FROM proveedor WHERE cif = ?";

        Proveedor proveedor = null;

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            String[] columnas = {"id", "cif", "nombre", "telefono", "email", "direccion"};
            
            System.out.println(VERDE + "\n-----------------------------------------------------------------------" + RESET);

            ps.setString(1, cif);
            ResultSet rs = ps.executeQuery();
                    
            while (rs.next()) {
                
                proveedor = new Proveedor(0, "X00000000", "X", "666999666","x@x.xx", "X");

                for (String columna : columnas) {

                    System.out.print(VERDE + " " + columna.toUpperCase() + "\t" + RESET);

                    if(columna.length() < 8) {
                        System.out.println(VERDE + "\t\t->\t" + RESET + rs.getString(columna));
                    } else if(columna.length() == 8 || columna.length() < 15) {
                        System.out.println(VERDE + "\t->\t" + RESET + rs.getString(columna));
                    } else {
                        System.out.println(VERDE + "->\t" + RESET + rs.getString(columna));
                    }

                    switch(columna) {

                        case "id":
                            proveedor.setId(rs.getInt(columna));
                            break;

                        case "nif":
                            proveedor.setCif(rs.getString(columna));
                            break;

                        case "nombre":
                            proveedor.setNombre(rs.getString(columna));
                            break;

                        case "telefono":
                            proveedor.setTelefono(rs.getString(columna));
                            break;

                        case "email":
                            proveedor.setEmail(rs.getString(columna));
                            break;

                        case "direccion":
                            proveedor.setDireccion(rs.getString(columna));
                            break;

                        default:
                            break;
                    }
                }
            }
            System.out.println(VERDE + "-----------------------------------------------------------------------" + RESET);   
            
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión a la base de datos: " + e.getMessage() + RESET);
        }
        return proveedor;
    }

    // Agencia ==========================================================================================================================================================

    /**
     * Método para insertar una nueva agencia en la base de datos
     * @param proveedor
     */
    public static void insertarAgencia(Agencia agencia) {

        String sql = "INSERT INTO agencia (cif, nombre, telefono, email, direccion) VALUES (?, ?, ?, ?, ?);";

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, agencia.getCif());
            ps.setString(2, agencia.getNombre());
            ps.setString(3, agencia.getTelefono());
            ps.setString(4, agencia.getEmail());
            ps.setString(5, agencia.getDireccion());

            int filas = ps.executeUpdate();

            if(filas > 0) {
                System.out.println(VERDE + "\n\t¡Nueva agencia creada!");
            } else {
                System.out.println(ROJO + "\n\tError al crear la agencia" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión a la base de datos: " + e.getMessage() + RESET);
        }
    }    

    /**
     * Método para borrar una agencia existente en la base de datos
     * @param cif
     */
    public static void borrarAgencia(String cif) {

        String sql = "DELETE FROM agencia WHERE cif = ?";
            
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, cif);
            
            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println(VERDE + "\n\t¡Agencia eliminada con éxito!" + RESET);
            } else {
                System.out.println(ROJO + "\n\tEl cif no pertenece a ninguna agencia" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión con la base de datos: " + e.getMessage() + RESET);
        }    
    }

    /**
     * Método para modificar una agencia de la base de datos
     * @param agencia
     * @param dato
     * @return
     */
    public static Agencia modificarAgencia(Agencia agencia, String dato) {

        String sql = "UPDATE agencia SET " + dato + " = ? WHERE cif = ?";
            
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(2, agencia.getCif());

            System.out.print(VERDE + "\n Nuevo " + dato + ": " + RESET);
            Scanner sc = new Scanner(System.in);

            String info = sc.nextLine();
            
            switch(dato) {

                case "nif":
                    agencia.setCif(info);
                    ps.setString(1, info);
                    break;

                case "nombre":
                    agencia.setNombre(info);
                    ps.setString(1, info);
                    break;

                case "telefono":
                    agencia.setTelefono(info);
                    ps.setString(1, info);
                    break;

                case "email":
                    agencia.setEmail(info);
                    ps.setString(1, info);
                    break;

                case "direccion":
                    agencia.setDireccion(info);
                    ps.setString(1, info);
                    break;

                default:
                    break;
            }
            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println(VERDE + "\n\t¡Dato modificado con éxito!" + RESET);
            } else {
                System.out.println(ROJO + "\n\tError al modificar el dato" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión con la base de datos: " + e.getMessage() + RESET);
        } catch(Exception ex) {
            System.out.println(ROJO + ex.getMessage() + RESET);
        }
        return agencia;
    }

    /**
     * Método para mostrar cualquier agencia de la base de datos mediante su número CIF
     * @param cif
     * @return
     */
    public static Agencia mostrarAgencia(String cif) {

        String sql = "SELECT * FROM agencia WHERE nif = ?";

        Agencia agencia = null;

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            String[] columnas = {"id", "cif", "nombre", "telefono", "email", "direccion"};
            
            System.out.println(VERDE + "\n-----------------------------------------------------------------------" + RESET);

            ps.setString(1, cif);
            ResultSet rs = ps.executeQuery();
                    
            while (rs.next()) {
                
                agencia = new Agencia(0, "X00000000", "X", "666777888","x@x.xx", "X");

                for (String columna : columnas) {

                    System.out.print(VERDE + " " + columna.toUpperCase() + "\t" + RESET);

                    if(columna.length() < 8) {
                        System.out.println(VERDE + "\t\t->\t" + RESET + rs.getString(columna));
                    } else if(columna.length() == 8 || columna.length() < 15) {
                        System.out.println(VERDE + "\t->\t" + RESET + rs.getString(columna));
                    } else {
                        System.out.println(VERDE + "->\t" + RESET + rs.getString(columna));
                    }

                    switch(columna) {

                        case "id":
                            agencia.setId(rs.getInt(columna));
                            break;

                        case "nif":
                            agencia.setCif(rs.getString(columna));
                            break;

                        case "nombre":
                            agencia.setNombre(rs.getString(columna));
                            break;

                        case "telefono":
                            agencia.setTelefono(rs.getString(columna));
                            break;

                        case "email":
                            agencia.setEmail(rs.getString(columna));
                            break;

                        case "direccion":
                            agencia.setDireccion(rs.getString(columna));
                            break;

                        default:
                            break;
                    }
                }
            }
            System.out.println(VERDE + "-----------------------------------------------------------------------" + RESET);   
            
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión a la base de datos: " + e.getMessage() + RESET);
        }
        return agencia;
    }

    // Pedido ==========================================================================================================================================================

    /**
     * Método para insertar un nuevo pedido en la base de datos
     * @param pedido
     */
    public static int insertarPedido(Pedido pedido) {

        String sql = "INSERT INTO pedido (tipo, fecha_creacion, fecha_estimada, id_empleado, estado) VALUES (?, ?, ?, ?, ?);";

        int idPedido = 0;

        String sql2 = "SELECT id FROM pedido ORDER BY id DESC LIMIT 1";

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, pedido.getTipo());
            ps.setObject(2, pedido.getFechaCreacion());
            ps.setObject(3, pedido.getFechaEstimada());
            ps.setInt(4, pedido.getIdEmpleado());
            ps.setString(5, pedido.getEstado());

            int filas = ps.executeUpdate();

            if(filas > 0) {
                System.out.println(VERDE + "\n\t¡Nuevo pedido creado!");

                try (PreparedStatement pst = conexion.prepareStatement(sql2);
                    ResultSet rs = pst.executeQuery()) {

                    while (rs.next()) {

                        idPedido = rs.getInt("id");
                    }
                }
            } else {
                System.out.println(ROJO + "\n\tError al crear el pedido" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión a la base de datos: " + e.getMessage() + RESET);
        }
        return idPedido;
    }    

    /**
     * Método para anular un pedido de la base de datos
     * @param pedido
     * @return
     */
    public static Pedido anularPedido(Pedido pedido) {

        String sql = "UPDATE pedido SET estado = ? WHERE id = ?";
            
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(2, pedido.getId());

            pedido.setEstado("anulado");
            ps.setString(1, "anulado");
                   
            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println(VERDE + "\n\t¡Pedido anulado!" + RESET);
            } else {
                System.out.println(ROJO + "\n\tError al anular el pedido" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión con la base de datos: " + e.getMessage() + RESET);
        } catch(Exception ex) {
            System.out.println(ROJO + ex.getMessage() + RESET);
        }
        return pedido;
    }

    /**
     * Método para modificar un pedido de la base de datos
     * @param pedido
     * @param dato
     * @return
     */
    public static Pedido modificarPedido(Pedido pedido, String dato) {

        String sql = "UPDATE pedido SET " + dato + " = ? WHERE id = ?";
            
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(2, pedido.getId());

            System.out.print(VERDE + "\n Nuevo " + dato + ": " + RESET);
            Scanner sc = new Scanner(System.in);

            String info = sc.nextLine();
            
            switch(dato) {

                case "id":
                    pedido.setId(Integer.parseInt(info));
                    ps.setString(1, info);
                    break;

                case "tipo":
                    pedido.setTipo(info);
                    ps.setString(1, info);
                    break;

                case "fecha_estimada":
                    pedido.setFechaEstimada(LocalDate.parse(info));
                    ps.setString(1, info);
                    break;

                case "estado":
                    pedido.setEstado(info);
                    ps.setString(1, info);
                    break;

                default:
                    break;
            }
            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println(VERDE + "\n\t¡Dato modificado con éxito!" + RESET);
            } else {
                System.out.println(ROJO + "\n\tError al modificar el dato" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión con la base de datos: " + e.getMessage() + RESET);
        } catch(Exception ex) {
            System.out.println(ROJO + ex.getMessage() + RESET);
        }
        return pedido;
    }

    /**
     * Método para mostrar cualquier pedido de la base de datos mediante su número ID
     * @param id
     * @return
     */
    public static Pedido mostrarPedido(int id) {

        String sql = "SELECT * FROM pedido WHERE id = ?";

        Pedido pedido = null;
        LocalDateTime fechaCreacion = LocalDateTime.now();
        LocalDate fechaEstimada = LocalDate.now();

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            String[] columnas = {"id", "tipo", "fecha_creacion", "fecha_estimada", "id_empleado", "estado"};
            
            System.out.println(VERDE + "\n-----------------------------------------------------------------------" + RESET);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
                    
            while (rs.next()) {
                
                pedido = new Pedido(0, "ls", fechaCreacion, fechaEstimada, 0, "pendiente");

                for (String columna : columnas) {

                    System.out.print(VERDE + " " + columna.toUpperCase() + "\t" + RESET);

                    if(columna.length() < 8) {
                        System.out.println(VERDE + "\t\t->\t" + RESET + rs.getString(columna));
                    } else if(columna.length() == 8 || columna.length() < 15) {
                        System.out.println(VERDE + "\t->\t" + RESET + rs.getString(columna));
                    } else {
                        System.out.println(VERDE + "->\t" + RESET + rs.getString(columna));
                    }

                    switch(columna) {

                        case "id":
                            pedido.setId(rs.getInt(columna));
                            break;

                        case "tipo":
                            pedido.setTipo(rs.getString(columna));
                            break;

                        case "fecha_creacion":
                            pedido.setFechaCreacion(LocalDateTime.parse(rs.getString(columna).replace(" ", "T")));
                            break;

                        case "fecha_estimada":
                            pedido.setFechaEstimada(LocalDate.parse(rs.getString(columna).substring(0, 10)));
                            break;

                        case "id_empleado":
                            pedido.setIdEmpleado(rs.getInt(columna));
                            break;

                        case "estado":
                            pedido.setEstado(rs.getString(columna));
                            break;

                        default:
                            break;
                    }
                }
            }
            System.out.println(VERDE + "-----------------------------------------------------------------------" + RESET);   
            
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión a la base de datos: " + e.getMessage() + RESET);
        }
        return pedido;
    }

    // LineaPedido ==========================================================================================================================================================

    /**
     * Método para insertar las líneas de un pedido de la base de datos
     * @param lineaPedido
     * @return
     */
    public static long insertarLineaPedido(LineaPedido lineaPedido) {

        String sql = "INSERT INTO linea_pedido (id_pedido, id_producto, cantidad, fecha) VALUES (?, ?, ?, ?);";

        long idLineaPedido = 1L;

        String sql2 = "SELECT id FROM linea_pedido ORDER BY id DESC LIMIT 1";

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, lineaPedido.getIdPedido());
            ps.setInt(2, lineaPedido.getIdProducto());
            ps.setInt(3, lineaPedido.getCantidad());
            ps.setObject(4, lineaPedido.getFecha());

            int filas = ps.executeUpdate();

            if(filas > 0) {
                System.out.println(VERDE + "\n\t¡Nuevo línea de pedido creada!");

                try (PreparedStatement pst = conexion.prepareStatement(sql2);
                    ResultSet rs = pst.executeQuery()) {

                    while (rs.next()) {

                        idLineaPedido = rs.getLong("id");
                    }
                }
            } else {
                System.out.println(ROJO + "\n\tError al crear la línea de pedido" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión a la base de datos: " + e.getMessage() + RESET);
        }
        return idLineaPedido;
    }
    
    /**
     * Método para eliminar una línea de un pedido existente en la base de datos
     * @param idPedido
     * @param idProducto
     */
    public static void borrarLineaPedido(int idPedido, int idProducto) {

        String sql = "DELETE FROM linea_pedido WHERE id_pedido = ? AND id_producto = ?";
            
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, idPedido);
            ps.setInt(2, idProducto);
            
            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println(VERDE + "\n\t¡Línea de pedido eliminada!" + RESET);
            } else {
                System.out.println(ROJO + "\n\tLa información no pertenece a ninguna línea de pedido" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión con la base de datos: " + e.getMessage() + RESET);
        }    
    }

    /**
     * Método para modificar la cantidad de una línea de pedido de la base de datos
     * @param pedido
     * @param dato
     * @return
     */
    public static void modificarLineaPedido(int idPedido, int idProducto, int cantidad) {

        String sql = "UPDATE linea_pedido SET cantidad = ? WHERE idPedido = ? AND idProducto = ?";
            
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(2, idPedido);
            ps.setInt(3, idProducto);

            System.out.print(VERDE + "\n Nueva cantidad: " + RESET);
            Scanner sc = new Scanner(System.in);

            String info = sc.nextLine();
            
            ps.setString(1, info);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println(VERDE + "\n\t¡Cantidad modificada con éxito!" + RESET);
            } else {
                System.out.println(ROJO + "\n\tError al modificar la cantidad" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión con la base de datos: " + e.getMessage() + RESET);
        } catch(Exception ex) {
            System.out.println(ROJO + ex.getMessage() + RESET);
        }
    }

    /**
     * Método para mostrar todas las líneas de un pedido de la base de datos
     * @param id
     * @return
     */
    public static List<LineaPedido> mostrarLineasPedido(int idPedido) {

        String sql = "SELECT * FROM linea_pedido WHERE id_pedido = ?";

        LineaPedido lineaPedido = null;
        LocalDateTime fecha = LocalDateTime.now();
        List<LineaPedido> contenidoPedido = new ArrayList<>();

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            String[] columnas = {"id", "id_pedido", "id_producto", "cantidad", "fecha"};

            ps.setInt(1, idPedido);
            ResultSet rs = ps.executeQuery();
                    
            while (rs.next()) {
                
                lineaPedido = new LineaPedido(1L, 0, 0, 0, fecha);

                for (String columna : columnas) {

                    if(columna.equals("id") || columna.equals("id_pedido")) {
                        ;
                    } else {
                        System.out.print(VERDE + " " + columna.toUpperCase() + ":\t" + RESET + rs.getString(columna) + "\t");
                    }
                    switch(columna) {

                        case "id":
                            lineaPedido.setId(rs.getLong(columna));
                            break;

                        case "id_pedido":
                            lineaPedido.setIdPedido(rs.getInt(columna));
                            break;

                        case "id_producto":
                            lineaPedido.setIdProducto(rs.getInt(columna));
                            break;

                        case "cantidad":
                            lineaPedido.setCantidad(rs.getInt(columna));
                            break;

                        case "fecha":
                            lineaPedido.setFecha(LocalDateTime.parse(rs.getString(columna).replace(" ", "T")));
                            break;

                        default:
                            break;
                    }
                }
                contenidoPedido.add(lineaPedido);
            }   
            
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión a la base de datos: " + e.getMessage() + RESET);
        }
        return contenidoPedido;
    }
}
