/**
 * 
 */
package es.smartcoding.ocp.seccion09;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.UserPrincipal;

/**
 * @author pep
 * 
 *         NIO.2 New Input/Output
 * 
 *         atributos de ficheros
 * 
 *         La clase Files, además de proporcionar numerosos métodos para crear, mover, elimiar o leer un
 *         fichero/directorio, también viene provista de métodos que trabajan con los atributos de un fichero/directorio
 *         también llamados 'metadata'.
 * 
 *         Por ejemplo, un fichero puede tener una marca de escondido (hidden), o de sólo lectura (read only).
 * 
 *         No todos los sistemas de archivos trabajan con el mismo conjunto de atributos, por ejemplo, los ficheros de
 *         algunos sistemas operativos no tienen permisos a nivel de usuario y otros sí.
 * 
 *         Revisa el código que acompaña a esta lección, responde a las preguntas planteadas y en definitiva, modifícalo
 *         para experimentar con los contenidos de esta lección.
 *
 */
public class Leccion_09_03 {

    /**
     * Determina la salida del código siguiente.
     * 
     * @param args
     */
    public static void main(String[] args) {
	/*
	 * Los atributos básicos de un fichero/directorio pueden consultarse con métodos que son perfectamente
	 * intuitivos.
	 * 
	 * Los métodos isHidden() y size() lanzan una excepción porque en algunos sistemas operativos es necesario leer
	 * información del propio fichero. Los otros métodos en vez de lanzar una excepción si van mal, sencillamente
	 * retornan false.
	 * 
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
	/*
	 * El método size() está definido solamente con ficheros. Si se invoca con directorios el resultado depende del
	 * sistema de archivos y es indefinido.
	 * 
	 */
	try {
	    System.out.println(Files.size(path1));
	} catch (IOException e) {
	    e.printStackTrace();
	}
	try {
	    System.out.println(Files.getLastModifiedTime(path1).toMillis());
	    Files.setLastModifiedTime(path1, FileTime.fromMillis(System.currentTimeMillis()));
	    System.out.println(Files.getLastModifiedTime(path1).toMillis());
	} catch (IOException e) {
	    e.printStackTrace();
	}
	try {
	    UserPrincipal owner = FileSystems.getDefault().getUserPrincipalLookupService().lookupPrincipalByName("pep");
	    System.out.println(owner);
	    System.out.println(Files.getOwner(path1).getName());
	    Files.setOwner(path1, owner);
	    System.out.println(Files.getOwner(path1).getName());
	} catch (IOException e) {
	    e.printStackTrace();
	}

	/*
	 * Views
	 * 
	 * Hasta ahora hemos estado accediendo a atributos de ficheros individuales con llamadas puntuales a diferentes
	 * métodos. Aunque esto es perfectamente correcto, sería más eficiente acceder a los atributos de los ficheros
	 * de forma colectiva con una sola llamada.
	 * 
	 * Una vista (view) es un grupo de atributos relacionados para un tipo de sistema de archivos particular.
	 * 
	 * Un fichero puede soportar múltiples vistas, lo que nos permite recuperar y actualizar múltiples conjuntos de
	 * atributos sobre un fichero.
	 * 
	 * Si necesitas leer múltiples atributos de un fichero o directorio a la vez, las vistas proporcionan una
	 * ventaja adicional, la velocidad de acceso.
	 * 
	 * Este fragmento de código, lee los atributos básicos de un fichero y que incrementa el atributo
	 * 'last-modified' del fichero en 10 segundos.
	 */
	BasicFileAttributeView view = Files.getFileAttributeView(path1, BasicFileAttributeView.class);
	BasicFileAttributes data;
	try {
	    data = view.readAttributes();
	    FileTime lastModifiedTime = FileTime.fromMillis(data.lastModifiedTime().toMillis() + 10_000);
	    /*
	     * setTimes(lastModifiedTime, lastAccessTime, createTime)
	     */
	    view.setTimes(lastModifiedTime, null, null);
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

}
