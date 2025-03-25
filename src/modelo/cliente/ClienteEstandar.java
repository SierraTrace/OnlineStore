// Grupo 2 - SQL SQUAD


package modelo.cliente;

import modelo.enums.TipoCliente;

public class ClienteEstandar extends Cliente {

    private final TipoCliente tipoCliente;

    public ClienteEstandar(String nombre, String domicilio, String nif, String email) {
        super(nombre, domicilio, nif, email);
        this.tipoCliente = TipoCliente.ESTANDARD;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    @Override
    public String toString() {
        return super.toString() + " Tipo: " + tipoCliente;
    }
}
