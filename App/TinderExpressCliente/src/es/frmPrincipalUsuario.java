/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es;

import administración.frmPerfilUsuario;
import administración.frmAdminPreferencias;
import auxiliar.Constantes;
import java.awt.Label;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import object.*;

/**
 *
 * @author jorge
 */
public class frmPrincipalUsuario extends javax.swing.JFrame {

    /**
     * Creates new form frmPrincipal
     */
    private Socket servidor;
    private Usuario usuario;
    private Escritor e;
    private Claves c;

    public frmPrincipalUsuario(Usuario usuario, Escritor escritor, Claves claves, Socket servidor) throws Exception {
        initComponents();
        this.setIconImage(new ImageIcon(getClass().getResource("/resources/logo.png")).getImage());
        this.e = escritor;
        this.c = claves;
        setLocationRelativeTo(null);
        this.usuario = usuario;
        lblNombreUsuario.setText(usuario.getNombre());
        this.servidor = servidor;
        cargarDatos();
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
        btnPreferencias = new javax.swing.JButton();
        lblNombreUsuario = new javax.swing.JLabel();
        btnPerfil = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        lblCerrar = new javax.swing.JLabel();
        btnAddAmigos = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        btnEnviarMsg = new javax.swing.JButton();
        btnVerMsgs = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tinder Express");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        btnPreferencias.setText("Mis Preferencias");
        btnPreferencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreferenciasActionPerformed(evt);
            }
        });

        lblNombreUsuario.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblNombreUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreUsuario.setText("Nombre");
        lblNombreUsuario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnPerfil.setText("Administrar Perfil");
        btnPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPerfilActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/señor.png"))); // NOI18N

        tblUsuarios.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Correo", "Cumpleaños"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblUsuarios);

        jLabel2.setText("Mis Amigos");

        lblCerrar.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        lblCerrar.setForeground(new java.awt.Color(0, 51, 255));
        lblCerrar.setText("Cerrar sesión");
        lblCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCerrarMouseClicked(evt);
            }
        });

        btnAddAmigos.setText("Añadir amigos");
        btnAddAmigos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddAmigosActionPerformed(evt);
            }
        });

        btnRefresh.setText("Refrescar");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnEnviarMsg.setText("Enviar Mensaje");
        btnEnviarMsg.setEnabled(false);
        btnEnviarMsg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarMsgActionPerformed(evt);
            }
        });

        btnVerMsgs.setText("Ver mis mensajes");
        btnVerMsgs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerMsgsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPreferencias, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(lblNombreUsuario))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAddAmigos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(lblCerrar)
                                .addGap(39, 39, 39)))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(191, 191, 191))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnVerMsgs)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEnviarMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblNombreUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPreferencias)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPerfil))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddAmigos)
                    .addComponent(btnRefresh)
                    .addComponent(btnEnviarMsg)
                    .addComponent(btnVerMsgs))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCerrar)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            e.escribir(false);
            servidor.close();
        } catch (Exception ex) {
            Logger.getLogger(frmLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowClosing

    private void btnPreferenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreferenciasActionPerformed
        abrirAdministradorPreferencias();
    }//GEN-LAST:event_btnPreferenciasActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {
            if (isPrimeraVez()) {
                this.setVisible(false);
            }
        } catch (Exception ex) {
            Logger.getLogger(frmPrincipalUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowOpened

    private void btnPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPerfilActionPerformed
        frmPerfilUsuario frm = new frmPerfilUsuario(usuario, e, servidor, this);
        frm.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnPerfilActionPerformed

    private void lblCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCerrarMouseClicked
        try {
            frmLogin frm = new frmLogin();
            this.setVisible(false);
            frm.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(frmPrincipalUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lblCerrarMouseClicked

    private void btnAddAmigosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddAmigosActionPerformed
        frmAddAmigos frm = new frmAddAmigos(usuario, e, servidor, this);
        this.setVisible(false);
        frm.setVisible(true);
    }//GEN-LAST:event_btnAddAmigosActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        try {
            cargarAmigos();
        } catch (Exception ex) {
            Logger.getLogger(frmPrincipalUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        try {
            cargarAmigos();
        } catch (Exception ex) {
            Logger.getLogger(frmPrincipalUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formComponentShown

    private void btnEnviarMsgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarMsgActionPerformed
        try {
            DefaultTableModel tm = (DefaultTableModel) tblUsuarios.getModel();
            String emailReceptor = String.valueOf(tm.getValueAt(tblUsuarios.getSelectedRow(), 1));
            String contenido = JOptionPane.showInputDialog("Mensaje para " + emailReceptor);
            if (contenido != null && !contenido.isEmpty()) {
                e.escribir(true);
                e.escribir(Constantes.ENVIAR_MENSAJE);
                Mensaje msg = new Mensaje(usuario.getEmail(), emailReceptor, contenido);
                e.escribir(msg);
                JOptionPane.showMessageDialog(null, "Mensaje enviado.");
            } else if (contenido.isEmpty()) {
                JOptionPane.showConfirmDialog(null, "No pueden enviarse mensajes vacios");
            }
        } catch (Exception ex) {
            Logger.getLogger(frmPrincipalUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEnviarMsgActionPerformed

    private void tblUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuariosMouseClicked
        DefaultTableModel tm = (DefaultTableModel) tblUsuarios.getModel();
        String fila = String.valueOf(tm.getValueAt(tblUsuarios.getSelectedRow(), 0));
        if (!fila.equals("null")) {
            btnEnviarMsg.setEnabled(true);
        } else {
            btnEnviarMsg.setEnabled(false);
        }
    }//GEN-LAST:event_tblUsuariosMouseClicked

    private void btnVerMsgsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerMsgsActionPerformed
        frmVerMensajes frm = new frmVerMensajes(usuario, e, this);
        this.setVisible(false);
        frm.setVisible(true);
    }//GEN-LAST:event_btnVerMsgsActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddAmigos;
    private javax.swing.JButton btnEnviarMsg;
    private javax.swing.JButton btnPerfil;
    private javax.swing.JButton btnPreferencias;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnVerMsgs;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCerrar;
    private javax.swing.JLabel lblNombreUsuario;
    private javax.swing.JTable tblUsuarios;
    // End of variables declaration//GEN-END:variables

    private void cargarDatos() throws Exception {
        if (isPrimeraVez()) {
            abrirAdministradorPreferencias();
        }
    }

    private boolean isPrimeraVez() throws Exception {
        e.escribir(true);
        e.escribir(Constantes.COMPROBAR_PRIMERA);
        return (boolean) e.leer();
    }

    private void abrirAdministradorPreferencias() {
        try {
            frmAdminPreferencias frm = new frmAdminPreferencias(usuario, e, c, servidor, this);
            frm.setVisible(true);
            this.setVisible(false);
        } catch (Exception ex) {
            Logger.getLogger(frmPrincipalUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cargarAmigos() throws Exception {
        DefaultTableModel tm = (DefaultTableModel) tblUsuarios.getModel();
        e.escribir(true);
        e.escribir(Constantes.CARGAR_AMIGOS);
        int fila = 0;
        limpiarTabla();
        while ((boolean) e.leer()) {
            Usuario u = (Usuario) e.leer();
            tm.addRow(new Object[3]);
            addUser(u, fila);
            fila++;
        }
    }

    private void addUser(Usuario usuario, int fila) {
        tblUsuarios.setValueAt(usuario.getNombre(), fila, 0);
        tblUsuarios.setValueAt(usuario.getEmail(), fila, 1);
        tblUsuarios.setValueAt(usuario.getFechaNac(), fila, 2);
    }

    private void limpiarTabla() {
        DefaultTableModel tb = (DefaultTableModel) tblUsuarios.getModel();
        int a = tblUsuarios.getRowCount() - 1;
        for (int i = a; i >= 0; i--) {
            tb.removeRow(tb.getRowCount() - 1);
        }
    }

}
