/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaarduinoproyecto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author AXVAN
 */
public class LoginDAO {
    ConexionSQL csql = new ConexionSQL();
    Connection con= csql.conexion();
    PreparedStatement pst;
    ResultSet rs;
    
    public Login log(String correo, String pass){
        Login l = new Login();
        String sql = "SELECT * FROM usuarios WHERE correo = ? AND pass = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, correo);
            pst.setString(2, pass);
            rs= pst.executeQuery();
            if (rs.next()) {
                l.setNombre(rs.getString("nombre"));
                l.setCorreo(rs.getString("correo"));
                l.setPassword(rs.getString("pass"));
                l.setNoCuenta(rs.getString("tarjeta"));
                l.setDate(rs.getInt("fecha"));
                l.setCvv(rs.getInt("cvv"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return l;
    }
    
    public boolean RegistrarUsuario(Login l){
        String SQL = "insert into usuarios (nombre,correo,pass,tarjeta,fecha,cvv) values(?,?,?,?,?,?)";
        try {
            pst = con.prepareStatement(SQL);
                            
            pst.setString(1, l.getNombre());
            pst.setString(2, l.getCorreo());
            pst.setString(3, l.getPassword());
            pst.setString(4, l.getNoCuenta());
            pst.setInt(5, l.getDate());
            pst.setInt(6, l.getCvv());
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
                System.out.println(e.toString());
            }
        }
    }
}
