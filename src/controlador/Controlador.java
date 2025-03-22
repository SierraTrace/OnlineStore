package controlador;

// el paquete controlador únicamente contendrá la clase Controlador, que hará de puente entre la vista y el modelo.
// La vista sólo podrá utilizar esta clase para acceder a la información del modelo.

import modelo.Cliente.Cliente;
import modelo.Cliente.ClienteEstandar;
import modelo.Cliente.ClientePremium;
import vista.Vista;

import java.io.PrintStream;
import java.util.List;

import modelo.*;

public class Controlador {

    private final Modelo modeloTienda;
    private final Vista vistaTienda;

    public Controlador(Modelo modeloTienda, Vista vistaTienda) {
        this.modeloTienda = modeloTienda;
        this.vistaTienda = vistaTienda;
    }

    ////////////// GESTION DE ARTÍCULOS ///////////////////////////////////////
    ////////Métodos que actualizan el modelo///////
    //Permite añadir un artículo al modelo.
    public void addArticle(String codigoArticulo, String descripcion,
                           Float precioVenta, Float gastosEnvio,
                           Integer tiempoPreparacion) {
        Articulo articulo = new Articulo(codigoArticulo, descripcion, precioVenta, gastosEnvio, tiempoPreparacion);

        modeloTienda.addArticulo(articulo);
    }

    ////////Métodos que toman info del modelo////////
    public boolean findItem(String codigoArticulo) {
        return (modeloTienda.findItem(codigoArticulo));
    }

    private <E> List<E> getListaArticulos() {
        return ((List<E>) modeloTienda.getArticulos());
    }


    ////////Métodos que actualizan la vista.////////

    //Imprime por el stream de salida la lista.
    public <E> void mostrarArticulos() {
        //PIde la lista generica al controlador.
        List<E> listaArticulos = this.getListaArticulos();
        StringBuilder sb = new StringBuilder();
        //Recorre la lista e imprime sus elementos
        for (E articulo : listaArticulos) {
            sb.append(articulo.toString() + "\n");
        }
        vistaTienda.updateView(sb.toString());
    }


    ///////////////////////Gestión de Clientes//////////////////////////
    /// Permite añadir un cliente al modelo.
    public void addCliente(String nombre, String domicilio, String nif, String email) {
        Cliente cliente = new Cliente(nombre, domicilio, nif, email);
        modeloTienda.addCliente(cliente);
    }


    //Métodos que toman info del modelo

    /**
     * @param email
     * @param <T> T
     * @return T
     */
    //Recupera cliente del modelo
    private <T> T getCliente(String email) {
        return ((T) modeloTienda.getCliente(email));
    }
    private <E> List<E> getListaClientes() {
        return ((List<E>) modeloTienda.getListaClientes());

    }
    public boolean findCliente(String emailCliente) {
        if(modeloTienda.getCliente(emailCliente)!= null) return true;
        return false;
    }

    //Comprueba que se trata de un objeto cliente premium
    private <E> boolean isPremium(E cliente){
        return cliente.getClass()== ClientePremium.class;
    }

    //Comprueba que se trata de un objeto cliente estandar
    private <E> boolean isEstandar(E cliente){
        return cliente.getClass()== ClienteEstandar.class;
    }


    //Métodos que actualizan la vista.

    public <E> void mostrarClientes() {
        List<E> listaClientes = getListaClientes();
        StringBuilder sb = new StringBuilder();
        sb.append("Lista de clientes: \n");
        for (E cliente : listaClientes) {
            sb.append(cliente.toString() + "\n");
        }
        //Envía la cadena a la vista y hace update
        vistaTienda.updateView(sb.toString());
    }

    public <E> void mostrarClientesPremium() {
        List<E> listaClientes = getListaClientes();
        StringBuilder sb = new StringBuilder();
        sb.append("Lista de clientes Premium: \n");
        for (E cliente : listaClientes) {
            if (isPremium(cliente)) {
                sb.append(cliente.toString() + "\n");
            }
        }
        //Envía la cadena a la vista y hace update
        vistaTienda.updateView(sb.toString());
    }

    public <E> void mostrarClientesEstandar() {
        List<E> listaClientes = getListaClientes();
        StringBuilder sb = new StringBuilder();
        sb.append("Lista de clientes Estandar: \n");
        for (E cliente : listaClientes) {
            if (isEstandar(cliente)) {
                sb.append(cliente.toString() + "\n");
            }
        }
        //Envía la cadena a la vista y hace update
        vistaTienda.updateView(sb.toString());
    }




    ///////////////////////Gestión de Pedidos//////////////////////////
    /// Permite añadir un pedido al modelo.
    public void addPedido(String codigoArticulo, Integer cantidadArticulos, String emailCliente) {
        Integer numeroPedido = modeloTienda.generarProximoPedido();
        Articulo articulo = modeloTienda.getArticulo(codigoArticulo);
        Cliente cliente = modeloTienda.getCliente(emailCliente);
        Pedido pedido = new Pedido(numeroPedido, articulo, cantidadArticulos, cliente);
        modeloTienda.addPedido(pedido);
        vistaTienda.updateView("Pedido añadido con el número: "+ numeroPedido);
    }

    //Permite eliminar un pedido del modelo.
    //. Un pedido puede ser borrado únicamente si no ha sido enviado, es decir, si el tiempo transcurrido a desde
    // la fecha y hora del pedido no supera el tiempo de preparación para el envío del artículo.
    public void removePedido(Integer numeroPedido) {
        if(esBorrable(numeroPedido)){
            modeloTienda.borrarPedido(numeroPedido); //FALTA EN MODELO
        } else {
            vistaTienda.updateView("No es posible borrar el pedido");
        }
    }


//Métodos que toman info del modelo

    //Recupera pedido del modelo
    private <T> T getPedido(String email) {
        return ((T) modeloTienda.getCliente(email));
    }


//Métodos que actualizan la vista.
    public <E> void mostrarPedidos(PrintStream streamSalida) {
        //PIde la lista generica al controlador.
        //TODO
    }

    public void mostrarPedidosPendientes(String iDcliente) {
        //TODO
    }

    public void mostrarPedidosEnviados(String iDcliente) {
        //TODO
    }




}

