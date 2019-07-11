package es.alfatec.core;

public class Vars {
    public static final class Constantes {
        public static final String NOMBRE_FICHERO_CONFIGURACION = "configuracion.json";
    }
    public static final class Configuracion {
        public static final class Imputacion {
            public static final String TIPO_NORMAL = "normal";
            public static final String TIPO_DIARIA = "diaria";
            public static final String TIPO_SEMANAL = "semanal";
            public static final String TIPO_MENSUAL = "mensual";
        }
    }
    public static final class WebFuifi {

        public static final class API {

            public static final String LOGIN_URL = "https://api.fuifi.com/api/v1/login";
            public static final String INPUT_DIAS_URL = "https://api.fuifi.com/api/v1/workdayrecord/store/lazy";
        }
    }
    public static final class Ficheros {
        public static final String FICHERO_IMPUTACIONES = "imputaciones_realizadas.txt";
    }
    public static final class Http {
        public static final class STATUS_CODE {
             public static final int SUCCESS = 200;
        }
    }
            
}
