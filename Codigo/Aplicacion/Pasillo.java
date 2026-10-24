import java.util.Objects;

public class Pasillo {
    
    private int id;
    private String nombre;
    private int idZona;

    // Constructor 

    public Pasillo(int id, String nombre, int idZona) {

        this.id = id;
        this.nombre = nombre;
        this.idZona = idZona;
    }

    // Getters y Setters

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public int getIdZona() { return idZona; }

    public void setId(int id) {

        this.id = id;
    }

    public void setNombre(String nombre) {

        this.nombre = nombre;
    }

    public void setIdZona(int idZona) {

        this.idZona = idZona;
    }

    // Sobreescritura de métodos

    @Override 
    public String toString() {

        return "Id: " + id + ", Nombre: " + nombre + ", ID Zona: " + idZona;
    }

    @Override 
    public boolean equals(Object obj) {

        if(this==obj) return true;
        if(obj==null) return false;
        if(getClass()!=obj.getClass()) return false;
        Pasillo otro = (Pasillo)obj;
        return Objects.equals(id, otro.id);
    }
}
