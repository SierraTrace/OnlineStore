package vista;

import controlador.Controlador;

import java.io.PrintStream;
import java.util.Scanner;

public class Vista {
    Scanner scanner;
    Controlador controlador;
    PrintStream streamSalida;
    //Constructor
    public Vista() {
        this.scanner= new Scanner(System.in);
        this.streamSalida= System.out;
    }
    //Actualiza el controlador y comienza el menú principal
    public void startVista(Controlador controlador) {
        this.controlador = controlador;
        menuPrincipal();
    }
    //Metodo genérico para mostrar por consola
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
                    menuGestionPedidos();
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
    //Menú de opciónes para la gestión de artículos
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
    //Pide al controlador actualizar la vista con la lista de artículos
    private void mostrarArticulos() {
        controlador.mostrarArticulos();
    }
    //Inicia el proceso de alta de nuevo artículo
    private void anyadirArticulo() {
        String codigoArticulo;
        scanner.nextLine(); //LIMPIA BUFFER
        System.out.print("Introduzca el código del articulo:");
        codigoArticulo = scanner.nextLine();
        controlador.nuevoCodigoArticulo(codigoArticulo);
    }
    //Pide el resto de datos al usuario
    public void pedirDatosArticulo(String codigoArticulo){
            String descripcion;
            float precioVenta;
            float gastosEnvio;
            int tiempoPreparacion;

            System.out.print("Introduzca la descripcion del articulo: ");
            descripcion = scanner.nextLine();

            System.out.print("Introduzca el precio de venta: ");
            precioVenta = scanner.nextFloat();

            System.out.print("Introduzca los gastos de envio: ");
            gastosEnvio = scanner.nextFloat();

            System.out.print("Introduzca el tiempo de preparacion: ");
            tiempoPreparacion = scanner.nextInt();
            controlador.addArticulo(codigoArticulo, descripcion, precioVenta, gastosEnvio, tiempoPreparacion);
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
        controlador.esClienteNuevo(emailCliente);
    }
    public void pedirDatosCliente(String emailCliente){
        Long id;
        String nombre;
        String domicilio;
        String nif;
        int tipoCliente; //(1)Premium (2) Estandar

        System.out.print("Introduzca el id del cliente: ");//TODO Es autogenerado, creo que no debería preguntarse sino asignarse según cuántos cl ya haya.
        id = scanner.nextLong();

        System.out.print("Introduzca el nombre del cliente: ");
        nombre = scanner.nextLine();

        System.out.print("Indroduzca el domicilio del cliente: ");
        domicilio = scanner.nextLine();

        System.out.print("Introduzca el nif del cliente: ");
        nif = scanner.nextLine();

        System.out.print("Elija el tipo de cliente: (1)Premium (2) Estandar :");
        tipoCliente = scanner.nextInt();

        controlador.addCliente(id, nombre, domicilio, nif, emailCliente, tipoCliente);

    }
    private void menuMostrarClientes(){
        int opcion;
        do {
            System.out.println("\nGestión de Clientes > Mostrar Clientes");
            System.out.println("1. Mostrar todos los clientes");
            System.out.println("2. Mostrar clientes Estandar");
            System.out.println("3. Mostrar clientes Premium");
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
    private void menuGestionPedidos() {
    int opcion;
    do {
        System.out.println("\nGestión de Pedidos");
        System.out.println("1. Añadir Pedido");
        System.out.println("2. Eliminar Pedido");
        System.out.println("3. Mostrar Pedidos Pendientes");
        System.out.println("4. Mostrar Pedidos Enviados");
        System.out.println("0. Volver");
        System.out.print("Seleccione una opción: ");
        opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                anyadirPedido();
                break;
            case 2:
                eliminarPedido();
                break;
            case 3:
                mostrarPedidosPendientes();
                break;
            case 4:
                mostrarPedidosEnviados();
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
        int cantidad;
        String emailCliente;
        System.out.print("Introduzca el código del artículo:");
        codigoArticulo = scanner.nextLine();
        System.out.print("Introduzca la cantidad:");
        cantidad = scanner.nextInt();
        scanner.nextLine();//LIMPIA BUFFER
        System.out.print("Introduzca el e-mail del cliente:");
        emailCliente = scanner.nextLine();
        controlador.esClienteNuevo(emailCliente);
        controlador.addPedido(codigoArticulo, cantidad, emailCliente);
    }
    private void eliminarPedido(){
        System.out.print("Introduzca el número del pedido:");
        Integer numeroPedido = scanner.nextInt();
        scanner.nextLine();
        controlador.removePedido(numeroPedido);
    }

    private void mostrarPedidosPendientes(){
        String opcion;
        scanner.nextLine();
        System.out.println("Si desea filtrar por cliente escriba su correo electronico, si quiere ver todos los pedidos pendientes escriba T :");
        opcion =scanner.nextLine();
        controlador.mostrarPedidosPendientes(opcion);

    }

    private void mostrarPedidosEnviados(){
        String opcion;
        scanner.nextLine();
        System.out.println("Si desea filtrar por cliente escriba su correo electronico, si quiere ver todos los pedidos enviados escriba T :");
        opcion =scanner.nextLine();
        controlador.mostrarPedidosEnviados(opcion);

    }
}