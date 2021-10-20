package t1_r5_conectingtohsqldb;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;
/**
 *
 * @author Iván Zambrana Naranjo
 */
public class T1_R5_ConectingToHSQLDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String sql;
        Statement exConsulta = null;
        ResultSet resultado = null;
        try {
            //Cargamos el driver
            Class.forName("org.hsqldb.jdbc.JDBCDriver");

            //Creamos conexión
            Connection conexion = DriverManager.getConnection("jdbc:hsqldb:file:instituto/instituto", "SA", "");
            
            if(conexion != null)
            {
                System.out.println("Conexion realizada con exito");
                
                sql = "select * from alumno";
                exConsulta = conexion.createStatement();
                        resultado = exConsulta.executeQuery(sql);
                        while(resultado.next())
                        {
                            System.out.println("\n****************************");
                            System.out.println("DNI: " + resultado.getInt(1)
                            + "\nnombre: " + resultado.getString(2)
                            + "\napellidos: " + resultado.getString(3)
                            + "\ndireccion: " + resultado.getString(4)
                            + "\ntfno: " + resultado.getString(5)
                            + "\nnota_expediente: " + resultado.getInt(6));
                        }
            }
            else
            {
                System.out.println("Problema al crear la conexion");
                
            }
        } catch (Exception e) {
            System.out.println("Error de conexion");
        }
    }
    
}
