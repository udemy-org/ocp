/**
 * 
 */
package es.smartcoding.ocp.seccion10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * @author pep
 * 
 *         JDBC
 * 
 *         Introducción a las base de datos relacionales y SQL
 * 
 *         Este capítulo cubre la API JDBC (Java Database Connectivity) de forma
 *         básica. Una base de datos relacional es una base de datos que
 *         organiza la información en tablas formadas por filas y columnas, al
 *         estilo de una hoja de cálculo.
 * 
 *         Hay dos formas de acceder a una base de datos relacional desde Java:
 * 
 *         1. JDBC.
 * 
 *         2. JPA API. O lo que es lo mismo mediante un ORM (Object Relational
 *         Mapping).
 * 
 *         Se utiliza el lenguaje SQL (Structured Query Language) que se divide
 *         en dos partes DDL (Data Definition Language) y DML (Data Management
 *         Language). El primero se utiliza para crear estructuras de datos:
 *         create table ..., etc. El segundo se utiliza para acceder a bases de
 *         datos: select ...
 * 
 *         Otras bases de datos NoSQL no estructuran la información en forma de
 *         tablas como las llamadas base de datos documentales.
 * 
 *         Algunas de las operaciones básicas que se pueden hacer en un base de
 *         datos relacional son:
 * 
 *         INSERT, que añade un registro o fila a la base de datos
 * 
 *         SELECT, que recupera información de la base de datos
 * 
 *         UPDATE, que cambia cero o más filas en la tabla
 * 
 *         DELETE, que elimina cero o más filas de la tabla
 * 
 *         Durante el examen OCP no necesitas ser un experto en SQL, basta que
 *         sepas interpretar instrucciones como esta:
 * 
 *         DELETE FROM eventos WHERE nombre = 'server shut down';
 *
 */
public class Leccion_10_01 {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		String url = "jdbc:mysql://localhost:3306/ocp";
		try (Connection conn = DriverManager.getConnection(url, "root", "admin");
				Statement stmt = conn.createStatement()) {
			conn.setCatalog("ocp");
			stmt.executeUpdate("DROP TABLE IF EXISTS eventos");
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS eventos (" + "id INTEGER PRIMARY KEY AUTO_INCREMENT, "
					+ "nombre VARCHAR(65) NOT NULL, " + "descripcion VARCHAR(255), "
					+ "fecha TIMESTAMP DEFAULT NOW())");
			stmt.executeUpdate(
					"INSERT INTO eventos (nombre, descripcion) VALUES ('shut down', 'se ha detenido el servidor por mantenimiento')");
			stmt.executeUpdate(
					"INSERT INTO eventos (nombre, descripcion) VALUES ('backup', 'se ha hecho una copia de seguridad del sistema')");
			stmt.executeUpdate(
					"INSERT INTO eventos (nombre, descripcion) VALUES ('restore', 'se ha restaurado la ultima imagen guardada')");
			stmt.executeUpdate("DELETE FROM eventos WHERE nombre = 'restore'");
			stmt.executeUpdate("UPDATE eventos SET nombre='copia' WHERE nombre = 'backup'");
			System.out.println("OK");
		}
	}

}
