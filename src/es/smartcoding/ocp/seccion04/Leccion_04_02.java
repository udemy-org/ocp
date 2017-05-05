/**
 * 
 */
package es.smartcoding.ocp.seccion04;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pep
 * 
 *         Los Genéricos
 * 
 *         Los genéricos son necesarios porque nos ayudan a prevenir que una
 *         lista de Strings contenga por ejemplo Numbers.
 * 
 *         Nosotros podemos definir nuestras propias clases, interfaces y
 *         métodos genéricos. En la clase genérica Box, el tipo genérico T está
 *         presente en toda la clase.
 * 
 *         El tipo de parámetro puede ser cualquier identificado valido pero
 *         existen alguna convenciones que conviene respetar:
 * 
 *         E para elemento
 * 
 *         K para la clave de un mapa
 * 
 *         V para el valor de un mapa
 * 
 *         N para un número
 * 
 *         T para un tipo de dato genérico
 * 
 *         S, U, V, etc. para múltiples tipos de datos genéricos
 * 
 *         Cuando creamos un objeto de tipo Box, especificamos el tipo de caja
 *         que queremos, es decir, la T se sustituye con Shoe, Boot, etc. Aunque
 *         internamente, ,Java lleva a cabo lo que se conoce como Type erasure
 *         que consiste en reemplazar todas las referencias a T con Object. Esto
 *         permite la compatibilidad con versiones anteriores de Java que no
 *         soportaban genéricos. De la misma manera, el compilador añade un cast
 *         cuando es necesario para pasar de Object al tipo adecuado.
 * 
 *         Paralelamente, también se pueden crear interfaces genéricas y pueden
 *         implementarse de tres formas distintas.
 * 
 *         Limitaciones de los genéricos
 * 
 *         La mayoría de estas limitaciones estan relacionadas con el concepto
 *         de type erasure que hemos visto antes.
 * 
 *         Oracle se refiere a los tipos cuya información esta totalmente
 *         disponible en tiempo de ejecución como reifilable. (Reify to regard
 *         something abstract as if it were a concrete material thing. To
 *         concretize).
 * 
 *         Con los tipos 'reifiable' se puede hacer cualquier cosa que permita
 *         Java mientras que los tipos 'non-feifiable' tienen algunas
 *         limitaciones:
 * 
 *         1. No se puede llamar al constructor new T() porque en tiempo de
 *         ejecución se convertiría en new Object().
 * 
 *         2. Crear un array de tipo T, porque en definitiva, estariamos creando
 *         un array de tipo de Objects.
 * 
 *         3. Llamar a instanceof, porque debido al type erasure, List<String> y
 *         List<Integer> parecen lo mismo.
 * 
 *         4. Usar un tipo primitivo como un parámetro genérico. Lo que no es
 *         demasiado grave porque siempre podemos utilizar una clase envolvente.
 * 
 *         5. Crear una variable estática de un tipo genérico de parámetro. No
 *         está permitido porque el tipo está enlazado a la instancia de la
 *         clase.
 * 
 */

interface WaterProofFootwear<T> {
	void ship(T t);
}

/*
 * Implementación de una interfaz genérica con un tipo fijo
 */
class Shoe implements WaterProofFootwear<Shoe> {

	@Override
	public void ship(Shoe t) {
		// TODO Auto-generated method stub

	}
}

/*
 * Implementación de una interfaz genérica con cualquier tipo
 */
class WaterProofBoot<U> implements WaterProofFootwear<U> {

	@Override
	public void ship(U t) {
		// TODO Auto-generated method stub

	}

}

/*
 * Implementación de una interfaz genérica sin genéricos.
 */
@SuppressWarnings("rawtypes")
class HighHeel implements WaterProofFootwear {

	@Override
	public void ship(Object t) {
		// TODO Auto-generated method stub

	}
}

class Boot {
}

class Box<T> {

	private T content;

	public void setContent(T content) {
		this.content = content;
	}

}

public class Leccion_04_02 {

	/**
	 * @param args
	 *            Eduard: Susana de direccio 10:03
	 */
	public static void main(String[] args) {
		List<String> cadenas = new ArrayList<>();
		cadenas.add("Alfa"); // OK
		// Error en tiempo de compilación. No son compatibles.
		// cadenas.add(new Integer(1));
		Box<Shoe> cajaDeZapatos = new Box<>();
		Box<Boot> cajaDeBotas = new Box<>();

	}

}
