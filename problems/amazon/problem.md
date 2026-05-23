## Requirements
1. The online shopping service should allow users to browse products, add them to the shopping cart, and place orders.
2. The system should support multiple product categories and provide search functionality.
3. Users should be able to manage their profiles, view order history, and track order status.
4. The system should handle inventory management and update product availability accordingly.
5. The system should support multiple payment methods and ensure secure transactions.
6. The system should handle concurrent user requests and ensure data consistency.
7. The system should be scalable to handle a large number of products and users.
8. The system should provide a user-friendly interface for a seamless shopping experience.

## Solution self
Features,
1. browseProducts(query?, category?): List<Products>
2. addToCart(product)
3. invenetory management
   1. crud
4. profile management
   1. crud
5. order
   1. crud

Model,
```
Product(id, name, quantity, price, category)
User(id, name)
Order(id, user, product, createdDate, status)
```

