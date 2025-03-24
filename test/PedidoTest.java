import modelo.Articulo;
import modelo.Cliente.Cliente;
import modelo.Pedido;
import modelo.enums.TipoEstado;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

class PedidoTest{

    @Test
    void testActualizarEstadoPreparacionCumpleTiempo() {

        //Articulo con 10 min de preparacion
        Articulo articulo = new Articulo("A111", "Artículo de prueba", 100.0f, 5.0f, 10);
        Cliente cliente = new Cliente("Maria", "Su casa", "11111111A", "maria@email.com");

        //Pedido con fecha de hace 15 min
        LocalDateTime fechaPedido = LocalDateTime.now().minusMinutes(15);
        Pedido pedido = new Pedido(1, articulo, 2, cliente);
        pedido.setFechaPedido(fechaPedido); //Introduccion manual de la fecha en Pedido

        // Llamada al metodo que vamos a testear
        pedido.actualizarEstadoPreparacion();

        //Verificar cambio de estado a "ENVIADO"
        assertEquals(TipoEstado.ENVIADO, pedido.getEstado());
    }

    @Test
    void testActualizarEstadoPreparacionNoCumpleTiempo() {

        //Artículo con 20 min de preparacion
        Articulo articulo = new Articulo("A111", "Artículo de prueba", 100.0f, 5.0f, 20);
        Cliente cliente = new Cliente("Maria", "Su casa", "11111111A", "maria@email.com");

        //Pedido con fecha de hace 10 min
        LocalDateTime fechaPedido = LocalDateTime.now().minusMinutes(10);
        Pedido pedido = new Pedido(1, articulo, 2, cliente);
        pedido.setFechaPedido(fechaPedido); // Introduccion manual de la fecha en pedido

        // Llamada al metodo que vamos a testear
        pedido.actualizarEstadoPreparacion();

        //Verificar que el estado se queda como "PENDIENTE"
        assertEquals(TipoEstado.PENDIENTE, pedido.getEstado());
    }

    
}