/**
 * 
 */
package es.smartcoding.ocp.seccion09;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.stream.Stream;

/**
 * @author pep
 * 
 *         NIO.2 New Input/Output
 * 
 *         Nuevos métodos orientados a flujos (Stream)
 * 
 *         Antes de la aparición de Java 8, las técnicas usadas para llevar a
 *         cabo operaciones complejas en NIO.2 como la búsqueda de un fichero en
 *         un sistema de archivos, a menudo implicaba escribir una clase entera
 *         para hacer una tarea sencilla.
 * 
 *         Cuando se publicó Java 8, se añadieron nuevos métodos a NIO.2 que se
 *         basan en Streams, con lo que a menudo es posible hacer tareas
 *         complejas con apenas unas líneas de código.
 * 
 *         Si deseamos buscar un fichero pueden usarse dos estrategias:
 * 
 *         1. En profundidad, primero se recorre la rama de más a la izquierda
 *         desde la raiz hasta la última hoja y así se va procediendo hacia la
 *         derecha.
 * 
 *         2. En anchura, Primero se reccore el primer nivel de nodos, a
 *         continuación el siguiente y así hasta el final.
 * 
 *         La API Streams utiliza la primera estrategia con una profundidad
 *         máxima de Integer.MAX_VALUE.
 * 
 *         El método Files.walk(Path) retorna un objeto Stream<Path> que
 *         atraviesa un directorio en profundidad.
 * 
 */
public class Leccion_09_04 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * Para evitar bucles infinitos, el método walk() no sigue enlaces
		 * simbólicos por defecto.
		 * 
		 * Pero si fuera necesario se puede utilizar la opción FOLLOW_LINKS,
		 * pero en ese caso es recomendable fijar una valor máximo de
		 * profundidad.
		 * 
		 */
		Path path1 = Paths.get(".");
		try {
			Files.walk(path1, Integer.MAX_VALUE, FileVisitOption.FOLLOW_LINKS)
					.filter(p -> p.toString().endsWith(".java")).forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}

		/*
		 * A la hora de buscar un fichero o conjunto de ficheros en un sistema
		 * de ficheros podemos utilizar el método Files.find(Path, int,
		 * BiPredicate<Path, BasicFileAttributes>) que se parece en cierta
		 * medida al método walk().
		 */
		long dateFilter = 1420070400000l;
		try {
			Stream<Path> stream = Files.find(path1, 10,
					(Path path, BasicFileAttributes attributes) -> path.toString().endsWith(".txt")
							&& attributes.lastModifiedTime().toMillis() > dateFilter);
			stream.forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * Pero si lo que queremos es un simple listado al estilo de
		 * java.io.File.listFiles() podemos usar el método Files.list(Path).
		 */
		try {
			Files.list(path1).filter(p -> !Files.isDirectory(p)).map(p -> p.toAbsolutePath())
					.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}

		/*
		 * Si recuerdas, el método Files.readAllLines(Path) retornaba un objeto
		 * List<String>. Esto en la mayoría de los casos puede ser lo más
		 * conveniente. Pero qué pasa si se trata de un fichero demasiado
		 * grande? Podemos tener una excepción OutOfMemoryError. Afortunadamente
		 * la API NIO.2 proporciona el método Files.lines(Path) que retorna un
		 * objeto Stream<String>, de manera que sólo un pequeña porción del
		 * flujo ocupa memoria en un momento dado.
		 * 
		 * Durante el examen OCP debes estar familiarizado con ambos métodos. En
		 * ocasiones puede ser confuso porque el metodo forEach() está presente
		 * tanto en la clase Stream como Collections.
		 * 
		 * Por ejemplo, ambas líneas de código compilan y se ejecutan sin
		 * problemas. Pero la primera lee todo el fichero en memoria mientras
		 * que la segunda lee la líneas perezosamente (lazily), es decir en el
		 * momento en que se necesitan y tan sólo mantiene una pequeña porción
		 * en memória.
		 * 
		 * Files.readAllLines(Paths.get("ocp.log")).forEach(System.out::println)
		 * ;
		 * 
		 * Files.lines(Paths.get("ocp.log")).forEach(System.out::println);
		 */
		try {
			Files.lines(Paths.get("ocp.log")).forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
