package es.smartcoding.ocp.seccion01;

/**
 * 
 * @author pep
 * 
 *         Diseño avanzado de clases
 * 
 *         El operador instanceof
 * 
 *         El operador instanceof suele utilizarse para identificar conversiones de tipo válidas en tiempo de ejecución.
 */
interface Flotante {}

abstract class Barco implements Flotante {}

class Crucero extends Barco {}

class PortaAviones extends Barco {}

public class Leccion_01_01 {

	public static void main(String[] args) {
		Flotante flotante = new Crucero();
		System.out.println("flotante instanceof Flotante: " + (flotante instanceof Flotante));
		System.out.println("flotante instanceof Barco: " + (flotante instanceof Barco));
		System.out.println("flotante instanceof Crucero: " + (flotante instanceof Crucero));
		System.out.println("flotante instanceof PortaAviones: " + (flotante instanceof PortaAviones));
		Crucero crucero = new Crucero();
		// System.out.println(crucero instanceof PortaAviones); // NO COMPILA: porque son tipos incompatibles, un crucero no es un portaaviones.
		crucero = null;
		System.out.println("crucero instanceof Crucero: " + (crucero instanceof Crucero));
		System.out.println("null instanceof Object: " + (null instanceof Object));
		// casts o conversiones de tipo seguras.
		if (flotante instanceof Crucero) {
			Crucero otroCrucero = (Crucero) flotante;
		}
	}

}