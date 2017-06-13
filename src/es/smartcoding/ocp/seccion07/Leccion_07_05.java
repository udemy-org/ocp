/**
 * 
 */
package es.smartcoding.ocp.seccion07;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Supplier;

/**
 * @author pep
 * 
 *         Concurrencia
 * 
 *         La interfaz funcional Callable
 * 
 *         Cuando salió la API de concurrencia junto con Java 5, se añadió la
 *         interfaz Callable, que es similar a la interfaz Runnable, excepto que
 *         su método abstracto call() retorna un valor.
 * 
 *         La interfaz ExecutorService incluye una versión sobrecargada del
 *         método submit() que tiene un argumento de tipo Callable<T> y que
 *         retorna un objeto de tipo Future<T>.
 * 
 */

public class Leccion_07_05 {

	/*
	 * Si te fijas el método abstracto de la interfaz Callable no tiene
	 * parámetros y retorna un objeto de tipo genérico T. La implementación
	 * lambda de esta interfaz es igual que la de un Supplier: () -> t. Con la
	 * excepción de que el método call() lanza una excepción comprobada.
	 * 
	 */

	public static void useCallable(Callable<Integer> expression) {
	}

	public static void useSupplier(Supplier<Integer> expression) {
	}

	public static void use(Supplier<Integer> expression) {
	}

	public static void use(Callable<Integer> expression) {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * Compila porque el método call() puede lanzar excepciones comprobadas
		 */
		useCallable(() -> {
			throw new IOException();
		});
		/*
		 * No compila porque el método get() de la interfaz Supplier no soporta
		 * excepciones
		 */
		// useSupplier(() -> {throw new IOException();});
		/*
		 * No compila porque es una definición ambigua: Callable o Supplier?, ya
		 * que el compilador no toma en consideración que se está lanzando una
		 * excepción
		 */
		// use(() -> { throw new IOException(); });
		/*
		 * Ahora sí compila, porque hacemos un cast a Callable.
		 */
		use((Callable<Integer>) () -> {
			throw new IOException();
		});

		/*
		 * La verdadera utilidad de Callable es la de proporcionar un valor de
		 * retorno. En este ejemplo, el callable retorna el mayor entero
		 * posible.
		 */
		ExecutorService service = null;
		try {
			service = Executors.newCachedThreadPool();
			Future<Integer> result = service.submit(() -> Integer.MAX_VALUE);
			System.out.println(result.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} finally {
			if (service != null) {
				service.shutdown();
			}
		}

	}

}
