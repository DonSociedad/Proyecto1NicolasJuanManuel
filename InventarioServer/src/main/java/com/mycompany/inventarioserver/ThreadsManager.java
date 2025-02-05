package com.mycompany.inventarioserver;

import com.mycompany.inventario.InventarioManager;
import com.mycompany.inventario.Product;
import com.mycompany.inventario.logs.Logs;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;

/**
 * Clase que maneja las operaciones del inventario en un hilo separado para cada
 * cliente.
 */
public class ThreadsManager extends Thread {

    private SSLSocket socket; // Socket SSL para la conexión con el cliente.
    private InventarioManager Manager; // Instancia para gestionar el inventario.
    private Logs log; // Instancia para generar logs.

    /**
     * Constructor que inicializa el hilo con un socket y un gestor de
     * inventario.
     *
     * @param socket Socket SSL para la conexión con el cliente.
     * @param Manager Gestor de inventario.
     */
    public ThreadsManager(SSLSocket socket, InventarioManager Manager) {
        this.socket = socket;
        this.Manager = Manager;
        this.log = new Logs();
    }

    /**
     * Método principal del hilo. Lee la operación solicitada por el cliente y
     * la ejecuta.
     */
    @Override
    public void run() {
        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            String ipClient = socket.getInetAddress().getHostAddress();

            int opcion = in.readInt();
            operarInventario(opcion, in, out, Manager, ipClient);

        } catch (IOException ex) {
            Logger.getLogger(ThreadsManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(ThreadsManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Ejecuta la operación solicitada por el cliente.
     *
     * @param opcion Operación a realizar.
     * @param in Flujo de entrada para leer datos del cliente.
     * @param out Flujo de salida para enviar datos al cliente.
     * @param manager Gestor de inventario.
     * @param ipClient Dirección IP del cliente.
     */
    public void operarInventario(int opcion, DataInputStream in, DataOutputStream out, InventarioManager manager, String ipClient) {
        try {
            switch (opcion) {
                case 1 -> {
                    // Agregar producto
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
                }
                case 2 -> {
                    // Eliminar producto
                    int idEliminar = in.readInt();
                    manager.removeProducts(idEliminar);
                    out.writeUTF("Producto eliminado correctamente.");
                    log.generateLog("DELETE", ipClient, "ID Product: " + String.valueOf(idEliminar));
                }
                case 3 -> {
                    // Actualizar producto
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
                }
                case 4 -> {
                    // Buscar producto
                    String nombreBuscar = in.readUTF();
                    String resultado = manager.searchProduct(nombreBuscar);
                    out.writeUTF(resultado);
                    log.generateLog("SEARCH", ipClient, "Name Product: " + nombreBuscar);
                }
                case 5 -> {
                    // Generar reporte
                    String reporte = manager.generateReport();
                    out.writeUTF(reporte);
                }
                case 6 -> {
                    // Obtener lista de productos
                    StringBuilder listaProductos = new StringBuilder();
                    for (Product product : manager.getProducts()) {
                        listaProductos.append(product.getId()).append(" - ")
                                .append(product.getName()).append(" - ")
                                .append(product.getPrice()).append(" - ")
                                .append(product.getQuantity()).append("\n");
                    }
                    out.writeUTF(listaProductos.toString());
                }
            }
        } catch (IOException ex) {
            System.out.println("error en threadManager");
        }
    }
}
