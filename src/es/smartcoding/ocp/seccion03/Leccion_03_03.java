/**
 * 
 */
package es.smartcoding.ocp.seccion03;

import java.util.ArrayDeque;
import java.util.TreeSet;

/**
 * @author pep
 * 
 *         Genéricos y Colecciones
 * 
 *         Lists, Sets, Maps y Queues
 * 
 *         Una colección es un grupo de objetos contenidos en un único objeto.
 *         La Java Collection Framework es un conjunto de clases del paquete
 *         java.util utilizadas para guardar coleciones de objetos.
 * 
 *         Hay cuatro interfaces importantes en la Java Collection Framework:
 * 
 *         List: una lista es una colección ordenada de elementos que permite
 *         duplicados. Los objetos de una lista pueden accederse a traves de un
 *         índice.
 * 
 *         Set: un conjunto es una colección que no permite objetos duplicados.
 * 
 *         Queue: Una cola es una colección que ordena sus elementos de una
 *         forma específica antes de ser procesados. Típicamente sigue un orden
 *         First-In, First-Out. Pero se pueden aplicar otras formas de
 *         ordenamiento.
 * 
 *         Map: un mapa es una colección que asocia claves con valores. Las
 *         claves no pueden estar duplicadas. Cada elemento de un mapa es una
 *         pareja clave/valor. Map no desciende de Collection pero List, Set y
 *         Queue sí.
 * 
 *         Todas las colecciones tienes algunos métodos comunes: add(),
 *         remove(), isEmpty(), size(), clear() y contains().
 * 
 *         ArrayList es una implementación de la interfaz List, que retorna
 *         cualquier elemento en tiempo constante mientras que añadir y eliminar
 *         un elemento es más lento que acceder. Esta propiedad hace a la clase
 *         ArrayList apropiada si leemos más a menudo que escribimos.
 * 
 *         En computación se utiliza la anotación Big O para comparar la
 *         productividad de un algoritmo. De un algoritmo que necesita 10
 *         segundos sobre otro que necesita 1 segundo para hacer la misma tarea
 *         decimos que requiere un orden de magnitud más.
 * 
 *         La notación Big O nos permite comparar el orden de magnitud de la
 *         productividad de un algoritmo en vez de la propia productividad.
 *         Siempre asume el tiempo de respuesta en el peor de los casos posible
 *         de un algoritmo. La notación utiliza una n para denotar el tamaño de
 *         la entrada. Las notaciones Big O más comunes son:
 * 
 *         1. O(1): Tiempo constante.
 * 
 *         2. O(log n): Tiempo logaritmico. El logaritmo es una función
 *         matemática que crece mucho más lentamente que n.
 * 
 *         3. O(n): Tiempo lineal.
 * 
 *         4. O(n^2): Tiempo cuadrático.
 * 
 *         Una LinkedList es un caso especial porque implementa dos interfaces,
 *         List y Queue. Tiene todos los métodos de la interfaz List y además
 *         implementa métodos para añadir y eliminar elementos desde el inicio
 *         y/o final de la lista.
 * 
 *         Una LinkedList es una buena opción cuando se va a utilizar como una
 *         cola porque sus elementos se pueden añadir, eleminiar y acceder tanto
 *         desde el inicio como desde el final en tiempo constante. Aunque si se
 *         accede mediante un índice ocupa tiempo lineal.
 * 
 *         Ninguna implementación de Set permite duplicados ni mantienen un
 *         orden particular. La clase HashSet guarda los elementos en un tabla
 *         hash, lo que quiere decir que utiliza el método hashCode() de los
 *         objetos para recuperarlos más eficientemente. Tanto añadir un
 *         elemento como verificar si existe requieren un tiempo constante que
 *         no depende del tamaño. El inconveniente es que se pierde el orden en
 *         que se introdujeron. La clase TreeSet guarda sus elementos en una
 *         estructura de árbol ordenada. Lo que quiere decir que sus elementos
 *         están siempre bien ordenados. El inconveniente es que añadir y
 *         verificar elementos tarda O(log n). La clase TreeSet implementa una
 *         interfaz especial NavigabaleSet que nos permite trocear la colección.
 * 
 *         El método add() de la interfaz Set retorna true si el valor ya existe
 *         y false si no existía préviamente en la coleccion. La clase TreeSet
 *         implementa la interfaz NavigableSet que declara algunos métodos
 *         interesante que debes conocer:
 * 
 *         E lower(E e) retorna el mayor elemento que es menor que e, o null si
 *         no existe
 * 
 *         E floor(E e) retorna el mayor elemento que es menor o igual que e, o
 *         null si no existe
 * 
 *         E ceiling(E e) retorna el menor elemento que es mayor o igual que e,
 *         o null si no existe
 * 
 *         E higher(E e) retorna el menor elemento que es mayor que e, o null si
 *         no existe
 * 
 *         La interfaz Queue (Cola) se utiliza cuando queremos añadir y eliminar
 *         elementos en un orden específico. Las colas se utilizan típicamente
 *         para ordenar elementos antes de procesarlos. La cola es una
 *         estructura dinámica FIFO (first-in, first-out) por defecto.
 * 
 *         La clase LinkedList es una cola doblemente enlazada donde se pueden
 *         añadir y eliminar elementos desde ambos extremos. Su principal
 *         ventaja es que implementa tanto la interfaz List como Queue. Y la
 *         desventaja es que no es tan eficiente como una cola 'pura'.
 * 
 *         La clase ArrayDeque es una cola doblemente encadenada 'pura'. Fué
 *         introducida en Java 6 y guarda los elementos en un array
 *         redimensionable. La principal ventaja es que es más eficiente que la
 *         LinkedList.
 * 
 *         La clase ArrayDeque contiene numerosos métodos, entre otros:
 * 
 *         boolean add(E e) añade un elemento al final de la cola y retorna true
 *         o lanza una exception (Queue)
 * 
 *         E element() retorna un elemento o lanza una exception si la cola esta
 *         vacia (Queue)
 * 
 *         boolean offer(E e) añade un elemento al final de la cola y retorna si
 *         ha ido bien o no (Queue)
 * 
 *         E remove() elimina y retorna el siguiente elemento o lanza una
 *         exception si la cola esta vacia (Queue)
 * 
 *         void push(E e) añade un elemento al inicio de la cola (Queue, Stack)
 * 
 *         E poll() elimina y retorna el siguiente elemento o lanza una
 *         exception si la cola esta vacia (Queue)
 * 
 *         E peek() retorna el siguiente elemento o lanza una exception si la
 *         cola esta vacia (Queue, Stack)
 * 
 *         E pop() elimina y retorna el siguiente elemento o lanza una exception
 *         si la cola esta vacia (Stack)
 * 
 *         Excepto el método push() todos los demás están en la interfaz Queue
 *         también. El método push() es el método que hace a una cola doblemente
 *         encadenada.
 * 
 *         Usaremos un mapa cuando queremos identificador objetos mediante una
 *         clave. Por lo tanto un mapa es una colección de parejas clave-valor.
 * 
 *         La clase HashMap guarda las claves en una tabla hash, lo que
 *         significa que usa el método hashCode() como generador de claves para
 *         recuperar los valores más eficientemente. La principal ventaja es que
 *         añadir y recuperar elementos tienen un coste constante. La desventaja
 *         es que se pierde el orden en el que se introdujeron. Si eso es un
 *         problema puedes utilizar las clase LinkedHashMap.
 * 
 *         La clase TreeMap guarda las claves en una estructura arbórea. Por lo
 *         tanto, el orden se mantiene pero las operaciones de insertar y
 *         comprobar tienen un coste O(log n).
 * 
 *         La clase Hashtable es parecida a la clase Vector esta escrita a
 *         prueba de threads y por lo tanto es lenta. Debería haberse llamado
 *         HashTable.
 * 
 *         Dado que la interfaz Map no hereda de Collection, hay más métodos en
 *         la interfaz Map. Por tratarse de una colección de parejas clave-valor
 *         necesitamos un tipo de parámetro genérico para cada uno. Se suele
 *         utilizar la K para la clave y la v para el valor. Estos son los
 *         métodos que debes conocer:
 * 
 *         void clear() elimina todas la claves y valores del mapa
 * 
 *         boolean isEmpty() retorna si el mapa esta vacio
 * 
 *         int size() retorna el número de entradas (clave-valor) en el mapa
 * 
 *         V get(Object key) retorna el valor asociado con la clave o null si no
 *         existe
 * 
 *         V put(K key, V value) añade o reemplaza una pareja clave-valor.
 *         Retorna el valor previo si existia o null
 * 
 *         V remove(Object key) elimina y retorna un valor asociado a una clave.
 *         Retorna null si no existe
 * 
 *         boolean containsKey(Object key) Retorna si una clave existe en el
 *         mapa
 * 
 *         boolean containsValue(Object value) Retorna si un valor existe en el
 *         mapa
 * 
 *         Set<K> keySet() retorna un conjunto con todas las claves del mapa
 * 
 *         Collection<V> values() retorna una colección con todos los valores
 *         del mapa
 * 
 *         En resumen:
 * 
 *         Tipo Duplicados Ordenada Clave-valor Añadir/eliminar en orden
 * 
 *         List Sí Sí (por índice) Sí Sí
 * 
 *         Map Sí (valores) No Sí No
 * 
 *         Queue Sí Sí No Sí
 * 
 *         Set No No No No
 * 
 * 
 *         Tipo Interface Ordenada hashCode llama compareTo
 * 
 *         ArrayList List No No No
 * 
 *         ArrayDequeu Queue No No No
 * 
 *         HashMap Map No Yes No
 * 
 *         Hashtable Map No Yes No
 * 
 *         LinkedList List, Queue No No No
 * 
 *         Stack List No No No
 * 
 *         TreeMap Map Yes No Yes
 * 
 *         TreeSet Set Yes No Yes
 * 
 *         Vector List No No No
 * 
 *         TreeSet no puede contener elementos nulos y TreeMap no puede contener
 *         claves nulas, pero valores nulos sí.
 * 
 *         ArrayDequeu no puede contener nulos porque algunos métodos como
 *         poll() usa null como valor de retorno para indicar que la colección
 *         está vacia.
 * 
 *         Hashtable no permite ni claves ni valores nulos.
 * 
 *         Finalmente, es preciso que seas capaz de seleccionar una estrutura de
 *         datos en función de un escenario concreto. Para ellos debes tener en
 *         cuenta el tipo de estructura LIFO, FIFO. Si se requiere que esté
 *         ordenada, si admite duplicados o nulos. Cuando se requiera una pila
 *         (LIFO) usa ArrayDeque. Si necesitas una cola (FIFO) utiliza una
 *         LinkedList. Si necesitas una lista utiliza ArrayList. Si necesitas un
 *         conjunto de elementos sin duplicados utiliza HashSet y si además debe
 *         estar ordenado utiliza TreeSet. Por último si requieres buscar
 *         objetos por clave utiliza HashMap, o TreeMap si requieres orden.
 * 
 */
public class Leccion_03_03 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TreeSet<Number> ts = new TreeSet<>();
		ts.add(1);
		ts.add(10);
		ts.add(20);
		System.out.println(ts.lower(10));
		System.out.println(ts.floor(10));
		System.out.println(ts.higher(10));
		System.out.println(ts.ceiling(10));

		System.out.println("================");

		ArrayDeque<Number> ad = new ArrayDeque<>();
		ad.offer(1);
		ad.offer(10);
		ad.offer(20);
		System.out.println(ad.poll());
		System.out.println(ad.peek());
		System.out.println(ad.pop());
	}

}
