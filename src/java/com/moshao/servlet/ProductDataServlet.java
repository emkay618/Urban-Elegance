package com.moshao.servlet;

import com.google.gson.Gson;
import com.moshao.connection.DbCon;
import com.moshao.dao.ProductDao;
import com.moshao.model.Product;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDataServlet extends HttpServlet {

    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get the real path to the SQL file
            String realPath = request.getServletContext().getRealPath("/exportedDbFile/urbanelegance2128084.sql");
            // Create ProductDao instance with database connection
            ProductDao productDao = new ProductDao(DbCon.getConnection(realPath));
            // Retrieve all products with image URLs from the database
            List<Product> products = productDao.getAllProductsWithImageURLs();
            // Convert products to JSON
            String jsonProducts = gson.toJson(products);
            // Set response content type and character encoding
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            // Write JSON products to response
            response.getWriter().write(jsonProducts);
        } catch (SQLException | ClassNotFoundException e) {
            // Handle SQL or ClassNotFound exceptions
            e.printStackTrace();
            // Set 500 status for internal server error
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            // Print error message to response
            response.getWriter().println("Failed to retrieve product data. Please try again.");
        } catch (InterruptedException ex) {
            Logger.getLogger(ProductDataServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle POST requests if necessary
        doGet(request, response);
    }
}
