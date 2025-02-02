/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.inventarioserver;


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
         *se crea el socket para el servidor
         */
        
        ServerSocket servidor ;
        /**
         * socket del cliente junto a input y output
         */
        Socket sc=null;
        DataInputStream in;
        DataOutputStream out;
        
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
            System.out.println("Servidor Iniciado en el puerto "+ puerto);
            /**
            * ciclo infinito para mantener al servidor siempre al pendiente de cualquier solicitud
            */
            while (true) {
                /**
                * el servidor espera una conexion con un cliente
                */
                sc = servidor.accept();
                System.out.println("Cliente encontrado");
                
                /**
                *se inicializa in para recibir informacion
                */
                in = new DataInputStream(sc.getInputStream());
                /**
                * se inicializa el out para enviar informacion
                */
                out = new DataOutputStream(sc.getOutputStream());
                
                /**
                * se crea la variable mensaje para esperar y recibir un mensaje del cliente
                */
                String message = in.readUTF();
                System.out.println(message);
                
                /**
                * envia un mensaje al cliente cuando este ya recibio su mensaje
                */
                out.writeUTF("Que tal desde el servidor");
                
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
