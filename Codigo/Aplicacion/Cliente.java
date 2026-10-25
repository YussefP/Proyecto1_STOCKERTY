import java.util.Objects;

public class Cliente {
    
    private String nif;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String email;
    private String telefono;

    // Constructor

    public Cliente(String nif, String nombre, String apellidos, String direccion, String email, String telefono) {

        if(nif==null || nif.isEmpty() || nif.isBlank()) {
            throw new IllegalArgumentException("NIF obligatorio");
        } else if(!nif.matches("^\\d{8}[A-Za-z]$")) {
            throw new IllegalArgumentException("NIF incorrecto, introduce un número de 8 dígitos + 1 letra");
        }

        if(nombre==null || nombre.isEmpty() || nombre.isBlank()) {
            throw new IllegalArgumentException("Nombre obligatorio");
        }

        if(apellidos==null || apellidos.isEmpty() || apellidos.isBlank()) {
            throw new IllegalArgumentException("Apellidos obligatorios");
        }

        if(direccion==null || direccion.isEmpty() || direccion.isBlank()) {
            throw new IllegalArgumentException("Direccion obligatoria");
        }

        if(email==null || email.isEmpty() || email.isBlank()) {
            throw new IllegalArgumentException("Email obligatorio");
        } else if(!email.matches("^[A-Za-z0-9._-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
            throw new IllegalArgumentException("Email incorrecto, introduce un formato válido");
        }
            
        if(telefono==null || telefono.isEmpty() || telefono.isBlank()) {
            throw new IllegalArgumentException("Teléfono obligatorio");
        } else if(!telefono.matches("^[679]\\d{8}$")) {
            throw new IllegalArgumentException("Teléfono incorrecto, introduce un número de 9 dígitos que empiece por 6, 7 o 9");
        }

        this.nif = nif;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
    }

    // Getters y Setters

    public String getNif() { return nif; }
    public String getNombre() { return nombre; }
    public String getApellidos() { return apellidos; }
    public String getDireccion() { return direccion; }
    public String getEmail() { return email; }
    public String getTelefono() { return telefono; }

    public void setNif(String nif) {

        if(nif==null || nif.isEmpty() || nif.isBlank()) {
            throw new IllegalArgumentException("NIF obligatorio");
        } else if(!nif.matches("^\\d{8}[A-Za-z]$")) {
            throw new IllegalArgumentException("NIF incorrecto, introduce un número de 8 dígitos + 1 letra");
        }
        this.nif = nif;
    }

    public void setNombre(String nombre) {

        if(nombre==null || nombre.isEmpty() || nombre.isBlank()) {
            throw new IllegalArgumentException("Nombre obligatorio");
        }
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {

        if(apellidos==null || apellidos.isEmpty() || apellidos.isBlank()) {
            throw new IllegalArgumentException("Nombre obligatorio");
        }
        this.apellidos = apellidos;
    }

    public void setDireccion(String direccion) {

        if(direccion==null || direccion.isEmpty() || direccion.isBlank()) {
            throw new IllegalArgumentException("Direccion obligatoria");
        }

        this.direccion = direccion;
    }

    public void setEmail(String email) {

        if(email==null || email.isEmpty() || email.isBlank()) {
            throw new IllegalArgumentException("Email obligatorio");
        } else if(!email.matches("^[A-Za-z0-9._-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
            throw new IllegalArgumentException("Email incorrecto, introduce un formato válido");
        }
        this.email = email;
    }

    public void setTelefono(String telefono) {

        if(telefono==null || telefono.isEmpty() || telefono.isBlank()) {
            throw new IllegalArgumentException("Teléfono obligatorio");
        } else if(!telefono.matches("^[679]\\d{8}$")) {
            throw new IllegalArgumentException("Teléfono incorrecto, introduce un número de 9 dígitos que empiece por 6, 7 o 9");
        }
        this.telefono = telefono;
    }

    // Sobreescritura de métodos

    @Override 
    public String toString() {

        return "NIF: " + nif + ", Nombre: " + nombre + ",Apellidos: " + apellidos + ", Email: " + email + ", Teléfono: " + telefono;
    }

    @Override 
    public boolean equals(Object obj) {

        if(this==obj) return true;
        if(obj==null) return false;
        if(getClass()!=obj.getClass()) return false;
        Cliente otro = (Cliente)obj;
        return Objects.equals(nif, otro.nif);
    }
}
