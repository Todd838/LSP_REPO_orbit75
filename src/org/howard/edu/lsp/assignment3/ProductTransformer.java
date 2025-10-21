package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Transforms products according to business rules.
 */
public class ProductTransformer {
    public List<Product> transform(List<Product> products) {
        List<Product> result = new ArrayList<>();

        for (Product p : products) {
            if (p == null) continue;

            // Uppercase name
            p.setName(p.getName().toUpperCase());

            // Apply 10% discount if Electronics
            if (p.getCategory().equalsIgnoreCase("Electronics")) {
                p.setPrice(p.getPrice().multiply(BigDecimal.valueOf(0.9)));

                // Re-categorize if price > 500
                if (p.getPrice().compareTo(BigDecimal.valueOf(500.00)) > 0) {
                    p.setCategory("Premium Electronics");
                }
            }

            // Compute price range
            p.setPriceRange(computePriceRange(p.getPrice()));

            result.add(p);
        }

        return result;
    }

    private String computePriceRange(BigDecimal price) {
        if (price.compareTo(BigDecimal.valueOf(10.00)) <= 0) return "Low";
        else if (price.compareTo(BigDecimal.valueOf(100.00)) <= 0) return "Medium";
        else if (price.compareTo(BigDecimal.valueOf(500.00)) <= 0) return "High";
        else return "Premium";
    }
}
