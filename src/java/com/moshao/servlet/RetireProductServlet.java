package com.moshao.servlet;

import com.moshao.connection.DbCon;
import com.moshao.dao.ProductDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RetireProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Check user authentication
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("auth") != null) {
            // Get product ID parameter
            String productId = request.getParameter("productId");

            if (productId != null && !productId.isEmpty()) {
                try {
                    // Get the real path to the SQL file
                    String realPath = request.getServletContext().getRealPath("/exportedDbFile/urbanelegance2128084.sql");
                    // Convert product ID to integer
                    int id = Integer.parseInt(productId);

                    // Create ProductDao instance
                    ProductDao productDao = new ProductDao(DbCon.getConnection(realPath));

                    // Call retireProduct method
                    boolean retired = productDao.retireProduct(id);

                    if (retired) {
                        // Successful retirement
                        response.setStatus(HttpServletResponse.SC_OK);
                        response.getWriter().write("Product retired successfully");
                    } else {
                        // Failure
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write("Error retiring product");
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().write("Error occurred while retiring product");
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().write("Invalid product ID");
                } catch (InterruptedException ex) {
                    Logger.getLogger(RetireProductServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                // Missing product ID parameter
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Missing product ID");
            }
        } else {
            // Unauthorized access
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized access");
        }
    }
}
