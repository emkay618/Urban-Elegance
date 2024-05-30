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

public class UpdateProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productId = request.getParameter("productId");
        if (productId != null && !productId.isEmpty()) {
            try {
                // Get the real path to the SQL file
                String realPath = request.getServletContext().getRealPath("/exportedDbFile/urbanelegance2128084.sql");
                
                int id = Integer.parseInt(productId);
                String name = request.getParameter("productName");
                double price = Double.parseDouble(request.getParameter("productPrice"));
                String category = request.getParameter("productCategory");
                String size = request.getParameter("productSize");
                String color = request.getParameter("productColor");

                Product product = new Product();
                product.setProductId(id);
                product.setName(name);
                product.setPrice(price);
                product.setCategory(category);
                product.setSize(size);
                product.setColor(color);

                ProductDao productDao = new ProductDao(DbCon.getConnection(realPath));
                boolean updated = productDao.updateProduct(product);

                if (updated) {
                    response.sendRedirect("productUpdate.jsp"); // Redirect back to product list
                } else {
                    response.sendRedirect("updateFailure.jsp");
                }
            } catch (NumberFormatException | SQLException e) {
                e.printStackTrace();
                response.sendRedirect("updateFailure.jsp");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UpdateProductServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(UpdateProductServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            response.sendRedirect("updateFailure.jsp");
        }
    }
}
