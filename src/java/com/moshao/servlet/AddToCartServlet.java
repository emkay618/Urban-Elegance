package com.moshao.servlet;

import com.moshao.model.Cart;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddToCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            int productId = Integer.parseInt(request.getParameter("id"));

            HttpSession session = request.getSession();
            ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cart-list");

            if (cartList == null) {
                cartList = new ArrayList<>();
            }

            // Check if the product already exists in the cart
            boolean productExists = false;
            for (Cart cartItem : cartList) {
                if (cartItem.getProductId() == productId) {
                    // If the product exists, update its quantity and set the flag
                    cartItem.setQuantity(cartItem.getQuantity() + 1);
                    productExists = true;
                    break;
                }
            }

            if (!productExists) {
                // If the product doesn't exist, add it to the cart with quantity 1
                Cart newCartItem = new Cart();
                newCartItem.setProductId(productId);
                newCartItem.setQuantity(1);
                cartList.add(newCartItem);
            }

            // Update the session attribute with the modified cart list
            session.setAttribute("cart-list", cartList);

            // Redirect back to the index page or cart page
            response.sendRedirect("index.jsp");
        } catch (NumberFormatException e) {
            response.sendRedirect("index.jsp?message=Invalid product ID");
        }
    }

}
