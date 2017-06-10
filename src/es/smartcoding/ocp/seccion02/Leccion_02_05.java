/**
 * 
 */
package es.smartcoding.ocp.seccion02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author pep
 * 
 *         Un patrón de diseño es una solución generalmente aceptada a un
 *         problema recurrente de diseño de software en un contexto dado.
 * 
 *         Estas soluciones que han encontrado otros programadores forman un
 *         vocabulario que utilizan los programadores para comunicarse entre
 *         ellos.
 * 
 *         En el curso de certificación OCP sólo se tratan dos de los patrones
 *         creacionales, que gestionan la creación de objetos dentro de las
 *         aplicaciones: el patrón Singleton y el patrón Immutable.
 * 
 *         El patrón Singleton soluciona el problema de cómo diseñar una clase
 *         de la que sólamente se pueda crear un único objeto.
 * 
 *         El patrón Immutable soluciona el problema de cómo crear objetos de
 *         sólo lectura para que puedan ser compartidos por múltiples clases.
 *         Como su estado no cambia después de haber sido creado, son
 *         inherentemente thread safe.
 *         
 *         Los patrones de diseño Builder y Factory aunque muy utiles no forman
 *         del examen. 
 * 
 * 
 */

class SingletonEarlyInstanciation {
	/*
	 * Instanciación temprana
	 */
	private static final SingletonEarlyInstanciation instance = new SingletonEarlyInstanciation();

	/*
	 * Constructor privado
	 */
	private SingletonEarlyInstanciation() {

	}

	public static SingletonEarlyInstanciation getInstance() {
		return instance;
	}
}

class SingletonLateInstanciation {
	private static SingletonLateInstanciation instance;

	/*
	 * Constructor privado.
	 */
	private SingletonLateInstanciation() {

	}

	/*
	 * Instanciación tardia y thread safe. Aunque sólo la primera invocación
	 * necesita estar sincronizada.
	 */
	public synchronized static SingletonLateInstanciation getInstance() {
		if (instance == null) {
			instance = new SingletonLateInstanciation();
		}

		return instance;
	}
}

class SingletonLateInstanciationWithDoubleCheckLocking {
	/*
	 * volatile previene optimizaciones. Previene que un objeto sea accedido
	 * antes de que se haya acabado de construir.
	 */
	private static volatile SingletonLateInstanciationWithDoubleCheckLocking instance;

	/*
	 * Constructor privado.
	 */
	private SingletonLateInstanciationWithDoubleCheckLocking() {

	}

	/*
	 * Instanciación tardia y thread safe. Realmente la sincronización sólo es
	 * necesaria la primera vez que se crea una instancia.
	 */
	public static SingletonLateInstanciationWithDoubleCheckLocking getInstance() {
		if (instance == null) {
			synchronized (SingletonLateInstanciationWithDoubleCheckLocking.class) {
				if (instance == null) {
					instance = new SingletonLateInstanciationWithDoubleCheckLocking();
				}
			}
		}

		return instance;
	}
}

/*
 * Requisitos:
 * 
 * 1. El constructor debe proporcionar un valor para cada propiedad.
 * 2. Todas las variables de instancia deben ser privadas y finales.
 * 3. No se deben debinir métodos setter.
 * 4. No se debe permitir el acceso a objetos mutables directamente.
 * 5. Prevenir que los métodos de la clase puedan ser sobrescritos.
 */
class Immutable {
	/*
	 * Debemos tener particular atencion con las colecciones
	 */
	private final List<String> elements;
	
	public Immutable(List<String> elements) {
		if (elements == null) {
			throw new RuntimeException("La lista de elementos no puede ser nula.");
		}
		/* Previene que se puedan modificar los elementos de 
		 * this.elements a través de elements.
		 */
		this.elements = new ArrayList<String>(elements);
		/*
		 * Esta variante no funciona!!!
		 * Porque hay dos referencias al mismo objeto por lo tanto
		 * no queda garantizada la inmutabilidad.
		 */
		// this.elements = elements; // NO FUNCIONA
	}

	/*
	 * No debemos compartir referencias a objetos mutables contenidos dentro de un objeto inmutable.
	 */
	public final List<String> getElements() {
		return Collections.unmodifiableList(elements);
	}
	
}

public class Leccion_02_05 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SingletonEarlyInstanciation singleton1 = SingletonEarlyInstanciation.getInstance();
		SingletonEarlyInstanciation singleton2 = SingletonEarlyInstanciation.getInstance();
		System.out.println(singleton1 == singleton2);
		
		List<String> elems = Arrays.asList("Alfa", "Bravo", "Charlie");
		Immutable immutable = new Immutable(elems);
		List<String> ret = immutable.getElements();
		// ret.add("Delta"); // Lanza una excepción del tipo java.lang.UnsupportedOperationException
		for(String string: ret) {
			System.out.println(string);
		}
	}

}
