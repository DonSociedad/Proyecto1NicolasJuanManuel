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
        /**
         * Scanner temporal para incertar los datos en el servidor
         */
        Scanner scanner = new Scanner(System.in);
        /**
         * variable para determinar que opcion se va a hacer 
         */
        int option = 0;
        /**
         * creacion de variable para almacenar la direccion local
         */
        final String host = "127.0.0.1";
        /**
         * puerto que se va a usar
         */
        int puerto = 5051;
        /**
         * creacion del socket junto a su input y output
         */
        Socket sc;
        DataInputStream in;
        DataOutputStream out;

        /**
         * try-catch para manejar las excepciones de socket in & out
         */
        try {
            /**
             * inicializa el socket del cliente con la IP del servidor y el
             * puerto que planea utilizar
             */
            sc = new Socket(host, puerto);

            /**
             * se inicializa el in para recibir informacion
             */
            in = new DataInputStream(sc.getInputStream());
            /**
             * se inicializa el out para enviar informacion
             */
            out = new DataOutputStream(sc.getOutputStream());

            /**
            *  ciclo para mantener el menu funcionando constantemente
            */
            while (option != 5) {
                /**
                * recibe e imprime el menu
                */
                String message = in.readUTF();
                System.out.println(message);
                System.out.println("Ingresa la opcion que deseas");
                /**
                * pone la opcion que desea realizar
                */
                option = scanner.nextInt();
                /**
                * envia la opcion deseada al servidor
                */
                out.writeInt(option);

                /**
                * opera en base a la opcion deseada
                */
                switch (option) {
                    case 1:
                        /**
                        * si la opcion es 1 muestra un mensaje confirmandola
                        */
                        System.out.println(in.readUTF());
                        break;
                    case 2:
                        /**
                        * si la opcion es 2 muestra un mensaje solicitando el id del producto a eliminar
                        */
                        System.out.println(in.readUTF());
                        /**
                        * se escribe y se envia el numero
                        */
                        int num = scanner.nextInt();
                        out.writeInt(num);
                        /**
                        * se confirma que se elimino la id y que se realizo la 2da operacion
                        */
                        System.out.println(in.readUTF());
                        System.out.println(in.readUTF());
                        break;
                    case 3:
                        /**
                        * si la opcion es 3 muestra un mensaje solicitando el nombre del producto
                        */
                        System.out.println(in.readUTF());
                        /**
                        * se escribe y se envia el nombre del producto
                        */
                        String prName = scanner.nextLine();
                        out.writeUTF(prName);
                        /**
                        * mensaje confirmando que se realizo la operacion 3
                        */
                        System.out.println(in.readUTF());
                        break;

                }
            }

            /**
             * Finaliza la conexion
             */
            sc.close();
        } catch (IOException ex) {
            Logger.getLogger(InventarioClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
