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
 *         Sincronización del acceso a los datos (Continuación)
 * 
 *         Otra solución al problema anterior pasa por utilizar bloques
 *         sincronizados: si sincronizamos el acceso a la zona crítica del
 *         método incrementAndPrint() obtenemos el resultado que queremos.
 * 
 *         La solución no es única, el método incrementAndPrint2() utiliza un
 *         enfoque similar y muy utilizado. El hecho de que la variable lock sea
 *         final no es relevante pero nos asegura que no será reasignada tan
 *         pronto como los threads entren en funcionamiento.
 * 
 *         Finalmente el método incrementAndPrint3() convierte al método entero
 *         en sincronizado lo que nos asegura que sólo entrará un thread a la
 *         vez.
 * 
 *         La sincronización protege la integridad de los datos pero tiene un
 *         coste en velocidad (performance).
 *
 */
public class Leccion_07_10 {

	private final Object lock = new Object();
	private int contador = 0;

	private void incrementAndPrint() {
		synchronized (this) {
			++contador;
			System.out.print(contador + " ");
		}
	}

	private void incrementAndPrint2() {
		synchronized (lock) {
			++contador;
			System.out.print(contador + " ");
		}
	}

	private synchronized void incrementAndPrint3() {
		++contador;
		System.out.print(contador + " ");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService service = null;
		try {
			service = Executors.newFixedThreadPool(20);
			Leccion_07_10 manager = new Leccion_07_10();
			for (int i = 0; i < 10; i++) {
				service.submit(() -> manager.incrementAndPrint());
			}
		} finally {
			if (service != null)
				service.shutdown();
		}

	}

}
