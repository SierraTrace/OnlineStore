package modelo;

// Grupo 2 - SQL SQUAD


import modelo.cliente.Cliente;
import modelo.enums.TipoEstado;
import modelo.cliente.ClienteEstandar;
import modelo.cliente.ClientePremium;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class Modelo {

    private HashMap<String, Cliente> clientes;
    private ArrayList<Articulo> articulos;
    private ArrayList<Pedido> pedidos;
    private Integer proximoPedido;


    public Modelo() {
        clientes = new HashMap<>();
        articulos = new ArrayList<>();
        pedidos = new ArrayList<>();
        proximoPedido = 0;
    }

    public Integer generarProximoPedido() {
        return ++proximoPedido;
    }

    //Añaden a listados
    public void addArticulo(Articulo articulo) {
        articulos.add(articulo);
    }

    public void addPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public void addCliente(Cliente cliente) {
        clientes.put(cliente.getEmail(), cliente);
    }

    //Getters individuales
    public Cliente getCliente(String email) {
        return clientes.get(email);
    }

    public Pedido getPedido(Integer numeroPedido) {
        for (Pedido pedido : pedidos) {
            if (pedido.getNumeroPedido().equals(numeroPedido)) {
                return pedido;
            }
        }
        return null;
    }


    public Articulo getArticulo(String codigoArticulo) {
        for (Articulo articulo : articulos) {
            if (articulo.getCodigoArticulo().equals(codigoArticulo)) {
                return articulo;
            }
        }
        return null;
    }

    //Getters de listados
    public HashMap<String, Cliente> getListaClientes() {
        if (clientes == null) {
            throw new IllegalStateException("Mapa de clientes no inicializado");
        }
        return new HashMap<String, Cliente>(clientes);
    }

    public ArrayList<Articulo> getArticulos() {
        if (articulos == null) {
            throw new IllegalStateException("Lista de articulos no inicializada");
        }
        return articulos;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public String toString() {
        return "Tienda OnLineStore de SQL Squad";
    }

    public boolean existeArticulo(String codigoArticulo) {
        for (Articulo articulo : articulos) {
            if (articulo.getCodigoArticulo().equals(codigoArticulo)) {
                return true;
            }
        }
        return false;
    }

    public void actualizarPedidos() {
        if (pedidos == null) {
            throw new IllegalStateException("Lista de pedidos no inicializada");
        }
        for (Pedido pedido : pedidos) {
            pedido.actualizarEstadoPreparacion();
        }
    }

    public boolean actualizarPedido(Integer numeroPedido) {
        if (pedidos == null) {
            throw new IllegalStateException("Lista de pedidos no inicializada");
        }
        for (Pedido pedido : pedidos) {
            if (pedido.getNumeroPedido().equals(numeroPedido)) {
                pedido.actualizarEstadoPreparacion();
                return true;
            }
        }
        return false;
    }


    public boolean eliminarPedido(Integer numeroPedido) {
        if (pedidos == null) {
            throw new IllegalStateException("Lista de pedidos no inicializada");
        }

        Iterator<Pedido> iterator = pedidos.iterator();
        while (iterator.hasNext()) {
            Pedido pedido = iterator.next();
            if (pedido.getNumeroPedido().equals(numeroPedido)) {
                pedido.actualizarEstadoPreparacion();
                //Verificar si el estado permite eliminación
                if (pedido.getEstado().equals(TipoEstado.PENDIENTE)) {
                    iterator.remove(); // Elimina el pedido
                    return true;
                }
            }
        }
        return false;
    }

    public void cargarDatosIniciales() {
        // Artículos
        Articulo a1 = new Articulo("100", "Mesa", 20f, 7f, 30);
        Articulo a2 = new Articulo("101", "Tabla", 40f, 2f, 20);
        Articulo a3 = new Articulo("102", "Silla", 30f, 1f, 1);
        addArticulo(a1);
        addArticulo(a2);
        addArticulo(a3);

        // Clientes
        Cliente c1 = new ClienteEstandar(1L,"Esteban Casa", "Calle Eterna", "11111111D", "esteban@c.com");
        Cliente c2 = new ClientePremium(2L, "Elisa Techo", "Avenida Sol 45", "11111111A", "elisa@c.com");
        Cliente c3 = new ClienteEstandar(3L, "Eduardo Mole", "Plaza Mayor 8", "11111111V", "eduardo@c.com");
        addCliente(c1);
        addCliente(c2);
        addCliente(c3);

        // Pedidos PENDIENTES
        Pedido p1 = new Pedido(generarProximoPedido(), a1, 1, c1, LocalDateTime.now(), TipoEstado.PENDIENTE);
        Pedido p2 = new Pedido(generarProximoPedido(), a2, 2, c2, LocalDateTime.now(), TipoEstado.PENDIENTE);
        addPedido(p1);
        addPedido(p2);

        // Pedidos ENVIADOS
        Pedido p3 = new Pedido(generarProximoPedido(), a3, 1, c3, LocalDateTime.now(), TipoEstado.PENDIENTE); // eduardo@.com
        Pedido p4 = new Pedido(generarProximoPedido(), a1, 1, c2, LocalDateTime.now(), TipoEstado.PENDIENTE);

        addPedido(p3);
        addPedido(p4);
    }
}
