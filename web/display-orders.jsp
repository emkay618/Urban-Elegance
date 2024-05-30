<%@page import="com.moshao.model.Cart"%>
<%@page import="com.moshao.dao.ProductDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.moshao.model.Product"%>
<%@page import="com.moshao.connection.DbCon"%>
<%@page import="com.moshao.model.Order"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/includes/head.jsp" %>
        <title>User Orders</title>
    </head>
    <body>
        <%@ include file="/includes/navbar.jsp" %>

        <div class="container my-3">
            <h1 class="text-center">User Orders</h1>
            <table class="table">
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Product ID</th>
                        <th>Quantity</th>
                        <th>Order Date</th>
                        <th>Price</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<Order> userOrders = (List<Order>) request.getAttribute("userOrders");
                        for (Order order : userOrders) {
                    %>
                    <tr>
                        <td><%= order.getOrderId()%></td>
                        <td><%= order.getProductid()%></td>
                        <td><%= order.getQuantity()%></td>
                        <td><%= order.getOrderDate()%></td>
                        <td><%= order.getPrice() %></td>
                    </tr>
                    <% }%>
                </tbody>
            </table>
        </div>

        <%@ include file="/includes/footer.jsp" %>
    </body>
</html>
