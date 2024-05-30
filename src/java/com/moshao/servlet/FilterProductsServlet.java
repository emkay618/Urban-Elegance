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

public class FilterProductsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Retrieving filter parameters from request
            String filter = request.getParameter("filter");
            String category = request.getParameter("category");
            double minPrice = Double.parseDouble(request.getParameter("minPrice"));
            double maxPrice = Double.parseDouble(request.getParameter("maxPrice"));
            String sizeFilter = request.getParameter("sizeFilter");
            String colorFilter = request.getParameter("colorFilter");

            // Get the real path to the SQL file
            String realPath = request.getServletContext().getRealPath("/exportedDbFile/urbanelegance2128084.sql");
            // Creating ProductDao instance with database connection
            ProductDao productDao = new ProductDao(DbCon.getConnection(realPath));
            // Filtering products based on parameters
            List<Product> filteredProducts = productDao.filterProducts(filter, category, minPrice, maxPrice, sizeFilter, colorFilter);
            // // Setting filtered products as attribute in request
            request.setAttribute("filteredProducts", filteredProducts);
            /// Forwarding request to index.jsp for displaying filtered products
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            // Handling exceptions
            Logger.getLogger(FilterProductsServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(FilterProductsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
