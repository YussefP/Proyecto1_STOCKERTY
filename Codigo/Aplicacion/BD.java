
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
     */
    public static void insertarEmpleado(Empleado empleado) {

        String sql = "INSERT INTO empleado (contrasenya, nif, nombre, apellidos, puesto, direccion, email, telefono, id_departamento, tipo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

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
            } else {
                System.out.println(ROJO + "\n\tError al crear el empleado" + RESET);
            }
        } catch(SQLException e) {
            System.out.println(ROJO + "\n\tError de conexión a la base de datos: " + e.getMessage() + RESET);
        }
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
     * @param id
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

    // Soporte ==========================================================================================================================================================
}
