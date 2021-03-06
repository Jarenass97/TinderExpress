/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es;

import auxiliar.Constantes;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import object.Claves;
import object.Escritor;
import object.Mensaje;
import object.Usuario;

/**
 *
 * @author jorge
 */
public class frmVerMensajes extends javax.swing.JFrame {

    /**
     * Creates new form frmVerMensajes
     */
    private Usuario usuario;
    private Escritor e;
    private Claves c;
    private JFrame principal;

    public frmVerMensajes(Usuario usuario, Escritor escritor, JFrame principal) {
        initComponents();
        this.setIconImage(new ImageIcon(getClass().getResource("/resources/logo.png")).getImage());
        setLocationRelativeTo(null);
        this.usuario = usuario;
        this.e = escritor;
        this.principal = principal;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblMensajes = new javax.swing.JTable();
        btnVolver = new javax.swing.JButton();
        btnLeer = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Tinder Express");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        tblMensajes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "De", "Mensaje"
            }
        ));
        tblMensajes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMensajesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMensajes);

        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        btnLeer.setText("Marcar como le??do");
        btnLeer.setEnabled(false);
        btnLeer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLeerActionPerformed(evt);
            }
        });

        btnRefresh.setText("Refrescar");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(btnVolver)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRefresh)
                .addGap(40, 40, 40)
                .addComponent(btnLeer)
                .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVolver)
                    .addComponent(btnLeer)
                    .addComponent(btnRefresh))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        principal.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {
            cargarMensajes();
        } catch (Exception ex) {
            Logger.getLogger(frmVerMensajes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowOpened

    private void tblMensajesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMensajesMouseClicked
        DefaultTableModel tm = (DefaultTableModel) tblMensajes.getModel();
        String fila = String.valueOf(tm.getValueAt(tblMensajes.getSelectedRow(), 0));
        if (!fila.equals("null")) {
            btnLeer.setEnabled(true);
        } else {
            btnLeer.setEnabled(false);
        }
    }//GEN-LAST:event_tblMensajesMouseClicked

    private void btnLeerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLeerActionPerformed
        try {
            e.escribir(true);
            e.escribir(Constantes.LEER_MENSAJE);
            DefaultTableModel tm = (DefaultTableModel) tblMensajes.getModel();
            String transmisor = String.valueOf(tm.getValueAt(tblMensajes.getSelectedRow(), 0));
            String contenido = String.valueOf(tm.getValueAt(tblMensajes.getSelectedRow(), 1));
            Mensaje msg = new Mensaje(transmisor, usuario.getEmail(), contenido);
            e.escribir(msg);
            JOptionPane.showMessageDialog(null, "Marcado como leido");
        } catch (Exception ex) {
            Logger.getLogger(frmVerMensajes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLeerActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        principal.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        try {
            cargarMensajes();
        } catch (Exception ex) {
            Logger.getLogger(frmVerMensajes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRefreshActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLeer;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnVolver;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblMensajes;
    // End of variables declaration//GEN-END:variables

    private void cargarMensajes() throws Exception {
        DefaultTableModel tm = (DefaultTableModel) tblMensajes.getModel();
        e.escribir(true);
        e.escribir(Constantes.CARGAR_MENSAJES); 
        limpiarTabla();
        int fila = 0;        
        while ((boolean) e.leer()) {
            Mensaje msg = (Mensaje) e.leer();
            tm.addRow(new Object[2]);
            addMsg(msg, fila);
            fila++;
        }
    }

    private void addMsg(Mensaje msg, int fila) {
        tblMensajes.setValueAt(msg.getTransmisor(), fila, 0);
        tblMensajes.setValueAt(msg.getContenido(), fila, 1);
    }    
    private void limpiarTabla() {
        DefaultTableModel tb = (DefaultTableModel) tblMensajes.getModel();
        int a = tblMensajes.getRowCount() - 1;
        for (int i = a; i >= 0; i--) {
            tb.removeRow(tb.getRowCount() - 1);
        }
    }
}
