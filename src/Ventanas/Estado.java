/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import Clases.SessionUser;
import Persistencia.Entities.Usuario;
import Persistencia.Implementacion.ImplEstado;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sebas
 */
public class Estado extends javax.swing.JFrame {

    private ImplEstado implEstado;
    private Ventanas.Usuarios usuarios;

    /**
     * Creates new form Estado
     */
    public Estado(Ventanas.Usuarios usuarios) {
        implEstado = new ImplEstado();
        this.usuarios = usuarios;
        initComponents();
        initElement();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelEstado = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtDescripcion_Estado = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnGuardar_Estado = new javax.swing.JButton();
        btnCancelar_Estado = new javax.swing.JButton();
        btnOpcion_Estado = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtCodigo_Estado = new javax.swing.JTextField();
        panel_Grid_Estado = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_Grid_Estado = new javax.swing.JTable();
        txtBuscar_Codigo_Estado = new javax.swing.JTextField();
        txtBuscar_Descripcion_Estado = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        btnBuscar_Todos = new javax.swing.JButton();

        setTitle("Estado");

        panelEstado.setBackground(new java.awt.Color(153, 153, 153));

        jLabel2.setText("Descripción");

        txtDescripcion_Estado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcion_EstadoActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        btnGuardar_Estado.setText("Guardar");
        btnGuardar_Estado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar_EstadoActionPerformed(evt);
            }
        });

        btnCancelar_Estado.setText("Cancelar");
        btnCancelar_Estado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar_EstadoActionPerformed(evt);
            }
        });

        btnOpcion_Estado.setText("Nuevo");
        btnOpcion_Estado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpcion_EstadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(btnOpcion_Estado, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(btnCancelar_Estado, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar_Estado, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(btnGuardar_Estado, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnOpcion_Estado, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(btnCancelar_Estado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );

        jLabel1.setText("Código");

        txtCodigo_Estado.setEnabled(false);

        javax.swing.GroupLayout panelEstadoLayout = new javax.swing.GroupLayout(panelEstado);
        panelEstado.setLayout(panelEstadoLayout);
        panelEstadoLayout.setHorizontalGroup(
            panelEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEstadoLayout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(panelEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelEstadoLayout.createSequentialGroup()
                        .addGroup(panelEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE))
                        .addGap(42, 42, 42)
                        .addGroup(panelEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCodigo_Estado, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                            .addComponent(txtDescripcion_Estado))))
                .addContainerGap(86, Short.MAX_VALUE))
        );
        panelEstadoLayout.setVerticalGroup(
            panelEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEstadoLayout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addGroup(panelEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigo_Estado, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescripcion_Estado, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        table_Grid_Estado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descripción"
            }
        ));
        jScrollPane1.setViewportView(table_Grid_Estado);

        jLabel3.setText("Id");

        jLabel4.setText("Descripción");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnBuscar_Todos.setText("Todos");
        btnBuscar_Todos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar_TodosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_Grid_EstadoLayout = new javax.swing.GroupLayout(panel_Grid_Estado);
        panel_Grid_Estado.setLayout(panel_Grid_EstadoLayout);
        panel_Grid_EstadoLayout.setHorizontalGroup(
            panel_Grid_EstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_Grid_EstadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_Grid_EstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_Grid_EstadoLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(panel_Grid_EstadoLayout.createSequentialGroup()
                        .addGroup(panel_Grid_EstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(txtBuscar_Codigo_Estado, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(81, 81, 81)
                        .addGroup(panel_Grid_EstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtBuscar_Descripcion_Estado)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panel_Grid_EstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBuscar_Todos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18))))
        );
        panel_Grid_EstadoLayout.setVerticalGroup(
            panel_Grid_EstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_Grid_EstadoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel_Grid_EstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panel_Grid_EstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panel_Grid_EstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_Grid_EstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBuscar_Codigo_Estado, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtBuscar_Descripcion_Estado, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnBuscar_Todos, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panel_Grid_Estado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelEstado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(panelEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panel_Grid_Estado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDescripcion_EstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcion_EstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcion_EstadoActionPerformed

    private void btnCancelar_EstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar_EstadoActionPerformed
        limpiarCampos();
        btnPorDefecto();
    }//GEN-LAST:event_btnCancelar_EstadoActionPerformed

    private void btnGuardar_EstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar_EstadoActionPerformed
        String g = btnGuardar_Estado.getText();
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
    }//GEN-LAST:event_btnGuardar_EstadoActionPerformed

    private void btnOpcion_EstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpcion_EstadoActionPerformed
        String g = btnOpcion_Estado.getText();
        switch (g) {
            case "Eliminar":
                this.eliminar();
                break;
        }
        btnPorDefecto();
        limpiarCampos();
    }//GEN-LAST:event_btnOpcion_EstadoActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        findAll(implEstado.buscar(txtBuscar_Codigo_Estado.getText(), txtBuscar_Descripcion_Estado.getText()));
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnBuscar_TodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar_TodosActionPerformed
        findAllEntities();
        txtBuscar_Codigo_Estado.setText("");
        txtBuscar_Descripcion_Estado.setText("");
    }//GEN-LAST:event_btnBuscar_TodosActionPerformed

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
            java.util.logging.Logger.getLogger(Estado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Estado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Estado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Estado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Estado(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscar_Todos;
    private javax.swing.JButton btnCancelar_Estado;
    private javax.swing.JButton btnGuardar_Estado;
    private javax.swing.JButton btnOpcion_Estado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelEstado;
    private javax.swing.JPanel panel_Grid_Estado;
    private javax.swing.JTable table_Grid_Estado;
    private javax.swing.JTextField txtBuscar_Codigo_Estado;
    private javax.swing.JTextField txtBuscar_Descripcion_Estado;
    private javax.swing.JTextField txtCodigo_Estado;
    private javax.swing.JTextField txtDescripcion_Estado;
    // End of variables declaration//GEN-END:variables

    public void cargarDatoGrid(String codigo) {
        String descripcion = txtDescripcion_Estado.getText();
        addRow(codigo, descripcion);
    }

    public void addRow(String codigo, String descripcion) {
        DefaultTableModel model = (DefaultTableModel) table_Grid_Estado.getModel();
        model.addRow(new Object[]{codigo, descripcion});
    }

    private void limpiarCampos() {
        txtCodigo_Estado.setText("");
        txtDescripcion_Estado.setText("");
    }

    private void initElement() {
        findAllEntities();
        table_Grid_Estado.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = table_Grid_Estado.rowAtPoint(e.getPoint());
                int columna = table_Grid_Estado.columnAtPoint(e.getPoint());
                if ((fila > -1) && (columna > -1)) {
                    txtCodigo_Estado.setText(getRowVector(fila)[0]);
                    txtDescripcion_Estado.setText(getRowVector(fila)[1]);
                    btnGuardar_Estado.setText("Guardar Cambios");
                    btnOpcion_Estado.setText("Eliminar");
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

            private void btnPorDefecto() {

            }
        });
    }

    public String[] getRowVector(int fila) {
        DefaultTableModel model = (DefaultTableModel) table_Grid_Estado.getModel();
        int col = table_Grid_Estado.getColumnCount();
        String result = "";
        for (int i = 0; i < col; i++) {
            result += (i == 0 ? "" : " - ") + model.getValueAt(fila, i).toString();
        }
        return result.split("-");
    }

    private void btnPorDefecto() {
        btnGuardar_Estado.setText("Guardar");
        btnOpcion_Estado.setText("Nuevo");
    }

    public void guardar() {
        try {
            Boolean validar = true;
            if (txtDescripcion_Estado.getText().equals("")) {
                validar = false;
            }
            if (validar) {
                Persistencia.Entities.Estado estado = new Persistencia.Entities.Estado();
                estado.setDescripcion(Boolean.parseBoolean(txtDescripcion_Estado.getText()));
                if (implEstado.createEstado(estado) != null) {
                    JOptionPane.showMessageDialog(this, "Estado creado");
                    cargarDatoGrid(estado.getIdEstado().toString());
                    reloadSelectEstado();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al crear el Estado");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error");
            e.printStackTrace();
        }
    }

    private void editar() {
        try {
            Boolean validar = true;
            if (txtDescripcion_Estado.getText().equals("")) {
                validar = false;
            }
            if (validar) {
                Persistencia.Entities.Estado estado = implEstado.finEstadoById(Integer.parseInt(txtCodigo_Estado.getText().trim()));
                estado.setDescripcion(Boolean.parseBoolean(txtDescripcion_Estado.getText()));
                if (implEstado.editEstado(estado) != null) {
                    JOptionPane.showMessageDialog(this, "Estado Editado");
                    clear_Table();
                    findAllEntities();
                    reloadSelectEstado();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al Editar el Estado");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error");
            e.printStackTrace();
        }
    }

    private void eliminar() {
        try {
            Persistencia.Entities.Estado estado = implEstado.finEstadoById(Integer.parseInt(txtCodigo_Estado.getText().trim()));
            Boolean validar = true;
            String mensaje = "";
            if (txtDescripcion_Estado.getText().equals("")) {
                validar = false;
            }
            if (estado.getUsuarioList().size() != 0) {
                mensaje += "Hay Usuarios con este Estado \n";
                validar = false;
            }
            if (validar) {

                if (implEstado.elimiarEstado(estado) == null) {
                    JOptionPane.showMessageDialog(this, "Estado Eliminado");
                    clear_Table();
                    findAllEntities();
                    reloadSelectEstado();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al Elimimnar el Estado");
                }
            }else {
                JOptionPane.showMessageDialog(this, mensaje);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error");
            e.printStackTrace();
        }
    }

    private void clear_Table() {
        DefaultTableModel modelo = (DefaultTableModel) table_Grid_Estado.getModel();
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
    }

    private void findAllEntities() {
        clear_Table();
        List<Persistencia.Entities.Estado> list = implEstado.findEstadoEntities();
        for (int i = 0; i < list.size(); i++) {
            addRow(list.get(i).getIdEstado().toString(), String.valueOf(list.get(i).getDescripcion()));
        }
    }

    private void findAll(List<Persistencia.Entities.Estado> list) {
        clear_Table();
        for (int i = 0; i < list.size(); i++) {
            addRow(list.get(i).getIdEstado().toString(),
                    String.valueOf( list.get(i).getDescripcion()));
        }
    }

    public void reloadSelectEstado() {
        if (this.usuarios != null) {
            this.usuarios.initEstado();
        }
    }

    private boolean isValidarSession() {
        Usuario usuario = SessionUser.getUsuario();
        Boolean res = false;
        if (usuario != null) {
            List<Persistencia.Entities.Permisos> listP = usuario.getIdperfil().getPermisosList();
            for (int i = 0; i < listP.size(); i++) {
                if (listP.get(i).getIdPermisos() == SessionUser.isEstado) {
                    res = true;
                }
            }
        }
        return res;
    }
}
