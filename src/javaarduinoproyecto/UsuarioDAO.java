/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaarduinoproyecto;

import Vistas.inicioSesion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author AXVAN
 */
public class UsuarioDAO {
    ConexionSQL csql = new ConexionSQL();
    Connection con= csql.conexion();
    PreparedStatement pst;
    ResultSet rs;
    Statement st;
    
    public boolean RegistrarUsuario(Usuario us){
        String SQL = "insert into usuarios (nombre,correo,pass,tarjeta,fecha,cvv) values(?,?,?,?,?,?)";
        try {
            pst = con.prepareStatement(SQL);
                            
            pst.setString(1, us.getNombre());
            pst.setString(2, us.getCorreo());
            pst.setString(3, us.getPassword());
            pst.setString(4, us.getNoCuenta());
            pst.setInt(5, us.getDate());
            pst.setInt(6, us.getCvv());
            pst.executeUpdate();      
            
            JOptionPane.showMessageDialog(null, "Cuenta creada, puede iniciar sesión","Cuenta", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexión: " + e, "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public List ListarUsuario(){
       List<Usuario> ListaUs = new ArrayList();
       String SQL = "SELECT * FROM usuario";
       try {
           pst = con.prepareStatement(SQL);
           rs = pst.executeQuery();
           while (rs.next()) {               
               Usuario us = new Usuario();
               us.setNombre(rs.getString("nombre"));
               us.setCorreo(rs.getString("correo"));
               us.setPassword(rs.getString("pass"));
               us.setNoCuenta(rs.getString("tarjeta"));
               us.setDate(rs.getInt("fecha"));
               us.setCvv(rs.getInt("cvv"));
               ListaUs.add(us);
           }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);        }
        return ListaUs;
    }
    
    public int consultarUsuarioId(String correo){
        String SQL = "select * from usuarios where correo = '"+ correo +"' ";
        
        try{    
            Statement st = con.createStatement();
            rs = st.executeQuery(SQL);   
            if(rs.next()){
                return(rs.getInt("idUser"));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }   
        return -1;
    }
    
    public boolean validarCvv(int idUser, int cvv){
        String SQL = "select * from usuarios where idUser = '" + idUser + "'";
        
        try{
            st = con.createStatement();
            rs= st.executeQuery(SQL);
            if(rs.next()){
                if(cvv == rs.getInt("cvv"))
                    return true;
            }      
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    
    public String[] devolverValores(int idUser){
        String SQL = "select * from usuarios where idUser = '" + idUser + "'";
        String[] valores = new String[4];
        try{
            st = con.createStatement();
            rs = st.executeQuery(SQL);

            if(rs.next()){
                valores[0]= rs.getString("nombre");
                valores[1]= rs.getString("correo");
                valores[2]= rs.getString("pass");
                valores[3]= "**** **** **** " +rs.getString("tarjeta").substring(12);
                return valores;
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e,"Error", JOptionPane.ERROR_MESSAGE);

        }
        return null;
    }
    
}