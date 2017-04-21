/**
 * 
 */
package es.smartcoding.ocp.seccion02;

/**
 * 
 * @author pep
 *
 *         @Override
 * 
 *         La anotación Override es útil solamente cuando hacemos referencia a un método, no a un campo.
 *         
 *         Se utiliza cuando implementamos un método tanto de una interfaz como de una superclase como por ejemplo Object.
 *         
 *         Aunque en el examen no aparece mucho, porque justamente se trata de verificar que sabes cuándo un método sobrescribe a otro,
 *         cuando forma parte de la declaración de un método, debes asegurarte de que se trata de un caso válido de sobrescritura y no
 *         de sobrecarga.
 *         
 */

abstract class Vehiculo {
	abstract public void arranca();
}

class Coche extends Vehiculo {

	@Override
	public void arranca() {	}
	
}

public class Leccion_02_03 {

	public static void main(String[] args) {}

}