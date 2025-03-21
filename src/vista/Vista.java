package vista;

import modelo.Modelo;
import controlador.controlador;

import java.util.Scanner;

public class Vista {

    private Scanner teclado = new Scanner(System.in);



    // Constructor
    public Vista() {

        // TODO Pendiente de evaluar si es correcto
        Modelo modeloTienda = new Modelo();
        Controlador controlador = new controlador(modeloTienda, this);

    }

    /**
     * Metodo para iniciar la vista
     */
    public void inicioVista() {

        //TODO Añadir variables de vista y modelo

        System.out.println("Bienvenido a Online Store");
        boolean salir = false;
        char opcion;
        do {
            printMenuPrincipal();
            opcion = OpcionMenu();
            switch (opcion) {
                case '1':
                    crearArticulo();
                    break;
                case '2':
                    // TODO Metodo para Mostrar producto
                    break;
                case '0':
                    salir = true;
                    teclado.close();
            }
        } while (!salir);
    }

    public void crearArticulo() {
        // TODO Metodo que crea el artículo
    }



    void printMenuPrincipal() {
        System.out.println("");
        System.out.println("1. Añadir Articulo");
        System.out.println("2. Mostrar Articulo");
        // TODO Pendiente de ir añadiendo opciones
        System.out.println("0. Salir");
    }

    /**
     * Gestión de opciones de los menús
     */
    char OpcionMenu() {
        String resp;
        System.out.print("Elige una opción: ");
        resp = teclado.nextLine();
        if (resp.isEmpty()) {
            resp = " ";
        }
        return resp.charAt(0);
    }
}
