package vista;
import java.io.PrintStream;
import java.util.Scanner;
import controlador.Controlador;
import java.util.List;

public class Vista {

    Scanner scanner;
    Controlador controlador;
    PrintStream streamSalida;

    public Vista() {
        this.scanner= new Scanner(System.in);
        this.streamSalida= System.out;
    }

    //Actualiza el controlador y comienza el menú principal
    public void startVista(Controlador controlador) {
        this.controlador = controlador;
        menuPrincipal();
    }

    //Muestra el nivel más alto de jerarquía del menú y controla el bucle.
    private void menuPrincipal(){
        int opcion;
        do{
            System.out.println("\nMenú Principal");
            System.out.println("1. Gestión de Artículos");
            System.out.println("2. Gestión de Clientes");
            System.out.println("3. Gestión de Pedidos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    menuGestionArticulos();
                    break;
                case 2:
                    gestionarClientes(scanner);
                    break;
                case 3:
                    gestionarPedidos(scanner);
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }


    //GESTION DE ARTICULOS
    private void menuGestionArticulos() {
        int opcion;
        do {
            System.out.println("\nGestión de Artículos");
            System.out.println("1. Añadir Artículos");
            System.out.println("2. Mostrar Artículos");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt(); //Cambiar para evitar errores con no numéricos

            switch (opcion) {
                case 1:
                    anyadirArticulo();
                    break;
                case 2:
                    mostrarArticulos();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    // T es el tipo genérico de la lista, los objetos de la lista deben tener método toString().
    private <E> void mostrarArticulos() {
        //PIde la lista generica al controlador.
        //List<E> listaArticulos = controlador.getListaArticulos();

        //Recorre la lista e imprime sus elementos
        //for (E articulo : listaArticulos) {
        //    System.out.println(articulo.toString()+"\n");
        //}
        controlador.mostrarArticulos(streamSalida);
    }

    private void anyadirArticulo() {
        String codigoArticulo;
        scanner.nextLine(); //LIMPIA BUFFER
        System.out.print("Introduzca el código del articulo:");
        codigoArticulo = scanner.nextLine();
        if (esArticuloNuevo(codigoArticulo)) {
            crearArticulo(codigoArticulo);
        }
    }
    private boolean esArticuloNuevo(String codigoArticulo){
        //Pide al controlador que compruebe si el árticulo ya existe
        return (!controlador.findItem(codigoArticulo));
    }
    private void crearArticulo(String codigoArticulo){
            String descripcion;
            Float precioVenta;
            Float gastosEnvio;
            Integer tiempoPreparacion;

            //Pedir todos los datos y actualizar las variables TODO
            System.out.print("S: ");
            descripcion = scanner.nextLine();

            System.out.print("S: ");
            precioVenta = scanner.nextFloat();

            System.out.print("S: ");
            gastosEnvio = scanner.nextFloat();

            System.out.print("S: ");
            tiempoPreparacion = scanner.nextInt();

            controlador.addArticle(codigoArticulo, descripcion, precioVenta, gastosEnvio, tiempoPreparacion);
    }

    //GESTIÓN DE CLIENTES
    private void gestionarClientes(Scanner scanner) {
    int opcion;
    do {
        System.out.println("\nGestión de Clientes");
        System.out.println("1. Añadir Clientes");
        System.out.println("2. Mostrar Clientes");
        System.out.println("0. Volver");
        System.out.print("Seleccione una opción: ");
        opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                System.out.println("Añadir Clientes aún no implementado.");
                break;
            case 2:
                System.out.println("Mostrar Clientes aún no implementado.");
                break;
            case 0:
                break;
            default:
                System.out.println("Opción no válida. Intente de nuevo.");
        }
    } while (opcion != 0);
}

    //GESTIÓN DE PEDIDOS
    private void gestionarPedidos(Scanner scanner) {
    int opcion;
    do {
        System.out.println("\nGestión de Pedidos");
        System.out.println("3.1. Añadir Pedido");
        System.out.println("3.2. Eliminar Pedido");
        System.out.println("3.3. Mostrar Pedidos Pendientes");
        System.out.println("3.4. Mostrar Pedidos Enviados");
        System.out.println("0. Volver");
        System.out.print("Seleccione una opción: ");
        opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                System.out.println("Añadir Pedido aún no implementado.");
                break;
            case 2:
                System.out.println("Eliminar Pedido aún no implementado.");
                break;
            case 3:
                System.out.println("Mostrar Pedidos Pendientes aún no implementado.");
                break;
            case 4:
                System.out.println("Mostrar Pedidos Enviados aún no implementado.");
                break;
            case 0:
                break;
            default:
                System.out.println("Opción no válida. Intente de nuevo.");
        }
    } while (opcion != 0);
}

}