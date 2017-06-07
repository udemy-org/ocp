/**
 * 
 */
package es.smartcoding.ocp.seccion05;

/**
 * @author pep
 * 
 *         Functional Programming
 * 
 *         Ya hemos visto que las expresines lambda y referencias a métodos se
 *         utilizan para implementar interfaces funcionales, en este capítulo
 *         profundizamos en el trabajo con streams de la API Streams, que no
 *         esta relacionado con los streams del paquete java.io.
 * 
 *         Las expresiones lambdas pueden acceder variables estáticas, variables
 *         de instancia, parámetros de métodos efectivamente finaled y variables
 *         locales efectivamente locales.
 * 
 *         Una variable es efectivamente final cuando una vez inicializada no
 *         vuelve a modifciarse.
 *
 */

@FunctionalInterface
interface I {
	String f();
}

class J {

	void g() {
		String msg = "Efectivamente final";
		// msg = "Ya no soy efectivamente final";
		/*
		 * Si descomentas la línea anterior la variable local msg deja de ser
		 * efectivamente final.
		 */
		I i1 = () -> msg;
	}

}

public class Leccion_05_01 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
