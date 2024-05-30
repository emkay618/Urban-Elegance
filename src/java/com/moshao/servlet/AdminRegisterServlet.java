package com.moshao.servlet;

import com.moshao.connection.DbCon;
import com.moshao.dao.AdminDao;
import com.moshao.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminRegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            String username = request.getParameter("register-username");
            String email = request.getParameter("register-email");
            String password = request.getParameter("register-password");
            String userType = request.getParameter("register-user-type");
            String phone = request.getParameter("register-phone");
            String country = request.getParameter("register-country");
            String city = request.getParameter("register-city");
            String postalCode = request.getParameter("register-postal-code");
            String street = request.getParameter("register-street");

            // Encrypt the password using SHA-256 hashing algorithm
            String hashedPassword = hashPassword(password);
            // Get the real path to the SQL file
            String realPath = request.getServletContext().getRealPath("/exportedDbFile/urbanelegance2128084.sql");
            AdminDao adminDao = new AdminDao(DbCon.getConnection(realPath));
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(hashedPassword);
            user.setPhone(phone);
            user.setCountry(country);
            user.setCity(city);
            user.setPostalCode(postalCode);
            user.setStreet(street);
            user.setAdmin("admin".equals(userType));

            if (adminDao.registerUser(user)) {
                response.sendRedirect("index.jsp");//redirect user to index.jsp
            } else {
                out.println("Failed to register user.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException ex) {
            Logger.getLogger(AdminRegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Method to hash the password using SHA-256 algorithm
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
