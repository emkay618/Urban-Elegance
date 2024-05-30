package com.moshao.servlet;

import com.moshao.connection.DbCon;
import com.moshao.dao.ProductDao;
import com.moshao.model.Product;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Get the real path to the SQL file
            String realPath = request.getServletContext().getRealPath("/exportedDbFile/urbanelegance2128084.sql");
            // Retrieve search text from request parameter
            String searchText = request.getParameter("searchText");
            // Check if search text is not null and not empty
            if (searchText != null && !searchText.isEmpty()) {
                // Create ProductDao instance with database connection
                ProductDao productDao = new ProductDao(DbCon.getConnection(realPath));
                // Search for products based on search text
                List<Product> searchedProducts = productDao.searchProducts(searchText);
                // Set searched products in request attribute
                request.setAttribute("searchedProducts", searchedProducts);
                // Forward request to search result page
                request.getRequestDispatcher("searchResult.jsp").forward(request, response);
            } else {
                // Handle empty search text by redirecting to search failure page
                response.sendRedirect("searchFailure.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database error
            response.getWriter().println("Failed to search products. Please try again.");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            // Handle class not found error
            response.getWriter().println("Failed to search products. Please try again.");
        } catch (InterruptedException ex) {
            Logger.getLogger(SearchProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle POST requests if needed
    }
}
