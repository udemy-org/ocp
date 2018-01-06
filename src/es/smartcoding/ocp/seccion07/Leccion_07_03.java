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
 *         La interfaz ExecutorService está presente desde Java 5, extiende la interfaz Executor y define el método
 *         execute() con un Runnable como parámetro. La classe Executors también presente desde Java 5, ofrece numerosos
 *         métodos de factoria para crear objetos de tipo ExecutorService.
 * 
 *         ExecutorService ofrece una serie de características como thread pooling, thread scheduling etc. que sería muy
 *         difícil de implementar por nosostros.
 * 
 *         Revisa el código que acompaña a esta lección, responde a las preguntas planteadas y en definitiva, modifícalo
 *         para experimentar con los contenidos de esta lección.
 *
 */
public class Leccion_07_03 {

    /**
     * Determina la salida de este código.
     * 
     * @param args
     */
    public static void main(String[] args) {
	ExecutorService service = null;
	try {
	    /*
	     * Con un single-thread tenemos la garantía que las tareas se haran en el orden en que se añaden al
	     * ExecutorService, aunque si el número de threads es muy elevado, puede no ser el caso.
	     * 
	     * Main por su puesto sigue siendo un thread independiente
	     */
	    service = Executors.newSingleThreadExecutor();
	    System.out.println("En pista de despegue " + Thread.currentThread().getName());
	    service.execute(() -> {
		System.out.println("Cerrando puertas " + Thread.currentThread().getName());
		try {
		    Thread.sleep(3000);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    });
	    service.execute(() -> {
		System.out.println("Comprobando el sistema " + Thread.currentThread().getName());
		try {
		    Thread.sleep(3000);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    });
	    service.execute(() -> {
		for (int i = 0; i < 4; i++) {
		    System.out.println("Motor " + i + " OK " + Thread.currentThread().getName());
		    try {
			Thread.sleep(1000);
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }
		}
		System.out.println("Listo para despegar " + Thread.currentThread().getName());
	    });

	} finally {
	    if (service != null)
		/*
		 * Si no cerramos el ExecutorService, el programa no acabará. una vez cerrado, es ilegal enviar nuevas
		 * tareas.
		 * 
		 * Si intentáramos enviar un nuevo proceso se produciría una excepción de tipo
		 * RejectedExecutionException.
		 * 
		 * Por su puesto, el método shutdown() NO para las tareas en marcha.
		 */
		service.shutdown();
	    System.out.println(service.isShutdown());
	    System.out.println(service.isTerminated());
	}

    }

}
