package es.alfatec.pruebas;

import es.alfatec.config.AppConfig;
import es.alfatec.core.Vars;
import es.alfatec.domain.Configuracion;
import es.alfatec.fichero.FicheroImputacionWriter;
import es.alfatec.web.WebFuifi;
import java.io.FileWriter;
import java.io.IOException;

public class PruebaImputaciones {
    public static void main(String[] args) throws IOException {
        try (FileWriter ficheroImputaciones = new FicheroImputacionWriter(Vars.Ficheros.FICHERO_IMPUTACIONES, true)) {
            ficheroImputaciones.write("INICIO IMPUTACIONES");
            Configuracion config = AppConfig.configuracion;
            config.setTiempoImputacionMilesimas(0);
            config.setTiempoVariableImputacionMilesimas(0);
            System.out.println(config);
            WebFuifi web = new WebFuifi(AppConfig.configuracion,ficheroImputaciones);
            web.fichaImputaciones();
            ficheroImputaciones.write("FIN IMPUTACIONES");
        }
    }
}
