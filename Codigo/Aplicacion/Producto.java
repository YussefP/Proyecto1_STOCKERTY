
import java.util.Objects;
import java.math.BigDecimal;
// import java.util.List;

public class Producto {
    
    private int id;
    private String ean;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private int stock;
    private int stockMinimo;
    // private List<String> ubicaciones;

    // Constructores

    // Constructor 1 - Para crear un producto omitiendo el número ID
    public Producto(String ean, String nombre, String descripcion, String precio, int stock, int stockMinimo) {

        if(ean==null || ean.isEmpty() || ean.isBlank()) {
            throw new IllegalArgumentException("EAN obligatorio");
        } else if(!ean.matches("^[0-9]{13}$")) {
            throw new IllegalArgumentException("El número EAN debe contener 13 dígitos");
        }

        if(nombre==null || nombre.isEmpty() || nombre.isBlank()) {
            throw new IllegalArgumentException("Nombre obligatorio");
        }

        if(descripcion==null || descripcion.isEmpty() || descripcion.isBlank()) {
            throw new IllegalArgumentException("Descripción obligatoria");
        }

        if(precio==null || precio.isEmpty() || precio.isBlank()) {
            throw new IllegalArgumentException("Precio obligatorio");
        } else if(!precio.matches("^\\d{1,10}\\.\\d{2}$")) {
            throw new IllegalArgumentException("El precio debe contener entre 1 y 10 dígitos, seguido de 1 punto y 2 decimales (ej.: 15.99, 1295.50, 0.25)");
        }

        this.ean = ean;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = new BigDecimal(precio);
        this.stock = stock;
        this.stockMinimo = stockMinimo;      
    }

    // Constructor 2 - Para leer un producto
    public Producto(int id, String ean, String nombre, String descripcion, String precio, int stock, int stockMinimo) {

        if(ean==null || ean.isEmpty() || ean.isBlank()) {
            throw new IllegalArgumentException("EAN obligatorio");
        } else if(!ean.matches("^[0-9]{13}$")) {
            throw new IllegalArgumentException("El número EAN debe contener 13 dígitos");
        }

        if(nombre==null || nombre.isEmpty() || nombre.isBlank()) {
            throw new IllegalArgumentException("Nombre obligatorio");
        }

        if(descripcion==null || descripcion.isEmpty() || descripcion.isBlank()) {
            throw new IllegalArgumentException("Descripción obligatoria");
        }

        if(precio==null || precio.isEmpty() || precio.isBlank()) {
            throw new IllegalArgumentException("Precio obligatorio");
        } else if(!precio.matches("^\\d{1,10}\\.\\d{2}$")) {
            throw new IllegalArgumentException("El precio debe contener entre 1 y 10 dígitos, seguido de 1 punto y 2 decimales (ej.: 15.99, 1295.50, 0.25)");
        }

        this.id = id;
        this.ean = ean;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = new BigDecimal(precio);
        this.stock = stock;
        this.stockMinimo = stockMinimo;      
    }

    // Getters y Setters

    public int getId() { return id; }
    public String getEan() { return ean; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public BigDecimal getPrecio() { return precio; }
    public int getStock() { return stock; }
    public int getStockMinimo() { return stockMinimo; }

    public void setId(int id) {

        this.id = id;
    }
    
    public void setEan(String ean) {

        if(ean==null || ean.isEmpty() || ean.isBlank()) {
            throw new IllegalArgumentException("EAN obligatorio");
        } else if(!ean.matches("^[0-9]{13}$")) {
            throw new IllegalArgumentException("El número EAN debe contener 13 dígitos");
        }
        this.ean = ean;
    }

    public void setNombre(String nombre) {

        if(nombre==null || nombre.isEmpty() || nombre.isBlank()) {
            throw new IllegalArgumentException("Nombre obligatorio");
        }
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {

        if(descripcion==null || descripcion.isEmpty() || descripcion.isBlank()) {
            throw new IllegalArgumentException("Descripción obligatoria");
        }
        this.descripcion = descripcion;
    }

    public void setPrecio(String precio) {

        if(precio==null || precio.isEmpty() || precio.isBlank()) {
            throw new IllegalArgumentException("Precio obligatorio");
        } else if(!precio.matches("^\\d{1,10}\\.\\d{2}$")) {
            throw new IllegalArgumentException("El precio debe contener entre 1 y 10 dígitos, seguido de 1 punto y 2 decimales (ej.: 15.99, 1295.50, 0.25)");
        }
        this.precio = new BigDecimal(precio);
    }

    public void setStock(int stock) {

        this.stock = stock;
    }

    public void setStockMinimo(int stockMinimo) {

        this.stockMinimo = stockMinimo;
    }

    // Sobreescritura de métodos

    @Override 
    public String toString() {

        return "ID: " + id + ", EAN: " + ean + ", Nombre: " + nombre + ", Descripción: " + descripcion + ", Precio: " + precio + ", Stock: " + stock + ", Stock mínimo: " + stockMinimo;
    }

    @Override 
    public boolean equals(Object obj) {

        if(this==obj) return true;
        if(obj==null) return false;
        if(getClass()!=obj.getClass()) return false;
        Producto otro = (Producto)obj;
        return Objects.equals(ean, otro.ean);
    }
}
