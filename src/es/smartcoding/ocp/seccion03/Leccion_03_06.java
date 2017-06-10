/**
 * 
 */
package es.smartcoding.ocp.seccion03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.function.BiFunction;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author pep
 * 
 *         Lo nuevo de Java 8
 * 
 *         Si dejamos a un lado el hecho de haber implementado un Comparator con
 *         una expresión lambda, todo lo demás no es particular de Java 8.
 * 
 *         La mayoría de las innovaciones de Java 8 giran en torno a los
 *         streams.
 * 
 *         En esta lección vamos a introducir las referencias a métodos que nos
 *         permiten escribir código más compacto.
 * 
 *         Las referencia a métodos, y las expresiones lambdas son parte del
 *         núcleo de Java por lo que pueden aparecer frecuentemente.
 * 
 *         En particular, veremos cómo usar los métodos removeIf(), forEach(),
 *         merge(), computeIfPresent() y computeIfAbsent().
 * 
 */

class Cilindro {
	double base;
	double altura;

	public static int comparaPorBase(Cilindro c1, Cilindro c2) {
		return (int) (c1.base - c2.base);
	}

	public static int comparaPorAltura(Cilindro c1, Cilindro c2) {
		return (int) (c1.altura - c2.altura);
	}
}

public class Leccion_03_06 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * Si quisieramos crear un Comparator de Cilindros por base podríamos
		 * usar lambdas
		 */
		Comparator<Cilindro> comparatorPorBase = (c1, c2) -> Cilindro.comparaPorBase(c1, c2);
		/*
		 * Aunque la implementación es correcta, hay cierta redundancia: la
		 * lambda declara dos parámetros que simplemente se los pasa al método
		 * estático comparaPorBase de la clase Cilindro. Esta redundancia se
		 * puede eliminar, usando una referencia a función.
		 */
		Comparator<Cilindro> comparatorPorAltura = Cilindro::comparaPorAltura;
		/*
		 * El operador de referencia a función :: dice a Java que pase
		 * automáticamente los parámetros a la función.
		 * 
		 * La expresión Cilindro::comparaPorAltura, retorna una interfaz
		 * funcional y por lo tanto es como una lambda.
		 */

		/*
		 * Hay cuatro formatos de referencia a métodos:
		 * 
		 * Métodos estáticos
		 * 
		 * Métodos de instancia de una instancia particular
		 * 
		 * Métodos de instancia de una instancia que se determinará en tiempo de
		 * ejecución
		 * 
		 * Constructores
		 * 
		 * Para ilustrar el tema vamos a usar algunas interfaces funciones
		 * vistas anteriormente:
		 * 
		 * Predicate es una interfaz funcional que toma un parámetro de tipo T y
		 * retorna un boolean
		 * 
		 * Consumer es una interfaz funcional que toma un parámetro de tipo T y
		 * retorn void
		 * 
		 * Supplier es una interfaz funcional que no toma ningún parámetro y
		 * devuelve T
		 * 
		 */

		/*
		 * Ejemplo de método estático
		 * 
		 * El método sort esta sobrecargado pero Java deduce del contexto de qué
		 * método se trata.
		 * 
		 * El método sort() de la clase Collections es un método estático que
		 * toma como parámetro una lista de objetos y que retorna void.
		 * 
		 */
		Consumer<List<Integer>> referenciaAMetodo1 = Collections::sort;
		Consumer<List<Integer>> lambda1 = (List<Integer> list) -> Collections.sort(list);

		List<Integer> list = new ArrayList<>();
		list.add(3);
		list.add(5);
		list.add(2);
		list.add(1);
		lambda1.accept(list);
		System.out.println(list);

		/*
		 * Ejemplo de método de instancia de una instancia particular
		 * 
		 */

		String s = "lo que sea";
		Predicate<String> referenciaAMetodo2 = s::startsWith;
		Predicate<String> lambda2 = (String start) -> s.startsWith(start);
		System.out.println(lambda2.test("lo que"));

		/*
		 * Ejemplo de método de instancia de una instancia conocida en tiempo de
		 * ejecución
		 * 
		 */

		Predicate<String> referenciaAMetodo3 = String::isEmpty;
		Predicate<String> lambda3 = (String string) -> string.isEmpty();
		System.out.println(lambda3.test("Java Rocks!!!"));

		/*
		 * Ejemplo de referencia a constructor
		 * 
		 * Se trata de un nuevo tipo de referencia que utiliza new en vez de un
		 * método.
		 * 
		 */

		Supplier<ArrayList<String>> referenciaAMetodo4 = ArrayList::new;
		Supplier<ArrayList<String>> lambda4 = () -> new ArrayList<>();

		/*
		 * El método boolean removeIf(Predicate<? super E> filter)
		 * 
		 * Elimina todos los objetos que una colección que cumplen una
		 * condición.
		 * 
		 * Cómo podría sustituirse (1) con una referencia a método?
		 * 
		 * Se trata de una pregunta con truco. No se puede, porque el método
		 * startsWith() requiere un parámetro que no es str.
		 * 
		 */

		List<String> strings = new ArrayList<>();
		strings.add("Alfa");
		strings.add("Bravo");
		strings.add("Charlie");
		strings.add("Delta");
		strings.add("Echo");
		strings.add("Delta");
		System.out.println(strings);
		strings.removeIf((String str) -> str.startsWith("A")); // (1)
		System.out.println(strings);

		/*
		 * El método void replaceAll(UnaryOperator<E> o)
		 * 
		 * Cambia todos los objetos de una colección por otro.
		 * 
		 * La interfaz funcional UnaryOperator es una especialización de
		 * Function para cuando el parámetro y el resultado son del mmismo tipo.
		 * 
		 */

		strings.replaceAll((String string) -> string.toUpperCase());
		System.out.println(strings);

		/*
		 * Recorrer una colección es una operación muy corriente. Java 8 añade
		 * otra más mediante el uso de lambdas.
		 * 
		 * 
		 */

		strings.forEach((String c) -> System.out.println(c));
		strings.forEach(System.out::println);

		/*
		 * La nueva API Map de Java 8
		 * 
		 * Al parecer sólo el método merge() forma parte de los objetivos del
		 * examen OCP. Y otros dos computeIfPresent() y computeIfAbsent() forman
		 * parte de los objetivos del examen de actualización.
		 * 
		 * El autor recomienda visitar este enlace para saber si se mantiene así:
		 * 
		 * http://www.selikoff.net/ocp
		 * 
		 */
		
		Map<String, String> favorites = new HashMap<>();
		// putIfAbsent no modificará porque ja esta asignado
		favorites.put("Jenny", "Bus Tour");
		// putIfAbsent() modificará el valor porque es null
		favorites.put("Tom", null); 
		favorites.putIfAbsent("Jenny", "Tram");
		favorites.putIfAbsent("Sam", "Tram");
		favorites.putIfAbsent("Tom", "Tram");
		System.out.println(favorites); 
		
		/*
		 * merge
		 * 
		 * La interfaz funcional UnaryOperator es un especialización de Function
		 * cuyo método abstracto acepta un parámetro y retorna un valor del
		 * mismo tipo.
		 * 
		 * 
		 */

		strings.replaceAll((String string) -> string.toUpperCase());
		System.out.println(strings);

		/*
		 * Recorriendo una colección
		 * 
		 * Java 8 introduce una nuevo método que utiliza una expresión lambda o
		 * referencia a función.
		 * 
		 * 
		 */

		strings.forEach((String str) -> System.out.println(str));
		strings.forEach(System.out::println);

		/*
		 * La nueva API Map de Java 8
		 * 
		 * Java 8 ha añadido nuevos métodos a la interfaz Map. Pero sólo merge
		 * forma parte de los objetivos del examen OCP. Dos métodos más,
		 * computeIfPresent() y computeIfAbsent() forman parte de los objetivos
		 * del examen de actualización
		 * 
		 */

		Map<String, String> capitales = new HashMap<>();
		capitales.put("Suiza", "Geneve");
		capitales.put("Francia", "Paris");
		capitales.put("Italia", null);
		// Modifica el valor de la clave "Suiza"
		capitales.put("Suiza", "Ginebra");
		// Modifica el valor si no existe o es nula
		capitales.putIfAbsent("Italia", "Roma");
		capitales.putIfAbsent("Suiza", "Kiev");
		System.out.println(capitales);

		/*
		 * El método merge()
		 * 
		 * Algunas veces necesitamos más lógica a la hora de determinar qué
		 * valor asignar a una clave.
		 * 
		 * El método merge proporciona un mecanismo para añadir lógica sobre qué
		 * valor escoger.
		 */

		/*
		 * Esta expresión es una función que retorna la cadena más larga
		 */
		BiFunction<String, String, String> biFunction = (String s1, String s2) -> s1.length() > s2.length() ? s1 : s2;
		/*
		 * Esta expresión es una función que retorna la cadena menor
		 */
		BiFunction<String, String, String> biFunction2 = (String s1, String s2) -> s1.compareTo(s2) < 0 ? s1 : s2;
		/*
		 * Esta expresión es una función que retorna null
		 */
		BiFunction<String, String, String> biFunction3 = (s1, s2) -> null;
		String value = "abcde";
		Map<String, String> nombres = new HashMap<>();
		nombres.put("clave1", "abcdefg");
		nombres.put("clave2", "abcd");
		nombres.put("clave3", null);
		String clave1 = nombres.merge("clave1", value, biFunction);
		String clave2 = nombres.merge("clave2", value, biFunction);
		/*
		 * En este caso la función de mapeo no se invoca, si se invocara
		 * lanzaría una NullPointerException.
		 */
		String clave3 = nombres.merge("clave3", value, biFunction);
		System.out.println(nombres);
		System.out.println(clave1);
		System.out.println(clave2);
		System.out.println(clave3);
		/*
		 * Por ultimo debes tener en cuenta que si la función de mapeo retorna
		 * null, la clave se elimina del mapa.
		 */
		clave2 = nombres.merge("clave2", value, biFunction3);
		System.out.println(clave2);
		System.out.println(nombres);
		/*
		 * computeIfPresent() y computeIfAbsent()
		 * 
		 * Estos dos métodos no forman parte del examen OCP sino del examen de
		 * actualización.
		 * 
		 * El método computeIfPresent realiza un calculo si la clave existe y
		 * toma como argumento la clave y una BiFunction.
		 * 
		 * El método computeIfAbsent realiza un calculo si la clave no existe o
		 * es un valor nulo y toma como argumento la clave i una Function.
		 * 
		 * Si la función de mapeo retorna un valor null, el método
		 * computeIfPresent(), la clave se elimina del mapa. Con el método
		 * computeIfAbsent() la clave no se llega a añadir nunca en el mapa.
		 * 
		 */

		Map<String, Integer> contadores = new HashMap<>();
		contadores.put("reds", 5);

		BiFunction<String, Integer, Integer> contBiFunction = (String clave, Integer val) -> val + 1;
		Function<String, Integer> contFunction = (String clave) -> 1;
		BiFunction<String, Integer, Integer> nullBiFunction = (String clave, Integer val) -> null;
		Function<String, Integer> nullFunction = (String clave) -> null;

		contadores.computeIfPresent("reds", contBiFunction);
		contadores.computeIfPresent("greens", contBiFunction);
		contadores.computeIfAbsent("blues", contFunction);
		System.out.println(contadores);
		contadores.computeIfPresent("reds", nullBiFunction);
		contadores.computeIfAbsent("pinks", nullFunction);
		System.out.println(contadores);

	}

}
