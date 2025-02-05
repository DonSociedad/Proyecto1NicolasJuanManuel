package com.mycompany.inventarioserver;

import com.mycompany.inventario.InventarioManager;
import com.mycompany.inventario.Product;
import com.mycompany.inventario.logs.Logs;
import java.io.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.*;
import javax.swing.SwingUtilities;

/**
 * Clase principal del servidor de inventario.
 * Este servidor maneja las solicitudes de los clientes para realizar operaciones
 * como agregar, eliminar, actualizar, buscar productos y generar reportes.
 * La comunicación con los clientes se realiza mediante sockets SSL para garantizar
 * la seguridad de los datos.
 *
 * @author TuNombre
 */
public class InventarioServer {

    /**
     * Método principal que inicia el servidor SSL.
     * Carga la configuración del certificado SSL desde un archivo de propiedades,
     * inicia el servidor en el puerto especificado y maneja las solicitudes de los clientes.
     *
     * @param args Argumentos de la línea de comandos (no se utilizan en esta aplicación).
     */
    public static void main(String[] args) {
        // Carga la configuración del certificado SSL desde el archivo de propiedades
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

        // Configura las propiedades del sistema para el certificado SSL
        String certificateRoute = p.getProperty("SSL_CERTIFICATE_ROUTE");
        String certificatePassword = p.getProperty("SSL_PASSWORD");
        
        System.setProperty("javax.net.ssl.keyStore", certificateRoute);
        System.setProperty("javax.net.ssl.keyStorePassword", certificatePassword);
        System.setProperty("javax.net.ssl.keyStoreType", "PKCS12");
        System.setProperty("javax.net.ssl.trustStore", certificateRoute);
        System.setProperty("javax.net.ssl.trustStorePassword", certificatePassword);
        System.setProperty("javax.net.ssl.trustStoreType", "PKCS12");

        // Inicia el servidor SSL
        InventarioManager manager = new InventarioManager();
        int puerto = 5051;
        
        try {
            SSLServerSocketFactory socketFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            SSLServerSocket servidor = (SSLServerSocket) socketFactory.createServerSocket(puerto);
            servidor.setEnabledProtocols(new String[]{"TLSv1.2"});
            System.out.println("Servidor SSL iniciado en el puerto " + puerto);
            
            while (true) {
                System.out.println("Esperando conexión segura de cliente...");
                try (
                    SSLSocket socket = (SSLSocket) servidor.accept();
                    DataInputStream in = new DataInputStream(socket.getInputStream());
                    DataOutputStream out = new DataOutputStream(socket.getOutputStream())
                ) {
                    String ipClient = socket.getInetAddress().getHostAddress();
                    
                    Logs log = new Logs();
                    System.out.println("Cliente conectado desde: " + socket.getInetAddress().getHostAddress());
                    int opcion = in.readInt();

                    switch (opcion) {
                        case 1: // Agregar producto
                            int id = in.readInt();
                            String nombre = in.readUTF();
                            double precio = in.readDouble();
                            int cantidad = in.readInt();

                            if (!manager.productExists(id)) {
                                manager.addProduct(new Product(id, nombre, precio, cantidad));
                                out.writeUTF("Producto agregado correctamente.");
                            } else {
                                out.writeUTF("El ID ya existe.");
                            }
                            log.generateLog("ADD", ipClient, "ID Product: " + String.valueOf(id));
                            break;
                        case 2: // Eliminar producto
                            int idEliminar = in.readInt();
                            manager.removeProducts(idEliminar);
                            out.writeUTF("Producto eliminado correctamente.");
                            log.generateLog("DELETE", ipClient, "ID Product: " + String.valueOf(idEliminar));
                            break;
                        case 3: // Actualizar producto
                            int idActualizar = in.readInt();
                            String nuevoNombre = in.readUTF();
                            double nuevoPrecio = in.readDouble();
                            int nuevaCantidad = in.readInt();
                            boolean actualizado = manager.updateProduct(idActualizar, new Product(idActualizar, nuevoNombre, nuevoPrecio, nuevaCantidad));
                            if (actualizado) {
                                out.writeUTF("Producto actualizado correctamente.");
                            } else {
                                out.writeUTF("Producto no encontrado.");
                            }
                            log.generateLog("UPDATE", ipClient, "ID Product: " + String.valueOf(idActualizar));
                            break;
                        case 4: // Buscar producto
                            String nombreBuscar = in.readUTF();
                            String resultado = manager.searchProduct(nombreBuscar);
                            out.writeUTF(resultado);
                            log.generateLog("SEARCH", ipClient, "Name Product: " + nombreBuscar);
                            break;
                        case 5: // Generar reporte
                            String reporte = manager.generateReport();
                            out.writeUTF(reporte);
                            break;
                        case 6: // Obtener lista de productos
                            StringBuilder listaProductos = new StringBuilder();
                            for (Product product : manager.getProducts()) {
                                listaProductos.append(product.getId()).append(" - ")
                                              .append(product.getName()).append(" - ")
                                              .append(product.getPrice()).append(" - ")
                                              .append(product.getQuantity()).append("\n");
                            }
                            out.writeUTF(listaProductos.toString());
                            break;
                    }
                } catch (IOException ex) {
                    Logger.getLogger(InventarioServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(InventarioServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}