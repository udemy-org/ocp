/**
 * 
 */
package es.smartcoding.ocp.seccion07;

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
 *
 */
public class Leccion_07_19 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
