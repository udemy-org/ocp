/**
 * 
 */
package es.smartcoding.ocp.seccion06;

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
 *         Rethrowing Exceptions
 * 
 *         Relanzar excepciones es un patrón muy común. Considera un método que pueda lanzar una o varias excepciones
 *         comprobadas.
 * 
 *         Una estrategia clásica utilizada por muchos programadores es la de gestionar la excepción de alguna manera
 *         dentro del método y relanzar una exception no comprobada (unchecked).
 * 
 *         Revisa el código que acompaña a esta lección, responde a las preguntas planteadas y en definitiva, modifícalo
 *         para experimentar con los contenidos de esta lección.
 *
 */
public class Leccion_06_05 {

    /*
     * Podemos tener un catch multiple o un solo catch con un tipo Exception.
     * 
     * En este caso, registramos la excepción y la relanzamos como una excepción no comprobada de tipo RuntimeException,
     * lo que genera un código más limpio a la hora de invocar el método.
     */
    public static void setLocale() {
	try {
	    Locale.setDefault(Locale.US);
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
	    Path path = Paths.get("anyfile.txt");
	    String text = new String(Files.readAllBytes(path));
	    LocalDate date = LocalDate.parse(text, formatter);
	    System.out.println(date);
	} catch (DateTimeParseException | IOException e) {
	    e.printStackTrace();
	    throw new RuntimeException(e);
	}
    }

    /**
     * Determina la salidad de este código:
     * 
     * @param args
     */
    public static void main(String[] args) {
	setLocale();
    }

}


