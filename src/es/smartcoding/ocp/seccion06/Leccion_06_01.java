/**
 * 
 */
package es.smartcoding.ocp.seccion06;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;

import static java.lang.System.out;

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

		/*
		 * El método estático of() de las clases LocalDate, LocalTime,
		 * LocalDateTime y ZonedDateTime esta sobrecargado y se utiliza para
		 * crear objetos de su correspondiente clase.
		 */

		LocalDate ld1 = LocalDate.of(1964, Month.MARCH, 11);
		System.out.println(ld1.getDayOfWeek());

		LocalTime lt1 = LocalTime.of(6, 15);
		LocalTime lt2 = LocalTime.of(6, 15, 30);
		LocalTime lt3 = LocalTime.of(6, 15, 30, 200);

		LocalDateTime ldt1 = LocalDateTime.of(2015, Month.JANUARY, 20, 6, 15, 30);
		LocalDateTime ldt2 = LocalDateTime.of(ld1, lt1);

		ZonedDateTime zdt1 = ZonedDateTime.of(ldt1, ZoneId.systemDefault());
		System.out.println(zdt1);
		ZoneId.getAvailableZoneIds().stream().sorted().forEach(out::println);
		System.out.println(ZoneId.getAvailableZoneIds().stream().count());

		/*
		 * Mira la tabla 5.1 del libro de referencia para consultar una lista
		 * completa de equivalencias entre código antiguo y actual.
		 */
		Date date1 = new Date();
		LocalDate date2 = LocalDate.now();

		/*
		 * La manipulación de fechas y horas se hace a través de los métodos
		 * plusXxxx, minusXxxx y se pueden aplicar a instancias de LocalDate,
		 * LocalTime o LocalDateTime según el caso.
		 * 
		 * Adicionalmente, los método plus()/mminus() toman un long y una
		 * unidad.
		 */
		System.out.println(lt1);
		System.out.println(lt1.plusHours(1));
		System.out.println(lt1.minus(1, ChronoUnit.MINUTES));

		/*
		 * Period, Instant y Duration son clases que contabilizan un lapsus de
		 * tiempo más o menos largo.
		 * 
		 * La clase LocalDate tiene el método toEpochDay() que retorna el número
		 * de dias transcurridos desde el uno de enero de 1970. Esta fecha se
		 * llama epoch.
		 * 
		 * Las clases LocalDateTime y ZonedDateTime tienen el método
		 * toEpochSecond() que retorna el número de segundos transcurridos desde
		 * epoch.
		 * 
		 * La clase LocalTime no tienen un método epoch porque representa una
		 * fecha que puede ocurrir en cualquier fecha.
		 * 
		 * Un período Period expresa un número de años, meses y dias.
		 * 
		 * Una duración Duration expresa un número de horas, minutos, segundos y
		 * nanosegundos.
		 * 
		 * Un instante Instant.
		 * 
		 */
		System.out.println(date2.toEpochDay());
		Period p1 = Period.of(1, 0, 21);
		Period p2 = Period.ofYears(1);
		Period p3 = Period.ofMonths(9);
		Period p4 = Period.ofWeeks(5);
		Period p5 = Period.ofDays(39);
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p3);
		System.out.println(p4);
		System.out.println(p5);

		Duration d1 = Duration.ofSeconds(34);
		Duration d2 = Duration.of(90, ChronoUnit.SECONDS);
		System.out.println(d1);
		System.out.println(d2);

		LocalTime one = LocalTime.of(5, 15);
		LocalTime two = LocalTime.of(6, 30);
		LocalDate date = LocalDate.of(2016, 1, 20);
		System.out.println(ChronoUnit.HOURS.between(one, two)); 
		System.out.println(ChronoUnit.MINUTES.between(one, two));
		// java.time.DateTimeException: Unable to obtain LocalTime from TemporalAccessor
		// System.out.println(ChronoUnit.MINUTES.between(one, date));
	}

}
