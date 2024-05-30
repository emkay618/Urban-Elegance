<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <!-- Setting page language and encoding -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- Including JSTL Core tag library -->
<%@ page import="com.moshao.model.*" %> <!-- Importing necessary model classes -->
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/includes/head.jsp" %> <!-- Including head section -->
        <meta charset="UTF-8"> 
        <title>Belts</title>
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
        <%@ include file="/includes/navbar.jsp" %> <!-- Including navigation bar -->
        <div class="row">
            <div class="container">
                <div class="card-header my-3">All Belts</div> <!-- Card header for all belts -->
                <c:if test="${empty belts}"> <!-- Checking if belts list is empty -->
                    <p>No belts available.</p> <!-- Display message if no belts available -->
                </c:if>
                <c:forEach var="belts" items="${belts}"> <!-- Looping through belts -->
                    <div class="col-md-5 my-3">
                        <div class="card w-800">
                            <div class="card-body">
                                <img src="${belts.imageUrl}" alt="${belts.name}"> <!-- Displaying belt image -->
                                <h2>${belts.name}</h2> <!-- Displaying belt name -->
                                <p>Price: $${belts.price}</p> <!-- Displaying belt price -->
                                <div class="mt-3 d-flex justify-content-between">
                                    <a class="btn btn-dark" href="add-to-cart?id=${belts.productId}">Add to Cart</a> <!-- Button to add belt to cart -->
                                    <a class="btn btn-primary" href="buy-now-servlet?id=${belts.productId}">Buy Now</a> <!-- Button to buy belt -->
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <%@ include file="/includes/footer.jsp" %> <!-- Including footer section -->
    </body>
</html>
