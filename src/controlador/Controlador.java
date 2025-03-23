package controlador;

// el paquete controlador únicamente contendrá la clase Controlador, que hará de puente entre la vista y el modelo.
// La vista sólo podrá utilizar esta clase para acceder a la información del modelo.

import modelo.Cliente.Cliente;
import modelo.Cliente.ClienteEstandar;
import modelo.Cliente.ClientePremium;
import vista.Vista;

import java.io.PrintStream;
import java.util.HashMap;
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
    public void addArticle(String codigoArticulo, String descripcion, Float precioVenta, Float gastosEnvio, Integer tiempoPreparacion) {
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

    ////////Métodos que interactuan con la vista.////////
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
    public void esArticuloNuevo(String codigoArticulo){
        if (!findItem(codigoArticulo)){
            vistaTienda.pedirDatosArticulo(codigoArticulo);
        } else{
            vistaTienda.updateView("Artículo duplicado\n");
        }
    }

    ///////////////////////Gestión de Clientes//////////////////////////
    /// Permite añadir un cliente al modelo.
    public void addCliente(String nombre, String domicilio, String nif, String email, Integer tipoCliente) {
        if (tipoCliente==1){
            ClientePremium cliente = new ClientePremium(nombre, domicilio, nif, email);
            modeloTienda.addCliente(cliente);
            vistaTienda.updateView("Se ha creado un cliente Premium ");
        } else{
            ClienteEstandar cliente = new ClienteEstandar(nombre, domicilio, nif, email);
            modeloTienda.addCliente(cliente);
            vistaTienda.updateView("Se ha creado un cliente Estandar ");
        }
    }


    //Métodos que toman info del modelo
    //Recupera cliente del modelo
    private <T> T getCliente(String email) {
        return ((T) modeloTienda.getCliente(email));
    }
    private <K,V> HashMap<K,V> getListaClientes() {
        return ((HashMap<K,V>) modeloTienda.getListaClientes());

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

    //Métodos que interactuan con la vista.
    public <K,V> void mostrarClientes() {
        HashMap<K,V> listaClientes = getListaClientes();
        StringBuilder sb = new StringBuilder();
        sb.append("Lista de clientes: \n");
        for (K clave : listaClientes.keySet()) {
            sb.append(listaClientes.get(clave).toString() + "\n");
        }
        //Envía la cadena a la vista y hace update
        vistaTienda.updateView(sb.toString());
    }
    public void esClienteNuevo(String emailCliente){
        if (!findCliente(emailCliente)){
            vistaTienda.pedirDatosCliente(emailCliente);
        } else{
            vistaTienda.updateView("Cliente duplicado");
        }
    }
    public <K,V> void mostrarClientesPremium() {
        HashMap<K,V> listaClientes = getListaClientes();
        StringBuilder sb = new StringBuilder();
        sb.append("Lista de clientes: \n");
        for (K clave : listaClientes.keySet()) {
            if (isPremium(listaClientes.get(clave))) {
                sb.append(listaClientes.get(clave).toString() + "\n");
            }
        }
        //Envía la cadena a la vista y hace update
        vistaTienda.updateView(sb.toString());
    }

    public <K,V> void mostrarClientesEstandar() {
        HashMap<K,V> listaClientes = getListaClientes();
        StringBuilder sb = new StringBuilder();
        sb.append("Lista de clientes: \n");
        for (K clave : listaClientes.keySet()) {
            if (isEstandar(listaClientes.get(clave))) {
                sb.append(listaClientes.get(clave).toString() + "\n");
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
            //modeloTienda.borrarPedido(numeroPedido); //FALTA EN MODELO
        } else {
            vistaTienda.updateView("No es posible borrar el pedido");
        }
    }


//Métodos que toman info del modelo

    //Recupera pedido del modelo
    private <T> T getPedido(String email) {
        return ((T) modeloTienda.getCliente(email));
    }
    private boolean esBorrable( int numeroPedido){
      return true;
    }

//Métodos que actualizan la vista.
    public <E> void mostrarPedidos() {
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

