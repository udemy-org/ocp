/**
 * 
 */
package es.smartcoding.ocp.seccion07;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author pep
 * 
 *         Concurrencia
 * 
 *         Sincronización del acceso a los datos
 * 
 *         Ya que los threads se ejecutan en un entorno compartido cómo prevenir
 *         que dos thread o más se interfieran mútuamente?
 * 
 */
public class Leccion_07_09 {

	private int sheepCount = 0;
	// private AtomicInteger sheepCount = new AtomicInteger(0);

	private void incrementAndReport() {
		System.out.print((++sheepCount) + " ");
		// System.out.print(sheepCount.incrementAndGet() + " ");
	}

	/**
	 * @param args
	 * 
	 *            La salida de este programa debería ser 1 2 3 4 5 6 7 8 9 10
	 *            pero cada vez que se ejecuta el resultado es diferente. Se
	 *            produce lo que se conoce como una 'condición de carrera'
	 *            porque el operador ++ no es thread-safe.
	 * 
	 *            La API de comcurrencia incluye el paquete
	 *            java.util.concurrent.atomic que proporciona atomicidad. Una
	 *            operación es atómica cuando se ejecuta como una unidad.
	 * 
	 *            Si sustituyes las variable int sheepCount por AtomicInteger
	 *            sheepCount i el operador de incremento por el método
	 *            incrementAndGet() siempre obtendrás TODOS los números del 1 al
	 *            10 en diferente orden pero SIN repetidos.
	 * 
	 */
	public static void main(String[] args) {
		ExecutorService service = null;
		try {
			service = Executors.newFixedThreadPool(20);
			Leccion_07_09 manager = new Leccion_07_09();
			for (int i = 0; i < 10; i++)
				service.submit(() -> manager.incrementAndReport());
		} finally {
			if (service != null)
				service.shutdown();
		}

	}

}
