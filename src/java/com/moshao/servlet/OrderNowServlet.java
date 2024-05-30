package com.moshao.servlet;

import com.moshao.connection.DbCon;
import com.moshao.dao.OrderDao;
import com.moshao.dao.ProductDao;
import com.moshao.model.Cart;
import com.moshao.model.JavaMailUtil;
import com.moshao.model.Order;
import com.moshao.model.Product;
import com.moshao.model.User;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderNowServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get the real path to the SQL file
            String realPath = request.getServletContext().getRealPath("/exportedDbFile/urbanelegance2128084.sql");
            // Create date formatter for formatting order date
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            // Retrieve cart items and authenticated user
            ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
            User auth = (User) request.getSession().getAttribute("auth");
            // Check authentication and cart list
            if (cart_list != null && auth != null) {
                // Prepare invoice content
                StringBuilder invoiceContentBuilder = new StringBuilder();
                double totalAmount = 0;
                // Start HTML content
                String companyName = "Urban Elegance";
                String companyAddress = "Greenland Street 322";
                String companyPhone = "2234321";
                invoiceContentBuilder.append("<p>").append(companyName).append("</p>");
                invoiceContentBuilder.append("<p>").append(companyAddress).append("</p>");
                invoiceContentBuilder.append("<p>").append(companyPhone).append("</p>");

                // Start HTML table
                invoiceContentBuilder.append("<table border=\"1\"><tr><th>Product ID</th><th>Product Name</th><th>Quantity</th><th>Price per unit</th><th>Total Price</th></tr>");
                // Iterate over cart items
                for (Cart c : cart_list) {
                    int productId = c.getProductId();
                    int quantity = c.getQuantity();

                    // Fetch product details from the database based on product ID
                    ProductDao productDao = new ProductDao(DbCon.getConnection(realPath));
                    Product product = productDao.getProductById(productId);
                    if (product != null) {
                        double price = product.getPrice();
                        double itemTotal = price * quantity;
                        totalAmount += itemTotal;
                        // Append product details to invoice content as HTML table rows
                        invoiceContentBuilder.append("<tr>");
                        invoiceContentBuilder.append("<td>").append(productId).append("</td>");
                        invoiceContentBuilder.append("<td>").append(product.getName()).append("</td>");
                        invoiceContentBuilder.append("<td>").append(quantity).append("</td>");
                        invoiceContentBuilder.append("<td>").append("$").append(price).append("</td>");
                        invoiceContentBuilder.append("<td>").append("$").append(itemTotal).append("</td>");
                        invoiceContentBuilder.append("</tr>");
                        // Update product quantity in the database
                        productDao.updateProductQuantity(product.getProductId(), c.getQuantity());
                    }
                }

                // Close HTML table and append total amount
                invoiceContentBuilder.append("</table>");
                invoiceContentBuilder.append("<p>Total Amount: $").append(totalAmount).append("</p>");
                // Convert invoice content to string
                String invoiceContent = invoiceContentBuilder.toString();
                String productId = request.getParameter("id");
                // Iterating over the cart items 
                for (Cart c : cart_list) {
                    // Prepare new order object
                    Order order = new Order();
                    order.setProductid(Integer.parseInt(productId));
                    order.setUserid(auth.getUserID());
                    order.setQuantity(c.getQuantity());
                    // Convert the Date object to a Timestamp object
                    Timestamp timestamp = new Timestamp(date.getTime());
                    order.setOrderDate(timestamp);
                    // Send data to OrderDao and invoke insert method
                    OrderDao oDao = new OrderDao(DbCon.getConnection(realPath));
                    boolean result = oDao.insertOrder(order);
                    if (!result) {
                        break;
                    }
                }
                // Clear the cart list
                cart_list.clear();
                // Redirect users back to home
                response.sendRedirect("index.jsp");
                // Trigger email dispatch upon order confirmation
                String customerEmail = auth.getEmail();
                // Send an email confirmation to the user along with the invoice content
                String subject = "Order Confirmation";
                String message = "Thank you for your order. Please find the attached invoice below.";
                JavaMailUtil.sendHtmlMail(customerEmail, subject, message, invoiceContent);
            } else {
                // If no logged-in user, redirect to login page
                if (auth == null) {
                    // If no logged-in user, redirect to login page
                    response.sendRedirect("login.jsp");
                }
                // Redirect users back to the cart
                response.sendRedirect("cart.jsp");
            }
        } catch (ClassNotFoundException | SQLException e) {
            // Handle ClassNotFoundException and SQLException
            e.printStackTrace();
        } catch (Exception ex) {
            // Handle other exceptions
            Logger.getLogger(CheckOutServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // If the request is a POST, forward it to doGet
        doGet(request, response);
    }

}
