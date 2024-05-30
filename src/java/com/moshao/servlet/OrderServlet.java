package com.moshao.servlet;

import com.moshao.connection.DbCon;
import com.moshao.dao.OrderDao;
import com.moshao.model.Order;
import com.moshao.model.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

public class OrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get session
        HttpSession session = request.getSession();
        // Retrieve authenticated user from session
        User auth = (User) session.getAttribute("auth");

        if (auth != null) {
            Connection con = null;
            try {
                // Get the real path to the SQL file
                String realPath = request.getServletContext().getRealPath("/exportedDbFile/urbanelegance2128084.sql");
                // Get the database connection
                con = DbCon.getConnection(realPath);

                // Retrieve orders based on user role
                OrderDao orderDao = new OrderDao(con);
                List<Order> orders;
                if (auth.isAdmin()) {
                    // Admin users can see all orders
                    orders = orderDao.getAllOrders();
                } else {
                    // Regular users can only see their own orders
                    orders = orderDao.getUserOrders(auth.getUserID());
                }

                // Define date format for displaying order date
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                // Set orders list and date format in request attributes
                request.setAttribute("orders", orders);
                request.setAttribute("dateFormat", dateFormat);
            } catch (Exception e) {
                // Handle exceptions
                e.printStackTrace();
                request.setAttribute("errorMessage", "Error occurred while retrieving orders: " + e.getMessage());
            } finally {
                // Close the database connection
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            // If user is not logged in, redirect to login page
            response.sendRedirect("login.jsp");
            return;
        }

        // Forward the request to the JSP for rendering
        request.getRequestDispatcher("/orders.jsp").forward(request, response);
    }
}
