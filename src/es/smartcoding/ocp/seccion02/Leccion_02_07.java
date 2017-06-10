/**
 * 
 */
package es.smartcoding.ocp.seccion02;

/**
 * @author pep
 * 
 * 
 *         El patrón de diseño creacional Factory resuelve el problema de cómo
 *         crear objetos aun cuando el tipo concreto de objeto no sea conocido
 *         hasta en tiempo de ejecución.
 * 
 *         El patrón Factoria también conocido como el patrón Method Factory, es
 *         un patrón creacional que se base en la idea de crear objetos en
 *         función de un conjunto de parámetros de entrada. Es similar al patrón
 *         Builder excepto que se centra en proporcionar polimorfismo.
 * 
 *         Igual que en el patrón Builder los constructores podrian privados o
 *         protegidos si la clase Factory fuera una clase interna o estuviera en
 *         el mismo paquete respectivamente.
 *         
 *         EL APARTADO ANTERIOR SE DEJA COMO EJERCICIO.
 *
 */

abstract class Vehiculo {
}

class Turismo extends Vehiculo {
}

class Deportivo extends Vehiculo {
}

class TodoTerreno extends Vehiculo {
}

class VehiculoFactory {
	public static Vehiculo getVehiculo(int ocupantes) {
		Vehiculo vehiculo = null;

		switch (ocupantes) {
		case 1:
		case 2:
			vehiculo = new Deportivo();
			break;
		case 3:
		case 4:
		case 5:
			vehiculo = new Turismo();
			break;
		default:
			vehiculo = new TodoTerreno();
			break;
		}

		return vehiculo;
	}
}

public class Leccion_02_07 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Vehiculo v = VehiculoFactory.getVehiculo(3);
		System.out.println(v);
	}

}
