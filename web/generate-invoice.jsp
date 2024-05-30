<%@page import="java.util.List"%>
<%@page import="com.moshao.connection.DbCon"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.moshao.dao.UserDao" %>
<%@ page import="com.moshao.model.User" %>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/includes/head.jsp" %>
        <title>Generate Invoice</title>
    </head>
    <body>
        <%@ include file="/includes/navbar.jsp" %>

        <div class="container my-3">
            <h1 class="text-center">Generate Invoice</h1>
            <form action="DisplayOrdersServlet" method="post">
                <div class="form-group">
                    <label for="userSelect">Select User:</label>
                    <select class="form-control" id="userSelect" name="userId">
                        <%
                            // Get the real path to the SQL file
                            String realPath = request.getServletContext().getRealPath("/exportedDbFile/urbanelegance2128084.sql");
                            UserDao userDao = new UserDao(DbCon.getConnection(realPath));
                            List<User> users = userDao.getCustomers();
                            for (User user : users) {
                        %>
                        <option value="<%= user.getUserID()%>"><%= user.getUsername()%></option>
                        <%
                            }
                        %>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Display Orders</button>
            </form>
        </div>

        <%@ include file="/includes/footer.jsp" %>
    </body>
</html>
