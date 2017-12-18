/**
 * 
 */
package es.smartcoding.ocp.seccion03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/**
 * @author pep
 * 
 *         Genéricos y Colecciones
 * 
 *         Búsquedas y ordenamiento
 * 
 *         El método sort() de la clase Collections está sobrecargado: uno toma como parámetro una colección de objetos
 *         que implementan la interfaz Comparable. Y el otro, una colección de objetos y un objeto Comparator.
 * 
 */

class Desordenada {
    int id;
}

public class Leccion_03_05 {

    /**
     * @param args
     */
    public static void main(String[] args) {
	List<Desordenada> list = new ArrayList<>();
	// Collections.sort(list); // No compila porque list no implementa
	// Comparable
	Comparator<Desordenada> c = (d1, d2) -> d1.id - d2.id;
	// Pero si le pasamos un comparator entonce sí
	Collections.sort(list, c);
	TreeSet<Desordenada> treeSet = new TreeSet<>();
	/*
	 * java.lang.ClassCastException: es.smartcoding.ocp.seccion04.Desordenada cannot be cast to java.lang.Comparable
	 * Al principio puede parecer extraño, porque no hay nada que comparar dado que sólo tiene un elemento la
	 * colección, pero Java trabaja así por consistencia.
	 */
	// treeSet.add(new Desordenada());

	// Considera el siguiente código y determina la salida
	List<String> names = Arrays.asList("Alfa", "Bravo", "Delta", "Echo");
	// List<String> names = Arrays.asList("Echo", "Delta", "Bravo", "Alfa"
	// );
	/*
	 * Java 8 ha añadido nuevos métodos estáticos y default a la interfaz Comparator que permiten crear comparators
	 * muy fácilmente.
	 */
	// Comparator<String> r = Comparator.naturalOrder();
	Comparator<String> r = Comparator.reverseOrder();
	int index = Collections.binarySearch(names, "Alfa", r);
	System.out.println(index);
	/*
	 * La respuesta es indeterminado porque el método binarySearch() espera un orden natural. Sería 0 si el
	 * comparador fuera en orden natural.
	 * 
	 * Te recomiendo que visites las documentación oficial de Oracle para ampliar información.
	 */
    }

}








