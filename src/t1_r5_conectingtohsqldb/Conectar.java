package t1_r5_conectingtohsqldb;

import java.sql.*;

/**
 *
 * @author Iván Zambrana Naranjo
 */
public class Conectar {
   
    //Declaracion de varialbles para la conexion
    private String url;
    private Connection conexion;
    
    //Carga del driver
    public Conectar(String sgdb, String db) throws ClassNotFoundException, SQLException
    {
        Class.forName("org.hsqldb.jdbc.JDBCDriver");
        
        if(sgdb.equalsIgnoreCase("mysql"))
        {
            url = "jdbc:" + sgdb + "://localhost/" + db + "?serverTimezone=UTC";
            //this.conexion = DriverManager.getConnection(url, user, passw);
            System.out.println("La conexion con mysql ha sido un exito");
        }
        else if(sgdb.equalsIgnoreCase("oracle"))
        {
            url = "jdbc:" + sgdb + "://localhost/" + db + "?serverTimezone=UTC";
            //this.conexion = DriverManager.getConnection(url, user, passw);
            System.out.println("La conexion con oracle ha sido un exito");
        }
        else if(sgdb.equalsIgnoreCase("hsqldb"))
        {
            this.conexion = DriverManager.getConnection("jdbc:hsqldb:file:C:\\hsqldb\\data\\" + db + "\\" + db , "SA", "");
            System.out.println("La conexion con HSQLDB ha sido un exito");
        }
        else
        {
            System.out.println("-ERROR-Ningun SGBD elejido");
            System.out.println("Saliendo del programa...");
            System.exit(0);
        }
    }

    public Connection getConnection() 
    {
        return conexion;
    }
            
}
