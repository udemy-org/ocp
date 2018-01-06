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
 *         Ya que los threads se ejecutan en un entorno compartido puede ser conveniente prevenir que dos thread o más
 *         se interfieran mútuamente?
 * 
 *         La salida de este programa debería ser los números 1 2 3 4 5 6 7 8 9 10 en cualquier orden. Pero la operación
 *         de incremento (++) no es un operación atómica, por lo que eventualmente se puede producir lo que se llama una
 *         'condición de carrera' que provoca que algunos números se escriban varias veces.
 * 
 *         La API de concurrencia incluye el paquete java.util.concurrent.atomic que proporciona atomicidad. Una
 *         operación es atómica cuando se ejecuta como una unidad.
 * 
 *         Si sustituyes (1) por (2) y (3) por (4), siempre obtendrás TODOS los números del 1 al 10 en diferente orden
 *         pero SIN repetidos.
 * 
 *         Revisa el código que acompaña a esta lección, responde a las preguntas planteadas y en definitiva, modifícalo
 *         para experimentar con los contenidos de esta lección.
 * 
 */
public class Leccion_07_09 {

    // (1)
    private int contador = 0;
    // (2)
    // private AtomicInteger contador = new AtomicInteger(0);

    private void incrementAndPrint() {
	// (3)
	++contador;
	// (3)
	System.out.print(contador + " ");
	// (4)
	// System.out.print(contador.getAndIncrement() + " ");
    }

    /**
     * Determina la salida del siguiente código.
     * 
     * @param args
     */
    public static void main(String[] args) {
	final int MAX_POOL = 10;
	ExecutorService service = null;
	try {
	    service = Executors.newFixedThreadPool(MAX_POOL);
	    Leccion_07_09 manager = new Leccion_07_09();
	    for (int i = 0; i < MAX_POOL; i++) {
		service.submit(() -> manager.incrementAndPrint());
	    }
	} finally {
	    if (service != null)
		service.shutdown();
	}

    }

}
