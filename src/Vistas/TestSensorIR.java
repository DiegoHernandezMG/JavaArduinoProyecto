/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import com.google.zxing.WriterException;
import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;



/**
 *
 * @author David
 */
public class TestSensorIR extends javax.swing.JFrame {

        PanamaHitek_Arduino Arduino = new PanamaHitek_Arduino();
        SerialPortEventListener listen = new SerialPortEventListener() {
            @Override
            public void serialEvent(SerialPortEvent spe) {
                try {
                    if(Arduino.isMessageAvailable()){
                        LabelLectura.setText(Arduino.printMessage());
                    }
                } catch (SerialPortException | ArduinoException ex) {
                    Logger.getLogger(TestSensorIR.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        
    /**
     * Creates new form TestSensorIR
     */
    public TestSensorIR() {
        initComponents();
        
            try {
                Arduino.arduinoRXTX("COM4", 9600, listen);
            } catch (ArduinoException ex) {
                Logger.getLogger(TestSensorIR.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        BotonIniciar = new javax.swing.JButton();
        BotonGenerarQR = new javax.swing.JButton();
        LabelLectura = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        BotonIniciar.setText("Iniciar");
        BotonIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonIniciarActionPerformed(evt);
            }
        });

        BotonGenerarQR.setText("Generar QR");
        BotonGenerarQR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonGenerarQRActionPerformed(evt);
            }
        });

        LabelLectura.setText("Lectura: ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(181, 181, 181)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelLectura)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(BotonGenerarQR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BotonIniciar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(203, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(BotonIniciar)
                .addGap(18, 18, 18)
                .addComponent(BotonGenerarQR)
                .addGap(18, 18, 18)
                .addComponent(LabelLectura)
                .addContainerGap(122, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotonGenerarQRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonGenerarQRActionPerformed
        // TODO add your handling code here:
        try {
            Ventana ventana = new Ventana();
            ventana.setVisible(true);
            
        } catch (WriterException ex) {
            Logger.getLogger(TestSensorIR.class.getName()).log(Level.SEVERE, null, ex);
        
    }
    }//GEN-LAST:event_BotonGenerarQRActionPerformed

    private void BotonIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonIniciarActionPerformed
        // TODO add your handling code here:
        try {
            Arduino.sendData("2");
            System.out.println("Mensaje envia a Arduino");
        } catch (ArduinoException | SerialPortException ex) {
            Logger.getLogger(Led.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_BotonIniciarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TestSensorIR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TestSensorIR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TestSensorIR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TestSensorIR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TestSensorIR().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonGenerarQR;
    private javax.swing.JButton BotonIniciar;
    private javax.swing.JLabel LabelLectura;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
