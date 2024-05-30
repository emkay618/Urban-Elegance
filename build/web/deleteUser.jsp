<%@ page import="java.util.List" %>
<%@ page import="com.moshao.model.User" %>
<%@ page import="com.moshao.dao.UserDao" %>
<%@ page import="com.moshao.connection.DbCon" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/includes/head.jsp" %>
        <title>UrbanElegance - Delete User</title>
    </head>
    <body>
        <%@ include file="/includes/navbar.jsp" %>

        <div class="container">
            <h1>Delete User</h1>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>User ID</th>
                        <th>Username</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Country</th>
                        <th>City</th>
                        <th>Postal Code</th>
                        <th>Street</th>
                        <th>User Type</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Iterate over each user and display details -->
                    <%
                        try {
                            // Get the real path to the SQL file
                            String realPath = request.getServletContext().getRealPath("/exportedDbFile/urbanelegance2128084.sql");
                            UserDao userDao = new UserDao(DbCon.getConnection(realPath));
                            List<User> users = userDao.getAllUsers();
                            for (User user : users) {
                    %>
                    <tr>
                        <td><%= user.getUserID()%></td>
                        <td><%= user.getUsername()%></td>
                        <td><%= user.getEmail()%></td>
                        <td><%= user.getPhone()%></td>
                        <td><%= user.getCountry()%></td>
                        <td><%= user.getCity()%></td>
                        <td><%= user.getPostalCode()%></td>
                        <td><%= user.getStreet()%></td>
                        <%--scriptlet directly assigns the user type based on the isAdmin property--%>
                        <%
                            String userType = user.isAdmin() ? "Admin" : "Customer";
                        %>
                        <td><%= userType%></td>

                        <td>
                            <form action="deleteUserServlet" method="post">
                                <input type="hidden" name="userId" value="<%= user.getUserID()%>">
                                <button type="submit" class="btn btn-danger">Delete</button>
                            </form>
                        </td>
                    </tr>
                    <%
                            }
                        } catch (Exception e) {
                            out.println("Error fetching and displaying users: " + e.getMessage());
                        }
                    %>
                </tbody>
            </table>
        </div>

        <%@ include file="/includes/footer.jsp" %>
    </body>
</html>
