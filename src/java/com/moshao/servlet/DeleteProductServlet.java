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

public class DeleteProductServlet extends HttpServlet {

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Check if the user is logged in
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("auth") != null) {
            // Get the product ID parameter from the request
            String productId = request.getParameter("id");

            if (productId != null && !productId.isEmpty()) {
                try {
                    // Convert the product ID to an integer
                    int id = Integer.parseInt(productId);
                    // Get the real path to the SQL file
                    String realPath = request.getServletContext().getRealPath("/exportedDbFile/urbanelegance2128084.sql");
                    // Create a ProductDao instance
                    ProductDao productDao = new ProductDao(DbCon.getConnection(realPath));

                    // Call the deleteProduct method to delete the product
                    boolean deleted = productDao.deleteProduct(id);

                    if (deleted) {
                        // Set the response status to 200 (OK) to indicate successful deletion
                        response.setStatus(HttpServletResponse.SC_OK);
                    } else {
                        // Set the response status to 500 (Internal Server Error) to indicate failure
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Set the response status to 500 (Internal Server Error) if an SQL exception occurs
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    // Set the response status to 400 (Bad Request) if the product ID is invalid
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    // Set the response status to 500 (Internal Server Error) if a class not found exception occurs
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                } catch (InterruptedException ex) {
                    Logger.getLogger(DeleteProductServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                // Set the response status to 400 (Bad Request) if the product ID parameter is missing
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            // Set the response status to 401 (Unauthorized) if the user is not logged in
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
