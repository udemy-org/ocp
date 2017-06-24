/**
 * 
 */
package es.smartcoding.ocp.seccion08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author pep
 * 
 *         Entradas y salidas
 * 
 *         Interactuando con usuarios
 * 
 *         La API java.io incluye numerosas clases utilizadas para interactuar
 *         con el usuario. La clase java.io.Console introducida en Java 6 es la
 *         clase recomendada para interactuar con el usuario en entornos texto.
 * 
 */
public class Leccion_08_04 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/*
		 * Tradicionalmente se ha accedido a la consola envonviendo System.in en
		 * un InputStreamReader y el InputStreamReader en un BufferedReader.
		 * 
		 */
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			System.out.print("Tu nombre: ");
			String userInput = reader.readLine();
			System.out.println("Hola " + userInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*
		 * Acutalmente esto esta superado, la forma de hacerlo 
		 */
	}

}
