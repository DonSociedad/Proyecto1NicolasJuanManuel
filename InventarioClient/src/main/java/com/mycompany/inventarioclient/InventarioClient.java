package com.mycompany.inventarioclient;

import com.mycompany.views.Interface;
import javax.swing.SwingUtilities;

/**
 * Clase principal del cliente que inicia la interfaz gráfica.
 * 
 * @author Juan Manuel
 */
public class InventarioClient {

    public static void main(String[] args) {
        /**
         * Inicia la interfaz gráfica en el hilo de despacho de eventos de Swing.
         */
        SwingUtilities.invokeLater(() -> {
            Interface gui = new Interface();
            gui.setVisible(true);
            gui.setLocationRelativeTo(null); // Centrar ventana
        });
    }
}
