package es.alfatec.pruebas;

import es.alfatec.config.AppConfig;
import java.io.IOException;

public class PruebaImputacionDiaria {
    public static void main(String[] args) throws IOException {
        WebFuifiPrueba webPrueba = new WebFuifiPrueba(AppConfig.configuracion);
        webPrueba.iniciaFichaje();
    }
}
