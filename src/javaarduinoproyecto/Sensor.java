/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaarduinoproyecto;

/**
 * 
 * @author David
 */
public class Sensor extends Componente {
    public String tipo;
    public int frecuencia;
    public int dist;
    
    public Sensor(String linkQR,String diagrama, String dataSheet,int Alimentacion, String tipo, int frecuencia, int dist){
        super(linkQR,diagrama,dataSheet,Alimentacion);
        this.tipo=tipo;
        this.frecuencia=frecuencia;
        this.dist=dist;
    }

    public String getTipo() {
        return tipo;
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    public int getDist() {
        return dist;
    }

    public String getLinkQR() {
        return linkQR;
    }

    public String getDiagrama() {
        return diagrama;
    }

    public String getDataSheet() {
        return dataSheet;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }

    public void setLinkQR(String linkQR) {
        this.linkQR = linkQR;
    }

    public void setDiagrama(String diagrama) {
        this.diagrama = diagrama;
    }

    public void setDataSheet(String dataSheet) {
        this.dataSheet = dataSheet;
    }

    public int getAlimentacion() {
        return Alimentacion;
    }

    public void setAlimentacion(int Alimentacion) {
        this.Alimentacion = Alimentacion;
    }

    
}
