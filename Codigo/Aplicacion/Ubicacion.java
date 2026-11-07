import java.util.List;
import java.util.Objects;

public class Ubicacion {
    
    private int id;
    private String nombre;
    private int idRack;
    private int idAltura;
    private int idDireccion;
    private List<Soporte> listadoSoportes;
    private List<Producto> listadoProductos;

    // Constructores

    // Constructor 1 - Para crear una ubicación omitiendo el ID
    public Ubicacion(String nombre, int idRack, int idAltura, int idDireccion) {

        this.nombre = nombre;
        this.idRack = idRack;
        this.idAltura = idAltura;
        this.idDireccion = idDireccion;
    }

    // Constructor 2 - Para leer una ubicación
    public Ubicacion(int id, String nombre, int idRack, int idAltura, int idDireccion) {

        this.id = id;
        this.nombre = nombre;
        this.idRack = idRack;
        this.idAltura = idAltura;
        this.idDireccion = idDireccion;
    }

    // Getters y Setters

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public int getIdRack() { return idRack; }
    public int getIdAltura() { return idAltura; }
    public int getIdDireccion() { return idDireccion; }
    public List<Soporte> getListadoSoportes() { return listadoSoportes; }
    public Soporte getSoporte(int i) { return listadoSoportes.get(i); }
    public List<Producto> getListadoProductos() { return listadoProductos; }
    public Producto getProducto(int i) { return listadoProductos.get(i); }

    public void setId(int id) {

        this.id = id;
    }

    public void setNombre(String nombre) {

        this.nombre = nombre;
    }

    public void setIdRack(int idRack) {

        this.idRack = idRack;
    }

    public void setIdAltura(int idAltura) {

        this.idAltura = idAltura;
    }

    public void setIdDireccion(int idDireccion) {

        this.idDireccion = idDireccion;
    }

    public void setListadoSoportes(String accion, Soporte soporte) {

        if(accion.equals("insertar")) {
            listadoSoportes.add(soporte);
        } else if(accion.equals("eliminar")) {
            listadoSoportes.remove(soporte);
        } else {
            throw new IllegalArgumentException("Acción incorrecta, insertar o eliminar soporte"); 
        }
    }

    public void setListadoProductos(String accion, Producto producto) {

        if(accion.equals("insertar")) {
            listadoProductos.add(producto);
        } else if(accion.equals("eliminar")) {
            listadoProductos.remove(producto);
        } else {
            throw new IllegalArgumentException("Acción incorrecta, insertar o eliminar producto"); 
        }
    }

    // Sobreescritura de métodos

    @Override 
    public String toString() {

        return "Id: " + id + ", Nombre: " + nombre + ", ID Rack: " + idRack + ", ID Altura: " + idAltura + ", ID Dirección: " + idDireccion;
    }

    @Override 
    public boolean equals(Object obj) {

        if(this==obj) return true;
        if(obj==null) return false;
        if(getClass()!=obj.getClass()) return false;
        Ubicacion otro = (Ubicacion)obj;
        return Objects.equals(id, otro.id);
    }
}
