/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.inventarioclient;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan Manuel
 */
public class InventarioClient {

    public static void main(String[] args) {
        
        final String host="127.0.0.1";
        int puerto=5051;
        Socket sc;
        DataInputStream in;
        DataOutputStream out;
        
        try {
            sc = new Socket(host, puerto);
            in=new DataInputStream(sc.getInputStream());
            out=new DataOutputStream(sc.getOutputStream());
            
            out.writeUTF("Hola Mundo desde el cliente");
            String message = in.readUTF();
            System.out.println(message);
            sc.close();
        } catch (IOException ex) {
            Logger.getLogger(InventarioClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
