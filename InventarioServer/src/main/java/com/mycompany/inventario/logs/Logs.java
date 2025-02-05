/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventario.logs;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 *
 * @author nmedi
 */
public class Logs {
    /**
     * Registra cada operación hecha en el inventario
     */
    public static void generateLog(String operacion, String ipCliente, String ipProduct) {
        /**
         * 
         */
        String registro = String.format("[%s] %s - %s - %s",LocalDateTime.now(), "Operación: "+operacion, "IP Employee:"+ipCliente, ipProduct);

        try (FileWriter fw = new FileWriter("logRegister.csv", true)) {
            fw.write(registro + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
