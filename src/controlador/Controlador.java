package controlador;

// el paquete controlador únicamente contendrá la clase Controlador, que hará de puente entre la vista y el modelo.
// La vista sólo podrá utilizar esta clase para acceder a la información del modelo.

import modelo.*;
import vista.Vista;

import java.io.PrintStream;
import java.util.List;


public class Controlador {

    private Modelo modeloTienda;
    private Vista  vistaTienda;

    public Controlador() {
        this.modeloTienda = new Modelo();
        this.vistaTienda = new Vista();
    }
    public Controlador(Modelo modeloTienda, Vista vistaTienda) {
        this.modeloTienda = modeloTienda;
        this.vistaTienda = vistaTienda;
    }



    ////////////// GESTION DE ARTÍCULOS ///////////////////////////////////////
    //Métodos que actualizan el modelo

    //Permite añadir un artículo al modelo.
    public void addArticle(String codigoArticulo, String descripcion,
                           Float precioVenta, Float gastosEnvio,
                           Integer tiempoPreparacion){

        Articulo articulo = new Articulo(codigoArticulo, descripcion, precioVenta,gastosEnvio,tiempoPreparacion);
        modeloTienda.addArticulo(articulo);
    }

    //Métodos que toman info del modelo
    public boolean findItem (String codigoArticulo) {
        return (modeloTienda.findItem(codigoArticulo));
    }

    private <E> List<E> getListaArticulos() {
        return ((List<E>)modeloTienda.getArticulos());
    }


    //Métodos que actualizan la vista.
    public <E> void mostrarArticulos(PrintStream streamSalida) {
        //PIde la lista generica al controlador.
        List<E> listaArticulos = this.getListaArticulos();

        //Recorre la lista e imprime sus elementos
        for (E articulo : listaArticulos) {
            streamSalida.println(articulo.toString()+"\n");
        }
    }


    ///////////////////////Gestión de Clientes//////////////////////////
    //Métodos que actualizan el modelo

    //Permite añadir un cliente al modelo.
    public void addClient(){
        //TODO

    }

    //Métodos que pasan información a la vista.

    // Este método devuelve una lista de los clientes como
    // string.
    public void  showClients(String tipo){
        //TODO
    }

    ///////////////////////Gestión de Pedidos//////////////////////////
    //Métodos que actualizan el modelo

    //Permite añadir un pedido al modelo.
    public void addOrder(){
        //TODO
    }

    //Permite eliminar un pedido del modelo.
    public void removeOrder(){
        //TODO
    }

    //Métodos que pasan información a la vista.

    // Este método devuelve una lista de los clientes como
    // string.
    public void  showPendingOrders(String iDcliente){
        //TODO
    }

    // Este método devuelve una lista de los clientes como
    // string.
    public void  showSentOrders(String iDcliente) {
        //TODO
    }
}
