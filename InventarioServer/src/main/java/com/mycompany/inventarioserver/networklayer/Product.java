/**
 * Represents a product in the inventory.
 */
public class Product {
    
    /** The unique identifier for the product. */
    private int id;
    
    /** The name of the product. */
    private String name;
    
    /** A brief description of the product. */
    private String description;
    
    /** The price of the product. */
    private double price;
    
    /** The available quantity of the product in stock. */
    private int quantity;
    
    /**
     * Constructs a new Product.
     * @param id The unique identifier of the product.
     * @param name The name of the product.
     * @param description A brief description of the product.
     * @param price The price of the product.
     * @param quantity The available quantity in stock.
     */
    public Product(int id, String name, String description, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }
    
    /**
     * Gets the product ID.
     * @return The unique identifier of the product.
     */
    public int getId() {
        return id;
    }
    
    /**
     * Gets the product name.
     * @return The name of the product.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gets the product description.
     * @return The description of the product.
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Gets the product price.
     * @return The price of the product.
     */
    public double getPrice() {
        return price;
    }
    
    /**
     * Gets the available quantity of the product in stock.
     * @return The available quantity.
     */
    public int getQuantity() {
        return quantity;
    }
    
    /**
     * Sets a new price for the product.
     * @param price The new price to be set.
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
    /**
     * Updates the quantity of the product in stock.
     * @param quantity The new quantity to be set.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    /**
     * Returns a string representation of the product.
     * @return A formatted string containing product details.
     */
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}

