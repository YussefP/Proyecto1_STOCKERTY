
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
    public static Ubicacion mostrarUbicacion(int id) {

        String sql = "SELECT * FROM ubicacion WHERE id = ?";

        Ubicacion ubicacion = null;

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            String[] columnas = {"id", "nombre", "id_rack", "id_altura", "id_direccion"};
            
            System.out.println(VERDE + "\n-------------------------------------------------------------------" + RESET);

            ps.setInt(1, id);
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
                        soporte.setIdUbicacion(Integer.parseInt(info));
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
                
                soporte = new Soporte(4L, 30567, "bulto", 0, fecha, 0, lista);
                
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
                            soporte.setIdUbicacion(rs.getInt(columna));
                            break;

                        case "fecha":
                            soporte.setFecha(LocalDateTime.parse(rs.getString(columna)));
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
                            accion.setFecha(LocalDateTime.parse(rs.getString(columna)));

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

    /*
    public void verEstructura(int idAlmacen) {

        String sql = "SELECT * FROM almacen WHERE id_almacen = ?";
    }
    */

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
     * @param id
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
     * Método para modificar un empleado de la base de datos
     * @param empleado
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
     * Método para mostrar cualquier empleado de la base de datos mediante su número ID
     * @param id
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
}
