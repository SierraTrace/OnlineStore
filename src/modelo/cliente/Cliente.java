// Grupo 2 - SQL SQUAD


package modelo.cliente;

public class Cliente {
    private long id;
    private String nombre;
    private String domicilio;
    private String nif;
    private String email;

    public Cliente(long id, String nombre, String domicilio, String nif, String email) {
        this.id =id;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.nif = nif;
        this.email = email;
    }
    public long getId(){return id;}
    public String getNombre() {
        return nombre;
    }
    public String getDomicilio() {
        return domicilio;
    }
    public String getNif() {
        return nif;
    }
    public String getEmail() {
        return email;
    }
    public void setId(int id) {this.id = id;}
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
    public void setNif(String nif) {
        this.nif = nif;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Id: " + id + " Nombre: " + nombre + " Domicilio: " + domicilio + " Nif: " + nif + " Email: " + email;
    }
}
