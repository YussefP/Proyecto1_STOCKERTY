import java.time.LocalDateTime;
import java.util.Objects;

public class ContenidoUbicacion {
    private long id;
    private String nombreUbicacion;
    private String tipo;
    private int idContenido;
    private int cantidad;
    private LocalDateTime fecha;

    // Constructores

    // Constructor 1 - Para insertar un contenido de ubicación en la base de datos omitiendo ID y fecha
    public ContenidoUbicacion(String nombreUbicacion, String tipo, int idContenido, int cantidad) {

        if(tipo==null || tipo.isEmpty() || tipo.isBlank()) {
            throw new IllegalArgumentException("Tipo de contenido obligatorio");
        } else if(!tipo.toLowerCase().equals("producto") && !tipo.toLowerCase().equals("soporte") && !tipo.toLowerCase().equals("pedido")) {
            throw new IllegalArgumentException("Tipo de contenido incorrecto, introduce uno de los siguientes valores: Producto, Soporte o Pedido");
        }

        this.nombreUbicacion = nombreUbicacion;
        this.tipo = tipo;
        this.idContenido = idContenido;
        this.cantidad = cantidad;
        this.fecha = LocalDateTime.now();
    }

    // Constructor 2 - Para leer un contenido de ubicación en la base de datos
    public ContenidoUbicacion(long id, String nombreUbicacion, String tipo, int idContenido, int cantidad, LocalDateTime fecha) {

        if(tipo==null || tipo.isEmpty() || tipo.isBlank()) {
            throw new IllegalArgumentException("Tipo de contenido obligatorio");
        } else if(!tipo.toLowerCase().equals("producto") && !tipo.toLowerCase().equals("soporte") && !tipo.toLowerCase().equals("pedido")) {
            throw new IllegalArgumentException("Tipo de contenido incorrecto, introduce uno de los siguientes valores: Producto, Soporte o Pedido");
        }

        this.id = id;
        this.nombreUbicacion = nombreUbicacion;
        this.tipo = tipo;
        this.idContenido = idContenido;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    // getters y Setters

    public long getId() { return id; }
    public String getNombreUbicacion() { return nombreUbicacion; }
    public String getTipo() { return tipo; }
    public int getIdContenido() { return idContenido; }
    public int getCantidad() { return cantidad; }
    public LocalDateTime getFecha() { return fecha; }

    public void setId(long id) {

        this.id = id;
    }

    public void setNombreUbicacion(String nombreUbicacion) {

        this.nombreUbicacion = nombreUbicacion;
    }

    public void setTipo(String tipo) {

        if(tipo==null || tipo.isEmpty() || tipo.isBlank()) {
            throw new IllegalArgumentException("Tipo de contenido obligatorio");
        } else if(!tipo.toLowerCase().equals("producto") && !tipo.toLowerCase().equals("soporte") && !tipo.toLowerCase().equals("pedido")) {
            throw new IllegalArgumentException("Tipo de contenido incorrecto, introduce uno de los siguientes valores: Producto, Soporte o Pedido");
        }
        this.tipo = tipo;
    }

    public void setIdContenido(int idContenido) {

        this.idContenido = idContenido;
    }

    public void setCantidad(int cantidad) {

        this.cantidad = cantidad;
    }

    public void setFecha(LocalDateTime fecha) {

        this.fecha = fecha;
    }

    // Sobreescritura de métodos

    @Override 
    public String toString() {
        return "ID: " + id + ", Ubicación: " + nombreUbicacion + ", Tipo: " + tipo + ", Contenido: " + idContenido + ", Cantidad: " + cantidad + ", Fecha: " + fecha;
    }

    @Override 
    public boolean equals(Object obj) {

        if(this==obj) return true;
        if(obj==null) return false;
        if(getClass()!=obj.getClass()) return false;
        ContenidoUbicacion otro = (ContenidoUbicacion)obj;
        return Objects.equals(id, otro.id);
    }
}
