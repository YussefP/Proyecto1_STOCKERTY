import java.time.LocalDateTime;
import java.util.Objects;

public class UbicaSoporte {
    
    private long id;
    private int idEmpleado;
    private int idUbicacion;
    private long idSoporte;
    private LocalDateTime fecha;
    private String tipo;

    // Constructores

    // Constructor 1 - Para crear una acción de ubicar soporte omitiendo el ID
    public UbicaSoporte(int idEmpleado, int idUbicacion, long idSoporte, LocalDateTime fecha, String tipo) {

        if(tipo==null || tipo.isEmpty() || tipo.isBlank()) {
            throw new IllegalArgumentException("Tipo de acción obligatoria");
        } else if(!tipo.toLowerCase().equals("ubica") && !tipo.toLowerCase().equals("elimina") && !tipo.toLowerCase().equals("modifica")) {
            throw new IllegalArgumentException("Tipo de acción incorrecta, introduce uno de los siguientes valores: ubica, elimina o modifica");
        }

        this.idEmpleado = idEmpleado;
        this.idUbicacion = idUbicacion;
        this.idSoporte = idSoporte;
        this.fecha = fecha;
        this.tipo = tipo;
    }

    // Constructor 2 - Para leer una acción de ubicar soporte
    public UbicaSoporte(long id, int idEmpleado, int idUbicacion, long idSoporte, LocalDateTime fecha, String tipo) {

        if(tipo==null || tipo.isEmpty() || tipo.isBlank()) {
            throw new IllegalArgumentException("Tipo de acción obligatoria");
        } else if(!tipo.toLowerCase().equals("ubica") && !tipo.toLowerCase().equals("elimina") && !tipo.toLowerCase().equals("modifica")) {
            throw new IllegalArgumentException("Tipo de acción incorrecta, introduce uno de los siguientes valores: ubica, elimina o modifica");
        }

        this.id = id;
        this.idEmpleado = idEmpleado;
        this.idUbicacion = idUbicacion;
        this.idSoporte = idSoporte;
        this.fecha = fecha;
        this.tipo = tipo;
    }

    // Getters y Setters

    public long getId() { return id; }
    public int getIdEmpleado() { return idEmpleado; }
    public int getIdUbicacion() { return idUbicacion; }
    public long getIdSoporte() { return idSoporte; }
    public LocalDateTime getFecha() { return fecha; }
    public String getTipo() { return tipo; }

    public void setId(long id) {

        this.id = id;
    }

    public void setIdEmpleado(int idEmpleado) {

        this.idEmpleado = idEmpleado;
    }

    public void setIdUbicacion(int idUbicacion) {

        this.idUbicacion = idUbicacion;
    }

    public void setIdSoporte(long idSoporte) {

        this.idSoporte = idSoporte;
    }

    public void setFecha(LocalDateTime fecha) {

        this.fecha = fecha;
    }

    public void setTipo(String tipo) {

        if(tipo==null || tipo.isEmpty() || tipo.isBlank()) {
            throw new IllegalArgumentException("Tipo de acción obligatoria");
        } else if(!tipo.toLowerCase().equals("ubica") && !tipo.toLowerCase().equals("elimina") && !tipo.toLowerCase().equals("modifica")) {
            throw new IllegalArgumentException("Tipo de acción incorrecta, introduce uno de los siguientes valores: ubica, elimina o modifica");
        }
        this.tipo = tipo;
    }

    // Sobreescritura de métodos
    @Override 
    public String toString() {

        return "ID: " + id + ", ID Empleado: " + idEmpleado + ", ID Ubicación" + idUbicacion + ", ID Soporte: " + idSoporte + ", Fecha: " + fecha + ", Tipo: " + tipo;
    }

    @Override 
    public boolean equals(Object obj) {

        if(this==obj) return true;
        if(obj==null) return false;
        if(getClass()!=obj.getClass()) return false;
        UbicaSoporte otro = (UbicaSoporte)obj;
        return Objects.equals(id, otro.id);
    }
}
