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
 *         En ocasiones, necesitamos planificar tareas que empiencen posteriormente, en algún momento en el futuro. La
 *         interfaz ScheduledExecutorService que es una subinterfaz de ExecutorService está diseñada justamente para
 *         esta tarea.
 * 
 *         La interfaz ScheduledExecutorService proporciona cuatro métodos: dos método schedule(), un método
 *         scheduleAtFixedRate() y un método ScheduleAtFixedDelay(). consulta la tabla 7.5 del libro de referencia si
 *         quieres obtener información detallada.
 * 
 *         ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit)
 * 
 *         ScheduledFuture<V> schedule(Runnable command, long delay, TimeUnit unit)
 * 
 *         scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit)
 * 
 *         scheduleWitFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit)
 * 
 *         Los dos primeros métodos tienen un Callable y un Runnable como parámetro respectivamente, así como un retardo
 *         y una unidad de tiempo que puede ser segundos, milisegundos, etc. Ambos retornan un ScheduledFuture que es
 *         como un objeto Future que además proporciona el método getDelay().
 * 
 *         Los dos últimos pueden ser algo confusos: aunque ambos realizan una tarea repetitivamente después de un
 *         retardo inicial, el primero, scheduleAtFixedRate(), crea una tarea cada x unidades de tiempo, tanto si la
 *         tarea anterior ha acabado o no. El segundo, scheduleWithFixedDelay(), crea una nueva tarea cuando ha acabado
 *         la anterior. Por ejemplo, si la primera tarea comienza a las 12:00 y dura 5 minutos con un retardo de 2
 *         minutos, la siguiente tarea comenzará a las 12:07 aproximadamente.
 * 
 *         El servicio debe estar activo para que, llegado el momento, las tareas se ejecuten.
 * 
 *         Revisa el código que acompaña a esta lección, responde a las preguntas planteadas y en definitiva, modifícalo
 *         para experimentar con los contenidos de esta lección.
 *
 */
public class Leccion_07_07 {

    /**
     * Determina la salida del siguiente código. Qué pasa si comentas (1) y descomentas (2)? Qué pasa si descomentas (3)
     * 
     * @param args
     * 
     */
    public static void main(String[] args) {
	ScheduledExecutorService service = null;
	try {
	    service = Executors.newSingleThreadScheduledExecutor();
	    // (1)
	    service.scheduleAtFixedRate(() -> {
		// (2)
		// service.scheduleWithFixedDelay(() -> {
		System.out.printf("job %s %s%n", LocalTime.now(), Thread.currentThread().getName());
		try {
		    Thread.sleep(6_000);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }, 0, 5, TimeUnit.SECONDS);
	} finally {
	    if (service != null) {
		/*
		 * El servicio tiene que estar activo en el momento que deben arrancar los hilos.
		 */
		// (3)
		// service.shutdown();
	    }
	}

    }

}
