/**
 * 
 */
package es.smartcoding.ocp.seccion05;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

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

	}

}
