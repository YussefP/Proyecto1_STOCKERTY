import java.time.LocalDateTime;
import java.util.Objects;

public class LlenaSoporte {
    
    private long id;
    private int idEmpleado;
    private long idSoporte;
    private int idProducto;
    private int cantidad;
    private LocalDateTime fecha;

    // Constructores

    // Constructor 1 - Para crear una acción de llenar soporte omitiendo el ID
    public LlenaSoporte(int idEmpleado, long idSoporte, int idProducto, int cantidad, LocalDateTime fecha) {

        this.idEmpleado = idEmpleado;
        this.idSoporte = idSoporte;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    // Constructor 2 - Para leer una acción de llenar soporte
    public LlenaSoporte(long id, int idEmpleado, long idSoporte, int idProducto, int cantidad, LocalDateTime fecha) {

        this.id = id;
        this.idEmpleado = idEmpleado;
        this.idSoporte = idSoporte;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    // Getters y Setters

    public long getId() { return id; }
    public int getIdEmpleado() { return idEmpleado; }
    public long getIdSoporte() { return idSoporte; }
    public int getIdProducto() { return idProducto; }
    public int getCantidad() { return cantidad; }
    public LocalDateTime getFecha() { return fecha; }

    public void setId(long id) {

        this.id = id;
    }

    public void setIdEmpleado(int idEmpleado) {

        this.idEmpleado = idEmpleado;
    }

    public void setIdSoporte(long idSoporte) {

        this.idSoporte = idSoporte;
    }

    public void setIdProducto(int idProducto) {

        this.idProducto = idProducto;
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

        return "ID: " + id + ", ID Empleado: " + idEmpleado + ", ID Soporte: " + idSoporte + ", ID Producto: " + idProducto + ", Cantidad: " + cantidad + ", Fecha: " + fecha;
    }

    @Override 
    public boolean equals(Object obj) {

        if(this==obj) return true;
        if(obj==null) return false;
        if(getClass()!=obj.getClass()) return false;
        LlenaSoporte otro = (LlenaSoporte)obj;
        return Objects.equals(id, otro.id);
    }
}
