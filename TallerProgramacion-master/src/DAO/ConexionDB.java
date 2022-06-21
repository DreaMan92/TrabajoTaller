package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConexionDB {
	
	 
    public String driver = "oracle.jdbc.driver.OracleDriver";




    public String hostname_port_db = "192.168.100.210:1521:xe";


    // Ruta de nuestra base de datos (desactivamos el uso de SSL con "?useSSL=false")
    public String url = "jdbc:oracle:thin:@" + hostname_port_db ;

    // Nombre de usuario


    public Connection conectarOracle(String username,String password) {
        Connection conn = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

}