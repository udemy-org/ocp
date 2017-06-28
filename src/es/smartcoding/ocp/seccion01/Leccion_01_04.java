/**
 * 
 */
package es.smartcoding.ocp.seccion01;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 
 * @author pep
 * 
 *         Diseño avanzado de clases
 * 
 *         Algunos métodos particulares
 * 
 *         Los método equals(), hashCode() y toString()
 * 
 *         El método equals() tiene un parámetro de tipo Object y retornará
 *         false si el argumento es null o el tipo no coincide
 * 
 *         El método hashCode() retorna un entero calculado a partir de todas o
 *         algunas de las variables de instancia de la clase
 * 
 *         El método toString() retorna una cadena que será la representación
 *         escrita de una clase
 *
 */

class Fraccion {
	private double numerador;
	private double denominador;

	public Fraccion(double numerador, double denominador) {
		super();
		this.numerador = numerador;
		this.denominador = denominador;
	}

	@Override
	public String toString() {
		// return String.format("Fraccion [numerador=%s, denominador=%s]",
		// numerador, denominador);
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(denominador);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(numerador);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		// Propiedad reflexiva, un objeto tiene que ser igual a sí mismo.
		if (this == obj) {
			return true;
		}
		// Recuerda, x.equals(null) siempre retorna false.
		if (obj == null) {
			return false;
		}
		// Una fraccion sólo puede comparse con una fracción, si se compara con
		// cualquier otras cosa retorna false.
		if (!(obj instanceof Fraccion)) {
			return false;
		}
		Fraccion other = (Fraccion) obj;
		// if (Double.doubleToLongBits(denominador) != Double
		// .doubleToLongBits(other.denominador)) {
		// return false;
		// }
		// if (Double.doubleToLongBits(numerador) != Double
		// .doubleToLongBits(other.numerador)) {
		// return false;
		// }
		// return true;
		return new EqualsBuilder().appendSuper(super.equals(obj)).append(numerador, other.numerador)
				.append(denominador, other.denominador).isEquals();
	}

}

public class Leccion_01_04 {

	public static void main(String[] args) {
		Fraccion f1 = new Fraccion(1, 2);
		Fraccion f2 = new Fraccion(2, 1);
		System.out.println(f1.hashCode());
		System.out.println(f2.hashCode());
	}
}