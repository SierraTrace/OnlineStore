package controlador;

// el paquete controlador únicamente contendrá la clase Controlador, que hará de puente entre la vista y el modelo.
// La vista sólo podrá utilizar esta clase para acceder a la información del modelo.

import modelo.Articulo;
import modelo.Modelo;
import vista.Vista;

public class controlador {

    private Modelo modeloTienda;
    private Vista VistaTienda;

    public controlador(Modelo modeloTienda, Vista VistaTienda) {
        this.modeloTienda = modeloTienda;
        this.VistaTienda = VistaTienda;
    }

    public anyadir(Articulo articulo) {


        // Lo que nos llegue de vista

        modeloTienda.addArticulo(articulo);
    }
}
