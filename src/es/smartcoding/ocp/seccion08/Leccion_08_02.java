/**
 * 
 */
package es.smartcoding.ocp.seccion08;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * @author pep
 * 
 *         Entradas y salidas
 * 
 *         Introducción a los Streams (java.io)
 * 
 *         Los streams o flujos del paquete java.io nada tienen que ver con los
 *         Streams del paquete java.util.stream. Los streams de entradas y
 *         salidas se refieren al acceso a recursos de lectura (input) o
 *         escritura (output).
 * 
 *         Los streams de I/O son flujos de datos. Por ejemplo, cuando vemos una
 *         película en streaming, la película se va cargando poco a poco,
 *         habitualmente con un buffer que se va llenando para no tener cortes
 *         en la proyección.
 * 
 *         Los streams I/O pueden estar orientados a bytes (Stream) o a
 *         caracteres (Reader/Writer). Las FileInputStream y FileReader son
 *         ejemplos de streams que leen ficheros. La primera lee en forma de
 *         datos binarios, bytes, y la segunda en forma de caracteres o cadenas
 *         (String).
 * 
 *         En general, las clases del paqueta java.io tienen cierta simetria.
 *         Por ejemplo, si hay una clase FileInputStream, también debe haber una
 *         clase FileOutputStream. Las excepciones a la regla son las clases
 *         PrintWriter y PrintStream que no tienen contrapartida.
 * 
 *         Los streams o flujos de datos pueden ser de bajo o alto nivel. Los
 *         streams de bajo nivel conectan directamente con el origen de datos.
 *         Por ejemplo, la clase FileInputStream lee datos de byte en byte.
 * 
 *         Paralelamente, los flujos de alto nivel se construyen encima de otro
 *         stream mediante un wrapping o clase envolvente. Por ejemplo, el
 *         constructor de la clase BufferedReader toma como argumento un Reader
 *         como FileReader.
 * 
 *         Al envolver una clase de un stream de bajo nivel en una clase de
 *         stream de alto nivel, conseguimos funcionalidades adicionales así
 *         como transformaciones automáticas de los datos.
 * 
 *         De la misma manera, un stream de alto nivel puede llevar como
 *         argumento otro stream de alto nivel.
 * 
 *         La libería java.io define cuatro clases abstractas de las que
 *         descienden todos los streams: InputStream, OutputStream, Reader y
 *         Writer.
 * 
 *         Durante el examen OCP, deberás tener especial cuidado en algunas
 *         preguntas trampa que mezclan clases Reader/Writer con
 *         InputStream/OutputStream.
 * 
 *         Consulta la tabla 8.2 del libro de referencia si quieres tener una
 *         relación completa de las clases de flujos.
 *
 */
public class Leccion_08_02 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/*
		 * Esto es un ejemplo de un stream de alto nivel (BufferedReader) que
		 * envuelve a una clase de stream de bajo nivel (FileReader).
		 */
		try {
			BufferedReader reader = new BufferedReader(new FileReader("anyfile.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
