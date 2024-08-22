package gov.iti.jets;

import java.util.List;

public class Category {
    private int id;
    private String name;
    private String description;
    private Integer parentCategoryId; // Optional for hierarchical categories

    // Default constructor
    public Category() {}

    // Parameterized constructor
    public Category(int id, String name, String description, Integer parentCategoryId) {
        this.id = id;
        this.name = name;
        this.description = description;
        //this.parentCategoryId = parentCategoryId;
    }

    // Getters and Setters
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Integer parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    // Method to get products in this category
    public List<Product> getProducts() {
        return ProductDAO.getProductsByCategoryId(this.id);
    }

    // Override toString() method for better readability
    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", parentCategoryId=" + parentCategoryId +
                '}';
    }

    // Override equals() and hashCode() for proper comparison and hashing
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id == category.id &&
                Objects.equals(name, category.name) &&
                Objects.equals(description, category.description) &&
                Objects.equals(parentCategoryId, category.parentCategoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, parentCategoryId);
    }
}
