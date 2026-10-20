
import java.util.List;
import java.util.Objects;

public class Soporte {
    
    private long id;
    private int abreviatura;
    private List<Producto> contenido;

    // Constructores

    // Constructor 1 - Para crear un soporte omitiendo Id y abreviatura
    public Soporte(List<Producto> contenido) {

        this.contenido = contenido;
        this.abreviatura = (int)(id % 100000);
    }

    // constructor 2 - Para leer un soporte
    public Soporte(long id, int abreviatura, List<Producto> contenido) {

        this.id = id;
        this.abreviatura = abreviatura;
        this.contenido = contenido;
    }

    // Getters y Setters

    public long getId() { return id; }
    public int getAbreviatura() { return abreviatura; }
    public List<Producto> getContenido() { return contenido; }

    public void setId(long id) {
        this.id = id;
    }

    public void setAbreviatura(int abreviatura) {

        this.abreviatura = (int)(id % 100000);
    }

    public void setContenido(List<Producto> contenido) {

        this.contenido = contenido;
    }

    // Sobreescritura de métodos

    @Override 
    public String toString() {

        return "ID: " + id + ", Abreviatura: " + abreviatura + ", Contenido: " + contenido; 
    }

    @Override 
    public boolean equals(Object obj) {

        if(this==obj) return true;
        if(obj==null) return false;
        if(getClass()!=obj.getClass()) return false;
        Soporte otro = (Soporte)obj;
        return Objects.equals(id, otro.id);
    }
}
