/**
 * 
 */
package es.smartcoding.ocp.seccion04;

/**
 * @author pep
 * 
 *         Programación Funcional
 * 
 *         Introducción
 * 
 *         Ya hemos visto que las expresiones lambda y las referencias a métodos se utilizan para implementar interfaces
 *         funcionales.
 * 
 *         Las expresiones lambda pueden acceder a las variables estáticas, variables de instancia, parámetros de
 *         métodos efectivamente finales y variables locales efectivamente finales.
 * 
 *         Una variable es efectivamente final cuando una vez inicializada no vuelve a modificarse. Es decir, una
 *         variable es efectivamente final cuando se inicializa una y sólo una vez.
 * 
 *         Revisa el código que acompaña a esta lección, responde a las preguntas planteadas y en definitiva, modifícalo
 *         para experimentar con los contenidos de esta lección. 
 *
 */

@FunctionalInterface interface I { String f(); }

class J {

    void g() {
	String msg = "Efectivamente final"; 
	// msg = "Ya no soy efectivamente final"; // (1)
	/* Qué pasa si eliminas el comentario de (1) */
	I i1 = () -> msg;
	System.out.println(i1.f());
    }

}

public class Leccion_04_01 {

    /**
     * Determina la salida del siguiente código.
     * 
     * @param args
     */
    public static void main(String[] args) {
	J j = new J();
	j.g();
    }

}



