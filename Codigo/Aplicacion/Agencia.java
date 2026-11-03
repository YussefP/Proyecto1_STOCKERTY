import java.util.Objects;

public class Agencia {

    private int id;
    private String cif;
    private String nombre;
    private String telefono;
    private String email;
    private String direccion;

    // Constructores

    // Constructor 1 - Para insertar una agencia en la base de datos omitiendo el id
    public Agencia(String cif, String nombre, String telefono, String email, String direccion) {

        if(cif==null || cif.isEmpty() || cif.isBlank()) {
            throw new IllegalArgumentException("NIF obligatorio");
        } else if(!cif.matches("^[A-Za-z]\\d{8}$")) {
            throw new IllegalArgumentException("CIF incorrecto, introduce 1 letra + un número de 8 dígitos");
        }

        if(nombre==null || nombre.isEmpty() || nombre.isBlank()) {
            throw new IllegalArgumentException("Nombre obligatorio");
        }

        if(telefono==null || telefono.isEmpty() || telefono.isBlank()) {
            throw new IllegalArgumentException("Teléfono obligatorio");
        } else if(!telefono.matches("^[679]\\d{8}$")) {
            throw new IllegalArgumentException("Teléfono incorrecto, introduce un número de 9 dígitos que empiece por 6, 7 o 9");
        }

        if(email==null || email.isEmpty() || email.isBlank()) {
            throw new IllegalArgumentException("Email obligatorio");
        } else if(!email.matches("^[A-Za-z0-9._-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
            throw new IllegalArgumentException("Email incorrecto, introduce un formato válido");
        }

        if(direccion==null || direccion.isEmpty() || direccion.isBlank()) {
            throw new IllegalArgumentException("Dirección obligatoria");
        }

        this.cif = cif;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
    }

    // Constructor 2 - Para leer una agencia de la base de datos
    public Agencia(int id, String cif, String nombre, String telefono, String email, String direccion) {

        if(cif==null || cif.isEmpty() || cif.isBlank()) {
            throw new IllegalArgumentException("NIF obligatorio");
        } else if(!cif.matches("^[A-Za-z]\\d{8}$")) {
            throw new IllegalArgumentException("CIF incorrecto, introduce 1 letra + un número de 8 dígitos");
        }

        if(nombre==null || nombre.isEmpty() || nombre.isBlank()) {
            throw new IllegalArgumentException("Nombre obligatorio");
        }

        if(telefono==null || telefono.isEmpty() || telefono.isBlank()) {
            throw new IllegalArgumentException("Teléfono obligatorio");
        } else if(!telefono.matches("^[679]\\d{8}$")) {
            throw new IllegalArgumentException("Teléfono incorrecto, introduce un número de 9 dígitos que empiece por 6, 7 o 9");
        }

        if(email==null || email.isEmpty() || email.isBlank()) {
            throw new IllegalArgumentException("Email obligatorio");
        } else if(!email.matches("^[A-Za-z0-9._-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
            throw new IllegalArgumentException("Email incorrecto, introduce un formato válido");
        }

        if(direccion==null || direccion.isEmpty() || direccion.isBlank()) {
            throw new IllegalArgumentException("Dirección obligatoria");
        }

        this.id = id;
        this.cif = cif;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
    }

    // Getters y Setters

    public int getId() { return id; }
    public String getCif() { return cif; }
    public String getNombre() { return nombre; }
    public String getTelefono() { return telefono; }
    public String getEmail() { return email; }
    public String getDireccion() { return direccion; }

    public void setId(int id) {

        this.id = id;
    }

    public void setCif(String cif) {

        if(cif==null || cif.isEmpty() || cif.isBlank()) {
            throw new IllegalArgumentException("NIF obligatorio");
        } else if(!cif.matches("^[A-Za-z]\\d{8}$")) {
            throw new IllegalArgumentException("CIF incorrecto, introduce 1 letra + un número de 8 dígitos");
        }
        this.cif = cif;
    }

    public void setNombre(String nombre) {

        if(nombre==null || nombre.isEmpty() || nombre.isBlank()) {
            throw new IllegalArgumentException("Nombre obligatorio");
        }
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {

        if(telefono==null || telefono.isEmpty() || telefono.isBlank()) {
            throw new IllegalArgumentException("Teléfono obligatorio");
        } else if(!telefono.matches("^[679]\\d{8}$")) {
            throw new IllegalArgumentException("Teléfono incorrecto, introduce un número de 9 dígitos que empiece por 6, 7 o 9");
        }
        this.telefono = telefono;
    }

    public void setEmail(String email) {

        if(email==null || email.isEmpty() || email.isBlank()) {
            throw new IllegalArgumentException("Email obligatorio");
        } else if(!email.matches("^[A-Za-z0-9._-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
            throw new IllegalArgumentException("Email incorrecto, introduce un formato válido");
        }
        this.email = email;
    }

    public void setDireccion(String direccion) {

        if(direccion==null || direccion.isEmpty() || direccion.isBlank()) {
            throw new IllegalArgumentException("Dirección obligatoria");
        }
        this.direccion = direccion;
    }

    // Sobreescritura de métodos

    @Override 
    public String toString() {

        return "ID: " + id + ", CIF: " + cif + ", Nombre: " + nombre + ", Teléfono: " + telefono + ", Email: " + email + ", Dirección: " + direccion;
    }

    @Override 
    public boolean equals(Object obj) {

        if(this==obj) return true;
        if(obj==null) return false;
        if(getClass()!=obj.getClass()) return false;
        Agencia otro = (Agencia)obj;
        return Objects.equals(cif, otro.cif);
    }
}
