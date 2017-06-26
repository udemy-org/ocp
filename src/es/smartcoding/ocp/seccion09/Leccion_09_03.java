/**
 * 
 */
package es.smartcoding.ocp.seccion09;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author pep
 * 
 *         NIO.2 New Input/Output
 * 
 *         atributos de ficheros
 * 
 *         La clase Files, además de proporcionar numerosos métodos para crear,
 *         mover, elimiar o leer un fichero/directorio, también viene provista
 *         de métodos que trabajan con los atributos de un fichero/directorio
 *         también llamados 'metadata'.
 * 
 *         Por ejemplo, un fichero puede tener una marca de escondido (hidden),
 *         o de sólo lectura.
 * 
 *         No todos los sistemas de archivos trabajan con el mismo conjunto de
 *         atributos, por ejemplo, los ficheros de algunos sistemas operativos
 *         no tienen permisos a nivel de usuario y otros sí.
 *
 */
public class Leccion_09_03 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * Los atributos básicos de un fichero/directorio pueden consultarse con
		 * métodos que son perfectamente intuitivos.
		 * 
		 * El método isHidden() lanza una excepción porque en algunos sistemas
		 * operativos es necesario leer información del propio fichero.
		 */
		Path path1 = Paths.get("ocp.log");
		System.out.println(Files.isDirectory(path1));
		System.out.println(Files.isRegularFile(path1));
		System.out.println(Files.isSymbolicLink(path1));
		try {
			System.out.println(Files.isHidden(path1));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(Files.isReadable(path1));
		System.out.println(Files.isExecutable(path1));
	}

}
