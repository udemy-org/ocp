/**
 * 
 */
package es.smartcoding.ocp.seccion02;
/**
 * 
 * @author pep
 * 
 *         Diseño de una interfaz.
 *         
 *         Las interfaces facilitan el desarrollo de software. Es un contrato que las clases que las implementan deben cumplir.
 *         
 *         Permite el prototipado rápido de una aplicación dejando los detalles de la implementación para más adelante.
 *         
 *         Algunas interfaces no declaran métodos, como java.io.Serializable, lo que se conoce como una interfaz de marca (marker interface).
 *         
 *         Otras, declaran exáctamente un único método abstracto (y posiblemente otros de tipo default y/o static). Son las interfaces funcionales que veremos posteriormente.
 *
 */

interface CuentaBancaria {

	public static final double INTERES = 3.6;

	public abstract void ingresa(final double ingreso);

	public abstract void abona(final double abono);

	public default strictfp double calculaInteres(final double cantidad,
			final double interes) {
		return cantidad * interes / 100;
	}

	public static double interesReducido() {
		return INTERES * 0.75;
	}
}

public class Leccion_02_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}