package org.howard.edu.lsp.assignment3;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles reading products from a CSV file.
 */
public class CSVReader {
    public List<Product> readProducts(String path) {
        List<Product> products = new ArrayList<>();
        File file = new File(path);

        if (!file.exists()) {
            System.err.println("Error: Input file not found: " + path);
            return null;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine(); // skip header
            if (line == null) return products; // empty file

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length < 4) {
                    products.add(null); // malformed
                    continue;
                }

                try {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    BigDecimal price = new BigDecimal(parts[2].trim());
                    String category = parts[3].trim();

                    products.add(new Product(id, name, price, category));
                } catch (NumberFormatException e) {
                    products.add(null);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return products;
    }
}
