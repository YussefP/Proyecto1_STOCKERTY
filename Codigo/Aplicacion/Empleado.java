
import java.util.Objects;

public class Empleado {
    
    private int id;
    private String contrasenya;
    private String nif;
    private String nombre;
    private String apellidos;
    private String puesto;
    private String direccion;
    private String email;
    private String telefono;
    private int idDepartamento;
    private String tipo;

    // Constructores

    // Constructor 1 - Para crear un empleado omitiendo el ID y la contrasenya
    public Empleado(String nif, String nombre, String apellidos, String puesto, String direccion, String email, String telefono, int idDepartamento, String tipo) {

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

        if(puesto==null || puesto.isEmpty() || puesto.isBlank()) {
            throw new IllegalArgumentException("Puesto obligatorio");
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

        if(idDepartamento<0 || idDepartamento>99) {
            throw new IllegalArgumentException("ID del departamento incorrecto, el número ID tiene 1 o 2 dígitos");
        }

        if(tipo==null || tipo.isEmpty() || tipo.isBlank()) {
            throw new IllegalArgumentException("Tipo de empleado obligatorio");
        } else if(!tipo.toLowerCase().equals("gestor") || !tipo.toLowerCase().equals("operario")) {
            throw new IllegalArgumentException("Tipo de empleado incorrecto, introduce uno de los siguientes valores: operario o gestor");
        }

        String contrasenya = apellidos.toLowerCase().replace("[ .-]+", "").replace("[á]", "a").replace("[é]", "e").replace("[í]", "i").replace("[ó]", "o").replace("[úü]", "u").replace("[ñ]", "n");
        this.contrasenya = contrasenya;
        this.nif = nif;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.puesto = puesto;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
        this.idDepartamento = idDepartamento;
        this.tipo = tipo;
    }

    // Constructor 2 - Para leer un empleado
    public Empleado(int id, String contrasenya, String nif, String nombre, String apellidos, String puesto, String direccion, String email, String telefono, int idDepartamento, String tipo) {

        if(contrasenya==null || contrasenya.isEmpty() || contrasenya.isBlank()) {
            throw new IllegalArgumentException("Contraseña obligatoria");
        }

        if(nif==null || nif.isEmpty() || nif.isBlank()) {
            throw new IllegalArgumentException("NIF obligatorio");
        } else if(!nif.matches("^\\d{8}[A-Za-z]$")) {
            throw new IllegalArgumentException("NIF incorrecto, introduce un número de 8 dígitos + 1 letra");
        }

        if(nombre==null || nombre.isEmpty() || nombre.isBlank()) {
            throw new IllegalArgumentException("Nombre obligatorio");
        }

        if(apellidos==null || apellidos.isEmpty() || apellidos.isBlank()) {
            throw new IllegalArgumentException("Nombre obligatorio");
        }

        if(puesto==null || puesto.isEmpty() || puesto.isBlank()) {
            throw new IllegalArgumentException("Nombre obligatorio");
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

        if(idDepartamento<0 || idDepartamento>99) {
            throw new IllegalArgumentException("ID del departamento incorrecto, el número ID tiene 1 o 2 dígitos");
        }

        if(tipo==null || tipo.isEmpty() || tipo.isBlank()) {
            throw new IllegalArgumentException("Tipo de empleado obligatorio");
        } else if(!tipo.toLowerCase().equals("gestor") || !tipo.toLowerCase().equals("operario")) {
            throw new IllegalArgumentException("Tipo de empleado incorrecto, introduce uno de los siguientes valores: operario o gestor");
        }

        this.id = id;
        this.contrasenya = contrasenya;
        this.nif = nif;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.puesto = puesto;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
        this.idDepartamento = idDepartamento;
        this.tipo = tipo;
    }

    // Getters y Setters

    public int getId() { return id; }
    public String getContrasenya() { return contrasenya; }
    public String getNif() { return nif; }
    public String getNombre() { return nombre; }
    public String getApellidos() { return apellidos; }
    public String getPuesto() { return puesto; }
    public String getDireccion() { return direccion; }
    public String getEmail() { return email; }
    public String getTelefono() { return telefono; }
    public int getIdDepartamento() { return idDepartamento; }
    public String getTipo() { return tipo; } 

    public void setId(int id) {

        this.id = id;
    }

    public void setContrasenya(String contrasenya) {

        if(contrasenya==null || contrasenya.isEmpty() || contrasenya.isBlank()) {
            throw new IllegalArgumentException("Contraseña obligatoria");
        } else if(!contrasenya.matches(".*[ \\sáéíóúÁÉÍÓÚüÜñÑ].*")) {
            throw new IllegalArgumentException("La contraseña no puede contener espacios, tildes, diéresis o virgulillas");
        }
        this.contrasenya = contrasenya;
    }

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

    public void setPuesto(String puesto) {

        if(puesto==null || puesto.isEmpty() || puesto.isBlank()) {
            throw new IllegalArgumentException("Nombre obligatorio");
        }
        this.puesto = puesto;
    }

    public void setDireccion(String direccion) {

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

    public void setIdDepartamento(int idDepartamento) {

        if(idDepartamento<0 || idDepartamento>99) {
            throw new IllegalArgumentException("ID del departamento incorrecto, el número ID tiene 1 o 2 dígitos");
        }
        this.idDepartamento = idDepartamento;
    }

    public void setTipo(String tipo) {

        if(tipo==null || tipo.isEmpty() || tipo.isBlank()) {
            throw new IllegalArgumentException("Tipo de empleado obligatorio");
        } else if(!tipo.toLowerCase().equals("gestor") && !tipo.toLowerCase().equals("operario")) {
            throw new IllegalArgumentException("Tipo de empleado incorrecto, introduce uno de los siguientes valores: operario o gestor");
        }
        this.tipo = tipo;
    }

    // Sobreescritura de métodos

    @Override 
    public String toString() {

        return "ID: " + id + ", Contraseña: " + contrasenya + ", NIF: " + nif + ", Nombre: " + nombre + ",Apellidos: " + apellidos + ", Puesto: " + puesto + ", Email: " + email + ", Teléfono: " + telefono + ", Departamento: " + idDepartamento + ", Tipo: " + tipo;
    }

    @Override 
    public boolean equals(Object obj) {

        if(this==obj) return true;
        if(obj==null) return false;
        if(getClass()!=obj.getClass()) return false;
        Empleado otro = (Empleado)obj;
        return Objects.equals(nif, otro.nif);
    }

    @Override 
    public int hashCode() {

        return Objects.hash(contrasenya);
    }
}
