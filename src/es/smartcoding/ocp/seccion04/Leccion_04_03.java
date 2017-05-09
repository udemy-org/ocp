/**
 * 
 */
package es.smartcoding.ocp.seccion04;

/**
 * @author pep
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
 *         verificar elementos tarda O(log n). La clase TreeSet implementa una interfaz especial NavigabaleSet que nos permite trocear la colección.
 * 
 * 
 */
public class Leccion_04_03 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
