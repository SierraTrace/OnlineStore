package vista;

import modelo.Modelo;
import controlador.controlador;

public class Vista {



    public Vista() {

        Modelo modeloTienda = new Modelo();
        controlador = new controlador(modeloTienda, this);

    }
}
