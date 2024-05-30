<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/includes/head.jsp"%>
        <meta charset="UTF-8">
        <title>Product Details</title>
        <style>
            .product-details {
                display: flex;
                align-items: flex-start; /* Align items from top */
                margin-top: 50px;
            }
            .product-details img {
                max-width: 60%;
                height: auto;
                margin-right: 30px; /* Add space between image and details */
            }
            .product-details .product-info {
                display: flex;
                flex-direction: row; /* Align sections horizontally */
                flex-wrap: wrap; /* Allow sections to wrap to next line if needed */
                width: 100%; /* Make sure it fills the width */
                height: 428px;
            }
            .product-details .product-info section {
                flex: 1; /* Make sections take equal width */
                border: 1px solid #ccc; /* Add border around each section */
                padding: 10px; /* Add padding inside each section */
                margin-right: 20px; /* Add space between sections */
                margin-bottom: 20px; /* Add space below sections */

            }
            .product-details .product-info section:last-child {
                margin-right: 0; /* Remove right margin from last section */
            }
            .product-details .product-info h3 {
                margin-top: 0; /* Remove top margin for section titles */
            }
            .product-details {
                width: 100%; /* Make price section take full width */
                text-align: center; /* Center align price and add to cart button */
            }
            .product-price-section {
                width: 100%; /* Make price section take full width */
                text-align: center; /* Center align price and add to cart button */
                height: 210px;
            }
            .product-details .product-availability {
                width: 100%; /* Make availability section take full width */
                text-align: center; /* Center align availability details */
            }
            .product-details .product-availability ul {
                list-style-type: disc; /* Use bullet points for list items */
                padding-left: 20px; /* Add indentation for list items */
                margin-top: 0; /* Remove top margin for list */
            }
            .product-details .product-availability li {
                margin-bottom: 5px; /* Add space between list items */
            }
            button {
                -webkit-text-size-adjust: 100%;
                --swiper-theme-color: #007aff;
                --toast-icon-margin-start: -3px;
                --toast-icon-margin-end: 4px;
                --toast-svg-margin-start: -1px;
                --toast-svg-margin-end: 0px;
                --toast-button-margin-start: auto;
                --toast-button-margin-end: 0;
                --toast-close-button-start: 0;
                --toast-close-button-end: unset;
                --toast-close-button-transform: translate(-35%, -35%);
                -webkit-font-smoothing: antialiased;
                vertical-align: middle;
                margin: 0 0 1rem 0;
                font-family: inherit;
                -webkit-appearance: none;
                line-height: 1;
                cursor: pointer;
                box-sizing: border-box;
                color: #fefefe;
                font-weight: bold;
                overflow: hidden;
                position: relative;
                text-align: center;
                transition: background-color 300ms, color 300ms, opacity 500ms, border-color 300ms;
                border-radius: 2px;
                font-size: 1rem;
                margin-bottom: 0;
                margin-top: 50px;
                min-width: 150px;
                display: block;
                width: 100%;
                margin-right: 0;
                margin-left: 0;
                background: #1c8644;
                padding: 12px 13px;
                border: 1px solid transparent;
                text-decoration: none;
            }
        </style>
    </head>
    <body>
        <%@include file="/includes/navbar.jsp"%>
        <h1>Product Details</h1>
        <div class="product-details">
            <c:if test="${empty product}">
                <p>Product not found.</p>
            </c:if>
            <c:if test="${not empty product}">
                <a href="ProductDetailsServlet?id=${product.productId}">
                    <img src="${product.imageUrl}" alt="${product.name}">
                </a>
                <div class="product-info">
                    <section>
                        <h3>${product.name}</h3>
                        <!-- Product details sections -->
                        <ul>
                            <li>Description: ${product.description}</li>
                            <li>Category: ${product.category}</li>
                            <li>Brand: ${product.price}</li>
                            <li>Color: ${product.quantity}</li>
                            <li>Size: ${product.size}</li>
                            <li>Material: ${product.color}</li>
                        </ul>
                    </section>
                    <section class="product-availability">
                        <h3>Availability</h3>
                        <ul>
                            <li>Availability: ${product.availability}</li>
                            <li>Discount: ${product.discount}</li>
                            <li>Rating: ${product.rating}</li>
                            <li>Release Date: ${product.releaseDate}</li>
                            <li>Promotion: ${product.promotion}</li>
                            <li>Shipping Weight: ${product.shippingWeight}</li>
                        </ul>
                    </section>
                    <!-- Product price and add to cart button -->
                    <section class="product-price-section">
                        <h3>Price</h3>
                        <p>FREE DELIVERY</p>
                        <p>$${product.price}</p>
                        <form action="AddToCartServlet" method="post">
                            <input type="hidden" name="productId" value="${product.id}">
                            <button type="submit">Add to Cart</button>
                        </form>
                    </section>
                </div>
            </c:if>
        </div>
        <%@include file="/includes/footer.jsp"%>
    </body>
</html>
