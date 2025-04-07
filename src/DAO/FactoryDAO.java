package DAO;

// Factory nos permite clasificar nuestros DAOS y tener una única forma para crear Objetos DAO

public class FactoryDAO {

    public static IDao getIDAO(String daoType) {
        switch (daoType) {
            case "CLIENTE":
                return new ClienteDAO();
            case "ARTICULO":
                return new ArticuloDAO();
            case "PEDIDO":
                return new PedidoDAO();
            default:
                throw new IllegalArgumentException("Tipo de DAO Incorrecto");
        }
    }
}