package com.mycompany.inventario;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que gestiona el inventario de productos.
 */
public class InventarioManager {
    private List<Product> products;

    /**
     * Constructor que inicializa la lista de productos con algunos valores predeterminados.
     */
    public InventarioManager() {
        products = new ArrayList<>();
        // Productos iniciales
        products.add(new Product(1, "Manzana", 0.5, 100));
        products.add(new Product(2, "Pera", 0.7, 150));
        products.add(new Product(3, "Banana", 0.3, 200));
        products.add(new Product(4, "Mango", 1.2, 50));
    }

    /**
     * Obtiene la lista de productos disponibles.
     * @return Lista de productos.
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Verifica si un producto con el ID dado existe en el inventario.
     * @param id ID del producto a buscar.
     * @return true si el producto existe, false en caso contrario.
     */
    public boolean productExists(int id) {
        return products.stream().anyMatch(product -> product.getId() == id);
    }

    /**
     * Agrega un nuevo producto al inventario si el ID no está duplicado.
     * @param product Producto a agregar.
     * @throws IllegalArgumentException si el ID del producto ya existe.
     */
    public void addProduct(Product product) {
        if (!productExists(product.getId())) {
            products.add(product);
        } else {
            throw new IllegalArgumentException("El ID ya existe.");
        }
    }

    /**
     * Elimina un producto del inventario según su ID.
     * @param id ID del producto a eliminar.
     */
    public void removeProducts(int id) {
        products.removeIf(product -> product.getId() == id);
    }

    /**
     * Actualiza un producto en el inventario con la información de un nuevo producto.
     * @param id ID del producto a actualizar.
     * @param newProduct Nueva información del producto.
     * @return true si el producto fue actualizado, false si el ID no existe.
     */
    public boolean updateProduct(int id, Product newProduct) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.set(i, newProduct);
                return true;
            }
        }
        return false;
    }

    /**
     * Busca un producto por su nombre.
     * @param name Nombre del producto a buscar.
     * @return Información del producto si se encuentra, o un mensaje si no existe.
     */
    public String searchProduct(String name) {
        return products.stream()
                .filter(product -> product.getName().equalsIgnoreCase(name))
                .map(Product::toString)
                .findFirst()
                .orElse("Producto no encontrado.");
    }

    /**
     * Genera un reporte del inventario en formato CSV.
     * @return Cadena con la información del inventario.
     */
    public String generateReport() {
        StringBuilder report = new StringBuilder("ID, Nombre, Precio, Cantidad\n");
        for (Product product : products) {
            report.append(product.getId()).append(", ")
                    .append(product.getName()).append(", ")
                    .append(product.getPrice()).append(", ")
                    .append(product.getQuantity()).append("\n");
        }
        return report.toString();
    }
}
