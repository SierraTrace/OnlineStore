// Grupo 2 - SQL SQUAD

package modelo;


import modelo.Cliente.Cliente;
import modelo.enums.TipoEstado;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Pedido {

    private Integer numeroPedido;
    private Articulo articulo;
    private Integer cantidadArticulos;
    private Cliente cliente;
    private Float precioTotal;
    private LocalDateTime fechaPedido;
    private TipoEstado estado;

    public Pedido(Integer numeroPedido, Articulo articulo, Integer cantidadArticulos, Cliente cliente) {
        this.numeroPedido = numeroPedido;
        this.articulo = articulo;
        this.cantidadArticulos = cantidadArticulos;
        this.cliente = cliente;
        this.precioTotal = calcularTotal();
        this.fechaPedido = LocalDateTime.now();
        this.estado = TipoEstado.PENDIENTE;
    }

    private float calcularTotal() {
        // TODO Desarrollar cálculo del precio total
        return 0; // TODO Controlar retorno
    }

    public void actualizarEstadoPreparacion() {
        // TODO Desarrollar método que verifique el "tiempoPreparación" vs "fechaPedido" y actualice "estado"
    }

    private TipoEstado getEstado() {
        return estado;
    }
    private void setEstado(TipoEstado estado) {
        this.estado = estado;
    }
    public Integer getNumeroPedido() {
        return numeroPedido;
    }
    public Articulo getArticulo() {
        return articulo;
    }
    private void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }
    public Integer getCantidadArticulos() {
        return cantidadArticulos;
    }
    private void setCantidadArticulos(Integer cantidadArticulos) {
        this.cantidadArticulos = cantidadArticulos;
    }
    public Cliente getCliente() {
        return cliente;
    }
    private void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public Float getPrecioTotal() {
        return precioTotal;
    }
    public LocalDateTime getFechaPedido() {
        return fechaPedido;
    }

    @Override
    public String toString() {

        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        String fechaConFormato = fechaPedido.format(formatoFecha);
        String horaConFormato = fechaPedido.format(formatoHora);

        StringBuilder sb = new StringBuilder();

        sb.append("----- Pedido -----\n");
        sb.append("\tNª Pedido: " + numeroPedido + "\n");
        sb.append("\tFecha: " + fechaConFormato + "\n");
        sb.append("\tHora: " + horaConFormato + "\n");
        sb.append("\tCliente.Cliente: " + cliente.getNombre() + "\n");
        sb.append("\tArticulo: " + articulo.getDescripcion() + "\n");
        sb.append("\tCantidad: " + cantidadArticulos + "\n");
        sb.append("\tPrecio: ");
        sb.append(String.format("%.2f", precioTotal));
        sb.append(" €\n");
        sb.append("\tEstado: " + estado + "\n");

        return sb.toString();
    }
}
