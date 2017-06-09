/**
 * 
 */
package es.smartcoding.ocp.seccion05;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Random;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleFunction;
import java.util.function.DoubleSupplier;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.IntFunction;
import java.util.function.IntSupplier;
import java.util.function.IntUnaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.function.LongFunction;
import java.util.function.LongSupplier;
import java.util.function.LongUnaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.System.out;

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
 *         Las tablas 4.6, 4.7 y 4.8 del libro de referencia deben saberse de
 *         memória.
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
		new Random().ints().limit(10).forEach(out::println);
		IntStream.range(1, 6).forEach(out::println);
		IntStream.rangeClosed(1, 5).forEach(out::println);
		IntStream.rangeClosed(1, 10).mapToDouble(n -> n * 1.0).forEach(out::println);

		/*
		 * También se puede crear un stream de primitivos desde un Stream con
		 * los métodos flatMapToInt(), flatMapToDouble() o flatMapToLong().
		 */
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
		IntStream ints = list.stream().flatMapToInt(x -> IntStream.of(x));

		/*
		 * Así como la clase Optional es genérica para utilizar con cualquier
		 * tipo envolvente, también disponemos de clases Optional para tipos
		 * primitivos: OptionalDouble, OptionalInt i OptionalLong. Todas ellas
		 * cuentan con métodos similares a la clase Optional.
		 */
		OptionalDouble odouble = IntStream.rangeClosed(1, 10).average();
		odouble.ifPresent(out::println);

		/*
		 * Tanto max() como min() son opereciones terminales que por decirlo de
		 * alguna manera, consumen el stream. Por lo tanto no podríamoso
		 * calcular la diferencia entre el número mayor y el menor de un stream.
		 * 
		 * En estos casos puede ayudar usar la clase IntSummaryStatistics.
		 */
		IntStream stream1 = IntStream.rangeClosed(1, 10);
		IntSummaryStatistics statistics = stream1.summaryStatistics();
		// Si hay valores registrados
		if (statistics.getCount() > 0) {
			System.out.println(statistics.getMax() - statistics.getMin()); // 9
		}

		/*
		 * Así como hay Optionals para algunos primitivos, también hay
		 * interfaces funcionales para primitivos: suppliers, consumers,
		 * predicates, functions, unary operators, binary operators. Todo menos
		 * biconsumers, bipredicates y bifunctions.
		 * 
		 */
		BooleanSupplier s1 = () -> true;
		BooleanSupplier s2 = () -> Math.random() > 0.5;
		DoubleSupplier s3 = () -> Math.random();
		IntSupplier s4 = () -> 1;
		LongSupplier s5 = () -> System.currentTimeMillis();

		/*
		 * Interfaces funciones comunes que usan primitivos
		 */
		DoubleFunction<Long> dfunc = (d) -> Math.round(d);
		IntFunction<Double> ifunc = (i) -> i * 1.0;
		LongFunction<Integer> lfunc = (l) -> (int) l;

		DoubleUnaryOperator duop = (d) -> d / 2;
		IntUnaryOperator iuop = (i) -> i * 2;
		LongUnaryOperator luop = (l) -> l * l;

		DoubleBinaryOperator dbop = (d1, d2) -> d1 + d2;
		IntBinaryOperator ibop = (i1, i2) -> i1 * i2;
		LongBinaryOperator lbop = (l1, l2) -> l1 / l2;

		/*
		 * Y la lista sigue y sigue. Consulta las tablas 4.9 y 4.10 del manual
		 * de referencia
		 */

	}

}
