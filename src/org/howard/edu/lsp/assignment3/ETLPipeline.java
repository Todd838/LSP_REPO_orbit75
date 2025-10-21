package org.howard.edu.lsp.assignment3;

import java.util.List;

public class ETLPipeline {
    public static void main(String[] args) {
        String inputPath = "data/products.csv";
        String outputPath = "data/transformed_products.csv";

        CSVReader reader = new CSVReader();
        List<Product> products = reader.readProducts(inputPath);

        if (products == null) return;

        int skippedRows = 0;
        for (Product p : products) if (p == null) skippedRows++;

        ProductTransformer transformer = new ProductTransformer();
        List<Product> transformed = transformer.transform(products);

        CSVWriter writer = new CSVWriter();
        writer.writeProducts(transformed, outputPath);

        System.out.println("\n--- Run Summary ---");
        System.out.println("Rows read: " + products.size());
        System.out.println("Rows transformed: " + transformed.size());
        System.out.println("Rows skipped (malformed): " + skippedRows);
    }
}
