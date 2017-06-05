/**
 * 
 */
package es.smartcoding.ocp.seccion05;

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
 */
public class Leccion_05_03 {

	public static Optional<Double> promedio(double... valores) {
		if (valores.length == 0) {
			return Optional.empty();
		}
		double d = 0;
		for (double valor: valores) {
			d += valor;
		}
		return Optional.of(d / valores.length);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		promedio(1,2,3,4,5).ifPresent(out::println);
	}

}
