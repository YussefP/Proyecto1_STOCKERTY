
import java.util.List;
import java.util.Objects;
import java.time.LocalDateTime;

public class Soporte {
    
    private long id;
    private int abreviatura;
    private String tipo;
    private int idUbicacion;
    private LocalDateTime fecha;
    private int idEmpleado;
    private List<Producto> contenido;

    // Constructores

    // Constructor 1 - Para crear un soporte omitiendo Id, abreviatura y contenido
    public Soporte(String tipo, LocalDateTime fecha, int idEmpleado) {

        if(tipo==null || tipo.isEmpty() || tipo.isBlank()) {
            throw new IllegalArgumentException("Tipo de soporte obligatorio");
        } else if(!tipo.toLowerCase().equals("bulto") && !tipo.toLowerCase().equals("palet")) {
            throw new IllegalArgumentException("Tipo de soporte incorrecto, introduce uno de los siguientes valores: bulto o palet");
        }
        this.tipo = tipo;
        this.fecha = fecha;
        this.idEmpleado = idEmpleado;
    }

    // Constructor 1 - Para crear un soporte omitiendo Id y abreviatura
    public Soporte(String tipo, LocalDateTime fecha, int idEmpleado, List<Producto> contenido) {

        if(tipo==null || tipo.isEmpty() || tipo.isBlank()) {
            throw new IllegalArgumentException("Tipo de soporte obligatorio");
        } else if(!tipo.toLowerCase().equals("bulto") && !tipo.toLowerCase().equals("palet")) {
            throw new IllegalArgumentException("Tipo de soporte incorrecto, introduce uno de los siguientes valores: bulto o palet");
        }
        this.tipo = tipo;
        this.fecha = fecha;
        this.idEmpleado = idEmpleado;
        this.contenido = contenido;
    }

    // constructor 2 - Para leer un soporte sin ubicación
    public Soporte(long id, int abreviatura, String tipo, LocalDateTime fecha, int idEmpleado, List<Producto> contenido) {

        if(tipo==null || tipo.isEmpty() || tipo.isBlank()) {
            throw new IllegalArgumentException("Tipo de soporte obligatorio");
        } else if(!tipo.toLowerCase().equals("bulto") && !tipo.toLowerCase().equals("palet")) {
            throw new IllegalArgumentException("Tipo de soporte incorrecto, introduce uno de los siguientes valores: bulto o palet");
        }
        this.id = id;
        this.abreviatura = abreviatura;
        this.tipo = tipo;
        this.fecha = fecha;
        this.idEmpleado = idEmpleado;
        this.contenido = contenido;
    }

    // constructor 3 - Para leer un soporte con ubicación
    public Soporte(long id, int abreviatura, String tipo, int idUbicacion, LocalDateTime fecha, int idEmpleado, List<Producto> contenido) {

        if(tipo==null || tipo.isEmpty() || tipo.isBlank()) {
            throw new IllegalArgumentException("Tipo de soporte obligatorio");
        } else if(!tipo.toLowerCase().equals("bulto") && !tipo.toLowerCase().equals("palet")) {
            throw new IllegalArgumentException("Tipo de soporte incorrecto, introduce uno de los siguientes valores: bulto o palet");
        }
        this.id = id;
        this.abreviatura = abreviatura;
        this.tipo = tipo;
        this.idUbicacion = idUbicacion;
        this.fecha = fecha;
        this.idEmpleado = idEmpleado;
        this.contenido = contenido;
    }

    // Getters y Setters

    public long getId() { return id; }
    public int getAbreviatura() { return abreviatura; }
    public String getTipo() { return tipo; }
    public int getIdUbicacion() { return idUbicacion; }
    public LocalDateTime getFecha() { return fecha; }
    public int getIdEmpleado() { return idEmpleado; }
    public List<Producto> getContenido() { return contenido; }

    public void setId(long id) {
        this.id = id;
    }

    public void setAbreviatura(int abreviatura) {

        this.abreviatura = abreviatura;
    }

    public void setTipo(String tipo) {

        if(tipo==null || tipo.isEmpty() || tipo.isBlank()) {
            throw new IllegalArgumentException("Tipo de soporte obligatorio");
        } else if(!tipo.toLowerCase().equals("bulto") && !tipo.toLowerCase().equals("palet")) {
            throw new IllegalArgumentException("Tipo de soporte incorrecto, introduce uno de los siguientes valores: bulto o palet");
        }
        this.tipo = tipo;
    }

    public void setIdUbicacion(int idUbicacion) {

        this.idUbicacion = idUbicacion;
    }

    public void setFecha(LocalDateTime fecha) {

        this.fecha = fecha;
    }

    public void setIdEmpleado(int idEmpleado) {

        this.idEmpleado = idEmpleado;
    }

    public void setContenido(List<Producto> contenido) {

        this.contenido = contenido;
    }

    // Sobreescritura de métodos

    @Override 
    public String toString() {

        return "ID: " + id + ", Abreviatura: " + abreviatura + ", Tipo: " + tipo + ", ID Ubicación: " + idUbicacion + ", Fecha: " + fecha + ", ID Empleado: " + idEmpleado + ", Contenido: " + contenido; 
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
