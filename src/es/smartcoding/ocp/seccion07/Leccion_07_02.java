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
 *         La interfaz Runnable es una interfaz funcional con un único método
 *         que no tiene argumentos y que retorna void. Aunque desde Java 8 es
 *         una interfaz funcional, existe desde el inicio de Java.
 * 
 *         La manera más fácil de crear un thread o hilo es mediante la clase
 *         Thread. Require dos pasos, primero definimos la tarea que debe hacer
 *         el thread en su método run() y segundo lo ponemos en marcha mediante
 *         el método start().
 * 
 *         Java no ofrece ninguna garantía sobre el orden en que se ejecutara
 *         una serie de hilos ni el retardo que puedan tener.
 * 
 *         Como sabes, a la hora de crear un hilo una clase puede implementar la
 *         interfaz Runnable o extender la clase Thread. El método preferido es
 *         implementar la interfaz Runnable dado que Java no soporta la herencia
 *         múltiple, además, la API Concurrency hace un uso extensivo de objetos
 *         Runnable.
 *
 */
public class Leccion_07_02 {

	private static int counter = 0;

	/**
	 * @param args
	 */
	public static void main(String[] args) throws InterruptedException {
		new Thread(() -> {
			for (int i = 0; i < 500; i++)
				Leccion_07_02.counter++;
		}).start();
		while (Leccion_07_02.counter < 100) {
			System.out.println("Not reached yet");
			Thread.sleep(1000);
		}
		System.out.println("Reached!");

	}

}
