import java.time.LocalDateTime;
import java.util.List;

public class Pedido {

    private int id;
    private String tipo;
    private List<Producto> lineas;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaEstimada;
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
        this.tipo = tipo;
    }

    
}
