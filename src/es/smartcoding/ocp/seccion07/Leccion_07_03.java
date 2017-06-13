/**
 * 
 */
package es.smartcoding.ocp.seccion07;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author pep
 * 
 *         Concurrencia
 * 
 *         ExecutorService
 * 
 *         A la hora de trabajar con la API Concurrency lo primero que haremos
 *         será crear un objeto de tipo ExecutorService. A partir de aquí, basta
 *         enviar las tareas a realizar. ExecutorService ofrece una serie de
 *         características como thread pooling, thread scheduling etc. que sería
 *         muy difícil de implementar por nosostros.
 * 
 * 
 *
 */
public class Leccion_07_03 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService service = null;
		try {
			/*
			 * Con un single-thread tenemos la garantía que las tareas se haran
			 * en el orden en que se añaden al ExecutorService, aunque si el
			 * número de threads es muy elevado, puede no ser el caso.
			 * 
			 * Main por su puesto sigue siendo un thread independiente
			 */
			service = Executors.newSingleThreadExecutor();
			System.out.println("En pista de despegue " + Thread.currentThread().getName());
			service.execute(() -> System.out.println("Cerrando puertas " + Thread.currentThread().getName()));
			service.execute(() -> System.out.println("Comprobando el sistema " + Thread.currentThread().getName()));
			service.execute(() -> {
				for (int i = 0; i < 4; i++)
					System.out.println("Motor " + i + " OK " + Thread.currentThread().getName());
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});

			System.out.println("Listo para despegar " + Thread.currentThread().getName());
		} finally {
			if (service != null)
				/*
				 * Si no cerramos el ExecutorService, el programa no acabará. A
				 * partir de este momento, es ilegal enviar nuevas tareas. Si
				 * intentáramos enviar un nuevo proceso se produciría una
				 * excepción de tipo RejectedExecutionException.
				 * 
				 * Por su puesto, el método shutdown() NO para las tareas en
				 * marcha.
				 */
				service.shutdown();
			System.out.println(service.isShutdown());
			System.out.println(service.isTerminated());
		}

	}

}
