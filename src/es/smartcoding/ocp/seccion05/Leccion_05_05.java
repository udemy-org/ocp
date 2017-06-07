/**
 * 
 */
package es.smartcoding.ocp.seccion05;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
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
 *         intermedias tratan los streams infinitos simplemente retornando un
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

		/*
		 * El método
		 * 
		 * <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends
		 * R>> mapper)
		 * 
		 * toma cada elemento del stream y lo convierte en un elemento de primer
		 * nivel en su propio stream.
		 * 
		 * Es útil a la hora de eliminar elementos vacíos de stream o si quieres
		 * combinar un stream de listas.
		 * 
		 */
		List<String> zero = Arrays.asList();
		List<String> one = Arrays.asList("Alfa");
		List<String> two = Arrays.asList("Bravo", "Charlie");
		Stream<List<String>> stream1 = Stream.of(zero, one, two);
		stream1.flatMap(l -> l.stream()).forEach(out::println);

		/*
		 * el método
		 * 
		 * Stream<T> sorted()
		 * 
		 * Stream<T> sorted(Comparator<? super T> comparator)
		 * 
		 */
		Stream.of("Bravo", "Charlie", "Alfa").sorted().forEach(out::println);
		Stream.of("Zulu", "Echo", "November").sorted(Comparator.reverseOrder()).forEach(out::println);

		/*
		 * El método Stream<T> peek(Consumer<? super T> action) es la última
		 * operación intermedia. Es ideal para depurar porque nos permite operar
		 * con el stream sin cambiarlo.
		 * 
		 * Habitualmente se utiliza para monitorizar los valores de un stream
		 * mientras progresa.
		 * 
		 * En este ejemplo vemos que cadenas cumplen el predicado. 
		 */
		long total = Stream.of("sdfds@asdfads.cat", "asdfasd.cat", "asdfasfd@dffdxz.cat").filter(s -> s.contains("@"))
				.peek(out::println).count();
		System.out.println(total);
		
		/*
		 * Intenta determinar la salida de esta línea de código
		 */
		Stream
		.iterate(1, n -> n + 1)
		.limit(5)
		.peek(out::print)
		.filter(n -> n % 2 == 0)
		.forEach(out::print);

	}

}
