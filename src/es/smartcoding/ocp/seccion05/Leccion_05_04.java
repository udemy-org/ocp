/**
 * 
 */
package es.smartcoding.ocp.seccion05;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.out;

/**
 * @author pep
 * 
 *         Usando Streams
 * 
 *         Un stream o flujo en Java es una secuencia de datos. Un stream
 *         pipeline o un tubería de flujos es la operación que se realiza en un
 *         stream y que produce un resultado.
 * 
 *         Podemos pensar que un stream pipeline es como una línea de ensamblaje
 *         de una fábrica. En cada etapa de la línea de ensamblaje se realiza
 *         una tarea, cuando termina pasa a la siguiente y ya no vuelve atrás,
 *         hasta obtener el producto final.
 * 
 *         Es diferente de una lista, en el sentido en que en una lista se puede
 *         acceder a un elemento arbitrario en cualquier momento. Con los
 *         streams, lo datos se crean en el momento que se necesitan.
 * 
 *         Cada etapa o estación de ensamblaje se llama stream operations. Por
 *         lo tanto, cada stream pipeline tiene tres partes:
 * 
 *         Source u origen: de donde proviene el stream.
 * 
 *         Operaciones intermedias: que transforman el stream en otro stream,
 *         son como cajas negras.
 * 
 *         Operaciones terminales: que producen un resultado. Dado que un stream
 *         es de un sólo uso, después de una operación terminal ya no esta
 *         disponible.
 * 
 *         Toda fábrica tiene un encargado, Java actúa como encargado cuando se
 *         trabaja con stream pipelines. A la hora de declarar el stream estamos
 *         dando instrucciones a Java, quien se encarga de definir las
 *         estaciones de trabajo y la tarea que debe llevarse a cabo en cada una
 *         de ellas. Pero cada tarea no empieza hasta que se de define una
 *         operación terminal.
 *         
 *         En esta lección veremos las operaciones terminales
 * 
 */
