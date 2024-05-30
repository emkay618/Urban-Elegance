package com.moshao.servlet;

import com.moshao.model.Cart;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuantityIncDecServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            // Retrieve action and id parameters from request
            String action = request.getParameter("action");
            int id = Integer.parseInt(request.getParameter("id"));
            // Retrieve cart list from session
            ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
            // Check action and id
            if (action != null && id >= 1) {
                // Increase quantity action
                if (action.equals("inc")) {
                    for (Cart c : cart_list) {
                        if (c.getProductId() == id) {
                            int quantity = c.getQuantity();
                            quantity++;//update quantity
                            c.setQuantity(quantity);//set new value
                            response.sendRedirect("cart.jsp");// Redirect to cart page
                        }
                    }
                }
                // Decrease quantity action
                if (action.equals("dec")) {
                    for (Cart c : cart_list) {
                        if (c.getProductId() == id && c.getQuantity() > 1) {
                            int quantity = c.getQuantity();
                            quantity--;// Decrement quantity
                            c.setQuantity(quantity); // Set new value
                            break;
                        }
                    }
                    // Redirect to cart page
                    response.sendRedirect("cart.jsp");
                }
            } else {
                // Redirect to cart page
                response.sendRedirect("cart.jsp");
            }
        }
    }

}
