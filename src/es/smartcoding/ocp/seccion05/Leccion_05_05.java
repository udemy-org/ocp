/**
 * 
 */
package es.smartcoding.ocp.seccion05;

import java.util.stream.Stream;

import static java.lang.System.out;

/**
 * @author pep
 * 
 *         Usando Streams
 * 
 *         Las operaciones intermedias
 * 
 *         A diferencia de las operaciones terminales, las operaciones
 *         intermedias tratan los streams infinitios simplemente retornando un
 *         stream infinito.
 * 
 */
public class Leccion_05_05 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * El método Stream<T> filter(Predicate<? super T> predicate) retorna un
		 * stream de objetos que pasan la condición del predicado.
		 */
		Stream.iterate(1, n -> n + 1).limit(5).filter(n -> n % 2 == 0).forEach(out::print);
		System.out.println();

		/*
		 * El método Stream<T> distinct() elimina duplicados
		 */
		Stream.of(1, 2, 3, 3).distinct().forEach(out::print);
		System.out.println();

		/*
		 * Los métodos
		 * 
		 * Stream<T> limit(int maxSize)
		 * 
		 * Stream<T> skip(int n)
		 * 
		 * limitan el número de elementos de un stream y saltan los n primeros
		 * respectivamente.
		 */
		Stream.iterate(1, n -> n + 1).skip(3).limit(4).forEach(out::print);
		System.out.println();

		/*
		 * El método <R> Stream<R> map(Function<? super T, ? extends R> mapper)
		 * crea una correspondencia uno-a-uno de cada objeto del stream.
		 * 
		 * Este ejemplo utiliza una stream de cadenas para crear un stream de
		 * enteros donde cada entero representa la longitud de la cadena.
		 * 
		 */
		Stream.of("Alfa", "Echo", "Italy", "Oscar", "Uniform").map(s -> s.length()).forEach(out::print);
		System.out.println();
		Stream.of("Alfa", "Echo", "Italy", "Oscar", "Uniform").map(String::length).forEach(out::print);
		System.out.println();
	}

}
