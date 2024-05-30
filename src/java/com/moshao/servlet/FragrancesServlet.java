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

public class FragrancesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ShirtsServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get the real path to the SQL file
            String realPath = request.getServletContext().getRealPath("/exportedDbFile/urbanelegance2128084.sql");
            // Create ProductDao instance with database connection
            ProductDao productDao = new ProductDao(DbCon.getConnection(realPath));
            // Retrieve fragrances from the database
            List<Product> fragrances = productDao.getFragrances();
            // Set fragrances as attribute in request
            request.setAttribute("fragrances", fragrances);
            // Forward request to fragrances.jsp for displaying fragrances
            request.getRequestDispatcher("fragrances.jsp").forward(request, response);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL Exception", e);
            // Handle database error
            response.getWriter().println("Failed to retrieve fragrance data. Please try again.");
        } catch (ClassNotFoundException ex) {
            logger.log(Level.SEVERE, "Class Not Found Exception", ex);
            response.getWriter().println("Failed to retrieve fragrance data. Please try again.");
        } catch (InterruptedException ex) {
            Logger.getLogger(FragrancesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle POST requests by ivoking doGet
        doGet(request, response);
    }
}
