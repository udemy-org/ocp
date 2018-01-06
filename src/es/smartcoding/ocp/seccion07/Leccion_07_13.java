/**
 * 
 */
package es.smartcoding.ocp.seccion07;

import java.util.ArrayList;
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
 *         Hay dos colecciones concurrentes que se comportan de una forma diferente al resto: CopyOnWriteArrayList y
 *         CopyOnWriteArraySet.
 * 
 *         Estas clases copian todos sus elementos a una nueva estructura cada vez que se añade, elimina o modifica un
 *         elemento de la colección. Entendiendo por elemento modificado cuando cambia una referencia de la colección.
 * 
 *         Aunque los datos se guardan en una nueva estructura, la referencia al objeto (la colección) no cambia, lo que
 *         es particularmente útil en un entorno multi-thread.
 * 
 *         Las clases CopyOnWrite pueden llegar a usar mucha memoria, ya que se crea una nueva estructura cada vez que
 *         la colección se modifica. Generalmente se utilizan en entornos multi-thread en situaciones donde las lecturas
 *         son mucho más comunes que las escrituras.
 * 
 *         A simple vista, parece que sigue un patrón de Objeto inmutable, porque se crea una nueva estructura cada vez
 *         que la colección se modifica, aunque estrictamente hablando no lo es, porque la referencia al objeto no
 *         cambia. Pero mantiene muchas similitudes.
 * 
 *         Revisa el código que acompaña a esta lección, responde a las preguntas planteadas y en definitiva, modifícalo
 *         para experimentar con los contenidos de esta lección.
 *
 */
public class Leccion_07_13 {

    /**
     * Determina la salida del siguiente código. Comenta (1) y descomenta (2) y cromprueba el resultado.
     * 
     * @param args
     */
    public static void main(String[] args) {
	/*
	 * Aunque este no es un entorno multi-thread, con una lista no concurrente, como por ejemplo ArrayList, se
	 * lanzaría una excepción de tipo java.util.ConcurrentModificationException.
	 * 
	 * Como puedes ver, el iterador recorre los tres elementos iniciales de la lista, aunque dentro del bucle se
	 * estan añadiendo nuevos elementos.
	 * 
	 */
	// (1)
	List<Integer> list = new CopyOnWriteArrayList<>(Arrays.asList(2, 3, 1));
	/*
	 * Lanza una excepción java.util.ConcurrentModificationException
	 */
	// (2)
	// List<Integer> list = new ArrayList<>(Arrays.asList(2, 3, 1));
	for (Integer item : list) {
	    System.out.print(item + " ");
	    list.add((int) (Math.random() * 100));
	}
	System.out.println("\n" + list);
	System.out.println("Tamaño: " + list.size());

    }

}
