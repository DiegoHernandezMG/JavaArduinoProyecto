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
public class Componente {
    public String linkQR;
    public String diagrama;
    public String dataSheet;
    public int Alimentacion;
    
    public Componente(String linkQR, String diagrama, String dataSheet, int Alimentacion){
        this.linkQR=linkQR;
        this.diagrama=diagrama;
        this.dataSheet=dataSheet;
        this.Alimentacion=Alimentacion;
    }

    public int getAlimentacion() {
        return Alimentacion;
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

    public String getLinkQR() {
        return linkQR;
    }

    public String getDiagrama() {
        return diagrama;
    }

    public String getDataSheet() {
        return dataSheet;
    }

    public void setAlimentacion(int Alimentacion) {
        this.Alimentacion = Alimentacion;
    }
       
    

}
   
