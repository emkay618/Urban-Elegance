package com.moshao.servlet;

import com.moshao.connection.DbCon;
import com.moshao.dao.UserDao;
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

public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            // Retrieve email and password from request parameters
            String email = request.getParameter("login-email");
            String password = request.getParameter("login-password");

            // Hash the password before comparing it with the stored hashed password
            String hashedPassword = hashPassword(password);
            // Get the real path to the SQL file
            String realPath = request.getServletContext().getRealPath("/exportedDbFile/urbanelegance2128084.sql");
            // Create UserDao instance
            UserDao userDao = new UserDao(DbCon.getConnection(realPath));
            // Perform user login using email and hashed password
            User user = userDao.userLogin(email, hashedPassword);
            // If user is authenticated, set user attribute in session and redirect to index.jsp
            if (user != null) {
                request.getSession().setAttribute("auth", user);
                response.sendRedirect("index.jsp");
            } else {
                // If authentication fails, display error message
                out.println("Invalid email or password.");
            }

        } catch (ClassNotFoundException | SQLException e) {
            // Handle ClassNotFoundException and SQLException
            e.printStackTrace();
        } catch (InterruptedException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
