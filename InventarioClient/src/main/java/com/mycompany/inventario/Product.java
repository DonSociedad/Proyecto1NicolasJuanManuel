package com.mycompany.inventario;

/**
 * La clase {@code Product} representa un producto en un sistema de inventario.
 * Contiene información básica como el ID, nombre, precio y cantidad del producto.
 * Esta clase proporciona métodos para acceder y manipular estos atributos.
 *
    * @author Nicolas y Juan Manuel
 * @version 1.0
 * @since 2023-10-01
 */
public class Product {
    private int id;          // Identificador único del producto
    private String name;     // Nombre del producto
    private double price;    // Precio del producto
    private int quantity;    // Cantidad disponible del producto

    /**
     * Constructor para crear una nueva instancia de {@code Product}.
     *
     * @param id        El identificador único del producto.
     * @param name      El nombre del producto.
     * @param price     El precio del producto.
     * @param quantity  La cantidad disponible del producto.
     * @throws IllegalArgumentException Si el ID, precio o cantidad son valores negativos.
     */
    public Product(int id, String name, double price, int quantity) {
        if (id < 0 || price < 0 || quantity < 0) {
            throw new IllegalArgumentException("ID, precio y cantidad no pueden ser negativos.");
        }
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Obtiene el ID del producto.
     *
     * @return El identificador único del producto.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene el nombre del producto.
     *
     * @return El nombre del producto.
     */
    public String getName() {
        return name;
    }

    /**
     * Obtiene el precio del producto.
     *
     * @return El precio del producto.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Obtiene la cantidad disponible del producto.
     *
     * @return La cantidad disponible del producto.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Devuelve una representación en formato de cadena del producto.
     * El formato es: "ID - Nombre - Precio - Cantidad".
     *
     * @return Una cadena que representa el producto.
     */
    @Override
    public String toString() {
        return id + " - " + name + " - " + price + " - " + quantity;
    }
}