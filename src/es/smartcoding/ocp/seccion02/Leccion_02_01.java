package es.smartcoding.ocp.seccion02;

/**
 * 
 * @author pep
 * 
 *         Principios de diseño y patrones
 * 
 *         Diseño de una interfaz.
 * 
 *         Las interfaces facilitan el desarrollo de software. Se trata de un contrato que la clase que la implementa debe de cumplir.
 * 
 *         Permite el prototipado rápido de una aplicación dejando los detalles de la implementación para más adelante.
 * 
 *         Algunas interfaces no declaran métodos, como java.io.Serializable, lo que se conoce como una interfaz de marca (marker interface).
 * 
 *         Otras, declaran exáctamente un único método abstracto (y posiblemente otros de tipo default y/o static). Son las interfaces funcionales que veremos
 *         posteriormente.
 * 
 */

interface Cuenta {

	public static final double INTERES = 3.6;

	/*
	 * Desde Java 8 las interfaces permiten definir métodos estáticos. Vienen a ser métodos de ayuda (helper methods).
	 */
	public static double interesReducido() {
		return INTERES * 0.75;
	}
}

interface CuentaBancaria extends Cuenta {

	public abstract void ingresa(final double ingreso);

	public abstract void abona(final double abono);

	/*
	 * Tambien desde Java 8 las interfaces pueden definir métodos por defecto. Estos métodos permiten añadir nuevas funcionalidades a interfaces antiguas
	 * (legacy code) sin necesidad de modificar las clases de las implementan.
	 * 
	 * strictfp es una palabra reservada de Java que restringe las operaciones en coma flotante para asegurarnos las portabilidad.
	 */
	public default strictfp double calculaInteres(final double cantidad, final double interes) {
		return cantidad * interes / 100;
	}

}

public class Leccion_02_01 {}






