import java.util.Objects;

public class Tienda {
    
    private int id;
    private String cif;
    private String nombre;
    private String direccion;

    // Constructor 

    public Tienda(int id, String cif, String nombre, String direccion) {

        if(cif==null || cif.isEmpty() || cif.isBlank()) {
            throw new IllegalArgumentException("NIF obligatorio");
        } else if(!cif.matches("^[A-Za-z]\\d{8}$")) {
            throw new IllegalArgumentException("CIF incorrecto, introduce 1 letra + un número de 8 dígitos");
        }
        this.id = id;
        this.cif = cif;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    // Getters y Setters

    public int getId() { return id; }
    public String getCif() { return cif; }
    public String getNombre() { return nombre; }
    public String getDireccion() { return direccion; }

    public void setId(int id) {

        this.id = id;
    }

    public void setCif(String cif) {

        if(cif==null || cif.isEmpty() || cif.isBlank()) {
            throw new IllegalArgumentException("NIF obligatorio");
        } else if(!cif.matches("^[A-Za-z]\\d{8}$")) {
            throw new IllegalArgumentException("CIF incorrecto, introduce 1 letra + un número de 8 dígitos");
        }
        this.cif = cif;
    }

    public void setNombre(String nombre) {

        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {

        this.direccion = direccion;
    }

    // Sobreescritura de métodos

    @Override 
    public String toString() {

        return "Id: " + id + ", CIF: " + cif + ", Nombre: " + nombre + ", Dirección: " + direccion;
    }

    @Override 
    public boolean equals(Object obj) {

        if(this==obj) return true;
        if(obj==null) return false;
        if(getClass()!=obj.getClass()) return false;
        Tienda otro = (Tienda)obj;
        return Objects.equals(id, otro.id);
    }
}
