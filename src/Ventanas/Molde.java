/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import Clases.ComboItem;
import Clases.SessionUser;
import Persistencia.Entities.EstadoMoldeMaquina;
import Persistencia.Entities.Cliente;

import Persistencia.Entities.MaterialMolde;
import Persistencia.Implementacion.ImplCliente;
import Persistencia.Implementacion.ImplEstadoMolde;
import javax.swing.JOptionPane;
import Persistencia.Implementacion.ImplMolde;
import Persistencia.Implementacion.ImplEstado;
import Persistencia.Implementacion.ImplMaterialMolde;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sebas
 */
public class Molde extends javax.swing.JFrame {

    private ImplMolde implMolde = null;
    private ImplCliente implCliente = null;
    private ImplMaterialMolde implMaterialMolde = null;
    private ImplEstadoMolde implEstadoMolde = null;

    public Molde() {

        implMolde = new ImplMolde();
        implCliente = new ImplCliente();
        implMaterialMolde = new ImplMaterialMolde();
        implEstadoMolde = new ImplEstadoMolde();
        initComponents();
        initElement();
        initData();
    }

    public void initElement() {
        findAllEntities();
        TableGridMolde.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = TableGridMolde.rowAtPoint(e.getPoint());
                int columna = TableGridMolde.columnAtPoint(e.getPoint());
                if ((fila > -1) && (columna > -1)) {

                    Persistencia.Entities.Molde molde = implMolde.finMolde(Integer.parseInt((getRowVector(fila)[0]).trim()));

                    selectItemCBX(cmbxCliente, molde, "Cliente");
                    selectItemCBX(cmbxEstadoMolde, molde, "EstadoMolde");
                    selectItemCBX(cmbxMaterialMolde, molde, "MaterialMolde");

                    txtCodigo.setText(molde.getIdCliente().toString());
                    txtNombre_molde.setText(molde.getNombremolde());
                    txtCavidades.setText(molde.getCavidadesmolde());
                    txtDuctos_molde.setText(molde.getDuctosmolde());
                    txtCanales_enfriamiento.setText(molde.getCanalesenfriamientomolde());
                    txtBarras_expulsoras.setText(molde.getBarrasexpulsorasmolde());
                    txtPeso_molde.setText(String.valueOf(molde.getPesomolde()));
                    txtTiempo_ciclo.setText(String.valueOf(molde.getCiclotiempomolde()));
                    txtPropiedad_intel.setText(molde.getPropiedadintelectualmolde());
                    txtFecha_creacion.setDate(molde.getFechacreacionmolde());
                    txtFecha_ingreso.setDate(molde.getFechaingresomolde());
                    txtObservaciones.setText(molde.getObservacionesmolde());

                    btnGuardar.setText("Guardar Cambios");
                    btnNuevo.setText("Eliminar");
                    panelMolde.setVisible(true);

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

    public void selectItemCBX(JComboBox jComboBox, Persistencia.Entities.Molde molde, String accion) {
        for (int i = 0; i < jComboBox.getItemCount(); i++) {
            ComboItem comboItem = (ComboItem) jComboBox.getItemAt(i);
            switch (accion) {
                case "Cliente":
                    if (comboItem.getValue().equals(molde.getIdCliente().getIdCliente().toString())) {
                        jComboBox.setSelectedIndex(i);
                    }
                    break;
                case "EstadoMolde":
                    if (comboItem.getValue().equals(molde.getIdEstadoMoldeMaquina().getIdEstadoMoldeMaquina().toString())) {
                        jComboBox.setSelectedItem(i);
                    }
                    break;
                case "MaterialMolde":
                    if (comboItem.getValue().equals(molde.getIdMaterialMolde().getIdMaterialMolde().toString())) {
                        jComboBox.setSelectedItem(i);
                    }
                    break;
            }
        }

    }

    public String[] getRowVector(int fila) {
        DefaultTableModel model = (DefaultTableModel) TableGridMolde.getModel();
        int col = TableGridMolde.getColumnCount();
        String result = "";
        for (int i = 0; i < col; i++) {
            result += (i == 0 ? "" : " - ") + model.getValueAt(fila, i).toString();
        }
        return result.split("-");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnMostrarFormMolde = new javax.swing.JButton();
        panelMolde = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtNombre_molde = new javax.swing.JTextField();
        txtCavidades = new javax.swing.JTextField();
        txtDuctos_molde = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtCanales_enfriamiento = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtBarras_expulsoras = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtPeso_molde = new javax.swing.JTextField();
        txtPropiedad_intel = new javax.swing.JTextField();
        txtTiempo_ciclo = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtObservaciones = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        cmbxCliente = new javax.swing.JComboBox();
        cmbxEstadoMolde = new javax.swing.JComboBox();
        cmbxMaterialMolde = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        txtFecha_ingreso = new com.toedter.calendar.JDateChooser();
        txtFecha_creacion = new com.toedter.calendar.JDateChooser();
        panelGridMolde = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableGridMolde = new javax.swing.JTable();
        btnBotonBuscar = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtBuscar_nombre = new javax.swing.JTextField();
        txtBuscar_identificador = new javax.swing.JTextField();
        btnCliente = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnMostrarFormMolde.setText("Ocultar formulario");
        btnMostrarFormMolde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarFormMoldeActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        btnGuardar.setIcon(new javax.swing.ImageIcon("C:\\Users\\yuri\\Desktop\\Philae\\src\\Imagenes\\Guardar.png")); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnNuevo.setIcon(new javax.swing.ImageIcon("C:\\Users\\yuri\\Desktop\\Philae\\src\\Imagenes\\gestionarmoldes.png")); // NOI18N
        btnNuevo.setText("Nuevo");

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ventanas/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(btnGuardar)
                .addGap(77, 77, 77)
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                .addComponent(btnCancelar)
                .addGap(83, 83, 83))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jLabel1.setText("FORMULARIO");

        jLabel2.setText("Código");

        jLabel3.setText("*Nombre molde");

        jLabel4.setText("Cavidades molde");

        jLabel5.setText("Ductos molde");

        txtCodigo.setEnabled(false);
        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });

