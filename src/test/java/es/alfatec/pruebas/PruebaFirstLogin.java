package es.alfatec.pruebas;

import es.alfatec.config.AppConfig;
import es.alfatec.domain.Configuracion;
import es.alfatec.web.WebFuifi;

import java.io.IOException;

public class PruebaFirstLogin {
    public static void main(String[] args) throws Exception {
        Configuracion config = AppConfig.configuracion;
        WebFuifi web = new WebFuifi(AppConfig.configuracion,null);
        web.login();
        System.out.println("Login correcto esperando 10 segundos");
        Thread.sleep(1000*1);
        web.firstLogin();
        System.out.println("PRUEBA OK");
    }
}
