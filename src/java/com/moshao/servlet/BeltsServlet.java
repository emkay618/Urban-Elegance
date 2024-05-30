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

public class BeltsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ShirtsServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get the real path to the SQL file
            String realPath = request.getServletContext().getRealPath("/exportedDbFile/urbanelegance2128084.sql");
            ProductDao productDao = new ProductDao(DbCon.getConnection(realPath));
            List<Product> belts = productDao.getBelts(); // Retrieve belts using getBelts()
            request.setAttribute("belts", belts);
            request.getRequestDispatcher("belts.jsp").forward(request, response);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL Exception", e);
            // Handle database error
            response.getWriter().println("Failed to retrieve belt data. Please try again.");
        } catch (ClassNotFoundException ex) {
            logger.log(Level.SEVERE, "Class Not Found Exception", ex);
            response.getWriter().println("Failed to retrieve belt data. Please try again.");
        } catch (InterruptedException ex) {
            Logger.getLogger(BeltsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle POST requests
        doGet(request, response);
    }
}
