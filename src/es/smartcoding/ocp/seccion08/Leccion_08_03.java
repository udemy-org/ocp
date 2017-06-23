/**
 * 
 */
package es.smartcoding.ocp.seccion08;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author pep
 * 
 *         Entradas y salidas
 * 
 *         Trabajando con streams
 * 
 *         A la hora de trabajar con streams hay algunas procesos que vale la
 *         pena recordar.
 * 
 *         1. Cierre de un stream, en general usaremos un bloque
 *         try-with-resource que cierra los streams de forma automática.
 * 
 *         2. Vaciado (Flushing) de un stream, cuando se escribe a un
 *         OutputStream, el sistema operativo no nos garantiza que se haga la
 *         escritura inmediatamemte, ya que puede ser guardada en memoria cache.
 *         Java proporciona el método flush() que fuerza el volcado de un
 *         buffer. También, si se invoca el método close() escribe el contenido
 *         del stream.
 * 
 *         3. Marcado (Marking) de un stream, si una clase soporta el marcado,
 *         puede verificarse llamando al método markSupported(), podemos
 *         mark(int) una posición para volver a ella con el método reset(). El
 *         método mark() tiene un parámetro entero que representa el valor
 *         límite de lectura hasta donde se puede hacer un reset().
 * 
 *         4. Salto (skipping) de datos, el método skip(n) nos permite saltar
 *         los siguientes n bytes de un flujo.
 *
 */
public class Leccion_08_03 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * Bloque try-with-resources que se ocupa del cierre automático de un
		 * recurso.
		 * 
		 */
		try (BufferedReader reader = new BufferedReader(new FileReader(new File("README.md")))) {
			if (reader.markSupported()) {
				reader.mark(16);
				System.out.print((char) reader.read());
				System.out.print((char) reader.read());
				System.out.print((char) reader.read());
				System.out.print((char) reader.read());
				System.out.print((char) reader.read());
				reader.reset();
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
