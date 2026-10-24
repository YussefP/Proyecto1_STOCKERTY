import java.util.Objects;

public class Altura {
    
    private int id;
    private int nivel;
    private int idRack;

    // Constructor 

    public Altura(int id, int nivel, int idRack) {

        this.id = id;
        this.nivel = nivel;
        this.idRack = idRack;
    }

    // Getters y Setters

    public int getId() { return id; }
    public int getNivel() { return nivel; }
    public int getIdRack() { return idRack; }

    public void setId(int id) {

        this.id = id;
    }

    public void setNombre(int nivel) {

        this.nivel = nivel;
    }

    public void setIdRack(int idRack) {

        this.idRack = idRack;
    }

    // Sobreescritura de métodos

    @Override 
    public String toString() {

        return "Id: " + id + ", Nivel: " + nivel + ", ID Rack: " + idRack;
    }

    @Override 
    public boolean equals(Object obj) {

        if(this==obj) return true;
        if(obj==null) return false;
        if(getClass()!=obj.getClass()) return false;
        Altura otro = (Altura)obj;
        return Objects.equals(id, otro.id);
    }
}
