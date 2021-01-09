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
    
    public Sensor(String linkQR,String diagrama, String dataSheet, String tipo, int frecuencia, int dist){
        super(linkQR,diagrama,dataSheet);
        this.tipo=tipo;
        this.frecuencia=frecuencia;
        this.dist=dist;
    }

}
