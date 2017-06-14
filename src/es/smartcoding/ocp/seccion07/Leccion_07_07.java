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
 *         Planificando tareas con ScheduledExecutorService
 * 
 *         En ocasiones, necesitamos planificar tareas que empiencen
 *         posteriormente, algún momento en el futuro. La interfaz
 *         ScheduledExecutorService que es una subinterfaz de ExecutorService
 *         está diseñada justamente para esta tarea.
 * 
 *         La interfaz ScheduledExecutorService proporciona cuatro métodos,
 *         consulta la tabla 7.5 del libro de referencia si quieres obtener
 *         información detallada.
 * 
 *         ScheduledFuture<V> schedule(Callable<V> callable, long delay,
 *         TimeUnit unit)
 * 
 *         ScheduledFuture<V> schedule(Runnable command, long delay, TimeUnit
 *         unit)
 * 
 *         scheduleAtFixedRate(Runnable command, long initialDelay, long period,
 *         TimeUnit unit)
 * 
 *         scheduleAtFixedDelay(Runnable command, long initialDelay, long delay,
 *         TimeUnit unit)
 * 
 *         Los dos primeros métodos tienen un Callable y un Runnable como
 *         parámetro respectivamente, así como un retardo y una unidad de tiempo
 *         que puede ser segundos, milisegundos, etc. Ambos retornan un
 *         ScheduledFuture que es como un objeto Future excepto que proporciona
 *         el método getDelay().
 * 
 *         Los dos últimos pueden ser algo confusos: aunque ambos realizan una
 *         tarea repetitivamente después de un retardo inicial, el primero,
 *         scheduleAtFixedRate(), crea una tarea cada x unidades de tiempo,
 *         tanto si la tarea anterior ha acabado o no. El segundo,
 *         scheduleAtFixedDelay(), crea una nueva tarea cuando ha acabado la
 *         anterior. Por ejemplo, su la primera tarea comienza a las 12:00 y
 *         dura 5 minutos con un retardo de 2 minutos, la siguiente tarea
 *         comenzará a las 12:07 aproximadamente.
 * 
 *         El servicio debe estar activo para que, llegado el momento, las
 *         tareas se ejecuten.
 * 
 * 
 *
 */
public class Leccion_07_07 {

	/**
	 * @param args
	 * 
	 *            En este ejemplo se pondrá en marcha un thread cada 15 segundos
	 *            o posteriormente, se el método run() tarda más.
	 */
	public static void main(String[] args) {
		ScheduledExecutorService service = null;
		try {
			service = Executors.newSingleThreadScheduledExecutor();
			service.scheduleAtFixedRate(() -> {
				System.out.println("job " + LocalTime.now() + " " + Thread.currentThread().getName());
				try {
					Thread.sleep(10_000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}, 0, 15, TimeUnit.SECONDS);
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
