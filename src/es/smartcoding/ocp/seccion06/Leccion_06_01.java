/**
 * 
 */
package es.smartcoding.ocp.seccion06;

import java.time.Duration;
import java.time.Instant;
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
 *         Fechas, horas y zona horarias
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
		 * Un período (Period) expresa un número de años, meses y dias.
		 * 
		 * Una duración (Duration) expresa un número de horas, minutos, segundos
		 * y nanosegundos.
		 * 
		 * Un instante (Instant) representa un momento específico en la zona
		 * horaria GMT.
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

		/*
		 * java.time.DateTimeException: Unable to obtain LocalTime from
		 * TemporalAccessor
		 * 
		 * Alguna operaciones no son válidas, por ejemplo, no se pueden calcular
		 * los minutos transcurridos entre una hora y una fecha.
		 * 
		 * La tabla 5.4 del libro de referencia resume todas las acciones
		 * válida. Pero en general, un Period es como una fecha y se puede
		 * operar con todo menos con LocalTime, porque es una hora; y una
		 * Duration es como una hora, por lo tanto se puede operar con todo
		 * menos con LocalDate.
		 * 
		 */
		// System.out.println(ChronoUnit.MINUTES.between(one, date));

		Instant i1 = Instant.now();
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
		}
		Instant i2 = Instant.now();
		Duration d3 = Duration.between(i1, i2);
		System.out.println(d3); // PT0.03S

		/*
		 * También podemos convertir un número de segundos transcurridos desde
		 * epoch en un instante.
		 */
		Instant i3 = Instant.ofEpochMilli(System.currentTimeMillis());
		System.out.println(i3);

		/*
		 * Finalmente, podemos convertir una ZonedDateTime en un instante que
		 * representan el mismo momento.
		 */
		ZonedDateTime zdt = ZonedDateTime.of(LocalDate.now(), LocalTime.now(), ZoneId.systemDefault());
		Instant i4 = zdt.toInstant();
		System.out.println(zdt);
		System.out.println(i4); // 2017-06-10T07:39:21.026Z

		/*
		 * Puede parecer extraño, pero un instante se representa con años y mes,
		 * en cambio, no se pueden hacer operaciones con esos campos.
		 */
		System.out.println(i4.plus(1, ChronoUnit.DAYS));
		System.out.println(i4.minus(2, ChronoUnit.MINUTES));
		// java.time.temporal.UnsupportedTemporalTypeException: Unsupported
		// unit: Months
		// System.out.println(i4.plus(3, ChronoUnit.MONTHS));

		/*
		 * En la mayoría de paises, no en todos, se produce una cambio horario.
		 * Por ejemplo, en España, en marzo, la hora pasa de 2 de la madrugada a
		 * 3, y en octubre, la hora pasa de 3 de la madrugada a 2. Suele hacerse
		 * en un domingo y el objetivo es el de ahorrar energia.
		 * 
		 * En USA los cambios se producen en marzo y noviembre respectivamente.
		 * 
		 * Oracle ha decido que esto es suficientemente importante como para
		 * estar presente en el examen OCP.
		 * 
		 * En este ejemplo vemos como una hora después de la 1:30 am del 13 de
		 * marzo de 2016, el dia en el que la hora pasa de las 2 am a las 3 am,
		 * no resulta en las 2:30 sino en las 3:30.
		 * 
		 * Y algo parecido sucedería el 6 de noviembre de 2016 si restamos 1
		 * hora a la 1:30 am. Se deja como ejercicio
		 * 
		 */
		LocalDate ldate = LocalDate.of(2016, Month.MARCH, 13);
		LocalTime ltime = LocalTime.of(1, 30);
		ZoneId zoneid = ZoneId.of("US/Eastern");
		ZonedDateTime dateTime = ZonedDateTime.of(ldate, ltime, zoneid);
		System.out.println(dateTime);
		dateTime = dateTime.plusHours(1);
		System.out.println(dateTime);

		/*
		 * En el caso de España, el 29 de octubre de 2017 pasamos de las 3 am a
		 * las 2 am. Y nuestro uso horario cambia de UTC+2 a UTC+1 en la
		 * Península y de UTC+1 a UTC+0 en Canarias.
		 * 
		 * El 25 de marzo de 2018 pasamos de las 2 am a las 3 am. Por lo tanto,
		 * durante los 7 meses que dura el horario de primavera-verano,
		 * estaremos en el uso horario UTC+2 en la Península y UTC+1 en
		 * Canarias.
		 * 
		 * En este ejemplo vemos como al sumar una hora a las 2:30 am del dia 29
		 * de octube de 2017, dia en el que España pasa al horario de
		 * otoño-invierno, no sólo la hora queda igual sino que además cambiamos
		 * de uso horario, pasando de UTC+2 a UTC+1.
		 */
		LocalDate ldate2 = LocalDate.of(2017, Month.OCTOBER, 29);
		LocalTime ltime2 = LocalTime.of(2, 30);
		ZoneId zoneid2 = ZoneId.of("Europe/Madrid");
		ZonedDateTime zonedDateTime = ZonedDateTime.of(ldate2, ltime2, zoneid2);
		System.out.println(zonedDateTime); // 2017-10-29T02:30+02:00[Europe/Madrid]
		zonedDateTime = zonedDateTime.plusHours(1);
		System.out.println(zonedDateTime); // 2017-10-29T02:30+01:00[Europe/Madrid]

	}

}
