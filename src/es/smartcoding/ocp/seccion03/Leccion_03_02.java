/**
 * 
 */
package es.smartcoding.ocp.seccion03;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pep
 * 
 *         Genéricos y Colecciones
 * 
 *         Los Genéricos
 * 
 *         Los genéricos son necesarios porque nos ayudan a prevenir que una lista de Strings
 *         contenga por ejemplo Numbers.
 * 
 *         Nosotros podemos definir nuestras propias clases, interfaces y métodos genéricos. Por
 *         ejemplo, En la clase genérica Box, el tipo genérico T está presente en toda la clase.
 * 
 *         El tipo de parámetro puede ser cualquier identificador válido pero existen alguna
 *         convenciones que conviene respetar:
 * 
 *         1. E para elemento
 * 
 *         2. K para la clave de un mapa
 * 
 *         3. V para el valor de un mapa
 * 
 *         4. N para un número
 * 
 *         5. T para un tipo de dato genérico
 * 
 *         6. S, U, V, etc. para múltiples tipos de datos genéricos
 * 
 *         Cuando creamos un objeto de tipo Box (Caja), especificamos el tipo de caja que queremos,
 *         es decir, la T se sustituye con Shoe, Boot, etc. Aunque internamente, Java lleva a cabo
 *         lo que se conoce como Type erasure que consiste en reemplazar todas las referencias a T
 *         con Object. Lo permite la compatibilidad con versiones anteriores de Java que no
 *         soportaban genéricos. De la misma manera, el compilador añade un cast cuando es necesario
 *         para pasar de Object al tipo adecuado.
 * 
 *         Paralelamente, también se pueden crear interfaces genéricas.
 * 
 *         Limitaciones de los genéricos
 * 
 *         La mayoría de estas limitaciones estan relacionadas con el concepto de type erasure que
 *         hemos visto antes.
 * 
 *         Oracle se refiere a los tipos cuya información esta totalmente disponible en tiempo de
 *         ejecución como reifilable. (Reify to regard something abstract as if it were a concrete
 *         material thing. To concretize. Materializar. Cosificar.).
 * 
 *         Con los tipos 'reifiable' se puede hacer cualquier cosa que permita Java mientras que los
 *         tipos 'non-feifiable', aquellos tipos donde se ha eliminado información en tiempo de
 *         ejecución debido al type-erasure, por ejemplo: List<String> y List<Number> tienen algunas
 *         limitaciones:
 * 
 *         1. No se puede llamar al constructor new T() porque en tiempo de ejecución se convertiría
 *         en new Object().
 * 
 *         2. No se puede crear un array de tipo T, porque en definitiva, estariamos creando un
 *         array de tipo de Objects.
 * 
 *         3. No se puede llamar a instanceof, porque debido al type erasure, List<String> y
 *         List<Integer> parecen lo mismo.
 * 
 *         4. No se puede usar un tipo primitivo como un parámetro genérico. Lo que no es demasiado
 *         grave porque siempre podemos utilizar una clase envolvente.
 * 
 *         5. No se puede crear una variable estática de un tipo genérico de parámetro. No está
 *         permitido porque el tipo está enlazado a la instancia de la clase y no a la clase en sí.
 * 
 *         Tanto los mètodos estáticos, de clase, como no estáticos, de instancia o virtuales pueden
 *         ser métodos genéricos. Si bien, son más comunes los métodos genéricos estáticos. En
 *         cualquier caso, el tipo genérico se declara antes del tipo de retorno de la función,
 *         excepto si es un tipo genérico conocido que proviene de la clase o interfaz genéricos.
 * 
 *         De la mezcla de 'legacy' code (codigo antiguo) y código moderno, se pueden derivar
 *         errores que no se detectan en tiempo de compilación, sino como meros avisos o warnings.
 *         Puede ser conveniente compilar el código con la opción -Xlint:unchecked para obtener un
 *         informe detallado de los problemas potenciales.
 * 
 *         Recuerda que el metodo get(int) de una colección no genérica (raw), retorna un Object y
 *         que Java no hace autoboxing de Object a otro tipo.
 * 
 *         Como hemos visto los genéricos se tratan como si fueran Objects. Los Bounded wildcards o
 *         comodines límite restringen qué tipos pueden utilizarse como genéricos. Un bounded
 *         parameter type o tipo de parámetro limitado es un tipo genérico que especifica el tipo
 *         límite de un genérico. Se representa mediante un '?' y representa un tipo genérico
 *         desconocido. El interrogante se pueden utilizar de tres formas:
 * 
 *         1. Comodín sin límine (Unbounded wildcard) ?. List<?> list = new ArrayList<String>();
 *         Acepta cualquier clase.
 * 
 *         2. Comodín con un límite superior (Wildcard with an upper bound) ? extends type. List<?
 *         extends Number> = new ArrayList<Integer>(); Acepta Number y cualquier clase que de derive
 *         de Number.
 * 
 *         3. Comodín con un límite inferior (Wildcard with a lower bound) ? super type. List<?
 *         super RuntimeException> = new ArrayList<Throwable>(); Acepta RuntimeException y cualquier
 *         superclase: Object, Throwable y Exception.
 * 
 *         Algo a tener en cuenta es que List<String> no deriva de List<Object> a pesar de que
 *         String deriva de Object, aunque un array de String sí que es un array de Object: Object[]
 *         s = new String[5]; Esto sucede porque Java elimina el tipo en los genéricos y lo pasa a
 *         Object para proporcionar compatibilidad con versiones anteriores. En el caso de los
 *         arrays, Java sí que conoce el verdadero tipo de cada array.
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

