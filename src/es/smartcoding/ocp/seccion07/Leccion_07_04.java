/**
 * 
 */
package es.smartcoding.ocp.seccion07;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author pep
 * 
 *         Concurrencia
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

	private static int counter = 0;

	/**
	 * @param args
	 * 
	 *            El método submit()
	 * 
	 *            La API Concurrency permite hacer tareas avanzadas con hilos
	 *            sin ni siquera utilizar la clase Thread directamente.
	 * 
	 *            El valor de retorno de una Future está determinado por el
	 *            valor de retorno del método run() de la interfaz Runnable, que
	 *            como sabes es void. Por lo tanto el método get() en este caso
	 *            retornará null.
	 * 
	 *            En este ejemplo, procesamos un Runnable con el método submit()
	 *            y guardamos el valor de retorno en la variable result de tipo
	 *            Future. Después esperamos 10 segundos y obtenemos el valor de
	 *            retorno, si no estuviera disponible se lanzaría una excepcion
	 *            de tipo TimeoutException. Como he adelantado anteriormente el
	 *            valor de retorno es null porque sencillamente el método run()
	 *            de Runnable retorna void.
	 * 
	 * 
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService service = null;
		try {
			service = Executors.newSingleThreadExecutor();
			Future<?> result = service.submit(() -> {
				for (int i = 0; i < 500; i++)
					Leccion_07_04.counter++;
			});
			Object object = result.get(10, TimeUnit.SECONDS);
			System.out.println("Reached! " + object);
		} catch (TimeoutException e) {
			System.out.println("Not reached in time");
		} finally {
			if (service != null)
				service.shutdown();
		}
	}

}
