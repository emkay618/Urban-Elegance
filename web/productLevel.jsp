<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.moshao.model.Product" %>
<%@ page import="com.moshao.dao.ProductDao" %>
<%@ page import="com.moshao.connection.DbCon" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <script src="https://kit.fontawesome.com/92d70a2fd8.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="css/searchengine.css">
</head>
<body>
    <div class="container">
        <div class="sidebar">
            <div class="sidehead">
                <div class="dots">
                    <i class="fa-solid fa-circle"></i>
                    <i class="fa-solid fa-circle" style="color: #333;"></i>
                    <i class="fa-solid fa-circle"></i>
                </div>
                <hr style="margin: 15px 0; border: 1px solid #eee;">
            </div>
            <div class="sidebody" style="height: 69vh;">
                <div class="searchbar">
                    <input placeholder="Search..." id="searchBar" name="searchBar" type="text">
                    <i class="fa-solid fa-magnifying-glass glass"></i>
                </div>
            </div>
            <div class="sidefoot">
                <hr style="margin: 15px 0; border: 1px solid #eee;">
                <div class="social-icons">
                    <i class="fa-brands fa-square-facebook"></i>
                    <i class="fa-brands fa-youtube"></i>
                    <i class="fa-brands fa-square-github"></i>
                </div>
            </div>
        </div>
        <div class="data">
            <div class="top">
                <a class="nav-link" href="index.jsp">Home</a>
                <p>urbanElegance@gmail.com</p>
            </div>
            <div class="header">
                <p>BUY EVERYTHING ONLINE</p>
            </div>
            <div class="body">
                <div id="root">
                    <!-- Products will be displayed here -->
                    <c:forEach var="product" items="${products}">
                        <div class='box'>
                            <div class='img-box'>
                                <img class='images' src='<c:out value="${product.imageUrl}" />' alt='<c:out value="${product.name}" />'></img>
                            </div> 
                            <div class='bottom'>
                                <p><c:out value="${product.name}" /></p>
                                <h2>$ <c:out value="${product.price}" />.00</h2>
                                <button>Add to cart</button>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
    <script src="javascript/searchengine.js"></script>
</body>
</html>

<%-- Java code to retrieve products from database --%>
<%
     // Get the real path to the SQL file
    String realPath = request.getServletContext().getRealPath("/exportedDbFile/urbanelegance2128084.sql");
    
    ProductDao productDao = new ProductDao(DbCon.getConnection(realPath));
    List<Product> products = productDao.getAllProductsWithImageURLs();
    pageContext.setAttribute("products", products); // Set the "products" attribute in page context
%>
