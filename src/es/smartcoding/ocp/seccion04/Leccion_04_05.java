/**
 * 
 */
package es.smartcoding.ocp.seccion04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/**
 * @author pep
 * 
 *         Searching and Sorting
 * 
 *         El método sort() de la clase Collections ordena colecciones de
 *         objetos que implementan la interfaz Comparable. Lanza una excepción
 *         en tiempo de compilación si la coleccion no contiene objetos que
 *         implementen la interfac Comparable.
 * 
 *         Sin embargo, La clase TreeSet que mantiene ordenada una colección
 *         según un orden natural, lanza un excepción en tiempo de ejecución si
 *         añadimos un objeto que no implementa la interfaz Comparabale.
 *
 */

class Desordenada {
	int id;
}

public class Leccion_04_05 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Desordenada> list = new ArrayList<>();
		// Collections.sort(list); // No compila porque list no implementa Comparable
		Comparator<Desordenada> c = (d1, d2) -> d1.id - d2.id;
		// Pero si le pasamos un comparator entonce sí
		Collections.sort(list, c);
		TreeSet<Desordenada> treeSet = new TreeSet<>();
		/*
		 * java.lang.ClassCastException:
		 * es.smartcoding.ocp.seccion04.Desordenada cannot be cast to
		 * java.lang.Comparable
		 * Al principio puede parecer extraño, porque no hay nada que comparar
		 * dado que sólo tiene un elemento la colección, pero Java trabaja
		 * así por consistencia.
		 */
		// treeSet.add(new Desordenada());
		
		// Considera el siguiente código y determina la salida
		List<String> names = Arrays.asList("Alfa", "Bravo", "Delta", "Echo");		
		// List<String> names = Arrays.asList("Echo", "Delta", "Bravo", "Alfa" );
		// Comparator<String> r = Comparator.naturalOrder();
		Comparator<String> r = Comparator.reverseOrder();
		int index = Collections.binarySearch(names, "Alfa", r);
		System.out.println(index);
		/*
		 * La respuesta es indeterminado porque el método binarySearch() espera un orden natural.
		 * Sería 0 si el comparador fuera en orden natural.
		 *  
		 */
	}

}
