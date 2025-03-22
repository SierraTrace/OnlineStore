package vista;
import java.io.PrintStream;
import java.util.Scanner;
import controlador.Controlador;
import modelo.Articulo;
import modelo.Cliente.Cliente;

public class Vista {

    Scanner scanner;
    Controlador controlador;
    PrintStream streamSalida;


    // Constructor
    public Vista() {
        this.scanner= new Scanner(System.in);
        this.streamSalida= System.out;
    }

    //Actualiza el controlador y comienza el menú principal
    public void startVista(Controlador controlador) {
        this.controlador = controlador;
        menuPrincipal();
    }

    //Método genérico para mostrar por consola
    public void updateView(String string){
        System.out.println(string);
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
                    menuGestionClientes();
                    break;
                case 3:
                    gestionarPedidos();
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

    // E es el tipo genérico de la lista, los objetos de la lista deben tener metodo toString().
    private <E> void mostrarArticulos() {
        // TODO verificar
        //PIde la lista generica al controlador.
        //List<E> listaArticulos = controlador.getListaArticulos();

        //Recorre la lista e imprime sus elementos
        //for (E articulo : listaArticulos) {
        //    System.out.println(articulo.toString()+"\n");
        //}
        controlador.mostrarArticulos();
    }
    private void anyadirArticulo() {
        String codigoArticulo;
        scanner.nextLine(); //LIMPIA BUFFER
        System.out.print("Introduzca el código del articulo:");
        codigoArticulo = scanner.nextLine();
        if (esArticuloNuevo(codigoArticulo)) {
            pedirDatosArticulo(codigoArticulo);
        }
    }
    private boolean esArticuloNuevo(String codigoArticulo){
        //Pide al controlador que compruebe si el árticulo ya existe
        return (!controlador.findItem(codigoArticulo));
    }
    private void pedirDatosArticulo(String codigoArticulo){
            String descripcion;
            Float precioVenta;
            Float gastosEnvio;
            Integer tiempoPreparacion;

            //TODO Pedir todos los datos y actualizar las variables
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
    private void menuGestionClientes() {
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
                anyadirCliente();
                break;
            case 2:
                menuMostrarClientes();
                break;
            case 0:
                break;
            default:
                System.out.println("Opción no válida. Intente de nuevo.");
        }
    } while (opcion != 0);
}
    private void anyadirCliente() {
        String emailCliente;
        scanner.nextLine(); //LIMPIA BUFFER
        System.out.print("Introduzca el e-mail del cliente:");
        emailCliente = scanner.nextLine();
        if (esClienteNuevo(emailCliente)) {
            pedirDatosCliente(emailCliente);
        }
    }
    private boolean esClienteNuevo(String emailCliente){
        //Pide al controlador que compruebe si el árticulo ya existe
        return (!controlador.findCliente(emailCliente));
    }
    private void pedirDatosCliente(String emailCliente){
        String nombre;
        String domicilio;
        String nif;

        //TODO Pedir todos los datos y actualizar las variables
        System.out.print("S: ");
        nombre = scanner.nextLine();

        System.out.print("S: ");
        domicilio = scanner.nextLine();

        System.out.print("S: ");
        nif = scanner.nextLine();

        controlador.addCliente(nombre, domicilio, nif, emailCliente);

    }
    private void menuMostrarClientes(){
    int opcion;
    do {
        System.out.println("\nGestión de Clientes > Mostrar Clientes");
        System.out.println("1. Mostrar todos los clientes");
        System.out.println("2. Mostrar clientes Estandar");
        System.out.println("2. Mostrar clientes Premium");
        System.out.println("0. Volver");
        System.out.print("Seleccione una opción: ");
        opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                controlador.mostrarClientes();
                break;
            case 2:
                controlador.mostrarClientesEstandar();
                break;
            case 3:
                controlador.mostrarClientesPremium();
                break;
            case 0:
                break;
            default:
                System.out.println("Opción no válida. Intente de nuevo.");
        }
    } while (opcion != 0);
}

//GESTIÓN DE PEDIDOS
    private void gestionarPedidos() {
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

private void anyadirPedido(){
    String codigoArticulo;
    Integer cantidad;
    String emailCliente;

    System.out.print("Introduzca el código del artículo:");
    codigoArticulo = scanner.nextLine();
    System.out.print("Introduzca la cantidad:");
    cantidad = scanner.nextInt();
    scanner.nextLine();//LIMPIA BUFFER
    System.out.print("Introduzca el e-mail del cliente:");
    emailCliente = scanner.nextLine();

    controlador.addPedido(codigoArticulo, cantidad, emailCliente);
}

private void eliminarPedido(){
    System.out.print("Introduzca el número del pedido:");
    Integer numeroPedido = scanner.nextInt();
    scanner.nextLine();
    controlador.removePedido(numeroPedido);
}

private void mostrarPedidosPendientes(){

}

private void mostrarPedidosEnviados(){

}
}