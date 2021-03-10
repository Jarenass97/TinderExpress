/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administración;

import auxiliar.Constantes;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import object.Claves;
import object.Escritor;
import object.Preferencia;
import object.Usuario;

/**
 *
 * @author jorge
 */
public class frmAdminPreferencias extends javax.swing.JFrame {

    private Usuario usuario;
    private Preferencia preferencia;
    private Escritor e;
    private Claves c;
    private Socket servidor;
    private JFrame principal;

    public frmAdminPreferencias(Usuario usuario, Escritor escritor, Claves claves, Socket servidor, JFrame frm) throws Exception {
        initComponents();
        this.setIconImage(new ImageIcon(getClass().getResource("/resources/logo.png")).getImage());
        this.usuario = usuario;
        this.e = escritor;
        this.c = claves;
        this.servidor = servidor;
        this.principal = frm;
        setLocationRelativeTo(null);
        this.preferencia = getPreferencia();
        if (preferencia != null) {
            cargarPreferencia();
        }else{
            btnVolver.setEnabled(false);
        }
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cbRelacionSeria = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        valDeportivos = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        valArtisticos = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        valPoliticos = new javax.swing.JSpinner();
        cbHijos = new javax.swing.JCheckBox();
        btnGuardar = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Mis Preferencias");
        setUndecorated(true);

        cbRelacionSeria.setText("Busco relación seria");

        jLabel1.setText("Rellena tus preferencias");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel2.setText("para encontrar la mejor pareja para ti");

        jLabel3.setText("Deportivos:");

        jLabel4.setText("Califica los siguientes gustos del 0 al 100:");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        valDeportivos.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));

        jLabel5.setText("Artísticos:");

        valArtisticos.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));

        jLabel6.setText("Políticos:");

        valPoliticos.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));

        cbHijos.setText("Quiero tener hijos");

        btnGuardar.setText("Guardar Cambios");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnVolver.setText("VOLVER");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbRelacionSeria)
                            .addComponent(cbHijos)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jLabel3)
                        .addGap(16, 16, 16)
                        .addComponent(valDeportivos, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jLabel5)
                        .addGap(22, 22, 22)
                        .addComponent(valArtisticos, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jLabel6)
                        .addGap(28, 28, 28)
                        .addComponent(valPoliticos, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel4)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(1, 1, 1)
                .addComponent(jLabel2)
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbRelacionSeria)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(cbHijos)))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel3))
                    .addComponent(valDeportivos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel5))
                    .addComponent(valArtisticos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(valPoliticos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardar)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
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

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            e.escribir(true);
            e.escribir(Constantes.SAVEPREFERENCES);
            boolean seria = cbRelacionSeria.isSelected();
            boolean quiereHijos = cbHijos.isSelected();
            int dep = (int) valDeportivos.getValue();
            int art = (int) valArtisticos.getValue();
            int pol = (int) valPoliticos.getValue();
            Preferencia p = new Preferencia(usuario.getEmail(), dep, art, pol, seria, quiereHijos);
            e.escribir(p);
            if ((boolean) e.leer()) {
                JOptionPane.showMessageDialog(null, "Datos guardados.");
                this.dispose();
                principal.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Se ha producido un error.");
            }
        } catch (Exception ex) {
            Logger.getLogger(frmAdminPreferencias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        this.dispose();
        principal.setVisible(true);
    }//GEN-LAST:event_btnVolverActionPerformed
    private Preferencia getPreferencia() throws Exception {
        e.escribir(true);
        e.escribir(Constantes.GET_PREFERENCE);        
        Preferencia p = (Preferencia) e.leer();
        return p;
    }

    private void cargarPreferencia() {
        if (preferencia.isRelacionSeria()) {
            cbRelacionSeria.setSelected(true);
        }
        valArtisticos.setValue(preferencia.getArtisticos());
        valDeportivos.setValue(preferencia.getDeportivos());
        valPoliticos.setValue(preferencia.getPoliticos());
        if (preferencia.isQuiereHijos()) {
            cbHijos.setSelected(true);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JCheckBox cbHijos;
    private javax.swing.JCheckBox cbRelacionSeria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSpinner valArtisticos;
    private javax.swing.JSpinner valDeportivos;
    private javax.swing.JSpinner valPoliticos;
    // End of variables declaration//GEN-END:variables
}
