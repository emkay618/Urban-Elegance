package com.moshao.dao;

import com.moshao.model.Cart;
import com.moshao.model.Product;
import java.sql.*;
import java.util.*;

public class ProductDao {

    private Connection con;

    private String query;
    private PreparedStatement pst;
    private ResultSet rs;

    // Constructor with database connection parameter
    public ProductDao(Connection con) {
        this.con = con;
    }

    // Method to add products
    public boolean addProduct(Product product) {
        try {
            // Prepare and execute the insert query
            String query = "INSERT INTO products (name, description, category, price, quantity, imageUrl, size, color) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, product.getName());
            pst.setString(2, product.getDescription());
            pst.setString(3, product.getCategory());
            pst.setDouble(4, product.getPrice());
            pst.setInt(5, product.getQuantity());
            pst.setString(6, product.getImageUrl());
            pst.setString(7, product.getSize());
            pst.setString(8, product.getColor());

            int rowsAffected = pst.executeUpdate();
            pst.close(); // Close PreparedStatement

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Retrieve all products
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try {
            // Prepare and execute the query to retrieve all products
            String query = "SELECT * FROM products";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                // Create Product objects for each retrieved row and add them to the list
                Product product = new Product();
                product.setProductId(rs.getInt("productID"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setCategory(rs.getString("category"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setImageUrl(rs.getString("imageUrl"));
                product.setSize(rs.getString("size"));
                product.setColor(rs.getString("color"));

                products.add(product);
            }
            rs.close(); // Close ResultSet
            pst.close(); // Close PreparedStatement

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    // Retrieve shirts from the database
    public List<Product> getShirts() {
        List<Product> products = new ArrayList<>();
        try {
            String query = "SELECT * FROM products WHERE category = 'Shirts'";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("productID"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setCategory(rs.getString("category"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setImageUrl(rs.getString("imageUrl"));
                product.setSize(rs.getString("size"));
                product.setColor(rs.getString("color"));

                products.add(product);
            }
            rs.close(); // Close ResultSet
            pst.close(); // Close PreparedStatement

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    // Retrieve pants from the database
    public List<Product> getPants() {
        List<Product> products = new ArrayList<>();
        try {
            String query = "SELECT * FROM products WHERE category = 'Pants'";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("productID"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setCategory(rs.getString("category"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setImageUrl(rs.getString("imageUrl"));
                product.setSize(rs.getString("size"));
                product.setColor(rs.getString("color"));

                products.add(product);
            }
            rs.close(); // Close ResultSet
            pst.close(); // Close PreparedStatement

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    // Retrieve suits from the database
    public List<Product> getSuits() {
        List<Product> products = new ArrayList<>();
        try {
            String query = "SELECT * FROM products WHERE category = 'Suits'";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("productID"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setCategory(rs.getString("category"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setImageUrl(rs.getString("imageUrl"));
                product.setSize(rs.getString("size"));
                product.setColor(rs.getString("color"));

                products.add(product);
            }
            rs.close(); // Close ResultSet
            pst.close(); // Close PreparedStatement

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    // Retrieve hats from the database
    public List<Product> getHats() {
        List<Product> products = new ArrayList<>();
        try {
            String query = "SELECT * FROM products WHERE category = 'Hats'";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("productID"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setCategory(rs.getString("category"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setImageUrl(rs.getString("imageUrl"));
                product.setSize(rs.getString("size"));
                product.setColor(rs.getString("color"));

                products.add(product);
            }
            rs.close(); // Close ResultSet
            pst.close(); // Close PreparedStatement

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    // Retrieve belts from the database
    public List<Product> getBelts() {
        List<Product> products = new ArrayList<>();
        try {
            String query = "SELECT * FROM products WHERE category = 'Belts'";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("productID"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setCategory(rs.getString("category"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setImageUrl(rs.getString("imageUrl"));
                product.setSize(rs.getString("size"));
                product.setColor(rs.getString("color"));

                products.add(product);
            }
            rs.close(); // Close ResultSet
            pst.close(); // Close PreparedStatement

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    // Retrieve jewellery from the database
    public List<Product> getJewellery() {
        List<Product> products = new ArrayList<>();
        try {
            String query = "SELECT * FROM products WHERE category = 'Jewellery'";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("productID"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setCategory(rs.getString("category"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setImageUrl(rs.getString("imageUrl"));
                product.setSize(rs.getString("size"));
                product.setColor(rs.getString("color"));

                products.add(product);
            }
            rs.close(); // Close ResultSet
            pst.close(); // Close PreparedStatement

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    // Retrieve shoes from the database
    public List<Product> getShoes() {
        List<Product> products = new ArrayList<>();
        try {
            String query = "SELECT * FROM products WHERE category = 'Shoes'";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("productID"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setCategory(rs.getString("category"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setImageUrl(rs.getString("imageUrl"));
                product.setSize(rs.getString("size"));
                product.setColor(rs.getString("color"));

                products.add(product);
            }
            rs.close(); // Close ResultSet
            pst.close(); // Close PreparedStatement

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    // Retrieve fragrances from the database
    public List<Product> getFragrances() {
        List<Product> products = new ArrayList<>();
        try {
            String query = "SELECT * FROM products WHERE category = 'Fragrance'";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("productID"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setCategory(rs.getString("category"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setImageUrl(rs.getString("imageUrl"));
                product.setSize(rs.getString("size"));
                product.setColor(rs.getString("color"));

                products.add(product);
            }
            rs.close(); // Close ResultSet
            pst.close(); // Close PreparedStatement

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    // Retrieve a single product
    public Product getSingleProduct(int id) {
        Product product = null;
        try {
            String query = "SELECT * FROM products WHERE productID=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                product = new Product();
                product.setProductId(rs.getInt("productID"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setCategory(rs.getString("category"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setImageUrl(rs.getString("imageUrl"));
            }
            rs.close(); // Close ResultSet
            pst.close(); // Close PreparedStatement

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    // Calculate total price of items in the cart
    public double getTotalCartPrice(ArrayList<Cart> cartList) {
        double sum = 0;
        try {
            if (!cartList.isEmpty()) {
                for (Cart item : cartList) {
                    query = "SELECT price FROM products WHERE productID=?";
                    pst = this.con.prepareStatement(query);
                    pst.setInt(1, item.getProductId());
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        sum += rs.getDouble("price") * item.getQuantity();
                    }

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return sum;
    }

    // Retrieve products in the cart
    public List<Cart> getCartProducts(ArrayList<Cart> cartList) {
        List<Cart> products = new ArrayList<>();
        try {
            if (!cartList.isEmpty()) {
                for (Cart item : cartList) {
                    query = "SELECT * FROM products WHERE productID=?";
                    pst = this.con.prepareStatement(query);
                    pst.setInt(1, item.getProductId());
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        Cart row = new Cart();
                        row.setProductId(rs.getInt("productID"));
                        row.setName(rs.getString("name"));
                        row.setCategory(rs.getString("category"));
                        row.setPrice(rs.getDouble("price") * item.getQuantity()); // calculate the total price
                        row.setQuantity(item.getQuantity());
                        products.add(row);
                    }

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return products;
    }

    // Method to update product details
    public boolean updateProduct(Product product) {
        try {
            String query = "UPDATE products SET name=?, description=?, category=?, price=?, quantity=?, imageUrl=? WHERE productID=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, product.getName());
            pst.setString(2, product.getDescription());
            pst.setString(3, product.getCategory());
            pst.setDouble(4, product.getPrice());
            pst.setInt(5, product.getQuantity());
            pst.setString(6, product.getImageUrl());
            pst.setInt(7, product.getProductId());

            int rowsAffected = pst.executeUpdate();
            pst.close(); // Close PreparedStatement

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to delete a product
    public boolean deleteProduct(int id) {
        try {
            String query = "DELETE FROM products WHERE productID=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);

            int rowsAffected = pst.executeUpdate();
            pst.close(); // Close PreparedStatement

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to retrieve product details by order items
    public List<Product> getProductsByOrderItems(List<Cart> cartList) {
        List<Product> products = new ArrayList<>();
        try {
            if (!cartList.isEmpty()) {
                for (Cart item : cartList) {
                    String query = "SELECT * FROM products WHERE productID=?";
                    PreparedStatement pst = con.prepareStatement(query);
                    pst.setInt(1, item.getProductId());
                    ResultSet rs = pst.executeQuery();

                    if (rs.next()) {
                        Product product = new Product();
                        product.setProductId(rs.getInt("productID"));
                        product.setName(rs.getString("name"));
                        product.setDescription(rs.getString("description"));
                        product.setCategory(rs.getString("category"));
                        product.setPrice(rs.getDouble("price"));
                        product.setQuantity(item.getQuantity()); // Set quantity from cart item
                        product.setImageUrl(rs.getString("imageUrl"));
                        products.add(product);
                    }
                    rs.close(); // Close ResultSet
                    pst.close(); // Close PreparedStatement
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    //methd to update price
    public boolean updateProductPrice(Product product) {
        try {
            String query = "UPDATE products SET price=? WHERE productID=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setDouble(1, product.getPrice());
            pst.setInt(2, product.getProductId());

            int rowsAffected = pst.executeUpdate();
            pst.close(); // Close PreparedStatement

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to filter products based on given parameters
    public List<Product> filterProducts(String filter, String category, double minPrice, double maxPrice, String size, String color) {
        List<Product> filteredProducts = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            StringBuilder queryBuilder = new StringBuilder("SELECT * FROM products WHERE 1=1");

            if (filter != null && !filter.isEmpty()) {
                queryBuilder.append(" AND filter = ?");
            }
            if (category != null && !category.isEmpty()) {
                queryBuilder.append(" AND category = ?");
            }
            if (minPrice > 0) {
                queryBuilder.append(" AND price >= ?");
            }
            if (maxPrice > 0) {
                queryBuilder.append(" AND price <= ?");
            }
            if (size != null && !size.isEmpty()) {
                queryBuilder.append(" AND size = ?");
            }
            if (color != null && !color.isEmpty()) {
                queryBuilder.append(" AND color = ?");
            }

            pst = con.prepareStatement(queryBuilder.toString());

            int paramIndex = 1;

            if (filter != null && !filter.isEmpty()) {
                pst.setString(paramIndex++, filter);
            }
            if (category != null && !category.isEmpty()) {
                pst.setString(paramIndex++, category);
            }
            if (minPrice > 0) {
                pst.setDouble(paramIndex++, minPrice);
            }
            if (maxPrice > 0) {
                pst.setDouble(paramIndex++, maxPrice);
            }
            if (size != null && !size.isEmpty()) {
                pst.setString(paramIndex++, size);
            }
            if (color != null && !color.isEmpty()) {
                pst.setString(paramIndex++, color);
            }

            rs = pst.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("productID"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setCategory(rs.getString("category"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setImageUrl(rs.getString("imageUrl"));
                product.setSize(rs.getString("size"));
                product.setColor(rs.getString("color"));

                filteredProducts.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return filteredProducts;
    }

    // Method to retrieve product details by ID
    public Product getProductById(int productId) throws SQLException {
        String query = "SELECT * FROM products WHERE productID = ?";
        try ( PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, productId);
            try ( ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    // Create a Product object with retrieved details
                    Product product = new Product();
                    product.setProductId(rs.getInt("productID"));
                    product.setName(rs.getString("name"));
                    product.setDescription(rs.getString("description"));
                    product.setPrice(rs.getDouble("price"));
                    product.setCategory(rs.getString("category"));
                    product.setQuantity(rs.getInt("quantity"));
                    product.setImageUrl(rs.getString("imageUrl"));
                    return product;
                }
            }
        }
        return null; // Product not found
    }

    // Update product quantity
    public boolean updateProductQuantity(int productId, int quantity) {
        boolean result = false;
        try {
            String query = "UPDATE products SET quantity = quantity - ? WHERE productID = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, quantity);
            pst.setInt(2, productId);
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                result = true;
            }
        } catch (SQLException e) {
            System.out.println("Error updating product quantity: " + e.getMessage());
        }
        return result;
    }

    // Search products by name
    public List<Product> searchProducts(String searchText) {
        List<Product> products = new ArrayList<>();
        try {
            String query = "SELECT * FROM products WHERE name LIKE ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, "%" + searchText + "%");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("productID"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setCategory(rs.getString("category"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setImageUrl(rs.getString("imageUrl"));
                products.add(product);
            }
            rs.close(); // Close ResultSet
            pst.close(); // Close PreparedStatement
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    // Retrieve all products with image URLs
    public List<Product> getAllProductsWithImageURLs() {
        List<Product> products = new ArrayList<>();
        try {
            String query = "SELECT productID, name, price, imageUrl FROM products";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("productID"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setImageUrl(rs.getString("imageUrl"));
                products.add(product);
            }
            rs.close(); // Close ResultSet
            pst.close(); // Close PreparedStatement
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

   // Method to retire a product
// Method to retire a product
public boolean retireProduct(int productId) {
    try {
        // Update the status column of the product to "retired"
        String updateQuery = "UPDATE products SET status = 'retired' WHERE productID = ?";
        PreparedStatement updateStatement = con.prepareStatement(updateQuery);
        updateStatement.setInt(1, productId);
        int rowsAffected = updateStatement.executeUpdate();

        // Check if the update was successful
        if (rowsAffected > 0) {
            return true; // Product retired successfully
        } else {
            return false; // Product not found or already retired
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return false; // Error occurred
    }
}

}
