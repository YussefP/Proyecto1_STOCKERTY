import java.util.Objects;

public class Direccion {
    
    private int id;
    private String posicion;
    private int idAltura;

    // Constructor 

    public Direccion(int id, String posicion, int idAltura) {

        this.id = id;
        this.posicion = posicion;
        this.idAltura = idAltura;
    }

    // Getters y Setters

    public int getId() { return id; }
    public String getPosicion() { return posicion; }
    public int getIdAltura() { return idAltura; }

    public void setId(int id) {

        this.id = id;
    }

    public void setPosicion(String posicion) {

        this.posicion = posicion;
    }

    public void setIdAltura(int idAltura) {

        this.idAltura = idAltura;
    }

    // Sobreescritura de métodos

    @Override 
    public String toString() {

        return "Id: " + id + ", Posición: " + posicion + ", ID Altura: " + idAltura;
    }

    @Override 
    public boolean equals(Object obj) {

        if(this==obj) return true;
        if(obj==null) return false;
        if(getClass()!=obj.getClass()) return false;
        Direccion otro = (Direccion)obj;
        return Objects.equals(id, otro.id);
    }
}
