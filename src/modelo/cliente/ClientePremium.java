// Grupo 2 - SQL SQUAD


package modelo.cliente;

import modelo.Articulo;
import modelo.enums.TipoCliente;

public class ClientePremium extends Cliente {

    private int descuento;
    private Float cuotaAnual;
    private final TipoCliente tipoCliente;

    public ClientePremium(Integer id, String nombre, String domicilio, String nif, String email) {
        super(id, nombre, domicilio, nif, email);
        this.descuento = 20;
        this.cuotaAnual = 30.0f;
        this.tipoCliente = TipoCliente.PREMIUM;
    }

    // TODO Revisar
    public ClientePremium(Integer id, String nombre, String domicilio, String nif,
                          String email, Integer descuento, Float cuotaAnual) {
        super(id, nombre, domicilio, nif, email);
        this.tipoCliente = TipoCliente.PREMIUM;
    }

    public int getDescuento() {
        return descuento;
    }
    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }
    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public Float getCuotaAnual() {
        return cuotaAnual;
    }
    public void setCuotaAnual(Float cuotaAnual) {
        this.cuotaAnual = cuotaAnual;
    }

    public void setDescuento(Integer descuento) {
        this.descuento = descuento;
    }

    @Override
    public String toString() {
        return super.toString() + " Descuento: " + descuento + " Tipo: " + tipoCliente;
    }
}
