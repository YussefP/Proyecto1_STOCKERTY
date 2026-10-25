import java.util.Objects;

public class Rack {
    
    private int id;
    private String nombre;
    private int idPasillo;

    // Constructores

    // Constructor 1 - Para crear un rack omitiendo el ID
    public Rack(String nombre, int idPasillo) {

        this.nombre = nombre;
        this.idPasillo = idPasillo;
    }

    // Constructor 2 - Para leer un rack
    public Rack(int id, String nombre, int idPasillo) {

        this.id = id;
        this.nombre = nombre;
        this.idPasillo = idPasillo;
    }

    // Getters y Setters

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public int getIdPasillo() { return idPasillo; }

    public void setId(int id) {

        this.id = id;
    }

    public void setNombre(String nombre) {

        this.nombre = nombre;
    }

    public void setIdPasillo(int idPasillo) {

        this.idPasillo = idPasillo;
    }

    // Sobreescritura de métodos

    @Override 
    public String toString() {

        return "Id: " + id + ", Nombre: " + nombre + ", ID Pasillo: " + idPasillo;
    }

    @Override 
    public boolean equals(Object obj) {

        if(this==obj) return true;
        if(obj==null) return false;
        if(getClass()!=obj.getClass()) return false;
        Rack otro = (Rack)obj;
        return Objects.equals(id, otro.id);
    }
}
