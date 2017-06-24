/**
 * 
 */
package es.smartcoding.ocp.seccion09;

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
 */
public class Leccion_09_01 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
