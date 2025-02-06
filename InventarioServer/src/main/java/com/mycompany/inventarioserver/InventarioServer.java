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
        //puerto en el que se hara la conexión
        int puerto = 5051;

        try {

            // Obtiene la fábrica de sockets SSL predeterminada
            SSLServerSocketFactory socketFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();

            // Crea un servidor SSL en el puerto especificado
            SSLServerSocket servidor = (SSLServerSocket) socketFactory.createServerSocket(puerto);

            // Habilita el protocolo TLS v1.2 para mayor seguridad
            servidor.setEnabledProtocols(new String[]{"TLSv1.2"});

            // Mensaje de confirmación de inicio del servidor
            System.out.println("Servidor SSL iniciado en el puerto " + puerto);

            // Bucle principal para aceptar conexiones de clientes.
            while (true) {
                // Espera conexiones de clientes
                System.out.println("Esperando conexión segura de cliente...");

                // Acepta la conexión de un cliente
                SSLSocket socket = (SSLSocket) servidor.accept();

                // Crea un nuevo hilo para manejar la conexión del cliente
                ThreadsManager tManager = new ThreadsManager(socket, manager);
                tManager.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(InventarioServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
