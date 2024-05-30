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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckOutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            // Retrieve all cart products
            ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
            // User authentication
            User auth = (User) request.getSession().getAttribute("auth");
            // Check authentication and cart list
            if (cart_list != null && auth != null) {
                // Create invoice content as HTML table
                StringBuilder invoiceContentBuilder = new StringBuilder();
                double totalAmount = 0;
                // DecimalFormat for formatting price and total amount
                DecimalFormat df = new DecimalFormat("#.##");
                // Start HTML content
                String companyName = "Urban Elegance";
                String companyAddress = "Greenland Street 322";
                String companyPhone = "2234321";
                invoiceContentBuilder.append("<p>").append(companyName).append("</p>");
                invoiceContentBuilder.append("<p>").append(companyAddress).append("</p>");
                invoiceContentBuilder.append("<p>").append(companyPhone).append("</p>");
                // Start HTML table
                invoiceContentBuilder.append("<table border=\"1\">");
                invoiceContentBuilder.append("<tr><th>Product Name</th><th>Price</th><th>Quantity</th><th>Total Price</th></tr>");
                 // Get the real path to the SQL file
                String realPath = request.getServletContext().getRealPath("/exportedDbFile/urbanelegance2128084.sql");
                // Iterating over the cart items 
                for (Cart c : cart_list) {
                    // Fetch product details from the database based on product ID
                    ProductDao productDao = new ProductDao(DbCon.getConnection(realPath));
                    Product product = productDao.getProductById(c.getProductId());
                    if (product != null) {
                        double price = product.getPrice();
                        double itemTotal = price * c.getQuantity();
                        totalAmount += itemTotal;
                        // Append product details to invoice content
                        invoiceContentBuilder.append("<tr>");
                        invoiceContentBuilder.append("<td>").append(product.getName()).append("</td>");
                        invoiceContentBuilder.append("<td>").append("$").append(df.format(price)).append("</td>");
                        invoiceContentBuilder.append("<td>").append(c.getQuantity()).append("</td>");
                        invoiceContentBuilder.append("<td>").append("$").append(df.format(itemTotal)).append("</td>");
                        invoiceContentBuilder.append("</tr>");
                        // Update product quantity in the database
                        productDao.updateProductQuantity(product.getProductId(), c.getQuantity());
                    }
                }
                // End HTML table and append total amount row
                invoiceContentBuilder.append("</table>");
                invoiceContentBuilder.append("<p>Total Amount: $").append(df.format(totalAmount)).append("</p>");
                String invoiceContent = invoiceContentBuilder.toString();
                // Iterating over the cart items 
                for (Cart c : cart_list) {
                    // Prepare new order object
                    Order order = new Order();
                    order.setProductid(c.getProductId()); // Product ID
                    order.setUserid(auth.getUserID());
                    order.setQuantity(c.getQuantity());
                    // Convert the Date object to a Timestamp object
                    Timestamp timestamp = new Timestamp(date.getTime());
                    order.setOrderDate(timestamp);
                    // Send data to OrderDao (instantiate first)
                    OrderDao oDao = new OrderDao(DbCon.getConnection(realPath));
                    boolean result = oDao.insertOrder(order); // Invoke insert method
                    if (!result) {
                        break;
                    }
                }
                cart_list.clear();
                // Redirect users back to the order
                response.sendRedirect("index.jsp");
                // Trigger email dispatch upon order confirmation
                String customerEmail = auth.getEmail();
                // Specify email subject
                String subject = "Order Confirmation";
                // Specify email message
                String message = "Thank you for your order. Please find the attached invoice below.";
                // Send an email confirmation to the user along with the invoice content and company details
                JavaMailUtil.sendHtmlMail(customerEmail, subject, message, invoiceContent);
            } else {
                if (auth == null) {
                    // If no logged-in user, redirect to login page
                    response.sendRedirect("login.jsp");
                }
                // Redirect users back to the cart
                response.sendRedirect("cart.jsp");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(CheckOutServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // If the request is a POST, forward it to doGet
        doGet(request, response);
    }

}
