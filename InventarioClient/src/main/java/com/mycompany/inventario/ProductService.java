package com.mycompany.inventario;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * La clase {@code ProductService} proporciona métodos para interactuar con un servidor
 * que gestiona un inventario de productos. Utiliza conexiones seguras (SSL/TLS) para
 * enviar y recibir datos.
 *
 * <p>Esta clase permite realizar operaciones como agregar, eliminar, actualizar y buscar
 * productos, así como generar reportes y obtener la lista completa de productos.</p>
 *
 * @author Nicolas y Juan Manuel
 * @version 1.0
 * @since 2023-10-01
 */
public class ProductService {
    private static final String SERVER_HOST = "127.0.0.1"; // Dirección del servidor
    private static final int SERVER_PORT = 5051; // Puerto del servidor

    /**
     * Envía una solicitud al servidor para realizar una operación específica.
     *
     * @param opcion    La operación a realizar (1: Agregar, 2: Eliminar, 3: Actualizar, 4: Buscar, 5: Generar reporte, 6: Obtener lista).
     * @param id        El ID del producto (requerido para agregar, eliminar y actualizar).
     * @param nombre    El nombre del producto (requerido para agregar, actualizar y buscar).
     * @param precio    El precio del producto (requerido para agregar y actualizar).
     * @param cantidad  La cantidad del producto (requerido para agregar y actualizar).
     * @return La respuesta del servidor como una cadena.
     * @throws IOException              Si ocurre un error de comunicación con el servidor.
     * @throws NumberFormatException    Si los campos numéricos no son válidos.
     * @throws IllegalArgumentException Si los campos obligatorios están vacíos o la opción no es válida.
     */
    public String enviarSolicitud(int opcion, String id, String nombre, String precio, String cantidad)
            throws IOException, NumberFormatException, IllegalArgumentException {
        try (SSLSocket socket = crearSocket();
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

            out.writeInt(opcion);

            switch (opcion) {
                case 1: // Agregar
                case 3: // Actualizar
                    if (id.isEmpty() || nombre.isEmpty() || precio.isEmpty() || cantidad.isEmpty()) {
                        throw new IllegalArgumentException("Todos los campos son obligatorios.");
                    }
                    out.writeInt(validarEntero(id, "ID"));
                    out.writeUTF(nombre);
                    out.writeDouble(validarDouble(precio, "Precio"));
                    out.writeInt(validarEntero(cantidad, "Cantidad"));
                    break;
                case 2: // Eliminar
                    if (id.isEmpty()) {
                        throw new IllegalArgumentException("El campo ID es obligatorio.");
                    }
                    out.writeInt(validarEntero(id, "ID"));
                    break;
                case 4: // Buscar
                    if (nombre.isEmpty()) {
                        throw new IllegalArgumentException("El campo Nombre es obligatorio.");
                    }
                    out.writeUTF(nombre);
                    break;
                case 5: // Generar reporte
                case 6: // Obtener lista de productos
                    // No se necesitan parámetros adicionales
                    break;
                default:
                    throw new IllegalArgumentException("Opción no válida: " + opcion);
            }

            // Leer respuesta del servidor
            return in.readUTF();
        }
    }

    /**
     * Obtiene la lista de productos desde el servidor.
     *
     * @return Una lista de cadenas que representan los productos.
     * @throws IOException Si ocurre un error de comunicación con el servidor.
     */
    public List<String> obtenerProductos() throws IOException {
        List<String> productos = new ArrayList<>();

        try (SSLSocket socket = crearSocket();
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.writeInt(6); // Opción 6: Obtener lista de productos
            out.flush();

            String linea;
            while ((linea = in.readLine()) != null) {
                productos.add(linea);
            }
        }
        return productos;
    }

    /**
     * Genera un reporte en formato CSV con la información de los productos.
     *
     * @throws IOException Si ocurre un error de comunicación con el servidor o al escribir el archivo.
     */
    public void generarReporte() throws IOException {
        try (SSLSocket socket = crearSocket();
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.writeInt(5); // Opción 5: Generar reporte
            out.flush();

            try (FileWriter fileWriter = new FileWriter("reporte.csv");
                 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

                String linea;
                while ((linea = in.readLine()) != null) {
                    bufferedWriter.write(linea);
                    bufferedWriter.newLine();
                }
            }
        }
    }

    /**
     * Crea un socket SSL seguro para comunicarse con el servidor.
     *
     * @return Un socket SSL configurado.
     * @throws IOException Si ocurre un error al crear el socket.
     */
    private SSLSocket crearSocket() throws IOException {
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, null, new SecureRandom());
            SSLSocketFactory socketFactory = sslContext.getSocketFactory();
            return (SSLSocket) socketFactory.createSocket(SERVER_HOST, SERVER_PORT);
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new IOException("Error al crear el socket SSL: " + e.getMessage(), e);
        }
    }

    /**
     * Valida que una cadena sea un número entero válido.
     *
     * @param texto  La cadena a validar.
     * @param campo  El nombre del campo (para mensajes de error).
     * @return El valor entero de la cadena.
     * @throws NumberFormatException Si la cadena no es un número entero válido.
     */
    private int validarEntero(String texto, String campo) throws NumberFormatException {
        try {
            return Integer.parseInt(texto);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("El campo " + campo + " debe ser un número entero válido.");
        }
    }

    /**
     * Valida que una cadena sea un número decimal válido.
     *
     * @param texto  La cadena a validar.
     * @param campo  El nombre del campo (para mensajes de error).
     * @return El valor decimal de la cadena.
     * @throws NumberFormatException Si la cadena no es un número decimal válido.
     */
    private double validarDouble(String texto, String campo) throws NumberFormatException {
        try {
            return Double.parseDouble(texto);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("El campo " + campo + " debe ser un número decimal válido.");
        }
    }
}