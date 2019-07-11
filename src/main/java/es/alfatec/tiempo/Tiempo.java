package es.alfatec.tiempo;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Tiempo {
    
    private static final Logger log = LogManager.getLogger();

    public static Random random = new Random();
    public static DateTimeFormatter horasTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    public static DateTimeFormatter fechaFormatter = DateTimeFormatter.ofPattern("yyy-MM-dd");
    public static DateTimeFormatter fechaTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    public static int SEGUNDOS = 1000;
    public static int MINUTOS = 60;
    
    private static Locale LOCALE_SPAIN = new Locale("es","ES");

    public static int segundosToMilesimas(int milesimas) {
        return milesimas * SEGUNDOS;
    }

    public static int milesismasToSegundos(int milesimas) {
        return milesimas / SEGUNDOS;
    }

    public static String nowToString() {
        return LocalDateTime.now().format(fechaTimeFormatter);
    }

    /**
     * Dado un tiempo en milesimas fijo y uno variable devuelve ese tiempo
     * sumado o restado a un valor random: EJ: 1000,100 devolvera de 900 a 11000
     *
     * @param tiempoEsperar
     * @param tiempoEsperarVariable
     * @return
     */
    public static int tiempoEsperarVariableMilesimas(int tiempoEsperar, int tiempoEsperarVariable) {
        int tiempoEsperarVariableMinimo = -tiempoEsperarVariable;
        int tiempoEsperaVariableCalculado = random.nextInt((tiempoEsperarVariable - tiempoEsperarVariableMinimo) + 1) + tiempoEsperarVariableMinimo;

        return tiempoEsperar + tiempoEsperaVariableCalculado;
    }

    /**
     * formato cadena hora HH:mm EJ: 08:01 , 18:11
     *
     * @param cadenaHora
     * @return
     */
    public static LocalTime cadenaHoraToLocalTime(String cadenaHora) {
        return LocalTime.parse(cadenaHora, horasTimeFormatter);
    }

    /**
     * Formato cadena hora HH:mm
     *
     * @param localTimeHora
     * @return
     */
    public static String localTimeToCadenaHora(LocalTime localTimeHora) {
        return localTimeHora.format(horasTimeFormatter);
    }

    public static String localDateToCadenaFecha(LocalDate localDateFecha) {
        return localDateFecha.format(fechaFormatter);
    }

    public static LocalDate cadenaFechaToLocalDate(String cadenaFecha) {
        return LocalDate.parse(cadenaFecha, fechaFormatter);
    }

    public static int tiempoIntervaloVariableMinutos(int intervaloVariableMinutos) {
        int intervaloVariableMinutosMinimo = -intervaloVariableMinutos;
        return random.nextInt((intervaloVariableMinutos - intervaloVariableMinutosMinimo) + 1) + intervaloVariableMinutosMinimo;
    }

    public static boolean isWeekend(LocalDate date) {
        DayOfWeek dayOfWeek = DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK));
        switch (dayOfWeek) {
            case SATURDAY:
            case SUNDAY:
                return true;
            default:
                return false;
        }
    }

    public static void sleep(int milesimas) {
        try {
            Thread.sleep(milesimas);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
    }
    public static String getDiaSemanaFromLocalDate(LocalDate date) {
        switch (date.getDayOfWeek().getValue()) {
            case 1: return "lunes";
            case 2: return "martes";
            case 3: return "miercoles";
            case 4: return "jueves";
            case 5: return "viernes";
            case 6: return "sabado";
            case 7: return "domingo";
            default: throw new IllegalArgumentException("Error en la fecha");
        }
    }
    public static LocalDate getNowFirstDayOfWeek() {
        return LocalDate.now().with(WeekFields.of(LOCALE_SPAIN).getFirstDayOfWeek());
    }
    public static LocalDate getNowFirstDayOfMonth() {
        return YearMonth.now().atDay(1);
    }
}
