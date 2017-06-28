/**
 * 
 */
package es.smartcoding.ocp.seccion05;

/**
 * @author pep
 * 
 *         Fechas, Cadenas y Localización
 * 
 *         La clase String
 * 
 *         Aunque la clase String no forma parte de los objetivos del examen
 *         OCP, como se trata de una clase omnipresente vamos a hacer un pequeño
 *         recordatorio de los hechos más relevantes y a menudo olvidados.
 * 
 * 
 *
 */
public class Leccion_05_02 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/*
		 * Estos resultados deberían ser obvios. Java utiliza un pool de cadenas
		 * por lo tanto, s1 y s2 apuntan a la misma cadena. Mientras que s3 es
		 * un nuevo objeto.
		 */
		String s1 = "bunny";
		String s2 = "bunny";
		String s3 = new String("bunny");
		System.out.println(s1 == s2); // true
		System.out.println(s1 == s3); // false
		System.out.println(s1.equals(s3)); // true

		/*
		 * El operador + esta sobrecargo. Cualquier adición que incluya una
		 * cadena retorna una cadena. Y recuerda, la evaluación se hace de
		 * izquierda a derecha.
		 */
		String s4 = "1" + 2 + 3;
		String s5 = 1 + 2 + "3";
		System.out.println(s4); // 123
		System.out.println(s5); // 33

		/*
		 * Estos resultados también deberían ser obvios. recuerda que la clase
		 * String es inmutable.
		 */
		String s = "abcde ";
		System.out.println(s.trim().length()); // 5
		System.out.println(s.charAt(4)); // e
		System.out.println(s.indexOf('e')); // 4
		System.out.println(s.indexOf("de")); // 3
		System.out.println(s.substring(2, 4).toUpperCase()); // CD
		System.out.println(s.replace('a', '1')); // 1bcde
		System.out.println(s.contains("DE")); // false
		System.out.println(s.startsWith("a")); // true

		/*
		 * Dada la naturaleza inmutable de la clase String, debes utilizar un
		 * StringBuilder para modificar cadenas.
		 */
		StringBuilder b = new StringBuilder();
		b.append(12345).append('-');
		System.out.println(b.length()); // 6
		System.out.println(b.indexOf("-")); // 5
		System.out.println(b.charAt(2)); // 3

		StringBuilder b2 = b.reverse();
		System.out.println(b.toString()); // -54321
		System.out.println(b == b2); // true

		/*
		 * Y un poco más...
		 */
		StringBuilder sb = new StringBuilder("abcde");
		sb.insert(1, '-').delete(3, 4);
		System.out.println(s); // a-bde
		System.out.println(s.substring(2, 4)); // bd

		/*
		 * Por último, recuerda:
		 * 
		 * String es inmutable, pooled y thread-safe pero no se puede cambiar su
		 * tamaño.
		 * 
		 * StringBuilder No es inmutable, ni pooled ni thread-safe pero SÍ se
		 * puede cambiar su tamaño.
		 * 
		 * StringBuffer No es inmutable ni pooled, pero SÍ thread-sabe y su
		 * tamaño también se puede cambiar.
		 */

	}

}
