package com.moshao.servlet;

import com.moshao.connection.DbCon;
import com.moshao.dao.AdminDao;
import com.moshao.model.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ViewUsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the session
        HttpSession session = request.getSession();
        // Retrieve the authenticated user from the session
        User auth = (User) session.getAttribute("auth");
        // Check if the user is authenticated and is an admin
        if (auth != null && auth.isAdmin()) { // Only admins can view all users
            Connection con = null;
            try {
                // Get the real path to the SQL file
                String realPath = request.getServletContext().getRealPath("/exportedDbFile/urbanelegance2128084.sql");

                // Get the database connection
                con = DbCon.getConnection(realPath);

                // Initialize AdminDao with the connection
                AdminDao adminDao = new AdminDao(con);

                // Get all users from database
                List<User> users = adminDao.getAllUsers();

                // Set the list of users in request attributes for JSP rendering
                request.setAttribute("users", users);
            } catch (Exception e) {
                // Handle exceptions
                e.printStackTrace();
                request.setAttribute("errorMessage", "Error occurred while retrieving users: " + e.getMessage());
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
            // If user is not logged in or not an admin, redirect to login page
            response.sendRedirect("login.jsp");
            return;
        }

        // Forward the request to the JSP for rendering
        request.getRequestDispatcher("/viewUsers.jsp").forward(request, response);
    }
}
