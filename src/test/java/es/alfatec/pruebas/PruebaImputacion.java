package es.alfatec.pruebas;

import es.alfatec.config.AppConfig;
import es.alfatec.domain.Configuracion;
import java.io.IOException;

public class PruebaImputacion {
    public static void main(String[] args) throws IOException {
        Configuracion config = AppConfig.configuracion;
        config.setTiempoImputacionMilesimas(0);
        config.setTiempoVariableImputacionMilesimas(0);
        WebFuifiPrueba webPrueba = new WebFuifiPrueba(config);
        webPrueba.iniciaFichaje();
    }
}
