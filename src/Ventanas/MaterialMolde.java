/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import Clases.SessionUser;
import Persistencia.Entities.Molde;
import Persistencia.Implementacion.ImplMaterialMolde;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;





public class MaterialMolde extends javax.swing.JFrame {

    private ImplMaterialMolde implMaterialMolde = null;
    private Ventanas.Molde molde;

    public MaterialMolde(Ventanas.Molde molde) {
        implMaterialMolde = new ImplMaterialMolde();
        this.molde = molde;
        initComponents();
        initElement();
    }
    
    
    
    @SuppressWarnings("unchecked")
     private void initElement() {
        findAllEntities();
        tableGrid_Tipo_Material_Molde.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = tableGrid_Tipo_Material_Molde.rowAtPoint(e.getPoint());
                int columna = tableGrid_Tipo_Material_Molde.columnAtPoint(e.getPoint());
                if ((fila > -1) && (columna > -1)) {
                    txtCodigo_MaterialMolde.setText(getRowVector(fila)[0]);
                    txtDescripcion_MaterialMolde.setText(getRowVector(fila)[1]);
                    btnGuardar_Material_Molde.setText("Guardar Cambios");
                    btnOpcion_MaterialMolde.setText("Eliminar");
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

        });
    }
      public String[] getRowVector(int fila) {
        DefaultTableModel model = (DefaultTableModel) tableGrid_Tipo_Material_Molde.getModel();
        int col = tableGrid_Tipo_Material_Molde.getColumnCount();
        String result = "";
        for (int i = 0; i < col; i++) {
            result += (i == 0 ? "" : " - ") + model.getValueAt(fila, i).toString();
        }
        return result.split("-");
    }

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTipo_Material = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCodigo_MaterialMolde = new javax.swing.JTextField();
        txtDescripcion_MaterialMolde = new javax.swing.JTextField();
        jPanelBotones = new javax.swing.JPanel();
        btnOpcion_MaterialMolde = new javax.swing.JButton();
        Cancelar = new javax.swing.JButton();
        btnGuardar_Material_Molde = new javax.swing.JButton();
        panelGridTipo_Material_Molde = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableGrid_Tipo_Material_Molde = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtBuscar_Codigo_Material_Molde = new javax.swing.JTextField();
        txtBuscar_Descripcion_Material_Molde = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        panelTipo_Material.setBackground(new java.awt.Color(102, 153, 255));

        jLabel1.setText("Id Material");

        jLabel2.setText("Nombre Material");

        txtCodigo_MaterialMolde.setEnabled(false);

        jPanelBotones.setBackground(new java.awt.Color(51, 255, 255));

        btnOpcion_MaterialMolde.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ventanas/Gestionar Producto.png"))); // NOI18N
        btnOpcion_MaterialMolde.setText("Nuevo");
        btnOpcion_MaterialMolde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpcion_MaterialMoldeActionPerformed(evt);
            }
        });

        Cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ventanas/cancelar.png"))); // NOI18N
        Cancelar.setText("Cancelar");
        Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarActionPerformed(evt);
            }
        });

        btnGuardar_Material_Molde.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ventanas/Guardar.png"))); // NOI18N
        btnGuardar_Material_Molde.setText("Guardar");
        btnGuardar_Material_Molde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar_Material_MoldeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBotonesLayout = new javax.swing.GroupLayout(jPanelBotones);
        jPanelBotones.setLayout(jPanelBotonesLayout);
        jPanelBotonesLayout.setHorizontalGroup(
            jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotonesLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(btnOpcion_MaterialMolde)
                .addGap(33, 33, 33)
                .addComponent(Cancelar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBotonesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar_Material_Molde)
                .addGap(97, 97, 97))
        );
        jPanelBotonesLayout.setVerticalGroup(
            jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnGuardar_Material_Molde, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cancelar)
                    .addComponent(btnOpcion_MaterialMolde))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelTipo_MaterialLayout = new javax.swing.GroupLayout(panelTipo_Material);
        panelTipo_Material.setLayout(panelTipo_MaterialLayout);
        panelTipo_MaterialLayout.setHorizontalGroup(
            panelTipo_MaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTipo_MaterialLayout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(panelTipo_MaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelTipo_MaterialLayout.createSequentialGroup()
                        .addGroup(panelTipo_MaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(36, 36, 36)
                        .addGroup(panelTipo_MaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodigo_MaterialMolde, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDescripcion_MaterialMolde, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        panelTipo_MaterialLayout.setVerticalGroup(
            panelTipo_MaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTipo_MaterialLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelTipo_MaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTipo_MaterialLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(34, 34, 34)
                        .addComponent(jLabel2))
                    .addGroup(panelTipo_MaterialLayout.createSequentialGroup()
                        .addComponent(txtCodigo_MaterialMolde, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDescripcion_MaterialMolde, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36)
                .addComponent(jPanelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        panelGridTipo_Material_Molde.setBackground(new java.awt.Color(102, 153, 255));

        tableGrid_Tipo_Material_Molde.setBackground(new java.awt.Color(153, 153, 255));
        tableGrid_Tipo_Material_Molde.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre"
            }
        ));
        jScrollPane1.setViewportView(tableGrid_Tipo_Material_Molde);

        jLabel3.setText("Id Material");

        jLabel4.setText("Nombre Material");

        jButton1.setIcon(new javax.swing.ImageIcon("C:\\Users\\yuri\\Desktop\\Philae\\src\\Imagenes\\Buscar.png")); // NOI18N
        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon("C:\\Users\\yuri\\Desktop\\Philae\\src\\Imagenes\\gestionarmoldes.png")); // NOI18N
        jButton2.setText("Todos");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelGridTipo_Material_MoldeLayout = new javax.swing.GroupLayout(panelGridTipo_Material_Molde);
        panelGridTipo_Material_Molde.setLayout(panelGridTipo_Material_MoldeLayout);
        panelGridTipo_Material_MoldeLayout.setHorizontalGroup(
            panelGridTipo_Material_MoldeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGridTipo_Material_MoldeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelGridTipo_Material_MoldeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(panelGridTipo_Material_MoldeLayout.createSequentialGroup()
                        .addGroup(panelGridTipo_Material_MoldeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtBuscar_Codigo_Material_Molde, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelGridTipo_Material_MoldeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBuscar_Descripcion_Material_Molde, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelGridTipo_Material_MoldeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(28, 28, 28)))
                .addContainerGap())
        );
        panelGridTipo_Material_MoldeLayout.setVerticalGroup(
            panelGridTipo_Material_MoldeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGridTipo_Material_MoldeLayout.createSequentialGroup()
                .addGroup(panelGridTipo_Material_MoldeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelGridTipo_Material_MoldeLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(panelGridTipo_Material_MoldeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)))
                    .addGroup(panelGridTipo_Material_MoldeLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelGridTipo_Material_MoldeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar_Descripcion_Material_Molde, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscar_Codigo_Material_Molde, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelTipo_Material, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelGridTipo_Material_Molde, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(panelTipo_Material, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelGridTipo_Material_Molde, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardar_Material_MoldeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar_Material_MoldeActionPerformed
        String g = btnGuardar_Material_Molde.getText();
        switch (g) {
            case "Guardar":
                this.guardar();
                break;
            case "Guardar Cambios":
                this.editar();
                break;
        }
        btnPorDefecto();
        limpiarCampos();
    }//GEN-LAST:event_btnGuardar_Material_MoldeActionPerformed


    private void btnOpcion_MaterialMoldeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpcion_MaterialMoldeActionPerformed
        String g = btnOpcion_MaterialMolde.getText();
        switch (g) {
            case "Eliminar":
                this.eliminar();
                break;
        }
        btnPorDefecto();
        limpiarCampos();
    }//GEN-LAST:event_btnOpcion_MaterialMoldeActionPerformed

    private void CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarActionPerformed
        // TODO add your handling code here:
        limpiarCampos();
        btnPorDefecto();
    }//GEN-LAST:event_CancelarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        findAll(implMaterialMolde.buscar(txtBuscar_Codigo_Material_Molde.getText(),txtBuscar_Descripcion_Material_Molde.getText()));
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        findAllEntities();
        txtBuscar_Codigo_Material_Molde.setText("");
        txtBuscar_Descripcion_Material_Molde.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed
    private void btnPorDefecto() {
        btnGuardar_Material_Molde.setText("Guardar");
        btnOpcion_MaterialMolde.setText("Nuevo");

    }

    private void limpiarCampos() {
        btnGuardar_Material_Molde.setText("");
        btnOpcion_MaterialMolde.setText("");

    }

    public void guardar() {
        try {
            String mensaje = "No entra a la validacion";
            Boolean validar = true;
            if (txtDescripcion_MaterialMolde.getText().equals("")) {
                validar = false;
            }
            if (validar) {
                Persistencia.Entities.MaterialMolde materialmolde = new Persistencia.Entities.MaterialMolde();
                materialmolde.setDescripcionmolde(txtDescripcion_MaterialMolde.getText());
                if (implMaterialMolde.createMaterialMolde(materialmolde) != null) {
                    JOptionPane.showMessageDialog(this, "Material molde creado");
                    cargarDatoGrid(materialmolde.getIdMaterialMolde().toString());   
                    reloadSelectMaterialMolde ();

                }else {
                    JOptionPane.showMessageDialog(this, "Error al crear el Material del molde");
                }

            }
            else {
                JOptionPane.showMessageDialog(this, mensaje);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error");
            e.printStackTrace();

        }
    }

        public void cargarDatoGrid(String codigo) {
        String descripcion = txtDescripcion_MaterialMolde.getText();
        addRow(codigo, descripcion);
    }

    public void addRow(String codigo, String descripcion) {
        DefaultTableModel table = (DefaultTableModel) tableGrid_Tipo_Material_Molde.getModel();
        table.addRow(new Object[]{codigo, descripcion});
    }

    public void editar() {
        try {
            Boolean validar = true;

            if (txtDescripcion_MaterialMolde.getText().equals("")) {
                validar = false;
            }
            if (validar) {
                Persistencia.Entities.MaterialMolde materialmolde = implMaterialMolde.findMaterialMoldeById(Integer.parseInt(txtCodigo_MaterialMolde.getText().trim()));
                materialmolde.setDescripcionmolde(txtDescripcion_MaterialMolde.getText());
                if (implMaterialMolde.editarmaterialMolde(materialmolde) != null) {
                    JOptionPane.showMessageDialog(this, "Material Maquina Editado");
                    clear_Table();
                    findAllEntities();
                    reloadSelectMaterialMolde();
                }else {
                    JOptionPane.showMessageDialog(this, "Error al Editar el Tipo de Documento");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error");
            e.printStackTrace();
        }
    }

    private void clear_Table() {
        DefaultTableModel modelo = (DefaultTableModel) tableGrid_Tipo_Material_Molde.getModel();
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
    }

    private void findAllEntities() {
        clear_Table();
        List<Persistencia.Entities.MaterialMolde> list = implMaterialMolde.findMaterialMoldeEntities();
        for (int i = 0; i < list.size(); i++) {
            addRow(list.get(i).getIdMaterialMolde().toString(), list.get(i).getDescripcionmolde());
        }
    }
    private void findAll(List<Persistencia.Entities.MaterialMolde> list) {
        clear_Table();
        for (int i = 0; i < list.size(); i++) {
            addRow(list.get(i).getIdMaterialMolde().toString(),
                    list.get(i).getDescripcionmolde().toString());
        }
    }
    public void reloadSelectMaterialMolde() {
        if (this.molde != null) {
            this.molde.initMaterialmolde();
        }
    }

    private void eliminar() {
        try {
            Persistencia.Entities.MaterialMolde materialMolde = implMaterialMolde.findMaterialMoldeById(Integer.parseInt(txtCodigo_MaterialMolde.getText().trim()));
            Boolean validar = true;
            String mensaje = "No entra a la validacion";
            if (txtCodigo_MaterialMolde.getText().equals("")) {
                validar = false;
            }
            if (materialMolde.getMoldeList().size() != 0) {
                mensaje += "Hay Moldes con éste tipo de Material \n";
                validar = false;
            }
            if (validar) {
                if(implMaterialMolde.elimiarMaterialMolde(materialMolde)==null){
                    JOptionPane.showMessageDialog(this, "Material  Eliminado");
                    clear_Table();
                    findAllEntities();
                    reloadSelectMaterialMolde();
                    
                }else {
                    JOptionPane.showMessageDialog(this, "Error al Eliminar Material Molde");
                }
                
            } else {
                JOptionPane.showMessageDialog(this, mensaje);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error");
            e.printStackTrace();
        }
    }

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
            java.util.logging.Logger.getLogger(MaterialMolde.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MaterialMolde.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MaterialMolde.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MaterialMolde.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MaterialMolde(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cancelar;
    private javax.swing.JButton btnGuardar_Material_Molde;
    private javax.swing.JButton btnOpcion_MaterialMolde;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelGridTipo_Material_Molde;
    private javax.swing.JPanel panelTipo_Material;
    private javax.swing.JTable tableGrid_Tipo_Material_Molde;
    private javax.swing.JTextField txtBuscar_Codigo_Material_Molde;
    private javax.swing.JTextField txtBuscar_Descripcion_Material_Molde;
    private javax.swing.JTextField txtCodigo_MaterialMolde;
    private javax.swing.JTextField txtDescripcion_MaterialMolde;
    // End of variables declaration//GEN-END:variables
}
