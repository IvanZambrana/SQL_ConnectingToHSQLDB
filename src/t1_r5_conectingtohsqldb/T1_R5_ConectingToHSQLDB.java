package t1_r5_conectingtohsqldb;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;
import java.util.Scanner;
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
        Statement exConsulta;
        PreparedStatement exConsultaP;
        ResultSet resultado;
        Scanner sc = new Scanner(System.in);
        int option, codA;
        try {
            System.out.println("Inserta base de datos (ejemplo instituto): ");
            String db = sc.nextLine();
            boolean exit = false;
            String dniProfe;
            //Cargamos el driver
            Class.forName("org.hsqldb.jdbc.JDBCDriver");

            //Creamos conexión
            Connection conexion = DriverManager.getConnection("jdbc:hsqldb:file:C:\\hsqldb\\data\\" + db + "\\" + db , "SA", "");
            
            //Creacion del menu
            while(!exit) {
                System.out.println("\n\n\n-------------------------------");
                System.out.println("---------MENU HSQLDB-----------");
                System.out.println("-------------------------------");
                System.out.println("1. Mostrar todos los datos de los alumnos.");
                System.out.println("2. Mostrar todos los datos de los profesores.");
                System.out.println("3. Mostrar asignaturas impartidas por un profesor.");
                System.out.println("4. Mostrar alumnos matriculados en una asignatura.");
                System.out.println("5. Salir");
                
                //Captura opcion insertada por teclado
                System.out.println("Elija una opcion: ");
                option = sc.nextInt();
                sc.nextLine();
                //Manejo de opciones
                switch(option){
                    case 1:
                        try {
                            System.out.println("--MOSTRAR ALUMNOS--");
                            sql = "select * from alumno";
                            exConsulta = conexion.createStatement();
                            resultado = exConsulta.executeQuery(sql);
                            while(resultado.next())
                            {
                                System.out.println("\n****************************");
                                System.out.println("DNI: " + resultado.getString(1)
                                + "\nnombre: " + resultado.getString(2)
                                + "\napellidos: " + resultado.getString(3)
                                + "\ndireccion: " + resultado.getString(4)
                                + "\ntfno: " + resultado.getString(5)
                                + "\nnota_expediente: " + resultado.getInt(6));
                            }
                            System.out.println("\n****************************");
                        } catch (Exception e)
                        {
                            System.out.println("-ERROR-La base de datos alumno no existe");
                        }
                        
                    break;
                    case 2:
                        try {
                            System.out.println("--MOSTRAR PROFESORES--");
                            sql = "select * from profesor";
                            exConsulta = conexion.createStatement();
                            resultado = exConsulta.executeQuery(sql);
                            while(resultado.next())
                            {
                                System.out.println("\n****************************");
                                System.out.println("DNI: " + resultado.getString(1)
                                + "\nnombre: " + resultado.getString(2)
                                + "\napellidos: " + resultado.getString(3)
                                + "\ntitulacion: " + resultado.getString(4));
                            }
                            System.out.println("\n****************************");
                        } catch(Exception e)
                        {
                                System.out.println("-ERROR-La base de datos alumno no existe");
                        }
                    break;
                    case 3:
                        System.out.println("--MOSTRAR ASIGNATURAS IMPARTIDAS POR PROFESOR--");
                        System.out.println("Inserte DNI del profesor: ");
                        dniProfe = sc.nextLine();
                        try{
                            sql = "select cod_asig, nombre from asignatura "
                                    + "join imparte on(imparte.cod_asig = asignatura.cod_asig) "
                                    + "join profesor on(profesor.dni_prof = imparte.dni_prof) "
                                    + "where dni_prof = '" + dniProfe + "'";
                            exConsulta = conexion.createStatement();
                            resultado = exConsulta.executeQuery(sql);
                        
                            while(resultado.next())
                            {
                                System.out.println("\n****************************");
                                System.out.println("Codigo de asignatura: " + resultado.getString(1)
                                + "\nNombre de asignatura: " + resultado.getString(2));
                            }
                            
                            System.out.println("\n****************************");
                        } catch (Exception e)
                        {
                            System.out.println("Error al mostrar asignaturas impartidas por el profesor.");
                        }
                    break;
                    case 4:
                        System.out.println("--MOSTRAR ALUMNOS MATRICULADOS EN UNA ASIGNATURA--");
                        try{
                            System.out.println("Inserte codigo de asignatura: ");
                            codA = sc.nextInt();
                            //sc.nextLine();
                            sql = "select dni, nombre, apellidos from alumno  "
                                    + "join matricula on(matricula.dni = alumno.dni) "
                                    + "join asignatura on(matricula.cod_asig = asignatura.cod_asig) "
                                    + "where cod_asig = " + codA;
                            exConsulta = conexion.createStatement();
                            resultado = exConsulta.executeQuery(sql);
                        
                            while(resultado.next())
                            {
                                System.out.println("\n****************************");
                                System.out.println("DNI: " + resultado.getString(1)
                                + "\nNombre del alumno: " + resultado.getString(2)
                                + "\nApellidos del alumno: " + resultado.getString(3));
                            }
                            
                            System.out.println("\n****************************");
                        } catch (Exception e)
                        {
                            System.out.println("Error al mostrar alumnos matriculados en la asignatura.");
                        }
                    break;
                    case 5:
                        System.out.println("Saliendo...");
                        exit=true;
                    break;
                    default:
                        System.out.println("Elija un numero entre 1 y 5.");
                }
            }
               
        } catch (ClassNotFoundException | SQLException cnfsql) {
            System.out.println("Error de conexion");
        }
    }
    
}
