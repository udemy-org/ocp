package es.smartcoding.ocp.seccion01;

/**
 * 
 * @author pep
 * 
 *         Diseño avanzado de clases
 * 
 *         Métodos virtuales
 * 
 *         Desde Java 8 se conocen a los métodos no static como métodos virtuales.
 * 
 *         Como ves en este ejemplo, Java llama al método virtual come() correspondiente, en función del objeto al que hace referencia, lo que se determina en
 *         tiempo de ejecución.
 * 
 *         Pero en el caso de las campos no ocurre lo mismo. Java accede al campo en función del tipo de la instancia y no del tipo de objeto al que hace
 *         referencia.
 *
 */
abstract class Animal {
	String nombre = "??????";

	abstract void come();
}

class Vaca extends Animal {
	String nombre = "Vaca";

	@Override
	void come() {
		comePastos();
	}

	private void comePastos() {
		System.out.println("comiendo pastos...");
	}

}

class Foca extends Animal {
	String nombre = "Foca";

	@Override
	void come() {
		comePeces();
	}

	private void comePeces() {
		System.out.println("comiendo peces...");
	}

}

public class Leccion_01_02 {

	public static void main(String[] args) {
		Animal foca = new Foca();
		Animal vaca = new Vaca();
		foca.come();
		vaca.come();
		System.out.println(foca.nombre);
		System.out.println(vaca.nombre);

	}

}