package com.moshao.servlet;

import com.moshao.connection.DbCon;
import com.moshao.dao.UserDao;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get the real path to the SQL file
            String realPath = request.getServletContext().getRealPath("/exportedDbFile/urbanelegance2128084.sql");
            String userId = request.getParameter("userId");
            UserDao userDao = new UserDao(DbCon.getConnection(realPath));
            try {
                userDao.deleteUser(userId);//invoke method from userDao to delete user
                response.sendRedirect("deleteUserSuccess.jsp"); // Redirect to a success page
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect("deleteUserFailure.jsp"); // Redirect to a failure page
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DeleteUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DeleteUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(DeleteUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
