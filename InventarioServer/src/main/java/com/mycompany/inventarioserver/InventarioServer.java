package com.mycompany.inventarioserver;

import com.mycompany.inventario.InventarioManager;
import com.mycompany.inventario.Product;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InventarioServer {

    public static void main(String[] args) {
        InventarioManager manager = new InventarioManager();
        int puerto = 5051;

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor TCP iniciado en el puerto " + puerto);

            while (true) {
                System.out.println("Esperando conexión de cliente...");
                try (Socket socket = servidor.accept();
                     DataInputStream in = new DataInputStream(socket.getInputStream());
                     DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

                    System.out.println("Cliente conectado desde: " + socket.getInetAddress().getHostAddress());

                    // Recibe la opción del cliente
                    int opcion = in.readInt();

                    // Procesa la solicitud del cliente
                    switch (opcion) {
                        case 1: // Agregar producto
                            int id = in.readInt();
                            String nombre = in.readUTF();
                            double precio = in.readDouble();
                            int cantidad = in.readInt();

                            if (!manager.productExists(id)) {
                                manager.getProducts().add(new Product(id, nombre, precio, cantidad));
                                out.writeUTF("Producto agregado correctamente.");
                            } else {
                                out.writeUTF("El ID ya existe.");
                            }
                            break;
                        case 2: // Eliminar producto
                            int idEliminar = in.readInt();
                            manager.removeProducts(idEliminar);
                            out.writeUTF("Producto eliminado correctamente.");
                            break;
                        case 3: // Actualizar producto
                            int idActualizar = in.readInt();
                            String nuevoNombre = in.readUTF();
                            double nuevoPrecio = in.readDouble();
                            int nuevaCantidad = in.readInt();
                            manager.updateProduct(idActualizar, new Product(idActualizar, nuevoNombre, nuevoPrecio, nuevaCantidad));
                            out.writeUTF("Producto actualizado correctamente.");
                            break;
                        case 4: // Buscar producto
                            String nombreBuscar = in.readUTF();
                            String resultado = manager.searchProduct(nombreBuscar);
                            out.writeUTF(resultado);
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
        } catch (IOException ex) {
            Logger.getLogger(InventarioServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
