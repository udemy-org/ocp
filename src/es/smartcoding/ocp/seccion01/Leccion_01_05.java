package es.smartcoding.ocp.seccion01;

/**
 * 
 * @author pep
 *
 *         Diseño avanzado de clases
 * 
 *         enums
 * 
 *         Ya que los enumerados representan un conjunto de constantes es habitual escribirlas en mayúsculas.
 * 
 *         El tipo enum internamente es un tipo de clase que principalmente contiene miembros static y algunos métodos de ayuda.
 * 
 *         De un enumerado no se puede heredar, ni añadir constantes en tiempo de ejecución.
 * 
 *         Como son constantes finales y estaticas pueden compararse con el operador de igualdad (==).
 * 
 */

/*
 * Un enumerado puede ser una simple definición de constantes.
 */
enum Planeta {
	MERCURIO, VENUS, TIERRA, MARTE, JUPITER, SATURNO, NEPTUNO, URANIO, PLUTON
}

/*
 * Y pueden ser como clases regulares: con campos, constructores (privados o default porque sólo se pueden llamar internamente) y métodos.
 */
enum Dia {

	LUNES(true) {

		@Override
		public void printHorario() {
			System.out.println("de 8:00 a 15:00");
		}

	},
	MARTES(true) {

		@Override
		public void printHorario() {
			System.out.println("de 8:00 a 15:00");
		}

	},
	MIERCOLES(true) {

		@Override
		public void printHorario() {
			System.out.println("de 8:00 a 15:00");
		}

	},
	JUEVES(true) {

		@Override
		public void printHorario() {
			System.out.println("de 8:00 a 15:00");
		}

	},
	VIERNES(true) {

		@Override
		public void printHorario() {
			System.out.println("de 8:00 a 13:00");
		}

	},
	SABADO(false) {

		@Override
		public void printHorario() {
			System.out.println("No laborable");
		}

	},
	DOMINGO(false) {

		@Override
		public void printHorario() {
			System.out.println("No laborable");
		}

	};

	private boolean laborable;

	private Dia(boolean laborable) {
		this.laborable = laborable;
	}

	@Override
	public String toString() {
		return super.toString() + " " + (laborable ? "Laborable" : "Festivo");
	}

	public boolean isLaborable() {
		return laborable;
	}

	public abstract void printHorario();

}

public class Leccion_01_05 {

	public static void main(String[] args) {

		for (Planeta planeta : Planeta.values()) {
			if (planeta == Planeta.TIERRA) {
				continue;
			}
			System.out.println(planeta);
		}

		Planeta planeta = Planeta.valueOf("TIERRA");

		switch (planeta) {
		case TIERRA:
			System.out.println(planeta);
			break;
		default:
			System.out.println("No es la Tierra");
		}

		for (Dia dia : Dia.values()) {
			if (!dia.isLaborable()) {
				continue;
			}
			System.out.println(dia);
			dia.printHorario();
		}

	}

}
