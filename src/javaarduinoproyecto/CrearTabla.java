/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaarduinoproyecto;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
        
/**
 *
 * @author AXVAN
 */
public class CrearTabla {

    public DefaultTableModel mostrarComponentes(String categoria){
        String []  nombresColumnas = {"Categoría","Nombre","Cantidad disponible","Precio","Imagen"};
        Object [] registros = new Object[5];
        DefaultTableModel modelo = new DefaultTableModel(null,nombresColumnas){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        
        String sql = "select * from componentes";
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        try{
            ConexionSQL csql = new ConexionSQL();
            cn= csql.conexion();
            pst = cn.prepareStatement(sql);                        
            rs = pst.executeQuery();
            
            while(rs.next()){
                registros[0] = rs.getString("categoria");
                registros[1] = rs.getString("nombre");
                registros[2] = rs.getString("cantidad");
                registros[3] = "$" + rs.getString("precio") + ".00";
                
                Blob blob = rs.getBlob("image");

                if(blob != null){
                   try{
                        byte[] data = blob.getBytes(1, (int)blob.length());
                        BufferedImage img = null;
                        try{
                        img = ImageIO.read(new ByteArrayInputStream(data));
                        }catch(Exception ex){
                        System.out.println(ex.getMessage());
                        }
                    ImageIcon icono = new ImageIcon(img);
                    registros[4] = new JLabel(icono);
                        }catch(Exception ex){
                            registros[4] = "No Imagen";
                        }
                }
                else{
                    registros[4] = "No Imagen";
                }
                
                if("Todos".equals(categoria))
                    modelo.addRow(registros);
                else if(registros[0].toString().equals(categoria))
                    modelo.addRow(registros);
            }
        }
        catch(SQLException e){
            
            JOptionPane.showMessageDialog(null,"Error al conectar" + e);
            
        }
        finally
        {
            try{
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (cn != null) cn.close();
            }
            catch(SQLException e){
                JOptionPane.showMessageDialog(null,e);
            }
        }
        return modelo;
    }

    public DefaultTableModel mostrarCarrito(int usuario){
        String []  nombresColumnas = {"id","Producto","Precio","Cantidad","Imagen"};
        Object [] registros = new Object[5];
        DefaultTableModel modelo = new DefaultTableModel(null,nombresColumnas){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        
        String sql1 = "select * from carrito";
        
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null; 
        try{
            ConexionSQL csql = new ConexionSQL();
            cn= csql.conexion();
            pst = cn.prepareStatement(sql1);                        
            rs1 = pst.executeQuery();
            
            while(rs1.next()){
                int userId = rs1.getInt("usuarioID");
                if(usuario == userId){
                    registros[0] = rs1.getString("idFactura");
                    String componenteId = rs1.getString("componenteID");
                    registros[3] = rs1.getString("cantidad");
                    String sql2 = "select * from componentes where idProducto = '" + componenteId + "'";
                    pst = cn.prepareStatement(sql2);                        
                    rs2 = pst.executeQuery();
                    
                    if(rs2.next()){
                        registros[1] = rs2.getString("nombre");
                        registros[2] = "$" + rs2.getString("precio") + ".00";
                        
                        Blob blob = rs2.getBlob("image");

                        if(blob != null){
                           try{
                                byte[] data = blob.getBytes(1, (int)blob.length());
                                BufferedImage img = null;
                                try{
                                    img = ImageIO.read(new ByteArrayInputStream(data));
                                }catch(Exception ex){
                                    System.out.println(ex.getMessage());
                                }
                                ImageIcon icono = new ImageIcon(img);
                                registros[4] = new JLabel(icono);
                                }catch(Exception ex){
                                    registros[4] = "No Imagen";
                                }
                        }
                        else{
                            registros[4] = "No Imagen";
                        }
                    }
                    modelo.addRow(registros);
                }
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error al conectar" + e);
        }
        finally
        {
            try{
                if (rs1 != null) rs1.close();
                if (rs2 != null) rs2.close();
                if (pst != null) pst.close();
                if (cn != null) cn.close();
            }
            catch(SQLException e){
                JOptionPane.showMessageDialog(null,e);
            }
        }
        return modelo;
    }  
}
