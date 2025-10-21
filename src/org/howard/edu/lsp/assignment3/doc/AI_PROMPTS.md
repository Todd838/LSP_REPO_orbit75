AI Prompts and Responses (Excerpts)

Prompt 1:
"How can I refactor a Java ETL pipeline into a more object-oriented design?"
Response Excerpt:

Suggested splitting responsibilities into separate classes (Product, CSVReader, ProductTransformer, CSVWriter)

Recommended encapsulating transformation logic within the Product class

Prompt 2:
"How can I apply encapsulation and single responsibility principle in my ETL Java program?"
Response Excerpt:

Make fields private and provide public methods for transformations

Each class should have one responsibility: reading, transforming, writing, or representing data

Prompt 3:
"Can you show an example of transforming product data in an object-oriented way in Java?"
Response Excerpt:

Provided an example where Product has methods uppercaseName(), applyElectronicsDiscount(), and computePriceRange()

Reflection on AI Usage:
I used the AI to suggest ways to split my monolithic ETL pipeline into classes, provide examples of encapsulation, and show how to apply transformations inside the Product object. I reviewed all suggestions, adjusted them to fit my project, and ensured that the final code still met all requirements from Assignment 2.