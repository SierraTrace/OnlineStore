package DAO;

import modelo.cliente.Cliente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Optional;
//import ConexionMySQL; TODO no consigo importar la clase ConexionMySQL

public class ClienteDAO implements IDao {
    Connection conexion = null;


    // TODO Pendiente implementar
    @Override
    public Optional getById(String id) {
        return Optional.empty();
    }

    // TODO Los Clientes los buscamos por EMAIL implementar getById(String id)
    @Override
    public Optional get(Object o) {
        if (o instanceof Cliente) {
            Cliente cliente = (Cliente) o;
            Long id = cliente.getId();

            // TODO verificar. Verificar el que?
            try {
                //TODO sustituir por función conectar()
                conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Producto3", "root", "contrasena");

                String sql = "SELECT * FROM Cliente WHERE nombre = ?";
                PreparedStatement sqlOriginal = conexion.prepareStatement(sql);
                sqlOriginal.setLong(1, id);

                ResultSet resultado = sqlOriginal.executeQuery();

                if (resultado.next()) {
                    if(resultado.getLong("id_cliente") == id){
                        cliente = new Cliente(
                                resultado.getLong("id_cliente"),
                                resultado.getString("nombre"),
                                resultado.getString("domicilio"),
                                resultado.getString("nif"),
                                resultado.getString("email")
                        );
                        return Optional.ofNullable(cliente);  // Permite el retorno de null
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
            System.out.println("El objeto no es del tipo Cliente");
        }

        return Optional.empty();
    }


    @Override
    public ArrayList getAll() { //Lo he cambiado por un Arraylist por sus ventajas.

        ArrayList<Cliente> clientes = new ArrayList<>();
        try {
            //TODO sustituir por función conectar()
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Producto3", "root", "contrasena");

            String sql = "SELECT * FROM Cliente";
            PreparedStatement sqlOriginal = conexion.prepareStatement(sql);//TODO aqui creo que no hace falta porque no hay que meter datos pero si lo quito no funciona.
            ResultSet resultado = sqlOriginal.executeQuery();

            while (resultado.next()) {
                Cliente cliente = new Cliente(
                        resultado.getLong("id_cliente"),
                        resultado.getString("nombre"),
                        resultado.getString("domicilio"),
                        resultado.getString("nif"),
                        resultado.getString("email")
                );
                clientes.add(cliente);//No estoy segura de si los tiene que guardar porque realmente ya están en la bbdd, maybe solo tiene que enseñarlos.
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
        return clientes;
    }

    @Override
    public void save(Object o) {
        if (o instanceof Cliente) {
            Cliente cliente = (Cliente) o;
            try {
                //TODO sustituir por función conectar()
                conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Producto3", "root", "contrasena");

                String sql = "INSERT INTO Cliente (nombre, domicilio, nif, email)" + //LA columna id es autogenerada, no hay que insertar nada.
                             "VALUES (?, ?, ?, ?);";
                PreparedStatement sqlOriginal = conexion.prepareStatement(sql);
                sqlOriginal.setString(1, cliente.getNombre());
                sqlOriginal.setString(2, cliente.getDomicilio());
                sqlOriginal.setString(3, cliente.getNif());
                sqlOriginal.setString(4, cliente.getEmail());

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
            System.out.println("El objeto no es del tipo Cliente");
        }
    }

    @Override
    public void update(Object o) {
        if (o instanceof Cliente) {
            Cliente cliente = (Cliente) o;
            try {
                //TODO sustituir por función conectar()
                conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Producto3", "root", "contrasena");

                String sql = "UPDATE Cliente" +
                             "SET nombre = ?, domicilio = ?, nif = ?, email = ?" +
                             "WHERE id_cliente = ?";
                PreparedStatement sqlOriginal = conexion.prepareStatement(sql);
                sqlOriginal.setString(1, cliente.getNombre());//TODO Pedir datos al usuario?
                sqlOriginal.setString(2, cliente.getDomicilio());
                sqlOriginal.setString(3, cliente.getNif());
                sqlOriginal.setString(4, cliente.getEmail());
                sqlOriginal.setLong(5, cliente.getId());


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
            System.out.println("El objeto no es del tipo Cliente");
        }
    }

    @Override
    public void delete(Object o) {
        if (o instanceof Cliente) {
            Cliente cliente = (Cliente) o;
            long id = cliente.getId();

            try {
                //TODO sustituir por función conectar()
                conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Producto3", "root", "contrasena");

                String sql = "DELETE FROM Cliente WHERE id_cliente = ?";
                PreparedStatement sqlOriginal = conexion.prepareStatement(sql);
                sqlOriginal.setLong(1, id);

                if (sqlOriginal.executeUpdate() > 0) {
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
            System.out.println("El objeto no es del tipo Cliente");
        }
    }
}
