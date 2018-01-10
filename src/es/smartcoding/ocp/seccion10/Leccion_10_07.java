/**
 * 
 */
package es.smartcoding.ocp.seccion10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author pep
 * 
 *         JDBC
 * 
 *         Cerrando recursos de bases de datos
 * 
 *         La API JDBC ahora implementa la interfaz Closeable, con lo que lo más práctico es utilizar un bloque
 *         try-with-resources a la hora de abrir recursos.
 * 
 *         Aunque se reconoce como un buen hábito cerrar los recursos que ya no se necesitan, no es estrictamente
 *         necesario.
 * 
 *         Cuando cerramos un objeto Connection, también se cierran los objetos Statement y ResultSet que puedan tener
 *         asociados. Y si cerramos un objeto Statement también se cierran sus objetos ResultSet.
 * 
 *         Además, JDBC cierra un objeto ResultSet cuando se ejecuta otra orden desde el mismo objeto Statement.
 * 
 *         Revisa el código que acompaña a esta lección, responde a las preguntas planteadas y en definitiva, modifícalo
 *         para experimentar con los contenidos de esta lección.
 *
 */
public class Leccion_10_07 {

    /**
     * Determina la salida del código siguiente. 
     * 
     * Nota: Es necesario que crees una conexión a tu base de datos y que adaptes la URL, nombre usuario y clave.
     * 
     * @param args
     */
    public static void main(String[] args) {
	String url = "jdbc:mysql://localhost:3306/ocp";
	try (Connection conn = DriverManager.getConnection(url, "root", "admin");
		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = stmt.executeQuery("select descripcion from eventos")) {
	    /*
	     * Esta orden hace que se cierre rs automáticamente
	     */
	    ResultSet rs2 = stmt.executeQuery("select count(*) from eventos");
	    if (rs2.next()) {
		System.out.println(rs2.getInt(1));
	    }
	    /*
	     * java.sql.SQLException: Operation not allowed after ResultSet closed
	     */
	    // System.out.println(rs.next());
	} catch (SQLException e) {
	    e.printStackTrace();
	}

    }

}
