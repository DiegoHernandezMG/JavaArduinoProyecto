/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaarduinoproyecto;

/**
 *
 * @author AXVAN
 */
public class Login {
    private String nombre;
    private String correo;
    private String password;
    private String noCuenta;
    private int date;
    private int cvv;

    public Login(){
    
    }
    
    public Login(String nombre, String correo, String password, String noCuenta, int date, int cvv) {
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.noCuenta = noCuenta;
        this.date = date;
        this.cvv = cvv;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNoCuenta() {
        return noCuenta;
    }

    public void setNoCuenta(String noCuenta) {
        this.noCuenta = noCuenta;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }
}
