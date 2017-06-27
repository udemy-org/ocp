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
 *         Recorriendo un ResultSet
 * 
 *         La tabla 10.6 del libro de referencia, muestra una guía completa de
 *         los métodos más importantes de la interfaz ResultSet.
 * 
 *         getString(), getInt(), getObject(), getTime(), getTimeStamp() ...
 * 
 *         La tabla 10.7 del libro de referencia, muestra una guía completa
 *         sobre la correspondencia entre tipos:
 * 
 *         java.sql.Date -> java.time.LocalDate
 * 
 *         java.sql.Time -> java.time.LocalTime
 * 
 *         java.sql.TimeStamp -> java.time.LocalDateTime
 * 
 * 
 * 
 */
public class Leccion_10_06 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/ocp";
		try (Connection conn = DriverManager.getConnection(url, "root", "admin");
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery("select descripcion from eventos")) {
			/*
			 * Un ResultSet navegable (scrollable) permite desplazarse por las
			 * filas de forma aleatoria.
			 * 
			 * El método absolute(int) mueve el cursor a una fila concreta. Pero
			 * ten en cuenta que absolute(0) mueve el cursor a la posición
			 * anterior a la primera fila, y que valores negativos empiezan a
			 * contar desde el final hacia atrás.
			 * 
			 * Si hay un método absolute(int) es porque también hay un método
			 * relative(int) que mueve el cursor hacia adelante o hacia atrás a
			 * partir de la posición actual.
			 * 
			 */
			rs.afterLast();
			System.out.println(rs.previous());
			System.out.println(rs.getString(1));
			System.out.println(rs.previous());
			System.out.println(rs.getString(1));
			System.out.println(rs.last());
			System.out.println(rs.first());
			rs.beforeFirst();
			// java.sql.SQLException: Before start of result set
			// System.out.println(rs.getString(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
