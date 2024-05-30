package com.moshao.servlet;

import com.moshao.connection.DbCon;
import com.moshao.dao.UserDao;
import com.moshao.model.User;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetUserOrdersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get the real path to the SQL file
            String realPath = request.getServletContext().getRealPath("/exportedDbFile/urbanelegance2128084.sql");
            // Create UsertDao instance with database connection
            UserDao userDao = new UserDao(DbCon.getConnection(realPath));
            // Retrieve user data from the database
            List<User> users = userDao.getAllUsers();

            // Set the user list as a request attribute
            request.setAttribute("users", users);

            // Forward the request to the JSP page
            request.getRequestDispatcher("invoiceGeneration.jsp").forward(request, response);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle exceptions
            // Set error message as request attribute
            request.setAttribute("errorMessage", "Error occurred while retrieving user data: " + e.getMessage());
            // Forward the request to an error page
            request.getRequestDispatcher("error-page.jsp").forward(request, response);
        } catch (InterruptedException ex) {
            Logger.getLogger(GetUserOrdersServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
