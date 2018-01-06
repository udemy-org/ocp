/**
 * 
 */
package es.smartcoding.ocp.seccion07;

import java.util.Deque;
import java.util.Queue;
import java.util.SortedMap;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author jmendez
 * 
 *         Concurrencia
 * 
 *         Las colecciones concurrentes más comunes
 * 
 *         Estas son algunas de las clases e interfaces concurrentes más comunes: la clase ConcurrentHashMap implementa
 *         la interfaz ConcurrentMap, que también forma parte de la API de concurrencia. Por lo tanto se puede utilizar
 *         tanto Map como ConcurrentMap para guardar una referencia. Dependerá de la implementación que necesitemos.
 * 
 *         La interfaz BlockingQueu extiende la interfaz Queue, se trata de una cola que además de los métodos
 *         habituales soporta operaciones que esperan un cierto tiempo tanto para leer como para escribir un valor.
 * 
 *         Además, debes conocer las clases ConcurrentSkipListSet y ConcurrentSkipListMap que son las versiones
 *         concurrentes de las clases ordenadas TreeSet y TreeMap.
 * 
 *         Revisa el código que acompaña a esta lección, responde a las preguntas planteadas y en definitiva, modifícalo
 *         para experimentar con los contenidos de esta lección.
 *
 */
public class Leccion_07_12 {

    /**
     * Determinada la salida de este código.
     * 
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

	/*
	 * En una BlockingQueue las inserciones se hacen detras y las eliminaciones y consultas en el princio. Para
	 * isertar un elemento tenemos los métodos offer() y put(). Y para consultar y recuperar elementos tenemos los
	 * métodos peek(), element() poll() y get(). Los metodos peek() y element() no elimina el elmento de la cola
	 * pero los métodos poll() y get() sí.
	 * 
	 */
	BlockingQueue<String> bq = new LinkedBlockingQueue<>();
	try {
	    boolean b1 = bq.offer("Alfa", 100, TimeUnit.MILLISECONDS);
	    boolean b2 = bq.offer("Bravo");
	    bq.put("Charlie");
	    System.out.println(bq);
	    System.out.println(bq.peek());
	    System.out.println(bq);
	    System.out.println(bq.element());
	    System.out.println(bq);
	    System.out.println(bq.poll(100, TimeUnit.MILLISECONDS));
	    System.out.println(bq);
	    System.out.println(bq.take());
	    System.out.println(bq);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}

	/*
	 * En una BlockingDeque los métodos pueden ser de cuatro tipos:
	 * 
	 * 1. Los que lanzan una excepción: addFirst(e), removeFirst() y getFirst() por una lado y addLast(e),
	 * removeLast(e) y getLast() por otro.
	 * 
	 * 2. Los que retornan un valor especial (null o false): offerFirst(e), pollFirst() y peekFirst() por una lado y
	 * offerLast(e), pollLast() y peekLast() por otro.
	 * 
	 * 3. Los que se bloquean indefinidamente hasta que la operación tiene éxito: putFirst(e) y takeFirst() por un
	 * lado y putLast(e) y takeLast() por otro.
	 * 
	 * 4. Los que se bloquean durante un límite máximo de tiempo: offerFirst(e, limit, unit) y pollFirst() por un
	 * lado y offerLast(e, limit, unit) y pollLast() por otro.
	 * 
	 * BlockingDeque es thread-safe y no permite elementos nulos.
	 * 
	 */

	BlockingDeque<String> bd = new LinkedBlockingDeque<>();
	try {
	    bd.offerFirst("Alfa", 100, TimeUnit.MILLISECONDS);
	    System.out.println(bd);
	    bd.offerLast("Zulu", 100, TimeUnit.MILLISECONDS);
	    System.out.println(bd);
	    bd.offer("Bravo");
	    System.out.println(bd);
	    System.out.println(bd.poll(100, TimeUnit.MILLISECONDS));
	    System.out.println(bd);
	    System.out.println(bd.pollLast(100, TimeUnit.MILLISECONDS));
	    System.out.println(bd);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}

	/*
	 * Las clases ConcurrentSkipListSet y ConcurrentSkipListMap son las versiones concurrentes de las clases
	 * ordenadas TreeSet y TreeMap.
	 * 
	 * En general, una lista 'skip' es una estructura de datos que permite una búsqueda rápida dentro de una
	 * secuencia ordenada de elementos.
	 */

	SortedMap<String, Integer> smap = new ConcurrentSkipListMap<>();
	smap.put("Alfa", 1);
	smap.put("Zulu", 26);
	smap.put("Charlie", 3);
	System.out.println(smap);
	System.out.println(smap.get("Zulu"));

    }

}
