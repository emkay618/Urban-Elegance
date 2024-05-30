<%@page import="com.moshao.connection.DbCon"%>
<%@page import="com.moshao.dao.ProductDao"%>
<%@page import="com.moshao.model.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    User auth = (User) request.getSession().getAttribute("auth");
    if (auth != null) {
        request.setAttribute("person", auth);
    }
    String realPath = request.getServletContext().getRealPath("/exportedDbFile/urbanelegance2128084.sql");
    ProductDao pd = new ProductDao(DbCon.getConnection(realPath));
    List<Product> products = pd.getAllProducts();
    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    if (cart_list != null) {
        request.setAttribute("cart_list", cart_list);
    }

    // Generating base URL
    String baseUrl = request.getRequestURL().toString().replace(request.getRequestURI(), request.getContextPath());
%>

<!DOCTYPE html>
<html>
    <head>
        <%@include file="/includes/head.jsp"%>
        <link rel="stylesheet" href="css/styles.css">
        <title>E-Commerce Cart</title>
        <style>
            /*Cataloque links*/
            .sale-link {
                margin-right: 10px; /* Adjust the space between links */
                text-decoration: none; /* Remove underline */
                color: black; /* Use the default text color */
                padding-right: 15px; /* Add right padding to each link */
                padding-left: 15px; /* Add left padding to each link */
                border: 2px solid transparent; /* Initially transparent border */
                margin-left: 10px;
            }

            .sale-link:hover {
                text-decoration: none; /* Remove underline on hover */
                border-radius: 20px; /* Add round border */
                border: 2px solid #f60; /* Red border */
            }
            /*Sorting options*/
            .sort-form {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 20px;
                padding: 10px;
                background-color: #f8f9fa;
                border-radius: 5px;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.15);
            }

            .sort-form select {
                margin-right: 10px;
            }
        </style>
    </head>
    <body>
        <%@include file="/includes/navbar.jsp"%>
        <div class="container">
            <div class="card-header my-3 font-weight-bold text-danger">
                BIG SALES:
                <a class="sale-link" href="shirts-servlet">Shirts</a> 
                <a class="sale-link" href="pants-servlet">Pants</a> 
                <a class="sale-link" href="suits-servlet">Suits</a> 
                <a class="sale-link" href="hats-servlet">Hats</a> 
                <a class="sale-link" href="belts-servlet">Belts</a> 
                <a class="sale-link" href="jewellery-servlet">Jewellery</a> 
                <a class="sale-link" href="shoes-servlet">Shoes</a> 
                <a class="sale-link" href="fragrances-servlet">Luxury Fragrances</a>
            </div>
            <!-- Add Sorting Options -->
            
            <div class="sort-options">
                <form id="sort-form" action="SortProductsServlet" method="GET">
                    <select name="sort" id="sort" class="form-control">
                        <option value="">Sort By</option>
                        <option value="priceLowToHigh">Price: Low to High</option>
                        <option value="priceHighToLow">Price: High to Low</option>
                        <option value="newest">Newest Arrivals</option>
                    </select>
                    <button id="sort-btn" class="btn btn-outline-success btn-sm my-2 my-sm-0" type="submit">Sort</button>
                </form>
            </div>

            <div class="carousel auto-scroll">
                <%
                    if (!products.isEmpty()) {
                        for (Product p : products) {
                %>
                <div class="carousel-item">
                    <a href="ProductDetailsServlet?id=<%= p.getProductId()%>">
                        <img src="<%= baseUrl + "/" + p.getImageUrl()%>" alt="<%= p.getName()%>">
                        <p>Price: $<%= p.getPrice()%></p>
                    </a>
                </div>

                <%
                        }
                    }
                %>
            </div>
            <div class="row">
                <div class="container">
                    <div class="card-header my-3">All Products</div>
                    <div class="row">

                        <%
                            if (request.getAttribute("filteredProducts") != null) {
                                // Include filtered-products.jsp content here
                        %>
                        <%@include file="filtered-products.jsp" %>
                        <%
                        } else {
                            if (!products.isEmpty()) {
                                for (Product p : products) {
                        %>
                        <div class="col-md-3 my-3">
                            <div class="card w-100">
                                <!-- Adjusting the image URL by prepending the base URL -->
                                <img class="card-img-top" src="<%= baseUrl + "/" + p.getImageUrl()%>" alt="Card image cap">
                                <div class="card-body">
                                    <h5 class="card-title"><%= p.getName()%></h5>
                                    <%--<h6 class="price">Description <%= p.getDescription() %></h6>--%>
                                    <h6 class="price">Price: $<%= p.getPrice()%></h6>
                                    <h6 class="category">Category: <%= p.getCategory()%></h6>
                                    <h6 class="quantity">Quantity: <%= p.getQuantity()%></h6>
                                    <div class="mt-3 d-flex justify-content-between">
                                        <a class="btn btn-dark" href="add-to-cart?id=<%= p.getProductId()%>">Add to Cart</a> 
                                        <a class="btn btn-primary" href="buy-now-servlet?id=<%= p.getProductId()%>">Buy Now</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <%
                                    }
                                } else {
                                    out.println("There is no product");
                                }
                            }
                        %>
                    </div>
                </div>
            </div>
        </div>

        <script>
            var carouselItems = Array.from(document.querySelectorAll('.carousel-item'));
            var currentIndex = 0;

            function updateCarousel() {
                carouselItems.forEach(function (item, index) {
                    if (index === currentIndex) {
                        item.classList.add('active');
                    } else {
                        item.classList.remove('active');
                    }
                });

                currentIndex = (currentIndex + 1) % carouselItems.length;
            }

            setInterval(updateCarousel, 5000); // Change active product every 5 seconds   

        </script>

        <%@include file="/includes/footer.jsp"%>
    </body>
</html>
