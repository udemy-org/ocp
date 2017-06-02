/**
 * 
 */
package es.smartcoding.ocp.seccion04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
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

public class Leccion_04_06 {

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
		 * Ejemplo de método de instancia de una instancia conocida en tiempo de ejecución
		 * 
		 */
		
		Predicate<String> referenciaAMetodo3 = String::isEmpty;
		Predicate<String> lambda3 = (String string) -> string.isEmpty();
		System.out.println(lambda3.test("Java Rocks!!!"));
		
		/*
		 * Ejemplo de referencia a constructor
		 * 
		 * Se trata de un nuevo tipo de referencia que utiliza new en vez de un método.
		 * 
		 */
		
		Supplier<ArrayList<String>> referenciaAMetodo4 = ArrayList::new;
		Supplier<ArrayList<String>> lambda4 = () -> new ArrayList<>();
		
		/*
		 * El método boolean removeIf(Predicate<? super E> filter)
		 * 
		 */
		
		List<String> strings = new ArrayList<>();
		strings.add("Alfa");
		strings.add("Bravo");
		strings.add("Charlie");
		strings.add("Delta");
		strings.add("Echo");
		System.out.println(strings);
		strings.removeIf((String str) -> str.startsWith("A"));
		System.out.println(strings);
		
	}

}