public class Leccion_05_04 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * En Java la interfaz Stream está en el paquete java.util.stream. Se
		 * pueden definir streams finitos de diferente manera.
		 */
		Stream<String> vacio = Stream.empty();
		Stream<Integer> unElemento = Stream.of(1);
		Stream<Integer> desdeArray = Stream.of(1, 2, 3);
		/*
		 * Dado que los streams son nuevos en Java 8, se ha añadido métodos a
		 * las listas para obtener streams de listas.
		 * 
		 * Por su puesto, no tiene sentido utilizar streams paralelos con listas
		 * tan pequeñas debido al sobrecoste que supone la coordinación entre
		 * los diferentes threads.
		 */
		List<String> list = Arrays.asList("a", "b", "c");
		Stream<String> desdeLista = list.stream();
		Stream<String> desdeListaEnParalelo = list.parallelStream();
		/*
		 * También podemos crear streams infinitos
		 */
		Stream<Double> randoms = Stream.generate(Math::random);
		Stream<Integer> impares = Stream.iterate(1, n -> n + 2);
		/*
		 * Operaciones terminales
		 */
		System.out.println(desdeLista.count()); // 3
		// System.out.println(randoms.count()); // no acaba nunca
		desdeLista = list.stream();
		Optional<String> minimo = desdeLista.min((String s1, String s2) -> s1.compareTo(s2));
		minimo.ifPresent(out::println);

		/*
		 * Los métodos Optional<T> findAny() y Optional<T> findFirst() retornan
		 * un elemento del stream si no está vacío, en cuyo caso retorna una
		 * Optional vacío. El método findAny() es útil cuando trabajamos con
		 * streams paralelos y proporciona el primer elemento que viene.
		 * 
		 * Estos métodos son operationes terminales pero no se trata de
		 * reducciones ya que muchas veces retornan sin procesar todos los
		 * elementos. Son útiles cuando queramos tener un caso cualquiera.
		 * 
		 */
		// Stream finito de 3 elementos
		Stream<String> stream1 = Stream.of("alfa", "bravo", "charlie");
		// Stream infinito
		Stream<String> stream2 = Stream.generate(() -> "zulu");
		stream1.findAny().ifPresent(out::println);
		stream2.findAny().ifPresent(out::println);

		/*
		 * Los métodos
		 * 
		 * boolean anyMatch(Predicate <? super T> predicate),
		 * 
		 * boolean allMatch(Predicate <? super T> predicate)
		 * 
		 * boolean noneMatch(Predicate <? super T> predicate)
		 * 
		 * buscan en un stream y retornan true si cumplen el predicado. Estos
		 * streams pueden terminar o no para streams infinitos, dependen de los
		 * datos. Igual que los métodos de búsqueda no son métodos reductores
		 * porque no necesariamente recorrren todos los elementos.
		 * 
		 */
		List<Integer> lista = Arrays.asList(121, 231, 99);
		Stream stream3 = Stream.generate(() -> 101);
		Predicate<Integer> predicate1 = n -> n > 100;
		// Un predicado SÍ se puede reutilizar pero un stream NO
		System.out.println(lista.stream().anyMatch(predicate1)); // true
		System.out.println(lista.stream().allMatch(predicate1)); // false
		System.out.println(lista.stream().noneMatch(predicate1)); // false
		System.out.println(stream3.anyMatch(predicate1)); // true

		/*
		 * El método void forEach(Consumer<? super T> action) proporciona un
		 * mecanismo para recorrer un stream dado que no se puede utilizar un
		 * bucle mejorado porque no implementa la interfaz Iterable.
		 * 
		 * Aunque a simple vista parece un bucle es una operación terminal, y es
		 * la única que retorna void.
		 * 
		 */
		Stream<Integer> stream4 = Stream.of(1, 2, 3, 4, 5);
		stream4.forEach(out::print);

		/*
		 * Los métodos:
		 * 
		 * T reduce(T identity, BinaryOperator<T> accumulator)
		 * 
		 * Optional<T> reduce(BinaryOperator<T> accumulator)
		 * 
		 * <U> U reduce(U identity, BiFunction<U,? super T,U> accumulator,
		 * BinaryOperator<U> combiner)
		 * 
		 * combinan un stream en un único objeto, y como el mismo nombre indica
		 * se trata de una reducción.
		 * 
		 * El primero permite especificar un valor inicial.
		 * 
		 * El tercero es útil para trabajar con streams paralelos.
		 * 
		 */

		/*
		 * Para ilustar el método vamos a concatenar un array de cadenas,
		 * primero sin streams y después con streams.
		 */

		// Sin streams
		String[] strings = new String[] { "Java ", "Rocks ", "Forever!!" };
		String titulo1 = "";
		for (String string : strings) {
			titulo1 += string;
		}
		System.out.println(titulo1);

		// Ahora con streams
		Stream<String> stream5 = Stream.of("Java ", "Rocks ", "Forever!!");
		String titulo2 = stream5.reduce("", (String s1, String s2) -> new StringBuilder(s1).append(s2).toString());
		System.out.println(titulo2);

		// O alternativamente
		Stream<String> stream6 = Stream.of("Java ", "Rocks ", "Forever!!");
		String titulo3 = stream6.reduce("", String::concat);
		System.out.println(titulo3);

		// Streams paralelos
		BinaryOperator<Integer> bop = (a, b) -> a * b;
		Stream<Integer> stream7 = Stream.iterate(1, n -> n + 1).limit(10);
		System.out.println(stream7.reduce(1, bop, bop));

		/*
		 * Los métodos:
		 * 
		 * <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T>
		 * accumulator, BiConsumer<R, R> combiner)
		 * 
		 * <R,A> R collect(Collector<? super T, A,R> collector)
		 * 
		 * representan una forma especial de reducción llamada reducción
		 * mutable. Es más eficiente que una reducción regular porque utilizamos
		 * el mismo objto mutable mientras vamos acumulando.
		 * 
		 * Los objetos mutables más habituales son StringBuilder y ArrayList.
		 */

		Stream<String> stream8 = Stream.of("Java ", "Rocks ", "Forever!!");
		StringBuilder builder = stream8.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
		System.out.println(builder);

		/*
		 * Algunos collectors estan ya implementados listos para usar. Por
		 * ejemplo, en caso que el orden sea importante podemos usar un TreeSet
		 * o un Set si no lo es.
		 */
		Stream<String> stream9 = Stream.of("Java ", "Rocks ", "Forever!!");
		TreeSet<String> ts = stream9.collect(Collectors.toCollection(TreeSet::new));
		System.out.println(ts);

		Stream<String> stream10 = Stream.of("Java ", "Rocks ", "Forever!!");
		Set<String> set = stream10.collect(Collectors.toSet());
		System.out.println(set);
		
		Stream<String> stream11 = Stream.of("Java ", "Rocks ", "Forever!!");
		List<String> list2 = stream11.collect(Collectors.toList());
		System.out.println(list2);

		/*
		 * Durante el examen deberas provar que conoces los Collectors
		 * predefinidos más comunes y que eres capaz de crear tus propios
		 * Collectors proporcionando un supplier, accumulator y combiner.
		 */

	}

}
