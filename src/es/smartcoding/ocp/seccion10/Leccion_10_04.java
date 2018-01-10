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
 *         Creando un Statement
 * 
 *         A la hora de crear un Statement se puede personalizar con dos parámetros, uno que denota el tipo de acceso y
 *         otro que denota el tipo de concurrencia de la conexión.
 *         
 *         Como tipo de acceso existen tres posibilidades:
 * 
 *         1. ResultSet.TYPE_FORWARD_ONLY, es el parámetro por defecto que sólo permite avanzar en una dirección.
 * 
 *         2. ResultSet.TYPE_SCROLL_INSENSITIVE, se trata de una fotografía estática del estado de la tabla.
 * 
 *         3. ResultSet.TYPE_SCROLL_SENSITIVE, se trata de una fotografía dinámica del estado de la tabla (la mayoría de
 *         las bases de datos no lo soportan).
 *         
 *         Y como tipo de concurrencia dos:
 * 
 *         1. ResultSet.CONCUR_READ_ONLY, es el parámetro por defecto de concurrencia. Significa que no se puede actualizar
 *         el ResultSet.
 * 
 *         2. ResultSet.CONCUR_UPDATABLE, este parámetro sí que permite actualizar el ResultSet pero en general, no esta
 *         soportado por las bases de datos.
 * 
 *         Revisa el código que acompaña a esta lección, responde a las preguntas planteadas y en definitiva, modifícalo
 *         para experimentar con los contenidos de esta lección.
 *
 */
public class Leccion_10_04 {

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
		Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)) {
	    boolean isResultSet = stmt.execute("select nombre from eventos");
	    if (isResultSet) {
		ResultSet rs = stmt.getResultSet();
		System.out.println("Se trata de una consulta.");
		while (rs.next()) {
		    System.out.println(rs.getString(1));
		}
	    } else {
		int result = stmt.getUpdateCount();
		System.out.println("Se trata de una actualización.");
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}

    }

}
