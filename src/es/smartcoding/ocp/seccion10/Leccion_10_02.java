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
 *         Interfaces de JDBC
 * 
 *         Durante el examen OCP debes conocer cuatro interfaces de la API JDBC:
 * 
 *         1. Driver, sirve para conectarse a la base de datos
 * 
 *         2. Connection, sabe cómo hablar con la base de datos
 * 
 *         3. Statement, sabe cómo ejecutar una orden SQL
 * 
 *         4. ResultSet, sabe cómo interpretar el resultado de una orden SQL
 * 
 */
public class Leccion_10_02 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String url = "jdbc:mysql://localhost:3306/ocp";
		try (Connection conn = DriverManager.getConnection(url, "root", "admin");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select descripcion from eventos")) {
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
			boolean isResultSet = stmt.execute("select nombre from eventos");
			if (isResultSet) {
				ResultSet rs1 = stmt.getResultSet();
				System.out.println("Se trata de una consulta.");
				while (rs1.next()) {
					System.out.println(rs1.getString(1));
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
