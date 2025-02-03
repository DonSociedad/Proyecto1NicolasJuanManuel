/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventario;

import java.util.ArrayList;

/**
 *
 * @author Juan Manuel
 */
public class InventarioManager {
    ArrayList<Product> products=new ArrayList<>();
    
     public InventarioManager() {
        this.products = new ArrayList<>();
        initializeInventory();
    }
    
    /**
     * Initializes the inventory with predefined products.
     */
    public void initializeInventory() {
        products.add(new Product(1, "Laptop", 1200.00, 10));
        products.add(new Product(2, "Smartphone", 800.00, 15));
        products.add(new Product(3, "Headphones", 200.00, 20));
        products.add(new Product(4, "Keyboard", 100.00, 25));
    }
    
    public void removeProducts(int id){
        products.removeIf(product -> product.getId() == id);
    }
    
    public void updateProduct(int id, Product newProduct) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.set(i, newProduct);
                return;
            }
        }
    }
    
    public String searchProduct(String name) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product.toString();
            }
        }
        return "not found";
    }
    
    /**
     * Muestra los productos en el inventario
     */
    public void displayProducts() {
        System.out.println("Current Inventory:");
        for (Product product : products) {
            System.out.println(product);
        }
    }
    
    public String generateReport() {
        StringBuilder report = new StringBuilder("Inventory Report:\n");
        for (Product product : products) {
            report.append(product.toString()).append("\n");
        }
        return report.toString();
    }

    
    
}
