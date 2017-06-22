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
 *         La API de concurrencia incluye clases que pueden ser utilizadas para
 *         gestionar tareas entre un grupo de threads relacionados. Estan
 *         diseñadas para ser usadas en escenarios específicos. Durante el
 *         examen debes estar familiarizado con las clases CyclicBarrier y
 *         ForkJoinPool.
 * 
 *         La clase CyclicBarrier
 * 
 *         El constructor de la clase CyclicBarrier esta sobrecargado, uno tiene
 *         solamente un parámetro, el número de threads que debe esperar en la
 *         barrera. Y el otro además tiene como parámetro un Runnable que lo
 *         ejecutará el último thread en acabar.
 * 
 *         Supongamos que queremos vaciar, limpiar y llenar de agua un deposito.
 *         Esta es una tarea que a un solo operario le llevaria demasiado
 *         tiempo. Sin embargo, si contratamos varios operarios podria ser mucho
 *         más eficiente. Por supuesto, los operarios deben gestionarse
 *         adecuadamente. Mientras los operarios estan vaciando el depósito no
 *         se puede entrar a limpiar, y mientras se está limpiando, no se puede
 *         empezar a llenar de agua.
 * 
 *         Para esta tarea, podríamos utilizar la clase CyclicBarrier que nos
 *         permite sincronizar threads entre ellos y esperar a que acaben una
 *         cierta tarea.
 * 
 */

class Deposito {
	/*
	 * nivel va de 0 (vacío) a 100 (lleno)
	 * 
	 * Inicialemente lleno (100)
	 */
	private AtomicInteger nivel = new AtomicInteger(100);
	/*
	 * progreso de la limpieza va de 0 (sucio) a 100 (limpio)
	 * 
	 * Inicialmente sucio (0)
	 */
	private AtomicInteger progresoLimpieza = new AtomicInteger(0);

	public void vacia() {
		System.out.println("vaciando desde thread " + Thread.currentThread().getName() + " nivel de vaciado "
				+ nivel.decrementAndGet());

		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean vacio() {
		return nivel.intValue() == 0;
	}

	public void llena() {
		System.out.println("llenando desde thread " + Thread.currentThread().getName() + " nivel de llenado "
				+ nivel.getAndIncrement());

		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean lleno() {
		return nivel.intValue() == 100;
	}

	public void limpia() {
		System.out.println("limpiando desde thread " + Thread.currentThread().getName() + " progreso limpieza "
				+ progresoLimpieza.getAndIncrement());

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
	 * @param args
	 * 
	 *            En este ejemplo un depósito es vaciado, limpiado y llenado
	 *            cooperativamente entre NUM_THREADS threads.
	 * 
	 *            El mismo objeto CyclicBarrier se puede volver utilizar una vez
	 *            todos los threads han acabado la etapa actual.
	 * 
	 *            Fíjate que puede darse el caso que una CyclicBarrier quede
	 *            bloqueada, simplemente es suficiente crear menos threads de
	 *            los que esta esperando.
	 * 
	 */
	public static void main(String[] args) {
		final int NUM_THREADS = 3;

		Deposito deposito = new Deposito();
		CyclicBarrier barrier = new CyclicBarrier(NUM_THREADS, () -> System.out.println("*** Nivel completado ***"));
		ExecutorService service = Executors.newFixedThreadPool(NUM_THREADS);
		for (int i = 0; i < NUM_THREADS; i++) {
			service.execute(new Trabajador(deposito, barrier));
		}
	}

}
