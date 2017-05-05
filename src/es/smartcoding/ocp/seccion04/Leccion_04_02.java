/**
 * 
 */
package es.smartcoding.ocp.seccion04;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pep
 * 
 *         Los Genéricos
 * 
 *         Los genéricos son necesarios porque nos ayudan a prevenir que una
 *         lista de Strings contenga por ejemplo Numbers.
 * 
 *         Nosotros podemos definir nuestras propias clases, interfaces y
 *         métodos genéricos. En la clase genérica Box, el tipo genérico T está
 *         presente en toda la clase.
 *         
 *         
 * 
 */

interface Footwear {
}

class Shoe implements Footwear {
}

class Boot extends Shoe {
}

class Box<T> {

	private T content;

	public void setContent(T content) {
		this.content = content;
	}

}

public class Leccion_04_02 {

	/**
	 * @param args
	 *            Eduard: Susana de direccio 10:03
	 */
	public static void main(String[] args) {
		List<String> cadenas = new ArrayList<>();
		cadenas.add("Alfa"); // OK
		// Error en tiempo de compilación. No son compatibles.
		// cadenas.add(new Integer(1));
		Box<Footwear> cajaDeZapatos = new Box<>();

	}

}
