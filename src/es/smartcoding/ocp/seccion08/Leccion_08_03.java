/**
 * 
 */
package es.smartcoding.ocp.seccion08;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author pep
 * 
 *         Entradas y salidas
 * 
 *         Trabajando con streams
 * 
 *         Las clases FileInputStream y FileOutputStream sirven para leer y escribir bytes desde y hacia un fichero
 *         respectivamente.
 * 
 *         Toman como argumento un objeto de tipo File o una cadena. La clase FileInputStream disponen del método read()
 *         que lee de byte en byte, este método retorna -1 al final del fichero. La clase FileOutputStream tiene el
 *         método write() que lleva como argumento un byte o un array de bytes.
 * 
 *         Hay algunas clases de alto nivel como InputStreamReader y InputStreamWriter, DataInputStream y
 *         DataOutputStream que no forman parte de los objetivos del examen pero que pueden ser útiles en el dia a dia.
 *         De la misma forma, las clases Filter, como FilterInputStream y FilterOutputStream tampoco forman parte de los
 *         objetivos del examen, pero pueden ser útiles para filtrar o transformar datos.
 * 
 *         El tema de la serialización se refiere al proceso de almacenar un objeto que reside en memoria y la
 *         deserialización se refiere al proceso inverso, es decir, a transformar un objeto guardado en algun soporte en
 *         un objeto propiamente.
 * 
 *         La interfaz Serializable no tiene ningún método, es una interfaz que se utiliza como marca. Una clase que
 *         implementa Serializable puede convertise a una cadena de bytes siempre y cuando sus miembros sean también
 *         serializables, de otro modo, se lanzará una excepción de tipo NotSerializableException.
 * 
 *         La palabra reservada 'transient' se utiliza para descartar un campo en un supuesto caso de serialización.
 * 
 *         Además, debido a su própia naturaleza, las clases estáticas, tampoco forman parte del proceso de
 *         serialización.
 * 
 *         El dia del examen, recuerda que tanto el constructor como cualquier otra inicialización por defecto son
 *         ignoradas durante el proceso de deserialización.
 * 
 *         Consulta la figura 8.3 si quieres conocer la jerarquía de clases o consulta la propia documentación del
 *         Javadoc relativa al paquete java.io.
 * 
 *         Revisa el código que acompaña a esta lección, responde a las preguntas planteadas y en definitiva, modifícalo
 *         para experimentar con los contenidos de esta lección.
 *
 */

class Caja implements Serializable {
    /**
     * Se recomienda que las clases serializables tengan esta propiedad para gestionar las diferentes versiones de la
     * clase.
     */
    private static final long serialVersionUID = 1L;

    private double altura, anchura, profundidad;
    private transient String claveApertura;

    /*
     * Constructor por defecto
     */
    public Caja() {
	this(0.0, 0.0, 0.0, null);
    }

    /**
     * @param altura
     * @param anchura
     * @param profundidad
     */
    public Caja(double altura, double anchura, double profundidad, String clave) {
	this.altura = altura;
	this.anchura = anchura;
	this.profundidad = profundidad;
	this.claveApertura = clave;
    }

    public double getAltura() {
	return altura;
    }

    public void setAltura(double altura) {
	this.altura = altura;
    }

    public double getAnchura() {
	return anchura;
    }

    public void setAnchura(double anchura) {
	this.anchura = anchura;
    }

    public double getProfundidad() {
	return profundidad;
    }

    public void setProfundidad(double profundidad) {
	this.profundidad = profundidad;
    }

    public String getClaveApertura() {
	return claveApertura;
    }

