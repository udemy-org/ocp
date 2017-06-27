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
 *         tablas.
 *
 */
public class Leccion_10_01 {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		String url = "jdbc:derby:zoo;create=true";
		try (Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()) {
			stmt.executeUpdate("CREATE TABLE species (" + "id INTEGER PRIMARY KEY, " + "name VARCHAR(255), "
					+ "num_acres DECIMAL)");
			stmt.executeUpdate("CREATE TABLE animal (" + "id INTEGER PRIMARY KEY, " + "species_id integer, "
					+ "name VARCHAR(255), " + "date_born TIMESTAMP)");
			stmt.executeUpdate("INSERT INTO species VALUES (1, 'African Elephant', 7.5)");
			stmt.executeUpdate("INSERT INTO species VALUES (2, 'Zebra', 1.2)");
			stmt.executeUpdate("INSERT INTO animal VALUES (1, 1, 'Elsa', '2001−05−06 02:15')");
			stmt.executeUpdate("INSERT INTO animal VALUES (2, 2, 'Zelda', '2002−08−15 09:12')");
			stmt.executeUpdate("INSERT INTO animal VALUES (3, 1, 'Ester', '2002−09−09 10:36')");
			stmt.executeUpdate("INSERT INTO animal VALUES (4, 1, 'Eddie', '2010−06−08 01:24')");
			stmt.executeUpdate("INSERT INTO animal VALUES (5, 2, 'Zoe', '2005−11−12 03:44')");
		}
	}

}
