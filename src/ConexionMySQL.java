import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {

    // Parámetros de conexión
    String url = "jdbc:mysql://localhost:3306/Producto3";
    String usuario = "root"; // Cambiar por tu usuario
    String contrasena = "contrasena"; // Cambiar por contraseña

    private Connection conexion;
    public Connection conectar() {
        try {
            return DriverManager.getConnection(url, usuario, contrasena);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    // Método para cerrar la conexión
    public void desconectar() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
