/**
 * 
 */
package es.smartcoding.ocp.seccion07;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author pep
 * 
 *         Concurrencia
 * 
 *         Submit de un conjunto de tareas
 * 
 *         Despues de hacer un submit de una colección de tareas, lo más común
 *         es esperar el resultado. Una solución es llamar al método get() en
 *         cada objeto Future que retorna el método submit(). Pero si no
 *         necesitamos los resultados, hay un enfoque más simple: primero
 *         cerramos el service con el método shutdown() y a continuación
 *         invocamos el método awaitTermination(long timeout, TimeUnit unit) del
 *         executor. Este método espera el tiempo especificado para completar
 *         todas las tareas, o antes, si las tareas acaban en un tiempo inferior
 *         al especificado o se lanza una excepción InterruptedException.
 * 
 *
 */

/*
 * Este Callable calcula ell índice de masa corporal
 */
class IMCJob implements Callable<Double> {

	private double weight;
	private double height;

	public IMCJob(double weight, double height) {
		this.weight = weight;
		this.height = height;
	}

	@Override
	public Double call() throws Exception {
		return weight / Math.pow(height, 2);
	}

}

public class Leccion_07_06 {

	/**
	 * @param args
	 * 
	 *            Este ejemplo ilustra el caso donde esperamos 1 segundo a que
	 *            el servicio termine.
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService service = null;
		Future<Double> future = null;
		try {
			service = Executors.newCachedThreadPool();
			future = service.submit(new IMCJob(85.0, 1.80));
		} finally {
			if (service != null) {
				service.shutdown();
			}
		}
		if (service != null) {
			service.awaitTermination(1, TimeUnit.MINUTES);
			if (service.isTerminated()) {
				System.out.println("Todas las tareas han acabado.");
				System.out.println(future.get());
			} else {
				System.out.println("Por lo menos una tarea esta en ejecución.");
			}
		}

	}

}
