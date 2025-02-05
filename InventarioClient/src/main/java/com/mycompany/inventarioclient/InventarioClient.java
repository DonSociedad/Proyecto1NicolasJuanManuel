package com.mycompany.inventarioclient;

import com.mycompany.views.Interface;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import javax.swing.SwingUtilities;

/**
 * Clase principal del cliente que inicia la interfaz gráfica. Carga la
 * configuración del certificado SSL y lanza la interfaz gráfica de usuario
 * (GUI).
 */
public class InventarioClient {

    /**
     * Método principal que inicia la aplicación. Carga las propiedades del
     * archivo de configuración, configura el certificado SSL y lanza la
     * interfaz gráfica de usuario.
     *
     * @param args Argumentos de la línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        // Carga las propiedades del archivo de configuración
        Properties p = new Properties();
        try {
            p.load(new FileInputStream(new File("configuration.properties")));
        } catch (FileNotFoundException ex) {
            System.out.println("Error: Archivo de configuración no encontrado.");
        } catch (IOException ex) {
            System.out.println("Error: No se pudo leer el archivo de configuración.");
        }

        // Obtiene la ruta y la contraseña del certificado SSL desde las propiedades
        String certificateRoute = p.getProperty("SSL_CERTIFICATE_ROUTE");
        String certificatePassword = p.getProperty("SSL_PASSWORD");

        // Configura las propiedades del sistema para el certificado SSL
        System.setProperty("javax.net.ssl.keyStore", certificateRoute);
        System.setProperty("javax.net.ssl.keyStorePassword", certificatePassword);
        System.setProperty("javax.net.ssl.keyStoreType", "PKCS12");
        System.setProperty("javax.net.ssl.trustStore", certificateRoute);
        System.setProperty("javax.net.ssl.trustStorePassword", certificatePassword);
        System.setProperty("javax.net.ssl.trustStoreType", "PKCS12");

        // Inicia la interfaz gráfica en el hilo de despacho de eventos de Swing
        SwingUtilities.invokeLater(() -> {
            Interface gui = new Interface();
            gui.setVisible(true);
            gui.setLocationRelativeTo(null); // Centrar ventana
        });
    }
}