class Boot extends Shoe {
}

class Box<T> {

	private T content;

	public void setContent(T content) {
		this.content = content;
	}

	public Box<T> post(T t) {
		System.out.println("Enviando " + t);
		return new Box<T>();
	}

	public static <U> Box<U> wrap(U u) {
		System.out.println("Envolviendo " + u);
		return new Box<U>();
	}

}

public class Leccion_03_02 {
	/*
	 * Acepta solo una lista de Shoe's
	 */
	private static void testShoes(List<Shoe> list) {
	}

	/*
	 * Acepta una lista de Shoe's o de Boot's
	 */
	private static void testAnyShoes(List<? extends Shoe> list) {
	}

	// Atención
	// Error: The method add(capture#1-of ?) in the type List<capture#1-of ?> is
	// not applicable for the arguments (String)
	// public static void addSound(List<?> list) {list.add("Alfa");}

	// Error: The method add(capture#1-of ? extends Object) in the type
	// List<capture#1-of ? extends Object> is not applicable for the arguments
	// (String)
	// public static void addSound(List<? extends Object> list)
	// {list.add("quack");}

	// Compila pero no se puede pasar una lista de Strings como parámetro formal
	// public static void addSound(List<Object> list) {list.add("quack");}

	// Compila y se puede pasar una lista de Strings y de Objects
	public static void addSound(List<? super String> list) {
		list.add("quack");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> cadenas = new ArrayList<>();
		cadenas.add("Alfa"); // OK
		// Error en tiempo de compilación. No son compatibles.
		// cadenas.add(new Integer(1));
		Box<Shoe> cajaDeZapatos = new Box<>();
		Box<Boot> cajaDeBotas = new Box<>();
		/*
		 * Estas dos llamadas son equivalentes, en (1) el compilador deduce el tipo genérico.
		 */
		Box<Shoe> b1 = Box.wrap(new Shoe()); // (1)
		Box<Shoe> b2 = Box.<Shoe>wrap(new Shoe());

		testShoes(new ArrayList<Shoe>());
		// testShoes(new ArrayList<Boot>()); // Error
		testAnyShoes(new ArrayList<Shoe>());
		testAnyShoes(new ArrayList<Boot>());

		// Atención
		List<? super IOException> exceptions = new ArrayList<Exception>();
		// exceptions.add(new Exception()); // DOES NOT COMPILE
		exceptions.add(new IOException());
		// OK porque FineNotFoundException es una IOException
		exceptions.add(new FileNotFoundException());
	}

}






