/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.inventarioserver;

import com.mycompany.inventario.InventarioManager;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan Manuel
 */
public class InventarioServer {

    public static void main(String[] args) {
        /**
        * creacion de objeto InventarioManager
        */
        InventarioManager manager = new InventarioManager();
        /**
        * variable para definir la operacion a realizar
        */
        int option = 0;

        /**
         * se crea el socket para el servidor
         */
        ServerSocket servidor;
        /**
         * socket del cliente junto a input y output
         */
        Socket sc = null;
        DataInputStream in;
        DataOutputStream out;
        /**
         * menu temporal para probar las funcionalidades del manager
         */
        Menu menu = new Menu();
        /**
         * puerto en el que se va a trabajar
         */
        int puerto = 5051;
        /**
         * try-catch para las excepciones de socket
         */
        try {
            /**
             * se inicializa el servidor
             */
            servidor = new ServerSocket(puerto);
            System.out.println("Servidor Iniciado en el puerto " + puerto);
            /**
             * ciclo infinito para mantener al servidor siempre al pendiente de
             * cualquier solicitud
             */
            while (true) {
                /**
                 * el servidor espera una conexion con un cliente
                 */
                sc = servidor.accept();
                System.out.println("Cliente encontrado");
                /**
                 * se inicializa in para recibir informacion
                 */
                in = new DataInputStream(sc.getInputStream());
                /**
                 * se inicializa el out para enviar informacion
                 */
                out = new DataOutputStream(sc.getOutputStream());
                /**
                * ciclo que mantendra el menu activo hasta que el cliente lo desee
                */
                while (option != 5) {
                    /**
                    * se muestran las opciones del menu
                    */
                    out.writeUTF(menu.showMenu());
                    /**
                    * recibe la opcion elegida por el cliente
                    */
                    option = in.readInt();
                    System.out.println("se recibio un "+option);

                    switch (option) {
                        case 1:
                            /**
                            * si se realiza la operacion 1 se llama a la funcion para mostrar el inventario
                            */
                            manager.displayProducts();
                            /**
                            * mensaje de confirmacion de la opcion 1
                            */
                            out.writeUTF("se realizo la operacion 1");
                            break;
                        case 2:
                            /**
                            * se solicita el Id del producto a eliminar
                            */
                            out.writeUTF("Ingresa el ID del producto que deseas eliminar");
                            /**
                            * el Id es recibido y se usa como parametro de la funcion
                            */
                            int id = in.readInt();
                            manager.removeProducts(id);
                            
                            /**
                            * mensaje de confirmacion de producto eliminado y operacion realizada
                            */
                            out.writeUTF("se elimino el producto con el id "+id);
                            out.writeUTF("se realizo la operacion 2");
                            break;
                        case 3:
                            /**
                            * mensaje solicitando el nombre del producto a buscar
                            */
                            out.writeUTF("Ingresa el nombre del producto que deseas buscar");
                            /**
                            * recibe el nombre del producto
                            */
                            String name = in.readUTF();
                            /**
                            * se crea la variable r para guardar el producto de la funcion
                            */
                            String r = manager.searchProduct(name);
                            /**
                            * se envia el producto al cliente
                            */
                            out.writeUTF(r);
                            break;
                        case 4:
                            
                    }
                }

                /**
                 * se cierra la conexion con el cliente
                 */
                sc.close();
                System.out.println("cliente desconectado...");
            }

        } catch (IOException ex) {
            Logger.getLogger(InventarioServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
