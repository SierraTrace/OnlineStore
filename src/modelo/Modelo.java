package modelo;

// Grupo 2 - SQL SQUAD

// En el diagrama de clases se han representado las funcionalidades que tendrá la tienda online sobre la clase tienda
// En esta clase de momento solo hemos desarrollado getters, setters y toString entendiendo que el desarrollo de
// las funcionalidades de la tienda se realizará en el Producto 2


import modelo.Cliente.Cliente;

import java.util.ArrayList;
import java.util.HashMap;


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

    public Cliente getPedido(Integer numeroPedido) {
        return Null;
    } //TODO

    public Cliente getArticulo(String codigoArticulo) {
        return Null;
    } //TODO

    //Getters de listados
    public ArrayList<Cliente> getListaClientes() {return Null;} //TODO

    public ArrayList<Articulo> getArticulos() {
        return articulos;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public String toString() {
        return "Tienda OnLineStore de SQL Squad";
    }

    public boolean findItem(String codigoArticulo) {
        for (Articulo articulo : articulos) {
            if (articulo.getCodigoArticulo().equals(codigoArticulo)) {
                return true;
            }
        }
        return false;
    }
}
