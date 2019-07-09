package es.alfatec.main;

import es.alfatec.config.AppConfig;
import es.alfatec.core.Vars;
import es.alfatec.domain.Configuracion;
import es.alfatec.fichero.FicheroImputacionWriter;
import es.alfatec.web.WebFuifi;
import java.io.FileWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    
    private static final Logger log = LogManager.getLogger();
    
    public static void main(String[] args) throws Exception {
        try (FileWriter ficheroImputaciones = new FicheroImputacionWriter(Vars.Ficheros.FICHERO_IMPUTACIONES, true)) {
            log.info("INICIO IMPUTACIONES");
            ficheroImputaciones.write("INICIO IMPUTACIONES");
            Configuracion config = AppConfig.configuracion;
            log.info(config);
            WebFuifi web = new WebFuifi(AppConfig.configuracion,ficheroImputaciones);
            web.iniciaFichaje();
            ficheroImputaciones.write("FIN IMPUTACIONES");
            log.info("FIN IMPUTACIONES");
        }
    }
}
