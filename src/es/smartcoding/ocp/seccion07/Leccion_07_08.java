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
 *         Mediante los pools de threads podemos incrementar la concurrencia. La idea es tener un conjunto de threads
 *         para tener un percepción más real de concurrencia.
 * 
 *         Un pool o conjunto de threads es un grupo de threads ya instanciados y reusables que estan disponibles para
 *         llevar a cabo una serie de tareas arbitrarias. Consulta la tabla 7.6 del libro de referencia para ver una
 *         lista completa de los métodos que forman parte de los objetivos del examen OCP.
 * 
 *         Todos los métodos de la tabla 7.6 retornan un ExecutorService o ScheduledExecutorService.
 * 
 *         La diferencia entre un executor single-thread y pooled-thread es lo que pasa cuando una tarea ya se está
 *         ejecutando. Mientras un executor single-thread esperará a que un hilo quede disponible antes de comenzar la
 *         siguiente tarea, un executor pooled-thread puede comenzar la siguiente tarea concurrentemente. Si el pool se
 *         queda sin threads disponibles, la tarea permanecerá en la cola hasta que le llegue su turno.
 * 
 *         El método newCachedThreadPool() creará un pool de threads sin un tamaño predefinido, y creará un nuevo thread
 *         cada vez que se requiera uno cuando todos los threads existentes esten ocupados. Este tipo de pool es
 *         especialmente adecuado para tareas asíncronas de corta duración y se desaconseja su uso para procesos de
 *         larga duración.
 * 
 *         El método newFixedThreadPool() prepara una serie de threads. Mientras haya menos tareas que threads
 *         disponibles, todas las tareas se ejecutaran concurrentemente. Pero en el momento en que haya más tareas que
 *         threads disponibles, tendrán que esperar su turno. De hecho, llamar al método newFixedThreadPool() con un
 *         parametro de 1 es equivalente a llamar al método newSingleThreadExecutor().
 * 
 *         El método newScheduledThreadPool() es idéntico al método newFixedThreadPool() excepto que retorna una
 *         instáncia de ScheduledExecutorService siendo por lo tanto compatible con tareas programadas.
 * 
 *         En la práctica puede ser bastante difícil escojer el número apropiado de threads de un tread pool. El número
 *         disponible de procesadores puede ser una guía. Si la tarea que han de realizar los threads no es muy
 *         dependiente de la CPU podemos tener 8 veces el número de procesadores.
 * 
 *         Revisa el código que acompaña a esta lección, responde a las preguntas planteadas y en definitiva, modifícalo
 *         para experimentar con los contenidos de esta lección.
 *
 */
public class Leccion_07_08 {

    /**
     * Determina la salida del siguiente código. Qué pasa si modificas (1) a 500 milisegundos?
     * 
     * @param args
     * 
     */
    public static void main(String[] args) {
	System.out.printf("Número de procesadores: %d%n", Runtime.getRuntime().availableProcessors());
	ScheduledExecutorService service = null;
	try {
	    service = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors() * 8);
	    /*
	     * Con el método scheduledAtFixedRate() se lanza un nuevo thread cada 6 segundos, que es lo que tarda la
	     * tarea. EN MI OPINIÓN DEBERÍA LANZAR UN THREAD CADA 2 SGUNDOS MIENTRAS HAYA THREADS DISPONIBLES pero como
	     * dice la documentación: If any execution of this task takes longer than its period, then subsequent
	     * executions may start late, but will not concurrently execute!!!
	     */
	    service.scheduleAtFixedRate(() -> {
		/*
		 * En cambio con el método scheduledWithFixedDelay() se lanza un nuevo thread cada 8 segundos, 6
		 * segundos que dura la tarea más 2 segundos de retardo.
		 */
		// service.scheduleWithFixedDelay(() -> {
		System.out.printf("job %s %s%n", LocalTime.now(), Thread.currentThread().getName());
		try {
		    // (1)
		    Thread.sleep(6_000);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }, 0, 2, TimeUnit.SECONDS);
	} finally {
	    if (service != null) {
		/*
		 * El servicio tiene que estar activo en el momento que deben arrancar los hilos.
		 */
		// service.shutdown();
	    }
	}
    }

}
