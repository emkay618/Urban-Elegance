package com.moshao.servlet;

import com.moshao.connection.DbCon;
import com.moshao.dao.UserDao;
import com.moshao.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GenerateInvoiceServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            // Get the real path to the SQL file
            String realPath = request.getServletContext().getRealPath("/exportedDbFile/urbanelegance2128084.sql");
            // Retrieve only customers from the database
            UserDao userDao = new UserDao(DbCon.getConnection(realPath));
            List<User> customers = userDao.getCustomers();

            // Pass the list of customers to the JSP for display
            request.setAttribute("customers", customers);
            request.getRequestDispatcher("generate-invoice.jsp").forward(request, response);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException ex) {
            Logger.getLogger(GenerateInvoiceServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handling of POST requests by invoking doGet
        doGet(request, response);
    }
}
