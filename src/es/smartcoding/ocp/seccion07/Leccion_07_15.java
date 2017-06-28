/**
 * 
 */
package es.smartcoding.ocp.seccion07;

import java.util.Arrays;
import java.util.stream.Stream;

import static java.lang.System.out;

/**
 * @author jmendez
 * 
 *         Concurrencia
 * 
 *         Streams paralelos
 * 
 *         Una de las característica más poderosas de la API Streams es que
 *         tiene integrado soporte para la concurrencia. Hasta ahora todos los
 *         streams con los que hemos trabajado han sido serie. Un stream série
 *         es un stream en el cual los resultados estan ordenados, donde se
 *         procesa una entrada cada vez.
 * 
 *         Un stream paralelo es un stream que es capaz de procesar resultados
 *         concurrentemente, usando múltiples threads o hilos.
 * 
 *         Por defecto, el número de threads disponible en un stream paralelo
 *         esta relacionado con el número de CPU's de tu entorno. Aunque siempre
 *         puedes crear tus propias clases para incrementar el número de
 *         threads.
 * 
 */
public class Leccion_07_15 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * La API Streams ha sido diseñada para hacer muy fácil el uso de sreams
		 * paralelos. Durante el examen OCP, debes estar familiarizado con con
		 * métodos:
		 * 
		 * 1. parallel(). Se trata de una operación intermedia. Es suficiente
		 * llamar a este método desde un stream para obtener otro que soporte
		 * procesamiento multi-thread.
		 * 
		 * 2. parallelStream(). Este método está presente en la interfaz
		 * Collection.
		 * 
		 * Recuerda que algunas operaciones sobre streams convervan el atributo
		 * de paralelismo mientras que otras no. Por ejemplo,
		 * Stream.concat(Stream s1, Stream s2) retornará un stream paralelo si
		 * s1 o s2 es paralelo. En cambio, el método flatMap() crea una stream
		 * que no es paralelo por defecto.
		 * 
		 */
		Stream<Integer> stream1 = Arrays.asList(1, 2, 3, 4, 5).stream();
		System.out.println(stream1.isParallel());
		Stream<Integer> stream2 = stream1.parallel();
		System.out.println(stream1.isParallel());
		System.out.println(stream2.isParallel());
		Stream<Integer> stream3 = Arrays.asList(1, 2, 3, 5, 5).parallelStream();
		System.out.println(stream3.isParallel());

		/*
		 * Los streams paralelos tienen un punto de impredictibilidad
		 */
		Stream<Integer> serie = Arrays.asList(1, 2, 3, 4, 5).stream();
		serie.forEach(out::print);
		System.out.println();
		Stream<Integer> paralelo = Arrays.asList(1, 2, 3, 4, 5).parallelStream();
		paralelo.forEach(out::print);
		System.out.println();

		/*
		 * La API Streams incluye una versión alternativa del método forEach()
		 * llamada forEachOrdered(), que fuerza al stream paralelo a procesar
		 * los elementos en orden, con un coste evidente en velocidad.
		 */
		Stream paralelo2 = Arrays.asList(1, 2, 3, 4, 5).parallelStream();
		paralelo2.forEachOrdered(out::print);
	}

}
