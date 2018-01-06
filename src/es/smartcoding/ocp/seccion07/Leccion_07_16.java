/**
 * 
 */
package es.smartcoding.ocp.seccion07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author pep
 * 
 *         Concurrencia
 * 
 *         Mejoras en la velocidad
 * 
 *         Supongamos que tenemos que procesar 3000 registros, y que cada registro requiere 10 milisengudos en promedio.
 *         En total tardaría unos 30 segundos (33.529) en completarse la tarea.
 * 
 *         En cambio, con un stream paralelo, tarda menos de 10 segundos (8.327) lo que mejora el resultado en un factor
 *         de 1 a 4. E incluso más, los resultados mejorarían si añadimos más CPU's.
 * 
 *         En general esto es aplicable a problemas con grandes volumenes de datos, donde cada dato puede tratarse
 *         independientemente. Aunque no hay garantía de que usando streams paralelos haya una mejora.
 * 
 *         Para acabar, recuerda que el orden de los resultados no es predecible cuando se trabaja con streams
 *         paralelos.
 * 
 *         Revisa el código que acompaña a esta lección, responde a las preguntas planteadas y en definitiva, modifícalo
 *         para experimentar con los contenidos de esta lección.
 *
 */
class Registro {
    int input;

    public Registro(int input) {
	this.input = input;
    }

}

public class Leccion_07_16 {

    public int procesaRegistro(Registro registro) {
	try {
	    Thread.sleep((int) (Math.random() * 20));
	} catch (InterruptedException e) {
	}
	return registro.input + 1;

    }

    public void procesaDatos(List<Registro> data) {
	data.stream().map(a -> procesaRegistro(a)).count();
	// data.parallelStream().map(a -> procesaRegistro(a)).count();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
	Leccion_07_16 calculator = new Leccion_07_16();
	// Definición de los datos
	List<Registro> data = new ArrayList<>();
	for (int i = 0; i < 3000; i++)
	    data.add(new Registro(i));
	// Procesamiento
	long start = System.currentTimeMillis();
	calculator.procesaDatos(data);
	double time = (System.currentTimeMillis() - start) / 1000.0;
	// Resultados
	System.out.println("Se han necesitado: " + time + " segundos.");

	/*
	 * Las expresiones lambda con estado (stateful lambda expressions) son aquellas cuyo resultado depende del
	 * estado, que puede cambiar durante la ejecución del pipeline.
	 * 
	 * El método forEachOrdered() muestra el contenido secuencialmente, mientras que el orden de los valores en la
	 * lista es totalmente aleatorio. Por supuesto que esto no hubiera pasado en un stream serie.
	 * 
	 */
	List<Integer> list = Collections.synchronizedList(new ArrayList<>());
	Arrays.asList(1, 2, 3, 4, 5, 6).parallelStream().map(i -> {
	    list.add(i);
	    return i;
	}).forEachOrdered(i -> System.out.print(i + " "));
	System.out.println();
	for (Integer e : list) {
	    System.out.print(e + " ");
	}

    }

}
