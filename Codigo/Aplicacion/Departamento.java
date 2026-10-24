import java.util.Objects;

public class Departamento {
    
    private int id;
    private String nombre;
    private String responsable;
    private int idTienda;

    // Constructor

    public Departamento(int id, String nombre, String responsable, int idTienda) {

        this.id = id;
        this.nombre = nombre;
        this.responsable = responsable;
        this.idTienda = idTienda;

    }

    // Getters y Setters

    public int getId() { return id;}
    public String getNombre() { return nombre; }
    public String getResponsable() { return responsable; }
    public int getIdTienda() { return idTienda; }

    public void setId(int id) {

        this.id = id;
    }

    public void setNombre(String nombre) {

        this.nombre = nombre;
    }

    public void setResponsable(String responsable) {

        this.responsable = responsable;
    }

    public void setIdTienda(int idTienda) {

        this.idTienda = idTienda;
    }

    // Sobreescritura de métodos

    @Override 
    public String toString() {

        return "Id: " + id + ", Nombre: " + nombre + ", Responsable: " + responsable + ", ID Tienda: " + idTienda;
    }

    @Override 
    public boolean equals(Object obj) {

        if(this==obj) return true;
        if(obj==null) return false;
        if(getClass()!=obj.getClass()) return false;
        Departamento otro = (Departamento)obj;
        return Objects.equals(id, otro.id);
    }
}
