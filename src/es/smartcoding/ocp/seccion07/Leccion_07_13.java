/**
 * 
 */
package es.smartcoding.ocp.seccion07;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author jmendez
 * 
 *         Concurrencia
 * 
 *         Las colecciones CopyOnWrite
 * 
 *         Hay dos colecciones concurrentes que se comportan de una forma
 *         diferente al resto: CopyOnWriteArrayList y CopyOnWriteArraySet.
 * 
 *         Estas clases copian todos sus elementos a una nueva estructura cada
 *         vez que se añade, elimina o modifica una elemento de la colección.
 *         Entendiendo por elemento modificado cuando cambia una referencia de
 *         la colección.
 * 
 *         Aunque los datos se guardan a una nueva estructura, la referencia al
 *         objeto (la colección) no cambia, lo que es particularmente útil en un
 *         entorno multi-thread.
 *
 */
public class Leccion_07_13 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * Aunque este no es un entorno multi-thread, con una lista no
		 * concurrente, como por ejemplo ArrayList, se lanzaría una excepción de
		 * tipo java.util.ConcurrentModificationException.
		 * 
		 * Como puedes ver, el iterador recorre los tres elementos iniciales de
		 * la lista, aunque dentro del bucle se estan añadiendo nuevos
		 * elementos.
		 * 
		 * A simple vista, parece que sigue un patrón de Objeto inmutable,
		 * porque se crea una nueva estructura cada vez que la colección se
		 * modifica, aunque estrictamente hablando no lo es, porque la
		 * referencia al objeto no cambia. Pero mantiene muchas similitudes.
		 * 
		 * Las clases CopyOnWrite pueden llegar a usar mucha memoria, ya que se
		 * crea una nueva estructura cada vez que la colección se modifica.
		 * Generalmente se utilizan en entornos multi-thread en situaciones
		 * donde las lecturas son mucho más comunes que las escrituras.
		 * 
		 */
		List<Integer> list = new CopyOnWriteArrayList<>(Arrays.asList(2, 3, 1));
		/*
		 * Lanza una excepción java.util.ConcurrentModificationException
		 */
		// List<Integer> list = new ArrayList<>(Arrays.asList(2, 3, 1));
		for (Integer item : list) {
			System.out.print(item + " ");
			list.add((int) (Math.random() * 100));
		}
		System.out.println("\n" + list);
		System.out.println("Tamañó: " + list.size());

	}

}
