/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import modelo.Quedada;
import modelo.Usuario;

/**
 *
 * @author VSPC-ETERNALSTORM2V5
 */
public class MiPanelQuedada extends javax.swing.JPanel {

    /**
     * Creates new form MiPanel
     */
    private Quedada quedada;
    private Usuario user;

    public MiPanelQuedada(Quedada quedada, Usuario user) {
        initComponents();
        this.quedada = quedada;
        this.user = user;
        ponerTextoEtiqueta(quedada.getNombreQuedada(), quedada.getCreador().getNickname());
    }

    public MiPanelQuedada() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblQuedada = new javax.swing.JLabel();

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        lblQuedada.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblQuedada.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQuedada.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblQuedada.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblQuedadaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblQuedada, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblQuedada, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        evt.getLocationOnScreen();
    }//GEN-LAST:event_formMouseClicked

    private void lblQuedadaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblQuedadaMouseClicked
        QuedadaFrameAjeno qFA = new QuedadaFrameAjeno(quedada,user);
        qFA.setVisible(true);
    }//GEN-LAST:event_lblQuedadaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblQuedada;
    // End of variables declaration//GEN-END:variables

    private void ponerTextoEtiqueta(String nombreQuedada, String creadorQuedada) {
        lblQuedada.setText(nombreQuedada + " creada por " + creadorQuedada);

    }
}