        jLabel6.setText("Canales de enfriamiento");

        jLabel7.setText("Barras expulsoras molde ");

        jLabel8.setText("*Peso molde");

        jLabel9.setText("*Tiempo ciclo molde");

        jLabel10.setText("*Propiedad intelectual molde");

        jLabel11.setText("*Fecha creación");

        jLabel12.setText("*Observaciones");

        jLabel13.setText("*Fecha ingreso");

        jPanel4.setBackground(new java.awt.Color(153, 153, 153));

        jLabel16.setText("*Cliente");

        jLabel17.setText("*Material Molde");

        jLabel18.setText("*Estado");

        cmbxEstadoMolde.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbxMaterialMolde.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel19.setText("INFORMACIÓN ASOCIADA");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18))
                        .addGap(58, 58, 58)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbxCliente, 0, 152, Short.MAX_VALUE)
                            .addComponent(cmbxMaterialMolde, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbxEstadoMolde, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel19))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel19)
                .addGap(45, 45, 45)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbxCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbxMaterialMolde, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbxEstadoMolde, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(18, 18, 18))
        );

        txtFecha_ingreso.setDateFormatString("yyyy-MM-dd HH:mm:ss");

        txtFecha_creacion.setDateFormatString("yyyy-MM-dd HH:mm:ss");

        javax.swing.GroupLayout panelMoldeLayout = new javax.swing.GroupLayout(panelMolde);
        panelMolde.setLayout(panelMoldeLayout);
        panelMoldeLayout.setHorizontalGroup(
            panelMoldeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMoldeLayout.createSequentialGroup()
                .addGroup(panelMoldeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMoldeLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelMoldeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(panelMoldeLayout.createSequentialGroup()
                                .addGroup(panelMoldeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelMoldeLayout.createSequentialGroup()
                                        .addGroup(panelMoldeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel8))
                                        .addGap(77, 77, 77)
                                        .addGroup(panelMoldeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtNombre_molde)
                                            .addComponent(txtCavidades)
                                            .addComponent(txtDuctos_molde)
                                            .addComponent(txtCanales_enfriamiento)
                                            .addComponent(txtBarras_expulsoras)
                                            .addComponent(txtPeso_molde, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGroup(panelMoldeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelMoldeLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(panelMoldeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(panelMoldeLayout.createSequentialGroup()
                                                .addGroup(panelMoldeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel11)
                                                    .addComponent(jLabel12)
                                                    .addComponent(jLabel13))
                                                .addGap(251, 251, 251)))
                                        .addGap(0, 13, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMoldeLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(panelMoldeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtFecha_creacion, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                                            .addComponent(txtObservaciones, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                                            .addComponent(txtPropiedad_intel, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                                            .addComponent(txtFecha_ingreso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(14, 14, 14))))
                            .addGroup(panelMoldeLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(103, 103, 103)
                                .addComponent(txtTiempo_ciclo, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelMoldeLayout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(85, Short.MAX_VALUE))
        );
        panelMoldeLayout.setVerticalGroup(
            panelMoldeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMoldeLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(23, 23, 23)
                .addGroup(panelMoldeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txtPropiedad_intel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelMoldeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelMoldeLayout.createSequentialGroup()
                        .addGroup(panelMoldeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelMoldeLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(36, 36, 36)
                                .addComponent(jLabel4))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMoldeLayout.createSequentialGroup()
                                .addGroup(panelMoldeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNombre_molde, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(17, 17, 17)
                                .addGroup(panelMoldeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(txtCavidades, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFecha_ingreso, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(23, 23, 23)
                        .addGroup(panelMoldeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txtFecha_creacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelMoldeLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(96, 96, 96)
                        .addGroup(panelMoldeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(txtDuctos_molde, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(30, 30, 30)
                .addGroup(panelMoldeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMoldeLayout.createSequentialGroup()
                        .addGroup(panelMoldeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCanales_enfriamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(27, 27, 27)
                        .addGroup(panelMoldeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(txtBarras_expulsoras, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelMoldeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(txtPeso_molde, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelMoldeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTiempo_ciclo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(56, 56, 56)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        TableGridMolde.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nombre", "Cavidades", "Ductos", "Canales", "Barras", "Peso", "Tiempo"
            }
        ));
        jScrollPane1.setViewportView(TableGridMolde);

        btnBotonBuscar.setText("Buscar");
        btnBotonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBotonBuscarActionPerformed(evt);
            }
        });

        jButton6.setText("Todos");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel14.setText("Id");

        jLabel15.setText("Nombre");

        javax.swing.GroupLayout panelGridMoldeLayout = new javax.swing.GroupLayout(panelGridMolde);
        panelGridMolde.setLayout(panelGridMoldeLayout);
        panelGridMoldeLayout.setHorizontalGroup(
            panelGridMoldeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGridMoldeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelGridMoldeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(panelGridMoldeLayout.createSequentialGroup()
                        .addGroup(panelGridMoldeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(txtBuscar_identificador, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(59, 59, 59)
                        .addGroup(panelGridMoldeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelGridMoldeLayout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnBotonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelGridMoldeLayout.createSequentialGroup()
                                .addComponent(txtBuscar_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        panelGridMoldeLayout.setVerticalGroup(
            panelGridMoldeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGridMoldeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelGridMoldeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelGridMoldeLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnBotonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelGridMoldeLayout.createSequentialGroup()
                        .addGroup(panelGridMoldeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelGridMoldeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBuscar_nombre)
                            .addComponent(txtBuscar_identificador))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        btnCliente.setText("Cliente");
        btnCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelGridMolde, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelMolde, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(btnMostrarFormMolde)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(101, 101, 101)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMostrarFormMolde)
                    .addComponent(btnCliente))
                .addGap(18, 18, 18)
                .addComponent(panelMolde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelGridMolde, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void btnPorDefecto() {
        btnGuardar.setText("Guardar");
        btnNuevo.setText("Nuevo");
    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String g = btnGuardar.getText();
        switch (g) {
            case "Guardar":
                this.guardar();
                break;
            case "Guardar cambios":
                this.editar();
                break;
        }
        btnPorDefecto();
        limpiarCampos();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnMostrarFormMoldeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarFormMoldeActionPerformed
        panelMolde.setVisible(!panelMolde.isVisible());
        if (panelMolde.isVisible() == true) {
            btnMostrarFormMolde.setText("Ocultar Formulario");
        } else {
            btnMostrarFormMolde.setText("Mostrar Formulario");
        }
    }//GEN-LAST:event_btnMostrarFormMoldeActionPerformed

    private void btnBotonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBotonBuscarActionPerformed
        findAll(implMolde.buscar(txtBuscar_identificador.getText(), txtBuscar_nombre.getText()));
    }//GEN-LAST:event_btnBotonBuscarActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        findAllEntities();
        txtBuscar_identificador.setText("");
        txtBuscar_nombre.setText("");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClienteActionPerformed
        // TODO add your handling code here:
        SessionUser.isValidarSessionJFrame(new Ventanas.Cliente(this),SessionUser.isCliente);
    }//GEN-LAST:event_btnClienteActionPerformed

    private void limpiarCampos() {
        txtBarras_expulsoras.setText("");
        txtBuscar_identificador.setText("");
        txtBuscar_nombre.setText("");
        txtCanales_enfriamiento.setText("");
        txtCavidades.setText("");
        txtCodigo.setText("");
        txtDuctos_molde.setText("");

        txtNombre_molde.setText("");
        txtObservaciones.setText("");
        txtPeso_molde.setText("");
        txtPropiedad_intel.setText("");
        txtTiempo_ciclo.setText("");
    }

    public void guardar() {
        try {
            Boolean validar = true;
            if (txtNombre_molde.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Campo vacío, digitar Nombre molde");
                return;
            }
            if (txtPeso_molde.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Campo vacío, digitar Peso molde");
                return;
            }
            if (txtTiempo_ciclo.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Campo vacío, digitar Tiempo ciclo molde");
                return;
            }

            if (txtObservaciones.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Campo vacío, digitar observaciones");
                return;
            }
            if (txtPropiedad_intel.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Campo vacío, digitar a quien pertece el molde");
                return;
            }
            if (validar) {
                Persistencia.Entities.Molde molde = new Persistencia.Entities.Molde();
                molde.setNombremolde(txtNombre_molde.getText());
                molde.setCavidadesmolde(txtCavidades.getText());
                molde.setDuctosmolde(txtDuctos_molde.getText());
                molde.setCanalesenfriamientomolde(txtCanales_enfriamiento.getText());
                molde.setBarrasexpulsorasmolde(txtBarras_expulsoras.getText());
                molde.setPesomolde(Integer.parseInt(txtPeso_molde.getText()));
                molde.setCiclotiempomolde(Integer.parseInt(txtTiempo_ciclo.getText()));
                molde.setPropiedadintelectualmolde(txtPropiedad_intel.getText());
                molde.setObservacionesmolde(txtObservaciones.getText());
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                JOptionPane.showMessageDialog(this, sdf.format(txtFecha_ingreso.getDate()));
                
                molde.setFechaingresomolde(txtFecha_ingreso.getDate());
                molde.setFechacreacionmolde(txtFecha_creacion.getDate());

                ComboItem valueIdCliente = (ComboItem) cmbxCliente.getSelectedItem();
                molde.setIdCliente(implCliente.findClienteById(Integer.parseInt(valueIdCliente.getValue())));
                ComboItem valueIdMaterialMolde = (ComboItem) cmbxMaterialMolde.getSelectedItem();
                molde.setIdMaterialMolde(implMaterialMolde.finMaterialMoldeById(Integer.parseInt(valueIdMaterialMolde.getValue())));
                ComboItem valueIdEstado = (ComboItem) cmbxEstadoMolde.getSelectedItem();
                molde.setIdEstadoMoldeMaquina((implEstadoMolde.finEstadoById(Integer.parseInt(valueIdEstado.getValue()))));

                if (implMolde.createMolde(molde) != null) {
                    JOptionPane.showMessageDialog(this, "Usuario creado");
                    cargarDatoGrid(molde.getIdMolde().toString());
                    panelMolde.setVisible(false);

                } else {
                    JOptionPane.showMessageDialog(this, "Error al crear Molde");
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error");
            e.printStackTrace();
        }

    }

    private void findAllEntities() {
        clear_Table();
        List<Persistencia.Entities.Molde> list = implMolde.findMoldeEntities();
        for (int i = 0; i < list.size(); i++) {
            addRow(list.get(i).getIdCliente().toString(),
                    list.get(i).getNombremolde(),
                    list.get(i).getCavidadesmolde(),
                    list.get(i).getDuctosmolde(),
                    list.get(i).getCanalesenfriamientomolde(),
                    list.get(i).getBarrasexpulsorasmolde(),
                    String.valueOf(list.get(i).getPesomolde()),
                    String.valueOf(list.get(i).getCiclotiempomolde()),
                    list.get(i).getPropiedadintelectualmolde(),
                    list.get(i).getObservacionesmolde(),
                    list.get(i).getFechaingresomolde(),
                    list.get(i).getFechacreacionmolde());

        }
    }

    public void findAll(List<Persistencia.Entities.Molde> list) {
        clear_Table();
        for(int i=0;i< list.size();i++){
             addRow(list.get(i).getIdCliente().toString(),
                    list.get(i).getNombremolde(),
                    list.get(i).getCavidadesmolde(),
                    list.get(i).getDuctosmolde(),
                    list.get(i).getCanalesenfriamientomolde(),
                    list.get(i).getBarrasexpulsorasmolde(),
                    String.valueOf(list.get(i).getPesomolde()),
                    String.valueOf(list.get(i).getCiclotiempomolde()),
                    list.get(i).getPropiedadintelectualmolde(),
                    list.get(i).getObservacionesmolde(),
                    list.get(i).getFechaingresomolde(),
                    list.get(i).getFechacreacionmolde());
        }

    }

    public void cargarDatoGrid(String codigo) {
        String nombre = txtNombre_molde.getText();
        String cavidades = txtCavidades.getText();
        String ductos = txtDuctos_molde.getText();
        String canales = txtCanales_enfriamiento.getText();
        String barras = txtBarras_expulsoras.getText();
        String peso = txtPeso_molde.getText();
        String ciclotiempo = txtTiempo_ciclo.getText();
        String propiedad = txtPropiedad_intel.getText();
        String observaviones = txtObservaciones.getText();
        Date fechaingreo = txtFecha_ingreso.getDate();
        Date fechacreacion = txtFecha_creacion.getDate();
        addRow(codigo, nombre, cavidades, ductos, canales, barras, peso, ciclotiempo, propiedad, observaviones, fechaingreo, fechacreacion);
    }

    public void addRow(String codigo, String nombre, String cavidades, String ductos, String canales, String barras, String peso, String ciclotiempo, String propiedad, String observaviones, Date fechaingreo, Date fechacreacion) {
        DefaultTableModel model = (DefaultTableModel) TableGridMolde.getModel();
        model.addRow(new Object[]{codigo, nombre, cavidades, ductos, canales, barras, peso, ciclotiempo, propiedad, observaviones, fechaingreo, fechacreacion});

    }

    private void clear_Table() {
        DefaultTableModel modelo = (DefaultTableModel) TableGridMolde.getModel();
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
    }

    private void editar() {
        try {
            Boolean validar = true;
            if (txtPeso_molde.getText().equals("")) {
                validar = false;
            }

            if (txtTiempo_ciclo.getText().equals("")) {
                validar = false;
            }

            if (txtPropiedad_intel.getText().equals("")) {
                validar = false;
            }
            if (cmbxCliente.getItemListeners().equals("")) {
                validar = false;
            }
            if (validar) {
                Persistencia.Entities.Molde molde = implMolde.finMolde(Integer.parseInt(txtCodigo.getText().trim()));

                molde.setNombremolde(txtNombre_molde.getText());
                molde.setCavidadesmolde(txtCavidades.getText());
                molde.setDuctosmolde(txtDuctos_molde.getText());
                molde.setCanalesenfriamientomolde(txtCanales_enfriamiento.getText());
                molde.setBarrasexpulsorasmolde(txtBarras_expulsoras.getText());
                molde.setPesomolde(Integer.parseInt(txtPeso_molde.getText()));
                molde.setCiclotiempomolde(Integer.parseInt(txtTiempo_ciclo.getText()));
                molde.setPropiedadintelectualmolde(txtPropiedad_intel.getText());
                molde.setObservacionesmolde(txtObservaciones.getText());
                molde.setFechaingresomolde(txtFecha_ingreso.getDate());
                molde.setFechacreacionmolde(txtFecha_creacion.getDate());

                ComboItem valueIdCliente = (ComboItem) cmbxCliente.getSelectedItem();
                molde.setIdCliente(implCliente.findClienteById(Integer.parseInt(valueIdCliente.getValue())));
                ComboItem valueIdMaterialMolde = (ComboItem) cmbxMaterialMolde.getSelectedItem();
                molde.setIdMaterialMolde(implMaterialMolde.finMaterialMoldeById(Integer.parseInt(valueIdMaterialMolde.getValue())));
                ComboItem valueIdEstado = (ComboItem) cmbxEstadoMolde.getSelectedItem();
                molde.setIdEstadoMoldeMaquina((implEstadoMolde.finEstadoById(Integer.parseInt(valueIdEstado.getValue()))));

                if (implMolde.editMolde(molde) != null) {
                    JOptionPane.showMessageDialog(this, "Molde Editado");
                    clear_Table();
                    findAllEntities();
                    panelMolde.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Error al Editar el Molde");
                }
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
            java.util.logging.Logger.getLogger(Molde.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Molde.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Molde.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Molde.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Molde().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TableGridMolde;
    private javax.swing.JButton btnBotonBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCliente;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnMostrarFormMolde;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox cmbxCliente;
    private javax.swing.JComboBox cmbxEstadoMolde;
    private javax.swing.JComboBox cmbxMaterialMolde;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelGridMolde;
    private javax.swing.JPanel panelMolde;
    private javax.swing.JTextField txtBarras_expulsoras;
    private javax.swing.JTextField txtBuscar_identificador;
    private javax.swing.JTextField txtBuscar_nombre;
    private javax.swing.JTextField txtCanales_enfriamiento;
    private javax.swing.JTextField txtCavidades;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDuctos_molde;
    private com.toedter.calendar.JDateChooser txtFecha_creacion;
    private com.toedter.calendar.JDateChooser txtFecha_ingreso;
    private javax.swing.JTextField txtNombre_molde;
    private javax.swing.JTextField txtObservaciones;
    private javax.swing.JTextField txtPeso_molde;
    private javax.swing.JTextField txtPropiedad_intel;
    private javax.swing.JTextField txtTiempo_ciclo;
    // End of variables declaration//GEN-END:variables

    private void initData() {
        initEstadoMolde();
        initCliente();
        initMaterialmolde();

    }

    public void initEstadoMolde() {
        cmbxEstadoMolde.removeAllItems();
        List<EstadoMoldeMaquina> ListEstadoMoldeMaquina = implEstadoMolde.findEstadoMoldeMaquinaEntities();
        for (int i = 0; i < ListEstadoMoldeMaquina.size(); i++) {
            cmbxEstadoMolde.addItem(new ComboItem(ListEstadoMoldeMaquina.get(i).getDescripcionmolde(), ListEstadoMoldeMaquina.get(i).getIdEstadoMoldeMaquina().toString()));
        }

    }

    public void initCliente() {
        cmbxCliente.removeAllItems();
        List<Cliente> LisCliente = implCliente.finClienteEntities();
        for (int i = 0; i < LisCliente.size(); i++) {
            cmbxCliente.addItem(new ComboItem(LisCliente.get(i).getNombrecontacto(), LisCliente.get(i).getIdCliente().toString()));
        }
    }

    public void initMaterialmolde() {
        cmbxMaterialMolde.removeAllItems();
        List<MaterialMolde> ListMaterialMolde = implMaterialMolde.findMaterialMoldeEntities();
        for (int i = 0; i < ListMaterialMolde.size(); i++) {
            cmbxMaterialMolde.addItem(new ComboItem(ListMaterialMolde.get(i).getDescripcionmolde(), ListMaterialMolde.get(i).getIdMaterialMolde().toString()));
        }
    }
    public void permisos(){
        txtBarras_expulsoras.setEditable(false);
        txtBuscar_identificador.setEditable(false);
        txtBuscar_nombre.setEditable(false);
        txtCanales_enfriamiento.setEditable(false);
        txtCavidades.setEditable(false);
        txtCodigo.setEditable(false);
        txtDuctos_molde.setEditable(false);
        txtFecha_creacion.setEnabled(false);
        txtFecha_ingreso.setEnabled(false);
        txtNombre_molde.setEditable(false);
        txtObservaciones.setEditable(false);
        txtPeso_molde.setEditable(false);
        txtPropiedad_intel.setEditable(false);
        txtTiempo_ciclo.setEditable(false);
        btnGuardar.setEnabled(false);
        btnNuevo.setEnabled(false);
        btnCancelar.setEnabled(false);
    }

}
