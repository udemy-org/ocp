/**
 * 
 */
package es.smartcoding.ocp.seccion06;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * @author pep
 * 
 *         Excepciones y aserciones
 * 
 *         Multi-Catch Exceptions
 * 
 *         Recuerda que cuando declaras un bloque try/catch, Java comprueba los bloques catch en el orden en que
 *         aparecen, por lo tanto es ilegal declarar un bloque catch con una subclase de un bloque anterior, por
 *         ejemplo: un bloque que capture Exception seguido de un bloque que capture IOException, porque ese codigo no
 *         llegaría a ejecutarse nunca. Además, Java no permite declarar un bloque catch para una exception comprobada
 *         que no puede lanzarse potencialmente.
 * 
 *         Java 7 introduce la idea de multi-catch exceptions lo que nos evita la duplicidad de código al tener que
 *         repetir varios bloques catch o escribir un único bloque catch capturando Exception algo que no es una buena
 *         idea porque no sabemos con qué excepción concreta estamos tratando.
 * 
 *         Pero ten en cuenta que un bloque multi-catch es efectivamente final, eso quiere decir que a diferencia de los
 *         bloques catch tradicionales, el parámetro no puede volverse a asignar.
 * 
 *         Revisa el código que acompaña a esta lección, responde a las preguntas planteadas y en definitiva, modifícalo
 *         para experimentar con los contenidos de esta lección.
 *
 */
public class Leccion_06_03 {

    /**
     * Determina la salida del siguiente código:
     * 
     * @param args
     */
    public static void main(String[] args) {

	/*
	 * Este código muestra un ejemplo de multi-catch, con el nombre del parámetro SÓLO al final.
	 */
	try {
	    Locale.setDefault(Locale.US);
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
	    Path path = Paths.get("anyfile.txt");
	    String text = new String(Files.readAllBytes(path));
	    LocalDate date = LocalDate.parse(text, formatter);
	    System.out.println(date);
	}
	// Pueden ser excepciones relacionadas? Convierte este código en varios bloques catch 'tradicionales'
	catch (DateTimeParseException | IOException e) {
	    /*
	     * Ilegal en bloques multi-catch porque son efectivamente finales pero sigue siendo permitido en
	     * single-catch.
	     */
	    // e = new RuntimeException();
	    e.printStackTrace();
	    throw new RuntimeException(e);
	}

    }

}




