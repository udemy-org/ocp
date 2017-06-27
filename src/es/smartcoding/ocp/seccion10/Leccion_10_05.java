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
 *         El método executeUpdate(SQL) se utiliza con instrucciones SQL que
 *         modifican el estado de las tablas (DELETE, INSERT, UPDATE) y retorna
 *         el número de filas que se han modificado.
 * 
 *         El método executeQuery(SQL) en cambio se utiliza con instrucciones
 *         SQL de consulta (SELECT) y retorna un ResultSet con las filas que
 *         satisfacen los criterios de selección.
 * 
 *         Hay un tercer método, el método execute(SQL) que puede ejecutar tanto
 *         una consulta como una actualización. Retorna un valor lógico de true
 *         cuando ha ejecutado una consulta.
 * 
 *         En el mundo real, debes considerar la posibilidad de utilizar la
 *         clase PrepareStatement.
 * 
 *         Las ventajas son las siguientes:
 * 
 *         1. Velocidad
 *  
 *         2. Seguridad
 * 
 *         3. Legibilidad
 * 
 */
public class Leccion_10_05 {

	/**
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
