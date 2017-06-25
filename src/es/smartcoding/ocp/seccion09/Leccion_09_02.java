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

		/*
		 * Los método getFileName(), getParent() y getRoot() retornan un objeto
		 * Path. El método getRoot() retorna null si la ruta es relativa.
		 */
		Path path3 = Paths.get("/user/pep/file.txt");
		System.out.println(path3.getFileName() + " " + path3.getParent() + " " + path3.getRoot());

		/*
		 * Los métodos isAbsolute() y toAbsolutePath() que convierte un path
		 * relativo en absoluto añadiendo el directorio de trabajo actual.
		 * 
		 * Recuerda que si el path ya representa una path absoluto entonces el
		 * método toAbsolutePath() retorna un nuevo Path con el mismo valor.
		 */
		Path path4 = Paths.get("user/pep/file.txt");
		System.out.println(path4.isAbsolute());
		System.out.println(path4.toAbsolutePath());

		/*
		 * El método subpath() se utiliza para extraer paths de un path
		 */
		Path path5 = Paths.get("/Users/pep/git/ocp/user/pep/file.txt");
		System.out.println("Path: " + path5);
		System.out.println("Subpath de 0 a 3: " + path5.subpath(0, 3));
		System.out.println("Subpath de 1 a 3: " + path5.subpath(1, 3));
		System.out.println("Subpath de 1 a 2: " + path5.subpath(1, 2));
		// java.lang.IllegalArgumentException
		// System.out.println("Subpath de 0 a 9: " + path5.subpath(0, 9));

		/*
		 * Símbolos de path: '.' y '..'
		 * 
		 * El símbolo '.' se refiere al directorio actual y el símbolo '..' al
		 * directorio padre.
		 * 
		 */
		Path path6 = Paths.get("./../file1.txt");
		System.out.println(path6.toAbsolutePath());

		/*
		 * El método relativize(Path) sirve para construir el path relativo
		 * desde un path a otro.
		 * 
		 * Si ambos caminos son relativos, entonces el método relativize()
		 * computa los caminos como si estuvieran en el mismo directorio de
		 * trabajo.
		 */
		Path path7 = Paths.get("fish.txt");
		Path path8 = Paths.get("birds.txt");
		System.out.println(path7.relativize(path8));
		System.out.println(path8.relativize(path7));

		/*
		 * Pero si ambos son paths absolutos entonces el método relativize()
		 * computa el path relativo del primero al segundo.
		 * 
		 * Aunque pueda parecer que el método relativize(Path) acceda al sistema
		 * de archivos, en realidad no lo hace.
		 * 
		 * Lo que sí que es necesario es que ambos paths sean o bien relativos o
		 * bien absolutos, en caso contrario, se lanza una excepción de tipo
		 * IllegalArgumentException en tiempo de ejecución.
		 * 
		 * Además en sistemas de archivos basados en Windows, ambos path deben
		 * tener la misma unidad de disco.
		 */
		Path path9 = Paths.get("/usr/home/pep");
		Path path10 = Paths.get("//bin");
		Path path11 = Paths.get("file1.txt");
		System.out.println(path9.relativize(path10));
		System.out.println(path10.relativize(path9));
		/*
		 * Lanza una excepcion de tipo IllegalArgumentException
		 */
		// System.out.println(path10.relativize(path11));

	}

}
