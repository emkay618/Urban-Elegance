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

public class HatsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ShirtsServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
             // Get the real path to the SQL file
                String realPath = request.getServletContext().getRealPath("/exportedDbFile/urbanelegance2128084.sql");
            // Create ProductDao instance with database connection
            ProductDao productDao = new ProductDao(DbCon.getConnection(realPath));
            // Retrieve hats from the database
            List<Product> hats = productDao.getHats();
            // Set hats as attribute in request
            request.setAttribute("hats", hats);
            // Forward request to hats.jsp for displaying hats
            request.getRequestDispatcher("hats.jsp").forward(request, response);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL Exception", e);
            // Handle database error
            response.getWriter().println("Failed to retrieve hat data. Please try again.");
        } catch (ClassNotFoundException ex) {
            // Log class not found exception
            logger.log(Level.SEVERE, "Class Not Found Exception", ex);
            // Handle database error
            response.getWriter().println("Failed to retrieve hat data. Please try again.");
        } catch (InterruptedException ex) {
            Logger.getLogger(HatsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle POST requests by invoking doGet
        doGet(request, response);
    }
}
