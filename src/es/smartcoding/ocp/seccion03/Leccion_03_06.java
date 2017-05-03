/**
 * 
 */
package es.smartcoding.ocp.seccion03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author pep
 * 
 *         El patron de diseño creacional Builder resuelve el problema de cómo
 *         crear un objeto que requiere establecer numerosos valores a la hora
 *         de crearlo.
 * 
 *         Llega un momento cuando el contructor puede crecer en exceso debido
 *         al gran número de parámetros que requiere un clase. Un constructor
 *         con un excesivo número de parámetros se conoce como el 'telescoping
 *         constructor anti-pattern'.
 * 
 *         Un anti-patrón es una solución común a un problema recurrente que
 *         tiende a ser poco manejable y de difícil manteninmiento.
 * 
 *         Las clases AnimalDeCompañia y AnimalDeCompañiaBuilder esta
 *         estrechamente ligadas, lo que quiere decir que un cambio en una
 *         implica cambios en la otra. Aunque esta es una característica que
 *         debemos evitar, en este caso es necesaria.
 * 
 *         Frecuentemente, una clase builder se proporciona junto la clase
 *         objetivo, AnimalDeCompañia en este caso, ya sea como una clase
 *         estática interna o dentro del mismo paquete lo que facilita la edicón
 *         de cambios. Otra ventaja es que los diseñadores pueden declarar el
 *         constructor como private o protected, lo que obligaria a utilizar la
 *         clase builder para crear instancias de la clase objetivo.
 *         
 *         EL APARTADO ANTERIOR SE DEJA COMO EJERCICIO.
 * 
 */

class AnimalDeCompañia {
	private String nombre;
	private String raza;
	private List<String> alimentosPreferidos = new ArrayList<>();

	public AnimalDeCompañia(String nombre, String raza, List<String> alimentosPreferidos) {

		this.nombre = nombre;
		this.raza = raza;
		this.alimentosPreferidos = new ArrayList<>(alimentosPreferidos);
	}

	// ...
}

class AnimalDeCompañiaBuilder {
	private String nombre;
	private String raza;
	private List<String> alimentosPreferidos;

	public AnimalDeCompañiaBuilder setNombre(String nombre) {
		this.nombre = nombre;
		return this;
	}

	public AnimalDeCompañiaBuilder setRaza(String raza) {
		this.raza = raza;
		return this;
	}

	public AnimalDeCompañiaBuilder setAlimentosPreferidos(String... alimentos) {
		alimentosPreferidos = Arrays.asList(alimentos);
		return this;
	}

	public AnimalDeCompañia build() {
		return new AnimalDeCompañia(nombre, raza, alimentosPreferidos);
	}

}

public class Leccion_03_06 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AnimalDeCompañia gato = new AnimalDeCompañiaBuilder().setNombre("Tom").setRaza("Siames")
				.setAlimentosPreferidos("ratones").build();

	}

}
