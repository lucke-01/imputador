package es.alfatec.config;

import es.alfatec.domain.Configuracion;
import es.alfatec.utils.GsonUtils;

public class AppConfig {
    public static final Configuracion configuracion;

    static {
        configuracion = GsonUtils.getConfiguracion();
    }
}
