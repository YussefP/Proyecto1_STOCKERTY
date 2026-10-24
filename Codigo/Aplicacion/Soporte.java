
import java.util.List;
import java.util.Objects;

public class Soporte {
    
    private long id;
    private int abreviatura;
    private String tipo;
    private List<Producto> contenido;

    // Constructores

    // Constructor 1 - Para crear un soporte omitiendo Id y abreviatura
    public Soporte(String tipo, List<Producto> contenido) {

        if(tipo==null || tipo.isEmpty() || tipo.isBlank()) {
            throw new IllegalArgumentException("Tipo de soporte obligatorio");
        } else if(!tipo.toLowerCase().equals("bulto") && !tipo.toLowerCase().equals("palet")) {
            throw new IllegalArgumentException("Tipo de soporte incorrecto, introduce uno de los siguientes valores: bulto o palet");
        }
        this.tipo = tipo;
        this.contenido = contenido;
        this.abreviatura = (int)(id % 100000);
    }

    // constructor 2 - Para leer un soporte
    public Soporte(long id, int abreviatura, String tipo, List<Producto> contenido) {

        if(tipo==null || tipo.isEmpty() || tipo.isBlank()) {
            throw new IllegalArgumentException("Tipo de soporte obligatorio");
        } else if(!tipo.toLowerCase().equals("bulto") && !tipo.toLowerCase().equals("palet")) {
            throw new IllegalArgumentException("Tipo de soporte incorrecto, introduce uno de los siguientes valores: bulto o palet");
        }
        this.id = id;
        this.abreviatura = abreviatura;
        this.tipo = tipo;
        this.contenido = contenido;
    }

    // Getters y Setters

    public long getId() { return id; }
    public int getAbreviatura() { return abreviatura; }
    public String getTipo() { return tipo; }
    public List<Producto> getContenido() { return contenido; }

    public void setId(long id) {
        this.id = id;
    }

    public void setAbreviatura(int abreviatura) {

        this.abreviatura = (int)(id % 100000);
    }

    public void setTipo(String tipo) {

        if(tipo==null || tipo.isEmpty() || tipo.isBlank()) {
            throw new IllegalArgumentException("Tipo de soporte obligatorio");
        } else if(!tipo.toLowerCase().equals("bulto") && !tipo.toLowerCase().equals("palet")) {
            throw new IllegalArgumentException("Tipo de soporte incorrecto, introduce uno de los siguientes valores: bulto o palet");
        }
        this.tipo = tipo;
    }

    public void setContenido(List<Producto> contenido) {

        this.contenido = contenido;
    }

    // Sobreescritura de métodos

    @Override 
    public String toString() {

        return "ID: " + id + ", Abreviatura: " + abreviatura + ", Tipo: " + tipo + ", Contenido: " + contenido; 
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
