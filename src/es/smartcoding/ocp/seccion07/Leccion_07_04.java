/**
 * 
 */
package es.smartcoding.ocp.seccion07;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author pep
 * 
 *         Concurrencia
 * 
 *         ExecutorService
 * 
 *         La interfaz ExecutorService extiende la interfaz Executor de donde hereda el método execute(). Una
 *         alternativa al método execute() es el método submit() que puede hacer todo lo que hace el método execute() y
 *         además retorna un objeto Future que representa el resultado pendiente de la tarea.
 * 
 *         Junto con los métodos execute() y submit(), debes conocer los métodos invokeAll() e invokeAny() que toman una
 *         colección de Callables de tipo T como parámetro pero mientras el primero retorna una lista de objetos Future
 *         de tipo T, el segundo retorna un objeto de tipo T.
 * 
 *         Pero, cómo podemos saber que una tarea ha finalizado? El método submit() retorna un objeto
 *         java.util.concurrent.Future<V>, que representa el resultado de una computación asíncrona y que puede
 *         utilizarse para conocer el estado del resultado.
 * 
 *         Revisa el código que acompaña a esta lección, responde a las preguntas planteadas y en definitiva, modifícalo
 *         para experimentar con los contenidos de esta lección.
 * 
 */
public class Leccion_07_04 {

    /**
     * @param args
     * 
     *            El método submit()
     * 
     *            La API Concurrency permite hacer tareas avanzadas con hilos sin ni siquera utilizar la clase Thread
     *            directamente.
     * 
     *            En este ejemplo, procesamos un Callable con el método submit() y guardamos el valor de retorno en la
     *            variable result de tipo Future. Después esperamos 10 segundos y obtenemos el valor de retorno, si no
     *            estuviera disponible se lanzaría una excepcion de tipo TimeoutException.
     * 
     *            Determina la salida del código siguiente.
     * 
     *            Modifica (1) para pasar como parámero un Runnable al método submit(). Qué pasa si comentas la
     *            expresión (2)?
     * 
     * 
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
	ExecutorService service = null;
	try {
	    service = Executors.newSingleThreadExecutor();
	    // (1)
	    Future<?> result = service.submit(() -> {
		int t = 1;
		for (int i = 0; i < 10; i++) {
		    t += t;
		}
		// (2)
		return t;
	    });
	    Object object = result.get(10, TimeUnit.SECONDS);
	    System.out.printf("Resultado %d", object);
	} catch (TimeoutException e) {
	    e.printStackTrace();
	} finally {
	    if (service != null)
		service.shutdown();
	}
    }

}







