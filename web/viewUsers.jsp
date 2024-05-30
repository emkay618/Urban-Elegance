<%@ page import="java.util.List" %>
<%@ page import="com.moshao.model.User" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/includes/head.jsp"%>
    <title>Users</title>
</head>
<body>
    <%@include file="/includes/navbar.jsp"%>

    <div class="container">
        <%
            try {
                // Retrieve data from the servlet request attributes
                List<User> users = (List<User>) request.getAttribute("users");

                if (users != null && !users.isEmpty()) {
        %>
        <div class="card-header my-3">All Users</div>
        <div class="row">
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
                        <th>Action</th> <!-- Added column for Delete button -->
                    </tr>
                </thead>
                <tbody>
                    <!-- Iterate over each user and display details -->
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td>${user.userID}</td>
                            <td>${user.username}</td>
                            <td>${user.email}</td>
                            <td>${user.phone}</td>
                            <td>${user.country}</td>
                            <td>${user.city}</td>
                            <td>${user.postalCode}</td>
                            <td>${user.street}</td>
                            <%--EL's bracket notation to access the property--%>
                            <td>${user['isAdmin'] ? 'Admin' : 'Customer'}</td>
                            <td>
                                <!-- Delete button with onclick event to delete user -->
                                <button type="button" class="btn btn-danger" onclick="deleteUser('${user.userID}')">Delete</button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <% } else { %>
        <div class="alert alert-info" role="alert">
            No Users Found
        </div>
        <% } %>
        <% } catch (Exception e) {
                e.printStackTrace();
                out.println("Error occurred while rendering users: " + e.getMessage());
        }%>
    </div>

    <%@include file="/includes/footer.jsp"%>

    <!-- Script to delete user -->
    <script>
        function deleteUser(userId) {
            if (confirm("Are you sure you want to delete this user?")) {
                // Send an AJAX request to delete the user
                $.ajax({
                    type: "POST",
                    url: "deleteUserServlet",
                    data: {userId: userId},
                    success: function (response) {
                        // Reload the page after deletion
                        location.reload();
                    },
                    error: function (xhr, status, error) {
                        console.error(xhr.responseText);
                        alert("Error deleting user. Please try again.");
                    }
                });
            }
        }
    </script>
</body>
</html>
