<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.moshao.model.*" %>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/includes/head.jsp" %>
        <meta charset="UTF-8">
        <title>Jewellery</title>
        <style>
            /* Define a grid layout */
            .image-grid {
                display: grid;
                grid-template-columns: repeat(3, 1fr); /* Four columns with equal width */
                gap: 20px; /* Add some spacing between grid items */
            }

            /* Style each grid item */
            .image-grid-item {
                width: 100%; /* Ensure each item fills the grid cell */
                overflow: hidden; /* Hide any content that overflows the container */
                margin-bottom: 20px; /* Add bottom margin for spacing */
            }

            .image-grid-item img {
                width: 100%; /* Make the image fill the container horizontally */
                height: auto; /* Automatically adjust height to maintain aspect ratio */
                object-fit: cover; /* Scale the image to cover the entire container */
                filter: brightness(120%); /* Increase brightness by 20% */
            }
        </style>
    </head>
    <body>
        <%@ include file="/includes/navbar.jsp" %>
        <div class="row">
            <div class="container">
                <div class="card-header my-3">All Jewellery</div>
                <c:if test="${empty jewellery}">
                    <p>No jewellery available.</p>
                </c:if>
                <c:forEach var="jewellery" items="${jewellery}">
                    <div class="col-md-5 my-3">
                        <div class="card w-800">
                            <div class="card-body">
                                <img src="${jewellery.imageUrl}" alt="${jewellery.name}">
                                <h2>${jewellery.name}</h2>
                                <p>Price: $${jewellery.price}</p>
                                <div class="mt-3 d-flex justify-content-between">
                                    <a class="btn btn-dark" href="add-to-cart?id=${jewellery.productId}">Add to Cart</a> 
                                    <a class="btn btn-primary" href="buy-now-servlet?id=${jewellery.productId}">Buy Now</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <%@ include file="/includes/footer.jsp" %>
    </body>
</html>
