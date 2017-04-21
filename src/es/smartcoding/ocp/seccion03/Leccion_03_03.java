/**
 * 
 */
package es.smartcoding.ocp.seccion03;

import java.util.Date;

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
 *         La línea (1) no compila porque un elefante no es un animal domestico.
 *         
 *         La línea (2) en cambio, sí que compila pero genera un error en tiempo de ejecución.
 *         
 *         Para acabar, es importante reconocer las diferencias entre objeto y referencia.
 *         
 *         En Java todos los objetos son accedidos por referencia.
 *         
 *         Hay dos reglas que resumen este concepto:
 *         
 *         1. El tipo de un objeto determina qué propiedades existen dentro del objeto en memória.
 *         
 *         2. El tipo de la referencia de un objeto determina qué métodos son accesibles.
 *         
 *         En la línea (3) el objeto o aunque hace referencia a una instancia de tipo AnimalDomestico
 *         
 *         no puede acceder al método habla().
 *
 */

interface AnimalDomestico { void habla();}

abstract class Animal implements AnimalDomestico {}

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
		AnimalDomestico ad = new Vaca();
		ad.habla();
		ad = new Gallina();
		ad.habla();
		// Es necesario el cast porque no todos los objetos de tipo ComunicacionAnimal son una Gallina
		Gallina gallina = (Gallina) ad;
		// en este caso no es necesario el cast porque toda Gallina es de tipo ComunicacionAnimal
		ad = gallina;
		Elefante elefante = new Elefante();
		// no compila porque no estan relacionados
		// ca = elefante; // (1)
		// compila pero genera un error en tiempo de ejecución
		ad = (AnimalDomestico) elefante; // (2)
		Object o = ad; // (3)
		
	}

}
