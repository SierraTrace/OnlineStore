package DAO;

import modelo.Articulo;
import modelo.Pedido;
import modelo.cliente.Cliente;
import modelo.enums.TipoEstado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.sql.Timestamp;


public class PedidoDAO implements IDao {
    Connection conexion = null;
    @Override
    public Optional get(Object o) {
        if (o instanceof Pedido) {
            Pedido pedido = (Pedido) o;
            int numeroPedido = pedido.getNumeroPedido();
            try {
                //TODO sustituir por función conectar()
                conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Producto3", "root", "contrasena");

                String sqlPedido = "SELECT * FROM Pedido WHERE numeroPedido = ?";
                PreparedStatement sqlPedidoPreparada = conexion.prepareStatement(sqlPedido);
                sqlPedidoPreparada.setInt(1, numeroPedido);

                ResultSet resultadoPedido = sqlPedidoPreparada.executeQuery();

                if (resultadoPedido.next()) {
                    if(resultadoPedido.getInt("numeroPedido") == numeroPedido){

                        String sqlArticulo = "SELECT * FROM Articulo WHERE codigo_articulo = ?"; //Nueva consulta sql que busca el artículo correspondiente al pedido indicado.
                        PreparedStatement sqlArticuloPreparada = conexion.prepareStatement(sqlArticulo);
                        sqlArticuloPreparada.setString(1, resultadoPedido.getString("codigo_articulo"));

                        ResultSet resutadoArticulo = sqlArticuloPreparada.executeQuery();
                        Articulo articulo = new Articulo(
                                resutadoArticulo.getString("codigo_articulo"),
                                resutadoArticulo.getString("descripcion"),
                                resutadoArticulo.getFloat("precioVenta"),
                                resutadoArticulo.getFloat("gastosEnvio"),
                                resutadoArticulo.getInt("tiempoPreparacion")
                        );

                        String sqlCliente = "SELECT * FROM Cliente WHERE id = ?"; //Nueva consulta sql que busca el cliente correspondiente al pedido indicado.
                        PreparedStatement sqlClientePreparada = conexion.prepareStatement(sqlCliente);
                        sqlClientePreparada.setString(1, resultadoPedido.getString("id"));

                        ResultSet resultadoCliente = sqlClientePreparada.executeQuery();

                        String estado = resultadoPedido.getString("estado");
                        TipoEstado estadoPedido = TipoEstado.valueOf(estado.toUpperCase());

                        Cliente cliente = new Cliente(
                                resultadoCliente.getInt("id_cliente"),
                                resultadoCliente.getString("nombre"),
                                resultadoCliente.getString("domicilio"),
                                resultadoCliente.getString("nif"),
                                resultadoCliente.getString("email")
                        );

                        LocalDateTime fechaPedido = resultadoPedido.getObject("fechaPedido", LocalDateTime.class);//Guada en una variable tipo LocalCateTime el contenido tipo DateTime de la bbdd.

                        pedido = new Pedido(
                                resultadoPedido.getInt("numeroPedido"),
                                articulo,
                                resultadoPedido.getInt("cantidadArticulos"),
                                cliente,
                                fechaPedido,
                                estadoPedido
                        );
                        return Optional.of(pedido);
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (conexion != null && !conexion.isClosed()) {
                        conexion.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            System.out.println("El objeto no es del tipo Pedido");
        }
        return Optional.empty();
    }

    @Override
    public ArrayList getAll() {

        ArrayList<Pedido> pedidos = new ArrayList<>();
        try {
            //TODO sustituir por función conectar()
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Producto3", "root", "contrasena");

            //ConsultaSQL para seleccionar todos los pedidos
            String sqlPedido = "SELECT * FROM Pedido";
            PreparedStatement sqlPedidoPreparada = conexion.prepareStatement(sqlPedido);
            ResultSet resultadoPedido = sqlPedidoPreparada.executeQuery();

            while (resultadoPedido.next()) {

                //Consulta SQL para seleccionar el artículo del pedido actual.

                String sqlArticulo = "SELECT * FROM Articulo WHERE codigo_articulo = ?";
                PreparedStatement sqlArticuloPreparada = conexion.prepareStatement(sqlArticulo);
                sqlArticuloPreparada.setString(1, resultadoPedido.getString("codigo_articulo"));
                ResultSet resutadoArticulo = sqlArticuloPreparada.executeQuery();
                Articulo articulo = new Articulo(
                        resutadoArticulo.getString("codigo_articulo"),
                        resutadoArticulo.getString("descripcion"),
                        resutadoArticulo.getFloat("precioVenta"),
                        resutadoArticulo.getFloat("gastosEnvio"),
                        resutadoArticulo.getInt("tiempoPreparacion")
                );


                //Consulta SQL para seleccionar el cliente del pedido actual.

                String sqlCliente = "SELECT * FROM Cliente WHERE id = ?"; //Nueva consulta sql que busca el cliente correspondiente al pedido indicado.
                PreparedStatement sqlClientePreparada = conexion.prepareStatement(sqlCliente);
                sqlClientePreparada.setString(1, resultadoPedido.getString("id"));
                ResultSet resultadoCliente = sqlClientePreparada.executeQuery();
                //Lee el estado de la bbdd, lo guarda en un String y lo combiente a TipoEstado
                String estado = resultadoPedido.getString("estado");
                TipoEstado estadoPedido = TipoEstado.valueOf(estado.toUpperCase());
                Cliente cliente = new Cliente(
                        resultadoCliente.getInt("id_cliente"),
                        resultadoCliente.getString("nombre"),
                        resultadoCliente.getString("domicilio"),
                        resultadoCliente.getString("nif"),
                        resultadoCliente.getString("email")
                );

                LocalDateTime fechaPedido = resultadoPedido.getObject("fechaPedido", LocalDateTime.class);//Guada en una variable tipo LocalCateTime el contenido tipo DateTime de la bbdd.

                Pedido pedido = new Pedido(
                        resultadoPedido.getInt("numeroPedido"),
                        articulo,
                        resultadoPedido.getInt("cantidadArticulos"),
                        cliente,
                        fechaPedido,
                        estadoPedido
                );
                pedidos.add(pedido);
            }
            return pedidos;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (conexion != null && !conexion.isClosed()) {
                    conexion.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return pedidos;
    }

    @Override
    public void save(Object o) {
        if (o instanceof Pedido) {
            Pedido pedido = (Pedido) o;

            try {
                //TODO sustituir por función conectar()
                conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Producto3", "root", "contrasena");

                //Tengo que acceder al atributo codigoArticulo del artticulo de pedido

                //Consulta SQL para seleccionar el artículo del pedido actual.


                String sqlPedido = "INSERT INTO Pedido (numeroPedido, codigo_articulo, cantidadArticulos, id_cliente, precioTotal, fechaPedido, estado)" + //LA columna id es autogenerada, no hay que insertar nada.
                        "VALUES (?, ?, ?, ?, ?, ?, ?);";

                PreparedStatement sqlPedidoPreparada = conexion.prepareStatement(sqlPedido);
                sqlPedidoPreparada.setInt(1, pedido.getNumeroPedido());
                sqlPedidoPreparada.setString(2, pedido.getArticulo().getCodigoArticulo());
                sqlPedidoPreparada.setInt(3,pedido.getCantidadArticulos());
                sqlPedidoPreparada.setLong(4, pedido.getCliente().getId());
                sqlPedidoPreparada.setDouble(5, pedido.getPrecioTotal());
                sqlPedidoPreparada.setTimestamp(6, Timestamp.valueOf(pedido.getFechaPedido()));
                sqlPedidoPreparada.setString(7, pedido.getEstado().name());

                int filasAfectadas = sqlPedidoPreparada.executeUpdate();
                System.out.println(filasAfectadas + " filas guardadas.");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (conexion != null && !conexion.isClosed()) {
                        conexion.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            System.out.println("El objeto no es del tipo Cliente");
        }
    }

    @Override
    public void update(Object o) {
        if (o instanceof Cliente) {
            Pedido pedido = (Pedido) o;
            try {
                //TODO sustituir por función conectar()
                conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Producto3", "root", "contrasena");

                String sql = "UPDATE Pedido" +
                        "SET numeroPedido = ?, codigo_articulo = ?, cantidadArticulos = ?, id_cliente = ?, precioTotal = ?, fechaPedido = ?, estadp = ?" +
                        "WHERE id_cliente = ?";
                PreparedStatement sqlPedidoPreparada = conexion.prepareStatement(sql);
                sqlPedidoPreparada.setInt(1, pedido.getNumeroPedido());
                sqlPedidoPreparada.setString(2, pedido.getArticulo().getCodigoArticulo());
                sqlPedidoPreparada.setInt(3,pedido.getCantidadArticulos());
                sqlPedidoPreparada.setLong(4, pedido.getCliente().getId());
                sqlPedidoPreparada.setDouble(5, pedido.getPrecioTotal());
                sqlPedidoPreparada.setTimestamp(6, Timestamp.valueOf(pedido.getFechaPedido()));
                sqlPedidoPreparada.setString(7, pedido.getEstado().name());

                int filasActualizadas = sqlPedidoPreparada.executeUpdate();
                System.out.println(filasActualizadas + " filas actualizadas.");

            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (conexion != null && !conexion.isClosed()) {
                        conexion.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            System.out.println("El objeto no es del tipo Cliente");
        }

    }

    @Override
    public void delete(Object o) {
        if (o instanceof Cliente) {
            Pedido pedido = (Pedido) o;
            int numeroPedido = pedido.getNumeroPedido();

            try {
                //TODO sustituir por función conectar()
                conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Producto3", "root", "contrasena");

                String sqlPedido = "DELETE FROM Pedido WHERE numeroPedido = ?";
                PreparedStatement sqlPedidoPreparada = conexion.prepareStatement(sqlPedido);
                sqlPedidoPreparada.setLong(1, numeroPedido);

                if (sqlPedidoPreparada.executeUpdate() > 0) {
                    System.out.println("Cliente eliminado correctamente.");
                } else {
                    System.out.println("No se encontró ningún cliente con ese ID.");
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (conexion != null && !conexion.isClosed()) {
                        conexion.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            System.out.println("El objeto no es del tipo Pedido");
        }
    }
}
