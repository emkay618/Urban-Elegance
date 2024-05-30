package com.moshao.servlet;

import com.moshao.connection.DbCon;
import com.moshao.dao.OrderDao;
import com.moshao.model.Order;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DisplayOrdersServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            // Get the user ID selected from the form
            int userId = Integer.parseInt(request.getParameter("userId"));
             // Get the real path to the SQL file
             String realPath = request.getServletContext().getRealPath("/exportedDbFile/urbanelegance2128084.sql");
            // Retrieve orders for the selected user from the database
            OrderDao orderDao = new OrderDao(DbCon.getConnection(realPath));
            List<Order> userOrders = orderDao.getUserOrders(userId);

            // Pass the list of orders to the JSP for display
            request.setAttribute("userOrders", userOrders);
            request.getRequestDispatcher("display-orders.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            // Handle invalid user ID input
            request.setAttribute("errorMessage", "Invalid user ID provided");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred while processing your request");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
