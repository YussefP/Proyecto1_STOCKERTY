import java.util.Objects;

public class Almacen {
    
    private int id;
    private String nombre;
    private String direccion;
    private int idTienda;

    // Constructor 

    public Almacen(int id, String nombre, String direccion, int idTienda) {

        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.idTienda = idTienda;
    }

    // Getters y Setters

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDireccion() { return direccion; }
    public int getIdTienda() { return idTienda; }

    public void setId(int id) {

        this.id = id;
    }

    public void setNombre(String nombre) {

        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {

        this.direccion = direccion;
    }

    public void setIdTienda(int idTienda) {

        this.idTienda = idTienda;
    }

    // Sobreescritura de métodos

    @Override 
    public String toString() {

        return "Id: " + id + ", Nombre: " + nombre + ", Dirección: " + direccion + ", ID Tienda: " + idTienda;
    }

    @Override 
    public boolean equals(Object obj) {

        if(this==obj) return true;
        if(obj==null) return false;
        if(getClass()!=obj.getClass()) return false;
        Almacen otro = (Almacen)obj;
        return Objects.equals(id, otro.id);
    }
}
