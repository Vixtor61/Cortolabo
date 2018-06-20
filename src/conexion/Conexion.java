package conexion;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vixtor61<00198117@uca.edu.sv>
 */
public class Conexion {
    
    private String user, pass, driver, url;
    
    private Connection conect;
    
    public static Conexion instance;
    
    public synchronized static Conexion conectar(){
        if (instance == null) {
            return new Conexion();
        }
        return instance;
    }
    
    private Conexion() {
        cargarCredenciales();

        try {

            Class.forName(this.driver);
            conect = (Connection) DriverManager.getConnection(this.url, this.user, this.pass);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cargarCredenciales() {
        user = "root";
        pass = "";
        driver = "com.mysql.jdbc.Driver";
        url = "jdbc:mysql://localhost:3306/movie";
    }

    public Connection getCnx() {
        return conect;
    }

    public void cerrarConexion() {
        instance = null;
    }
}
