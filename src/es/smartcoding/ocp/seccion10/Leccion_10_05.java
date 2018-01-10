/**
 * 
 */
package es.smartcoding.ocp.seccion10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author pep
 * 
 *         JDBC
 * 
 *         Ejecutando un Statement
 * 
 *         El método executeUpdate(SQL) de la interfaz Statement, se utiliza con instrucciones SQL como DELETE, INSERT o
 *         UPDATE que modifican el estado de las tablas y retorna el número de filas que se han modificado.
 * 
 *         El método executeQuery(SQL) en cambio se utiliza con instrucciones SQL de consulta como SELECT y retorna un
 *         ResultSet con las filas que satisfacen los criterios de selección.
 * 
 *         Hay un tercer método, el método execute(SQL) que puede ejecutar tanto una consulta como una actualización.
 *         Retorna un valor lógico de true cuando ha ejecutado una consulta.
 * 
 *         En el mundo real, debes considerar la posibilidad de utilizar la interfaz PreparedStatement.
 * 
 *         Las ventajas son las siguientes:
 * 
 *         1. Mayor velocidad.
 * 
 *         2. Mayor seguridad
 * 
 *         3. Mayor legibilidad
 * 
 *         Revisa el código que acompaña a esta lección, responde a las preguntas planteadas y en definitiva, modifícalo
 *         para experimentar con los contenidos de esta lección.
 * 
 */
public class Leccion_10_05 {

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

	    /*
	     * Otra ventaja de la interfaz PreparedStatement es que permite incluir parámetros de sustitución en la
	     * orden SQL.
	     */
	    PreparedStatement ps = conn.prepareStatement("SELECT * FROM eventos WHERE nombre = ?");
	    ps.setString(1, "shut down");
	    if (ps.execute()) {
		ResultSet rs1 = ps.getResultSet();
		while (rs1.next()) {
		    System.out.println(rs1.getString(2));
		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}

    }

}
