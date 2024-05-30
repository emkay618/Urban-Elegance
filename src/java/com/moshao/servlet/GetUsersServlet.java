package com.moshao.servlet;

import com.google.gson.Gson;
import com.moshao.dao.UserDao;
import com.moshao.model.User;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GetUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Create UsertDao instance
        UserDao userDao = new UserDao();
        // Fetch user data from the database
        List<User> users = userDao.getAllUsers();

        // Convert user data to JSON
        Gson gson = new Gson();
        String json = gson.toJson(users);

        // Set response content type
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Write JSON data to response
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }
}
