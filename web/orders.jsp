<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.moshao.model.Order"%>
<%@page import="com.moshao.dao.OrderDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/includes/head.jsp"%>
        <script src="javascript/script.js"></script>
        <link rel="stylesheet" href="css/styles.css">
        <title>Orders</title>
    </head>
    <body>
        <%@include file="/includes/navbar.jsp"%>
        <%
            try {
                // Retrieve data from the servlet request attributes
                List<Order> orders = (List<Order>) request.getAttribute("orders");
                SimpleDateFormat dateFormat = (SimpleDateFormat) request.getAttribute("dateFormat");
                String errorMessage = (String) request.getAttribute("errorMessage");

                if (errorMessage != null) {
        %>
        <div class="alert alert-danger" role="alert">
            <%= errorMessage%>
        </div>
        <%
        } else {
        %>
        <div class="container my-3">
            <div class="card-header text-center"><h1>ORDERS</h1></div>
            <table class="table table-light">
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>User ID</th>
                        <th>Product ID</th>
                        <th>Order Quantity</th>
                        <th>Order Date</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Iterate over each order and display details -->
                    <c:forEach items="${orders}" var="order">
                        <tr>
                            <td>${order.orderId}</td>
                            <td>${order.userid}</td>
                            <td>${order.productid}</td>
                            <td>${order.quantity}</td>
                            <td>${dateFormat.format(order.orderDate)}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <%
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    out.println("Error occurred while rendering orders: " + e.getMessage());
                }
            %>
        </div>
        <%@include file="/includes/footer.jsp"%>
    </body>
</html>
