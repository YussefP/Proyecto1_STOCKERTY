import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Pedido {

    private int id;
    private String tipo;
    private List<Producto> lineas;
    private LocalDateTime fechaCreacion;
    private LocalDate fechaEstimada;
    private int idEmpleado;
    private String estado;

    // Constructores

    // Constructor 1 - Para insertar un pedido en la base de datos omitiendo el ID, las fechas y el estado
    public Pedido(String tipo, List<Producto> lineas, int idEmpleado) {

        if(tipo==null || tipo.isEmpty() || tipo.isBlank()) {
            throw new IllegalArgumentException("Tipo de pedido obligatorio");
        } else if(!tipo.toLowerCase().equals("ls") && !tipo.toLowerCase().equals("pc")) {
            throw new IllegalArgumentException("Tipo de pedido incorrecto, introduce uno de los siguientes valores: LS o PC");
        }
        
        if(lineas==null || lineas.isEmpty()) {
            throw new IllegalArgumentException("No hay lineas de producto para crear el pedido");
        }

        this.tipo = tipo;
        this.lineas = lineas;
        this.fechaCreacion = LocalDateTime.now();
        this.fechaEstimada = fechaCreacion.toLocalDate().plusDays(7);
        this.idEmpleado = idEmpleado;
        this.estado = "Pendiente";
    }

    // Constructor 2 - Para leer un pedido en la base de datos
    public Pedido(int id, String tipo, List<Producto> lineas, LocalDateTime fechaCreacion, LocalDate fechaEstimada, int idEmpleado, String estado) {

        if(tipo==null || tipo.isEmpty() || tipo.isBlank()) {
            throw new IllegalArgumentException("Tipo de pedido obligatorio");
        } else if(!tipo.toLowerCase().equals("ls") && !tipo.toLowerCase().equals("pc")) {
            throw new IllegalArgumentException("Tipo de pedido incorrecto, introduce uno de los siguientes valores: LS o PC");
        }
        
        if(lineas==null || lineas.isEmpty()) {
            throw new IllegalArgumentException("No hay lineas de producto para crear el pedido");
        }

        this.id = id;
        this.tipo = tipo;
        this.lineas = lineas;
        this.fechaCreacion = fechaCreacion;
        this.fechaEstimada = fechaEstimada;
        this.idEmpleado = idEmpleado;
        this.estado = estado;
    }

    // Getters y Setters

    public int getId() { return id; }
    public String getTipo() { return tipo; }
    public List<Producto> getLineas() { return lineas; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public LocalDate getFechaEstimada() { return fechaEstimada; }
    public int getIdEmpleado() { return idEmpleado; }
    public String getEstado() { return estado; }

    public void setId(int id) {

        this.id = id;
    }

    public void setTipo(String tipo) {

        if(tipo==null || tipo.isEmpty() || tipo.isBlank()) {
            throw new IllegalArgumentException("Tipo de pedido obligatorio");
        } else if(!tipo.toLowerCase().equals("ls") && !tipo.toLowerCase().equals("pc")) {
            throw new IllegalArgumentException("Tipo de pedido incorrecto, introduce uno de los siguientes valores: LS o PC");
        }
        this.tipo = tipo;
    }

    public void setLineas(List<Producto> lineas) {

        if(lineas==null || lineas.isEmpty()) {
            throw new IllegalArgumentException("No hay lineas de producto para crear el pedido");
        }
        this.lineas = lineas;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {

        this.fechaCreacion = fechaCreacion;
    }

    public void setFechaEstimada(LocalDate fechaEstimada) {

        this.fechaEstimada = fechaEstimada;
    }

    public void setIdEmpleado(int idEmpleado) {

        this.idEmpleado = idEmpleado;
    }

    public void setEstado(String estado) {

        if(estado==null || estado.isEmpty() || estado.isBlank()) {
            throw new IllegalArgumentException("Estado de pedido obligatorio");
        } else if(!estado.toLowerCase().equals("Pendiente") && !estado.toLowerCase().equals("En tránsito") && !estado.toLowerCase().equals("Recepcionado") && !estado.toLowerCase().equals("Ubicado") && !estado.toLowerCase().equals("Saldado")) {
            throw new IllegalArgumentException("Estado de pedido incorrecto, introduce uno de los siguientes valores: LS o PC");
        }
        this.estado = estado;
    }
    
    // Sobreescritura de métodos

    @Override 
    public String toString() {

        return "ID: " + id + ", Tipo: " + tipo + ", Lineas: " + lineas + ", Fecha de creación: " + fechaCreacion + ", Fecha estimada: " + fechaEstimada + ", Creador del pedido: " + idEmpleado + ", Estado: " + estado;
    }

    @Override 
    public boolean equals(Object obj) {

        if(this==obj) return true;
        if(obj==null) return false;
        if(getClass()!=obj.getClass()) return false;
        Pedido otro = (Pedido)obj;
        return Objects.equals(id, otro.id);
    }
}
