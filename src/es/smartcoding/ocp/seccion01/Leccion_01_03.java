package es.smartcoding.ocp.seccion01;

/**
 * 
 * @author pep
 * 
 *         Diseño avanzado de clases
 *
 *         La anotación @Override
 * 
 *         La anotación Override es útil sólamente cuando definimos un método, pero no un campo.
 * 
 *         Se utiliza cuando sobrescribimos un método ya sea de una interfaz o de una superclase como por ejemplo Object.
 * 
 *         En el examen no aparece mucho, porque justamente se trata de verificar que reconoces cuándo un método sobrescribe a otro y como sabes forma parte de
 *         la declaración de un método. Además tienes que asegurarte de que se trata de un caso válido de sobrescritura y no de sobrecarga.
 * 
 */

interface AutoGuiado {
	void pilotoAutomatico();
}

abstract class Vehiculo {
	abstract public void arranca();
}

class Coche extends Vehiculo implements AutoGuiado {

	@Override
	public void arranca() {}

	@Override
	public void pilotoAutomatico() {}

}

public class Leccion_01_03 {

	public static void main(String[] args) {}

}










