package com.moshao.servlet;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.util.*;
import com.moshao.model.Product;
import com.moshao.dao.ProductDao;
import com.moshao.connection.DbCon;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SortProductsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get the selected sorting option from the request parameter
            String sortOption = request.getParameter("sort");
            
            // Retrieve all products from the database
            String realPath = getServletContext().getRealPath("/exportedDbFile/urbanelegance2128084.sql");
            ProductDao pd = new ProductDao(DbCon.getConnection(realPath));
            List<Product> products = pd.getAllProducts();
            
            // Sort the products based on the selected option
            if (sortOption.equals("priceLowToHigh")) {
                products.sort(Comparator.comparingDouble(Product::getPrice));
            } else if (sortOption.equals("priceHighToLow")) {
                products.sort((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()));
            } else if (sortOption.equals("newest")) {
                // Sort by productId (assuming it represents the creation order)
                products.sort(Comparator.comparingInt(Product::getProductId).reversed());
            }
            
            // Set the sorted product list as a request attribute
            request.setAttribute("products", products);
            
            // Forward the request to the JSP for rendering
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SortProductsServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SortProductsServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(SortProductsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
