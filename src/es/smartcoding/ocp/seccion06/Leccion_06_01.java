package es.smartcoding.ocp.seccion06;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * @author pep
 * 
 *         Excepciones y aserciones
 * 
 *         Un programa puede fallar por muchas razones: intento de acceso a un
 *         fichero que no existe, problemas de red en el acceso a una base de
 *         datos, una instrucción SQL mal escrita...
 * 
 *         Las preguntas sobre excepciones pueden estar escondidas. Quizas
 *         pienses que se trate de una pregunta de formateo de fechas cuando en
 *         realidad se trata de probar tu familiaridad con la gestión de las
 *         excepciones en Java.
 * 
 *         Si has superado el examen OCA, buena parte de este material te será
 *         familiar pero no todo.
 * 
 *         Cuando Java lanza una excepción es porque no sabe por donde tirar y
 *         necesita la intervención humana. Cuando escribimos una función,
 *         procedimiento o método X, tienes dos opciones, o tratas la excepción
 *         dentro de X o bien la remites al código que invocó a X en primer
 *         lugar.
 * 
 *         A estas alturas ya debes saber que hay dos categorías de excepciones:
 *         excepciones comprobadas (checked) y no comprobadas (unchecked)
 *         tambien llamadas excepciones en tiempo de ejecución aunque
 *         curiosamente todas ocurren en tiempo de ejecución.
 * 
 *         Cualquier clase que hereda de Exception pero no es una Runtime
 *         exception es una excepción de tipo comproboda y por lo tanto debe ser
 *         gestionada o declarada.
 * 
 *         Durante el curso OCA te familizarizastes con algunas excepciones,
 *         como ArithmeticException, ArrayIndexOutOfBoundException,
 *         ClassCastException, IllegalArgumentException, NullPointerException y
 *         NumberFormatException. También aprendistes que IOException es una
 *         excepción de tipo comprobada.
 * 
 *         Durante el curso OCP debes familiarizarte con algunas más. Revisa las
 *         tablas 6.2 y 6.3 para consultar la lista completa, que incluye
 *         ParseException, IOException, FileNotFoundException,
 *         NotSerializableException, SQLException, ArrayStoreException,
 *         DateTimeException, MissingResourceException, IllegalStateException y
 *         UnsupportedOperationException.
 * 
 *         Recuerda que cuando declaras un bloque try/catch, Java comprueba los
 *         bloques catch en el orden en que aparecen, por lo tanto es ilegal
 *         declarar un bloque catch con una subclase de un bloque anterior,
 *         porque ese codigo no llegaría a ejecutarse nunca. Además, Java no
 *         permite declarar un bloque catch para una exception comprobada que no
 *         puede lanzarse potencialmente.
 * 
 *         Recuerda también la diferencia entre throws y throw y cómo se pueden
 *         crear exceptciones personalizadas, así como la posibilidad de
 *         capturar múltiples excepciones en un mismo bloque catch, lo que se
 *         conoce como multi-catch.
 * 
 * 
 *
 */
public class Leccion_06_01 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/*
		 * Este código muestra un ejemplo de multi-catch, con el nombre del
		 * parámetro SÓLO al final.
		 */
		try {
			Path path = Paths.get("anyfile.txt");
			String text = new String(Files.readAllBytes(path));
			LocalDate date = LocalDate.parse(text);
			System.out.println(date);
		} catch (DateTimeParseException | IOException e) {
			/*
			 * Ilegal en bloques multi-catch pero sigue siendo permitido en
			 * single-catch.
			 */
			// e = new RuntimeException();
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		/*
		 * Recuerdas los problemas que se derivan de trabajar con recursos?
		 * 
		 * Los recursos, tales como ficheros, deben cerrarse, pero el método
		 * close() a su vez puede lanzar una excepción comprobada de tipo
		 * IOException.
		 */
		Path path1 = Paths.get("unrecurso");
		Path path2 = Paths.get("unrecurso");
		BufferedReader in = null;
		BufferedWriter out = null;
		try {
			in = Files.newBufferedReader(path1);
			out = Files.newBufferedWriter(path2);
			out.write(in.readLine());
		} catch (IOException e) {
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		/*
		 * La solución es utilizar un bloque try-with-resources, que no sólo
		 * reduce el código dramaticamente, sino que además es más sencillo y
		 * mantenible.
		 * 
		 * Los recursos se cierran automáticamente al acabar el bloque try.
		 * Existe un bloque finally implícito que se ejecuta antes que cualquier
		 * otro bloque catch/finally. Por lo tanto el bloque finally se hace
		 * incesario.
		 */
		try (BufferedReader br = Files.newBufferedReader(path1); BufferedWriter bw = Files.newBufferedWriter(path2)) {
			// trabajar con los ficheros...
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
