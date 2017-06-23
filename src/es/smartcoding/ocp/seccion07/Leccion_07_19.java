/**
 * 
 */
package es.smartcoding.ocp.seccion07;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * @author jmendez
 * 
 *         Concurrencia
 * 
 *         La Framework Fork/Join
 * 
 *         Cuando una tarea es muy compleja, podemos dividirla en múltiples
 *         subtascas más sencillas con la framework Fork/Join.
 * 
 *         La framework se basa en el concepto de recursividad para resolver
 *         tareas complejas.
 * 
 *         Un ejemplo clásico que ya debes conocer es el cálculo del factorial
 *         de un número.
 * 
 *         public static int factorial(int n) {
 * 
 *         if(n<=1) return 1; else return n * factorial(n-1);
 * 
 *         }
 * 
 *         La clave de toda función recursiva es encontrar el caso base donde
 *         acaba la recursividad. En este ejemplo, el caso base es un número
 *         menor o igual a 1.
 * 
 *         Supongamos que queremos encontrar el valor máximo de una array de
 *         doubles de gran tamaño, podríamos subdividir la tarea en subtareas
 *         más pequeñas hasta tener muestras pequeñas de por ejemplo 10
 *         elementos.
 * 
 *         Double[] values = new Double[Integer.MAX_VALUE];
 * 
 *         Si queremos aplicar la framework fork/join debemos hacerlo en tres
 *         etapas:
 * 
 *         1. Crear un ForkJoinTask
 * 
 *         2. Crear el ForkJoinPool
 * 
 *         3. Iniciar el ForkJoinTask
 * 
 *         La primera etapa es la mas compleja, requiere definir el proceso
 *         recursivo. Afortunadamente las otras dos etapas apenas requieren unas
 *         líneas de código.
 * 
 *         Durante el examen OCP, deberás saber cómo implementar una solución
 *         fork/join extendiendo una de las dos clases RecursiveAction o
 *         RecursiveTask, ya que ambas implementan la interfaz ForkJoinTask.
 * 
 *         La ForkJoinTask es una entidad parecida a un thread pero más ligera.
 *         Una ForkJoinTask comienza la ejecución cuando se envía a un
 *         ForkJoinPool. Una vez ha comenzado, pondrá en marcha otras subtareas.
 * 
 *         Una ForkJoinTask es como un Future mas ligero. El principal mecanismo
 *         de coordinación es el método fork(), que arranca la ejecución de
 *         hilos asíncrona y el método join() que no continúa hasta que se ha
 *         computado el resultado de la tasca.
 * 
 *         Generalmente no se hereda de la clase ForkJoinTask, sino de alguna de
 *         las clases abstractas que soportan algún estilo particular de proceso
 *         fork/join. Aquellas tareas que no retornan un valor heredarán de
 *         RecursiveAction (Son parecidas a Runnable). Si retornan un valor
 *         heredaran de RecursiveTask (Son parecidas a Callable). Y para
 *         aquellas acciones que disparan otras acciones heredaran de
 *         CountedCompleter.
 * 
 *         Las tareas ForkJoinTask deberían llevar a cabo relativamente pequeñas
 *         cantidades de computación. Las tareas grandes deberían dividirse en
 *         subtascas más pequeñas, normalmente a través de una descomposición
 *         recursiva. Como regla general, una subtarea debe realizar entre 100 y
 *         1000 operaciones básicas de computación y por su puesto, deben evitar
 *         a toda costa los lazos infinitos. Si las subtareas son demasiado
 *         grandes, entonce el paralelismo puede que no mejore la productividad.
 *         Y si son demasiado pequeñas, entonces la memoria y la propia
 *         sobrecarga interna de gestión pueden sobrecargar el sistema.
 * 
 */

/*
 * Esta clase ilustra el tema de una acción, rellenar un array muy grande,
 * dividido en subacciones de forma recursiva mediante diferentes hilos hasta
 * que se da una condición base.
 * 
 */
class RellenaArrayRecursiveAction extends RecursiveAction {

	// Límite inferior
	private static final int THRESHOLD = 100;

	/*
	 * Algunos autores usan 'm' como prefijo de las variable miembro
	 */
	private int mInicio;
	private int mFinal;
	private Double[] mValores;

	/**
	 * @param mInicio
	 * @param mFinal
	 * @param valores
	 */
	public RellenaArrayRecursiveAction(int ini, int fin, Double[] valores) {
		this.mInicio = ini;
		this.mFinal = fin;
		this.mValores = valores;
	}

	@Override
	protected void compute() {
		if (mFinal - mInicio <= THRESHOLD) {
			for (int i = mInicio; i < mFinal; i++) {
				mValores[i] = Math.random();
			}
		} else {
			int centro = mInicio + ((mFinal - mInicio) / 2);
			invokeAll(new RellenaArrayRecursiveAction(mInicio, centro, mValores),
					new RellenaArrayRecursiveAction(centro, mFinal, mValores));
		}
	}
}

/*
 * Esta clase ilustra el tema de una tarea, encontrar la suma de los valores de
 * un array muy grande de doubles, dividido en subtareas de forma recursiva
 * mediante diferentes hilos que retornan cálculos intermedios hasta que se da
 * una condición base.
 * 
 */
class SumaArrayRecursiveTask extends RecursiveTask<Double> {

	// Límite inferior
	private static final int THRESHOLD = 100;

	/*
	 * Algunos autores usan 'm' como prefijo de las variable miembro
	 */
	private int mInicio;
	private int mFinal;
	private Double[] mValores;

	/**
	 * @param mInicio
	 * @param mFinal
	 * @param valores
	 */
	public SumaArrayRecursiveTask(int ini, int fin, Double[] valores) {
		this.mInicio = ini;
		this.mFinal = fin;
		this.mValores = valores;
	}

	@Override
	protected Double compute() {
		Double suma = 0.;
		if (mFinal - mInicio <= THRESHOLD) {
			for (int i = mInicio; i < mFinal; i++) {
				suma += mValores[i];
			}
			return suma;
		} else {
			int centro = mInicio + ((mFinal - mInicio) / 2);
			RecursiveTask<Double> tascaIzda = new SumaArrayRecursiveTask(mInicio, centro, mValores);
			tascaIzda.fork();
			return new SumaArrayRecursiveTask(centro, mFinal, mValores).compute() + tascaIzda.join();
		}

	}
}

public class Leccion_07_19 {

	// Dimension del array
	private static final int DIM = 10_000;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*
		 * Rellenamos el array
		 */
		Double[] ds = new Double[DIM];
		ForkJoinTask<?> task = new RellenaArrayRecursiveAction(0, ds.length, ds);
		ForkJoinPool pool = new ForkJoinPool();
		pool.invoke(task);

		/*
		 * Mostramos el array
		 */
		for (Double d : ds) {
			System.out.println(d);
		}

		/*
		 * Sumamos los valores de una array
		 */
		ForkJoinTask<Double> task2 = new SumaArrayRecursiveTask(0, ds.length, ds);
		ForkJoinPool pool2 = new ForkJoinPool();
		Double suma = pool2.invoke(task2);
		System.out.println("Suma: " + suma);
	}

}
