/**
 * 
 */
package es.smartcoding.ocp.seccion03;

/**
 * @author pep
 * 
 *         Implementando Polimorfismo.
 * 
 *         El polimorfismo es la característica por la que una interfaz puede tener múltiples implementaciones.
 *         
 *         En Java a todos los objetos se accede mediante referencias, lo que significa que no tenemos acceso directo a la memoria que ocupa el objeto mismo.
 *         
 *         Por lo tanto el objeto es la entidad que existe en memoria que Java a reservado.
 *         
 *         Cuando reasignamos un objeto cualquiera a una referencia de tipo Object, el objeto en si no cambia, lo que cambia es que ya no podemos acceder a las propiedades del objeto original.
 *         
 *         En cambio, podemos volver a acceder a las propiedades del objeto original si hacemos el cast adecuado.
 *          
 *         Un cast, sin embargo, sigue unas reglas básicas:
 *         
 *         1. Si vamos de subclase a superclase no necesitamos ningún casting explícito, (por ejemplo, de gallina a animal).
 *         
 *         2. El camino contrario de superclase a subclase sí que necesita un casting explícito, (por ejemplo, de animal a gallina).
 *         
 *         3. Una conversion (cast) de tipos que no estan relacionados generan un error en tiempo de compilación.
 *         
 *         4. Incluso cuando el código compila sin problemas, puede lanzare una excepción en tiempo de ejecución si la transformación no es válida.
 *         
 *         
 *         
 *         #113
 *
 */

interface ComunicacionAnimal { void habla();}

abstract class Animal implements ComunicacionAnimal {}

class Gallina extends Animal {

	@Override
	public void habla() {
		System.out.println("cocococ");
		
	}
	
}

class Vaca extends Animal {

	@Override
	public void habla() {
		System.out.println("muuuuuuuuu");
		
	}
	
}

class Elefante {}


public class Leccion_03_03 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ComunicacionAnimal ca = new Vaca();
		ca.habla();
		ca = new Gallina();
		ca.habla();
		// Es necesario el cast porque no todos los objetos de tipo ComunicacionAnimal son una Gallina
		Gallina gallina = (Gallina) ca;
		// en este caso no es necesario el cast porque toda Gallina es de tipo ComunicacionAnimal
		ca = gallina;
		Elefante elefante = new Elefante();
		// no compila porque no estan relacionados
		// ca = elefante;
		// The compiler will not allow casts to unrelated types ???
		// compila pero genera un error en tiempo de ejecución
		ca = (ComunicacionAnimal) elefante;
	}

}
