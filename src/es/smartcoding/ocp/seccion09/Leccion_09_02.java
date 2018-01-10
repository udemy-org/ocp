/**
 * 
 */
package es.smartcoding.ocp.seccion09;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pep
 * 
 *         NIO.2 New Input/Output
 * 
 *         Las clases Paths y Files
 * 
 *         Es importante destacar que un objeto Path no es un fichero sino una representación de una localización dentro
 *         del sistema de ficheros. Por este motivo, la mayoría de operaciones sobre Paths puede llevarse a cabo tanto
 *         si el fichero/directorio realmente existe o no. Por ejemplo, recuperar el directorio raíz de una Path no
 *         requiere que el fichero exista, mientras que si invocamos al método toRealPath() el fichero debe existir o se
 *         lanzará una excepción comprobada.
 * 
 *         Revisa el código que acompaña a esta lección, responde a las preguntas planteadas y en definitiva, modifícalo
 *         para experimentar con los contenidos de esta lección.
 *
 */
public class Leccion_09_02 {

    /**
     * Determina la salida del código siguiente.
     * 
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
	     * Muchos de los métodos que interaccionan con ficheros y directorios llevan parámetros adicionales en forma
	     * de varargs.
	     * 
	     * Si bien para el examen OCP no es necesario memorizarlos todos, sí que deberías se capaz de reconocer qué
	     * significado tienene.
	     * 
	     * La tabla 9.1. del libro oficial es un buen punto de referencia de estos parámetros opcionales.
	     * 
	     * Esta tabla omite las clases enum por simplicidad pero puedes consultarlas en el Javadoc.
	     * 
	     * java.nio.file.LinkOption.NOFOLLOW_LINKS
	     * 
	     * java.nio.file.FileVisitOption.FOLLOW_LINKS
	     * 
	     * java.nio.file.StandardCopyOption.ATOMIC_MOVE, COPY_ATTRIBUTES, REPLACE_EXISTING
	     */
	    System.out.println(path1.toRealPath(LinkOption.NOFOLLOW_LINKS));
	} catch (IOException e) {
	    e.printStackTrace();
	}

	/*
	 * Muchos de los métodos de la interfaz Path transforma el camino de alguna manera y retornan un nuevo objeto
	 * Path, lo que permite encadenar llamadas de esta forma:
	 * 
	 */
	System.out.println(Paths.get("/bin/../ocp.log").getParent().normalize().toAbsolutePath());

	/*
	 * Un objeto Path esta formado por diferentes componentes pero el elemento raíz no se incluye en la lista de
	 * nombres:
	 */
	Path path2 = Paths.get("/user/pep/file.txt");
	System.out.println("Nombre del path completo: " + path2);
	for (int i = 0; i < path2.getNameCount(); i++) {
	    System.out.println("El elemento " + i + " es: " + path2.getName(i));
	}

	/*
	 * Los método getFileName(), getParent() y getRoot() retornan un objeto Path. El método getRoot() retorna null
	 * si la ruta es relativa.
	 */
	Path path3 = Paths.get("/user/pep/file.txt");
	System.out.println(path3.getFileName() + " " + path3.getParent() + " " + path3.getRoot());

	/*
	 * Los métodos isAbsolute() y toAbsolutePath() que convierte un path relativo en absoluto añadiendo el
	 * directorio de trabajo actual.
	 * 
	 * Recuerda que si el path ya representa una path absoluto entonces el método toAbsolutePath() retorna un nuevo
	 * Path con el mismo valor.
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
	 * El símbolo '.' se refiere al directorio actual y el símbolo '..' al directorio padre.
	 * 
	 */
	Path path6 = Paths.get("./../file1.txt");
	System.out.println(path6.toAbsolutePath());

	/*
	 * El método relativize(Path) sirve para construir el path relativo desde un path a otro.
	 * 
	 * Si ambos caminos son relativos, entonces el método relativize() computa los caminos como si estuvieran en el
	 * mismo directorio de trabajo.
	 */
	Path path7 = Paths.get("fish.txt");
	Path path8 = Paths.get("birds.txt");
	System.out.println(path7.relativize(path8));
	System.out.println(path8.relativize(path7));

	/*
	 * Pero si ambos son paths absolutos entonces el método relativize() computa el path relativo del primero al
	 * segundo.
	 * 
	 * Aunque pueda parecer que el método relativize(Path) acceda al sistema de archivos, en realidad no lo hace.
	 * 
	 * Lo que sí que es necesario es que ambos paths sean o bien relativos o bien absolutos, en caso contrario, se
	 * lanza una excepción de tipo IllegalArgumentException en tiempo de ejecución.
	 * 
	 * Además en sistemas de archivos basados en Windows, ambos path deben tener la misma unidad de disco.
	 */
	Path path9 = Paths.get("/usr/home/pep");
	Path path10 = Paths.get("/bin");
	Path path11 = Paths.get("file1.txt");
	System.out.println(path9.relativize(path10));
	System.out.println(path10.relativize(path9));
	/*
	 * Lanza una excepcion de tipo IllegalArgumentException
	 */
	// System.out.println(path10.relativize(path11));

	/*
	 * El método resolve(Path) sirve para crear un nuevo Path uniendo un Path con el Path actual. Es decir, la
	 * instrucción a.resolve(b) donde a y b son Paths junta ambos Path en uno.
	 * 
	 * Ni el método relativize() ni el método resolve() resuleven nombres simbólicos como '.' o '..'.
	 * 
	 */
	final Path path12 = Paths.get("/temporal/../bin");
	final Path path13 = Paths.get("sbin");
	System.out.println(path12.resolve(path13));
	/*
	 * Pero si ambos Paths son absolutos el primer Path se ignora y el resultado es el segundo Path.
	 */
	final Path path14 = Paths.get("/temporal/../bin");
	final Path path15 = Paths.get("/sbin");
	System.out.println(path14.resolve(path15));

	/*
	 * Como hemos visto, los métodos anteriores relativize() y resolve() no interpretan nombres simbólicos como '.'
	 * o '..'. El método normalize() en cambio sí que lo hace. Además fíjate como Java no comprueba que existan
	 * estos Paths en el sistema de archivos.
	 */
	Path path16 = Paths.get("/data");
	Path path17 = Paths.get("/user/home");
	Path relativePath = path16.relativize(path17);
	System.out.println(path16.resolve(relativePath));
	System.out.println(path16.resolve(relativePath).normalize());

	/*
	 * Con el método toRealPath(Path) todo cambia. Java comprueba que el Path exista dentro del sistema de archivos
	 * ya que devuelve una referencia a un Path real dentro del sistema de archivos.
	 * 
	 * Es similar al método toAbsolutePath() en el sentido que convierte un camino relativo a absoluto y además
	 * verifica que el fichero referenciado existe lanzando una excepción de tipo IOException si no fuera el caso.
	 * 
	 * Es el único método que soporta la opción NOFOLLOW_LINKS.
	 * 
	 * El método toRealPath(Path) lleva a cabo pasos adicionales como eliminar elementos redundantes del Path, es
	 * decir, llama a normalize() implícitamente.
	 * 
	 */
	Path path18 = Paths.get("ocp.log");
	try {
	    System.out.println(path18.toRealPath(LinkOption.NOFOLLOW_LINKS));
	    /*
	     * Directorio de trabajo actual
	     */
	    System.out.println(Paths.get(".").toRealPath());
	} catch (IOException e) {
	    e.printStackTrace();
	}

	/*
	 * Los nombres de los métodos de la clase Files de NIO.2 son mucho más directos que los nombres de la clase
	 * java.io.File. Por ejemplo, el método mkdir() de la clase File ahora es createDirectory() en la clase Files.
	 */
	System.out.println(Files.exists(Paths.get("target")));
	/*
	 * Dos ficheros pueden ser iguales en términos de nombres pero tener distintos contenidos.
	 * 
	 * El método isSameFile primero llama al método equals(), si son dos cadenas iguales retorna true
	 * automáticamente. Ni siquiera mira que existan. Pero si el método equals() retorna false entonces localiza los
	 * ficheros y verica que sean el mismo fichero o directorio. En este caso, si no existe alguno de ellos lanza
	 * una excepcion de tipo IOException.
	 * 
	 */
	try {
	    System.out.println(Files.isSameFile(Paths.get("ocpx.log"), Paths.get("ocpx.log")));
	    System.out.println(Files.isSameFile(Paths.get("ocp.log"), Paths.get("target/../ocp.log")));
	} catch (IOException e) {
	    e.printStackTrace();
	}

	/*
	 * Creación de directorios con los métodos createDirectory() y createDirectories(). El método createDirectory()
	 * lanzará una excepción de tipo IOException si el directorio ya existe. El método createDirectories() crea un
	 * directorio y los directorios intermedios que haga falta.
	 * 
	 */
	try {
	    // Files.createDirectory(Paths.get("temporal"));
	} catch (Exception e) {
	    e.printStackTrace();
	}

	/*
	 * A diferencia de la clase java.io.File, la clase Files de NIO.2 proporciona una versión sobrecargada del
	 * método copy() que copia ficheros y directorios dentro del sistema de archivos.
	 * 
	 * El comportamiento es el habitual, el método copy() lanza una excepción si el fichero/directorio de origen no
	 * existe o si el fichero/directorio de destino ya existen excepto si se utiliza la opción
	 * StandardCopyOption.REPLACE_EXISTING.
	 * 
	 */
	try {
	    Files.copy(Paths.get("temporal"), Paths.get("temporal2"), StandardCopyOption.COPY_ATTRIBUTES);
	    Files.copy(Paths.get("/temporal/system.log"), Paths.get("/temporal2/system.log"),
		    StandardCopyOption.ATOMIC_MOVE);
	} catch (IOException e) {
	    e.printStackTrace();
	}

	/*
	 * En este ejemplo, se utiliza una versión sobrecargada del método copy() con streams de java.io.
	 */
	try (InputStream is = new FileInputStream("ocp.log"); OutputStream out = new FileOutputStream(".")) {
	    Files.copy(is, Paths.get("."));
	    Files.copy(Paths.get("ocp.log"), out);
	} catch (IOException e) {
	    e.printStackTrace();
	}

	/*
	 * El método Files.move(Path, Path) mueve o renombra un fichero/directorio. El método move() puede aplicarse a
	 * directorios con contenido pero siempre en la misma unidad, de lo contrario se lanzaría una excepción de tipo
	 * DirectoryNotEmptyException. Mientras que si se trata de directorio vacíos pueden estar en diferentes
	 * unidades.
	 * 
	 */
	try {
	    Files.move(Paths.get("temporal"), Paths.get("temp"));
	    Files.move(Paths.get("README.back"), Paths.get("README2.back"));
	} catch (IOException e) {
	    e.printStackTrace();
	}

	/*
	 * El método Files.delete(Path) elimina un fichero o directorio vacío dentro del sistema de archivos. Puede
	 * lanzar una excepción comprobada de tipo IOException en algunos contextos. Si el path representa una
	 * directorio no vacío se lanzará una excepción de tipo DirectoryNotEmptyException.
	 * 
	 * Si el path es un enlace simbólico, se eliminará el enlace simbólico pero no el fichero real.
	 * 
	 * El método Files.deleteIfExists(Path) es idéntico al método Files.delete(Path) excepto en que no lanzará una
	 * excepción si el fichero/directorio no existe pero sí si el directorio no esta vacío.
	 */
	try {
	    Files.delete(Paths.get("temporal2"));
	    Files.deleteIfExists(Paths.get("temporal"));
	} catch (IOException e) {
	    e.printStackTrace();
	}

	/*
	 * La API NIO.2 también proporciona métodos para operar con flujos (streams) de java.io.
	 */
	Path path19 = Paths.get("ocp.log");
	try (BufferedReader reader = Files.newBufferedReader(path19, Charset.defaultCharset())) {
	    String line = null;
	    while ((line = reader.readLine()) != null)
		System.out.println(line);
	} catch (IOException e) {
	    e.printStackTrace();
	}

	Path path20 = Paths.get("temporal/file1.txt");
	List<String> data = new ArrayList();
	try (BufferedWriter writer = Files.newBufferedWriter(path20, Charset.forName("UTF-16"))) {
	    writer.write("Java NIO.2 Rocks!!!");
	} catch (IOException e) {
	    e.printStackTrace();
	}

	/*
	 * Por último, el método Files.readAllLines() lee todas la líneas de un fichero de texto y retorna el resultado
	 * como una lista de cadenas.
	 * 
	 * Importante tener en cuenta aqui que si el fichero es demasiado grande puede llegar a lanzarse una excepción
	 * de tipo OutOfMemoryError.
	 */
	Path path21 = Paths.get("temporal/file1.txt");
	try {
	    final List<String> lines = Files.readAllLines(path21);
	    for (String line : lines) {
		System.out.println(line);
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

}
