package com.mycompany.views;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class VentanaPrincipal extends JFrame {
    private JTextArea displayArea;
    private JTextField idField, nameField, priceField, quantityField;
    private JList<String> productList; // Lista de productos
    private DefaultListModel<String> listModel; // Modelo de lista para productos

    public VentanaPrincipal() {
        setTitle("Gestión de Inventario");
        setSize(1000, 600); // Tamaño más grande para mejor visualización
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel izquierdo: Lista de productos
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createTitledBorder("Lista de Productos"));
        listModel = new DefaultListModel<>();
        productList = new JList<>(listModel);
        productList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        productList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedProduct = productList.getSelectedValue();
                if (selectedProduct != null) {
                    // Llenar campos con los datos del producto seleccionado
                    String[] datos = selectedProduct.split(" - ");
                    idField.setText(datos[0]); // ID
                    nameField.setText(datos[1]); // Nombre
                    priceField.setText(datos[2]); // Precio
                    quantityField.setText(datos[3]); // Cantidad
                }
            }
        });
        leftPanel.add(new JScrollPane(productList), BorderLayout.CENTER);
        add(leftPanel, BorderLayout.WEST);

        // Panel central: Área de visualización
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createTitledBorder("Detalles"));
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        centerPanel.add(new JScrollPane(displayArea), BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        // Panel derecho: Campos de entrada y botones
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createTitledBorder("Acciones"));

        // Panel de entrada de datos
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        inputPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Nombre:"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Precio:"));
        priceField = new JTextField();
        inputPanel.add(priceField);
        inputPanel.add(new JLabel("Cantidad:"));
        quantityField = new JTextField();
        inputPanel.add(quantityField);
        rightPanel.add(inputPanel, BorderLayout.NORTH);

        // Panel para los botones
        JPanel buttonPanel = new JPanel(new GridLayout(1, 6, 5, 5));
        JButton addButton = new JButton("Agregar");
        JButton removeButton = new JButton("Eliminar");
        JButton updateButton = new JButton("Actualizar");
        JButton searchButton = new JButton("Buscar");
        JButton reportButton = new JButton("Reporte");
        JButton clearButton = new JButton("Limpiar");

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(reportButton);
        buttonPanel.add(clearButton);
        rightPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(rightPanel, BorderLayout.EAST);

        // Acciones de los botones
        addButton.addActionListener(e -> enviarSolicitud(1));
        removeButton.addActionListener(e -> enviarSolicitud(2));
        updateButton.addActionListener(e -> enviarSolicitud(3));
        searchButton.addActionListener(e -> enviarSolicitud(4));
        reportButton.addActionListener(e -> generarReporte());
        clearButton.addActionListener(e -> limpiarCampos());

        // Cargar productos desde el servidor al inicio
        actualizarListaProductos();
    }

    private void enviarSolicitud(int opcion) {
        try (Socket socket = new Socket("127.0.0.1", 5051);
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

            System.out.println("Conectado al servidor TCP. Enviando solicitud...");

            // Envía la opción al servidor
            out.writeInt(opcion);

            // Envía los datos según la opción seleccionada
            switch (opcion) {
                case 1: // Agregar
                    int id = validarEntero(idField.getText(), "ID");
                    if (!productExists(id)) {
                        out.writeInt(id);
                        out.writeUTF(nameField.getText());
                        out.writeDouble(validarDouble(priceField.getText(), "Precio"));
                        out.writeInt(validarEntero(quantityField.getText(), "Cantidad"));
                    } else {
                        JOptionPane.showMessageDialog(this, "El ID ya existe", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case 2: // Eliminar
                    out.writeInt(validarEntero(idField.getText(), "ID"));
                    break;
                case 3: // Actualizar
                    out.writeInt(validarEntero(idField.getText(), "ID"));
                    out.writeUTF(nameField.getText());
                    out.writeDouble(validarDouble(priceField.getText(), "Precio"));
                    out.writeInt(validarEntero(quantityField.getText(), "Cantidad"));
                    break;
                case 4: // Buscar
                    out.writeUTF(nameField.getText());
                    break;
                case 5: // Reporte
                    break;
            }

            // Recibe la respuesta del servidor
            String respuesta = in.readUTF();
            displayArea.setText(respuesta); // Muestra la respuesta en el área de texto
            actualizarListaProductos();

            // Limpia los campos después de cada operación
            limpiarCampos();

        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al conectar con el servidor: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error en los datos ingresados: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

    private boolean productExists(int id) {
        for (int i = 0; i < listModel.getSize(); i++) {
            if (listModel.get(i).contains(String.valueOf(id))) {
                return true;
            }
        }
        return false;
    }

    private void limpiarCampos() {
        idField.setText("");
        nameField.setText("");
        priceField.setText("");
        quantityField.setText("");
        productList.clearSelection(); // Deseleccionar cualquier producto seleccionado
    }

    private void generarReporte() {
        try {
            Socket socket = new Socket("127.0.0.1", 5051);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            out.writeInt(5); // Opción para generar el reporte

            // Recibe la respuesta (reporte)
            String respuesta = in.readUTF();

            // Guardar el reporte en un archivo CSV
            FileWriter fileWriter = new FileWriter("reporte.csv");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(respuesta);
            bufferedWriter.close();
            JOptionPane.showMessageDialog(this, "Reporte generado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al generar el reporte: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarListaProductos() {
        listModel.clear();
        // Lógica para obtener y mostrar los productos de la base de datos o servidor
        List<String> productos = obtenerProductos();
        for (String producto : productos) {
            listModel.addElement(producto);
        }
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
            JOptionPane.showMessageDialog(this, "Error al obtener la lista de productos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return productos;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal gui = new VentanaPrincipal();
            gui.setVisible(true);
            System.out.println("Cliente TCP iniciado. Intentando conectar al servidor...");
        });
    }
}
