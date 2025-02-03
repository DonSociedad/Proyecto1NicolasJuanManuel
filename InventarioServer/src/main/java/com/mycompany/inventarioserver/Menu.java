/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventarioserver;

import com.mycompany.inventario.InventarioManager;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Juan Manuel
 */
public class Menu {

    Scanner sc = new Scanner(System.in);
    InventarioManager manager = new InventarioManager();
    String menu = """
                  Que deseas hacer: 
                   1) ver los productos disponibles 
                   2) eliminar un producto 
                   3) buscar un producto por el nombre 
                   4)generar un reporte
                   5) salir""";
    int option;

    public String showMenu() {
        return menu;
    }

    

}
