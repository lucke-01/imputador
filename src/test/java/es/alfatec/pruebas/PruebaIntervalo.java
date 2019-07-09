package es.alfatec.pruebas;

import es.alfatec.config.AppConfig;
import es.alfatec.domain.Configuracion;
import es.alfatec.tiempo.Tiempo;

public class PruebaIntervalo {
    public static void main(String[] args) {
        Configuracion config= AppConfig.configuracion;
        for (int f = 0;f<100;f++) {
            System.out.println("Imputamos hora");
            //System.out.println(Tiempo.tiempoEsperarVariableMilesimas(100,50));
            //System.out.println(Tiempo.milesismasToSegundos(Tiempo.tiempoEsperarVariableMilesimas(config.getTiempoImputacionMilesimas(),config.getTiempoVariableImputacionMilesimas())));
            System.out.println(Tiempo.milesismasToSegundos(config.calculaTiempoMilesimasEsperarImputacion()));
        }
    }
}
