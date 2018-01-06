/**
 * 
 */
package es.smartcoding.ocp.seccion07;

import java.util.ArrayList;
import java.util.List;
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
 *         Despues de hacer un submit de una colección de tareas, lo más común es esperar el resultado. Una solución es
 *         llamar al método get() en cada objeto Future que retorna el método submit().
 * 
 *         Pero si no necesitamos los resultados inmediatamente, hay un enfoque más simple: primero cerramos el service
 *         con el método shutdown() y a continuación invocamos el método awaitTermination(long timeout, TimeUnit unit)
 *         del executor.
 * 
 *         Este método espera el tiempo especificado para completar todas las tareas, o antes, si las tareas acaban en
 *         un tiempo inferior al especificado o se lanza una excepción InterruptedException.
 * 
 *         Revisa el código que acompaña a esta lección, responde a las preguntas planteadas y en definitiva, modifícalo
 *         para experimentar con los contenidos de esta lección.
 * 
 */

/*
 * Este Callable calcula el índice de masa corporal
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
	double imc = weight / Math.pow(height, 2);
	// (1)
	// Thread.sleep(1000);
	return imc;
    }

}

public class Leccion_07_06 {

    /**
     * Determina la salida de este código. Qué pasa si descomentas (1)? Qué pasa si comentas (2)?
     * 
     * Transforma este ejemplo para que pueda utilizarse el método:
     * 
     * <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException
     * 
     * @param args
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
	ExecutorService service = null;
	List<Future<Double>> futures = new ArrayList<>();
	try {
	    service = Executors.newCachedThreadPool();
	    futures.add(service.submit(new IMCJob(91.7, 1.815)));
	    futures.add(service.submit(new IMCJob(85.0, 1.815)));
	    futures.add(service.submit(new IMCJob(80.0, 1.815)));
	    futures.add(service.submit(new IMCJob(77.0, 1.815)));
	} finally {
	    if (service != null) {
		// (2)
		service.shutdown();
	    }
	}
	if (service != null) {
	    service.awaitTermination(1000, TimeUnit.MILLISECONDS);
	    if (service.isTerminated()) {
		System.out.println("Todas las tareas han acabado.");
		for (Future<Double> future : futures) {
		    System.out.printf("%3.3f%n", future.get());
		}
	    } else {
		System.out.println("Por lo menos una tarea esta en ejecución.");
	    }
	}
    }
}
