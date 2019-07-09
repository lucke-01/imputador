package es.alfatec.pruebas;

import es.alfatec.domain.Imputacion;
import java.time.LocalTime;

/**
 *
 * @author ricardo
 */
public class PruebaHoraEntradaSalida {

    public static void main(String[] args) {
        Imputacion imputacion = new Imputacion();
        imputacion.setHoraEntrada("08:00");
        imputacion.setHoraSalida("15:00");
        imputacion.setIntervaloVariableMinutos(10);
        for (int f = 0; f < 100; f++) {
            Imputacion imputacionCopia = imputacion.clone();
            System.out.println("--ORIGINAL");
            System.out.println(imputacionCopia.getHoraEntradaYHoraSalida());
            System.out.println("--MODIFICADO");
            imputacionCopia.cambiaHoraEntradaYSalidaConIntervaloVariable();

            System.out.println(imputacionCopia.getHoraEntradaYHoraSalida());
            System.out.println("----------------");
        }
    }
}
