/**
 * 
 */
package es.smartcoding.ocp.seccion04;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * @author pep
 * 
 *         Programación Funcional
 * 
 *         Interfaces funcionales de Java 8
 * 
 *         Java 8 incorpora algunas interfaces funcionales que debes conocer
 * 
 *         Como convención se utiliza T y U como tipos genéricos y R como tipo de retorno genérico.
 * 
 *         Predicate<T> es una interfaz funcional cuyo método abstracto, test(T), toma un parámetro de tipo T y retorna
 *         un boolean
 * 
 *         Consumer<T> es una interfaz funcional cuyo método abstracto, accept(T), toma un parámetro de tipo T y retorn
 *         void
 * 
 *         Supplier<T> es una interfaz funcional cuyo método abstracto, get(), no toma ningún parámetro y devuelve T
 * 
 *         Function<T, R> es una interfaz funcional cuyo método abstracto, apply(T), toma un parámetro de tipo T y
 *         retorna R
 * 
 *         Además de estas interfaces funcionales, Predicate, Consumer, Supplier y Function existen otras muchas que son
 *         meras variantes como por ejemplo : BiConsumer, BiFunction, BiPredicate etc.
 * 
 *         Revisa el código que acompaña a esta lección, responde a las preguntas planteadas y en definitiva, modifícalo
 *         para experimentar con los contenidos de esta lección.
 *
 */

public class Leccion_04_02 {

    /*
     * Parámetros: ninguno. Valor de retorno <T>. Método abstracto: get().
     */
    static Supplier<LocalDate> ahora1 = LocalDate::now;
    static Supplier<LocalDate> ahora2 = () -> LocalDate.now();
    static Supplier<Integer> aleatorio = () -> new Random().nextInt();
    static Supplier<StringBuilder> sb1 = StringBuilder::new;
    static Supplier<StringBuilder> sb2 = () -> new StringBuilder();
    static Supplier<ArrayList<String>> al = ArrayList<String>::new;
    static ArrayList<String> a1 = al.get();
    /*
     * Parámetros: <T>. Valor de retorno void. Método abstracto: accept().
     */
    static Consumer<Integer> consumer = i -> System.out.println(i);
    static Consumer<Integer> consumer2 = System.out::println;
    /*
     * Parámetros: <T>, <U>. Valor de retorno <T>. Método abstracto: accept().
     */
    static BiConsumer<Double, Double> imc = (altura, peso) -> System.out.println(peso / (altura * altura));
    /*
     * Parámetros: <T>. Valor de retorno boolean. Método abstracto: test().
     */
    static Predicate<Integer> adulto = (Integer edad) -> edad >= 18 ? true : false;
    static Predicate<String> huevos = s -> s.contains("Huevos");
    static Predicate<String> bacon = s -> s.contains("Bacon");
    static Predicate<String> huevosConBacon = huevos.and(bacon);
    /*
     * Parámetros: <T>, <U>. Valor de retorno boolean. Método abstracto: test().
     */
    static BiPredicate<Integer, Double> esMasGrande = (q, i) -> q > i ? true : false;
    /*
     * Parámetros: <T>, <U>. Valor de retorno <U>. Método abstracto: apply().
     */
    static Function<Double, Double> cuadrado = n -> n * n;
    /*
     * Parámetros: <T>, <U>, <R>. Valor de retorno <R>. Método abstracto: apply().
     */
    static BiFunction<Double, Double, Double> potencia = (base, exponente) -> Math.pow(base, exponente);
    /*
     * Parámetros: <T>. Valor de retorno <T>. Método abstracto: apply().
     */
    static UnaryOperator<String> mayusculas = (String s) -> s.toUpperCase();
    /*
     * Parámetros: <T>, <T>. Valor de retorno <T>. Método abstracto: apply().
     */
    static BinaryOperator<String> concatena = (String s1, String s2) -> s1 + s2;

    /**
     * @param args
     * 
     *            Como ejercicio invoca los métodos de algunas de las interfaces funcionales que ves aquí.
     * 
     */
    public static void main(String[] args) {
	System.out.println(ahora1);
	System.out.println(ahora2);
	imc.accept(1.81, 85.0);
	System.out.println(adulto.test(34));
	System.out.println(potencia.apply(2.2, 3.3));
	System.out.println(concatena.apply("abcde", "fghij"));
	System.out.println(huevosConBacon.test("Huevos, Chorizo, Bacon"));
    }

}
