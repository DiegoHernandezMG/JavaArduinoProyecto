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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author AXVAN
 */
public class ProductoDAO {
    ConexionSQL csql = new ConexionSQL();
    Connection con= csql.conexion();
    Statement st;
    PreparedStatement pst;
    ResultSet rs;
    
    public List ListarProductos(){
       List<Producto> ListaPr = new ArrayList();
       String SQL = "SELECT * FROM componentes";
       try {
           pst = con.prepareStatement(SQL);
           rs = pst.executeQuery();
           while (rs.next()) {               
               Producto pr = new Producto();
               pr.setIdProducto(rs.getInt("idProducto"));
               pr.setCategoria(rs.getString("categoria"));
               pr.setNombre(rs.getString("nombre"));
               pr.setCantidad(rs.getInt("cantidad"));
               pr.setPrecio(rs.getInt("precio"));
               pr.setImage(rs.getObject("image"));
               ListaPr.add(pr);
           }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return ListaPr;
    }
    
    public int consultarInventario(String nombre){
        String SQL = "select * from componentes where nombre = '"+ nombre +"' ";
        
        try{    
            st = con.createStatement();
            rs = st.executeQuery(SQL);   
            if(rs.next()){
                return rs.getInt("cantidad");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }   
        return -1;
    }
    
    public int consultarId(String nombre){
        ConexionSQL csql = new ConexionSQL();
        Connection con= csql.conexion();
        String SQL = "select * from componentes where nombre = '"+ nombre +"' ";
        
        try{    
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL);   
            if(rs.next()){
                return(rs.getInt("idProducto"));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }   
        return -1;
    }
}