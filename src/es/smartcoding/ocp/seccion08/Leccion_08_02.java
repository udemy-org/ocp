/**
 * 
 */
package es.smartcoding.ocp.seccion08;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author pep
 * 
 *         Entradas y salidas
 * 
 *         Introducción a los Streams (java.io)
 * 
 *         Los streams o flujos del paquete java.io nada tienen que ver con los Streams del paquete java.util.stream.
 *         Los streams de entradas y salidas se refieren al acceso a recursos de lectura (input) o escritura (output).
 * 
 *         Los streams de I/O son flujos de datos. Por ejemplo, cuando vemos una película en streaming, la película se
 *         va cargando poco a poco, habitualmente con un buffer que se va llenando para no tener cortes en la
 *         proyección.
 * 
 *         Los streams I/O pueden estar orientados a bytes (Stream) o a caracteres (Reader/Writer). Las FileInputStream
 *         y FileReader son ejemplos de streams que leen ficheros. La primera lee en forma de datos binarios, bytes, y
 *         la segunda en forma de caracteres o cadenas (String).
 * 
 *         En general, las clases del paqueta java.io tienen cierta simetria. Por ejemplo, si hay una clase
 *         FileInputStream, también debe haber una clase FileOutputStream. Las excepciones a la regla son las clases
 *         PrintWriter y PrintStream que no tienen contrapartida.
 * 
 *         Los streams o flujos de datos pueden ser de bajo o alto nivel. Los streams de bajo nivel conectan
 *         directamente con el origen de datos. Por ejemplo, la clase FileInputStream lee datos de byte en byte.
 * 
 *         Paralelamente, los flujos de alto nivel se construyen encima de otro stream mediante un wrapping o clase
 *         envolvente. Por ejemplo, el constructor de la clase BufferedReader toma como argumento un Reader como
 *         FileReader.
 * 
 *         Al envolver una clase de un stream de bajo nivel en una clase de stream de alto nivel, conseguimos
 *         funcionalidades adicionales así como transformaciones automáticas de los datos.
 * 
 *         De la misma manera, un stream de alto nivel puede llevar como argumento otro stream de alto nivel.
 * 
 *         La libería java.io define cuatro clases abstractas de las que descienden todos los streams: InputStream,
 *         OutputStream, Reader y Writer.
 * 
 *         Durante el examen, deberás tener especial cuidado en algunas preguntas trampa que mezclan clases
 *         Reader/Writer con InputStream/OutputStream.
 * 
 *         Consulta la tabla 8.2 del libro de referencia si quieres tener una relación completa de las clases de flujos.
 * 
 *         A la hora de trabajar con streams hay algunas procedimientos que vale la pena recordar:
 * 
 *         1. Cierre de un stream, en general usaremos un bloque try-with-resource que cierra los streams de forma
 *         automática.
 * 
 *         2. Vaciado (Flushing) de un stream, cuando se escribe a un OutputStream, el sistema operativo no nos
 *         garantiza que se haga la escritura inmediatamemte, ya que puede ser guardada en memoria cache. Java
 *         proporciona el método flush() que fuerza el volcado de un buffer. También, si se invoca el método close()
 *         escribe el contenido del stream.
 * 
 *         3. Marcado (Marking) de un stream, si una clase soporta el marcado, puede verificarse llamando al método
 *         markSupported(), podemos utilizar el método mark(int) en una posición para volver a ella con el método
 *         reset(). El método mark() tiene un parámetro entero que representa el valor límite de lectura hasta donde se
 *         puede hacer un reset().
 * 
 *         4. Salto (skipping) de datos, el método skip(n) nos permite saltar los siguientes n bytes de un flujo.
 * 
 *         Revisa el código que acompaña a esta lección, responde a las preguntas planteadas y en definitiva, modifícalo
 *         para experimentar con los contenidos de esta lección.
 *
 */
public class Leccion_08_02 {

    /**
     * Determina la salida del código siguiente.
     * 
     * @param args
     */
    public static void main(String[] args) {

	/*
	 * Esto es un ejemplo de un stream de alto nivel (BufferedReader) que envuelve a una clase de stream de bajo
	 * nivel (FileReader).
	 * 
	 * Demasiado código 'boiler plate' para cerrar el recurso.	
	 */
	BufferedReader bufferedReader = null;
	try {
	    bufferedReader = new BufferedReader(new FileReader("anyfile.txt"));
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
	finally {
	    if (bufferedReader != null) {
		try {
		    bufferedReader.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	}

	/*
	 * Bloque try-with-resources que se ocupa del cierre automático de un recurso.
	 */
	try (BufferedReader reader = new BufferedReader(new FileReader(new File("README.md")))) {
	    /*
	     * Si BufferedReader soporta marcas?
	     */
	    if (reader.markSupported()) {
		/*
		 * Marca un bloque de 16 bytes a lo largo de los cuales se puede hacer un reset, es decir, volver atrás.
		 * Después de este límite, reset puede fallar.
		 */
		reader.mark(16);
		System.out.print((char) reader.read());
		System.out.print((char) reader.read());
		System.out.print((char) reader.read());
		System.out.print((char) reader.read());
		System.out.print((char) reader.read());
		/*
		 * Reposiciona el puntero del fichero donde estaba.
		 */
		reader.reset();
		/*
		 * Salta dos bytes
		 */
		reader.skip(2);
		System.out.print((char) reader.read());
		System.out.print((char) reader.read());
		System.out.print((char) reader.read());
	    }
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

}
