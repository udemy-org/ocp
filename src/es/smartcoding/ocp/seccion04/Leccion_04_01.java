/**
 * 
 */
package es.smartcoding.ocp.seccion04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author pep
 * 
 *         Generics y Collections
 * 
 *         Como Java es acumulativo, debes recordar cómo trabajar con la clase
 *         ArrayList y arrays vistos en el curso OCA.
 * 
 *         También a modo de recordatorio, el método Arrays.binarySearch() busca
 *         un elemento en un array ordenado y retorna la posición que ocupa. Si
 *         no existe retorna la posición que deberia ocupar negada y le resta
 *         -1.
 * 
 *         Las clases envolventes (Wrappers) son clases que enmascaran un valor
 *         primitivo como un objeto. Cada tipo primitivo: boolean, byte, short,
 *         int, long, float, double, char tiene su clase envolvente: Boolean,
 *         Byte, Short, Integer, Long, Float, Double y Character
 *         respectivamente.
 * 
 *         Autoboxing se refiere al mecanismo por el cual Java convierte de
 *         forma automática un valor primitivo en su correspondiente objeto
 *         (inboxing) o viceversa (unboxing).
 * 
 *         Así como Java 5 introdujo los genericos, Java 7 introdujo el operador
 *         diamante (diamond) '<>' para no tener que repetir el tipo de la clase
 *         genérica. 
 *         
 *         Así List<String> l = new List<String>(); pasó a List<String> l = new List<>();
 * 
 */
public class Leccion_04_01 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Este código no debería presentar ningún problema
		List<String> list = new ArrayList<>(); // Lista vacia
		list.add("Alfa"); // [ "Alfa" ]
		list.add("Bravo"); // [ "Alfa", "Bravo" ]
		String[] array = new String[list.size()]; // array vacio
		array[0] = list.get(1); // [ "Bravo" ]
		array[1] = list.get(0); // [ "Bravo", "Alfa" ]
		// Muestra Bravo-Alfa-
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + "-");
		}

		/*
		 * este código demuestra dos aspectos interesantes:
		 * 
		 * 1. Si modificamos list2 estamos modificando array2
		 * 
		 * 2. El método Arrays.asList() retorna una lista cuyo tamaño no se
		 * puede modificar
		 * 
		 */
		String[] array2 = { "Alfa", "Bravo" };
		List<String> list2 = Arrays.asList(array2);
		list2.set(1, "Charlie");
		array2[0] = "Delta";
		String[] array3 = (String[]) list2.toArray();
		System.out.println(array2[0] + "-" + array2[1]);
		// list2.remove(1); // java.lang.UnsupportedOperationException

		int[] numbers1 = { 3, 1, 5, 9 };
		Arrays.sort(numbers1); // [1, 3, 5, 9]
		System.out.println(Arrays.binarySearch(numbers1, 4)); // -3

		List<Integer> list3 = Arrays.asList(3, 1, 5, 9);
		Collections.sort(list3); // [1, 3, 5, 9]
		System.out.println(Collections.binarySearch(list3, 7)); // -4

		/*
		 * Inboxing
		 */
		Integer i = 1;
		/*
		 * Outboxing
		 */
		int k = i;

		/*
		 * Considera este código y determina la salida.
		 */
		List<Integer> enteros = new ArrayList<Integer>();
		enteros.add(1); // Inboxing
		enteros.add(new Integer(7));
		enteros.add(new Integer(9));
		enteros.remove(1); // (1)
		enteros.remove(new Integer(9)); // (2)
		System.out.println(enteros);
		/*
		 * La salida es [1]. Porque (1) elima el elemento con índice 1 de la
		 * lista, es decir 7 y (2) elimina 9.
		 */
	}

}
