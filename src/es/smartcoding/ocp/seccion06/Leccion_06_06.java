/**
 * 
 */
package es.smartcoding.ocp.seccion06;

/**
 * @author pep
 * 
 *         Excepciones y aserciones
 * 
 *         Trabajando con aserciones
 * 
 *         Una aserción o afirmación es una expresión booleana que situamos en algún lugar de nuestro código donde
 *         esperamos que sea cierta.
 * 
 *         En castellano, assert significa que algo es cierto. Una orden assert contiene la expresión y opcionalmente
 *         una cadena.
 * 
 *         Las aserciones nos permiten detectar defectos en nuestro codigo. Pueden activarse durante la fase de
 *         depuración y desactivarse durante en producción.
 * 
 *         Las aserciones se pueden utilizar por muchas razones:
 * 
 *         1. Invariantes internas: confirmamos que se da una cierta restricción.
 * 
 *         2. Invariantes de clase: comprobamos la validez del estado de un objeto.
 * 
 *         3. Invariantes de control de flujo: ayudan a detectar unreacheble code. (lo hace el propio compilador).
 * 
 *         4. Precondiciones: nos aseguramos de que se da una cierta condición antes de invocar a un método·
 * 
 *         5. Post condiciones: nos aseguramos de que se da una cierta condición después de invocar un método.
 * 
 *         Pero dado que las aserciones pueden habilitarse y deshabilitarse pasando los parámetros -enableassert o
 *         -disableassert respectivamente a la maquina virtual, no deben contener código que forme parte de la lógica
 *         del programa.
 * 
 *         Para acabar, recuerda que puedes pasar parámetros tanto al main como a la maquina virtual desde el menú
 *         Run/Run Configurations, pestaña Arguments.
 * 
 */
public class Leccion_06_06 {

    /*
     * No uses aserciones para comprobar la validez de los argumentos de un método. Es más conveniente lanzar una
     * IllegalArgumentException.
     * 
     */
    static double div(double num, double den) {
	// assert den != 0 : "División por cero"; // NO!
	if (den == 0) {
	    throw new IllegalArgumentException("El denominador no puede ser 0");
	}
	return num / den;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
	if (args.length > 0) {
	    double num = Double.parseDouble(args[0]);
	    double den = Double.parseDouble(args[1]);
	    assert den != 0 : "Division por cero no permitida";
	    System.out.println(div(num, den));
	}

    }

}
