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
 * @author jmendez
 * 
 *         JDBC
 * 
 *         Gestión de excepciones
 * 
 *         Hasta ahora hemos vivido en un mundo perfecto donde no nos hemos
 *         preocupado en exceso de las posibles excepciones que se pueden lanzar
 *         trabajando con JDBC.
 * 
 *         El método getMessage() retorna una cadena con el problema que ha
 *         encontrado. El método getSQLState() retorna un código de error SQL.
 *         Finalmente el método getErrorCode() retorna un código de error
 *         específico de tu base de datos.
 * 
 */
public class Leccion_10_08 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/ocp";
		try (Connection conn = DriverManager.getConnection(url, "root", "admin");
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery("select descripcion from eventos")) {
			while (rs.next()) {
				System.out.println(rs.getString(2));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getSQLState());
			System.out.println(e.getErrorCode());
		}

	}
}
