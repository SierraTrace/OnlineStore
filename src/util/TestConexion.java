import java.sql.Connection;
import util.ConexionBD;

public class TestConexion {
    public static void main(String[] args) {
        try (Connection conn = ConexionBD.getConexion()) {
            if (conn != null) {
                System.out.println("¡Conexión exitosa!");
            } else {
                System.out.println("No se pudo conectar.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

