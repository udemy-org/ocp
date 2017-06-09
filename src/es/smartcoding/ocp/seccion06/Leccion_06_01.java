/**
 * 
 */
package es.smartcoding.ocp.seccion06;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZonedDateTime;

/**
 * @author pep
 * 
 *         Fechas, Cadenas y Localización
 * 
 *         Java 8 ha renovado completamente cómo trabajamos con fechas y horas y
 *         aunque todavía se pueden utilizar las clases antiguas, esas clases no
 *         están en el examen.
 * 
 *         En inglés americano la palabra 'date' tiene dos significados, algunas
 *         veces se refiere a la combinación de mes/dia/año y otras veces se
 *         trata del dia del mes. Es decir, las palabras 'data' y 'day' pueden
 *         intercambiarse.
 * 
 *         El examen OCP espera que tengas cierto dominio sobre las zonas
 *         horarias. Por ejemplo, en España tenemos el UTC+0 (Tiempo Universal
 *         Coordinado) en la zona horaria Athlantic/Canary y UTC+1 en las zonas
 *         horarias Africa/Ceuta y Europe/Madrid.
 * 
 *         Pero para mantenerlo tan simple como sea posible asumiremos que
 *         estamos en la misma zona horaria.
 * 
 *         Cuando trabajamos con fechas y horas, lo primero se trata de decidir
 *         cuánta información necesitamos. El examen nos da varias alternativas:
 * 
 *         LocalDate
 * 
 *         LocalTime
 * 
 *         LocalDateTime
 * 
 *         ZonedDateTime
 */
public class Leccion_06_01 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/*
		 * LocalDate representa una fecha en formato aaaa-mm-dd
		 * 
		 * 2017-06-09
		 * 
		 * LocalTime representa una hora en forma hh:MM:ss,milesimas
		 * 
		 * 13:18:05.232
		 * 
		 * LocalDateTime representa una fecha y una hora separada por T de Time
		 * 
		 * 2017-06-09T13:18:05.232
		 * 
		 * ZonedDateTime representa una fecha y una hora separada por T más
		 * información de la zona horaria. En este caso, España esta dos horas
		 * adelantada con respecto a la hora de referencia GMT.
		 * 
		 * 2017-06-09T13:18:05.232+02:00[Europe/Madrid]
		 * 
		 * GMT (Greenwich Mean Time) es una zora horaria europea que se
		 * corresponde con la hora 0. UTC (Coordinated European Time) es una
		 * zona horaria estándar. Ambas usan la misma zona horaria 0.
		 * 
		 * Finalmente, recuerda que el examen usará el formato de fecha
		 * americano donde el mes viene antes del dia y un formato de hora de 24
		 * horas. Y que desde ahora, los meses se empiezan a contar desde 1.
		 * 
		 */
		System.out.println(LocalDate.now());
		System.out.println(LocalTime.now());
		System.out.println(LocalDateTime.now());
		System.out.println(ZonedDateTime.now());

		LocalDate ld1 = LocalDate.of(1964, Month.MARCH, 11);
		System.out.println(ld1.getDayOfWeek());

		LocalTime lt1 = LocalTime.of(6, 15);
		LocalTime lt2 = LocalTime.of(6, 15, 30);
		LocalTime lt3 = LocalTime.of(6, 15, 30, 200);
		
		LocalDateTime ldt1 = LocalDateTime.of(2015, Month.JANUARY, 20, 6, 15, 30);
		LocalDateTime ldt2 = LocalDateTime.of(ld1, lt1);

	}

}
