package com.moshao.servlet;

import com.moshao.connection.DbCon;
import com.moshao.dao.ProductDao;
import com.moshao.model.Product;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ProductDetailsServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve the product ID from the request parameter
            int productId = Integer.parseInt(request.getParameter("id"));

            // Get the real path to the SQL file
            String realPath = request.getServletContext().getRealPath("/exportedDbFile/urbanelegance2128084.sql");

            // Create a ProductDao object with the connection
            ProductDao productDao = new ProductDao(DbCon.getConnection(realPath));

            // Fetch product details from the database based on the product ID
            Product product = productDao.getProductById(productId);

            // Set the product attribute in request scope
            request.setAttribute("product", product);

            // Forward the request to cataloque-details.jsp
            request.getRequestDispatcher("/product-details.jsp").forward(request, response);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL Exception", e);
            // Handle database error
            response.getWriter().println("Failed to retrieve product data. Please try again.");
        } catch (ClassNotFoundException ex) {
            logger.log(Level.SEVERE, "Class Not Found Exception", ex);
            response.getWriter().println("Failed to retrieve product data. Please try again.");
        } catch (NumberFormatException | NullPointerException ex) {
            logger.log(Level.SEVERE, "Invalid Product ID", ex);
            response.getWriter().println("Invalid product ID. Please provide a valid ID.");
        } catch (InterruptedException ex) {
            logger.log(Level.SEVERE, "Interrupted Exception", ex);
            response.getWriter().println("Failed to import database. Please try again.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle POST request
        doGet(request, response);
    }
}
