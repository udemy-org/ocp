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
 *         Recorriendo un ResultSet
 * 
 *         La tabla 10.6 del libro de referencia, muestra una guía completa de los métodos más importantes de la
 *         interfaz ResultSet.
 * 
 *         Entre otros, getString(), getInt(), getObject(), getTime(), getTimeStamp(), etc.
 * 
 *         Y La tabla 10.7 del libro de referencia, muestra una guía completa sobre la correspondencia entre tipos, por
 *         ejemplo:
 * 
 *         java.sql.Date se corresponde con java.time.LocalDate
 * 
 *         java.sql.Time se corresponde con java.time.LocalTime
 * 
 *         java.sql.TimeStamp se corresponde con java.time.LocalDateTime
 * 
 *         Revisa el código que acompaña a esta lección, responde a las preguntas planteadas y en definitiva, modifícalo
 *         para experimentar con los contenidos de esta lección.
 * 
 */
public class Leccion_10_06 {

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
	     * Un ResultSet navegable (scrollable) permite desplazarse por las filas de forma aleatoria.
	     * 
	     * El método absolute(int) mueve el cursor a una fila concreta. Pero ten en cuenta que absolute(0) mueve el
	     * cursor a la posición anterior a la primera fila, y que valores negativos empiezan a contar desde el final
	     * hacia atrás.
	     * 
	     * Si hay un método absolute(int) es porque también hay un método relative(int) que mueve el cursor hacia
	     * adelante o hacia atrás a partir de la posición actual.
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
