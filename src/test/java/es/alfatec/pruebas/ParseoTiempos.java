package es.alfatec.pruebas;

import es.alfatec.config.AppConfig;
import es.alfatec.tiempo.Tiempo;

public class ParseoTiempos {
    public static void main(String[] args) {
        System.out.println(AppConfig.configuracion);
        System.out.println(Tiempo.localTimeToCadenaHora(Tiempo.cadenaHoraToLocalTime("08:01")));
        for (int f = 0;f<100;f++) {
            System.out.println(Tiempo.tiempoIntervaloVariableMinutos(10));
        }
    }
}
