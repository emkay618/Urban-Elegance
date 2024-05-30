<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Product Catalog</title>
        <link rel="stylesheet" href="css/styles.css">
        <style>
            /* Add carousel styling */
            .product-list {
                display: flex;
                overflow: hidden; /* Hide overflowing items */
            }
            .product-card {
                flex: 0 0 auto; /* Prevent items from growing */
                width: 300px;
                margin-right: 20px; /* Adjust spacing between cards */
            }
            .product-card img {
                width: 100%;
                height: auto;
                cursor: pointer; /* Change cursor to pointer on hover */
            }

        </style>
    </head>
    <body>
        <h1>Product Catalog</h1>
        <div class="product-list">
            <c:forEach items="${products}" var="product">
                <div class="product-card">
                    <!-- Make the image clickable -->
                    <a href="ProductDetailsServlet?productId=${product.id}">
                        <img src="${product.photo}" alt="${product.name}">
                    </a>
                    <h2>${product.name}</h2>
                    <p>Price: $${product.price}</p>
                    <form action="CartServlet" method="post">
                        <input type="hidden" name="productId" value="${product.id}">
                    </form>
                </div>
            </c:forEach>
        </div>

        <!-- JavaScript for auto-scrolling -->
        <script>
            // Auto-scroll product cards right to left
            const productList = document.querySelector('.product-list');
            const productCards = document.querySelectorAll('.product-card');
            const cardWidth = productCards[0].offsetWidth + parseInt(getComputedStyle(productCards[0]).marginRight);
            let position = 0;

            function autoScroll() {
                position -= 1; // Move one pixel at a time
                productList.style.transform = `translateX(${position}px)`;

                if (position <= -cardWidth) {
                    position = 0; // Reset position to the beginning
                    productList.appendChild(productList.firstElementChild); // Move first card to end
                }
            }

            setInterval(autoScroll, 30); // Auto-scroll every 30 milliseconds
        </script>
    </body>
</html>
