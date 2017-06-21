/**
 * 
 */
package es.smartcoding.ocp.seccion07;

import static java.lang.System.out;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author pep
 * 
 *         Concurrencia
 * 
 *         Reducciones paralelas
 * 
 *         Además de obtener una mejora sensible en la velocidad y de modificar
 *         el orden de las operaciones, usar streams paralelos puede impactar en
 *         la forma en que escribes tus aplicaciones. Las operaciones de
 *         reducción en los streams paralelos se conocen como reducciones
 *         paralelas. Los resultados de las reducciones paralelas pueden ser
 *         diferentes de los resultados obtenidos con una reducción serie.
 * 
 *         Debido a que el orden no esta garantizado durante una operación en
 *         paralelo, métodos como findAny() pueden tener un comportamiento
 *         impredecible.
 *
 */
public class Leccion_07_17 {

	/**
	 * @param args
	 * 
	 *            En el segundo stream, la Máquina Virtual Java selecciona el
	 *            hilo que acaba antes, por lo que el resultado no es
	 *            predecible.
	 * 
	 *            Las operaciones de stream que estan basadas en orden, como
	 *            findFirst(), limit() o skip() pueden ser más lentas en
	 *            entornos paralelos. El motivo es el esfuerzo adicional de
	 *            sincronización de threads que debe hacerse.
	 * 
	 */
	public static void main(String[] args) {
		Stream<Integer> stream1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).stream();
		System.out.println(stream1.findAny().get());
		Stream<Integer> stream2 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).parallelStream();
		System.out.println(stream2.findAny().get());

		/*
		 * Por otro lado, el resultado de operaciones ordenadas en streams
		 * paralelos será consistente con los resultados de streams serie.
		 */
		Stream<Integer> stream3 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).stream();
		Stream<Integer> stream4 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).parallelStream();
		System.out.println(stream3.skip(3).limit(5).findFirst().get());
		System.out.println(stream4.skip(3).limit(5).findFirst().get());

		/*
		 * En ocasiones puede ser necesario crear un stream desordenado a partir
		 * de un stream ordenado. Aunque no tiene efecto en streams series, sí
		 * que lo tiene en streams paralelos. Los streams desordenados no forman
		 * parte del examen OCP pero pueden ser útiles en tu día a día.
		 * 
		 */
		Arrays.asList(1, 2, 3, 4, 5).stream().unordered().forEach(out::print);
		System.out.println();
		Arrays.asList(1, 2, 3, 4, 5).stream().unordered().parallel().forEach(out::print);
		System.out.println();

		/*
		 * Como sabes la operación reduce() combina streams en un único objeto.
		 * El primer parámetro de una reducción se llama identidad, el segundo
		 * parámetro es una acumulador y el tercer parámetro opcional es un
		 * combinador.
		 * 
		 * Por ejemplo, podríamos concatenar una cadena de la siguiente manera.
		 * 
		 * El operador del acumulador y combinador debe cumplir la propiedad
		 * asociativa y no tener estado, es decir (a op b) op c debe ser igual a
		 * a op (b op c).
		 * 
		 */
		System.out.println(Arrays.asList("alfa", "bravo", "charlie").stream().reduce("", (s1, s2) -> s1 + s2));
		System.out.println(Arrays.asList("alfa", "bravo", "charlie").stream().reduce("", String::concat));
		System.out.println(Arrays.asList("alfa", "bravo", "charlie").parallelStream().reduce("", String::concat));

		/*
		 * La operación collect()
		 */
		List<String> list = Arrays.asList("Alfa", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot");
		Stream<String> stream5 = list.parallelStream();
		SortedSet<String> set1 = stream5.collect(ConcurrentSkipListSet::new, Set::add, Set::addAll);
		System.out.println(set1);
		Stream<String> stream6 = list.parallelStream();
		Set<String> set2 = stream6.collect(Collectors.toSet());
		System.out.println(set2);
		Stream<String> stream7 = list.parallelStream();
		Map<Integer, List<String>> map1 = stream7.collect(Collectors.groupingBy(String::length));
		System.out.println(map1);
		Stream<String> stream8 = list.parallelStream();
		ConcurrentMap<Integer, String> map2 = stream8
				.collect(Collectors.toConcurrentMap(String::length, k -> k.toUpperCase(), (s1, s2) -> s1 + "-" + s2));
		System.out.println(map2);
	}

}
