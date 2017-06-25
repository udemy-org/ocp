/**
 * 
 */
package es.smartcoding.ocp.seccion09;

import java.io.IOException;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author pep
 * 
 *         NIO.2 New Input/Output
 * 
 *         Las clases Paths y Files
 * 
 *         Es importante destacar que un objeto Path no es un fichero sino una
 *         representación de una localización dentro del sistema de ficheros.
 *         Por este motivo, la mayoría de operaciones sobre Paths puede llevarse
 *         a cabo tanto si el fichero/directorio realmente existe o no. Por
 *         ejemplo, recuperar el directorio raíz de una Path no requiere que el
 *         fichero exista, mientras que si invocamos al método toRealPath() el
 *         fichero ha de existir o se lanzará una excepción comprobada.
 *
 */
public class Leccion_09_02 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/*
		 * Si el fichero no existiera, se lanzaría una excepción de tipo:
		 * 
		 * java.nio.file.NoSuchFileException
		 * 
		 */
		Path path1 = Paths.get("ocp.log");
		try {
			/*
			 * Muchos de los métodos que enteraccionan con ficheros y
			 * directorios llevan parámetros adicionales en forma de varargs.
			 * 
			 * Si bien para el examen OCP no es necesario memorizarlos todos, sí
			 * que deberías se capaz de reconocer qué significado tienene.
			 * 
			 * La tabla 9.1. del libro oficial es un buen punto de referencia de
			 * estos parámetros opcionales.
			 * 
			 * Esta tabla omite las clases enum por simplicidad pero puedes
			 * consultarlas en el Javadoc.
			 * 
			 * java.nio.file.LinkOption.NOFOLLOW_LINKS
			 * 
			 * java.nio.file.FileVisitOption.FOLLOW_LINKS
			 * 
			 * java.nio.file.StandardCopyOption.ATOMIC_MOVE, COPY_ATTRIBUTES,
			 * REPLACE_EXISTING
			 */
			System.out.println(path1.toRealPath(LinkOption.NOFOLLOW_LINKS));
		} catch (IOException e) {
			e.printStackTrace();
		}

		/*
		 * Muchos de los métodos de la interfaz Path transforma el camino de
		 * alguna manera y retornan un nuevo objeto Path, lo que permite
		 * encadenar llamadas de esta forma:
		 * 
		 */
		System.out.println(Paths.get("/bin/../ocp.log").getParent().normalize().toAbsolutePath());

		/*
		 * Un objeto Path esta formado por diferentes componentes pero el
		 * elemento raíz no se incluye en la lista de nombres:
		 */
		Path path2 = Paths.get("/user/pep/file.txt");
		System.out.println("Nombre del path completo: " + path2);
		for (int i = 0; i < path2.getNameCount(); i++) {
			System.out.println("El elemento " + i + " es: " + path2.getName(i));
		}
	}

}
