package DAO;

public class FactoryDAO {

    public static IDao getIDAO(String daoType) {
        switch (daoType) {

        }
    }
}


/*

public class DAOFactory {
    public static DAO getDAO(String daoType) {
        if (daoType.equalsIgnoreCase("USER")) {
            return new UserDAO();
        } else if (daoType.equalsIgnoreCase("PRODUCT")) {
            return new ProductDAO();
        }
        throw new IllegalArgumentException("Invalid DAO type");
    }
}

 */