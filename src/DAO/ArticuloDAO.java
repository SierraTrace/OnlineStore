package DAO;

import modelo.Articulo;
import modelo.cliente.Cliente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// TODO, pendiente de revisar e implementar.

public class ArticuloDAO implements IDao {
    Connection conexion = null;
    @Override
    public Optional get(Object o) {
        if (o instanceof Articulo) {
            Articulo articulo = (Articulo) o;
            String codigo_articulo = articulo.getCodigoArticulo();
            try {
                //TODO sustituir por función conectar()
                conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Producto3", "root", "contrasena");

                String sql = "SELECT * FROM Articulo WHERE codigo_articulo = ?";
                PreparedStatement sqlOriginal = conexion.prepareStatement(sql);
                sqlOriginal.setString(1, codigo_articulo);

                ResultSet resultado = sqlOriginal.executeQuery();

                if (resultado.next()) {
                    if(resultado.getString("codigo_articulo") == codigo_articulo){
                        articulo = new Articulo(
                                resultado.getString("codigo_articulo"),
                                resultado.getString("descripcion"),
                                resultado.getFloat("precioVenta"),
                                resultado.getFloat("gastosEnvio"),
                                resultado.getInt("tiempoPreparación")
                        );
                        return Optional.of(articulo);
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
            System.out.println("El objeto no es del tipo Articulo");
        }
        return Optional.empty();
    }

    @Override
    public List getAll() {
        ArrayList<Articulo> articulos = new ArrayList<>();
        try {
            //TODO sustituir por función conectar()
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Producto3", "root", "contrasena");

            String sql = "SELECT * FROM Articulo";
            PreparedStatement sqlOriginal = conexion.prepareStatement(sql);//TODO aqui creo que no hace falta porque no hay que meter datos pero si lo quito no funciona.
            ResultSet resultado = sqlOriginal.executeQuery();

            while (resultado.next()) {
                Articulo articulo = new Articulo(
                        resultado.getString("codigo_articulo"),
                        resultado.getString("descripcion"),
                        resultado.getFloat("precioVenta"),
                        resultado.getFloat("gastosEnvio"),
                        resultado.getInt("tiempoPreparación")
                );

                articulos.add(articulo);//No estoy segura de si los tiene que guardar porque realmente ya están en la bbdd, maybe solo tiene que enseñarlos.
                System.out.println(
                        "Articulo Nº" + resultado.getString("codigo_articulo") +
                                ". Descripcion: " + articulo.getDescripcion()
                                + ". Precio de venta: " + articulo.getPrecioVenta()
                                + ". Gastos de envio: " + articulo.getGastosEnvio()
                                + ". Tiempo de preparacion: " + articulo.getTiempoPreparacion());
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
        return articulos;
    }

    @Override
    public void save(Object o) {
        if (o instanceof Articulo) {
            Articulo articulo = (Articulo) o;
            try {
                //TODO sustituir por función conectar()
                conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Producto3", "root", "contrasena");

                String sql = "INSERT INTO Articulo (codigo_articulo, descripcion, precioVenta, tiempoPreparacion)" + //LA columna id es autogenerada, no hay que insertar nada.
                        "VALUES (?, ?, ?, ?);";
                PreparedStatement sqlOriginal = conexion.prepareStatement(sql);
                sqlOriginal.setString(1, articulo.getDescripcion());
                sqlOriginal.setFloat(2, articulo.getPrecioVenta());
                sqlOriginal.setFloat(3, articulo.getGastosEnvio());
                sqlOriginal.setInt(4, articulo.getTiempoPreparacion());

                int filasAfectadas = sqlOriginal.executeUpdate();
                System.out.println(filasAfectadas + " filas modificadas.");
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
            System.out.println("El objeto no es del tipo Articulo");
        }

    }

    @Override
    public void update(Object o) {
        if (o instanceof Cliente) {
            Articulo articulo = (Articulo) o;
            try {
                //TODO sustituir por función conectar()
                conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Producto3", "root", "contrasena");

                String sql = "UPDATE Articulo" +
                        "SET descripcion = ?, precioVenta = ?, gastosEnvio = ?, tiempoPreparacion = ?" +
                        "WHERE codigo_articulo = ?";
                PreparedStatement sqlOriginal = conexion.prepareStatement(sql);
                sqlOriginal.setString(1, articulo.getDescripcion());
                sqlOriginal.setFloat(2, articulo.getPrecioVenta());
                sqlOriginal.setFloat(3, articulo.getGastosEnvio());
                sqlOriginal.setInt(4, articulo.getTiempoPreparacion());
                sqlOriginal.setString(5, articulo.getCodigoArticulo());


                int resultado = sqlOriginal.executeUpdate();
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
            System.out.println("El objeto no es del tipo Articulo");
        }
    }

    @Override
    public void delete(Object o) {
        if (o instanceof Articulo) {
            Articulo articulo = (Articulo) o;
            String codigo_articulo = articulo.getCodigoArticulo();

            try {
                //TODO sustituir por función conectar()
                conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Producto3", "root", "contrasena");

                String sql = "DELETE FROM Articulo WHERE codigo_articulo = ?";
                PreparedStatement sqlOriginal = conexion.prepareStatement(sql);
                sqlOriginal.setString(1, codigo_articulo);

                if (sqlOriginal.executeUpdate() > 0) {
                    System.out.println("Articulo eliminado correctamente.");
                } else {
                    System.out.println("No se encontró ningún articulo con ese codigo.");
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
            System.out.println("El objeto no es del tipo Articulo");
        }
    }
}
