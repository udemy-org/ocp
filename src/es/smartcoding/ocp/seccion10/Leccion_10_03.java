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
 *         Conexión a una base de datos
 * 
 *         Este código muestra una secuencia típica de conexión a una base de
 *         datos. Una URL de conexión está formada por el prefijo 'jdbc:'
 *         seguida del nombre del fabricante y a continuación el host con un
 *         puerto opcional para acabar con el nombre de la base de datos.
 * 
 *         Hay dos maneras de conseguir un objeto Connection:
 * 
 *         1. DriverManager, es la clase que aparece en el examen OCP. Tiene una
 *         método de factorio getConnection()
 * 
 *         2. DataSource, sigue el patrón factory
 * 
 *         En un escenario real, deberíamos usar un DataSource en vez de la
 *         clase DriverManager, porque mantiene un pool de conexiones
 *         reutilizables.
 * 
 *         Si utilizas un driver <= 3.0 debes cargarlo mediante la orden
 *         Class.forName("org.postgress.Driver") en el caso del driver de
 *         PostgreSQL. Pero para drivers superiores la carga es automática.
 * 
 */
public class Leccion_10_03 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/ocp";
		try (Connection conn = DriverManager.getConnection(url, "root", "admin");
				Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery("select descripcion from eventos")) {
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
