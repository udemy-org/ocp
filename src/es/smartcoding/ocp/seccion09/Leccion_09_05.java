/**
 * 
 */
package es.smartcoding.ocp.seccion09;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.util.stream.Stream;

/**
 * @author pep
 * 
 *         NIO.2 New Input/Output
 * 
 *         Comparación entre File y las nuevas clases de NIO.2
 * 
 *         Las nuevas clases de la API NIO.2 en gran medida reemplanzan las clases antiguas del paquete java.io.
 * 
 *         Consulta la tabla 9.5 del libro de referencia para tener una referencia completa sobre las equivalencias
 *         entre métodos.
 *         
 *         Algunos fragmentos de código esta comentados, descomentalos para experimentar con ellos.
 * 
 *         Revisa el código que acompaña a esta lección, responde a las preguntas planteadas y en definitiva, modifícalo
 *         para experimentar con los contenidos de esta lección.
 *
 */
public class Leccion_09_05 {

    /**
     * Determina la salida del código siguiente. 
     * 
     * Nota: Crea el fichero 'ocp.log' antes de ejecutar este código si no existe.
     * 
     * @param args
     */
    public static void main(String[] args) {
	Path path = Paths.get("ocp.log");
	File file = new File("ocp.log");

	/*
	 * Comprueba la existencia de un fichero/path.
	 */
	boolean b1 = file.exists();
	boolean b2 = Files.exists(path);
	
	System.out.printf("%b %b%n", b1, b2);

	/*
	 * Consulta el nombre/path de un fichero/path
	 */
	String str1 = file.getName();
	Path path2 = path.getFileName();

	/*
	 * Consulta el path absoluto de un file/path
	 */
	String str2 = file.getAbsolutePath();
	Path path3 = path.toAbsolutePath();

	/*
	 * Consulta de una fichero/path es un directorio
	 */
	boolean b3 = file.isDirectory();
	boolean b4 = Files.isDirectory(path);

	/*
	 * Consulta de un fichero/path es un fichero regular
	 */
	boolean b5 = file.isFile();
	boolean b6 = Files.isRegularFile(path);

	/*
	 * Consulta de un fichero/path esta oculto
	 */
	boolean b7 = file.isHidden();
	try {
	    boolean b8 = Files.isHidden(path);
	} catch (IOException e) {
	    e.printStackTrace();
	}

	/*
	 * Consulta la longitud de un fichero/path
	 */
	long l1 = file.length();
	try {
	    long l2 = Files.size(path);
	} catch (IOException e) {
	    e.printStackTrace();
	}

	/*
	 * Consulta la fecha en la que un fichero/path se modificó por última vez.
	 */
	long l3 = file.lastModified();
	try {
	    FileTime ft1 = Files.getLastModifiedTime(path);
	} catch (IOException e) {
	    e.printStackTrace();
	}

	/*
	 * Establece la fecha en la que un fichero/path se modificó por última vez.
	 */
	file.setLastModified(System.currentTimeMillis());
	try {
	    Files.setLastModifiedTime(path, FileTime.fromMillis(System.currentTimeMillis()));
	} catch (IOException e) {
	    e.printStackTrace();
	}

	/*
	 * Elimina un fichero/path
	 */
//	boolean b9 = file.delete();
//	try {
//	    Files.delete(path);
//	} catch (IOException e) {
//	    e.printStackTrace();
//	}

	/*
	 * Cambia el nombre de un fichero/path
	 */
//	boolean b10 = file.renameTo(new File("ocp.back"));
//	try {
//	    Path path4 = Files.move(path, Paths.get("ocp.back"));
//	} catch (IOException e) {
//	    e.printStackTrace();
//	}

	/*
	 * Crea un directorio o ruta
	 */
//	boolean b11 = file.mkdir();
//	try {
//	    Path path5 = Files.createDirectory(path);
//	} catch (IOException e) {
//	    e.printStackTrace();
//	}
//	boolean b12 = file.mkdirs();
//	try {
//	    Path path6 = Files.createDirectories(path);
//	} catch (IOException e) {
//	    e.printStackTrace();
//	}

	/*
	 * Consulta el directorio.
	 */
	Path path4 = Paths.get(".");
	//File[] files = file4.listFiles();
	try {
	    Stream<Path> paths = Files.list(path4);
	    paths.forEach(System.out::println);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

}
