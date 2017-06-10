package es.smartcoding.ocp.seccion06;

import java.util.ListResourceBundle;

/**
 * Los ficheros de propiedades suelen ser suficientes, pero en ocasiones una
 * clase de recursos puede ser más conveniente.
 * 
 * Los ficheros tienen algunas restricciones, sólo puede tener cadenas como
 * valores mientras que las clases de recursos pueden tener cualquier tipo.
 * Aunque evidentemente al final se tratará como una cadena.
 * 
 * Las clases de recursos tienen prioridad sobre los fichero de propiedades, además presentan dos ventajas adicionales:
 * 
 * 1. Se puede usar cualquier tipo de valor aunque no sea una cadena
 * 
 * 2. Se pueden crear los valores de las propiedades en tiempo de ejecución.
 * 
 * La tabla 5.7 muestra el orden en que Java determina el recurso a utilizar.
 * 
 */
public class l0603_es extends ListResourceBundle {

	@Override
	protected Object[][] getContents() {
		return new Object[][] { { "saludo", "Bienvenidos sean" }, };
	}

}