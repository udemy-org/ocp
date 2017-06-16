/**
 * 
 */
package es.smartcoding.ocp.seccion07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author jmendez
 * 
 *         Concurrencia
 * 
 *         Sincronización de colecciones
 * 
 *         Además de las colecciones concurrentes, las API de concurrencia
 *         también incluye métodos para obtener versiones sincronizadas de las
 *         colecciones no concurrentes.
 * 
 *         Consulta la tabla 7.12 del libro de referencia para obtener una lista
 *         completa.
 *
 */
public class Leccion_07_14 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/*
		 * Aunque sea posible obtener una lista sincronizada, sigue siendo
		 * imperativo utilizar un bloque de sincronización a la hora de iterar a
		 * través de sus elementos.
		 * 
		 * Este código sigue lanzando una excepción de tipo
		 * java.util.ConcurrentModificationException incluso si utilizamos un
		 * bloque sincronizado.
		 * 
		 * Pero por lo demás, una lista sincronizada es perfectamente segura
		 * para usarla en un entorno multi-thread.
		 * 
		 */

		List<Integer> list = Collections.synchronizedList(new ArrayList<>(Arrays.asList(1, 2, 3)));
		synchronized (list) {
			for (Integer i : list) {
				System.out.println(i);
				list.add(i * i);
			}
		}
	}

}
