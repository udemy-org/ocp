/**
 * 
 */
package es.smartcoding.ocp.seccion07;

/**
 * @author pep
 * 
 *         Concurrency
 * 
 *         ExecutorService
 * 
 *         La interfaz ExecutorService extiende la interfaz Executor de donde
 *         hereda el método execute(). Una alternativa al método execute() es el
 *         método submit() que puede hacer todo lo que hace el método execute()
 *         y además retorna un objeto Future que representa el resultado
 *         pendiente de la tarea.
 * 
 *         Junto con los métodos execute() y submit(), debes conocer los métodos
 *         invokeAll() e invokeAny() que toman una colección de tareas y de
 *         forma síncrona los ejecutan todos o el primero que acabe
 *         respectivamente.
 * 
 *         Pero, cómo podemos saber que una tarea ha finalizado? El método
 *         submit() retorna un objeto java.util.concurrent. Future<V>, o
 *         Future<V> para los amigos que puede utilizarse para conocer el estado
 *         del resultado.
 *
 */
public class Leccion_07_04 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
