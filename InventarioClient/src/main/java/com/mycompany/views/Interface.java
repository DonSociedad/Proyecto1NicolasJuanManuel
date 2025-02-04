/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.views;

import java.awt.BorderLayout;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author nmedi
 */
public class Interface extends javax.swing.JFrame {

    public Interface() {
        initComponents();
        actualizarListaProductos();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ListaProductos = new javax.swing.JList<>();
        jPanel2 = new javax.swing.JPanel();
        BTN_Agregar = new javax.swing.JButton();
        BTN_Eliminar = new javax.swing.JButton();
        BTN_Buscar = new javax.swing.JButton();
        BTN_Actualizar = new javax.swing.JButton();
        TextField_ID = new javax.swing.JTextField();
        TextField_Nombre = new javax.swing.JTextField();
        TextField_Precio = new javax.swing.JTextField();
        TextField_Cantidad = new javax.swing.JTextField();
        BTN_Reporte = new javax.swing.JButton();
        BTN_Limpiar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1000, 600));
        setResizable(false);

        jPanel3.setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 48)); // NOI18N
        jLabel2.setText("Lista De Productos");

        ListaProductos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        ListaProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ListaProductosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(ListaProductos);

        BTN_Agregar.setText("Agregar Producto");
        BTN_Agregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTN_AgregarMouseClicked(evt);
            }
        });

        BTN_Eliminar.setText("Eliminar Producto");
        BTN_Eliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTN_EliminarMouseClicked(evt);
            }
        });

        BTN_Buscar.setText("Buscar Producto");
        BTN_Buscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTN_BuscarMouseClicked(evt);
            }
        });

        BTN_Actualizar.setText("Actualizar Producto");
        BTN_Actualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTN_ActualizarMouseClicked(evt);
            }
        });
        BTN_Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_ActualizarActionPerformed(evt);
            }
        });

        TextField_ID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_IDActionPerformed(evt);
            }
        });

        TextField_Cantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_CantidadActionPerformed(evt);
            }
        });

        BTN_Reporte.setText("Reporte CSV");
        BTN_Reporte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTN_ReporteMouseClicked(evt);
            }
        });

        BTN_Limpiar.setText("Limpiar Campos");
        BTN_Limpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTN_LimpiarMouseClicked(evt);
            }
        });

        jLabel1.setText("ID:");

        jLabel3.setText("Nombre:");

        jLabel4.setText("Precio:");

        jLabel5.setText("Cantidad:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TextField_ID)
                            .addComponent(TextField_Nombre)
                            .addComponent(TextField_Precio)
                            .addComponent(TextField_Cantidad)
                            .addComponent(BTN_Limpiar, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BTN_Reporte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(BTN_Actualizar, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                                    .addComponent(BTN_Buscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(BTN_Eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                                    .addComponent(BTN_Agregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addGap(27, 27, 27))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextField_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextField_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextField_Precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextField_Cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addComponent(BTN_Limpiar)
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BTN_Actualizar, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(BTN_Eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BTN_Buscar, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(BTN_Agregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(39, 39, 39)
                .addComponent(BTN_Reporte)
                .addGap(67, 67, 67))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)))
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 57, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 6, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BTN_LimpiarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_LimpiarMouseClicked
        limpiarCampos();
    }//GEN-LAST:event_BTN_LimpiarMouseClicked

    private void BTN_ReporteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_ReporteMouseClicked
        generarReporte();
    }//GEN-LAST:event_BTN_ReporteMouseClicked

    private void TextField_CantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_CantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_CantidadActionPerformed

    private void TextField_IDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_IDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_IDActionPerformed

    private void BTN_ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ActualizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_ActualizarActionPerformed

    private void BTN_ActualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_ActualizarMouseClicked
        enviarSolicitud(3);
    }//GEN-LAST:event_BTN_ActualizarMouseClicked

    private void BTN_BuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_BuscarMouseClicked
        enviarSolicitud(4);
    }//GEN-LAST:event_BTN_BuscarMouseClicked

    private void BTN_EliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_EliminarMouseClicked
        enviarSolicitud(2);
    }//GEN-LAST:event_BTN_EliminarMouseClicked

    private void BTN_AgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_AgregarMouseClicked
        enviarSolicitud(1);
    }//GEN-LAST:event_BTN_AgregarMouseClicked

    private void ListaProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListaProductosMouseClicked
        if (ListaProductos.getSelectedIndex() != -1) { // Verifica que se haya seleccionado un elemento
        String seleccionado = ListaProductos.getSelectedValue();
        
        // Se asume que el formato del producto es: "ID - Nombre - Precio - Cantidad"
        String[] datos = seleccionado.split(" - ");

        if (datos.length == 4) {
            TextField_ID.setText(datos[0].trim());        // ID
            TextField_Nombre.setText(datos[1].trim());    // Nombre
            TextField_Precio.setText(datos[2].trim());    // Precio
            TextField_Cantidad.setText(datos[3].trim());  // Cantidad
        } else {
            JOptionPane.showMessageDialog(this, "Error al procesar el producto seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    }//GEN-LAST:event_ListaProductosMouseClicked

    private void enviarSolicitud(int opcion) {
        try (Socket socket = new Socket("127.0.0.1", 5051);
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {
            
            out.writeInt(opcion);
            
            switch (opcion) {
                case 1:
                    out.writeInt(validarEntero(TextField_ID.getText(), "ID"));
                    out.writeUTF(TextField_Nombre.getText());
                    out.writeDouble(validarDouble(TextField_Precio.getText(), "Precio"));
                    out.writeInt(validarEntero(TextField_Cantidad.getText(), "Cantidad"));
                    break;
                case 2:
                    out.writeInt(validarEntero(TextField_ID.getText(), "ID"));
                    break;
                case 3:
                    out.writeInt(validarEntero(TextField_ID.getText(), "ID"));
                    out.writeUTF(TextField_Nombre.getText());
                    out.writeDouble(validarDouble(TextField_Precio.getText(), "Precio"));
                    out.writeInt(validarEntero(TextField_Cantidad.getText(), "Cantidad"));
                    break;
                case 4:
                    out.writeUTF(TextField_Nombre.getText());
                    break;
            }
            
            String respuesta = in.readUTF();
            JOptionPane.showMessageDialog(this, respuesta, "Respuesta del Servidor", JOptionPane.INFORMATION_MESSAGE);
            actualizarListaProductos();
            limpiarCampos();
        } catch (IOException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private int validarEntero(String texto, String campo) throws NumberFormatException {
        try {
            return Integer.parseInt(texto);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("El campo " + campo + " debe ser un número entero válido.");
        }
    }
    
    private double validarDouble(String texto, String campo) throws NumberFormatException {
        try {
            return Double.parseDouble(texto);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("El campo " + campo + " debe ser un número decimal válido.");
        }
    }
    
    private void limpiarCampos() {
        TextField_ID.setText("");
        TextField_Nombre.setText("");
        TextField_Precio.setText("");
        TextField_Cantidad.setText("");
        ListaProductos.clearSelection();
    }
    
    private DefaultListModel<String> listModel = new DefaultListModel<>();

    private void actualizarListaProductos() {
        listModel.clear();
        // Lógica para obtener y mostrar los productos de la base de datos o servidor
        List<String> productos = obtenerProductos();
        for (String producto : productos) {
            listModel.addElement(producto);
        }
        ListaProductos.setModel(listModel); // Actualizar la JList con el nuevo modelo
    }

    private List<String> obtenerProductos() {
        List<String> productos = new ArrayList<>();
        try (Socket socket = new Socket("127.0.0.1", 5051);
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

            // Enviar solicitud para obtener la lista de productos
            out.writeInt(6); // Opción 6: Obtener lista de productos

            // Recibir la respuesta del servidor
            String respuesta = in.readUTF();
            String[] productosArray = respuesta.split("\n"); // Dividir la respuesta en líneas

            // Agregar cada producto a la lista
            for (String producto : productosArray) {
                productos.add(producto);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al obtener la lista de productos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return productos;
    }

    
    private void generarReporte() {
        try (Socket socket = new Socket("127.0.0.1", 5051);
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

            out.writeInt(5); // Opción para generar el reporte

            // Recibe la respuesta (reporte)
            String respuesta = in.readUTF();

            // Guardar el reporte en un archivo CSV
            try (FileWriter fileWriter = new FileWriter("reporte.csv");
                 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                bufferedWriter.write(respuesta);
            }

            JOptionPane.showMessageDialog(this, "Reporte generado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al generar el reporte: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Actualizar;
    private javax.swing.JButton BTN_Agregar;
    private javax.swing.JButton BTN_Buscar;
    private javax.swing.JButton BTN_Eliminar;
    private javax.swing.JButton BTN_Limpiar;
    private javax.swing.JButton BTN_Reporte;
    private javax.swing.JList<String> ListaProductos;
    private javax.swing.JTextField TextField_Cantidad;
    private javax.swing.JTextField TextField_ID;
    private javax.swing.JTextField TextField_Nombre;
    private javax.swing.JTextField TextField_Precio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
