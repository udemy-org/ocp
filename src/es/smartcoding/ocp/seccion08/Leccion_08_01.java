/**
 * 
 */
package es.smartcoding.ocp.seccion08;

import java.io.File;

/**
 * @author pep
 * 
 *         Entradas y salidas
 * 
 *         Introducción
 * 
 *         Durante el examen OCP debes demostrar que sabes escribir y leer desde
 *         la consola.
 * 
 *         También debes estar familiarizado con las clases BufferedReader,
 *         BufferedWriter, File, FileReader, FileWriter, FileInputStream,
 *         FileOutputStream, ObjectOutputStream, ObjectInputStream y PrintWriter
 *         del paquete java.io.
 * 
 *         Ficheros y directorios
 * 
 *         Un fichero (file) es un registro dentro del sistema de ficheros que
 *         se utiliza para guardar datos del usuario y del sistema. Los ficheros
 *         se organizan dentro de directorios o carpetas. Un directorio es un
 *         registro dentro del sistema de ficheros que puede contener ficheros
 *         así como otros directorios. Finalmente, el directorio raiz (root) es
 *         la carpeta o directorio prinpical de donde nacen todos los demas. En
 *         Linux y Max se denota mediante la barra inclinada '/' mientras que en
 *         Windows se denota mediante un nombre de unidad, por ejemplo 'C:/'.
 * 
 *         No todos los sistemas operativos utilizan el mismo sistema de
 *         ficheros, aunque Java utiliza los mismo métodos indistintamente.
 * 
 *         Un 'Path' o camino es una representación en forma de cadena de un
 *         fichero o directorio dentro del sistema de archivos. Cada sistema
 *         operativo define su propio separador de archivos. La ruta
 *         '/user/home/pep/file.txt' se refiere al archivo 'file.txt' que cuelga
 *         del directorio '/user/home/pep'.
 * 
 *         Las rutas, caminos o paths pueden ser absolutos, si empiezan con '/'
 *         en Linux o Mac o 'C:/' en Windows o relativos si no.
 * 
 *         La clase java.io.File se utiliza para leer información sobre ficheros
 *         y directorios, mostrar el contenido de un directorio y crear/eliminar
 *         ficheros y directorios.
 * 
 *         Una instancia de la clase File representa una ruta de un sitema de
 *         ficheros particular. La clase File no puede utilizarse directamente
 *         para leer o escribir información en un fichero.
 * 
 */
public class Leccion_08_01 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * El formato de las rutas puede variar entre sistemas de ficheros. Java
		 * proporciona dos maneras de acceder al separador de rutas.
		 */
		System.out.println(System.getProperty("file.separator"));
		System.out.println(java.io.File.separatorChar);

		/*
		 * Estos son los métodos más comunes de la clsase File
		 */
		File file = new File("anyfile.txt");
		System.out.println(file.exists());
		System.out.println(file.getName());
		System.out.println(file.getAbsolutePath());
		System.out.println(file.isDirectory());
		System.out.println(file.isFile());
		System.out.println(file.length());
		System.out.println(file.lastModified());
		File[] files = new File(".").listFiles();

		for (File f : files) {
			System.out.println(f);
		}

		/*
		 * Juntos con otros como delete(), renameTo(file), mkdir(), mkdirs() y
		 * getParent().
		 * 
		 */

		new File("tmp").mkdir();
		new File("tmp").delete();
		// ...
	}

}
