// Grupo 2 - SQL SQUAD

package modelo;


import modelo.Cliente.Cliente;
import modelo.Cliente.ClientePremium;
import modelo.enums.TipoEstado;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class  Pedido {

    private Integer numeroPedido;
    private Articulo articulo;
    private Integer cantidadArticulos;
    private Cliente cliente;
    private double precioTotal;
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

    private double calcularTotal() {
        // TODO Pendiente implementar test unitario
        // Calcular gastos de envío
        double gastosEnvio;
        if (cliente instanceof ClientePremium clientePremium) {
            gastosEnvio = articulo.getGastosEnvio() * (clientePremium.getDescuento() / 100.0);
        } else {
            gastosEnvio = articulo.getGastosEnvio();
        }

        // Calcular total
        return gastosEnvio + (articulo.getPrecioVenta() * cantidadArticulos);
    }

    public void actualizarEstadoPreparacion() {
        // TODO Pendiente implementar test unitario
        if (estado == TipoEstado.PENDIENTE) {
            // Calcular el tiempo transcurrido
            long tiempoTranscurrido = Duration.between(fechaPedido, LocalDateTime.now()).toMinutes();

            // Actualizar estado se ha cumplido el tiempo de preparación
            if (tiempoTranscurrido >= articulo.getTiempoPreparacion()) {
                estado = TipoEstado.ENVIADO;
            }
        }
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

    public double getPrecioTotal() {
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
