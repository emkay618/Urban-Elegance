package com.moshao.servlet;

import com.moshao.connection.DbCon;
import com.moshao.dao.UserDao;
import com.moshao.model.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Get the real path to the SQL file
            String realPath = request.getServletContext().getRealPath("/exportedDbFile/urbanelegance2128084.sql");
            // Get database connection
            Connection con = DbCon.getConnection(realPath);
            // Initialize UserDao with the connection
            UserDao userDao = new UserDao(con);
            // Retrieve all users from the database
            List<User> users = userDao.getAllUsers();
            // Set users attribute in request for JSP rendering
            request.setAttribute("users", users);
            // Forward request to userUpdate.jsp for rendering
            request.getRequestDispatcher("userUpdate.jsp").forward(request, response);
            // Close the database connection
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Redirect to error.jsp in case of database error
            response.sendRedirect("error.jsp");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdateUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(UpdateUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // This will handle the form submission for updating a user
        // Get the parameters from the form
        String userId = request.getParameter("userId");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String country = request.getParameter("country");
        String city = request.getParameter("city");
        String postalCode = request.getParameter("postalCode");
        String street = request.getParameter("street");
        boolean isAdmin = Boolean.parseBoolean(request.getParameter("isAdmin"));

        try {
            // Get the real path to the SQL file
            String realPath = request.getServletContext().getRealPath("/exportedDbFile/urbanelegance2128084.sql");
            // Get database connection
            Connection con = DbCon.getConnection(realPath);
            // Initialize UserDao with the connection
            UserDao userDao = new UserDao(con);
            // Create a User object with updated information
            User user = new User();
            user.setUserID(Integer.parseInt(userId));
            user.setUsername(username);
            user.setEmail(email);
            user.setPhone(phone);
            user.setCountry(country);
            user.setCity(city);
            user.setPostalCode(postalCode);
            user.setStreet(street);
            user.setAdmin(isAdmin);
            // Update user in the database
            boolean updated = userDao.updateUser(user);
            // Close the database connection
            con.close();
            // Redirect to update success or failure page based on the update result
            if (updated) {
                response.sendRedirect("updateUserSuccess.jsp");
            } else {
                response.sendRedirect("updateUserFailure.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("updateUserFailure.jsp");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("updateUserFailure.jsp");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdateUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(UpdateUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
