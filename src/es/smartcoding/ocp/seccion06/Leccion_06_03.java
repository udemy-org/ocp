/**
 * 
 */
package es.smartcoding.ocp.seccion06;

import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @author pep
 * 
 *         Fechas, Cadenas y Localización
 * 
 *         Internacionalización y localización
 * 
 *         Muchas aplicaciones necesitan funcionar en diferentes paises con
 *         diferentes lenguas. Por ejemplo la fecha 11/3/17 se refiere al 11 de
 *         marzo o al 3 de noviembre?
 * 
 *         Internacionalización (i18n) es el proceso de diseñar un software para
 *         que pueda ser adaptable.
 * 
 *         Localización (l10n) significa que un software soporta múltiples
 *         locales, entendiendo por local una lengua, cultura, sistema
 *         monetario, unidades de medida, etc.
 * 
 *         Oracle define un locale como un región geográfica, política o
 *         cultural específica. Dos letras minúsculas para la lengua, un
 *         subrallado, y dos letras mayúsculas para el país.
 *
 */

public class Leccion_06_03 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Locale locale1 = new Locale("es", "ES");
		Locale locale2 = new Locale.Builder().setLanguage("es").setRegion("CU").build();
		System.out.println(locale1); // es_ES Español de España
		System.out.println(locale2); // es_CU Español de Cuba
		// No hace cambios en el sistema
		Locale.setDefault(locale1);
		Locale locale3 = Locale.getDefault();
		System.out.println(locale3);

		/*
		 * Resource Bundle
		 * 
		 * Un Resource Bundle o fichero de recursos contiene aspectos del
		 * programa susceptible de cambiar con el Locale: etiquetas, formatos de
		 * fechas, moneda...
		 * 
		 * Es decir, se trata de externalizar los recursos en un fichero o clase
		 * Java y NO incluirlos en el código.
		 * 
		 * Afortunadamente, no tenemos que escribir
		 */
		Locale locale4 = new Locale.Builder().setLanguage("es").build();
		Locale locale5 = Locale.ENGLISH;
		ResourceBundle bundle_es = ResourceBundle.getBundle("es.smartcoding.ocp.seccion06.l0603", locale4);
		ResourceBundle bundle_en = ResourceBundle.getBundle("es.smartcoding.ocp.seccion06.l0603", locale5);
		System.out.println(bundle_es.getString("saludo"));
		System.out.println(bundle_en.getString("saludo"));
		// Podemos pasar parametros a los valores de las claves de los ficheros
		// de recursos.
		System.out.println(MessageFormat.format(bundle_en.getString("saludo"), "Pep"));
		Properties properties = new Properties();
		bundle_es.keySet().stream().forEach(key -> properties.put(key, bundle_es.getString(key)));
		System.out.println(properties.getProperty("Saludo", "Hola"));

		/*
		 * Formateando números
		 * 
		 * Los métodos getInstance() y getNumberInstance() son equivalentes.
		 */
		NumberFormat nf1 = NumberFormat.getInstance();
		NumberFormat nf2 = NumberFormat.getInstance(Locale.US);
		NumberFormat nf3 = NumberFormat.getNumberInstance();
		NumberFormat nf4 = NumberFormat.getNumberInstance(Locale.getDefault());
		NumberFormat nf5 = NumberFormat.getCurrencyInstance();
		NumberFormat nf6 = NumberFormat.getCurrencyInstance(Locale.US);
		NumberFormat nf7 = NumberFormat.getPercentInstance();
		NumberFormat nf8 = NumberFormat.getPercentInstance(Locale.US);
		/*
		 * Redondea valores decimales. No forma parte del examen OCP.
		 */
		NumberFormat nf9 = NumberFormat.getIntegerInstance();
		NumberFormat nf10 = NumberFormat.getIntegerInstance(Locale.US);

		// Default locale es_ES
		System.out.println(nf1.format(3 / 2.0));
		System.out.println(nf2.format(3 / 2.0));
		System.out.println(nf5.format(33 / 1.3));
		System.out.println(nf6.format(33 / 1.3));
		System.out.println(nf7.format(30 / 60.0));
		System.out.println(nf8.format(30 / 60.0));
		System.out.println(nf9.format(3_2010 / 777.0));

		/*
		 * La clase NumberFormat también tiene un método parse() que en función
		 * del locale interpretará las comas y los puntos como separadores de
		 * miles o como separador decimal.
		 */

		try {
			Number number1 = nf1.parse("1,2");
			System.out.println(number1);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		/*
		 * A la hora de formatear fechas, las cosas se complican un poco. ISO es
		 * un estandar de representación de fechas. Pero el método
		 * ofLocalizedDate() de la clase DateTimeFormatter ofrece una
		 * representación más humana.
		 * 
		 */
		LocalDate date = LocalDate.of(2010, Month.AUGUST, 31);
		LocalTime time = LocalTime.of(19, 07, 0);
		LocalDateTime dateTime = LocalDateTime.of(date, time);
		System.out.println(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
		System.out.println(time.format(DateTimeFormatter.ISO_LOCAL_TIME));
		System.out.println(dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		/*
		 * FormatStyle.SHORT, FormatStyle.MEDIUM, FormatStyle.LONG,
		 * FormatStyle.FULL
		 */
		DateTimeFormatter shortDateTime = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
		System.out.println(shortDateTime.format(dateTime));
		System.out.println(shortDateTime.format(date));
		// UnsupportedTemporalTypeException
		// System.out.println(shortDateTime.format(time));
		DateTimeFormatter shortDateTime2 = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
		System.out.println(dateTime.format(shortDateTime2));
		System.out.println(date.format(shortDateTime2));
		// java.time.temporal.UnsupportedTemporalTypeException: Unsupported
		// field: DayOfWeek
		// System.out.println(time.format(shortDateTime2));
		/*
		 * También podemos crear nuestros propios formatos:
		 * 
		 * MMMM Representa el mes. Se puede especificar entre 1 y 4 M's con
		 * distintos resultados para el mes.
		 * 
		 * dd representa el dia. Con o sin 0 inicial.
		 * 
		 * yyyy Representa el año.
		 * 
		 * hh representa la hora.
		 * 
		 * mm representa los minutos.
		 * 
		 * , y : forman parte de la cadena, no tienen un significado especial.
		 */
		DateTimeFormatter f = DateTimeFormatter.ofPattern("MMMM dd, yyyy, hh:mm");
		System.out.println(dateTime.format(f));

		/*
		 * A la hora de la práctica es muy comun usar este procedimiento
		 */
		DateTimeFormatter f2 = DateTimeFormatter.ofPattern("hh:mm");
		System.out.println(f2.format(dateTime));
		System.out.println(f2.format(time));

		/*
		 * Tambien hay un método parse() que puede utilizarse en combinación con
		 * el método ofPattern().
		 */
		DateTimeFormatter f3 = DateTimeFormatter.ofPattern("MM dd yyyy");
		LocalDate ldate = LocalDate.parse("08 31 2010", f3);
		LocalTime ltime = LocalTime.parse("19:07");
		System.out.println(ldate);
		System.out.println(ltime);

	}

}
