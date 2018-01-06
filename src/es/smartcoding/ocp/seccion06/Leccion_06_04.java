/**
 * 
 */
package es.smartcoding.ocp.seccion06;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author pep
 * 
 *         Excepciones y aserciones
 * 
 *         Try-With-Resources
 * 
 *         Así como los bloques multi-catch nos permiten escribir código sin duplicación, todavía tenemos un problema de
 *         duplicación en los bloques finally.
 * 
 *         Recuerda que es importante cerrar recursos cuando se ha acabado de trabajar con ellos. Pero como ves en este
 *         fragmento de código puede llegar a requerir demasiado esfuerzo.
 * 
 *         La solución es utilizar un bloque try-with-resources, que no sólo reduce el código dramáticamente, sino que
 *         además es más sencillo y fácil de mantener.
 * 
 *         La clave está en que ahora prácticamente todos los recursos implementan la interfaz Autocloseable, lo que
 *         permite a Java cerrar los recursos de forma automática.
 * 
 *         Revisa el código que acompaña a esta lección, responde a las preguntas planteadas y en definitiva, modifícalo
 *         para experimentar con los contenidos de esta lección.
 *
 */
public class Leccion_06_04 {

    /**
     * @param args
     */
    public static void main(String[] args) {

	/*
	 * Recuerdas los problemas que se derivan de trabajar con recursos?
	 * 
	 * Los recursos, tales como ficheros, deben cerrarse, pero el método close() a su vez puede lanzar una excepción
	 * comprobada de tipo IOException.
	 */
	Path path1 = Paths.get("anyfile_in.txt");
	Path path2 = Paths.get("anyfile_out.txt");
	BufferedReader in = null;
	BufferedWriter out = null;
	try {
	    in = Files.newBufferedReader(path1);
	    out = Files.newBufferedWriter(path2);
	    out.write(in.readLine());
	} catch (IOException e) {
	} finally {
	    if (in != null)
		try {
		    in.close();
		} catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	    if (out != null)
		try {
		    out.close();
		} catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	}

	/*
	 * La solución es utilizar un bloque try-with-resources, que no sólo reduce el código dramaticamente, sino que
	 * además es más sencillo y mantenible.
	 * 
	 * Los recursos se cierran automáticamente al acabar el bloque try. Existe un bloque finally implícito que se
	 * ejecuta antes que cualquier otro bloque catch/finally. Por lo tanto el bloque finally se hace incesario.
	 * 
	 * El bloque try-with-resources trabaja con clases que implementan la interfaz Closeable. La interfaz Closeable
	 * declara un método
	 * 
	 * public void close() throws Exception;
	 * 
	 * Aunque en la práctica, es aconsejable que se lance una excepción más específica.
	 * 
	 * Java también recomienda que el método close() se idempotent, es decir, que por muchas veces que se invoque no
	 * tiene mayores consecuencias.
	 * 
	 * Cuando se introdujo la interfaz AutoCloseable en Java 7, ya existía otra interfaz similar, la interfaz
	 * Closeable que hacía lo que los diseñadores buscaban pero con algunas diferencias:
	 * 
	 * 1. La interfaz Closeable solamente podía lanzar una excepción de tipo IOException.
	 * 
	 * 2. Closeable requiere que las implementaciones sean idempotent.
	 * 
	 * Por lo tanto, las alternativas eran, o bien se modificaba la interfaz Closeable, lo que no rompía con el
	 * espíritu de compatibilidad de Java, o bien se creaba una nueva. Lógicamente, al final se optó por la segunda
	 * que era más conservadora.
	 * 
	 */
	try (BufferedReader br = Files.newBufferedReader(path1); BufferedWriter bw = Files.newBufferedWriter(path2)) {
	    // trabajar con los ficheros...
	} catch (IOException e) {
	    e.printStackTrace();
	}

	/*
	 * Pero, qué pasa si el cuerpo de un bloque try-with-resource lanza una excepción y el método close() de la
	 * interfaz AutoCloseable también.
	 * 
	 * En este caso se genera lo que se conoce como una excepción suprimida.
	 * 
	 * Un caso diferente es cuando tanto el bloque try como el bloque finally lanzan una excepción. Esto ya estaba
	 * permitido mucho antes de Java 7 y si recuerdas, la excepción lanzada desde el bloque finally era la que
	 * finalmente prevalecía. La otra se perdia. Este comportamiento sigue siendo válida por temas de
	 * compatibilidad.
	 * 
	 * Para acabar, recuerda que Java cierra los recursos en orden inverso a como los abrió.
	 * 
	 */
	try (Coche coche = new Coche()) {
	    System.out.println("Los pasajeros entran");
	    // Excepción primaria
	    throw new RuntimeException("Exceso de peso...");
	} catch (IllegalStateException e) {
	    System.out.println(e.getMessage());
	    /*
	     * Bucle para acceder a las excepciones suprimidas, en este caso la lanzada por el método close()
	     */
	    for (Throwable throwable : e.getSuppressed()) {
		System.out.println(throwable.getMessage());
	    }
	}

    }

}
