Reflection: Comparing Assignment 2 and Assignment 3 Implementations

Design Differences
In Assignment 2, the ETL pipeline was implemented in a single class (ETLPipeline). All steps—extracting, transforming, and loading products—were contained in static methods, and product data was represented by a simple inner Product class. This design worked functionally but was not very modular or object-oriented.

In Assignment 3, I refactored the code into multiple classes with clear responsibilities:

Product encapsulates all product attributes and behavior, such as applying discounts and computing price ranges.

CSVReader handles extraction from the CSV file.

ProductTransformer handles transformation logic.

CSVWriter handles loading data back into a CSV file.

ETLPipeline orchestrates the ETL process without managing individual details.

This design separates concerns, makes the code more modular, and improves readability and maintainability.

Object-Oriented Improvements
Assignment 3 is more object-oriented in several ways:

Encapsulation: Product fields are private, and all transformations are handled through public methods.

Objects and Classes: Each concept (Product, CSVReader, Transformer, Writer) is represented as a separate class.

Single Responsibility Principle: Each class has a clear, focused role.

Reusability: Methods and classes can be reused in other ETL pipelines without modifying ETLPipeline.

Testing
To ensure Assignment 3 behaves identically to Assignment 2, I performed the following tests:

Normal Input: Used the sample products.csv with mixed categories and prices; confirmed that transformed_products.csv matches the output from Assignment 2.

Empty Input: Tested with a CSV containing only a header row; verified that the output still produces a header.

Missing Input File: Renamed the input file to test error handling; confirmed that the program prints a clear message and exits gracefully.

Manual Verification: Checked that product names are uppercased, Electronics discounts applied correctly, categories updated properly, prices rounded to two decimals, and PriceRange computed as expected.

Overall, Assignment 3 achieves the same functionality as Assignment 2 but with a cleaner, more object-oriented design that is easier to maintain and extend.