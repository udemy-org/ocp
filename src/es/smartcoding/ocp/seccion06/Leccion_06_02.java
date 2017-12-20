/**
 * 
 */
package es.smartcoding.ocp.seccion06;

/**
 * @author pep
 *
 *         Excepciones y aserciones
 * 
 *         Custom Exceptions
 * 
 *         El lenguaje Java proporciona multiples excepciones 'out of the box' pero ocasionalmente puedes tener la
 *         necesidad de crear excepciones más especializadas.
 * 
 *         La primera decisión que debes tomar es si ha de ser una excepción comprobada o no, aunque lo más corriente es
 *         extender directamente de la clase Exception.
 * 
 *         Como ves en este ejemplo crear una excepción a medida es sumamente fácil.
 * 
 *         Revisa el código que acompaña a esta lección, responde a las preguntas planteadas y en definitiva, modifícalo
 *         para experimentar con los contenidos de esta lección.
 *
 */

class IllegalCocheStateException extends IllegalStateException {

    private static final long serialVersionUID = -2685480080240879494L;

    /**
     * Default Constructor
     */
    public IllegalCocheStateException() {
    }

    /**
     * @param message
     * @param cause
     */
    public IllegalCocheStateException(String message, Throwable cause) {
	super(message, cause);
    }

    /**
     * @param message
     */
    public IllegalCocheStateException(String message) {
	super(message);
    }

    /**
     * @param cause
     */
    public IllegalCocheStateException(Throwable cause) {
	super(cause);
    }

}

class Coche implements AutoCloseable {

    @Override
    public void close() throws IllegalCocheStateException {
	throw new IllegalStateException("Puerta atascada");

    }

}

public class Leccion_06_02 {

    /**
     * @param args
     */
    public static void main(String[] args) {
	// Crea la excepción DerrapeException
    }

}
