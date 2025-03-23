import controlador.Controlador;
import modelo.*;
import modelo.Cliente.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vista.Vista;

import static org.junit.jupiter.api.Assertions.*;

class ControladorTest {

    private Modelo modelo;
    private Vista vista;
    private Controlador controlador;

    @BeforeEach
    void setUp() {
        modelo = new Modelo();
        vista = new Vista();
        controlador = new Controlador(modelo, vista);
    }

    @Test
    void testAddArticle() {
        controlador.addArticulo("A001", "Articulo Test", 100.0f, 5.0f, 2);
        assertTrue(modelo.existeArticulo("A001"));
    }

    @Test
    void testFindItem() {
        modelo.addArticulo(new Articulo("A002", "Articulo de prueba", 50.0f, 2.0f, 1));
        assertTrue(controlador.existeArticulo("A002"));
    }

    @Test
    void testAddCliente() {
        controlador.addCliente("Juan", "Calle 123", "12345678A", "juan@email.com");
        assertTrue(controlador.findCliente("juan@email.com"));
    }

    @Test
    void testFindCliente() {
        modelo.addCliente(new Cliente("Ana", "Calle 456", "87654321B", "ana@email.com"));
        assertTrue(controlador.findCliente("ana@email.com"));
    }

    @Test
    void testAddPedido() {
        modelo.addArticulo(new Articulo("A003", "Articulo Pedido", 30.0f, 1.0f, 2));
        modelo.addCliente(new Cliente("Luis", "Calle 789", "111222333C", "luis@email.com"));
        int numeroPedidoAntes = modelo.generarProximoPedido();
        controlador.addPedido("A003", 3, "luis@email.com");
        assertEquals(numeroPedidoAntes, modelo.getPedidos().size());
    }
}
