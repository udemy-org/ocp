/**
 * 
 */
package es.smartcoding.ocp.seccion09;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author pep
 * 
 *         NIO.2 New Input/Output
 * 
 *         Introducción
 * 
 *         NIO es el acrónimo de Non-blocking Input/Output API, aunque también
 *         se la conoce como la New Input/Output API y está presente desde Java
 *         1.4. Esta API permite hacer mucho más que la API java.io y en muchos
 *         casos la sustituye.
 * 
 *         La interfaz Path se utiliza para operar a nivel de fichero y
 *         directorio mientras que la clase Files sirve para leer, borrar,
 *         copiar, mover y gestionar los metadatos de un fichero o directorio.
 * 
 *         Algunos de los métodos añadidos en Java 8 se basan en la API Streams
 *         para hacer operaciones complejas con muy poco código.
 * 
 *         La API NIO.2 está presente desde Java 7 y es el reemplazo natural de
 *         la clase java.io.File. Si bien la API NIO no tuvo excesivo caladado
 *         entre los programadores y no forma parte del examen OCP, la API NIO.2
 *         sí que está presente.
 * 
 *         El objetivo de la API NIO.2 es el de proporcionar una API más
 *         intuitiva y rica que la anterior donde la interfaz java.nio.file.Path
 *         tiene un papel central. Un objeto Path representa una ruta en la
 *         jerarquía del sistema de archivos, algo así como la clase
 *         java.io.File. Ambas pueden apuntar a un fichero o directorio como un
 *         camino absoluto o relativo, pero la clase Path además contiene
 *         soporte para enlaces simbólicos, punteros a otros ficheros o
 *         directorios.
 * 
 *         NIO.2 hace buen uso del patrón Factory: una clase que proporciona
 *         métodos estáticos para crear instancias de otra clase. La clase Paths
 *         (con 's' al final) es justamente eso.
 * 
 *         NIO.2 también incluye clases de ayuda como java.nio.file.Files
 *         diseñadas para trabajar con instancias de la clase Path. Las clases
 *         de ayuda son similiares a las clases factorias en el sentido que
 *         también proporcionan un conjunto de métodos estáticos que operan con
 *         una clase concreta, Path en este caso.
 * 
 *         La figura 9.1 del libro de referencia te ayudará a entender cómo
 *         interactuan las clases de la API NIO.2 con su entorno.
 * 
 */
public class Leccion_09_01 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/*
		 * Crear un objeto Path es verdaderamente simple. En este ejemplo, tanto
		 * path1 como path2 se refieren a la misma ruta relativa (porque no
		 * empieza con '/')
		 * 
		 */
		Path path1 = Paths.get("temporal/ocp.log");
		Path path2 = Paths.get("temporal", "ocp.log");
		URI uri1 = path1.toUri();
		System.out.println(path1);
		System.out.println(path2);
		System.out.println(uri1);

		/*
		 * El método get() de la clase Paths también toma una URI como
		 * argumento.
		 */
		try {
			Path path3 = Paths.get(new URI("file:/temporal/ocp.log"));
			URI uri2 = path3.toUri();
			System.out.println(path3);
			System.out.println(uri2);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		Path path4 = FileSystems.getDefault().getPath("temporal/ocp.log");
		System.out.println(path4);

		/*
		 * Es posible construir un Path de otro sistema de ficheros
		 */
		FileSystem fileSystem;
		try {
			fileSystem = FileSystems.getFileSystem(new URI("file:/"));
			Path path5 = fileSystem.getPath("ocp.log");
			System.out.println(path5);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		/*
		 * Finalmente, la clase File proporciona el método toPath() para
		 * convertir un File en un Pah.
		 */
		Path path5 = new File("temporal/ocp.log").toPath();
		System.out.println(path5);

	}

}
