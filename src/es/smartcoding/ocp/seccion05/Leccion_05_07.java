/**
 * 
 */
package es.smartcoding.ocp.seccion05;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author pep
 * 
 *         Usando Streams
 * 
 *         Trabajando con conceptos avanzados de tuberias de streams
 * 
 */
public class Leccion_05_07 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/*
		 * Cuando creamos un stream de una colección de datos, el stream es
		 * dinámico. es decir se actualiza en cada momento.
		 * 
		 * La línea (1) realmente no crea el stream, sino que se crea un objeto
		 * que sabe dónde mirar cuando se necesite el flujo de datos.
		 * 
		 */
		List<String> l = Arrays.asList("Barcelona", "Bilbao", "A Coruña");
		List<String> capitales = new ArrayList<>(l);
		Stream<String> stream1 = capitales.stream(); // (1)
		capitales.add("Sevilla");
		System.out.println(stream1.count()); // 4

		/*
		 * Los métodos de Optional también se pueden encadenar.
		 * 
		 * Supongamos que queremos imprimir el entero que contiene un Optional
		 * pero sólo si es una capicúa de tres dígitos.
		 * 
		 * Utilizamos una expresión regular de dos dígitos seguida del primero.
		 * 
		 * Prueba a hacer lo mismo sin expresiones lambda...
		 * 
		 */
		String regExpr = "(\\d)(\\d)\\1";
		Optional.of(343).map(n -> String.valueOf(n)).filter(n -> n.matches(regExpr)).ifPresent(out::println);
		Optional.of(342).map(n -> String.valueOf(n)).filter(n -> n.matches(regExpr)).ifPresent(out::println);
		Optional.of(696).map(n -> String.valueOf(n)).filter(n -> n.matches(regExpr)).ifPresent(out::println);

		/*
		 * Por último vamos a ver los Collectors predefinidos de Java 8
		 * 
		 * Anteriormente vimos el método collect() como una operación terminal,
		 * pero con lon colectores predefinidos se puede hacer practicamente
		 * todo lo imaginable como crear mapas, conjuntos.
		 */

		TreeMap<Integer, String> tmap = Stream.of("Alfa", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot")
				.collect(Collectors.toMap(String::length, k -> k, (s1, s2) -> s1 + "," + s2, TreeMap::new));
		System.out.println(tmap); 
		System.out.println(tmap.getClass()); // class. java.util.TreeMap

		Map<Integer, List<String>> map = Stream.of("Alfa", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot")
				.collect(Collectors.groupingBy(String::length));
		System.out.println(map);
	}

}
