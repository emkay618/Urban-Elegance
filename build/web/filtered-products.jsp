<%@page import="java.util.List"%>
<%@page import="com.moshao.model.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Filtered Products</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container mt-5">
            <h2>Filtered Products</h2>
            <div class="row">
                <%
                    List<Product> filteredProductsList = (List<Product>) request.getAttribute("filteredProducts");
                    if (filteredProductsList != null && !filteredProductsList.isEmpty()) {
                        for (Product filteredProduct : filteredProductsList) {
                %>
                <div class="col-md-3 mb-4">
                    <div class="card">
                        <img src="<%= filteredProduct.getImageUrl()%>" class="card-img-top" alt="<%= filteredProduct.getName()%>">
                        <div class="card-body">
                            <h5 class="card-title"><%= filteredProduct.getName()%></h5>
                            <p class="card-text"><%= filteredProduct.getDescription()%></p>
                            <p class="card-text">Category: <%= filteredProduct.getCategory()%></p>
                            <p class="card-text">Price: $<%= filteredProduct.getPrice()%></p>
                            <p class="card-text">Quantity: <%= filteredProduct.getQuantity()%></p>
                            <p class="card-text">Quantity: <%= filteredProduct.getSize()%></p>
                            <p class="card-text">Quantity: <%= filteredProduct.getColor()%></p>
                        </div>
                    </div>
                </div>
                <%
                    }
                } else {
                %>
                <div class="col-md-12">
                    <p>No products found.</p>
                </div>
                <% }%>
            </div>
        </div>
    </body>
</html>
