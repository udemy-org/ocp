/**
 * NOT USED
 */
package es.smartcoding.ocp.seccion01;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import es.smartcoding.ocp.seccion01.Gato.Color;

@FunctionalInterface
interface Felino {

	String nombre = "Felino";

	public static void salta() {
		System.out.println("Felino salta.");
	}

	public default void come() {
		System.out.println("Felino come.");
	}

	// virtual method
	public abstract void corre() throws Exception;

}

class Gato implements Felino {

	private String nombre = "Gato";
	private Color color;

	public Gato() {
		// TODO Auto-generated constructor stub
	}

	public Gato(String nombre, Color color) {
		this.nombre = nombre;
		this.color = color;
	}

	@Override
	public void corre() {
		System.out.println("Gato corre.");
	}

	// Los métodos static no se heredan.
	public static void salta() {
		System.out.println("Gato salta.");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {

		// return EqualsBuilder.reflectionEquals(this, obj);

		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Gato)) {
			return false;
		}
		Gato other = (Gato) obj;
		// evita null.equals(not null)
		if (nombre == null) {
			if (other.nombre != null) {
				return false;
			}
		} else if (!nombre.equals(other.nombre)) {
			return false;
		}
		return true;
	}

	// El punto y como al final opcional en este caso.
	enum Caracter {
		JUGETON, MORDEDOR
	}

	// El constructor sólo se llama una vez para cada elemento.
	enum Color {
		PARDO("Pardo") {

			@Override
			public void printDieta() {
				System.out.println("Pienso gatuno tipo A");
			}

		},
		MARRON("Marrón") {

			@Override
			public void printDieta() {
				System.out.println("Pienso gatuno tipo B");
			}

		},
		BLANCO("Blanco") {

			@Override
			public void printDieta() {
				System.out.println("Pienso gatuno tipo C");
			}

		},
		GRIS("Gris") {

			@Override
			public void printDieta() {
				System.out.println("Pienso gatuno tipo D");
			}
		};
		private String descripcion;

		private Color(String descripcion) {
			System.out.println("Calling constructor.");
			this.descripcion = descripcion;
		}

		// Los enums puden tener métodos abstractos que depués tienen que
		// implementar las constantes de la clase.
		public abstract void printDieta();

		@Override
		public String toString() {
			return descripcion;
		}

	}

	/**
	 * http://commons.apache.org/proper/commons-lang
	 */
	@Override
	public String toString() {
		// return String.format("Gato [nombre=%s]", nombre);
		// return ToStringBuilder.reflectionToString(this);
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SIMPLE_STYLE);
	}

}

public class Main {

	// Hay cuatro tipos de nested classes

	class NestedInnerClass {
		final static int i = 0;
	}

	static class StaticNestedClass {
	}

	public static void main(String[] args) {

		abstract class LocalInnerClass {
			abstract void f();
		}

		// Anonymous inner class, sin nombre.
		// Anonymous inner classes are required to extend an existing class or implement an existing interface. 
		new LocalInnerClass() {

			@Override
			void f() {
				// TODO Auto-generated method stub

			}

		};

		// Felino gato = new Gato();
		Gato gato = new Gato();
		if (gato instanceof Felino) {
			System.out.println(Felino.nombre);
			gato.come();
			gato.corre();
			Gato.salta();
		}

		Gato g1 = new Gato("tom", Color.BLANCO);
		Gato g2 = new Gato("jerry", Color.PARDO);
		System.out.println(g1.equals(g2));
		System.out.println(g1);
		for (Color color : Color.values()) {
			System.out.printf("%s %s%n", color.name(), color.ordinal());
		}
		Color gris = Color.valueOf("GRIS");
		System.out.println(gris);
		gris.printDieta();
	}

}