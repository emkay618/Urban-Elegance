<%@page import="java.util.Comparator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.moshao.model.Product" %>
<%@ page import="com.moshao.dao.ProductDao" %>
<%@ page import="com.moshao.connection.DbCon" %>

<%
    // Generating base URL
    String baseUrl = request.getRequestURL().toString().replace(request.getRequestURI(), request.getContextPath());
    // Get the selected sorting option from the request parameter
    String sortOption = request.getParameter("option");

    // Retrieve all products from the database
    String realPath = request.getServletContext().getRealPath("/exportedDbFile/urbanelegance2128084.sql");
    ProductDao pd = new ProductDao(DbCon.getConnection(realPath));
    List<Product> products = pd.getAllProducts();

    // Sort the products based on the selected option
    if (sortOption.equals("priceLowToHigh")) {
        products.sort(Comparator.comparingDouble(Product::getPrice));
    } else if (sortOption.equals("priceHighToLow")) {
        products.sort(( p1,     p2) -> Double.compare(p2.getPrice(), p1.getPrice()));
    } else if (sortOption.equals("newest")) {
        // Sort by productId (assuming it represents the creation order)
        products.sort(Comparator.comparingInt(Product::getProductId).reversed());
    }

    // Include the product list content here
    for (Product p : products) {
%>
<!-- Render product card -->
<div class="col-md-3 my-3">
    <div class="card w-100">
        <!-- Adjusting the image URL by prepending the base URL -->
        <img class="card-img-top" src="<%= baseUrl + "/" + p.getImageUrl()%>" alt="Card image cap">
        <div class="card-body">
            <h5 class="card-title"><%= p.getName()%></h5>
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
%>
