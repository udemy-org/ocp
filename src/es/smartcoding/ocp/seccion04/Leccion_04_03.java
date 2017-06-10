/**
 * 
 */
package es.smartcoding.ocp.seccion04;

import java.util.Optional;
import static java.lang.System.out;

/**
 * @author pep
 * 
 *         La clase Optional
 * 
 *         La clase Optional se utiliza para expresar en Java que un resultado
 *         todavia no está disponible.
 * 
 *         Los objetos de la clase Optional se crean usando una factoría: se
 *         puede obtener un Optional vacío o un Optional con un valor a modo de
 *         envolvente.
 * 
 *         Se puede pensar en un Optional como en una caja que puede estar vacía
 *         o tener algún contenido.
 * 
 *         Por lo tanto, retornar Optional puede considerarse como una
 *         alternativa más próximo al estilo de programación funcional que
 *         retornar null.
 *
 */
public class Leccion_04_03 {

	public static Optional<Double> promedio(Double... valores) {
		if (valores.length == 0) {
			return Optional.empty();
		}
		double d = 0;
		for (double valor : valores) {
			d += valor;
		}
		return Optional.of(d / valores.length);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Optional<Double> promedio = promedio(1.0, 2.0, 3.0);
		if (promedio.isPresent()) {
			System.out.println(promedio.get());
		}
		// alternativamente
		promedio(1.0, 2.0, 3.0, 4.0, 5.0).ifPresent(out::println);

		promedio = promedio();
		// java.util.NoSuchElementException: No value present
		// System.out.println(promedio.get());
		/*
		 * Alternativas a get() con la lógica necesaria para prever el caso de
		 * que no exista un valor.
		 * 
		 */
		System.out.println(promedio.orElse(Double.NaN));
		System.out.println(promedio.orElseGet(() -> 0.0));
		System.out.println(promedio.orElseThrow(() -> new IllegalStateException()));
	}

}
