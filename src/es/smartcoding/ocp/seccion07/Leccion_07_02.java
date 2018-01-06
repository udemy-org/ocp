/**
 * 
 */
package es.smartcoding.ocp.seccion07;

/**
 * @author pep
 * 
 *         Concurrencia
 * 
 *         Runnable
 * 
 *         La interfaz Runnable es una interfaz con un único método run() que no tiene argumentos y que retorna
 *         void. Aunque desde Java 8 es una interfaz funcional, existe desde las primeras versiones de Java.
 * 
 *         La manera más fácil de crear un thread o hilo es mediante la clase Thread. Require dos pasos, primero
 *         definimos la tarea que debe hacer el thread en su método run() y segundo lo ponemos en marcha mediante el
 *         método start().
 * 
 *         Java no ofrece ninguna garantía sobre el orden en que se ejecutara una serie de hilos ni el retardo que
 *         puedan tener.
 * 
 *         Como sabes, a la hora de crear un hilo una clase puede implementar la interfaz Runnable o extender la clase
 *         Thread. El método preferido es implementar la interfaz Runnable dado que Java no soporta la herencia
 *         múltiple, además, la API Concurrency hace un uso extensivo de objetos Runnable.
 *
 *         Revisa el código que acompaña a esta lección, responde a las preguntas planteadas y en definitiva, modifícalo
 *         para experimentar con los contenidos de esta lección.
 */
public class Leccion_07_02 {

    private static int counter = 0;

    /**
     * Determina la salida de este código.
     * 
     * Reescribe la expresión (1) sin lambda.
     * 
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
	// (1)
	new Thread(() -> {
	    for (;;) {
		try {
		    Thread.sleep(100);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		Leccion_07_02.counter++;
	    }
	}).start();
	
	while (Leccion_07_02.counter < 10) {
	    System.out.println("Cuenta actual " + Leccion_07_02.counter);
	}
	System.out.println("Main acaba!");

    }

}

