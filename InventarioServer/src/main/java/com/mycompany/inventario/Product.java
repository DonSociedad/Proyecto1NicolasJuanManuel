/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventario;

/**
 * Representa el producto en el inventario
 */
public class Product {
    
    /** identificador del producto */
    private int id;
    
    /** nombre del producto */
    private String name;
    
    /** precio del producto */
    private double price;
    
    /** cantidad de producto disponible en stock */
    private int quantity;
    
    /**
     * Constructor de un nuevo producto
     */
    public Product(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    
    /**
     * obtener el ID de un producto
     */
    public int getId() {
        return id;
    }
    
    /**
     * obtener el nombre de un producto
     */
    public String getName() {
        return name;
    }

    
    /**
     * obtener el precio del producto
     */
    public double getPrice() {
        return price;
    }
    
    /**
     * obtener la cantidad de stock del producto
     */
    public int getQuantity() {
        return quantity;
    }
    
    /**
     * actualizar el precio a un producto
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
    /**
     * actualizar la cantidad del producto en stock
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    /**
     * Representar el producto como un String
     */
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}

