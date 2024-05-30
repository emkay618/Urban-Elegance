package com.moshao.servlet;

import com.moshao.connection.DbCon;
import com.moshao.dao.OrderDao;
import com.moshao.dao.ProductDao;
import com.moshao.model.JavaMailUtil;
import com.moshao.model.Order;
import com.moshao.model.Product;
import com.moshao.model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BuyNowServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get product ID from request parameter
        String productId = request.getParameter("id");

        // Check if user is authenticated
        User auth = (User) request.getSession().getAttribute("auth");
        if (auth != null) {
            try {
                 // Get the real path to the SQL file
                String realPath = request.getServletContext().getRealPath("/exportedDbFile/urbanelegance2128084.sql");
                // Get product details based on product ID
                ProductDao productDao = new ProductDao(DbCon.getConnection(realPath));
                Product product = productDao.getProductById(Integer.parseInt(productId));
                if (product != null) {
                    // Create order object
                    Order order = new Order();
                    order.setProductid(product.getProductId());
                    order.setUserid(auth.getUserID());
                    order.setQuantity(1); // Assuming quantity is always 1 for direct purchases
                    // Set order date
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    order.setOrderDate(timestamp);
                    // Insert order into database
                    OrderDao oDao = new OrderDao(DbCon.getConnection(realPath));
                    boolean result = oDao.insertOrder(order);
                    if (result) {
                        // If insertion is successful, redirect to home page
                        response.sendRedirect("index.jsp");
                        // Send order confirmation email
                        String customerEmail = auth.getEmail();
                        // Retrieve data from the database and append invoice to the email
                        StringBuilder invoiceContentBuilder = new StringBuilder();
                        double totalAmount = product.getPrice(); // Assuming quantity is always 1
                        // Start HTML content
                        String companyName = "Urban Elegance";
                        String companyAddress = "Greenland Street 322";
                        String companyPhone = "2234321";
                        invoiceContentBuilder.append("<p>").append(companyName).append("</p>");
                        invoiceContentBuilder.append("<p>").append(companyAddress).append("</p>");
                        invoiceContentBuilder.append("<p>").append(companyPhone).append("</p>");
                        // Format invoice as HTML table
                        invoiceContentBuilder.append("<table border=\"1\">");
                        invoiceContentBuilder.append("<tr><th>Product ID</th><th>Product Name</th><th>Quantity</th><th>Price per unit</th><th>Total Price</th></tr>");
                        invoiceContentBuilder.append("<tr>");
                        invoiceContentBuilder.append("<td>").append(product.getProductId()).append("</td>");
                        invoiceContentBuilder.append("<td>").append(product.getName()).append("</td>");
                        invoiceContentBuilder.append("<td>").append(1).append("</td>"); // Assuming quantity is always 1
                        invoiceContentBuilder.append("<td>$").append(product.getPrice()).append("</td>");
                        invoiceContentBuilder.append("<td>$").append(totalAmount).append("</td>");
                        invoiceContentBuilder.append("</tr>");
                        invoiceContentBuilder.append("</table>");
                        // Update product quantity in the database
                        productDao.updateProductQuantity(product.getProductId(), product.getQuantity());

                        // Total amount
                        invoiceContentBuilder.append("<p>Total Amount: $").append(totalAmount).append("</p>");

                        String invoiceContent = invoiceContentBuilder.toString();
                        String subject = "Order Confirmation";
                        String message = "Thank you for your order. Please find the attached invoice below.";
                        // Send email with HTML-formatted invoice content
                        JavaMailUtil.sendHtmlMail(customerEmail, subject, message, invoiceContent);
                    } else {
                        // If insertion fails, redirect to an error page or show an error message
                        response.sendRedirect("error.jsp");
                    }
                } else {
                    // If the product does not exist, redirect to an error page or show an error message
                    response.sendRedirect("error.jsp");
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                // Redirect to an error page or show an error message
                response.sendRedirect("error.jsp");
            } catch (Exception ex) {
                Logger.getLogger(OrderNowServlet.class.getName()).log(Level.SEVERE, null, ex);
                // Redirect to an error page or show an error message
                response.sendRedirect("error.jsp");
            }
        } else {
            // If user is not authenticated, redirect to login page
            response.sendRedirect("login.jsp");
        }
    }
}
