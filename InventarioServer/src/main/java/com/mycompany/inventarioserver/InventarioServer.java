package com.mycompany.inventarioserver;

import com.mycompany.inventario.InventarioManager;
import java.io.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.*;

/**
 * Servidor de inventario que maneja operaciones sobre productos mediante
 * conexiones SSL.
 */
public class InventarioServer {

    /**
     * Punto de entrada del servidor.
     *
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        // Carga las propiedades del archivo de configuración.
        Properties p = new Properties();
        try {
            p.load(new FileInputStream(new File("configuration.properties")));
        } catch (FileNotFoundException ex) {
            System.out.println("Error: Archivo de configuración no encontrado.");
            return;
        } catch (IOException ex) {
            System.out.println("Error: No se pudo leer el archivo de configuración.");
            return;
        }

        // Configura las propiedades del sistema para SSL.
        String certificateRoute = p.getProperty("SSL_CERTIFICATE_ROUTE");
        String certificatePassword = p.getProperty("SSL_PASSWORD");

        System.setProperty("javax.net.ssl.keyStore", certificateRoute);
        System.setProperty("javax.net.ssl.keyStorePassword", certificatePassword);
        System.setProperty("javax.net.ssl.keyStoreType", "PKCS12");
        System.setProperty("javax.net.ssl.trustStore", certificateRoute);
        System.setProperty("javax.net.ssl.trustStorePassword", certificatePassword);
        System.setProperty("javax.net.ssl.trustStoreType", "PKCS12");

        // Inicia el servidor SSL.
        InventarioManager manager = new InventarioManager();
        int puerto = 5051;

        try {
            SSLServerSocketFactory socketFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            SSLServerSocket servidor = (SSLServerSocket) socketFactory.createServerSocket(puerto);
            servidor.setEnabledProtocols(new String[]{"TLSv1.2"});
            System.out.println("Servidor SSL iniciado en el puerto " + puerto);

            // Bucle principal para aceptar conexiones de clientes.
            while (true) {
                System.out.println("Esperando conexión segura de cliente...");
                SSLSocket socket = (SSLSocket) servidor.accept();
                ThreadsManager tManager = new ThreadsManager(socket, manager);
                tManager.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(InventarioServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
