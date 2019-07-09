package es.alfatec.domain;

import es.alfatec.tiempo.Tiempo;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Imputacion implements Serializable,Cloneable {

    /**
     * Formato YYYY-MM-DD
     */
    private String diaInicio;
    /**
     * Formato YYYY-MM-DD
     */
    private String diaFin;
    /**
     * Formato HH:mm
     */
    private String horaEntrada;
    /**
     * Formato HH:mm
     */
    private String horaSalida;
    /**
     * Formato HH:mm
     */
    private String horasEfectivas;
    /**
     * Formato YYYY-MM-DD
     */
    private List<String> diasExcluidos;
    private int intervaloVariableMinutos;

    public Imputacion() {
        super();
    }

    /**
     * dados unos minutos variables cambiar la horaEntrada y horaSalida EJ: 8:00
     * y 15:00 (10minutos) puede ser de 7:50 - 14:50 a 8:10 - 15:10
     */
    public void cambiaHoraEntradaYSalidaConIntervaloVariable() {
        LocalTime horaEntradaLocalTime = Tiempo.cadenaHoraToLocalTime(horaEntrada);
        LocalTime horaSalidaLocalTime = Tiempo.cadenaHoraToLocalTime(horaSalida);
        long intervaloMinutosCalculado = Tiempo.tiempoIntervaloVariableMinutos(intervaloVariableMinutos);

        horaEntradaLocalTime = horaEntradaLocalTime.plusMinutes(intervaloMinutosCalculado);
        horaSalidaLocalTime = horaSalidaLocalTime.plusMinutes(intervaloMinutosCalculado);

        //modificamos los valores
        this.horaEntrada = Tiempo.localTimeToCadenaHora(horaEntradaLocalTime);
        this.horaSalida = Tiempo.localTimeToCadenaHora(horaSalidaLocalTime);
    }
    @Override
    public Imputacion clone() {
        Imputacion imputacionCopia = new Imputacion();
        imputacionCopia.diaInicio = this.diaInicio;
        imputacionCopia.diaFin = this.diaFin;
        imputacionCopia.horaEntrada = this.horaEntrada;
        imputacionCopia.horaSalida = this.horaSalida;
        imputacionCopia.horasEfectivas = this.horasEfectivas;
        if (this.diasExcluidos != null && !this.diasExcluidos.isEmpty()) {
            imputacionCopia.diasExcluidos = new ArrayList<>(this.diasExcluidos);
        }
        imputacionCopia.intervaloVariableMinutos = this.intervaloVariableMinutos;
        return imputacionCopia;
    }

    @Override
    public String toString() {
        return "Imputacion{" + "diaInicio=" + diaInicio + ", diaFin=" + diaFin + ", horaEntrada=" + horaEntrada + ", horaSalida=" + horaSalida + ", horasEfectivas=" + horasEfectivas + ", intervaloVariableMinutos=" + intervaloVariableMinutos + ", diasExcluidos=" + diasExcluidos + '}';
    }

    public String getHoraEntradaYHoraSalida() {
        return this.horaEntrada + " - " + this.horaSalida;
    }

    //getters and setters
    public String getDiaInicio() {
        return diaInicio;
    }

    public void setDiaInicio(String diaInicio) {
        this.diaInicio = diaInicio;
    }

    public String getDiaFin() {
        return diaFin;
    }

    public void setDiaFin(String diaFin) {
        this.diaFin = diaFin;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getHorasEfectivas() {
        return horasEfectivas;
    }

    public void setHorasEfectivas(String horasEfectivas) {
        this.horasEfectivas = horasEfectivas;
    }

    public int getIntervaloVariableMinutos() {
        return intervaloVariableMinutos;
    }

    public void setIntervaloVariableMinutos(int intervaloVariableMinutos) {
        this.intervaloVariableMinutos = intervaloVariableMinutos;
    }

    public List<String> getDiasExcluidos() {
        return diasExcluidos;
    }

    public void setDiasExcluidos(List<String> diasExcluidos) {
        this.diasExcluidos = diasExcluidos;
    }
}
