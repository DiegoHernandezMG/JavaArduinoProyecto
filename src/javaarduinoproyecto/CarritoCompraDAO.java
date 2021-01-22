/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaarduinoproyecto;

import com.itextpdf.text.BaseColor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.util.Date;

/**
 *
 * @author AXVAN
 */
public class CarritoCompraDAO {
    ConexionSQL csql = new ConexionSQL();
    Connection con= csql.conexion();
    Statement st;
    PreparedStatement pst;
    ResultSet rs;
    
    public void insertarCarrito(int idUser, int idComponente, int cantidad){
        String SQL = "insert into carrito (usuarioID,componenteID,cantidad) values(?,?,?)";

        try{
            pst = con.prepareStatement(SQL);
            pst.setInt(1, idUser);
            pst.setInt(2, idComponente);
            pst.setInt(3, cantidad);
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Su producto se ha agregado correctamente al "
                    + "carrito", "Carrito", JOptionPane.INFORMATION_MESSAGE);
                
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    
    }
    
    public void eliminarCarrito(int idFactura){
        String SQL = "DELETE from carrito where idFactura = '" + idFactura + "'";
        try{
            pst = con.prepareStatement(SQL);
            pst.executeUpdate();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void crearTicket(int idUser, String precio){
        UsuarioDAO userD = new UsuarioDAO();
        String[] datos = new String[4];
        datos = userD.devolverValores(idUser);
        Document documento = new Document();
            try{    
                java.util.Date fecha = new Date();
                int h = fecha.getHours();
                int m = fecha.getMinutes();
                int s = fecha.getSeconds();
                String ruta = System.getProperty("user.home") + "/Desktop/Ticket" + "-"+ h + "-" + m + "-" + s +".pdf";
                String SQL = "select * from carrito where usuarioID = '" + idUser + "'";
                PdfWriter.getInstance(documento, new FileOutputStream(ruta));
                documento.open();
                
                Font fuente = new Font();     
                fuente.setFamily("Courier");
                fuente.setStyle(Font.BOLD);
                fuente.setSize(20);
                
                Paragraph parrafo[] = new Paragraph[2];
                parrafo[0] = new Paragraph("Ticket de compra",fuente);
                parrafo[1] = new Paragraph(fecha.toString());
                
                PdfPTable tabla= new PdfPTable(4);
                PdfPCell celda[] = new PdfPCell[4];
                celda[0] = new PdfPCell(new Paragraph("Número de compra"));
                celda[0].setBackgroundColor(BaseColor.LIGHT_GRAY);
                celda[1] = new PdfPCell(new Paragraph("Nombre"));
                celda[1].setBackgroundColor(BaseColor.LIGHT_GRAY);
                celda[2] = new PdfPCell(new Paragraph("Precio"));
                celda[2].setBackgroundColor(BaseColor.LIGHT_GRAY);
                celda[3] = new PdfPCell(new Paragraph("Cantidad"));
                celda[3].setBackgroundColor(BaseColor.LIGHT_GRAY);
                
                parrafo[0].setAlignment(1);
                documento.add(parrafo[0]);
                documento.add(parrafo[1]);
                documento.add(new Paragraph("Nombre Usuario: " + datos[0]));
                documento.add(new Paragraph("Correo electrónico: " + datos[1]));
                documento.add(new Paragraph("Pago con la cuenta: " + datos[3]));
                documento.add(new Paragraph("--------------------------------------------------------------------"));
                tabla.addCell(celda[0]);
                tabla.addCell(celda[1]);
                tabla.addCell(celda[2]);
                tabla.addCell(celda[3]);
    
                try{
                    pst = con.prepareStatement(SQL);
                    rs = pst.executeQuery();
                    while(rs.next()){

                        String componenteId = rs.getString("componenteID");
                        tabla.addCell(rs.getString("idFactura"));
                        String SQL2 = "select * from componentes where idProducto = '" + componenteId + "'";
                        pst = con.prepareStatement(SQL2);                        
                        ResultSet rs2 = pst.executeQuery();

                        if(rs2.next()){
                            tabla.addCell(rs2.getString("nombre"));
                            tabla.addCell(rs.getString("cantidad"));
                            tabla.addCell("$" + rs2.getString("precio") + ".00");
                        }
                    }
                    documento.add(tabla);
                    Paragraph price = new Paragraph("Total: " + precio);
                    price.setAlignment(Paragraph.ALIGN_RIGHT);  
                    documento.add(price);       
                } catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Error: " + e,"Error", JOptionPane.ERROR_MESSAGE);
                }
                documento.close();
                JOptionPane.showMessageDialog(null, "Se ha generado su ticket","Compra", JOptionPane.INFORMATION_MESSAGE);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Error: " + e,"Error", JOptionPane.ERROR_MESSAGE);
            }
    }
    
    public void vaciarCarro(int idUser){
        String SQL = "DELETE from carrito where usuarioID = '" + idUser + "'";
            try{
                pst = con.prepareStatement(SQL);
                pst.executeUpdate();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "Error: " + e,"Error", JOptionPane.ERROR_MESSAGE);
            }
        
    }
    
    public void pagarCarrito(int idUser){
        String SQL1 = "select * from carrito where usuarioID = '" + idUser + "'";
        String SQL2 = "Update componentes set cantidad = ? where idProducto = ?";
        try{
            st = con.createStatement();
            rs = st.executeQuery(SQL1);
            pst = con.prepareStatement(SQL2);

            while(rs.next()){
                String SQL3 = "select * from componentes where idProducto = '" + rs.getInt("componenteID") + "'";
                int cantidad = rs.getInt("cantidad");
                int idProducto = rs.getInt("componenteID");
                Statement st3 = con.createStatement();
                ResultSet rs3 = st3.executeQuery(SQL3);
                if(rs3.next()){
                    int cantidadTotal = rs3.getInt("cantidad");
                    if(cantidad<=cantidadTotal){
                        pst.setInt(1,cantidadTotal - cantidad);
                        pst.setInt(2, idProducto);
                        pst.executeUpdate();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "No hay cantidad suficiente del producto ","Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Pago realizado correctamente","Compra", JOptionPane.INFORMATION_MESSAGE);
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
