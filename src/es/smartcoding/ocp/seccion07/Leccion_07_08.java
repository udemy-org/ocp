/**
 * 
 */
package es.smartcoding.ocp.seccion07;

import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author pep
 * 
 *         Concurrencia
 * 
 *         Pools de threads
 * 
 *         Mediante pools de threads podemos incrementar la concurrencia. La
 *         idea es tener un conjunto de threads para tener un percepción más
 *         real de concurrencia.
 * 
 *         Un pool o conjunto de threads es un grupo de threads ya instanciados
 *         y reusables que estan disponibles para llevar a cabo una serie de
 *         tareas arbitrarias. Consulta la tabla 7.6 del libro de referencia
 *         para ver una lista completa de los métodos que forman parte de los
 *         objetivos del examen OCP.
 * 
 *         Todos los métodos de la tabla 7.6 retornan un ExecutorService o
 *         ScheduledExecutorService.
 * 
 *         La diferencia entre un executor single-thread y pooled-thread es lo
 *         que pasa cuando una tarea ya se está ejecutando. Mientras un executor
 *         single-thread esperará a que un hilo quede disponible antes de
 *         comenzar la siguiente tarea, un executor pooled-thread puede comenzar
 *         la siguiente tarea concurrentemente. Si el pool se queda sin threads
 *         disponibles, la tarea quedaá en la cola hasta que le llegue su turno.
 * 
 *         El método newCachedThreadPool() creará un pool de threads sin un
 *         tamaño predefinido, y creará un nuevo thread cada vez que se requiera
 *         uno cuando todos los threads existentes esten ocupados. Este tipo de
 *         pool es especialmente adecuado para tareas asíncronas de corta
 *         duración y se desaconseja su uso para procesos de larga duración.
 * 
 *         El método newFixedThreadPool() prepara una serie de threads. Mientras
 *         haya menos tareas que threads disponibles, todas las tareas se
 *         ejecutaran concurrentemente. Pero en el momento en que haya más
 *         tareas que threads disponibles, tendrán que esperar su turno. De
 *         hecho, llamar al método newFixedThreadPool() con un parametro de 1 es
 *         equivalente a llamar al método newSingleThreadExecutor().
 * 
 *         El método newScheduledThreadPool() es idéntico al método
 *         newFixedThreadPool() excepto que retorna una instáncia de
 *         ScheduledExecutorService siendo por lo tanto compatible con tareas
 *         programadas.
 *
 */
public class Leccion_07_08 {

	/**
	 * @param args
	 * 
	 *            En la práctica puede ser bastante difícil escojer el número
	 *            apropiado de threads de un tread pool. El número disponible de
	 *            procesadores puede ser una guía. Si la tarea que han de
	 *            realizar los threads no es muy dependiente de la CPU podemos
	 *            tener 8 veces el número de procesadores.
	 * 
	 *            Este es el ejemplo anterior ahora con un pool de threads. Con
	 *            el método scheduledAtFixedRate() se obtiene se lanza un nuevo
	 *            thread cada 6 segundos, que es lo que tarda la tarea. En
	 *            cambio con el método scheduledAtFixedDelay() se lanza un nuevo
	 *            thread cada 8 segundo, 6 segundo que dura la tarea más 2
	 *            segundos de retardo.
	 * 
	 */
	public static void main(String[] args) {
		System.out.println(Runtime.getRuntime().availableProcessors());
		ScheduledExecutorService service = null;
		try {
			service = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors() * 8);
			// service.scheduleWithFixedRate(() -> {
			service.scheduleWithFixedDelay(() -> {
				System.out.println("job " + LocalTime.now() + " " + Thread.currentThread().getName());
				try {
					Thread.sleep(6_000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}, 0, 2, TimeUnit.SECONDS);
		} finally {
			if (service != null) {
				/*
				 * El servicio tiene que estar activo en el momento que deben
				 * arrancar los hilos.
				 */
				// service.shutdown();
			}
		}
	}

}
