import java.time.LocalDateTime;
import java.util.Objects;

public class LineaPedido {
    
    private long id;
    private int idPedido;
    private int idProducto;
    private int cantidad;
    private LocalDateTime fecha;

    // Constructores

    // Constructor 1 - Para insertar una linea de pedido en la base de datos omitiendo el ID y la fecha
    public LineaPedido(int idPedido, int idProducto, int cantidad) {

        this.idPedido = idPedido;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.fecha = LocalDateTime.now();
    }

    // Constructor 2 - Para leer una linea de pedido de la base de datos
    public LineaPedido(long id, int idPedido, int idProducto, int cantidad, LocalDateTime fecha) {

        this.id = id;
        this.idPedido = idPedido;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    // Getters y Setters

    public long getId() { return id; }
    public int getIdPedido() { return idPedido; }
    public int getIdProducto() { return idProducto; }
    public int getCantidad() { return cantidad; }
    public LocalDateTime getFecha() { return fecha; }

    public void setId(long id) {

        this.id = id;
    }

    public void setIdPedido(int idPedido) {

        this.idPedido = idPedido;
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
        
        return "ID: " + id + ", Pedido: " + idPedido + ", Producto: " + idProducto + ", Cantidad: " + cantidad + ", Fecha: " + fecha;
    }

    @Override 
    public boolean equals(Object obj) {

        if(this==obj) return true;
        if(obj==null) return false;
        if(getClass()!=obj.getClass()) return false;
        LineaPedido otro = (LineaPedido)obj;
        return Objects.equals(id, otro.id);
    }
}
