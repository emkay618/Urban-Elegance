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
        <table class="table">
            <thead>
                <tr>
                    <th>Order ID</th>
                    <th>User ID</th>
                    <th>Product ID</th>
                    <th>Order Quantity</th>
                    <th>Order Date</th>
                    <th>Action</th>
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
                        <td>
                            <button type="button" class="btn btn-primary" onclick="generateInvoice('${order.orderId}', '${order.userid}', '${order.productid}', '${order.quantity}', '${dateFormat.format(order.orderDate)}')">Generate Invoice</button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- Invoice container -->
        <div id="invoiceContainer" style="display: none;">
            <div class="invoice">
                <div class="invoice-header">
                    <div class="company-details">
                        <h2>Urban Elegance</h2>
                        <p>123 Willows, Bloemfontein, Bloemfontein, 9300</p>
                        <p>Phone: 123-456-7890</p>
                        <p>Email: info@UrbanElegance.com</p>
                    </div>
                    <div class="user-details">
                        <h2>BILL TO:</h2>
                        <p>123 Willows, Bloemfontein, Bloemfontein, 9300</p>
                        <p>Phone: 123-456-7890</p>
                        <p>Email: info@UrbanElegance.com</p>
                    </div>
                    <div class="invoice-details">
                        <h3>Invoice</h3>
                        <p>Invoice Number: #INV-001</p>
                        <p>Date: January 1, 2024</p>
                    </div>
                </div>
                <hr>
                <div id="invoiceContent">
                    <!-- Invoice details will be dynamically populated here -->
                </div>
                <hr>
                <div class="invoice-footer">
                    <button type="button" class="btn btn-secondary" onclick="closeInvoice()">Close</button>
                    <button type="button" class="btn btn-success" onclick="sendInvoice()">Send</button>
                </div>
            </div>
        </div>
        <%
                }
            } catch (Exception e) {
                e.printStackTrace();
                out.println("Error occurred while rendering orders: " + e.getMessage());
            }
        %>
        <%@include file="/includes/footer.jsp"%>
    </body>
</html>
