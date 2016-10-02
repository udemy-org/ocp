/**
 * 
 */
package es.smartcoding.ocp.seccion01;

/**
 * 
 * @author pep
 * 
 *         Ejemplo de clase interna anónima
 * 
 *         Una clase interna anónima es una clase interna local que no tiene nombre. 
 *         
 *         Se declara y se instancia todo en una misma orden mediante la palabra clave new.
 *         
 *         Todas las clases internas anónimas deben extender otra clase o implementar una interfaz pero no puede hacer las dos cosas a la vez.
 *         
 *         Son muy útiles a la hora de hacer una implementación corta que no se usará en ningún otro sitio.
 *         
 *         
 *         
 */

public class Leccion_01_06_C {
	
	abstract class Impresora {
		public abstract void imprime() ;
	}


	public void print(Impresora impresora) {
		impresora.imprime();
	}
	
	public static void main(String[] args) {
		String doc = "OCP";
		Leccion_01_06_C l17c = new Leccion_01_06_C();
		l17c.print(l17c.new Impresora() {

			@Override
			public void imprime() {
//				System.out.println(doc);
				
			}
			
		});

	}

}