    public void setClaveApertura(String claveApertura) {
	this.claveApertura = claveApertura;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	long temp;
	temp = Double.doubleToLongBits(altura);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	temp = Double.doubleToLongBits(anchura);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	result = prime * result + ((claveApertura == null) ? 0 : claveApertura.hashCode());
	temp = Double.doubleToLongBits(profundidad);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Caja other = (Caja) obj;
	if (Double.doubleToLongBits(altura) != Double.doubleToLongBits(other.altura))
	    return false;
	if (Double.doubleToLongBits(anchura) != Double.doubleToLongBits(other.anchura))
	    return false;
	if (claveApertura == null) {
	    if (other.claveApertura != null)
		return false;
	} else if (!claveApertura.equals(other.claveApertura))
	    return false;
	if (Double.doubleToLongBits(profundidad) != Double.doubleToLongBits(other.profundidad))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("Caja [altura=");
	builder.append(altura);
	builder.append(", anchura=");
	builder.append(anchura);
	builder.append(", profundidad=");
	builder.append(profundidad);
	builder.append(", claveApertura=");
	builder.append(claveApertura);
	builder.append("]");
	return builder.toString();
    }

}

public class Leccion_08_03 {

    /*
     * Las clases FileInputStream y FileOutputStream sirven para leer y escribir bytes desde y hacia un fichero
     * respectivamente.
     * 
     * Toman como argumento un objeto de tipo File o una cadena. La clase FileInputStream disponen del método read() que
     * lee de byte en byte, el método read() retorna -1 al final del fichero. La clase FileOutputStream tiene el método
     * write() que lleva como argumento un byte o un array de bytes.
     * 
     */
    public static void copiaSinBuffer(File source, File destination) throws IllegalArgumentException {
	try (InputStream in = new FileInputStream(source); OutputStream out = new FileOutputStream(destination)) {
	    int b;
	    while ((b = in.read()) != -1) {
		out.write(b);
	    }
	} catch (FileNotFoundException e) {
	    throw new IllegalArgumentException("El fichero " + source.getAbsolutePath() + " no existe.");
	} catch (IOException e) {
	    throw new IllegalArgumentException("Error de I/O " + e.getMessage());
	}
    }

    /*
     * Esta versión del método copy() anterior representa una mejora sustancial porque utiliza buffers. Generalmente, es
     * buena idea utilizar un tamaño de buffer que sea potencia de 2 porque tanto el hardware como la memoria cache
     * utiliza tamaños que son potencia de 2.
     * 
     */
    public static void copiaConBuffer(File source, File destination) throws IllegalArgumentException {
	try (InputStream in = new BufferedInputStream(new FileInputStream(source));
		OutputStream out = new BufferedOutputStream(new FileOutputStream(destination))) {
	    byte[] buffer = new byte[1024];
	    int lengthRead;
	    while ((lengthRead = in.read(buffer)) > 0) {
		out.write(buffer, 0, lengthRead);
		out.flush();
	    }
	} catch (FileNotFoundException e) {
	    throw new IllegalArgumentException("El fichero " + source.getAbsolutePath() + " no existe.");
	} catch (IOException e) {
	    throw new IllegalArgumentException("Error de I/O " + e.getMessage());
	}
    }

