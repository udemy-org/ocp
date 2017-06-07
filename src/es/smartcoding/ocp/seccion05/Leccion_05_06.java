/**
 * 
 */
package es.smartcoding.ocp.seccion05;

import java.util.stream.Stream;

/**
 * @author pep
 * 
 *         Usando Streams
 * 
 *         Trabajando con primitivos
 * 
 *         A la hora de trabajar con tipos primitivos, int, double, etc., hay
 *         streams que tratan con estos tipos primitivos.
 * 
 * 
 *
 */
public class Leccion_05_06 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Suma 1 + 2 + 3
		System.out.println(Stream.of(1, 2, 3).reduce(0, (a, b) -> a + b));
		// Suma los numeros pares que hay entre 1 y 100
		System.out.println(Stream.iterate(1, n -> n + 1).limit(100).filter(n -> n % 2 == 0).reduce(0, (a, b) -> a + b));
		System.out.println(Stream.iterate(1, n -> n + 1).limit(100).filter(n -> n % 2 == 0).mapToInt(n -> n).sum());
		// Suma los cuadrados de los numeros pares que hay entre 1 y 100
		System.out.println(Stream.iterate(1, n -> n + 1).limit(100).filter(n -> n % 2 == 0).mapToInt(n -> n * n).sum());

	}

}
