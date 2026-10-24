import java.util.Objects;

public class Zona {
    
    private int id;
    private String nombre;
    private int idAlmacen;

    // Constructor 

    public Zona(int id, String nombre, int idAlmacen) {

        this.id = id;
        this.nombre = nombre;
        this.idAlmacen = idAlmacen;
    }

    // Getters y Setters

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public int getIdAlmacen() { return idAlmacen; }

    public void setId(int id) {

        this.id = id;
    }

    public void setNombre(String nombre) {

        this.nombre = nombre;
    }

    public void setIdAlmacen(int idAlmacen) {

        this.idAlmacen = idAlmacen;
    }

    // Sobreescritura de métodos

    @Override 
    public String toString() {

        return "Id: " + id + ", Nombre: " + nombre + ", ID Almacén: " + idAlmacen;
    }

    @Override 
    public boolean equals(Object obj) {

        if(this==obj) return true;
        if(obj==null) return false;
        if(getClass()!=obj.getClass()) return false;
        Zona otro = (Zona)obj;
        return Objects.equals(id, otro.id);
    }
}
