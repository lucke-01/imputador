/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.alfatec.pruebas;

import es.alfatec.tiempo.Tiempo;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.WeekFields;
import java.util.Locale;

/**
 *
 * @author ricardo
 */
public class DiaSemana {
    public static void main(String[] args) {
        System.out.println(LocalDate.now().getDayOfWeek().getValue());
        
        System.out.println(Tiempo.getDiaSemanaFromLocalDate(LocalDate.now()));
        
        System.out.println(Tiempo.getNowFirstDayOfWeek());
        System.out.println(getNowFirstDayOfMonth());
        
    }
    public static LocalDate getNowFirstDayOfMonth() {
        return YearMonth.now().atDay(1);
    }
}
