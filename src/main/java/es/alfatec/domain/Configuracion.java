package es.alfatec.domain;

import es.alfatec.tiempo.Tiempo;
import java.io.Serializable;
import java.util.List;

public class Configuracion implements Serializable {
    private String dominio;
    private String usuario;
    private String password;
    private int tiempoImputacionMilesimas;
    private int tiempoVariableImputacionMilesimas;
    private List<Imputacion> imputaciones;

    public Configuracion() {
        this(Tiempo.segundosToMilesimas(20),Tiempo.segundosToMilesimas(10));
    }
    public Configuracion(int tiempoImputacionMilesimas,int tiempoVariableImputacionMilesimas) {
        this.tiempoImputacionMilesimas = tiempoImputacionMilesimas;
        this.tiempoVariableImputacionMilesimas = tiempoVariableImputacionMilesimas;
    }
    /**
     * Devuelve en milesimas el tiempoImputacionMilesimas sumandole o restandole entre rangos a tiempoVariableImputacionMilesimas
     * Usarse antes de cada imputacion
     * @return 
     */
    public int calculaTiempoMilesimasEsperarImputacion() {
        return Tiempo.tiempoEsperarVariableMilesimas(this.getTiempoImputacionMilesimas(),this.getTiempoVariableImputacionMilesimas());
    }
    //getters and setters
    public String getDominio() {
        return dominio;
    }
    public void setDominio(String dominio) {
        this.dominio = dominio;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public List<Imputacion> getImputaciones() {
        return imputaciones;
    }
    public void setImputaciones(List<Imputacion> imputaciones) {
        this.imputaciones = imputaciones;
    }
    public int getTiempoImputacionMilesimas() {
        return tiempoImputacionMilesimas;
    }
    public void setTiempoImputacionMilesimas(int tiempoImputacionMilesimas) {
        this.tiempoImputacionMilesimas = tiempoImputacionMilesimas;
    }
    public int getTiempoVariableImputacionMilesimas() {
        return tiempoVariableImputacionMilesimas;
    }
    public void setTiempoVariableImputacionMilesimas(int tiempoVariableImputacionMilesimas) {
        this.tiempoVariableImputacionMilesimas = tiempoVariableImputacionMilesimas;
    }
    @Override
    public String toString() {
        return "Configuracion{" + "dominio=" + dominio + ", usuario=" + usuario + ", password=" + password + ", imputaciones=" + imputaciones + '}';
    }

}
