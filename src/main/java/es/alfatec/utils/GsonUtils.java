package es.alfatec.utils;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import es.alfatec.core.Vars;
import es.alfatec.domain.Configuracion;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class GsonUtils {

    private static final Logger log = LogManager.getLogger();
    
    public static final Gson gson = new Gson();

    public static Configuracion getConfiguracion() {
        Configuracion configuracion = null;
        try {
            JsonReader reader = new JsonReader(new FileReader(new File(Vars.Constantes.NOMBRE_FICHERO_CONFIGURACION)));
            configuracion = gson.fromJson(reader, Configuracion.class);
        } catch (FileNotFoundException e) {
            log.error("fallo al inicializar fichero de configuracion");
        }
        return configuracion;
    }
}
