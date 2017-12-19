/**
 * 
 */
package es.smartcoding.ocp.seccion06;

/**
 * @author pep
 * 
 *         Excepciones y aserciones
 *         
 *         Try-With-Resources
 *         
 *         Un programa puede fallar por muchas razones: intento de acceso a un fichero que no existe, problemas de red
 *         en el acceso a una base de datos, una instrucción SQL mal escrita...
 * 
 *         Las preguntas sobre excepciones pueden estar escondidas. Quizas pienses que se trate de una pregunta de
 *         formateo de fechas cuando en realidad se trata de probar tu familiaridad con la gestión de las excepciones en
 *         Java.
 * 
 *         Si has superado el examen OCA, buena parte de este material te será familiar pero no todo.
 * 
 *         Cuando Java lanza una excepción es porque no sabe por donde tirar y necesita la intervención del programador.
 *         Cuando escribimos una función, procedimiento o método X, tienes dos opciones, o tratas la excepción dentro de
 *         X o bien la remites al código que invocó a X en primer lugar.
 * 
 *         A estas alturas ya debes saber que hay dos categorías de excepciones: excepciones comprobadas (checked) y no
 *         comprobadas (unchecked) tambien llamadas excepciones en tiempo de ejecución aunque curiosamente todas ocurren
 *         en tiempo de ejecución.
 * 
 *         Cualquier clase que hereda de Exception pero no es una Runtime exception es una excepción de tipo comproboda
 *         y por lo tanto debe ser gestionada o declarada.
 * 
 *         Durante el curso OCA te familizarizastes con algunas excepciones, como ArithmeticException,
 *         ArrayIndexOutOfBoundException, ClassCastException, IllegalArgumentException, NullPointerException y
 *         NumberFormatException. También aprendistes que IOException es una excepción de tipo comprobada.
 * 
 *         Durante el curso OCP debes familiarizarte con algunas más. Revisa las tablas 6.2 y 6.3 para consultar la
 *         lista completa, que incluye ParseException, IOException, FileNotFoundException, NotSerializableException,
 *         SQLException, ArrayStoreException, DateTimeException, MissingResourceException, IllegalStateException y
 *         UnsupportedOperationException.
 * 
 *         Recuerda que cuando declaras un bloque try/catch, Java comprueba los bloques catch en el orden en que
 *         aparecen, por lo tanto es ilegal declarar un bloque catch con una subclase de un bloque anterior, porque ese
 *         codigo no llegaría a ejecutarse nunca. Además, Java no permite declarar un bloque catch para una exception
 *         comprobada que no puede lanzarse potencialmente.
 * 
 *         Recuerda también la diferencia entre throws y throw y cómo se pueden crear exceptciones personalizadas, así
 *         como la posibilidad de capturar múltiples excepciones en un mismo bloque catch, lo que se conoce como
 *         multi-catch.
 *
 */
public class Leccion_06_04 {

    /**
     * @param args
     */
    public static void main(String[] args) {
	// TODO Auto-generated method stub

    }

}
