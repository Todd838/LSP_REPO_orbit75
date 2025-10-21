package org.howard.edu.lsp.assignment3;

import java.io.*;
import java.util.List;

/**
 * Writes transformed products to a CSV file.
 */
public class CSVWriter {
    public void writeProducts(List<Product> products, String path) {
        File outFile = new File(path);

        // Ensure parent directories exist
        File parentDir = outFile.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outFile))) {
            // Header
            bw.write("ProductID,Name,Price,Category,PriceRange");
            bw.newLine();

            for (Product p : products) {
                bw.write(p.getId() + "," + p.getName() + "," + p.getPrice() + "," + p.getCategory() + "," + p.getPriceRange());
                bw.newLine();
            }

            System.out.println("Output written to: " + outFile.getPath());
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }
}
