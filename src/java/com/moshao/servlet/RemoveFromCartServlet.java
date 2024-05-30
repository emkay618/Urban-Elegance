package com.moshao.servlet;

import com.moshao.model.Cart;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveFromCartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            // Retrieve cart list from session
            String bookId = request.getParameter("id");
            // Check if cart is not null
            if (bookId != null) {
                // Retrieve cart list from session
                ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
                // Check if cart list is not null
                if (cart_list != null) {
                    // Iterate over cart items
                    for (Cart c : cart_list) {
                        // Check if cart item's productId matches bookId
                        if (c.getProductId() == Integer.parseInt(bookId)) {
                            cart_list.remove(cart_list.indexOf(c));// Remove cart item
                            break;
                        }
                    }
                }
                // Redirect to cart page
                response.sendRedirect("cart.jsp");

            } else {
                // Redirect to cart page if bookId is null
                response.sendRedirect("cart.jsp");
            }

        }
    }

}
