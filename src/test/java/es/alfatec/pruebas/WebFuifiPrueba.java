package es.alfatec.pruebas;
import es.alfatec.core.Vars;
import es.alfatec.domain.Configuracion;
import es.alfatec.domain.Imputacion;
import es.alfatec.tiempo.Tiempo;
import es.alfatec.utils.GsonUtils;
import es.alfatec.web.entity.AuthorizationTokenResponse;
import es.alfatec.web.entity.ImputacionPost;
import es.alfatec.web.entity.ImputacionResponse;
import es.alfatec.web.entity.LoginPost;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WebFuifiPrueba {

    private static final Logger log = LogManager.getLogger();

    private AuthorizationTokenResponse authToken;
    private Configuracion config;    

    public WebFuifiPrueba() {
        this(null);
    }

    public WebFuifiPrueba(Configuracion config) {
        this.config = config;
    }

    public void iniciaFichaje() throws UnsupportedEncodingException, IOException {
        this.login();
        //esperamos un X Tiempo
        log.info("Login realizado esperando 5 segundos");
        this.fichaImputaciones();
    }

    public void login() throws UnsupportedEncodingException, IOException {
        LoginPost loginPost = new LoginPost(config.getUsuario(), config.getPassword(), config.getDominio());
        String loginPostString = GsonUtils.gson.toJson(loginPost);
        StringEntity loginPostEntity = new StringEntity(loginPostString);

        //peticion post
        HttpPost httpPost = new HttpPost(Vars.WebFuifi.API.LOGIN_URL);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        httpPost.setEntity(loginPostEntity);
        log.info("request login post: "+loginPostEntity);
        //respuesta
    }

    public void fichaImputaciones() throws UnsupportedEncodingException, IOException {
        //Imputaciones
        for (Imputacion imputacion : this.config.getImputaciones()) {
            log.info("iniciando imputacion");
            this.fichaImputacion(imputacion);
            log.info("terminada imputacion");
        }
    }
    
    private void fichaImputacion(Imputacion imputacion) throws UnsupportedEncodingException, IOException {
        LocalDate imputacionDiaInicio = null;
        LocalDate imputacionDiaFin = null;
        final List<LocalDate> diasExcluidosLocalDate;
        diasExcluidosLocalDate = imputacion.getDiasExcluidos() != null
                ? imputacion.getDiasExcluidos().stream().map(diaCad -> Tiempo.cadenaFechaToLocalDate(diaCad)).collect(Collectors.toList()) : new ArrayList<>();
        imputacion.setDiasSemanaExcluidos(imputacion.getDiasSemanaExcluidos() != null ? imputacion.getDiasSemanaExcluidos() : new ArrayList<>());

        if (Vars.Configuracion.Imputacion.TIPO_NORMAL.equals(imputacion.getTipo())) {
            imputacionDiaInicio = Tiempo.cadenaFechaToLocalDate(imputacion.getDiaInicio());
            imputacionDiaFin = Tiempo.cadenaFechaToLocalDate(imputacion.getDiaFin());
        } else if (Vars.Configuracion.Imputacion.TIPO_DIARIA.equals(imputacion.getTipo())) {
            imputacionDiaInicio = LocalDate.now();
            imputacionDiaFin = LocalDate.now();
        } else if (Vars.Configuracion.Imputacion.TIPO_SEMANAL.equals(imputacion.getTipo())) {
            imputacionDiaInicio = Tiempo.getNowFirstDayOfWeek();
            imputacionDiaFin = LocalDate.now();
        } else if(Vars.Configuracion.Imputacion.TIPO_MENSUAL.equals(imputacion.getTipo())) {
            imputacionDiaInicio = Tiempo.getNowFirstDayOfMonth();
            imputacionDiaFin = LocalDate.now();
        } else {
            log.error("Imputacion sin tipo");
            System.exit(100);
            return;
        }
        //incluimos el dia final
        imputacionDiaFin = imputacionDiaFin.plusDays(1);

        ImputacionPost imputacionPost = new ImputacionPost();
        imputacionPost.setEffectiveTime(imputacion.getHorasEfectivas());
        while (!imputacionDiaInicio.equals(imputacionDiaFin)) {
            //si esta en el array de dias excluidos no lo imputamos
            if (!diasExcluidosLocalDate.contains(imputacionDiaInicio)) {
                //si es finde semana no incluimos imputacion
                if (!Tiempo.isWeekend(imputacionDiaInicio)) {
                    if (!imputacion.getDiasSemanaExcluidos().contains(Tiempo.getDiaSemanaFromLocalDate(imputacionDiaInicio))) {
                        int tiempoEsperarMilesimas = config.calculaTiempoMilesimasEsperarImputacion();
                        //esperamos un X Tiempo
                        log.info("Tiempo antes de imputacion: " + Tiempo.milesismasToSegundos(tiempoEsperarMilesimas) + " segundos.");
                        Tiempo.sleep(tiempoEsperarMilesimas);
                        imputacionPost.setDate(Tiempo.localDateToCadenaFecha(imputacionDiaInicio));

                        //copiamos imputacion, modificamos sus horas y las pasamos como parametro
                        Imputacion imputacionCalcular = imputacion.clone();
                        imputacionCalcular.cambiaHoraEntradaYSalidaConIntervaloVariable();
                        imputacionPost.setTimeFrom(imputacionCalcular.getHoraEntrada());
                        imputacionPost.setTimeTo(imputacionCalcular.getHoraSalida());

                        String imputacionPostString = GsonUtils.gson.toJson(imputacionPost);
                        log.info("imputacionPOST: "+imputacionPostString);
                        StringEntity imputacionPostEntity = new StringEntity(imputacionPostString);

                        HttpPost httpPost = new HttpPost(Vars.WebFuifi.API.INPUT_DIAS_URL);
                        httpPost.setHeader("Accept", "application/json");
                        httpPost.setHeader("Content-type", "application/json");
                        //usamos el token para autentificarnos
                        httpPost.setEntity(imputacionPostEntity);
                    } else {
                        log.info("Omitiendo Dia de semana: ["+Tiempo.getDiaSemanaFromLocalDate(imputacionDiaInicio)+"] dia: [" + imputacionDiaInicio + "]");
                    }
                } else {
                    log.info("Omitiendo Dia finde semana: [" + imputacionDiaInicio + "]");
                }
            } else {
                log.info("Omitiendo Dia excluido: [" + imputacionDiaInicio + "]");
            }
            imputacionDiaInicio = imputacionDiaInicio.plusDays(1);
        }
    }

}
