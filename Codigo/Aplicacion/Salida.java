import java.time.LocalDateTime;
import java.util.Objects;

public class Salida {
    
    private int id;
    private int idPedido;
    private int idAgencia;
    private int numAlbaran;
    private int numExpedicion;
    private int bultos;
    private int palets;
    private String observaciones;
    private LocalDateTime fecha;

    // Constructores

    // Constructor 1 - Para insertar una salida en la base de datos omitiendo el id y la fecha
    public Salida(int idPedido, int idAgencia, int numAlbaran, int numExpedicion, int bultos, int palets, String observaciones) {

        this.idPedido = idPedido;
        this.idAgencia = idAgencia;
        this.numAlbaran = numAlbaran;
        this.numExpedicion = numExpedicion;
        this.bultos = bultos;
        this.palets = palets;
        this.observaciones = observaciones;
        this.fecha = LocalDateTime.now();
    }

    // Constructor 2 - Para leer un salida de la base de datos
    public Salida(int id, int idPedido, int idAgencia, int numAlbaran, int numExpedicion, int bultos, int palets, String observaciones, LocalDateTime fecha) {

        this.id = id;
        this.idPedido = idPedido;
        this.idAgencia = idAgencia;
        this.numAlbaran = numAlbaran;
        this.numExpedicion = numExpedicion;
        this.bultos = bultos;
        this.palets = palets;
        this.observaciones = observaciones;
        this.fecha = fecha;
    }

    // Getters y Setters

    public int getId() { return id; }
    public int getIdPedido() { return idPedido; }
    public int getIdAgencia() { return idAgencia; }
    public int getNumAlbaran() { return numAlbaran; }
    public int getNumExpedicion() { return numExpedicion; }
    public int getBultos() { return bultos; }
    public int getPalets() { return palets; }
    public String getObservaciones() { return observaciones; }
    public LocalDateTime getFecha() { return fecha; }

    public void setId(int id) {

        this.id = id;
    }

    public void setIdPedido(int idPedido) {

        this.idPedido = idPedido;
    }

    public void setIdAgencia(int idAgencia) {

        this.idAgencia = idAgencia;
    }

    public void setNumAlbaran(int numAlbaran) {

        this.numAlbaran = numAlbaran;
    }

    public void setNumExpedicion(int numExpedicion) {

        this.numExpedicion = numExpedicion;
    }

    public void setBultos(int bultos) {

        this.bultos = bultos;
    }

    public void setPalets(int palets) {

        this.palets = palets;
    }

    public void setObservaciones(String observaciones) {

        this.observaciones = observaciones;
    }

    public void setFecha(LocalDateTime fecha) {

        this.fecha = fecha;
    }

    // Sobreescritura de métodos
    
    @Override 
    public String toString() {

        return "ID: " + id + ", Pedido: " + idPedido + ", Agencia: " + idAgencia + ", Albarán: " + numAlbaran + ", Expedición: " + numExpedicion + ", Nº Bultos: " + bultos + ", Nº Palets: " + palets + ", Observaciones: " + observaciones + ", Fecha: " + fecha;
    }

    @Override 
    public boolean equals(Object obj) {

        if(this==obj) return true;
        if(obj==null) return false;
        if(getClass()!=obj.getClass()) return false;
        Salida otro = (Salida)obj;
        return Objects.equals(id, otro.id);
    }
}
