/**
 * 
 */
package es.smartcoding.ocp.seccion07;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author pep
 * 
 *         Concurrencia
 * 
 *         Colecciones concurrente
 * 
 *         Además de tratar la gestión de threads, la API de concurrencia
 *         incluye clases e interfaces que nos pesmiten coordinar el acceso a
 *         las colecciones en un entorno multi tasca.
 * 
 *         Pero si podemos sincronizar el accesos a los métodos, realmente son
 *         necesarias nuevas colecciones? La respuesta es que sí, porque evitan
 *         errores de implementaciones particulares.
 * 
 *         Por ejemplo si queremos que el método put() de una mapa se ejecute de
 *         forma atómica tenemos dos soluciones, o bien creamos un método
 *         sincronizado que lo contenga o bien usamos una estructura de datos a
 *         prueba de threads como ConcurrentHashMap<String, Object>().
 * 
 *         El propósito de usar las clases de las colecciones concurrentes es el
 *         de evitar errores de consistencia de memoria. Por error de
 *         consistencia de memoria se entiende cuando varios threads tienen una
 *         visión inconsistente de los que debería ser la misma estructura de
 *         datos. En otras palabras, lo que queremos es que si un thread escribe
 *         en una colección, otro thread efectivamente vea el estado final y no
 *         el prévio.
 *
 */
public class Leccion_07_11 {

	/**
	 * @param args
	 * 
	 *            Esto código lanza una excepción de tipo
	 *            ConcurrentModificationException porque se esta eliminando una
	 *            clave de la colección a la vez que se esta recorriendo la
	 *            collección por clave.
	 * 
	 *            Si sustituimos (1) por (2) se resuelve el problema.
	 * 
	 *            En general, debemos usar una colección concurrente cada vez
	 *            que tengamos múltiples threads que la modifiquen.
	 * 
	 *            Consulta la tabla 7.9 del libro de referencia para ver una
	 *            referencia completa de las colecciones que debes conocer.
	 */
	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>(); // (1)
		// Map<String, Object> map = new ConcurrentHashMap<String, Object> ();
		// // (2)
		map.put("alfa", 'a');
		map.put("bravo", 'b');
		for (String key : map.keySet())
			map.remove(key);

	}

}
