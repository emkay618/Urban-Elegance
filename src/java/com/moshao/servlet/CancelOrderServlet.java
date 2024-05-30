package com.moshao.servlet;

import com.moshao.connection.DbCon;
import com.moshao.dao.OrderDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CancelOrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try ( PrintWriter out = response.getWriter()) {
            // Get the real path to the SQL file
            String realPath = request.getServletContext().getRealPath("/exportedDbFile/urbanelegance2128084.sql");
            String id = request.getParameter("id");
            //check if there is any id or user logged in
            if (id != null) {
                OrderDao orderDao = new OrderDao(DbCon.getConnection(realPath));
                orderDao.cancelOrder(Integer.parseInt(id));//invoke  cancelOder method from OrderDao
            }
            response.sendRedirect("orders.jsp");//redict them to oders.jsp
        } catch (ClassNotFoundException | SQLException e) {

            e.printStackTrace();
        } catch (InterruptedException ex) {
            Logger.getLogger(CancelOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
