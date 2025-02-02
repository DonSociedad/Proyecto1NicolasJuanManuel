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
        ServerSocket servidor ;
        Socket sc=null;
        DataInputStream in;
        DataOutputStream out;
        
        int puerto = 5051;
        
        try {
            servidor = new ServerSocket(puerto);
            System.out.println("Servidor Iniciado en el puerto "+ puerto);
            while (true) {
                sc = servidor.accept();
                System.out.println("Cliente encontrado");
                
                in = new DataInputStream(sc.getInputStream());
                out = new DataOutputStream(sc.getOutputStream());
                String message = in.readUTF();
                System.out.println(message);
                out.writeUTF("Que tal desde el servidor");
                sc.close();
                System.out.println("cliente desconectado...");
            }

        } catch (IOException ex) {
            Logger.getLogger(InventarioServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
