package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Represents a product in the ETL pipeline.
 */
public class Product {
    private int id;
    private String name;
    private BigDecimal price;
    private String category;
    private String priceRange;

    public Product(int id, String name, BigDecimal price, String category) {
        this.id = id;
        this.name = name;
        this.price = price.setScale(2, RoundingMode.HALF_UP);
        this.category = category;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getPrice() { return price; }
    public String getCategory() { return category; }
    public String getPriceRange() { return priceRange; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setPrice(BigDecimal price) { this.price = price.setScale(2, RoundingMode.HALF_UP); }
    public void setCategory(String category) { this.category = category; }
    public void setPriceRange(String priceRange) { this.priceRange = priceRange; }
}
