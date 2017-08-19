package es.smartcoding.ocp.seccion01;

/**
 * 
 * @author pep
 * 
 *         Diseño avanzado de clases
 * 
 *         Ejemplo de clase interna local
 * 
 *         Las clases internas locales tienen estas características:
 * 
 *         1. No pueden tener ningún modificador de acceso
 * 
 *         2. No pueden ser estáticas ni declarar ningún miembro estático
 * 
 *         3. Tienen acceso a todos los miembros de la clase contenedora (Leccion_01_06_B)
 * 
 *         4. Sólo tienen acceso a las variables locales finales o efectivamente finales (que se han inicializado exactamente una vez)
 * 
 */

public class Leccion_01_06_B {

	private double T = 1.1e0;

	public void calcula(final double euros) {
		int k = 3;
		// k++; // 'k' no seria efectivamente final
		class InnerLocalClass {
			public void calcula() {
				System.out.println(euros * T * k);
			}
		}
		InnerLocalClass c = new InnerLocalClass();
		c.calcula();
	}

	public static void main(String[] args) {
		Leccion_01_06_B l17 = new Leccion_01_06_B();
		l17.calcula(10.0);
	}

}






