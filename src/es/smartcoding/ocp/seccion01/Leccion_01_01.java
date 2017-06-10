/**
 * 
 */
package es.smartcoding.ocp.seccion01;

/**
 * 
 * @author pep
 * 
 *         Diseño avanzado de clases
 * 
 *         El operador instanceof
 * 
 *         El operador instanceof suele utilizarse para identificar conversiones
 *         de tipo válidas en tiempo de ejecución.
 */
interface Flotante {
}

abstract class Barco implements Flotante {
}

class Crucero extends Barco {
}

class PortaAviones extends Barco {
}

public class Leccion_01_01 {

	public static void main(String[] args) {
		Flotante c = new Crucero();
		System.out.println("c instanceof Flotante: " + (c instanceof Flotante));
		System.out.println("c instanceof Barco: " + (c instanceof Barco));
		System.out.println("c instanceof Crucero: " + (c instanceof Crucero));
		System.out.println("c instanceof PortaAviones: " + (c instanceof PortaAviones));
		Crucero c2 = new Crucero();
		// System.out.println(c2 instanceof PortaAviones); // NO COMPILA: porque
		// son tipos incompatibles, un crucero no es un portaaviones.
		c2 = null;
		System.out.println("c2 instanceof Crucero: " + (c2 instanceof Crucero));
		System.out.println("null instanceof Object: " + (null instanceof Object));
		// El operador instanceof habitualmente se utiliza para hacer casts o
		// conversiones de tipo seguras.
		if (c instanceof Crucero) {
			Crucero crucero = (Crucero) c;
		}
	}

}