/**
 * 
 */
package es.smartcoding.ocp.seccion07;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author jmendez
 * 
 *         Concurrencia
 * 
 *         Gestión de procesos concurrentes
 * 
 *         La API de concurrencia incluye clases que pueden ser utilizadas para gestionar las tareas de un proceso entre
 *         un grupo de threads relacionados. Estan diseñadas para ser usadas en escenarios específicos. Durante el
 *         examen debes estar familiarizado con las clases CyclicBarrier y ForkJoinPool.
 * 
 *         La clase CyclicBarrier representa una barrera de sincronización de threads. Su constructor esta sobrecargado,
 *         uno tiene solamente un parámetro, el número de threads que debe esperar en la barrera. Y el otro además tiene
 *         como parámetro un Runnable que ejecutará el último thread tras acabar la tarea actual.
 * 
 *         Supongamos que queremos vaciar, limpiar y llenar de agua un deposito todo de forma manual. Este es una
 *         proceso que a un solo operario le llevaria demasiado tiempo. Sin embargo, si contratamos varios operarios que
 *         trabajen conjuntamente en cada tarea podria ser mucho más eficiente. Por supuesto, los operarios deben
 *         gestionarse adecuadamente. Mientras los operarios achicando agua del depósito no se puede entrar a limpiar, y
 *         mientras se está limpiando, no se puede empezar a acarrear el agua para llenarlo.
 * 
 *         Con ese objetivo, podríamos utilizar la clase CyclicBarrier, una barrera reutilizable, que nos permite
 *         sincronizar threads entre ellos y esperar a que todos ellos acaben una cierta tarea antes de comenzar la
 *         siguiente.
 * 
 *         Revisa el código que acompaña a esta lección, responde a las preguntas planteadas y en definitiva, modifícalo
 *         para experimentar con los contenidos de esta lección.
 * 
 */

class Deposito {
    /*
     * La propiedad 'nivel' va de 0 (vacío) a 100 (lleno)
     * 
     * Inicialemente lleno (100)
     */
    private AtomicInteger nivel = new AtomicInteger(100);
    /*
     * La propiedad 'progresoLimpieza' va de 0 (sucio) a 100 (limpio)
     * 
     * Inicialmente sucio (0)
     */
    private AtomicInteger progresoLimpieza = new AtomicInteger(0);

    public void vacia() {
	System.out.printf("Vaciando desde thread %s nivel de vaciado: %d.%n", Thread.currentThread().getName(),
		nivel.getAndDecrement());
	try {
	    Thread.sleep(10);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
    }

    public boolean vacio() {
	return nivel.intValue() <= 0;
    }

    public void llena() {
	System.out.printf("Llenando desde thread %s, nivel de llenado: %d.%n", Thread.currentThread().getName(),
		nivel.getAndIncrement());
	try {
	    Thread.sleep(10);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
    }

    public boolean lleno() {
	return nivel.intValue() >= 100;
    }

    public void limpia() {
	System.out.printf("Limpiando desde thread %s, progreso de liempieza: %d.%n", Thread.currentThread().getName(),
		progresoLimpieza.getAndIncrement());
	try {
	    Thread.sleep(10);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
    }

    public boolean limpio() {
	return progresoLimpieza.intValue() >= 100;
    }
}

class Trabajador implements Runnable {

    private Deposito deposito;
    private CyclicBarrier barrier;

    public Trabajador(Deposito deposito, CyclicBarrier barrier) {
	this.deposito = deposito;
	this.barrier = barrier;
    }

    @Override
    public void run() {
	while (!deposito.vacio()) {
	    deposito.vacia();
	}
	/*
	 * Esperamos a que acaben la tarea actual todos los threads involucrados.
	 */
	try {
	    barrier.await();
	} catch (InterruptedException | BrokenBarrierException e) {
	    e.printStackTrace();
	}
	while (!deposito.limpio()) {
	    deposito.limpia();
	}
	try {
	    barrier.await();
	} catch (InterruptedException | BrokenBarrierException e) {
	    e.printStackTrace();
	}
	while (!deposito.lleno()) {
	    deposito.llena();
	}
	try {
	    barrier.await();
	} catch (InterruptedException | BrokenBarrierException e) {
	    e.printStackTrace();
	}
    }

}

public class Leccion_07_18 {

    /**
     * Determina la salida del código siguiente. Que pasa si modificas (1) a NUM_THREAD - 1?
     * 
     * @param args
     */
    public static void main(String[] args) {
	final int NUM_THREADS = 3;

	Deposito deposito = new Deposito();
	CyclicBarrier barrier = new CyclicBarrier(NUM_THREADS, () -> System.out.println("*** Tarea completada ***"));
	ExecutorService service = Executors.newFixedThreadPool(NUM_THREADS);
	/*
	 * Si creamos menos threads de los que espera el CyclicBarrier quedará bloqueado.
	 */
	// (1)
	for (int i = 0; i < NUM_THREADS; i++) {
	    service.execute(new Trabajador(deposito, barrier));
	}
	service.shutdown();
	while (!service.isTerminated())
	    ;
	System.out.println("Proceso finalizado.");
    }

}
