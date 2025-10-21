package org.howard.edu.lsp.assignment2;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ETLPipeline {

    // Simple Product model
    static class Product {
        int id;
        String name;
        BigDecimal price;
        String category;
        String priceRange;

        Product(int id, String name, BigDecimal price, String category) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.category = category;
        }
    }

    public static void main(String[] args) {
        String inputPath = "data/products.csv";
        String outputPath = "data/transformed_products.csv";

        List<Product> products = extract(inputPath);
        if (products == null) return;  // missing file

        int skippedRows = countSkippedRows(products);

        List<Product> transformed = transform(products);
        load(transformed, outputPath);

        // Run summary
        System.out.println("\n--- Run Summary ---");
        System.out.println("Rows read: " + products.size());
        System.out.println("Rows transformed: " + transformed.size());
        System.out.println("Rows skipped (malformed): " + skippedRows);
        System.out.println("Output written: " + outputPath);
    }

    /**
     * Extract products from CSV
     */
    public static List<Product> extract(String path) {
        List<Product> products = new ArrayList<>();
        File file = new File(path);

        if (!file.exists()) {
            System.err.println("Error: Input file not found: " + path);
            return null;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine(); // read header
            if (line == null) {
                System.out.println("Warning: Input file is empty. Creating output with header only.");
                return products; // empty list
            }

            // Read data rows
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // skip blank lines
                String[] parts = line.split(",");

                if (parts.length < 4) {
                    products.add(null); // malformed row
                    continue;
                }

                try {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    BigDecimal price = new BigDecimal(parts[2].trim());
                    String category = parts[3].trim();

                    products.add(new Product(id, name, price, category));
                } catch (NumberFormatException e) {
                    products.add(null); // malformed numeric value
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return products;
    }

    /**
     * Count how many rows are skipped due to malformed data
     */
    private static int countSkippedRows(List<Product> products) {
        int skipped = 0;
        for (Product p : products) {
            if (p == null) skipped++;
        }
        return skipped;
    }

    /**
     * Transform products according to business rules
     */
    public static List<Product> transform(List<Product> products) {
        List<Product> result = new ArrayList<>();

        for (Product p : products) {
            if (p == null) continue; // skip malformed row

            // 1) Uppercase name
            p.name = p.name.toUpperCase();

            // 2) Apply 10% discount if Electronics
            if (p.category.equalsIgnoreCase("Electronics")) {
                p.price = p.price.multiply(BigDecimal.valueOf(0.9));
                p.price = p.price.setScale(2, RoundingMode.HALF_UP);

                // 3) Re-categorize if price > 500
                if (p.price.compareTo(BigDecimal.valueOf(500.00)) > 0) {
                    p.category = "Premium Electronics";
                }
            } else {
                // Round other categories to 2 decimals just in case
                p.price = p.price.setScale(2, RoundingMode.HALF_UP);
            }

            // 4) Compute PriceRange based on final price
            p.priceRange = computePriceRange(p.price);

            result.add(p);
        }

        return result;
    }

    /**
     * Compute PriceRange from price
     */
    private static String computePriceRange(BigDecimal price) {
        if (price.compareTo(BigDecimal.valueOf(10.00)) <= 0) {
            return "Low";
        } else if (price.compareTo(BigDecimal.valueOf(100.00)) <= 0) {
            return "Medium";
        } else if (price.compareTo(BigDecimal.valueOf(500.00)) <= 0) {
            return "High";
        } else {
            return "Premium";
        }
    }

    /**
     * Load transformed products into output CSV
     * Automatically creates parent folder if missing
     */
    public static void load(List<Product> products, String path) {
        File outFile = new File(path);

        // Ensure parent directories exist
        File parentDir = outFile.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            if (parentDir.mkdirs()) {
                System.out.println("Created folder: " + parentDir.getPath());
            } else {
                System.err.println("Failed to create folder: " + parentDir.getPath());
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outFile))) {
            // Write header
            bw.write("ProductID,Name,Price,Category,PriceRange");
            bw.newLine();

            // Write data rows
            for (Product p : products) {
                bw.write(p.id + "," + p.name + "," + p.price + "," + p.category + "," + p.priceRange);
                bw.newLine();
            }

            System.out.println("Output successfully written to: " + outFile.getPath());

        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }
}