    /*
     * En vez de leer y escribir de byte en byte, podríamos leer y escribir de línea en línea.
     * 
     * Trabajando con cadenas (String) en vez de con bytes, podemos manipular la entrada/salida fácilmente.
     */
    public static List<String> leeFichero(File source) throws IllegalArgumentException {
	List<String> data = new ArrayList<String>();
	try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
	    String s;
	    while ((s = reader.readLine()) != null) {
		data.add(s);
	    }
	} catch (FileNotFoundException e) {
	    throw new IllegalArgumentException("El fichero " + source.getAbsolutePath() + " no existe.");
	} catch (IOException e) {
	    throw new IllegalArgumentException("Error de I/O " + e.getMessage());
	}
	return data;
    }

    public static void escribeFichero(List<String> data, File destination) throws IllegalArgumentException {
	try (BufferedWriter writer = new BufferedWriter(new FileWriter(destination))) {
	    for (String s : data) {
		writer.write(s);
		/*
		 * Inserta un salto de línea.
		 */
		writer.newLine();
	    }
	} catch (IOException e) {
	    throw new IllegalArgumentException("Error de I/O " + e.getMessage());
	}
    }

    /*
     * El tema de la serialización/deserialización se refiere al proceso de almacenar un objeto que reside en memoria y
     * viceversa.
     * 
     * La interfaz Serializable no tiene ningún método, es una interfaz que se utiliza como marca. Una clase que
     * implementa Serializable puede convertise a una cadena de bytes siempre y cuando sus miembros sean también
     * serializables, de otro modo, se lanzará una excepción de tipo NotSerializableException.
     * 
     * La palabra reservada 'transient' se utiliza para descartar un campo en un supuesto caso de serialización.
     * 
     * Además, debido a su própia naturaleza, las clases estáticas, tampoco forman parte del proceso de serialización.
     * 
     * El dia del examen, recuerda que tanto el constructor como cualquier otra inicialización por defecto son ignoradas
     * durante el proceso de deserialización.
     * 
     */
    public static void creaFicheroDeCajas(List<Caja> cajas, File dataFile) throws IllegalArgumentException {
	try (ObjectOutputStream out = new ObjectOutputStream(
		new BufferedOutputStream(new FileOutputStream(dataFile)))) {
	    for (Caja caja : cajas)
		out.writeObject(caja);
	} catch (FileNotFoundException e) {
	    throw new IllegalArgumentException("El fichero " + dataFile.getAbsolutePath() + " no existe.");
	} catch (IOException e) {
	    throw new IllegalArgumentException("Error de I/O " + e.getMessage());
	}
    }

    public static List<Caja> leeFicheroDeCajas(File dataFile) throws IllegalArgumentException {
	List<Caja> cajas = new ArrayList<Caja>();
	try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(dataFile)))) {
	    while (true) {

		Object object = in.readObject();
		if (object instanceof Caja)
		    cajas.add((Caja) object);
	    }
	} catch (EOFException e) {
	} catch (IOException | ClassNotFoundException e) {
	    throw new IllegalArgumentException("Error de I/O " + e.getMessage());
	}
	return cajas;
    }

    /**
     * Determina la salida del código siguiente.
     * 
     * Añade un campo static y comprueva como no forma parte del proceso de serialización.
     * 
     * Añade un campo anotado como transient y comprueva que tampoco forma parte del proceso de serialización.
     * 
     * @param args
     */
    public static void main(String[] args) {
	copiaSinBuffer(new File("README.md"), new File("README.back"));
	copiaConBuffer(new File("README.md"), new File("README.back"));
	// ...

	/*
	 * Java soporta un amplio rango de codificaciones de caracteres: UTF-8, UTF-16, ASCII, etc.
	 */
	Charset usAsciiCharset = Charset.forName("US-ASCII");
	Charset utf8Charset = Charset.forName("UTF-8");
	Charset utf16Charset = Charset.forName("UTF-16");

	/*
	 * Ejemplo de serialización y deserialización de un lista de objetos de tipo Caja.
	 */
	List<Caja> cajas = Arrays.asList(new Caja(1.0234, 1.2345, 1.654, "135341"),
		new Caja(2.045, 2.4055, 2.430432, "34534"));
	creaFicheroDeCajas(cajas, new File("cajas.dat"));
	List<Caja> cajas2 = leeFicheroDeCajas(new File("cajas.dat"));
	for (Caja caja : cajas2) {
	    System.out.println(caja);
	}

	/*
	 * Las clases PrintStream y PrintWriter representan streams de alto nivel. La clase PrintStream esta orientada a
	 * bytes mientras que la clase PrintWriter esta orientada a caracteres. De hecho, System.out y System.err son
	 * objetos PrintStream donde se declaran métodos como print(), println(), printf(), format() y write() que es el
	 * único que lanza una excepción comprobada de tipo IOException.
	 * 
	 * De todas formas, la clase PrintWriter suele ser una mejor opción porque trabaja con texto.
	 * 
	 */
	File source = new File("ocp.log");
	try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(source)))) {
	    out.print("Today's weather is: ");
	    out.println("Sunny");
	    out.print("Today's temperature at the zoo is: ");
	    out.print(1 / 3.0);
	    out.println('C');
	    out.format("It has rained 10.12 inches this year");
	    out.println();
	    out.printf("It may rain 21.2 more inches this year");
	} catch (IOException e) {
	    e.printStackTrace();
	}

	System.out.println("OK");

    }

}
