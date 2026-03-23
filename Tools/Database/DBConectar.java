
package Tools.Database;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Takamaru Senshi
 */
public class DBConectar {
    static String bd = "Equipos";
    static String login = "postgres";
    static String password = "senshi";
    static String url = "jdbc:postgresql://localhost/"+bd;
    static String mensaje = "";
    
    Connection conexion = null;
    
    /**
     * Genera la conexión con la BD
     */
    public DBConectar(){
        try{
            Class.forName("org.postgresql.Driver");
            conexion = DriverManager.getConnection(url,login,password);
            if(conexion!=null){
                //System.out.println("Connection to "+bd+ " established...");
            }
        }
        catch(HeadlessException | ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null,"Failed at: Connect to "+bd+" ...","Warning",0);
            System.out.println(e);
        }
    }
    
    public static String getMensaje() {
        return mensaje;
    }
    
    public static void setMensaje(String mensaje) {
        DBConectar.mensaje = mensaje;
    }
    
    public Connection getConexion(){
        return conexion;
    }
    
    public void desconectar() throws SQLException{
        conexion = null;
    }
}