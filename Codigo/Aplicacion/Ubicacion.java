import java.util.Objects;

public class Ubicacion {
    
    private int id;
    private String nombre;
    private int idRack;
    private int idAltura;
    private int idDireccion;

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
