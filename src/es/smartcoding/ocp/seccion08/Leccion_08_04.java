/**
 * 
 */
package es.smartcoding.ocp.seccion08;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * @author pep
 * 
 *         Entradas y salidas
 * 
 *         Interactuando con usuarios
 * 
 *         La API java.io incluye numerosas clases utilizadas para interactuar con el usuario. La clase java.io.Console
 *         introducida en Java 6 es la clase recomendada para interactuar con el usuario en entornos texto.
 * 
 *         Revisa el código que acompaña a esta lección, responde a las preguntas planteadas y en definitiva, modifícalo
 *         para experimentar con los contenidos de esta lección.
 * 
 */
public class Leccion_08_04 {

    /**
     * Determina la salida del código siguiente.
     *      
     * @param args
     */
    public static void main(String[] args) {

	/*
	 * Tradicionalmente se ha accedido a la consola envolviendo System.in en un InputStreamReader y el
	 * InputStreamReader en un BufferedReader, de esta manera:
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
	 * Actualmente esto esta superado, la forma recomendada de hacerlo es así.
	 */
	Console console = System.console();
	if (console != null) {
	    console.writer().print("Nombre de usuario: ");
	    String user = console.readLine();
	    console.writer().print("clave: ");
	    char[] psw = console.readPassword();
	} else {
	    System.out.println("La consola no esta disponible.");
	}
	/*
	 * Para ejecutar este código debes abrir un terminal, acceder al directorio ocp/target y escribir:
	 * 
	 * java -cp classes es.smartcoding.ocp.seccion08.Leccion_08_04
	 * 
	 * Desafortudamente, no consigo obtener un objeto Console desde mi Mac.
	 * 
	 * Alternativamente, se puede leer un flujo de entrada con la clase Scanner.
	 * 
	 */

    }

}
