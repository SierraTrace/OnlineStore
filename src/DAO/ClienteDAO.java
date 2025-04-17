package DAO;

import modelo.cliente.Cliente;
import modelo.cliente.ClienteEstandar;
import modelo.cliente.ClientePremium;
import modelo.enums.TipoCliente;
import modelo.enums.TipoEstado;
import util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;
//import ConexionMySQL; TODO no consigo importar la clase ConexionMySQL

public class ClienteDAO implements IDao<Cliente> {
    // Connection conexion = null;

    @Override
    public Optional<Cliente> getById(String mail) {

        String sql = "SELECT * FROM cliente WHERE email = ?";

        try (Connection conexion = ConexionBD.getConexion()) {
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setString(1, mail);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Integer id = rs.getInt("idCliente");
                String email = rs.getString("email");
                String nif = rs.getString("nif");
                String nombre = rs.getString("nombre");
                String domicilio = rs.getString("domicilio");
                TipoCliente tipoCliente = TipoCliente.valueOf(rs.getString("tipoCliente"));
                Integer descuento = rs.getInt("descuento");
                Float cuotaAnual = rs.getFloat("cuotaAnual");

                if (tipoCliente == TipoCliente.PREMIUM) {
                    // Creamos un cliente de tipo Premium
                    ClientePremium clientePremium = new ClientePremium(
                            id, nombre, domicilio, nif, email, descuento, cuotaAnual);
                    return Optional.of(clientePremium);
                }
                if (tipoCliente == TipoCliente.ESTANDARD) {
                    // Creamos un cliente de tipo Estandar
                    ClienteEstandar clienteEstandar = new ClienteEstandar(
                            id, nombre, domicilio, nif, email);
                    return Optional.of(clienteEstandar);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al acceder a la BBDD con email: " + mail + " " + e.getMessage());
        }
        return Optional.empty();
    }


    @Override
    public ArrayList getAll() {

        String sql = "SELECT * FROM cliente";
        ArrayList<Cliente> listaClientes = new ArrayList<>();

        try (Connection conexion = ConexionBD.getConexion()) {
            PreparedStatement stmt = conexion.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("idCliente");
                String email = rs.getString("email");
                String nif = rs.getString("nif");
                String nombre = rs.getString("nombre");
                String domicilio = rs.getString("domicilio");
                TipoCliente tipoCliente = TipoCliente.valueOf(rs.getString("tipoCliente"));
                Integer descuento = rs.getInt("descuento");
                Float cuotaAnual = rs.getFloat("cuotaAnual");

                if (tipoCliente == TipoCliente.PREMIUM) {
                    // Creamos un cliente de tipo Premium
                    ClientePremium clientePremium = new ClientePremium(
                            id, nombre, domicilio, nif, email, descuento, cuotaAnual);
                    listaClientes.add(clientePremium);
                }
                if (tipoCliente == TipoCliente.ESTANDARD) {
                    // Creamos un cliente de tipo Estandar
                    ClienteEstandar clienteEstandar = new ClienteEstandar(
                            id, nombre, domicilio, nif, email);
                    listaClientes.add(clienteEstandar);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al acceder a la BBDD" + e.getMessage());
        }
        return listaClientes;
    }

    @Override
    public void save(Object o) {
        // TODO Trabajando...
        String sql = "INSERT INTO cliente (email, nif, nombre, domicilio, tipoCliente, descuento, cuotaAnual) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        if (o instanceof ClientePremium) {
            ClientePremium clientePremium = (ClientePremium) o;
            try (Connection conexion = ConexionBD.getConexion()) {
                conexion.setAutoCommit(false); // Deshabilitar autocommit en la BBDD

                try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
                    stmt.setString(1, clientePremium.getEmail());
                    stmt.setString(2, clientePremium.getNif());
                    stmt.setString(3, clientePremium.getNombre());
                    stmt.setString(4, clientePremium.getDomicilio());
                    stmt.setString(5, clientePremium.getTipoCliente().toString());
                    stmt.setFloat(6, clientePremium.getDescuento());
                    stmt.setFloat(7, clientePremium.getCuotaAnual());
                    stmt.executeUpdate();

                    conexion.commit(); // Confirmar cambios en BBDD
                } catch (SQLException e) {
                    conexion.rollback();
                    System.err.println("Aplicado rollback por error en BBDD, save cliente" + e.getMessage());
                }
            } catch (SQLException e) {
                System.err.println("Error al acceder a la BBDD " + e.getMessage());
            }
        }
        if (o instanceof ClienteEstandar) {
            ClienteEstandar clienteEstandar = (ClienteEstandar) o;
            try (Connection conexion = ConexionBD.getConexion()) {
                conexion.setAutoCommit(false); // Deshabilitar autocommit en la BBDD

                try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
                    stmt.setString(1, clienteEstandar.getEmail());
                    stmt.setString(2, clienteEstandar.getNif());
                    stmt.setString(3, clienteEstandar.getNombre());
                    stmt.setString(4, clienteEstandar.getDomicilio());
                    stmt.setString(5, clienteEstandar.getTipoCliente().toString());
                    stmt.setInt(6, 0);          // Descuento 0 por defecto
                    stmt.setFloat(7, 0.00F);    // CuotaAnual 0 por defecto
                    stmt.executeUpdate();

                    conexion.commit(); // Confirmar cambios en BBDD
                } catch (SQLException e) {
                    conexion.rollback();
                    System.err.println("Aplicado rollback por error en BBDD, save cliente" + e.getMessage());
                }
            } catch (SQLException e) {
                System.err.println("Error al acceder a la BBDD " + e.getMessage());
            }
        }
    }
} // Clase



    /*
    // TODO Los Clientes los buscamos por EMAIL implementar getById(String id)
    @Override
    public Optional get(Object o) {
        if (o instanceof Cliente) {
            Cliente cliente = (Cliente) o;
            Long id = cliente.getId();


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
*/
/*
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
*/
    /*
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
*/