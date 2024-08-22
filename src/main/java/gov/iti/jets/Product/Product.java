package gov.iti.jets.Product;
import gov.iti.jets.Category.CategoryDAO;

import java.util.Objects;

public class Product {
    private int id;
    private String name;
    private double price; // Added attribute for product price
    private int categoryId;
    private int quantity; // Added attribute for product quantity
    private String description; // Added attribute for product description

    // Default constructor
    public Product() {}

    // Parameterized constructor
    public Product(int id, String name, double price, int categoryId, int quantity, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
        this.quantity = quantity;
        this.description = description;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Method to get category
    public Category getCategory() {
        return CategoryDAO.getCategoryById(this.categoryId);
    }

    // Method to check if product is in stock
    public boolean isInStock() {
        return this.quantity > 0;
    }

    // Method to update quantity
    public void updateQuantity(int newQuantity) {
        this.quantity = newQuantity;
    }

    // Override toString() method for better readability
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", categoryId=" + categoryId +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                '}';
    }

    // Override equals() and hashCode() for proper comparison and hashing
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                Double.compare(product.price, price) == 0 &&
                categoryId == product.categoryId &&
                quantity == product.quantity &&
                Objects.equals(name, product.name) &&
                Objects.equals(description, product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, categoryId, quantity, description);
    }
}
