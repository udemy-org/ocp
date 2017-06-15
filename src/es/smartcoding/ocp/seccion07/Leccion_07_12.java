/**
 * 
 */
package es.smartcoding.ocp.seccion07;

import java.util.Deque;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

/**
 * @author jmendez
 * 
 *         Concurrencia
 * 
 *         Colecciones concurrentes más comunes
 * 
 *         Estas son algunas de las clases concurrentes más comunes. La clase
 *         ConcurrentHashMap implementa la interfaz ConcurrentMap, que también
 *         forma parte de la API de concurrencia. Por lo tanto se puede utilizar
 *         tanto Map como ConcurrentMap para guardar una referencia. Dependerá
 *         de la implementación que necesitemos.
 * 
 *         La interfaz BlockingQueu extiende la interfaz Queue, se trata de una
 *         cola que además de los métodos habituales soporta operaciones que
 *         esperan un cierto tiempo.
 * 
 * 
 *
 */
public class Leccion_07_12 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Map<String, Integer> map = new ConcurrentHashMap<>();
		ConcurrentMap<String, Integer> map = new ConcurrentHashMap<>();
		map.put("alfa", 1);
		map.put("zulu", 26);
		System.out.println(map.get("alfa"));
		Queue<Integer> queue = new ConcurrentLinkedQueue<>();
		queue.offer(31);
		System.out.println(queue.peek());
		System.out.println(queue.poll());
		Deque<Integer> deque = new ConcurrentLinkedDeque<>();
		deque.offer(10);
		deque.push(4);
		System.out.println(deque.peek());
		System.out.println(deque.pop());
	}

}
