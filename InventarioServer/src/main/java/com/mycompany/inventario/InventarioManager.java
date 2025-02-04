package com.mycompany.inventario;

import java.util.ArrayList;
import java.util.List;

public class InventarioManager {
    private List<Product> products;

    public InventarioManager() {
        products = new ArrayList<>();
        // Productos iniciales
        products.add(new Product(1, "Manzana", 0.5, 100));
        products.add(new Product(2, "Pera", 0.7, 150));
        products.add(new Product(3, "Banana", 0.3, 200));
        products.add(new Product(4, "Mango", 1.2, 50));
    }

    public List<Product> getProducts() {
        return products;
    }

    public boolean productExists(int id) {
        return products.stream().anyMatch(product -> product.getId() == id);
    }

    public void addProduct(Product product) {
        if (!productExists(product.getId())) {
            products.add(product);
        } else {
            throw new IllegalArgumentException("El ID ya existe.");
        }
    }

    public void removeProducts(int id) {
        products.removeIf(product -> product.getId() == id);
    }

    public boolean updateProduct(int id, Product newProduct) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.set(i, newProduct);
                return true;
            }
        }
        return false;
    }

    public String searchProduct(String name) {
        return products.stream()
                .filter(product -> product.getName().equalsIgnoreCase(name))
                .map(Product::toString)
                .findFirst()
                .orElse("Producto no encontrado.");
    }

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